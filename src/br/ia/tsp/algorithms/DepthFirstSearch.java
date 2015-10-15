package br.ia.tsp.algorithms;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.moeaframework.problem.tsplib.DistanceTable;
import org.moeaframework.problem.tsplib.TSPInstance;

public class DepthFirstSearch {
	
	//RoutesMatrix distances;
    Integer sourceCity;
    String result = new String();
    
    ArrayList<Integer> initialRoute, optimumRoute;
    int nodes = 0;
    double routeCost = 0;
    double optimumCost = Integer.MAX_VALUE;

    private TSPInstance instance;
	private DistanceTable distances;
    
    
    /** Creates a new instance of DepthFirstSearch 
     * @throws IOException */
    public DepthFirstSearch(File instancePath, Integer sourceCity) throws IOException {
    	instance = new TSPInstance(instancePath);
        distances = instance.getDistanceTable();
        this.sourceCity = sourceCity;
    }
    
    /**
     * executes the algorithm
     */
    public String execute () {
        
        initialRoute = new ArrayList<Integer>();
        initialRoute.add(sourceCity);
        optimumRoute = new ArrayList<Integer>();
        nodes++;
        
        result =  "DEPTH FIRST SEARCH\n\n";
        
        long startTime = System.currentTimeMillis();
        search(sourceCity, initialRoute);
        long endTime = System.currentTimeMillis();
        
        result += "\nBetter solution: "+optimumRoute.toString() + "// Cost: "+optimumCost+"\n";
        result += "Visited Nodes: "+nodes+"\n";
        result += "Elapsed Time: "+(endTime-startTime)+" ms\n";
        
        return result;         
    }
    
    
    /**
     * @param from node where we start the search.
     * @param route followed route for arriving to node "from".
     */
    public void search (Integer from, ArrayList<Integer> followedRoute) {
        
        // we've found a new solution
        if (followedRoute.size() == instance.getDimension()) {
            
            followedRoute.add(sourceCity);
            nodes++;
            
            // update the route's cost
            routeCost += distances.getDistanceBetween(from.intValue(), sourceCity.intValue());
            
            if (routeCost < optimumCost) {
                optimumCost = routeCost;
                optimumRoute = (ArrayList<Integer>) followedRoute.clone();
            }
            
          //  result += followedRoute.toString() + "// Cost: "+ routeCost + "\n";
            
            
            // update the route's cost (back to the previous value)
            routeCost -= distances.getDistanceBetween(from.intValue(), sourceCity.intValue());
        }
        else {
            for (int to=1; to <= instance.getDimension(); to++){
                if (!followedRoute.contains(Integer.valueOf(to))) {
                    
                    ArrayList<Integer> increasedRoute = (ArrayList)followedRoute.clone();
                    increasedRoute.add(Integer.valueOf(to));
                    nodes++;
                    
                    // update the route's cost
                    routeCost += distances.getDistanceBetween(from.intValue(), to);
                    
                    search(Integer.valueOf(to), increasedRoute);
                    
                    // update the route's cost (back to the previous value)
                    routeCost -= distances.getDistanceBetween(from.intValue(), to);
                }
            }
        }
        
    }

}
