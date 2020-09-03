package promptgenerator.gui.actionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import promptgenerator.logic.Template;

public class TemplateComboBoxListener implements ActionListener{
    public int index = 0;
    private JLabel jl;
    private Template template;
    private TemplateButtonListener tbl;

    public TemplateComboBoxListener(JLabel jl, Template template, TemplateButtonListener tbl) {
        this.jl = jl;
        this.template = template;
        this.tbl = tbl;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox) e.getSource();
        index = cb.getSelectedIndex()-1;
        tbl.setIndex(index);
        jl.setText(template.returnTemplate(index));
    }
    
}
