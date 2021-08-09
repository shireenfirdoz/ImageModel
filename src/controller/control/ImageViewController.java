package controller.control;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import controller.command.ImageProcessorCommand;
import controller.command.pattern.CreateCustomDmcPattern;
import controller.command.pattern.DmcPatternCommand;
import controller.command.pattern.RemoveDmcPatternCommand;
import controller.command.pattern.SwapDmcPatternCommand;
import controller.exception.CommandValidationException;
import controller.utilities.ControllerConstant;
import controller.utilities.HelperController;
import imageview.ImageView;
import model.ImageModel;
import model.exception.ModelValidationException;
import model.imageprocessor.PatternGeneratorImpl;
import model.images.Image;
import model.images.RgbImage;
import model.pattern.ImagePattern;
import model.pixel.Colour;
import model.pixel.Position;
import model.utilities.Helper;
import model.utilities.ImageUtilities;

/**
 * This class represents the controller for the Graphical User Interface for the
 * Image Processing Application. This controller interacts with the GUI view for
 * the application and the image model to perform the various operations
 * available.
 */
public class ImageViewController extends AbstractImageController implements ImageFeatures {

  private final ImageView imageView;
  private final Map<String, Function<Scanner, ImageProcessorCommand<ImagePattern>>> patternCommand;
  private boolean isPatternGenerated;
  private boolean isImageLoaded;
  private StringBuilder command;
  private Image outputImage;

  /**
   * Constructor for the GUI Controller that interacts with the view and the model
   * for the Image Processing Application.
   * 
   * @param model     the Image model
   * @param imageView the view Image View
   */
  public ImageViewController(ImageModel model, ImageView imageView) {
    super(model);
    HelperController.isObjectNull(imageView);
    HelperController.isObjectNull(model);
    imageView.setFeatures(this);
    this.imageView = imageView;
    this.patternCommand = new HashMap<>();
    populateKnownCommands();
    isPatternGenerated = false;
    isImageLoaded = false;
    command = new StringBuilder();
    outputImage = null;
  }

  private void populateKnownCommands() {

    patternCommand.put("dmcPattern", (Scanner s) -> {
      return new DmcPatternCommand(new PatternGeneratorImpl());
    });

    patternCommand.put("swapDmcPattern", (Scanner s) -> {
      return new SwapDmcPatternCommand(new PatternGeneratorImpl(), s.next(), s.next(), s.next());
    });

    patternCommand.put("removeDmcPattern", (Scanner s) -> {
      return new RemoveDmcPatternCommand(new PatternGeneratorImpl(), s.next(), s.next());
    });

    patternCommand.put("customDmcPattern", (Scanner s) -> {
      return new CreateCustomDmcPattern(new PatternGeneratorImpl(), s.next());
    });

  }

  @Override
  public void start() throws IOException {
    this.imageView.setVisible();
  }

  @Override
  public void exitProgram() {
    System.exit(0);

  }

  @Override
  public void blur() {
    try {
      executeCommand("blur");
      setBufferedImageView(outputImage);
    } catch (CommandValidationException | ModelValidationException e) {
      this.errorMessage(e.getMessage());
    } catch (IOException e) {
      this.errorMessage(ControllerConstant.FILE_READ_WRITE_ERROR);
    }

  }

  private void executeCommand(String operation)
      throws IOException, CommandValidationException, ModelValidationException {
    HelperController.isObjectNull(operation);

    InputStream inputStream = new ByteArrayInputStream(operation.getBytes());
    Scanner scan = new Scanner(inputStream);
    try {
      while (scan.hasNext()) {
        outputImage = this.execute(scan);
      }

    } finally {
      scan.close();
    }
  }

  private void errorMessage(String message) {
    HelperController.isObjectNull(message);
    this.imageView.errorMessage(message);
  }

  private void infoMessage(String message) {
    HelperController.isObjectNull(message);
    this.imageView.infoMessage(message);
  }

  private void successMessage(String message) {
    HelperController.isObjectNull(message);
    this.imageView.successMessage(message);
  }

  private ImagePattern executePattern(String operation)
      throws IOException, CommandValidationException, ModelValidationException {

    HelperController.isObjectNull(operation);
    ImageProcessorCommand<ImagePattern> imgCommand;

    InputStream inputStream = new ByteArrayInputStream(operation.getBytes());
    Scanner scan = new Scanner(inputStream);

    String input = scan.next();
    Function<Scanner, ImageProcessorCommand<ImagePattern>> cmd = null;
    HelperController.validatePatternCommand(input, patternCommand);
    cmd = patternCommand.getOrDefault(input, null);
    try {
      if (cmd == null) {
        throw new IllegalArgumentException();
      } else {
        imgCommand = cmd.apply(scan);
        return imgCommand.execute(model);

      }
    } finally {
      scan.close();
    }
  }

  @Override
  public void loadFile() {
    // file name will be validated by model
    try {
      if (isImageLoaded) {
        this.imageView.removePreviousImage();
      }
      this.imageView.getImage();
      String fileName = this.imageView.getFileName();
      HelperController.isObjectNullStr(fileName, "file name cannot be blank");
      executeCommand("load " + fileName + " n");
      isImageLoaded = true;
      isPatternGenerated = false;
    } catch (CommandValidationException | ModelValidationException e) {
      this.errorMessage(e.getMessage());
    } catch (IOException e) {
      this.errorMessage("ControllerConstant.FILE_READ_WRITE_ERROR");
    }

  }

  @Override
  public void sharpen() {
    try {
      executeCommand("sharpen");
      setBufferedImageView(outputImage);
    } catch (CommandValidationException | ModelValidationException e) {
      this.errorMessage(e.getMessage());
    } catch (IOException e) {
      this.errorMessage("ControllerConstant.FILE_READ_WRITE_ERROR");
    }

  }

  @Override
  public void sepiaTone() {
    try {
      executeCommand("sepia");
      setBufferedImageView(outputImage);
    } catch (CommandValidationException | ModelValidationException e) {
      this.errorMessage(e.getMessage());
    } catch (IOException e) {
      this.errorMessage("ControllerConstant.FILE_READ_WRITE_ERROR");
    }
  }

  private void setBufferedImageView(Image image) {
    HelperController.isObjectNull(image);
    this.imageView.showBufferedImage(ImageUtilities.getBufferedImage(
        Helper.createImgIntArray(image), image.getImageWidth(), image.getImageHeight()));
  }

  @Override
  public void greyScale() {
    try {
      executeCommand("grey");
      setBufferedImageView(outputImage);
    } catch (CommandValidationException | ModelValidationException e) {
      this.errorMessage(e.getMessage());
    } catch (IOException e) {
      this.errorMessage("ControllerConstant.FILE_READ_WRITE_ERROR");
    }
  }

  @Override
  public void dither() {
    String maxColor = this.imageView.getInput("Dither Parameter: ");

    if (maxColor == null || (maxColor != null && "".equals(maxColor))) {
      this.errorMessage("Please input the correct Reduction parameter");
    } else {

      try {

        executeCommand("dither " + maxColor);
        setBufferedImageView(outputImage);
      } catch (CommandValidationException | ModelValidationException e) {
        this.errorMessage(e.getMessage());
      } catch (IOException e) {
        this.errorMessage("ControllerConstant.FILE_READ_WRITE_ERROR");
      }
    }
  }

  @Override
  public void mosaic() {
    String seedLength = this.imageView.getInput("Mosaic Parameter: ");

    if (seedLength == null || (seedLength != null && "".equals(seedLength))) {
      this.errorMessage("Please input the correct mosaic parameter");
    } else {
      try {
        executeCommand("mosaic " + seedLength);
        setBufferedImageView(outputImage);
      } catch (CommandValidationException | ModelValidationException e) {
        this.errorMessage(e.getMessage());
      } catch (IOException e) {
        this.errorMessage("ControllerConstant.FILE_READ_WRITE_ERROR");
      }
    }
  }

  @Override
  public void pixelate() {

    String pixelation = this.imageView.getInput("pixelate Parameter: ");

    if (pixelation == null || (pixelation != null && "".equals(pixelation))) {
      this.errorMessage("Please input the correct pixelation parameter");
    } else {

      try {
        executeCommand("pixelate " + pixelation);
        setBufferedImageView(outputImage);
      } catch (CommandValidationException | ModelValidationException e) {
        this.errorMessage(e.getMessage());
      } catch (IOException e) {
        this.errorMessage("ControllerConstant.FILE_READ_WRITE_ERROR");
      }
    }
  }

  @Override
  public void reduceColor() {

    String maxColor = this.imageView.getInput("Reduction Parameter: ");

    if (maxColor == null || (maxColor != null && "".equals(maxColor))) {
      this.errorMessage("Please input the correct Reduction parameter");
    } else {

      try {
        executeCommand("reduce " + maxColor);
        setBufferedImageView(outputImage);
      } catch (CommandValidationException | ModelValidationException e) {
        this.errorMessage(e.getMessage());
      } catch (IOException e) {
        this.errorMessage("ControllerConstant.FILE_READ_WRITE_ERROR");
      }
    }
  }

  @Override
  public void saveFile(String fileName) {
    try {
      HelperController.isObjectNullStr(fileName, "file name cannot be blank");
      executeCommand("save " + fileName + " n");
    } catch (CommandValidationException | ModelValidationException e) {
      this.errorMessage(e.getMessage());
    } catch (IOException e) {
      this.errorMessage("ControllerConstant.FILE_READ_WRITE_ERROR");
    }
  }

  @Override
  public void generatePattern() {
    this.successMessage(
        "Cross stitched pattern generation operation will take some time to complete ");
    try {
      ImagePattern imagePattern = executePattern("dmcPattern");

      this.getDmcPallete();
      updateViewPatternOperation(imagePattern);
      this.imageView.showPatternView();
      isPatternGenerated = true;
      this.successMessage("Cross stitched pattern generation operation completed succesfully");
    } catch (CommandValidationException | ModelValidationException e) {
      this.errorMessage(e.getMessage());
    } catch (IOException e) {
      this.errorMessage("ControllerConstant.FILE_READ_WRITE_ERROR");
    }

  }

  @Override
  public void swapDmcColorMenuClick() {
    if (isPatternGenerated) {
      this.imageView.showDmcPallete(false);
    } else {
      this.infoMessage("Please click on generate pattern and then click on swap operation");
    }
  }

  private void getDmcPallete() {
    Map<String, Colour> dmcPalleteMap = null;
    // As this is the single operation hence not used command design pattern for it.
    try {
      dmcPalleteMap = model.getDmcColorPalleteMap();
      this.imageView.getDmcPalleteMap(dmcPalleteMap);
    } catch (IOException e) {
      this.errorMessage("Technical issue occured while reading file");
    }

  }

  @Override
  public void savePattern(String fileName) {
    try {
      HelperController.isObjectNullStr(fileName, "file name cannot be blank");
      executeCommand("savePattern " + fileName + " n");
    } catch (CommandValidationException | ModelValidationException e) {
      this.errorMessage(e.getMessage());
    } catch (IOException e) {
      this.errorMessage("ControllerConstant.FILE_READ_WRITE_ERROR");
    }
  }

  @Override
  public void customDmcPatternMenuClick() {
    if (isPatternGenerated) {
      this.imageView.showDmcPallete(true);
    } else {
      this.infoMessage(
          "Please click on generate pattern and then click on custom dmc color pattern "
              + "generation operation");
    }
  }

  @Override
  public void executeBatch() {
    String input = command.toString();
    try {
      if (input == null || "".equals(input)) {
        this.imageView.errorMessage("Please write the commands and then execute");
      } else {
        executeCommand(input);
        command = new StringBuilder();
      }
    } catch (CommandValidationException | ModelValidationException e) {
      this.errorMessage(e.getMessage());
    } catch (IOException e) {
      this.errorMessage("ControllerConstant.FILE_READ_WRITE_ERROR");
    }
  }

  private void updateViewPatternOperation(ImagePattern imagePattern) {
    HelperController.isObjectNull(imagePattern);

    this.imageView.showPatternDimension(imagePattern.getHeight(), imagePattern.getWidth());
    this.imageView.showLegend(imagePattern.getLegend());
    Image image = new RgbImage(imagePattern.getPixelArray(), imagePattern.getPixelArray().length,
        imagePattern.getPixelArray()[0].length);
    this.imageView.showImagePattern(ImageUtilities.getBufferedImage(Helper.createImgIntArray(image),
        image.getImageWidth(), image.getImageHeight()), imagePattern.getImageMap());
  }

  private Position getPosition(Position position) {
    HelperController.isObjectNull(position);
    if (position.getPositionHeight() < 0 || position.getPositionWidth() < 0) {
      this.infoMessage("Please select the dmc color to be replaced by clicking on image pattern"
          + " and then perform the operation");
      return null;
    }
    return position;
  }

  @Override
  public void swapDmcColor(Position position, String dmcColorName) {
    try {
      HelperController.isObjectNull(position);
      Position newPosition = getPosition(position);
      if (newPosition != null) {
        if (dmcColorName != null && !"".equalsIgnoreCase(dmcColorName)) {
          StringBuilder sb = new StringBuilder();
          sb.append("swapDmcPattern ");
          sb.append(String.valueOf(position.getPositionWidth()) + " ");
          sb.append(String.valueOf(position.getPositionHeight()) + " ");
          sb.append(dmcColorName);
          ImagePattern imagePattern = executePattern(sb.toString());
          updateViewPatternOperation(imagePattern);
          this.successMessage(
              "Cross stitched pattern swap dmc color operation completed succesfully");
        } else {
          this.infoMessage("Please select the dmc color to be replaced with from the pallete");
        }
      }
    } catch (CommandValidationException | ModelValidationException e) {
      this.errorMessage(e.getMessage());
    } catch (IOException e) {
      this.errorMessage("ControllerConstant.FILE_READ_WRITE_ERROR");
    }

  }

  @Override
  public void removeDmcColor(Position position) {
    HelperController.isObjectNull(position);
    try {
      Position newPosition = getPosition(position);
      if (newPosition != null) {

        StringBuilder sb = new StringBuilder();
        sb.append("removeDmcPattern ");
        sb.append(String.valueOf(position.getPositionWidth()) + " ");
        sb.append(String.valueOf(position.getPositionHeight()));
        ImagePattern imagePattern = executePattern(sb.toString());
        updateViewPatternOperation(imagePattern);
        this.successMessage(
            "Cross stitched pattern remove dmc color operation completed succesfully");
      }
    } catch (CommandValidationException | ModelValidationException e) {
      this.errorMessage(e.getMessage());
    } catch (IOException e) {
      this.errorMessage("ControllerConstant.FILE_READ_WRITE_ERROR");
    }

  }

  @Override
  public void customDmcColorPattern(List<String> dmcColorList) {
    HelperController.isObjectNull(dmcColorList);

    try {
      if (!dmcColorList.isEmpty()) {
        StringBuilder sb = new StringBuilder();
        sb.append("customDmcPattern ");
        StringBuilder sbdmc = new StringBuilder();
        for (int i = 0; i < dmcColorList.size(); i++) {
          sbdmc.append(dmcColorList.get(i));
          if (i != dmcColorList.size() - 1) {
            sbdmc.append(",");
          }
        }
        sb.append(sbdmc.toString());
        ImagePattern imagePattern = executePattern(sb.toString());
        updateViewPatternOperation(imagePattern);
        this.successMessage(
            "Cross stitched pattern create with custom dmc color operation completed succesfully");
      } else {
        this.imageView.errorMessage(
            "Please select the list of dmc color to be used for generating th pattern");
      }

    } catch (CommandValidationException | ModelValidationException e) {
      this.errorMessage(e.getMessage());
    } catch (IOException e) {
      this.errorMessage("ControllerConstant.FILE_READ_WRITE_ERROR");
    }
  }

  @Override
  public void removeDmcColorMenuClick() {
    if (isPatternGenerated) {
      this.imageView.showRemoveDmcColorView();
    } else {
      this.infoMessage(
          "Please click on generate pattern and then click on remove dmc color pattern "
              + "generation operation");
    }
  }

  @Override
  public void loadCommand() {
    String fileName = this.imageView.getInput("Please enter the file name");
    String string = "load " + fileName + " y";
    command.append(string);
    this.imageView.appendTextArea(string + "\n");

  }

  @Override
  public void saveCommand() {
    String fileName = this.imageView.getInput("Please enter the file name to save image");
    command.append(" ");
    String string = "save " + fileName + " y";
    command.append(string);
    this.imageView.appendTextArea(string + "\n");

  }

  @Override
  public void savePatternCommand() {
    String fileName = this.imageView.getInput("Please enter the file name to save pattern");
    command.append(" ");
    String string = "savePattern " + fileName + " y";
    command.append(string);
    this.imageView.appendTextArea(string + "\n");

  }

  @Override
  public void blurCommand() {
    String string = "blur";
    command.append(" ");
    command.append(string);
    this.imageView.appendTextArea(string + "\n");
  }

  @Override
  public void sharpenCommand() {
    String string = "sharpen";
    command.append(" ");
    command.append(string);
    this.imageView.appendTextArea(string + "\n");

  }

  @Override
  public void greyScaleCommand() {
    String string = "grey";
    command.append(" ");
    command.append(string);
    this.imageView.appendTextArea(string + "\n");

  }

  @Override
  public void sepiaCommand() {
    String string = "sepia";
    command.append(" ");
    command.append(string);
    this.imageView.appendTextArea(string + "\n");

  }

  @Override
  public void ditherCommand() {
    String maxColor = this.imageView.getInput("Dither Parameter: ");
    String string = "dither " + maxColor;
    command.append(" ");
    command.append(string);
    this.imageView.appendTextArea(string + "\n");

  }

  @Override
  public void mosaicCommand() {
    String seedLenth = this.imageView.getInput("Mosaic Parameter: ");
    String string = "mosaic " + seedLenth;
    command.append(" ");
    command.append(string);
    this.imageView.appendTextArea(string + "\n");
  }

  @Override
  public void reduceCommand() {
    String maxColor = this.imageView.getInput("Reduction Parameter: ");
    String string = "reduce " + maxColor;
    command.append(" ");
    command.append(string);
    this.imageView.appendTextArea(string + "\n");
  }

  @Override
  public void pixelateCommand() {
    String pixelation = this.imageView.getInput("Pixelate Parameter: ");
    String string = "pixelate " + pixelation;
    command.append(" ");
    command.append(string);
    this.imageView.appendTextArea(string + "\n");

  }

  @Override
  public void patternCommand() {
    String string = "pattern";
    command.append(" ");
    command.append(string);
    this.imageView.appendTextArea(string + "\n");

  }

}
