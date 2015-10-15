/**
 * 
 */
package br.ia.tsp.exp.greedy;

import java.io.File;
import java.io.IOException;

/**
 * @author Priscylla
 *
 */
public class GreedyMain {
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
//		File instancePath = new File("./data/tsp/pr76.tsp");
		
		GreedyExp exp = new GreedyExp();
//		exp.run(new File("./data/tsp/pr76.tsp"));
		exp.run(new File("./data/tsp/brazil58.tsp"));
		exp.run(new File("./data/tsp/eil101.tsp"));
		exp.run(new File("./data/tsp/gil262.tsp"));
		
//		GreedySearch greedy = new GreedySearch(instancePath);
//		TSPInstance instance  = new TSPInstance(instancePath);
//		List<Integer> bestPath = null;
//		
//		System.out.println("START");
//		long startTime = System.currentTimeMillis();
//		
//		double lowerCost = Double.MAX_VALUE;
//		for (int i = 1; i <= instance.getDimension(); i++) {
//			List<Integer> newPath = greedy.search(Integer.valueOf(i));
//			double newCost = greedy.cost(newPath); 
//			if(newCost < lowerCost){
//				bestPath = newPath;
//				lowerCost = newCost;
//			}
//		}
//		
//		
//		
//		long endTime = System.currentTimeMillis();
//		System.out.println("It took " + (double) ((endTime - startTime) / 1000) + " seconds");
//		System.out.println("It took " + (double) (((endTime - startTime) / 1000) / 60) + " minutes");
//		System.out.println("Tempo Total: " + (endTime - startTime) + " milliseconds");
//		
//		
//		System.out.println("Path: " + bestPath);
//		System.out.println("Cost: " + lowerCost);

	}

}
