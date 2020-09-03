package promptgenerator.gui.actionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import promptgenerator.logic.Template;

public class TemplateButtonListener implements ActionListener {
    private Template temp;
    private int index = 0;
    JButton button;
    JLabel prompt;

    public TemplateButtonListener(JLabel jl, Template temp, JButton button) {
        this.temp = temp;
        this.button = button;
        this.prompt = jl;
    }
    
    public void setIndex(int i) {
        this.index = i;
        if (index < 0) button.setEnabled(false);
        else button.setEnabled(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        this.prompt.setText(temp.generate(index));
    }
    
}
