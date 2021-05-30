/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package week_12_GA;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author zekikus
 */
public class Utils {

    static int nbrClause; // Number of clauses
    static int nbrVariable; // Number of variable
    static int[][] conjunctives; // Store the all conjunctives

    public static void readFile(String fileName) throws FileNotFoundException {
        Scanner reader = new Scanner(new File(fileName));

        int nbrLine = 0;
        int conjunctive = 0;

        // The loop will run until the lines in the file are finished.
        while (reader.hasNext()) {
            String nextLine = reader.nextLine(); // Get Next Line

            if (nbrLine == 0) {
                nbrLine++;
                String[] data = nextLine.split(" ");
                nbrClause = Integer.parseInt(data[3]);
                nbrVariable = Integer.parseInt(data[2]);
                conjunctives = new int[nbrClause][nbrVariable];
                continue;
            } // Skip First Row

            // Split Line
            String[] line = nextLine.replace("\n", "").split(" ");
            for (int i = 0; i < line.length - 1; i++) {
                int value = Integer.parseInt(line[i]);
                conjunctives[conjunctive][Math.abs(value) - 1] = value;
            }

            conjunctive++;
        }

    }

    // You can use many selection operation to create the mating pool
    // 1) Get random n solution from the current population (without replacement)
    // 2) Select random k solution from the current population, k must be equal to the population size
    // If you use the second method possibly more than one copy of some individuals in the mating pool (within replacement)
    public static Individual[] createMatingPool(Individual[] population, int poolSize) {

        // Creates the random numbers without replacement
        // Length of the random selected Indexes array will be equal to poolSize in this example
        int[] randIndexes = new Random().ints(0, population.length - 1).distinct().limit(poolSize).toArray();

        // Copy selected Individuals from the current population to the matingPool
        Individual[] matingPool = new Individual[poolSize];
        for (int i = 0; i < matingPool.length; i++) {
            matingPool[i] = population[randIndexes[i]];
        }

        return matingPool;
    }

    public static void printEquation() {
        String equation = "";
        for (int i = 0; i < conjunctives.length; i++) {
            equation += "(";
            int counter = 0;
            for (int j = 0; j < conjunctives[i].length; j++) {
                if(conjunctives[i][j] == 0) continue;
                
                if (conjunctives[i][j] < 0) {
                    equation += "¬x_" + Math.abs(conjunctives[i][j]);
                } else {
                    equation += "x_" + conjunctives[i][j];
                }
                
                counter++;
                if (counter != 3) {
                    equation += " v ";
                }
                
            }

            equation += ")";

            if (i != conjunctives.length - 1) {
                equation += " ^ ";
            }
        }

        System.out.println("Equation: " + equation);
    }

}
