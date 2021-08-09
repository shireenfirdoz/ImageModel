package model.pattern;

import model.images.Image;
import model.pixel.SuperPixel;

import java.util.Map;

/**
 * This interface represents the ImagePattern that is generated for the
 * cross-stitched pattern for a given input image. The pattern is generated
 * after performing image chunkig operation using the pixelation operation.
 */
public interface ImagePattern extends Image {

  /**
   * Getter method to get the width dimension for the image that we generated a
   * cross-stitched pattern for.
   *
   * @return int value for image width
   */
  int getWidth();

  /**
   * Getter method to get the height dimension for the image that we generated a
   * cross-stitched pattern for.
   *
   * @return int value for image height
   */
  int getHeight();

  /**
   * Getter method to get the generated cross-stitched image map for the image.
   *
   * @return a 2D array for cross-stitched patterns
   */
  SuperPixel[][] getImageMap();

  /**
   * Getter method to get the legend map for all the DMC colors available for use
   * as generated from the DMC Properties file.
   *
   * @return a map with key as DMC color.
   */
  Map<String, String> getLegend();

}
