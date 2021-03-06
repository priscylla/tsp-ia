/**
 * 
 */
package br.ia.tsp.exp.depth;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.moeaframework.problem.tsplib.TSPInstance;

import br.ia.tsp.algorithms.DepthSearch;

/**
 * @author Priscylla
 *
 */
public class DepthExp {
	
	public void run(File instancePath) throws IOException{
		DepthSearch depth = new DepthSearch(instancePath);
		TSPInstance instance  = new TSPInstance(instancePath);
		List<Integer> bestPath = null;
		
		System.out.println("-------- START :" + instance.getName() + " ----------");
		long startTime = System.currentTimeMillis();
		
		double lowerCost = Double.MAX_VALUE;
		for (int i = 1; i <= instance.getDimension(); i++) {
			List<Integer> newPath = depth.search(Integer.valueOf(i));
			double newCost = depth.cost(newPath); 
			if(newCost < lowerCost){
				bestPath = newPath;
				lowerCost = newCost;
			}
		}
		
		
		
		long endTime = System.currentTimeMillis();
		System.out.println("It took " + (double) ((endTime - startTime) / 1000) + " seconds");
		System.out.println("It took " + (double) (((endTime - startTime) / 1000) / 60) + " minutes");
		System.out.println("Tempo Total: " + (endTime - startTime) + " milliseconds");
		
		
		System.out.println("Path: " + bestPath);
		System.out.println("Cost: " + lowerCost);
	}

}
