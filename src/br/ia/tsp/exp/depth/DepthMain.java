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
public class DepthMain {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		DepthExp exp = new DepthExp();
//		exp.run(new File("./data/tsp/pr76.tsp"));
		exp.run(new File("./data/tsp/brazil58.tsp"));
		exp.run(new File("./data/tsp/eil101.tsp"));
		exp.run(new File("./data/tsp/gil262.tsp"));

	}

}
