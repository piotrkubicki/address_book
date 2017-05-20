package evolution_find_pattern;

import java.util.ArrayList;
import java.util.List;

public class Solution {
	private List<Integer> solution;
	private int fitness = 0;
	
	private Solution(int length) {
		solution = new ArrayList<Integer>();
		
		for (int j = 0; j < length; j++) {
			solution.add((int)Math.round(Math.random() * 11));
		}
	}
	
	public static Solution generatePossibleSolution(int length) {
		return new Solution(length);
	}
	
	public List<Integer> getSolution() {
		return solution;
	}
	
	public int getFitness() {
		return fitness;
	}
	
	public void setFitness(int fitness) {
		this.fitness = fitness;
	}
	
	public void add(int value) {
		solution.add(value);
	}
	
	@Override
	public String toString() {
		return "Solution: " +  solution.toString();
	}
}
