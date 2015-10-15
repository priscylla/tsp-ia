/**
 * 
 */
package br.ia.tsp.exp.ga;

import java.io.File;
import java.io.IOException;

import br.ia.tsp.algorithms.GASearch;

/**
 * @author Priscylla
 *
 */
public class GAMain {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		GASearch ga = new GASearch();
		
		for (int i = 1; i <= 3; i++) {
			
		
		System.out.println("--------Execução: " + i +"       ---------- Brazil 58");
		long startTime = System.currentTimeMillis();
		
		ga.search(new File("./data/tsp/brazil58.tsp"));
		
		long endTime = System.currentTimeMillis();
		System.out.println("It took " + (double) ((endTime - startTime) / 1000) + " seconds");
		System.out.println("It took " + (double) (((endTime - startTime) / 1000) / 60) + " minutes");
		System.out.println("Tempo Total: " + (endTime - startTime) + " milliseconds");
		
		
		ga = new GASearch();
		System.out.println("--------Execução: " + i +"       ---------- eil 101");
		startTime = System.currentTimeMillis();
		
		ga.search(new File("./data/tsp/eil101.tsp"));
		
		endTime = System.currentTimeMillis();
		System.out.println("It took " + (double) ((endTime - startTime) / 1000) + " seconds");
		System.out.println("It took " + (double) (((endTime - startTime) / 1000) / 60) + " minutes");
		System.out.println("Tempo Total: " + (endTime - startTime) + " milliseconds");
		
		ga = new GASearch();
		System.out.println("--------Execução: " + i +"       ---------- gil 262");
		startTime = System.currentTimeMillis();
		
		ga.search(new File("./data/tsp/gil262.tsp"));
		
		endTime = System.currentTimeMillis();
		System.out.println("It took " + (double) ((endTime - startTime) / 1000) + " seconds");
		System.out.println("It took " + (double) (((endTime - startTime) / 1000) / 60) + " minutes");
		System.out.println("Tempo Total: " + (endTime - startTime) + " milliseconds");
		
		}

	}

}
