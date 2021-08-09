package model.imageprocessor;

/**
 * Clamping operation on the images represent the application of clamping to the
 * pixel values in whenever an operation is performed on an image. This makes
 * sure that the value of the respective color in the pixel stay in the range
 * 0-255. All the value below 0 are changed to 0 and all the values above 155
 * are changed to 255.
 *
 * @param <T> the object of type T to apply clamping to
 */
public interface Clamping<T> {
  /**
   * This method performs the clamp operation on the object of type t.
   *
   * @return Gives object of type after performing clamping.
   */
  T toClamp();
}
