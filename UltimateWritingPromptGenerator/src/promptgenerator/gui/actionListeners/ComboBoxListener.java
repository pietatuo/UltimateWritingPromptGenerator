package promptgenerator.gui.actionListeners;

import java.awt.event.*;
import javax.swing.JComboBox;

public class ComboBoxListener implements ActionListener {
    public String chosen = "";

    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox)e.getSource();
        chosen = (String)cb.getSelectedItem();
    }
    
}
