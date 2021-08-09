package view.utilities;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * This Helper class represents the basic utility methods that are used by the
 * view to perform the operations that are common.
 */
public class HelperView {

  /**
   * This method validate whether argument is null or not.
   *
   * @param object Object with datatype as object.
   */
  public static void isObjectNull(Object object) {

    if (null == object) {
      throw new IllegalArgumentException(ViewConstant.OBJECT_NULL);
    }
  }

  /**
   * Method to generate a dialog for showing an error message in the view for the
   * GUI.
   * 
   * @param jframe  the jFrame to return to
   * @param message the message to be shown
   * @param type    type of mesage to be shown
   */
  public static void genericMessageDialogError(JFrame jframe, String message, String type) {
    isObjectNull(jframe);
    isObjectNull(message);
    isObjectNull(type);
    JOptionPane.showMessageDialog(jframe, message, type, JOptionPane.ERROR_MESSAGE);
  }

  /**
   * Method to generate a dialog for showing an information message in the view
   * for the GUI.
   * 
   * @param message the message to be shown
   * @param type    type of mesage to be shown
   */
  public static void genericMessageDialogInfo(String message, String type) {
    isObjectNull(message);
    isObjectNull(type);
    JOptionPane.showMessageDialog(null, message, type, JOptionPane.INFORMATION_MESSAGE);

  }

  /**
   * Method to generate a dialog for user input to perform the different
   * operations.
   * 
   * @param message the message to be shown
   * @return
   */
  public static String genericMessageDialogInput(String message) {
    isObjectNull(message);
    return JOptionPane.showInputDialog(message);

  }

}
