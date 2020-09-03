package promptgenerator.gui.actionListeners;

import javax.swing.JCheckBox;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class CheckBoxListener implements ChangeListener{
    public boolean checked = true;

    @Override
    public void stateChanged(ChangeEvent e) {
        JCheckBox cb = (JCheckBox) e.getSource();
        checked = cb.isSelected();
    }

}
