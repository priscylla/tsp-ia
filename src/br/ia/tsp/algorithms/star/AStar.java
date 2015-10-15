package br.ia.tsp.algorithms.star;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

import org.moeaframework.problem.tsplib.DistanceTable;
import org.moeaframework.problem.tsplib.TSPInstance;

public class AStar {
	
	private TSPInstance instance;
	private DistanceTable distances;
	
	Integer sourceCity;
	
	PriorityQueue<Town> opened = new PriorityQueue<Town>(5000, new Comparator<Town>() {

		@Override
		public int compare(Town a, Town b) {
			return (int) (a.f - b.f);
		}
	} );
	
	
    
    String result;
    
    ArrayList<Integer> optimumRoute, followedRoute;
    int nodes = 0;
    double routeCost = 0;
    double optimumCost = Double.MAX_VALUE;     
    
    // Estimation of the cost between two cities, it can overestimate the real value (h' > h),
    // so the algorithm it's not optimum.
    //int HEURISTICCONSTANT = 15;
    
    /**
     * Gets the heuristic value for a given depth
     * The level 0 has the maximum value.
     */
    private int getHeuristicValue (int node) {
        return (int) distances.getDistanceBetween(sourceCity.intValue(), node);
        //return HEURISTICCONSTANT * (instance.getDimension() - level);
    }
    
    /** Creates a new instance of AStar 
     * @throws IOException */
    public AStar(File instancePath, Integer sourceCity) throws IOException {
    	instance = new TSPInstance(instancePath);
        distances = instance.getDistanceTable();
        this.sourceCity = sourceCity;        
    }
    
    /**
     * executes the algorithm
     */
    public String execute() {
    	
    	result = new String();

        // have we found the solution?
        boolean solution = false;
        
        // start the timer
        long startTime = System.currentTimeMillis();
        
        // initial town
        //opened.add(new Town(sourceCity.intValue(), 0, getHeuristicValue(0), 0));
        opened.add(new Town(sourceCity.intValue(), 0, 0, 0));
        
        while (!opened.isEmpty() && !solution) {
            // gets the city with lower g value
            Town currentTown = opened.poll();
            nodes++;

            // rebuild the followed route for the selected town
            Town aux = currentTown;
            followedRoute = new ArrayList<Integer>();
            followedRoute.add(Integer.valueOf(aux.number));
            while (aux.level != 0) {
                aux = aux.parent;
                followedRoute.add(0, Integer.valueOf(aux.number));
            }
            
            if (currentTown.level == instance.getDimension()) {
                solution = true;
                optimumRoute = followedRoute;
                optimumCost = currentTown.g;
            } else {
                
                for (int i=1; i<=instance.getDimension(); i++) {
                    // have we visited this city in the current followed route?
                    boolean visited = followedRoute.contains(Integer.valueOf(i));
                    boolean isSolution = (followedRoute.size() == instance.getDimension())&&(sourceCity.equals(Integer.valueOf(i)));

                    if (!visited || isSolution) {
                        Town childTown = new Town(i, (int) (currentTown.g + distances.getDistanceBetween(currentTown.number, i)), 
                                getHeuristicValue(currentTown.number), currentTown.level + 1);
                        childTown.parent = currentTown;
                        opened.add(childTown);  
                    }
                }                
            }
        }
        long endTime = System.currentTimeMillis();
        
        result =  "A STAR SEARCH\n\n";     
        result += "Better solution: "+optimumRoute.toString() + "// Cost: "+optimumCost+"\n";
        result += "Visited Nodes: "+nodes+"\n";
        result += "Elapsed Time: "+(endTime-startTime)+" ms\n";
        
        return result;        
    }    

}
