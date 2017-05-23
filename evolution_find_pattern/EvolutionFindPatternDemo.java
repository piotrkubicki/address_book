package evolution_find_pattern;

import java.util.ArrayList;
import java.util.List;

public class EvolutionFindPatternDemo {

	public static void main(String[] args) {
		final int POPULATION_SIZE = 15;
		final int MAX_NUMBER_OF_GENERATIONS = 10;
		List<Integer> pattern = new ArrayList<Integer>();
		pattern.add(2);
		pattern.add(1);
		pattern.add(2);
		pattern.add(6);
		pattern.add(7);
		
		Population population = new Population(POPULATION_SIZE, pattern.size(), true);

		System.out.println("Initial population");
		for (Solution s : population.getPopulation()) {
			System.out.println(s);
		}
		
		System.out.println("\nPattern: " + pattern);
		
		Solution result = EvoFindPatternAlgorithm.find(population, pattern, MAX_NUMBER_OF_GENERATIONS);
		
		System.out.println("Result: " + result);
		System.out.println("Fitness: " + Population.getHighestFitness());
		System.out.println("Number of generations: " + EvoFindPatternAlgorithm.getGenration());
	}

}
