package evolution_find_pattern;

import java.util.ArrayList;
import java.util.List;

public class Population {
	private List<Solution> population;
	private static int highestFitness = 0;
	
	public Population(int populationSize, int solutionLength, boolean initialise) {
		generatePopulation(populationSize, solutionLength, initialise);
	}
	
	private void generatePopulation(int populationSize, int solutionLength, boolean initialise) {
		if (population == null) {
			population = new ArrayList<Solution>();
		}
		
		if (initialise) {
			for (int i = 0; i < populationSize; i++) {
				population.add(Solution.generatePossibleSolution(solutionLength));
			}
		}
	}
	
	public static void calculateFitness(Solution solution, List<Integer> pattern) {
		int fitness = 0;
			
		for (int i = 0; i < solution.size(); i++) {
			if (solution.get(i) == pattern.get(i)) {
				fitness++;
			}
			
			solution.setFitness(fitness);
		}
		
		if (highestFitness < fitness) highestFitness = fitness;
	}
	
	public List<Solution> getPopulation() {
		return population;
	}
	
	public static int getHighestFitness() {
		return highestFitness;
	}
	
	public int size() {
		return population.size();
	}
	
	public Solution get(int index) {
		return population.get(index);
	}
	
	public void add(Solution solution) {
		population.add(solution);
	}
	
	public void remove(Solution solution) {
		population.remove(solution);
	}
}
