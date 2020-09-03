package promptgenerator.logic;

import java.util.*;
import promptgenerator.logic.generateables.*;

public class Template {
    private List<List<String>> templates = new ArrayList<>();
    private FileReader reader;
    private final Genre gen = new Genre(); //#
    private final Setting set = new Setting(); //¤
    private final PlotDevice plot = new PlotDevice(); //%
    private final ChaTraits cha = new ChaTraits(); //&
    
    public Template(int x) {
        for (int i = 1; i <= x; i++) {
            this.reader = new FileReader("template"+i+".txt");
            List<String> t = reader.read();
            if (t == null) System.out.println("No template found.");
            else templates.add(t);
        }
        this.cha.toggle("age", false);
        this.cha.toggle("nat", false);
    }
    
    public Template(String file) {
        this.reader = new FileReader(file);
        List<String> t = reader.read();
        if (t == null) System.out.println("No template found.");
        else templates.add(t);
        this.cha.toggle("age", false);
        this.cha.toggle("nat", false);
    }
    
    public String generate(int temp) {
//        System.out.println("eyyy lmao");
        List<String> t = templates.get(temp);
        int s = Integer.parseInt(t.get(0));
        int p = Integer.parseInt(t.get(1));
        int c = Integer.parseInt(t.get(2));
        String story = t.get(3);
        //generate the correct number of each
        ArrayList<String> settings = set.generate(s);
        ArrayList<String> plots = plot.generate(p);
        ArrayList<String> characteristics = cha.generate(c);
        //read through the template and add the generated answers
        for (int i = 0; i < s; i++) story = story.replaceFirst("¤", settings.get(i));
        for (int i = 0; i < p; i++) story = story.replaceFirst("%", plots.get(i));
        for (int i = 0; i < c; i++) story = story.replaceFirst("&", characteristics.get(i));
        
        
        return story;
    }
    
    public String returnTemplate(int i) {
        if (i < 0) return "That is not a template, you silly goose, why would you pick it?";
        String s =  templates.get(i).get(3);
        s = s.replaceAll("#", "___");
        s = s.replaceAll("¤", "___");
        s = s.replaceAll("%", "___");
        s = s.replaceAll("&", "___");
        
        return s;
    }
}
