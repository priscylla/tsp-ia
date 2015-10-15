/**
 * 
 */
package br.ia.tsp.algorithms;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.moeaframework.core.Algorithm;
import org.moeaframework.core.EvolutionaryAlgorithm;
import org.moeaframework.core.Problem;
import org.moeaframework.core.Solution;
import org.moeaframework.core.spi.AlgorithmFactory;
import org.moeaframework.core.variable.EncodingUtils;
import org.moeaframework.problem.AbstractProblem;
import org.moeaframework.problem.tsplib.TSP2OptHeuristic;
import org.moeaframework.problem.tsplib.TSPInstance;
import org.moeaframework.problem.tsplib.Tour;

/**
 * @author Priscylla
 *
 */
public class GASearch {
	
	/**
	 * Converts a MOEA Framework solution to a {@link Tour}.
	 * 
	 * @param solution the MOEA Framework solution
	 * @return the tour defined by the solution
	 */
	public static Tour toTour(Solution solution) {
		int[] permutation = EncodingUtils.getPermutation(
				solution.getVariable(0));
		
		// increment values since TSP nodes start at 1
		for (int i = 0; i < permutation.length; i++) {
			permutation[i]++;
		}
		
		return Tour.createTour(permutation);
	}
	
	/**
	 * Saves a {@link Tour} into a MOEA Framework solution.
	 * 
	 * @param solution the MOEA Framework solution
	 * @param tour the tour
	 */
	public static void fromTour(Solution solution, Tour tour) {
		int[] permutation = tour.toArray();
		
		// decrement values to get permutation
		for (int i = 0; i < permutation.length; i++) {
			permutation[i]--;
		}
		
		EncodingUtils.setPermutation(solution.getVariable(0), permutation);
	}
	
	/**
	 * The optimization problem definition.  This is a 1 variable, 1 objective
	 * optimization problem.  The single variable is a permutation that defines
	 * the nodes visited by the salesman.
	 */
	public static class TSPProblem extends AbstractProblem {

		/**
		 * The TSP problem instance.
		 */
		private final TSPInstance instance;
		
		/**
		 * The TSP heuristic for aiding the optimization process.
		 */
		private final TSP2OptHeuristic heuristic;
		
		/**
		 * Constructs a new optimization problem for the given TSP problem
		 * instance.
		 * 
		 * @param instance the TSP problem instance
		 */
		public TSPProblem(TSPInstance instance) {
			super(1, 1);
			this.instance = instance;
			
			heuristic = new TSP2OptHeuristic(instance);
		}

		@Override
		public void evaluate(Solution solution) {
			Tour tour = toTour(solution);
			
			// apply the heuristic and save the modified tour
			heuristic.apply(tour);
			fromTour(solution, tour);

			solution.setObjective(0, tour.distance(instance));
		}

		@Override
		public Solution newSolution() {
			Solution solution = new Solution(1, 1);
			
			solution.setVariable(0, EncodingUtils.newPermutation(
					instance.getDimension()));
			
			return solution;
		}
		
	}
	
	public void search(File instancePath) throws IOException{
		// create the TSP problem instance and display panel
				TSPInstance instance = new TSPInstance(instancePath);
				
				// create the optimization problem and evolutionary algorithm
				Problem problem = new TSPProblem(instance);
				
				Properties properties = new Properties();
				properties.setProperty("swap.rate", "0.2"); //Taxa de mutação (troca duas posições)
				properties.setProperty("insertion.rate", "0.3"); //Taxa de mutação (troca uma posição)
				properties.setProperty("pmx.rate", "0.7"); // Taxa de cruzamento que contém um operador de reparo para garantir a descendencia valida
				properties.setProperty("populationSize", "100");//Tamanho da população
				
				Algorithm algorithm = AlgorithmFactory.getInstance().getAlgorithm(
						"NSGAII", properties, problem);
				
				int iteration = 0;
				double bestDistance = 0;
				Tour best = null;
				double previousDist = 0;
				int qntRep = 0;
				
				// now run the evolutionary algorithm
				//while(iteration<=50) {
				while(iteration<200 || (qntRep<20 || qntRep==0)){
					algorithm.step();
					iteration++;
					

					// display population with light gray lines
					if (algorithm instanceof EvolutionaryAlgorithm) {
						EvolutionaryAlgorithm ea = (EvolutionaryAlgorithm)algorithm;
						
						int nSolution = 1;
						
						for (Solution solution : ea.getPopulation()) {
							//System.out.println(nSolution + " : " + toTour(solution).toString() + " Cost: " + toTour(solution).distance(instance));
							//nSolution++;
						}
					}
					
					// display current optimal solutions with red line
					best = toTour(algorithm.getResult().get(0));

//					System.out.println("Iteration " + iteration + ": " +
//							best.distance(instance) + "\n");
					
					bestDistance = best.distance(instance);
										
					if(iteration>=20){
						if(bestDistance==previousDist){
							qntRep++;
						} else {
							previousDist = bestDistance;
							qntRep = 0;
						}
					}
					
				}
				
				System.out.println("Iteration: " + iteration + " | Best Distance: " + bestDistance);
				System.out.println("Best Tour: " + best.toString());
				System.out.println("Best Tour: " + best.distance(instance));
	}

}
