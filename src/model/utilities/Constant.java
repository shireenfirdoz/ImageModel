package model.utilities;

/**
 * This class represents constants for constant files for the Image Model
 * project where all the constants can be declared and reuse across the project.
 */
public class Constant {

  public static final int COLOR_MIN = 0;

  public static final int COLOR_MAX = 255;

  public static final int DEPTH_MIN = 0;

  public static final int DEPTH_MAX = 2;

  public static final int TRANSFORMATION_MATRIX_SIZE = 3;

  public static final float[] BLUR_KERNEL_MATRIX = new float[]{

      1f / 16f, 1f / 8f, 1f / 16f,

      1f / 8f, 1f / 4f, 1f / 8f,

      1f / 16f, 1f / 8f, 1f / 16f};

  public static final float[] SHARPEN_KERNEL_MATRIX = new float[]{

      -1f / 8f, -1f / 8f, -1f / 8f, -1f / 8f, -1f / 8f,

      -1f / 8f, 1f / 4f, 1f / 4f, 1f / 4f, -1f / 8f,

      -1f / 8f, 1f / 4f, 1f, 1f / 4f, -1f / 8f,

      -1f / 8f, 1f / 4f, 1f / 4f, 1f / 4f, -1f / 8f,

      -1f / 8f, -1f / 8f, -1f / 8f, -1f / 8f, -1f / 8f,

  };

  public static final float[] SEPIA_TRANSFORMATION_MATRIX = new float[]{

      0.393f, 0.769f, 0.189f,

      0.349f, 0.686f, 0.168f,

      0.272f, 0.534f, 0.131f};

  public static final float[] GREYSCALE_TRANSFORMATION_MATRIX = new float[]{

      0.2126f, 0.7152f, 0.0722f,

      0.2126f, 0.7152f, 0.0722f,

      0.2126f, 0.7152f, 0.0722f};

  public static final String CROSS = " X ";
  public static final String LEGEND = " LEGEND ";
  

  public static final String DMC_INFO = "Please select a dmc color from"
      + " the pallete by checking the checkbox and then perform the operation";
  

  public static final String DMC_INFO_LIST = "Please select a list of dmc color from"
      + " the pallete by checking the checkbox and then perform the operation";
  
}
