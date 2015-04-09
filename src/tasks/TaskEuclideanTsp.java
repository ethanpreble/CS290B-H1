
package tasks;

import api.Task;
import java.io.Serializable;
import org.paukov.combinatorics.*;


public class TaskEuclideanTsp implements Task<java.util.List<Double[]>>, Serializable
{

    private static final long serialVersionUID = 235L;

    //private Double[][] _cities;
    private java.util.List<Double[]> _cities;


	public TaskEuclideanTsp(Double[][] cities)
	{
 		
		_cities = java.util.Arrays.asList(cities);

		System.out.println("[TaskEuclideanTsp constructor()] " + _cities.size() + " cities");

	}


	public java.util.List<Double[]> execute()
	{
		System.out.println("[TaskEuclideanTsp.execute()] " + _cities.size() + " cities");
 		ICombinatoricsVector<Double[]> initialVector = Factory.createVector(_cities);
		Generator<Double[]> generator = Factory.createPermutationGenerator(initialVector);
		
		// Find shortest
		ICombinatoricsVector<Double[]> shortest_permutation = findShortestRoute(generator);
		java.util.List<Double[]> result = shortest_permutation.getVector();
		return result;

	}





	private ICombinatoricsVector<Double[]> findShortestRoute(Generator<Double[]> generator_all_routes)
	{
		ICombinatoricsVector<Double[]> shortest_permutation = null;
		Double shortest_len = null;

		for(ICombinatoricsVector<Double[]> route_permutation:generator_all_routes){

			// System.out.println(count + ": hello");//combination[0] + " " + combination[1]);
			double len = getRouteLength(route_permutation);

			// Set a new shortest length if this is the first or if it's shorter than the previous record.			
			if((shortest_len == null) || (len < shortest_len) ){
				shortest_len = len;
				shortest_permutation = route_permutation;
				System.out.println("[TaskEuclideanTsp.findShortestRoute] New shortest: " + shortest_len);
			}
		}

		System.out.println("[TaskEuclideanTsp.findShortestRoute] Shortest len: " + shortest_len);

		return shortest_permutation;
	}




	private double getRouteLength(ICombinatoricsVector<Double[]> route){

		double total_dist = 0.0;
		double edge_dist = 0.0;

		java.util.Iterator routeItr = route.iterator();

		// Save the first city to link with the last.
		Double[] first_city_coord = (Double[])routeItr.next();

		// Iterate through city pairs
		Double[] current_city_coord = first_city_coord;
		while(routeItr.hasNext()){

				Double[] next_city_coord = (Double[])routeItr.next();
				edge_dist = getDistanceBetween(current_city_coord[0], current_city_coord[1], next_city_coord[0], next_city_coord[1]);
				total_dist += edge_dist;

				// set current to next before next round
				current_city_coord = next_city_coord;

		}

		//Get the distance between the last city and the first to close the loop.
		edge_dist = getDistanceBetween(current_city_coord[0], current_city_coord[1], first_city_coord[0], first_city_coord[1]);
		total_dist += edge_dist;

		// System.out.println("[TaskEuclideanTsp.getRouteLength] route nodes: " + route.getSize() + ", route length: " + total_dist);

		return total_dist;
	}


	private double getDistanceBetween(double ax, double ay, double bx, double by){

		return java.lang.Math.sqrt( java.lang.Math.pow((ax - bx),2.0) + java.lang.Math.pow((ay - by), 2.0));

	}


}


/*




For the EuclideanTspTask, use the following list of cities as a problem instance: Each line that follows has the x and y coordinates of a city, starting with city 0 and ending with city 9:

6 3
2 2
5 8
1 5
1 6
2 7
2 8
6 5
1 3
6 6



 */