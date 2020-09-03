package promptgenerator.logic;

import java.util.*;

public interface Generateable {
    ArrayList<String> generate(int x);
    List<String> all();
    void toggle(String s, boolean b);
}