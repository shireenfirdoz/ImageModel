package imageview.colorpallete;

import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import view.utilities.HelperView;

/**
 * This class represents a custom check box list that is used to render multiple
 * items with check boxes to be selected.
 */
public class CustomCheckboxListRenderer extends JCheckBox
    implements ListCellRenderer<CustomListItem> {

  /**
   * generated serial id.
   */
  private static final long serialVersionUID = 201685098883098094L;

  @Override
  public Component getListCellRendererComponent(JList<? extends CustomListItem> list,
      CustomListItem item, int index, boolean isSelected, boolean cellHasFocus) {
    HelperView.isObjectNull(item);
    HelperView.isObjectNull(list);
    setEnabled(list.isEnabled());
    setSelected(item.isSelected());
    setFont(list.getFont());
    setForeground(list.getForeground());
    setText(item.getLabel());
    setBackground(item.getBackGround());
    return this;
  }

}
