package model.imageprocessor;

/**
 * An enumeration of the different matrix scenarios during pixelation having
 * different superpixel dimension.scenario as SUPERPIXEL_ONE_WIDTH_ONE_HEIGHT:
 * image width is divisible by pixelation parameter and height completely
 * divisible by superpixel width, scenario as SUPERPIXEL_TWO_WIDTH_ONE_HEIGHT:
 * image width is not divisible by pixelation parameter and height completely
 * divisible by superpixel width, 2 types of super pixel with different width,
 * scenario as SUPERPIXEL_ONE_WIDTH_TWO_HEIGHT: image width is divisible but not
 * the height, 2 types of super pixel with different height, scenario as
 * SUPERPIXEL_TWO_WIDTH_TWO_HEIGHT: image width is not divisible and height also
 * not divisble by superpixel width.
 */
public enum PixelateImageMatrix {

  SUPERPIXEL_ONE_WIDTH_ONE_HEIGHT, SUPERPIXEL_ONE_WIDTH_TWO_HEIGHT, SUPERPIXEL_TWO_WIDTH_ONE_HEIGHT,
  SUPERPIXEL_TWO_WIDTH_TWO_HEIGHT

}
