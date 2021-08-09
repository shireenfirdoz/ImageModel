package model.imageprocessor;

import model.exception.ModelValidationException;
import model.images.Image;
import model.images.RgbImage;
import model.pixel.Pixel;
import model.utilities.Helper;

import java.io.IOException;
import java.util.Map;

/**
 * This class represents an operator for the image processing application to
 * perform pixelation for an image given a pixelation parameter. A pixelation
 * paramter determines the number of super pixels to be taken in the given image
 * to perform image chunking.
 */
public class PixelateOperator implements ImagePixelateOperator {

  private final String pixelationStr;

  /**
   * This is the constructor of PixelateOperator class which takes input
   * pixelation parameter to be used for pixelate operation.
   * 
   * @param pixelationStr the input parameter
   */
  public PixelateOperator(String pixelationStr) {
    super();
    // pixelationStr will be validated in apply method .
    this.pixelationStr = pixelationStr;
  }

  @Override
  public Image apply(Image image) throws ModelValidationException, IOException {
    Helper.imageChecks(image);
    Helper.inValidSingleNumber(image.getImageHeight());
    Helper.inValidSingleNumber(image.getImageWidth());

    int pixelation = Helper.inputParameterDecode(this.pixelationStr, "Pixelate ");

    if ((pixelation <= 0) || (image.getImageHeight() < pixelation)
        || (image.getImageWidth() < pixelation)) {
      throw new ModelValidationException("pixelation parameter is invalid");
    }

    Map<String, Object> resultMap = Helper.imageChunkingOnSuperPixel(image.getPixelArray(), 
        pixelation, MatrixType.TRANSFORMATION_PIXELATE.toString(), null);

    Pixel[][] newImagePixelMatrix = (Pixel[][]) resultMap
        .get(MatrixType.TRANSFORMATION_PIXELATE.toString());

    return new RgbImage(newImagePixelMatrix, newImagePixelMatrix.length,
        newImagePixelMatrix[0].length);
  }

  @Override
  public int getPixelation() throws ModelValidationException {
    return Helper.inputParameterDecode(this.pixelationStr, "Pixelate ");
  }

}
