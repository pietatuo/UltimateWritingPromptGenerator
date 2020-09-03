package promptgenerator.logic.generateables;

import java.util.*;
import promptgenerator.logic.*;

public class Genre implements Generateable {
    private List<String> genres;
    private FileReader reader;
    
    public Genre() {
        this.reader = new FileReader("genres.txt");
        this.genres = this.reader.read();
        if (this.genres == null) System.out.println("No genres found.");
    }
    
    public Genre(String file) {
        this.reader = new FileReader(file);
        this.genres = this.reader.read();
        if (this.genres == null) System.out.println("No genres found.");
    }

    @Override
    public ArrayList<String> generate(int x) {
        int rs = this.genres.size()-1;
        if (this.genres == null) return null;
        ArrayList<String> g = new ArrayList<>();
        ArrayList<String> dupes = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < x; i++) {
            String nextGen = this.genres.get(random.nextInt(rs));
            while (dupes.contains(nextGen)) nextGen = this.genres.get(random.nextInt(rs));
            dupes.add(nextGen);
            g.add(nextGen);
        }
        return g;
    }

    @Override
    public List<String> all() {
        return this.genres;
    }

    @Override
    public void toggle(String s, boolean b) {
        //currently nothing to toggle
        System.out.println(":)");
    }
    
    @Override
    public String toString() {
        return "genres";
    }
}