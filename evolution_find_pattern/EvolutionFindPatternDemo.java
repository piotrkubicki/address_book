package evolution_find_pattern;

import java.util.ArrayList;
import java.util.List;

public class EvolutionFindPatternDemo {

	public static void main(String[] args) {
		Population population = new Population(15, 5, true);

		System.out.println("Initial population");
		for (Solution s : population.getPopulation()) {
			System.out.println(s);
		}
		
		List<Integer> pattern = new ArrayList<Integer>();
		pattern.add(2);
		pattern.add(1);
		pattern.add(2);
		pattern.add(6);
		pattern.add(7);
		
		System.out.println("\nPattern: " + pattern);
		
		Solution result = EvoFindPatternAlgorithm.find(population, pattern, 15);
		
		System.out.println("Result: " + result);
		System.out.println("Fitness: " + Population.getHighestFitness());
	}

}
