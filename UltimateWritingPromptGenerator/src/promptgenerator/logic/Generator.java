package promptgenerator.logic;

import java.util.*;
import promptgenerator.logic.generateables.*;

public class Generator {
    private Generateable generatee; //what's being generated
    
    public Generator(Generateable generatee) {
        this.generatee = generatee;
    }
    
    public Generator(String g) {
        switch (g) {
            case "character":
                this.generatee = new ChaTraits();
                break;
            case "setting":
                this.generatee = new Setting();
                break;
            case "genre":
                this.generatee = new Genre();
                break;
            default:
                this.generatee = new PlotDevice();
                break;
        }
    }
    
    public ArrayList<String> generate(int x) {
        return this.generatee.generate(x);
    }
    
    public void toggle(String s, boolean b) {
        this.generatee.toggle(s, b);
    }
    
    @Override
    public String toString() {
        return this.generatee.toString();
    }
}
