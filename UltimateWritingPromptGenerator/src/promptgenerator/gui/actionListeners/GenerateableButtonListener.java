package promptgenerator.gui.actionListeners;

import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import promptgenerator.logic.*;

public class GenerateableButtonListener implements ActionListener {
    Generator generator;
    ArrayList<JLabel> prompt;
    JComboBox amount;
    HashMap<String, CheckBoxListener> toggles = new HashMap<>();
    ComboBoxListener CBL = new ComboBoxListener();
    
    public GenerateableButtonListener(Generator g, ArrayList<JLabel> jl, JComboBox cb) {
        generator = g;
        this.prompt = jl;
        this.amount = cb;
        this.amount.addActionListener(CBL);
    }
    
    public void addToggle(JCheckBox cb) {
        CheckBoxListener cbl = new CheckBoxListener();
        cb.addChangeListener(cbl);
        this.toggles.put(cb.getName() ,cbl);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.toggles.keySet().forEach((s) -> {
            this.generator.toggle(s, this.toggles.get(s).checked);
        });
        String s = CBL.chosen;
        int a = 1;
        if (!s.equals("")) a = Integer.parseInt(s);
        List<String> p = generator.generate(a);
        String t = "<html><ul>";
        for (int i = 0; i < p.size(); i++) {
            t += "<li>" + p.get(i);
            if (i != p.size()-1) t += "<br>";
            else t += "</ul></html>";
        }
        for (int i = this.prompt.size()-1; i > 0; i--) {
            JLabel prev = this.prompt.get(i);
            JLabel next = this.prompt.get(i-1);
            prev.setText(next.getText());
        }
        this.prompt.get(0).setText(t);
    }
    
}

