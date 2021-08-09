package model.utilities;

import model.pattern.ImagePattern;
import model.pixel.SuperPixel;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * This WriteToFile class represents a static method to write a generated
 * cross-stitched pattern to a file.
 */
public class WriteToFile {

  /**
   * This static method writes a generated cross-stitched pattern to a file.
   *
   * @param imagePattern the generated imagePattern
   * @param filename     the string type filename for the pattern
   * @throws IOException I/O Exception thrown for IO.
   */
  public static void writeFile(ImagePattern imagePattern, String filename) throws IOException {
    Helper.isObjectNull(filename);
    Helper.isObjectNull(imagePattern);
    try {
      StringBuilder patternSpec = new StringBuilder();
      patternSpec.append(imagePattern.getHeight());
      patternSpec.append(Constant.CROSS);
      patternSpec.append(imagePattern.getWidth());
      FileWriter imgWriter = new FileWriter(filename);
      imgWriter.write(patternSpec.toString());
      imgWriter.write(String.format("%n"));
      Helper.isObjectNull(imagePattern.getImageMap());
      SuperPixel[][] imageMap = imagePattern.getImageMap();
      StringBuilder patternImgRow = null;
      for (int i = 0; i < imageMap.length; i++) {
        patternImgRow = new StringBuilder();
        for (int j = 0; j < imageMap[0].length; j++) {
          patternImgRow.append(imageMap[i][j].getSymbol());
        }
        imgWriter.write(patternImgRow.toString());
        imgWriter.write(String.format("%n"));
      }
      imgWriter.write(String.format("%n"));
      imgWriter.write(Constant.LEGEND);
      imgWriter.write(String.format("%n"));
      Helper.isObjectNull(imagePattern.getLegend());
      Set<Entry<String, String>> entries = imagePattern.getLegend().entrySet();
      StringBuilder patternLegendSb = null;
      for (Map.Entry<String, String> entry : entries) {
        patternLegendSb = new StringBuilder();
        patternLegendSb.append(entry.getValue());
        patternLegendSb.append(" ");
        patternLegendSb.append(entry.getKey());
        imgWriter.write(patternLegendSb.toString());
        imgWriter.write(String.format("%n"));
      }
      imgWriter.close();
    } catch (IOException e) {
      throw e;
    }
  }
}
