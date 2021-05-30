import java.io.FileNotFoundException;

/**
 *
 * @author zekikus
 */
// Solves a given 3-SAT 'equation' with the supplied parameters using GA
public class GA {

    private Population currentPopulation;
    private final int POP_SIZE = 6; // Store the population size
    private final int MAX_GENERATION = 5; // Store the maximum generation count
    private final int CHROMOSOME_LENGTH = Utils.nbrVariable; // Store the chromosome length or number of gene count
    private final double MUTATION_RATE = 0.5;

    public void startGA() throws FileNotFoundException {

        currentPopulation = new Population(POP_SIZE, CHROMOSOME_LENGTH);
        currentPopulation.initializePopulation(); // Generate the initial population
        currentPopulation.printPopulation();

        int generation = 0;

        // The first loop runs until the reached max generation count
        while (generation < MAX_GENERATION) {

            Population newPop = new Population(POP_SIZE, CHROMOSOME_LENGTH);
            int counter = 0;
            // The inner loop runs until the POP_SIZE child is produced
            while (counter < POP_SIZE) {
                // Selection - Roulette Wheel Selection
                Individual parent_1 = tournamentSelection();
                Individual parent_2 = tournamentSelection();

                // Crossover operation to the selected pairs
                Individual child = onePointCrossover(parent_1, parent_2);

                // Apply Bitwise Mutation
                bitwiseMutation(child);

                // Calculate Fitness value of the child
                child.evaluate();

                // Set generated child to the new population
                newPop.getIndividuals()[counter] = child;

                counter++;
            }

            int bestIndividualIdx = getFittestSolution();
            int worstIndividualIdx = getWorstSolution(newPop);

            // Elitism - elite individuals replace worst indivual in the generated new population if they are better
            if (currentPopulation.getIndividuals()[bestIndividualIdx].getFitness() > newPop.getIndividuals()[worstIndividualIdx].getFitness()) {
                newPop.getIndividuals()[worstIndividualIdx] = currentPopulation.getIndividuals()[bestIndividualIdx];
            }

            // Used to generation model. Pass every child produced to the next generation;
            currentPopulation = newPop;
            generation++;

            System.out.println("\n GENERATION " + generation);
            currentPopulation.printPopulation();

        }

    }

    public Individual tournamentSelection() {

        // Choose k (the tournament size) individuals from the population at random
        Individual[] pool = Utils.createMatingPool(currentPopulation.getIndividuals(), 5);

        // Set first solution in the population as best parent
        Individual bestIndividual = pool[0]; 

        // Pick the best (fittest solution) as a parent         
        for (Individual individual : pool) {
            if (individual.getFitness() > bestIndividual.getFitness()) {
                bestIndividual = individual;
            }
        }

        return bestIndividual;
    }

    public Individual onePointCrossover(Individual parent1, Individual parent2) {

        Individual child = new Individual(CHROMOSOME_LENGTH, false);

        // Select a random midpoint between 1 and CHROMOSOME_LENGTH - 1
        int midpoint = 1 + (int) (Math.random() * CHROMOSOME_LENGTH - 1);

        // Create the new child
        // Get genes up to the midpoint from first parent
        // Get genes after the midpoint from second parent
        for (int i = 0; i < CHROMOSOME_LENGTH; i++) {
            if (i > midpoint) {
                child.getChromosome()[i] = parent1.getChromosome()[i];
            } else {
                child.getChromosome()[i] = parent2.getChromosome()[i];
            }
        }

        return child;
    }

    // Bitwise bit-flipping with selected mutation probability
    // This mutation operator takes the chosen genome and inverts the bits 
    public void bitwiseMutation(Individual child) {
        for (int i = 0; i < CHROMOSOME_LENGTH; i++) {
            if (Math.random() > MUTATION_RATE) {
                child.getChromosome()[i] = child.getChromosome()[i] ^ 1;
            }
        }
    }

    // Get best/fittest solution in the current population
    public int getFittestSolution() {
        int idx = 0;

        Individual bestIndividual = currentPopulation.getIndividuals()[0];
        for (int i = 0; i < POP_SIZE; i++) {
            if (currentPopulation.getIndividuals()[i].getFitness() > bestIndividual.getFitness()) {
                bestIndividual = currentPopulation.getIndividuals()[i];
                idx = i;
            }

        }

        return idx;
    }

    // Get worst solution in the given population as parameter
    public int getWorstSolution(Population pop) {
        int idx = 0;
        Individual worstIndividual = currentPopulation.getIndividuals()[0];
        for (int i = 0; i < POP_SIZE; i++) {
            if (currentPopulation.getIndividuals()[i].getFitness() < worstIndividual.getFitness()) {
                worstIndividual = currentPopulation.getIndividuals()[i];
                idx = i;
            }

        }

        return idx;
    }

}
