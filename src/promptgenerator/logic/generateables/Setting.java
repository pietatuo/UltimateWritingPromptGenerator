package promptgenerator.logic.generateables;

import java.util.*;
import promptgenerator.logic.*;

public class Setting implements Generateable {
    private List<String> settings;
    private FileReader reader;
    
    public Setting() {
        this.reader = new FileReader("settings.txt");
        this.settings = this.reader.read();
        if (this.settings == null) System.out.println("No settings found.");
    }
    
    public Setting(String file) {
        this.reader = new FileReader(file);
        this.settings = this.reader.read();
        if (this.settings == null) System.out.println("No settings found.");
    }

    @Override
    public ArrayList<String> generate(int x) {
        int rs = this.settings.size()-1;
        if (this.settings == null) return null;
        ArrayList<String> s = new ArrayList<>();
        ArrayList<String> generated = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < x; i++) {
            String nextSet = this.settings.get(random.nextInt(rs));
            while (generated.contains(nextSet)) nextSet = this.settings.get(random.nextInt(rs));
            generated.add(nextSet);
            s .add(nextSet);
        }
        return s;
    }

    @Override
    public List<String> all() {
        return this.settings;
    }

    @Override
    public void toggle(String s, boolean b) {
        //currently nothing to toggle
        System.out.println(":)");
    }
    
    @Override
    public String toString() {
        return "settings";
    }
}