package promptgenerator.gui;
import java.awt.*;
import static java.awt.Component.*;
import java.util.ArrayList;
import javax.swing.*;
import promptgenerator.gui.actionListeners.*;
import promptgenerator.logic.*;

public class Interface implements Runnable {

    private JFrame frame;

    public Interface() {
    }

    @Override
    public void run() {
        frame = new JFrame("Just keep writing");
        frame.setPreferredSize(new Dimension(550,450));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        JMenuBar greenMenuBar = new JMenuBar();
        greenMenuBar.setOpaque(true);
        greenMenuBar.setBackground(new Color(154, 165, 127));
        greenMenuBar.setPreferredSize(new Dimension(750, 20));

        frame.setJMenuBar(greenMenuBar);
        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents(Container container) {        
        JTabbedPane tabPane = new JTabbedPane();
        container.add(tabPane);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setAlignmentX(LEFT_ALIGNMENT);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel intro = new JLabel("<html><font size=+1>Welcome to the Ultimate Writing Prompt Generator!</font><br>"
                + " <br>"
                + "Are you feeling stuck? No words coming out? Got a writer's block you just <i>can't</i> get rid of?<br>"
                + "<b>Don't worry!</b><br>"
                + "Now you too can generate a bunch of genres, settings and plot<br>"
                + "points that are sure to get your creative juices flowing once more!<br>"
                + " <br>"
                + "Here's how it works."
                + "<ol>"
                + "<li>Pick what you want generated and switch to that tab"
                + "<li>Select how many prompts you want and press the button"
                + "<li>???"
                + "<li>PROFIT! I mean prompts!</ol>"
                + "More features coming soon, maybe who knows!<br>"
                + "Bye and happy writing!"
                + "</html>");
        intro.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(intro);
        
        tabPane.addTab("Main", panel);
        generateTab(tabPane, "Plot");
        generateTab(tabPane, "Setting");
        generateTab(tabPane, "Genre");
        generateTab(tabPane, "Character");
        templateTab(tabPane);
    }

    private void generateTab(JTabbedPane tabPane, String gene) {
        //create the generator with the name given
        String gen = gene.toLowerCase();
        Generator g = new Generator(gen);
        //set up the panel's layout
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setAlignmentX(LEFT_ALIGNMENT);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        //the intro text found on every tab
        JLabel intro = new JLabel("<html>Welome to the " + gen + " generation page.<br>"
                + "Select the number of prompts you want generated from the drop down menu and press the button to get your promts.<br>"
                + "I hope this helps!</html>");
        intro.setAlignmentX(Component.CENTER_ALIGNMENT);
        //creating the panel which holds the generate button and the dropdown menu to select number of prompts
        JPanel buttons = new JPanel();
        buttons.setMaximumSize(new Dimension(Short.MAX_VALUE, 30));
        String[] am = {"1","2","3","4"};
        JComboBox amount = new JComboBox(am);
        JButton button = new JButton("Generate " + gen + " ideas!");
        //adding main components of the button label
        buttons.add(amount);
        buttons.add(button);
        //creating a list of label which show the previous 5 generated prompts
        ArrayList<JLabel> p = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            JLabel prompt = new JLabel("");
            prompt.setName(gen);
            prompt.setOpaque(true);
            prompt.setAlignmentX(Component.CENTER_ALIGNMENT);
            p.add(prompt);
        }
        //setting up listeners
        GenerateableButtonListener PBL = new GenerateableButtonListener(g, p, amount);
        button.addActionListener(PBL);
        toggleRedirect(gen, PBL, buttons); //redirecting to different methods to keep this one clear
        //adding the components to the panel in the correct order
        panel.add(intro);
        panel.add(Box.createRigidArea(new Dimension(0,5)));
        panel.add(buttons);
        panel.add(Box.createRigidArea(new Dimension(0,5)));
        //adding the prompts in a sensible layout
        JPanel prompt = new JPanel();
        prompt.setLayout(new GridLayout(2,2));
        for (JLabel pr: p) prompt.add(pr);
        panel.add(prompt);
        //creating the tab
        tabPane.addTab(gene, panel);
   }
    
    private void toggleRedirect(String s, GenerateableButtonListener g, Container c) {
        //redirects the checkbox button creation to the appropriate method
        //characer is currently the only class to utilise choosable options
        //but this way it can be easily added to the other categories if need be
        if (s.equals("character")) charToggle(g, c);
    }
    
    private void charToggle(GenerateableButtonListener g, Container c) {
        //creates the checkboxes used in character generation
        JCheckBox age = new JCheckBox("Age");
        age.setSelected(true);
        age.setName("age");
        JCheckBox nat = new JCheckBox("Nationality");
        nat.setSelected(true);
        nat.setName("nationality");
        g.addToggle(age);
        g.addToggle(nat);
        c.add(age);
        c.add(nat);
    }
    
    private void templateTab(JTabbedPane tabPane) {
        int tempNum = 1;
        //create the template
        Template temp = new Template(tempNum);
        //set up the panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setAlignmentX(LEFT_ALIGNMENT);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        //the intro text to explain tab
        JLabel intro = new JLabel("<html>Welome to the template page.<br>"
                + "Here you can generate answers to fill in pre-made story templates. Just select the template you want from the drop down menu, and press the button!<br>"
                + "They might not make gramatical sense, but hopefully you'll be inspired!</html>");
        intro.setAlignmentX(Component.CENTER_ALIGNMENT);
        //creating the rest of the panel parts
        JPanel buttons = new JPanel();
        buttons.setMaximumSize(new Dimension(Short.MAX_VALUE, 30));
        JLabel prompt = new JLabel("");
        prompt.setAlignmentX(Component.CENTER_ALIGNMENT);
        //creating the button and dropdown menu
        String[] x = new String[tempNum+1];
        x[0] = "---";
        for (int i = 1; i <= tempNum; i++) x[i] = i+"";
        JComboBox index = new JComboBox(x);
        JButton button = new JButton("Generate a story!");
        button.setEnabled(false);
        //adding button listeners
        TemplateButtonListener tbl = new TemplateButtonListener(prompt, temp, button);
        button.addActionListener(tbl);
        TemplateComboBoxListener cbl = new TemplateComboBoxListener(prompt, temp, tbl);
        index.addActionListener(cbl);
        //adding main components of the button label
        buttons.add(index);
        buttons.add(button);
        //adding everything to panel
        panel.add(intro);
        panel.add(Box.createRigidArea(new Dimension(0,5)));
        panel.add(buttons);
        panel.add(Box.createRigidArea(new Dimension(0,15)));
        panel.add(prompt);
        tabPane.addTab("Templates", panel);
    }
    
    public JFrame getFrame() {
        return frame;
    }
}
