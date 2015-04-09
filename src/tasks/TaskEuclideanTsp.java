package tasks;

import api.Task;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import org.paukov.combinatorics.*;

class City implements Serializable
{
	public Integer index;			// 
	public Double[] coords;	// coordinate

	public City(Integer idx, Double[] coordinates){
		index = idx;
		coords = coordinates;
	}

}

/**
 * Task class to ascertain the shortest route between cities passed 
 * as Euclidean coordinates to the constructor.
 */
public class TaskEuclideanTsp implements Task<List<Integer>>, Serializable
{

    private static final long serialVersionUID = 235L;

    //private Double[][] _cities;
    private List<Double[]> _cities;
    private ArrayList<City> _city_list = new ArrayList<City>();


    /**
     * Constructor. Takes a double array of doubles. The first dimension refers to a list of cities. 
     * The second dimension holds the x and y coordinates of the city.
     * @param  cities double[][]
     */
	public TaskEuclideanTsp(Double[][] cities)
	{
		_cities = java.util.Arrays.asList(cities);

		java.util.Iterator city_iter = _cities.iterator();
		for(int i=0; i<_cities.size(); i++){
			if(city_iter.hasNext()){
				City newCity = new City(i, (Double[]) city_iter.next());
				_city_list.add(newCity);
			}
		}

		System.out.println("[TaskEuclideanTsp constructor()] " + _city_list.size() + " in city list");
	}



	/**
	 * Executes this task. Fulfills the task interface. Server will call this function and execute server-side.
	 * @return List of Integers of the indices of the cities after they're sorted for the shortest route.
	 */
	public List<Integer> execute()
	{

		printCities();

		System.out.println("[TaskEuclideanTsp.execute()] " + _cities.size() + " cities");
 		ICombinatoricsVector<City> initialVector = Factory.createVector(_city_list);
		Generator<City> generator = Factory.createPermutationGenerator(initialVector);
		
		// Find shortest
		ICombinatoricsVector<City> shortest_permutation = findShortestRoute(generator);
		
		List<Integer> route_list = new ArrayList<Integer>();

		for(City city : shortest_permutation){
			route_list.add(city.index);
		}

		// See the shortest TSP
		//for(Integer city:route_list)
		//	System.out.println(city);
		
		return route_list;
	}





	private ICombinatoricsVector<City> findShortestRoute(Generator<City> generator_all_routes)
	{
		ICombinatoricsVector<City> shortest_permutation = null;
		Double shortest_len = null;

		for(ICombinatoricsVector<City> route_permutation:generator_all_routes){
			//System.out.println("hello");//combination[0] + " " + combination[1]);
			double len = getRouteLength(route_permutation);

			// Set a new shortest length if this is the first or if it's shorter than the previous record.			
			if((shortest_len == null) || (len < shortest_len) ){
				shortest_len = len;
				shortest_permutation = route_permutation;
			//	System.out.println("[TaskEuclideanTsp.findShortestRoute] New shortest: " + shortest_len);
			}
		}
		System.out.println("[TaskEuclideanTsp.findShortestRoute] Shortest len: " + shortest_len);

		return shortest_permutation;
	}




	private double getRouteLength(ICombinatoricsVector<City> route){

		double total_dist = 0.0;
		double edge_dist = 0.0;

		java.util.Iterator routeItr = route.iterator();

		// Save the first city to link with the last.
		//City first_city = ;
		Double[] first_city_coord = ((City)routeItr.next()).coords;

		// Iterate through city pairs
		Double[] current_city_coord = first_city_coord;
		while(routeItr.hasNext()){

			Double[] next_city_coord = ((City)routeItr.next()).coords;
			edge_dist = getDistanceBetween(current_city_coord[0], current_city_coord[1], next_city_coord[0], next_city_coord[1]);
			total_dist += edge_dist;

			// set current to next before next round
			current_city_coord = next_city_coord;

		}

		//Get the distance between the last city and the first to close the loop.
		edge_dist = getDistanceBetween(current_city_coord[0], current_city_coord[1], first_city_coord[0], first_city_coord[1]);
		total_dist += edge_dist;
		//System.out.println("[TaskEuclideanTsp.getRouteLength] route nodes: " + route.getSize() + ", route length: " + total_dist);

		return total_dist;
	}


	private double getDistanceBetween(double ax, double ay, double bx, double by){
		return java.lang.Math.sqrt( java.lang.Math.pow((ax - bx),2.0) + java.lang.Math.pow((ay - by), 2.0));
	}


	void printCities(){
		System.out.println("Printing " + _city_list.size() + " cities:");

		for(City city:_city_list){
			System.out.println(city.index + " (" + city.coords[0] + ", " + city.coords[1] + ")"); 

		}
	}
}

