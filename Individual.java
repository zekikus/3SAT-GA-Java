import java.util.Arrays;

/**
 *
 * @author zekikus
 */
// Each individual has fitness value and chromosome array
public class Individual {

    private int fitness; // Store the fitness value of this individual, for this problem fitness value store the number of 1's count
    private int[] chromosome; // Store the each gene of the chromosome

    public Individual(int length, boolean init) {
        chromosome = new int[length];
        if (init) {
            // Fill chrosome array with random values between 0-1
            initializeChromosome();
            // Evaluate this individual
            evaluate();
        }
    }

    // Initialize the genes of the chrosome, individual
    public void initializeChromosome() {
        for (int i = 0; i < chromosome.length; i++) {
            if (Math.random() >= 0.5) {
                chromosome[i] = 1;
            } else {
                chromosome[i] = 0;
            }
        }
    }


    // In order to make a choice in the genetic algorithm, it is necessary to evaluate the good or bad of the solution.
    // For problems with constraint satisfaction type, the solution is either feasible or infeasible.
    // To be able to compare the two solutions, we need a fitness function.
    // Calculate the fitness value of this chromosome, individual
    // The fitness value is found by calculating the number of sub clauses that are true.
    public void evaluate() {

        // Get conjunctive or clauses in this problem set
        int[][] conjunctives = Utils.conjunctives;

        // Iterate each clause
        // We represent each clause as following:
            // ': represent the complement
            // ^: represent the and operation
            // Let asssume we have clause like this: (x1 V 'x2 V x4) ^ ('x2 V x3 V x4) ^ ('x3 V 'x4 V x5)
            // For example this is the toy 3sat example
            // We can represent this clause by 2d array: [[1 -2 4], [-2 3 4], [-3 -4 5]]
            // Each of this arrays represent the each clause, or conjunctive
        for (int[] conjunctive : conjunctives) {
            for (int v : conjunctive) {
                if(v == 0) continue;
                // If one of the literals in this boolean expression is equal to 1, the result of this subclause is one.
                
                if (v < 0) {
                    // If a literal has a negative value, we must check comparison for its complement.
                    // If the gene value corresponding to this literal is equal to 0, its complement will be 1.
                    // Thus, the result of this sub clause will also be 1. 
                    // Due to the truth table of the OR operation.
                    // If one of the variables in the OR expression has a value of 1, the result of that expression is 1.
                    if (chromosome[Math.abs(v) - 1] == 0) {
                        fitness++;
                        break;
                    }
                } else {
                    // Otherwise, check gene value corresponding to this literal, if this value is equal to 1, sub clause will be 1
                    if (chromosome[v - 1] == 1) {
                        fitness++;
                        break;
                    }
                }

                // If even one of the above conditions is satisfied, move on to the next clause.
            }
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(chromosome) + " - Fitness:" + fitness;
    }

    public int getFitness() {
        return fitness;
    }

    public int[] getChromosome() {
        return chromosome;
    }

}
