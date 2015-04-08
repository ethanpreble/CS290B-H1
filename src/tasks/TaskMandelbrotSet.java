/*
	new TaskMandelbrotSet( LOWER_LEFT_X, LOWER_LEFT_Y, EDGE_LENGTH, N_PIXELS, ITERATION_LIMIT) );


*/

package tasks;

import api.Task;
import java.io.Serializable;

public class TaskMandelbrotSet implements Task, Serializable
{


    private final double _lower_left_x;
    private final double _lower_left_y;
    private final double _edge_length;
    private final int _n_pixels;
    private final int _iteration_limit;


 



	public TaskMandelbrotSet(double lower_left_x, double lower_left_y, double edge_length, int n_pixels, int iteration_limit)
	{

		System.out.println("[TaskMandelbrotSet constructor()]");

		_lower_left_x = lower_left_x;
		_lower_left_y = lower_left_y;
		_edge_length = edge_length;
		_n_pixels = n_pixels;
		_iteration_limit = iteration_limit;

	}


	public Task execute()
	{
		System.out.println("[TaskMandelbrotSet.execute()]");

		Task anewTask = null;
		return anewTask;

	}

}
