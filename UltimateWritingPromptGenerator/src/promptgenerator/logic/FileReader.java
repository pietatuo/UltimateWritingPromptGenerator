package promptgenerator.logic;

import java.io.File;
import java.util.*;

public class FileReader {
    private File file;

    public FileReader(String file) {
        this.file = new File(file);
    }
    
    public List<String> read() {
        List<String> read = new ArrayList<>();
        Scanner reader = null;
        try {
            reader = new Scanner(this.file);
        }
        catch (Exception e) {
            System.out.println("Could not find file.");
            return null;
        }
        while (reader.hasNextLine()) read.add(reader.nextLine());
        return read;
    }
}