package imageview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.control.ImageFeatures;
import imageview.colorpallete.CustomCheckboxListRenderer;
import imageview.colorpallete.CustomListItem;
import imageview.colorpallete.CustomListRenderer;
import model.pixel.Colour;
import model.pixel.Position;
import model.pixel.PositionImpl;
import model.pixel.RgbColor;
import model.pixel.SuperPixel;
import view.utilities.HelperView;
import view.utilities.ViewConstant;

/**
 * This class represents the view i.e. the Graphical User Interface for the user
 * to interact with the Image Processing Application. This class implements the
 * ImageVinew interface and uses Java Swing Library to render different GUI
 * components. This class handles the user interaction and calls the respective
 * functionality in the model via the controller.
 */
public class ImageViewImpl extends JFrame implements ImageView {

  /**
   * This is generated version id.
   */
  private static final long serialVersionUID = 2167543866451576740L;

  private JButton executeBatch;
  private JPanel batchFile;
  private JTextArea scriptTextArea;

  private JMenuItem menuItemCreateBatch;

  private JButton loadButton;
  private JButton saveImageButton;
  private JButton savePatternButton;
  private JButton blurButton;
  private JButton sharpenButton;
  private JButton sepiaButton;
  private JButton greyToneButton;
  private JButton mosaicButton;
  private JButton pixelateButton;
  private JButton patternButton;
  private JButton ditherButton;
  private JButton reduceButton;
  private JButton clearButton;

  private JPanel rightPanelBatch;
  private JPanel southPanelBatch;

  private JMenuItem menuItemQuit;
  private String filename;
  private JMenuItem menuItemLoad;
  private JMenuItem menuItemBlur;
  private JMenuItem menuItemSharpen;
  private JMenuItem menuItemGrey;
  private JMenuItem menuItemSepia;
  private JMenuItem menuItemDither;
  private JMenuItem menuItemReduce;
  private JMenuItem menuItemMosaic;
  private JMenuItem menuItemPixelate;
  private JMenuItem menuItemSaveImgFile;
  private JMenuItem menuItemPattern;
  private JMenuItem menuItemSavePattern;

  private ScrollPanel scrollPanel;
  private List<String> selectedDmcColor;
  private String selectedDmcColorStr;
  private Map<String, Colour> dmcColorMap;
  private JMenuItem menuItemSwapDmc;

  private Map<String, String> legend;

  private JPanel dmcPanel;
  private JPanel southPanel;

  private JLabel patternDimension;

  private JButton swapDmcButton;

  private JMenuItem menuItemRemoveDmc;
  private JMenuItem menuItemCustomDmc;

  private JButton removeDmcButton;
  private JButton createCustomDmcButton;

  private JPanel legendPanel;
  private JPanel dmcRadioPanel;
  private JPanel rightPanel;

  /**
   * Constructor to initialize the view for the application. The controller
   * interacts with the view and the model to perform the operation as requested
   * by the user through the view.
   * 
   * @param caption the caption to show
   */
  public ImageViewImpl(String caption) {
    super(caption);
    this.filename = null;
    this.menuItemQuit = new JMenuItem("Quit");

    menuItemBlur = new JMenuItem("Blur");
    menuItemSharpen = new JMenuItem("Sharpen");
    menuItemGrey = new JMenuItem("Grey Scale");
    menuItemSepia = new JMenuItem("Sepia Tone");
    menuItemReduce = new JMenuItem("Color Reduciton");
    menuItemDither = new JMenuItem("Dithering");
    menuItemMosaic = new JMenuItem("Mosaic");
    menuItemPixelate = new JMenuItem("Pixelate");
    menuItemPattern = new JMenuItem("Generate Pattern");
    menuItemSaveImgFile = new JMenuItem("Save Image");
    menuItemSavePattern = new JMenuItem("Save Pattern");

    selectedDmcColor = new ArrayList<String>();
    dmcColorMap = new TreeMap<String, Colour>();
    menuItemSwapDmc = new JMenuItem("Swap DMC Color");
    legend = new TreeMap<String, String>();
    rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

    dmcPanel = new JPanel();
    patternDimension = new JLabel();
    southPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    swapDmcButton = new JButton();
    selectedDmcColorStr = new String();

    menuItemRemoveDmc = new JMenuItem("Remove DMC Color");
    menuItemCustomDmc = new JMenuItem("Create Custom Pattern");
    removeDmcButton = new JButton();
    createCustomDmcButton = new JButton();
    legendPanel = new JPanel();

    menuItemCreateBatch = new JMenuItem("Batch File");

    executeBatch = new JButton("Execute Script");
    batchFile = new JPanel();
    scriptTextArea = new JTextArea(20, 20);
    scriptTextArea.setEditable(false);
    rightPanelBatch = new JPanel(new GridLayout(12, 1));

    loadButton = new JButton("Load");
    saveImageButton = new JButton("Save Image");
    savePatternButton = new JButton("Save Pattern");
    blurButton = new JButton("Blur");
    sharpenButton = new JButton("Sharpen");
    sepiaButton = new JButton("Sepia Tone");
    greyToneButton = new JButton("Grey Tone");
    mosaicButton = new JButton("Mosaic");
    pixelateButton = new JButton("Pixelate");
    patternButton = new JButton("Pattern");
    ditherButton = new JButton("Dither");
    reduceButton = new JButton("Reduce");
    clearButton = new JButton("Clear");

    southPanelBatch = new JPanel(new FlowLayout(FlowLayout.LEFT));
    dmcRadioPanel = new JPanel();

    this.setSize(500, 400);
    this.setLocation(200, 200);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // necessary so it closes correctly
    this.setLayout(new BorderLayout());

    this.setJMenuBar(createMenu());

    scrollPanel = new ScrollPanel();
    this.add(scrollPanel, BorderLayout.CENTER);

    this.getContentPane().add(rightPanel, BorderLayout.EAST);
    rightPanel.add(dmcPanel);
    rightPanel.add(legendPanel);
    rightPanel.add(dmcRadioPanel);

    this.getContentPane().add(southPanel, BorderLayout.SOUTH);
    southPanel.add(patternDimension);

  }

  private JMenuBar createMenu() {

    JMenuBar menuBar = new JMenuBar();

    JMenu menuHome = new JMenu("Home");
    menuBar.add(menuHome);

    menuItemLoad = new JMenuItem("Load Image");
    menuHome.add(menuItemLoad);

    JMenu subMenuImage = new JMenu("Image Operation");
    menuHome.add(subMenuImage);

    subMenuImage.add(menuItemBlur);
    subMenuImage.add(menuItemSharpen);
    subMenuImage.add(menuItemGrey);
    subMenuImage.add(menuItemSepia);
    subMenuImage.add(menuItemReduce);
    subMenuImage.add(menuItemDither);
    subMenuImage.add(menuItemMosaic);
    subMenuImage.add(menuItemPixelate);
    subMenuImage.add(menuItemSaveImgFile);

    JMenu menuPattern = new JMenu("Cross Stitched Pattern");
    menuHome.add(menuPattern);

    menuPattern.add(menuItemPattern);
    menuPattern.add(menuItemSavePattern);
    menuPattern.add(menuItemSwapDmc);
    menuPattern.add(menuItemRemoveDmc);
    menuPattern.add(menuItemCustomDmc);

    menuHome.add(menuItemCreateBatch);

    menuItemCreateBatch.addActionListener(e -> this.showBatchFile());

    menuHome.add(menuItemQuit);

    JMenu menuHelp = new JMenu("Help");
    menuBar.add(menuHelp);

    JMenuItem menuItemInstructions = new JMenuItem("Instructions");
    menuHelp.add(menuItemInstructions);

    menuItemInstructions.addActionListener(e -> this.showInstructions());

    JMenuItem menuItemAbout = new JMenuItem("About");
    menuHelp.add(menuItemAbout);

    menuItemAbout.addActionListener(e -> this.showAbout());

    return menuBar;
  }

  private void showBatchFile() {

    resetForBatchExecution();
    rightPanelBatch.setVisible(true);
    southPanelBatch.setVisible(true);
    batchFile.setVisible(true);
    this.add(batchFile, BorderLayout.CENTER);
    this.getContentPane().add(rightPanelBatch, BorderLayout.EAST);
    this.getContentPane().add(southPanelBatch, BorderLayout.SOUTH);
    batchFile.add(scriptTextArea);

    rightPanelBatch.add(loadButton);
    rightPanelBatch.add(blurButton);
    rightPanelBatch.add(sharpenButton);
    rightPanelBatch.add(sepiaButton);
    rightPanelBatch.add(greyToneButton);
    rightPanelBatch.add(mosaicButton);
    rightPanelBatch.add(ditherButton);
    rightPanelBatch.add(reduceButton);
    rightPanelBatch.add(pixelateButton);
    rightPanelBatch.add(saveImageButton);
    rightPanelBatch.add(patternButton);
    rightPanelBatch.add(savePatternButton);
    rightPanelBatch.add(clearButton);
    southPanelBatch.add(executeBatch);

  }

  @Override
  public void appendTextArea(String command) {
    HelperView.isObjectNull(command);
    scriptTextArea.append(command);
  }

  @Override
  public void removePreviousImage() {
    this.scrollPanel.removeAll();
    revalidate();
    repaint();
  }

  @Override
  public void getImage() {

    JFileChooser fileChooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Image", "jpg", "png", "gif",
        "bmp");
    fileChooser.setFileFilter(filter);
    int result = fileChooser.showOpenDialog(this);
    if (result == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();

      this.filename = file.getAbsolutePath();
      try {
        BufferedImage image = ImageIO.read(file);
        if (image == null) {
          HelperView.genericMessageDialogError(this, ViewConstant.IMAGE_LOAD_ERROR, "Image");
        }

        scrollPanel.setImageOnLoad(image);
        revalidate();
        repaint();
      } catch (IOException ex) {

        HelperView.genericMessageDialogError(this, ViewConstant.IMAGE_LOAD_ERROR, "Image");

      }
    }
  }

  @Override
  public void showBufferedImage(BufferedImage image) {
    HelperView.isObjectNull(image);
    scrollPanel.setImage(image);
    revalidate();
    repaint();
  }

  @Override
  public String getInput(String message) {
    return HelperView.genericMessageDialogInput(message);
  }

  @Override
  public void setFeatures(ImageFeatures feature) {
    menuItemQuit.addActionListener(e -> {
      feature.exitProgram();
    });

    menuItemLoad.addActionListener(e -> {
      resetForImageOperation();
      feature.loadFile();

    });

    menuItemBlur.addActionListener(e -> {
      resetForImageOperation();
      feature.blur();
    });

    menuItemSharpen.addActionListener(e -> {
      resetForImageOperation();
      feature.sharpen();
    });

    menuItemGrey.addActionListener(e -> {
      resetForImageOperation();
      feature.greyScale();
    });

    menuItemSepia.addActionListener(e -> {
      resetForImageOperation();
      feature.sepiaTone();
    });

    menuItemDither.addActionListener(e -> {
      resetForImageOperation();
      feature.dither();
    });

    menuItemReduce.addActionListener(e -> {
      resetForImageOperation();
      feature.reduceColor();
    });

    menuItemMosaic.addActionListener(e -> {
      resetForImageOperation();
      feature.mosaic();
    });

    menuItemPixelate.addActionListener(e -> {
      resetForImageOperation();
      feature.pixelate();
    });

    menuItemSaveImgFile.addActionListener(e -> {
      resetForImageOperation();
      this.saveImage();
      feature.saveFile(filename);
    });

    menuItemSavePattern.addActionListener(e -> {
      this.savePattern();
      feature.savePattern(filename);
    });

    menuItemPattern.addActionListener(e -> {
      feature.generatePattern();
    });

    menuItemSwapDmc.addActionListener(e -> {
      feature.swapDmcColorMenuClick();
      selectedDmcColorStr = null;
    });
    swapDmcButton.addActionListener(e -> {
      feature.swapDmcColor(getMouseClickPosition(), getDmcColorForSwap());
    });

    menuItemRemoveDmc.addActionListener(e -> {
      feature.removeDmcColorMenuClick();
    });
    removeDmcButton.addActionListener(e -> {
      feature.removeDmcColor(getMouseClickPosition());
    });

    menuItemCustomDmc.addActionListener(e -> {
      feature.customDmcPatternMenuClick();
      selectedDmcColor = new ArrayList<String>();

    });
    createCustomDmcButton.addActionListener(e -> {
      feature.customDmcColorPattern(getDmcColorForGenerateAll());
    });

    loadButton.addActionListener(e -> {
      feature.loadCommand();
    });

    blurButton.addActionListener(e -> {
      feature.blurCommand();
    });
    sharpenButton.addActionListener(e -> {
      feature.sharpenCommand();
    });
    sepiaButton.addActionListener(e -> {
      feature.sepiaCommand();
    });
    greyToneButton.addActionListener(e -> {
      feature.greyScaleCommand();
    });
    mosaicButton.addActionListener(e -> {
      feature.mosaicCommand();
    });
    ditherButton.addActionListener(e -> {
      feature.ditherCommand();
    });
    reduceButton.addActionListener(e -> {
      feature.reduceCommand();
    });
    pixelateButton.addActionListener(e -> {
      feature.pixelateCommand();
    });
    saveImageButton.addActionListener(e -> {
      feature.saveCommand();
    });
    patternButton.addActionListener(e -> {
      feature.patternCommand();
    });
    savePatternButton.addActionListener(e -> {
      feature.savePatternCommand();
    });

    executeBatch.addActionListener(e -> {
      feature.executeBatch();
    });

    clearButton.addActionListener(e -> {
      this.clearText();
    });
  }

  private void clearText() {
    scriptTextArea.setText("");
  }

  private void saveImage() {

    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Specify a file to save");
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Image", "jpg", "png", "gif",
        "bmp");
    fileChooser.setFileFilter(filter);
    int userSelection = fileChooser.showSaveDialog(this);

    if (userSelection == JFileChooser.APPROVE_OPTION) {
      File fileToSave = fileChooser.getSelectedFile();
      filename = fileToSave.toString();
    }

  }

  private void savePattern() {

    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Specify a file to save");

    int userSelection = fileChooser.showSaveDialog(this);

    if (userSelection == JFileChooser.APPROVE_OPTION) {
      File fileToSave = fileChooser.getSelectedFile();
      filename = fileToSave.toString();
    }

  }

  private void showInstructions() {

    String helpMessage = "<html><body width='%1s'><h1>Help</h1>"
        + "<h3>Please see below help for the operations that can be performed:</h3>"
        + "<p>Basic Image Operations: (Home \u2192 Image Operations)<br>"
        + "<b>Blur:</b> Blur an Image<br>" + "<b>Sharpen:</b> Sharpen an Image<br>"
        + "<b>Grey Scale:</b> Convert an Image to Grey Scale<br>"
        + "<b>Sepia Tone:</b> Convert an Image to Sepia Tone<br>"
        + "<b>Color Reduction:</b> Reduce the number of colors in an Image<br>"
        + "<b>Dither:</b> Dither an Image<br>" + "<b>Mosaic:</b> Create Mosaic Art for an Image<br>"
        + "<b>Pixelate:</b> Pixelate an Image (Requires user input for Pixelation Count)<br>"
        + "<br>" + "Cross Stitch Pattern Generation: (Home \u2192 Cross Stitch Pattern)<br>"
        + "Generates a cross-stitched pattern for an Image using provided DMC Colors and "
        + "requires Pixelation operation to be performed prior to a pattern generation" + "<br>"
        + "Swap DMC Color: Swap a DMC Color for a SuperPixel with another<br>"
        + "Remove DMC Color: Remove a DMC Color for a selected SuperPixel to blank<br>"
        + "Custom DMC Color: Create a custom DMC Pattern with selective DMC colors";
    int dialogBoxWidth = 500;

    JOptionPane.showMessageDialog(null, String.format(helpMessage, dialogBoxWidth, dialogBoxWidth));
  }

  private void showAbout() {
    String aboutMessage = "<html><body width='%1s'><h1>Image Processing Application</h1>"
        + "<h1>Shireen Firdoz</h1>" + "<h2>Prof. Maria Jump</h2>"
        + "<p>Developed for CS5010: Programming Design Paradigm Course<br>"
        + "Khoury College of Computer Sciences - Northeastern University";
    int dialogBoxWidth = 400;

    JOptionPane.showMessageDialog(null,
        String.format(aboutMessage, dialogBoxWidth, dialogBoxWidth));

  }

  @Override
  public void errorMessage(String message) {
    HelperView.isObjectNull(message);
    HelperView.genericMessageDialogError(this, message, "Image Operation");

  }

  @Override
  public void infoMessage(String message) {
    HelperView.isObjectNull(message);
    HelperView.genericMessageDialogInfo(message, "Image Operation");
  }

  @Override
  public void successMessage(String message) {
    HelperView.isObjectNull(message);
    HelperView.genericMessageDialogInfo(message, "Image Operation");

  }

  @Override
  public void setVisible() {
    this.setVisible(true);
  }

  private void createDmcColorPallete(boolean multiselect) {
    dmcPanel.removeAll();
    revalidate();
    repaint();
    CustomListItem[] itemArray = getArray();

    JList<CustomListItem> list = new JList<CustomListItem>(itemArray);
    list.setCellRenderer(new CustomCheckboxListRenderer());
    if (multiselect) {
      list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    } else {
      list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    list.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent event) {
        JList<CustomListItem> list = (JList<CustomListItem>) event.getSource();

        int index = list.locationToIndex(event.getPoint());
        CustomListItem item = (CustomListItem) list.getModel().getElementAt(index);

        item.setSelected(!item.isSelected());

        if (multiselect) {
          if (item.isSelected()) {
            selectedDmcColor.add(item.getLabel());
          } else {
            if (selectedDmcColor.contains(item.getLabel())) {
              selectedDmcColor.remove(item.getLabel());
            }
          }
        } else {
          selectedDmcColorStr = item.getLabel();
        }

        list.repaint(list.getCellBounds(index, index));
        list.repaint();
      }
    });
    JScrollPane scrollpane = new JScrollPane(list);
    dmcPanel.add(scrollpane);
    scrollpane.getViewport().revalidate();
    revalidate();
    repaint();

  }

  private CustomListItem[] getArray() {

    CustomListItem[] itemArray = new CustomListItem[dmcColorMap.size()];
    int red = 0;
    int green = 0;
    int blue = 0;

    Set<Entry<String, Colour>> entries = dmcColorMap.entrySet();
    int counter = 0;
    for (Map.Entry<String, Colour> entry : entries) {
      red = entry.getValue().getRedColor();
      green = entry.getValue().getGreenColor();
      blue = entry.getValue().getBlueColor();
      itemArray[counter] = new CustomListItem(new Color(red, green, blue), entry.getKey());
      counter++;
    }
    return itemArray;
  }

  private void createDmcColorPalleteSwap() {
    dmcRadioPanel.removeAll();
    revalidate();
    repaint();

    CustomListItem[] itemArray = getArray();

    JList<CustomListItem> list = new JList<CustomListItem>(itemArray);
    list.setCellRenderer(new CustomListRenderer());

    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    list.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent event) {
        JList<CustomListItem> list = (JList<CustomListItem>) event.getSource();

        int index = list.locationToIndex(event.getPoint());
        CustomListItem item = (CustomListItem) list.getModel().getElementAt(index);

        item.setSelected(!item.isSelected());

        if (item.isSelected()) {
          selectedDmcColorStr = item.getLabel();
        }

        list.repaint(list.getCellBounds(index, index));
        list.repaint();
      }
    });
    JScrollPane scrollpane = new JScrollPane(list);
    dmcRadioPanel.add(scrollpane);
    scrollpane.getViewport().revalidate();
    revalidate();
    repaint();

  }

  private void createLegendPallete() {
    legendPanel.removeAll();
    revalidate();
    repaint();
    CustomListItem[] itemArray = new CustomListItem[legend.size()];
    int red = 0;
    int green = 0;
    int blue = 0;

    Set<Entry<String, String>> entries = legend.entrySet();
    int counter = 0;
    Colour newColour;
    StringBuilder stringBuilder;
    for (Map.Entry<String, String> entry : entries) {
      if ("blank".equalsIgnoreCase(entry.getKey())) {

        itemArray[counter] = new CustomListItem(new Color(255, 255, 255), ". blank");
      } else {
        newColour = dmcColorMap.get(entry.getKey());
        red = newColour.getRedColor();
        green = newColour.getGreenColor();
        blue = newColour.getBlueColor();
        stringBuilder = new StringBuilder();
        stringBuilder.append(entry.getValue());
        stringBuilder.append(" ");
        stringBuilder.append(entry.getKey());
        itemArray[counter] = new CustomListItem(new Color(red, green, blue),
            stringBuilder.toString());
      }
      counter++;
    }

    JList<CustomListItem> list = new JList<CustomListItem>(itemArray);
    list.setCellRenderer(new CustomListRenderer());
    list.repaint();
    JScrollPane scrollpane = new JScrollPane(list);
    legendPanel.add(scrollpane);
    scrollpane.getViewport().revalidate();

  }

  @Override
  public void showLegend(Map<String, String> legend) {
    HelperView.isObjectNull(legend);
    this.legend = new TreeMap<String, String>();
    Set<Entry<String, String>> entries = legend.entrySet();
    for (Map.Entry<String, String> entry : entries) {
      this.legend.put(entry.getKey(), entry.getValue());
    }
    createLegendPallete();
    revalidate();
    repaint();
  }

  @Override
  public void showPatternDimension(int patternHeight, int patternWidth) {
    patternDimension.setText(patternWidth + " X " + patternHeight);

  }

  @Override
  public void showImagePattern(BufferedImage image, SuperPixel[][] imageMap) {
    HelperView.isObjectNull(image);
    HelperView.isObjectNull(imageMap);
    scrollPanel.overlaySymbol(imageMap, image);
    revalidate();
    repaint();
  }

  @Override
  public void getDmcPalleteMap(Map<String, Colour> dmcPallete) {
    HelperView.isObjectNull(dmcPallete);
    this.dmcColorMap = new TreeMap<String, Colour>();
    Set<Entry<String, Colour>> entries = dmcPallete.entrySet();
    int red;
    int green;
    int blue;
    Colour newColor;
    for (Map.Entry<String, Colour> entry : entries) {
      red = entry.getValue().getRedColor();
      green = entry.getValue().getGreenColor();
      blue = entry.getValue().getBlueColor();
      newColor = new RgbColor(red, green, blue);
      this.dmcColorMap.put(entry.getKey(), newColor);
    }

  }

  @Override
  public void showDmcPallete(boolean multiSelect) {
    if (multiSelect) {
      dmcRadioPanel.setVisible(false);
      dmcPanel.setVisible(true);
      createDmcColorPallete(multiSelect);

    } else {
      dmcRadioPanel.setVisible(true);
      dmcPanel.setVisible(false);
      createDmcColorPalleteSwap();

    }
    if (multiSelect) {
      createCustomDmcButton.setText("Generate pattern");
      southPanel.add(createCustomDmcButton);
      swapDmcButton.setVisible(false);
      createCustomDmcButton.setVisible(true);
    } else {
      swapDmcButton.setText("Swap Color");
      createCustomDmcButton.setVisible(false);
      swapDmcButton.setVisible(true);
      southPanel.add(swapDmcButton);
    }

    legendPanel.setVisible(true);
    patternDimension.setVisible(true);
    removeDmcButton.setVisible(false);
    rightPanel.setVisible(true);
    hideBatchComponents();
    revalidate();
    repaint();
  }

  private Position getMouseClickPosition() {
    Position position = new PositionImpl(scrollPanel.getMouseClickedPosition().getPositionHeight(),
        scrollPanel.getMouseClickedPosition().getPositionWidth());
    return position;
  }

  private String getDmcColorForSwap() {
    String string = selectedDmcColorStr;
    return string;
  }

  private List<String> getDmcColorForGenerateAll() {
    List<String> strList = new ArrayList<String>();
    for (String str : selectedDmcColor) {
      strList.add(str);
    }

    return strList;
  }

  @Override
  public void showRemoveDmcColorView() {
    dmcPanel.setVisible(false);
    swapDmcButton.setVisible(false);
    createCustomDmcButton.setVisible(false);
    removeDmcButton.setText("remove dmc");
    removeDmcButton.setVisible(true);
    legendPanel.setVisible(true);
    patternDimension.setVisible(true);
    dmcRadioPanel.setVisible(false);
    southPanel.add(removeDmcButton);
    rightPanel.setVisible(true);
    hideBatchComponents();
    repaint();
    revalidate();

  }

  private void resetForImageOperation() {
    scrollPanel.setVisible(true);
    dmcPanel.setVisible(false);
    legendPanel.setVisible(false);
    swapDmcButton.setVisible(false);
    createCustomDmcButton.setVisible(false);
    dmcRadioPanel.setVisible(false);
    removeDmcButton.setVisible(false);
    patternDimension.setVisible(false);
    rightPanel.setVisible(true);
    hideBatchComponents();
    repaint();
    revalidate();
  }

  private void resetForBatchExecution() {
    scrollPanel.setVisible(false);
    southPanel.setVisible(false);
    rightPanel.setVisible(false);
    repaint();
    revalidate();
  }

  private void hideBatchComponents() {
    rightPanelBatch.setVisible(false);
    southPanelBatch.setVisible(false);
    batchFile.setVisible(false);
    repaint();
    revalidate();
  }

  @Override
  public void showPatternView() {
    scrollPanel.setVisible(true);
    dmcPanel.setVisible(false);
    dmcRadioPanel.setVisible(false);
    legendPanel.setVisible(true);
    swapDmcButton.setVisible(false);
    createCustomDmcButton.setVisible(false);
    removeDmcButton.setVisible(false);
    patternDimension.setVisible(true);
    rightPanel.setVisible(true);
    hideBatchComponents();
    repaint();
    revalidate();

  }

  @Override
  public String getFileName() {
    String fileName = this.filename;
    return fileName;
  }

  @Override
  public String getTextArea() {
    String input = scriptTextArea.getText();
    return input;
  }

}
