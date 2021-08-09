package imageview.colorpallete;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import view.utilities.HelperView;

/**
 * This lass represents the rendering for the custom list that is used to show
 * the different DMC color in the Image Processing Application.
 */
public class CustomListRenderer extends JLabel implements ListCellRenderer<CustomListItem> {

  /**
   * generated version id.
   */
  private static final long serialVersionUID = 6823070571405755478L;

  /**
   * Constructor for the custom list renderer to initialize rendering.
   */
  public CustomListRenderer() {
    super.setOpaque(true);
  }

  @Override
  public Component getListCellRendererComponent(JList<? extends CustomListItem> list,
      CustomListItem item, int index, boolean isSelected, boolean cellHasFocus) {
    HelperView.isObjectNull(item);
    HelperView.isObjectNull(list);
    setEnabled(list.isEnabled());
    setFont(list.getFont());
    setForeground(list.getForeground());
    setText(item.getLabel());
    setBackground(item.getBackGround());
    return this;
  }

}
