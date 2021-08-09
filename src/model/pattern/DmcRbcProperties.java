package model.pattern;

import model.pixel.Colour;
import model.utilities.Helper;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

/**
 * This class represents the DMC colors that are available for use for the
 * cross-stitched pattern generation. This is a singleton class that can only be
 * instantiated once to save all the DMC floss colors as a Map for comparison.
 * The file is read as a properties file with key value pairs.
 */
public class DmcRbcProperties {

  private static DmcRbcProperties instance = null;
  private final Properties dmcProperties;

  /**
   * Private constructor for the Singleton class.
   *
   * @throws IOException for file read issues
   */
  private DmcRbcProperties() throws IOException {

    dmcProperties = new Properties();
    dmcProperties.load(getClass().getResourceAsStream("/dmcProp.properties"));

  }

  /**
   * This method instantiates the singleton class.
   *
   * @return an instance for the DmcRbcProperties class
   * @throws IOException for file read issues
   */
  public static DmcRbcProperties getInstance() throws IOException {
    if (instance == null) {
      synchronized (DmcRbcProperties.class) {
        instance = new DmcRbcProperties();
      }

    }
    return instance;
  }

  /**
   * This is a getter method to generate a map for the properties file for the DMC
   * floss colors that are available with  with key as the dmc color name.
   *
   * @return a map for the DMC floss colors with key.
   */
  public Map<String, Colour> getDmcProperties() {
    Map<String, Colour> dmcPropertyMap = new TreeMap<String, Colour>();
    Colour dmcColor = null;
    Enumeration dmcColorkey = dmcProperties.propertyNames();
    while (dmcColorkey.hasMoreElements()) {
      String dmckey = (String) dmcColorkey.nextElement();
      dmcColor = Helper.decodeDmcColor(dmcProperties.getProperty(dmckey));
      dmcPropertyMap.put(dmckey, dmcColor );
    }

    return dmcPropertyMap;

  }
  
  /**
   * This is a getter method to generate a map for the properties file for the DMC
   * floss colors that are available  with key as the color.
   *
   * @return a map for the DMC floss colors with key as the color.
   */
  public Map<String, String> getDmcPropertiesColorKey() {
    Map<String, String> dmcPropertyMap = new TreeMap<String, String>();
    Enumeration dmcColorkey = dmcProperties.propertyNames();
    while (dmcColorkey.hasMoreElements()) {
      String dmckey = (String) dmcColorkey.nextElement();
      dmcPropertyMap.put(dmcProperties.getProperty(dmckey), dmckey );
    }

    return dmcPropertyMap;

  }

}
