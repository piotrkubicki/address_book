package evolution_find_pattern;

import java.util.ArrayList;
import java.util.List;

public class EvoFindPatternAlgorithm {
	
	public static Solution crossover(Solution solution1, Solution solution2) {
		Solution newSolution = Solution.generatePossibleSolution(0); // empty solution
		
		for (int i = 0; i < solution1.getSolution().size(); i++) {
			int selector = (int)Math.round(Math.random() * 2);
			
			if (selector == 0) {
				newSolution.add(solution1.getSolution().get(i));
			} else {
				newSolution.add(solution2.getSolution().get(i));
			}
		}
		
		return newSolution;
	}
	
	public static void mutate(Solution solution, List<Integer> pattern) {
		int selector = (int)Math.round(Math.random() * solution.getSolution().size() - 1);
		
		if (selector < 0) {
			selector = 0;
		}
		
		solution.getSolution().set(selector, (int)Math.round(Math.random() * 11));
		calculateFitness(solution, pattern);
	}
	
	public static Solution find(Population population, List<Integer> pattern, int generations) {
		Solution result = null;
		
		for (int i = 0; i < generations; i++) {
			population = EvoFindPatternAlgorithm.evolve(population, pattern);
		}
		
		result = population.getPopulation().get(0);
		
		for (int i = 0; i < population.getPopulation().size(); i++) {
			if (result.getFitness() < population.getPopulation().get(i).getFitness()) {
				result = population.getPopulation().get(i);
			}
		}
		
		return result;
	}
	
	public static Population evolve(Population population, List<Integer> pattern) {
		Population newPopulation = new Population(0, 0, false); //TODO
		List<Solution> populationList = new ArrayList<Solution>();
		// copy population list
		for (Solution solution : population.getPopulation()) {
			populationList.add(solution);
		}
		
		int populationSize = population.getPopulation().size();
		
		for (int i = 0; i < populationSize; i++) {
			List<Solution> selectedPair = selectPair(populationList);
			Solution newSolution = crossover(selectedPair.get(0), selectedPair.get(1));
			mutate(newSolution, pattern);
			calculateFitness(newSolution, pattern);
			population.getPopulation().add(newSolution);
		}
		
		while (newPopulation.getPopulation().size() < populationSize) {
			Solution temp = population.getPopulation().get(0);
			
			for (int i = 1; i < population.getPopulation().size(); i++) {
				if (temp.getFitness() < population.getPopulation().get(i).getFitness()) {
					temp = population.getPopulation().get(i);
				}
			}
			
			newPopulation.getPopulation().add(temp);
			population.getPopulation().remove(temp);
		}
		
		return newPopulation;
	}
	
	// Select solution with highest fitness
	public static List<Solution> selectPair(List<Solution> population) {
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
	
	public static void calculateFitness(Solution solution, List<Integer> pattern) {
		int fitness = 0;
			
		for (int i = 0; i < solution.getSolution().size(); i++) {
			if (solution.getSolution().get(i) == pattern.get(i)) {
				fitness++;
			}
			
			solution.setFitness(fitness);
			
		}	
	}
	
}
