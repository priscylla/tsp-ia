/**
 * 
 */
package br.ia.tsp.exp.depth;

import java.io.File;
import java.io.IOException;

import br.ia.tsp.algorithms.DepthFirstSearch;

/**
 * @author Priscylla
 *
 */
public class DepthFirstExp {
	
	public void run(File instancePath) throws IOException{
		DepthFirstSearch depthFirst = new DepthFirstSearch(instancePath, Integer.valueOf(1));
		String result = depthFirst.execute();
		System.out.println("Melhor custo encontrado: " + result);
	}

}
