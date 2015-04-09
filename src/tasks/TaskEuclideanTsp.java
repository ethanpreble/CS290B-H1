
package tasks;

import api.Task;
import java.io.Serializable;
import org.paukov.combinatorics.*;


public class TaskEuclideanTsp implements Task<java.util.List<Integer>>, Serializable
{

    private static final long serialVersionUID = 235L;

    private double[][] _cities;


	public TaskEuclideanTsp(double[][] cities)
	{
		System.out.println("[TaskEuclideanTsp constructor()]");
 		
		_cities = cities;







	}


	public java.util.List<Integer> execute()
	{
		System.out.println("[TaskEuclideanTsp.execute()]");







 		ICombinatoricsVector<double[]> initialVector = Factory.createVector(_cities);





 		// 3 ?????

		Generator<double[]> generator = Factory.createSimpleCombinationGenerator(initialVector, initialVector.getSize());







		printAllRoutes(generator);



		java.util.List<Integer> result = null;
		return result;

	}

	private void printAllRoutes(Generator<double[]> generator){

		System.out.println("Combinations: ");

		int count = 0;
		for(ICombinatoricsVector<double[]> combination:generator){

			System.out.println(count + ": hello");//combination[0] + " " + combination[1]);
			count++;
		}
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