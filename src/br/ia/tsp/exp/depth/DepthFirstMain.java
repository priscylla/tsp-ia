/**
 * 
 */
package br.ia.tsp.exp.depth;

import java.io.File;
import java.io.IOException;

/**
 * @author Priscylla
 *
 */
public class DepthFirstMain {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		DepthFirstExp exp = new DepthFirstExp();
		exp.run(new File("./data/tsp/ulysses16.tsp"));

	}

}
