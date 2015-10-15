/**
 * 
 */
package br.ia.tsp;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.moeaframework.problem.tsplib.TSPInstance;

import br.ia.tsp.algorithms.DepthFirstSearch;
import br.ia.tsp.algorithms.DepthSearch;
import br.ia.tsp.algorithms.star.AStar;
import br.ia.tsp.algorithms.star.dois.AStar2;

/**
 * @author Priscylla
 *
 */
public class Teste {
	
	public static void main(String[] args) throws IOException {
		// create the TSP problem instance
		TSPInstance instance = new TSPInstance(
				new File("./data/tsp/pr76.tsp"));		
		
//		System.out.println(instance.getDistanceTable().getDistanceBetween(1, 1));
//		System.out.println(instance.getDimension());
		
		
		
		System.out.println("INICIO");
		
//		AStar2 star = new AStar2(new File("./data/tsp/ulysses16.tsp")); //1 4 3 2 4
//		star.search(Integer.valueOf(1));
		
		AStar star = new AStar(new File("./data/tsp/ulysses16.tsp"), Integer.valueOf(1));
		String result = star.execute();
		System.out.println(result);
		
		star = new AStar(new File("./data/tsp/brazil58.tsp"), Integer.valueOf(1));
		result = star.execute();
		System.out.println(result);
		
		star = new AStar(new File("./data/tsp/eil101.tsp"), Integer.valueOf(1));
		result = star.execute();
		System.out.println(result);
		
		star = new AStar(new File("./data/tsp/gil262.tsp"), Integer.valueOf(1));
		result = star.execute();
		System.out.println(result);
		
//		DepthFirstSearch depthFirst = new DepthFirstSearch(new File("./data/tsp/ulysses16.tsp"), Integer.valueOf(1));
//		String result = depthFirst.execute();
//		System.out.println("Melhor custo encontrado: " + result);
		
//		DepthSearch depth = new DepthSearch(new File("./data/tsp/pr76.tsp"));
//		List<Integer> caminho = depth.search(Integer.valueOf(1));
//		System.out.println(caminho);
//		System.out.println(depth.cost(caminho));
//		System.out.println("FIM");
		
//		GreedySearch greedy = new GreedySearch(new File("./data/tsp/pr76.tsp"));
//		List<Integer> caminho = greedy.search(Integer.valueOf(1));
//		System.out.println(caminho);
//		System.out.println(greedy.cost(caminho));


	}

}
