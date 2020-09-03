package promptgenerator.logic.generateables;

import java.util.*;
import promptgenerator.gui.actionListeners.CheckBoxListener;
import promptgenerator.logic.*;

public class ChaTraits implements Generateable{
    private List<String> age;
    private List<String> nat;
    private List<String> traits;
    private FileReader ageReader;
    private FileReader natReader;
    private FileReader traitReader;
    boolean ageToggle = true;
    boolean natToggle = true;
    CheckBoxListener ageListener;
    CheckBoxListener natListener;
    
    public ChaTraits() {
        this.ageReader = new FileReader("age.txt");
        this.natReader = new FileReader("nationality.txt");
        this.traitReader = new FileReader("character.txt");
        this.age = this.ageReader.read();
        this.nat = this.natReader.read();
        this.traits = this.traitReader.read();
        if (this.age == null) System.out.println("No ages found.");
        if (this.nat == null) System.out.println("No nationalities found.");
        if (this.traits == null) System.out.println("No traits found.");
    }
    
    public ChaTraits(String ageFile,String natFile, String traitFile) {
        this.ageReader = new FileReader(ageFile);
        this.natReader = new FileReader(natFile);
        this.traitReader = new FileReader(traitFile);
        this.age = this.ageReader.read();
        this.nat = this.natReader.read();
        this.traits = this.traitReader.read();
        if (this.age == null) System.out.println("No ages found.");
        if (this.nat == null) System.out.println("No nationalities found.");
        if (this.traits == null) System.out.println("No traits found.");
    }

    @Override
    public ArrayList<String> generate(int x) {
        ArrayList<String> t = new ArrayList<>();
        int rs = this.traits.size()-1;
        if (this.traits == null) return null;
        Random random = new Random();
        ArrayList<String> c = new ArrayList<>();
        if (ageToggle) {
            String ageS = "Age: " + this.age.get(random.nextInt(this.age.size()-1));
            c.add(ageS);
        }
        if (natToggle) {
            String natS = "Nationality: " + this.nat.get(random.nextInt(this.nat.size()-1));
            c.add(natS);
        }
        for (int i = 0; i < x; i++) {
            String s = this.traits.get(random.nextInt(rs));
            while (t.contains(s)) s = this.traits.get(random.nextInt(rs));
            t.add(s);
            int index = s.indexOf('|');
            String s2 = s.substring(index+1);
            s = s.substring(0, index);
            if (random.nextInt(2) == 0) c.add(s2);
            else c.add(s);
        }
        return c;
    }

    @Override
    public List<String> all() {
        List<String> allCha = new ArrayList<>();
        allCha.add("AGES:");
        allCha.addAll(age);
        allCha.add("NATIONALITIES");
        allCha.addAll(nat);
        allCha.add("TRAITS");
        allCha.addAll(traits);
        return allCha;
    }
    
    @Override
    public void toggle(String s, boolean b) {
        if (s.equals("age")) ageToggle = b;
        else natToggle = b;
    }
    
    @Override
    public String toString(){
        return "traits";
    }
    
}
