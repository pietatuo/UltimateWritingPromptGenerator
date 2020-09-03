package promptgenerator;

import java.util.List;
import javax.swing.SwingUtilities;
import promptgenerator.gui.*;
import promptgenerator.logic.*;
import promptgenerator.logic.generateables.*;

public class Main {

    public static void main(String[] args) {
        Interface kayttoliittyma = new Interface();
        SwingUtilities.invokeLater(kayttoliittyma);
        System.out.println("Just keep writing");
//        Generator generator = new Generator(new Setting());
//        generator.generate();
//        generator.generate();
//        generator.generate();
//        List<String> returned = generator.returnGenerated();
//        for (String g : returned) {
//            System.out.println(g);
//        }
//        generator.resetGenerated();
//        System.out.println("");
//        generator.generate();
//        System.out.println(generator.returnGenerated());
    }
    
}
