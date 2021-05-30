package week_12_GA;

import java.io.FileNotFoundException;

/**
 *
 * @author zekikus
 */
public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        
        Utils.readFile("src/problems/3_20_1.dimacs");
        Utils.printEquation();
        GA ga = new GA();
        ga.startGA();
    }
}
