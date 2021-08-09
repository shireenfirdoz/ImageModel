package imageview;

import model.pixel.Position;
import model.pixel.PositionImpl;
import view.utilities.HelperView;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 * This class represents the image panel for the GUI for the Image Processing
 * Application. It extends from JPanel and loads the image using the load
 * command.
 */
public class ImagePanel extends JPanel {
  /**
   * Generated serial version id.
   */
  private static final long serialVersionUID = 2629289981291590449L;
  private BufferedImage image;
  private Position mouseClickedPos;
  private boolean mouseClicked;

  /**
   * Constructor for the ImagePanel panel view to show the image on the GUI in the
   * view for the Image Processing Application.
   * 
   * @param image an image of type BufferedImage
   */
  public ImagePanel(BufferedImage image) {
    super();
    HelperView.isObjectNull(image);
    setImage(image);
    mouseClicked = false;
    mouseClickedPos = null;

    this.addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent me) {
        mouseClicked = true;
        mouseClickedPos = new PositionImpl(me.getY(), me.getX());
      }
    });
  }

  /**
   * This method sets the loaded image to be shown in the panel.
   * 
   * @param image an image of type BufferedImage
   */
  public void setImage(BufferedImage image) {
    HelperView.isObjectNull(image);
    this.image = image;
    setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
    repaint();
    revalidate();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (image != null) {
      g.drawImage(image, 0, 0, null);
    }
  }

  private int getNumber(int yPos) {
    if (yPos % 2 != 0) {
      yPos = (yPos + 1) / 2;
    } else {
      yPos = yPos / 2;
    }
    return yPos;
  }

  /**
   * This method is used by the various different functionalities to get the mouse
   * click location which given the location of the pixel to be worked upon in the
   * image for the different operations.
   * 
   * @return
   */
  public Position getMouseClickedPosition() {
    int yPos = -1;
    int xPos = -1;
    if (mouseClicked) {
      yPos = getNumber(mouseClickedPos.getPositionHeight());
      xPos = getNumber(mouseClickedPos.getPositionWidth());

    }
    return new PositionImpl(yPos, xPos);

  }
}
