package imageview.colorpallete;

import view.utilities.HelperView;

import java.awt.Color;

/**
 * This class represents a custom list item that can be added to the CustomCheckBoxList.
 * This is being used to represent various DMC colors for the different operations like
 * DMC swap etc.
 */
public class CustomListItem {

  private boolean isSelected;
  private final Color backGroundColor;
  private final String label;

  /**
   * Constructor for a custom list item to initialize a new custom DMC color item.
   * @param backGround the background for the DMC color
   * @param label the DMC color name
   */
  public CustomListItem(Color backGround, String label) {
    HelperView.isObjectNull(backGround);
    HelperView.isObjectNull(label);
    this.backGroundColor = backGround;
    this.label = label;
    isSelected = false;
  }

  /**
   * Method to check whether a check for a DMC item is selected.
   * @return
   */
  public boolean isSelected() {
    boolean isSelected = this.isSelected;
    return isSelected;
  }

  /**
   * Method to toggle the selection check box for an item.
   * @param isSelected boolean value for the current status for the checkbox
   */
  public void setSelected(boolean isSelected) {
    this.isSelected = isSelected;
  }

  /**
   * Method to get the background color for the custom item in the list. This is shows from the
   * DMC list of colors.
   * @return a color to be show in background
   */
  public Color getBackGround() {
    Color newBackGroundColor = new Color(backGroundColor.getRed(), backGroundColor.getGreen(),
        backGroundColor.getBlue());
    return newBackGroundColor;
  }

  /**
   * Method to get the name for the DMC color to be shown in the DMC custom list.
   * @return a string of DMC color name
   */
  public String getLabel() {
    String label = this.label;
    return label;
  }
}