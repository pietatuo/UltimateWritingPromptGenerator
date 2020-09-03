package promptgenerator.logic.generateables;

import java.util.*;
import promptgenerator.logic.*;

public class PlotDevice implements Generateable {
    private List<String> plotDevices;
    private FileReader reader;
    
    public PlotDevice() {
        this.reader = new FileReader("plotpoints.txt");
        this.plotDevices = this.reader.read();
        if (this.plotDevices == null) System.out.println("No plotpoints found.");
    }
    
    public PlotDevice(String file) {
        this.reader = new FileReader(file);
        this.plotDevices = this.reader.read();
        if (this.plotDevices == null) System.out.println("No plotpoints found.");
    }

    @Override
    public ArrayList<String> generate(int x) {
        int rs = this.plotDevices.size()-1;
        if (this.plotDevices == null) return null;
        ArrayList<String> p = new ArrayList<>();
        ArrayList<String> dupes = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < x; i++) {
            String nextPlot = this.plotDevices.get(random.nextInt(rs));
            while (dupes.contains(nextPlot)) nextPlot = this.plotDevices.get(random.nextInt(rs));
            dupes.add(nextPlot);
            p.add(nextPlot);
        }
        return p;
    }

    @Override
    public List<String> all() {
        return this.plotDevices;
    }

    @Override
    public void toggle(String s, boolean b) {
        //currently nothing to toggle
        System.out.println(":)");
    }
    
    @Override
    public String toString() {
        return "plots";
    }
}