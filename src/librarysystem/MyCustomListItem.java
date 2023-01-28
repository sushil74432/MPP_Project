package librarysystem;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

public class MyCustomListItem extends JLabel implements ListCellRenderer<MenuItem>{

	@Override
	public Component getListCellRendererComponent(JList<? extends MenuItem> list, MenuItem value, int index,
			boolean isSelected, boolean cellHasFocus) {
		
		
		String image = value.getIcon();
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("images/" + image));

        
        setIcon(imageIcon);
        setText(value.getMenuLabel());
        
        setEnabled(value.isEnabled());
        
        return this;
	}

}
