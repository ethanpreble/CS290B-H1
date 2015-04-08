
package tasks;

import api.Task;
import java.io.Serializable;

public class TaskEuclideanTsp implements Task<java.util.List<Integer>>, Serializable
{

    private static final long serialVersionUID = 235L;

    private final double[][] _cities;

 



	public TaskEuclideanTsp(double[][] cities)
	{

		System.out.println("[TaskEuclideanTsp constructor()]");

		_cities = cities;

	}


	public java.util.List<Integer> execute()
	{
		System.out.println("[TaskEuclideanTsp.execute()]");

		java.util.List<Integer> result = null;
		return result;

	}

}
