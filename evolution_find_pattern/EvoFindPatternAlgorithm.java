package evolution_find_pattern;

import java.util.ArrayList;
import java.util.List;

public class EvoFindPatternAlgorithm {
	private static int generation = 0;
	/**
	 * Takes two solutions as arguments and return new solution containing random values from both parents  
	 * @param solution1 Solution
	 * @param solution2 Solution
	 * @return Solution
	 */
	private static Solution crossover(Solution solution1, Solution solution2) {
		Solution newSolution = Solution.generatePossibleSolution(0); // empty solution
		
		for (int i = 0; i < solution1.size(); i++) {
			int selector = (int)Math.round(Math.random() * 2);
			
			if (selector == 0) {
				newSolution.add(solution1.get(i));
			} else {
				newSolution.add(solution2.get(i));
			}
		}
		
		return newSolution;
	}
	
	/**
	 * Select random value from sequence and replace it with new random value
	 * @param solution Solution
	 */
	private static void mutate(Solution solution) {
		int index = (int)Math.round(Math.random() * solution.size() - 1);
		
		if (index < 0) {
			index = 0;
		}
		
		solution.set(index, (int)Math.round(Math.random() * 11));
	}
	
	/**
	 * Main function that runs the algorithm.
	 * @param population Population
	 * @param pattern List<Integer>
	 * @param maxGenerations int
	 * @return Solution
	 */
	public static Solution find(Population population, List<Integer> pattern, int maxGenerations) {
		Solution result = null;
		
		for (int i = 0; i < maxGenerations; i++) {
			population = EvoFindPatternAlgorithm.evolve(population, pattern);
			
			generation++;
			
			if (Population.getHighestFitness() == pattern.size()) break;
		}
		
		result = population.getPopulation().get(0);
		
		for (int i = 0; i < population.size(); i++) {
			if (result.getFitness() < population.get(i).getFitness()) {
				result = population.get(i);
			}
		}
		
		return result;
	}
	
	/**
	 * Used to create new generations.
	 * @param population Population
	 * @param pattern List<Integer>
	 * @return Population
	 */
	private static Population evolve(Population population, List<Integer> pattern) {
		Population newPopulation = new Population(0, 0, false); //TODO
		List<Solution> populationList = new ArrayList<Solution>();
		// copy population list
		for (Solution solution : population.getPopulation()) {
			populationList.add(solution);
		}
		
		int populationSize = population.size();
		
		for (int i = 0; i < populationSize; i++) {
			List<Solution> selectedPair = selectPair(populationList);
			Solution newSolution = crossover(selectedPair.get(0), selectedPair.get(1));
			mutate(newSolution);
			Population.calculateFitness(newSolution, pattern);
			population.getPopulation().add(newSolution);
		}
		
		while (newPopulation.size() < populationSize) {
			Solution temp = population.getPopulation().get(0);
			
			for (int i = 1; i < population.size(); i++) {
				if (temp.getFitness() < population.get(i).getFitness()) {
					temp = population.get(i);
				}
			}
			
			newPopulation.add(temp);
			population.remove(temp);
		}
		
		return newPopulation;
	}
	
	/**
	 * Select solution with highest fitness.
	 * @param population List<Solution>
	 * @return List<Solution>
	 */
	private static List<Solution> selectPair(List<Solution> population) {
		List<Solution> selectedPair = new ArrayList<Solution>();
		Solution temp = population.get(0);
		selectedPair.add(null);
		selectedPair.add(null);
		
		while (selectedPair.get(1) == null) {
			for (int i = 1; i < population.size(); i++) {
				if (temp.getFitness() < population.get(i).getFitness()) {
					temp = population.get(i);
				}
			}
			
			if (selectedPair.get(0) == null) {
				selectedPair.set(0, temp);
			} else {
				selectedPair.set(1, temp);
			}
		}
		
		return selectedPair;
	}
	
	public static int getGenration() {
		return generation;
	}
	
}
