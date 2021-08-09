package controller.control;

import controller.command.ColorTransformCommand;
import controller.command.ExitCommand;
import controller.command.FilterCommand;
import controller.command.ImageProcessorCommand;
import controller.command.LoadCommand;
import controller.command.MosaicCommand;
import controller.command.PatternCommand;
import controller.command.PixelateCommand;
import controller.command.ReductionCommand;
import controller.command.SaveCommand;
import controller.command.SavePatternCommand;
import controller.exception.CommandValidationException;
import controller.utilities.HelperController;
import model.ImageModel;
import model.exception.ModelValidationException;
import model.imageprocessor.BlurFilter;
import model.imageprocessor.GreyScaleTransformation;
import model.imageprocessor.MosaicOperator;
import model.imageprocessor.PatternGeneratorImpl;
import model.imageprocessor.PixelateOperator;
import model.imageprocessor.ReduceImage;
import model.imageprocessor.ReduceImageEssence;
import model.imageprocessor.SepiaToneTransformation;
import model.imageprocessor.SharpenFilter;
import model.images.Image;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

/**
 * This abstract class represents the Controller for the Image Processing Application.
 * The abstracted functionality for the console type controller and the GUI type 
 * controller has been abstracted in this.
 */
public abstract class AbstractImageController implements ImageController {
  protected final ImageModel model;
  protected final Map<String, Function<Scanner, ImageProcessorCommand<Image>>> knownCommands;

  /**
   * This is default constructor for the AbstractImageController which takes the
   * image model as the parameter.
   * 
   * @param model an object for the model for our Image Processing application
   */
  protected AbstractImageController(ImageModel model) {
    super();
    HelperController.isObjectNull(model);
    this.model = model;
    this.knownCommands = new HashMap<String, Function<Scanner, ImageProcessorCommand<Image>>>();
    populateKnownCommands();
  }

  private void populateKnownCommands() {
    knownCommands.put("load", (Scanner s) -> {
      return new LoadCommand(s.next(), s.next());
    });
    knownCommands.put("mosaic", s -> new MosaicCommand(new MosaicOperator(s.next())));
    knownCommands.put("save", s -> new SaveCommand(s.next(), s.next()));
    knownCommands.put("blur", s -> new FilterCommand(new BlurFilter()));
    knownCommands.put("sharpen", s -> new FilterCommand(new SharpenFilter()));
    knownCommands.put("sepia", s -> new ColorTransformCommand(new SepiaToneTransformation()));
    knownCommands.put("grey", s -> new ColorTransformCommand(new GreyScaleTransformation()));
    knownCommands.put("reduce", (Scanner s) -> {
      return new ReductionCommand(new ReduceImage(s.next()));
    });
    knownCommands.put("dither", (Scanner s) -> {

      return new ReductionCommand(new ReduceImageEssence(s.next()));
    });
    knownCommands.put("pixelate", (Scanner s) -> {
      return new PixelateCommand(new PixelateOperator(s.next()));
    });
    knownCommands.put("pattern", (Scanner s) -> {
      return new PatternCommand(new PatternGeneratorImpl());
    });
    knownCommands.put("savePattern",
        s -> new SavePatternCommand(s.next(), s.next(), new PatternGeneratorImpl()));
    knownCommands.put("quit", s -> new ExitCommand());
  }

  protected Image execute(Scanner scan)
      throws IOException, CommandValidationException, ModelValidationException {
    HelperController.isObjectNull(scan);
    ImageProcessorCommand<Image> imgCommand;
    String input = scan.next();
    Function<Scanner, ImageProcessorCommand<Image>> cmd = null;
    HelperController.validateCommand(input, knownCommands);
    cmd = knownCommands.getOrDefault(input, null);
    if (cmd == null) {
      throw new IllegalArgumentException();
    } else {
      imgCommand = cmd.apply(scan);
      return imgCommand.execute(model);

    }

  }

}
