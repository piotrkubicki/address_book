package evolution_find_pattern;

import java.util.ArrayList;
import java.util.List;

public class Population {
	private List<Solution> population;
	
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
	
	public List<Solution> getPopulation() {
		return population;
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
