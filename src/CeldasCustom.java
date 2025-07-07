
import java.awt.Color;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 * Custom renderer to display a country's flag alongside its name
 *
 * @author wwww.codejava.net
 */
public class CeldasCustom extends JLabel implements ListCellRenderer<String> {
 
    public CeldasCustom() {
        setOpaque(true);
    }
 
    @Override
    public Component getListCellRendererComponent(JList<? extends String> list, String elemento, int index,
            boolean isSelected, boolean cellHasFocus) {
 
 
    	setText(elemento);
    	
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
 
        return this;
    }
} 