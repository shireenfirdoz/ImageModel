package imageview;

import model.pixel.Position;
import model.pixel.SuperPixel;
import view.utilities.HelperView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * This class represents the scroll bar panel for the image. The image can be scrolled through
 * if the dimensions go out of the current set dimensions for the image that has been loaded.
 */
public class ScrollPanel extends JPanel {

  /**
   * Generated serial version id.
   */
  private static final long serialVersionUID = 5005390647972544253L;
  private JScrollPane scrollpane;
  private ImagePanel imagePanel;

  /**
   * Constructor to initialize the scroll panel for the image.
   */
  public ScrollPanel() {
    super();
    scrollpane = null;
    imagePanel = null;

  }

  /**
   * Method to set and initialize the scroll panel when an image is loaded for the view.
   * @param img an image of type BufferedImage
   */
  public void setImageOnLoad(BufferedImage img) {
    HelperView.isObjectNull(img);
    imagePanel = new ImagePanel(img);
    scrollpane = new JScrollPane(imagePanel);

    setLayout(new BorderLayout());
    add(scrollpane, BorderLayout.CENTER);
    scrollpane.getViewport().revalidate();
    revalidate();
    repaint();
  }

  /**
   * Method to generate overlay symbols for the stitch pattern generation to create a legend of
   * colors that are used.
   * @param imageMap a 2D array of SuperPixel representing an image map
   * @param image an image of type BufferedImage
   * @return a new BufferedImage with overlayed symbols
   */
  public BufferedImage overlaySymbol(SuperPixel[][] imageMap, BufferedImage image) {
    HelperView.isObjectNull(imageMap);
    HelperView.isObjectNull(image);
    BufferedImage ovarlayImage = overlay(imageMap, image);
    setImage(ovarlayImage);
    return ovarlayImage;
  }


  private BufferedImage overlay(SuperPixel[][] imageMap, BufferedImage image) {
    HelperView.isObjectNull(imageMap);
    HelperView.isObjectNull(image);
    int w = image.getWidth() * 2;
    int h = image.getHeight() * 2;
    BufferedImage scaledImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2d = scaledImage.createGraphics();
    g2d.drawImage(image, 0, 0, w, h, this);
    g2d.setPaint(Color.black);
    g2d.setFont(new Font("Serif", Font.PLAIN, 10));

    SuperPixel superPixel = null;
    for (int i = 0; i < imageMap.length; i++) {
      for (int j = 0; j < imageMap[0].length; j++) {
        superPixel = imageMap[i][j];
        g2d.drawString(superPixel.getSymbol(), superPixel.getSuperPixelPosX() * 2,
            superPixel.getSuperPixelPosY() * 2);
      }

    }

    g2d.dispose();
    return scaledImage;
  }

  /**
   * Method to set the loaded image to be show on the image panel.
   * @param img an image of type BufferedImage
   */
  public void setImage(BufferedImage img) {
    HelperView.isObjectNull(img);
    imagePanel.setImage(img);
    imagePanel.repaint();
    scrollpane.getViewport().revalidate();
    repaint();
  }

  /**
   * Method to get the position of the mouse click on the image panel.
   * @return a mouse click position on the image panel
   */
  public Position getMouseClickedPosition() {
    return imagePanel.getMouseClickedPosition();
  }
}