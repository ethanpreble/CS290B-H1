/*
	new TaskMandelbrotSet( LOWER_LEFT_X, LOWER_LEFT_Y, EDGE_LENGTH, N_PIXELS, ITERATION_LIMIT) );


*/

package tasks;

import api.Task;
import java.io.Serializable;
import java.lang.Math;


public class TaskMandelbrotSet implements Task<Integer[][]>, Serializable
{
    private static final long serialVersionUID = 229L;

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
	
	private double distance(double a, double b)
	{
		return Math.sqrt(Math.pow(a,2)+Math.pow(a,2));
	}
	
	private class Complex
	{
		public double _real;
		public double _imaginary;
		
		public Complex(double real, double imaginary)
		{
			_real=real;
			_imaginary=imaginary;
		}
	}


	public Integer[][] execute()
	{
		System.out.println("[TaskMandelbrotSet.execute()]");

		Integer[][] result = new Integer[_n_pixels][_n_pixels];
		for(int i=0; i<_n_pixels; i++){
			for(int j=0; j<_n_pixels; j++){
				//z0 = c; ie zk=c when k=0
				double offset_x = i*(_edge_length/_n_pixels);
				double offset_y = j*(_edge_length/_n_pixels);
				Complex zk = new Complex(_lower_left_x + offset_x, _lower_left_y + offset_y);
				int iterations=0;
				while((iterations < _iteration_limit) && (distance(zk._real,zk._imaginary) <2))
				{
					//find new zk
				}
				result[i][j] = iterations;
			}
		}
		
		return result;
		
		

	}

	/*
	This task helps produce a visualization of the some part of the Mandelbrot set.
	The constructor takes the following inputs:
	2 doubles that represent the lower left corner of a square in the complex plane.
	a double that represents the edge length of a square in the complex plane, whose sides are parallel to the axes.
	an int , n, such that the square region of the complex plane is subdivided into n X n squares, each of which is visualized by 1 pixel.
	an int which is the iteration limit: It defines when the representative point of a region is considered to be in the Mandelbrot set.
	The execute method returns an Integer[n][n] count array, where count[i][j] = k, where |zk| > 2 or k is the iteration limit (whichever is smaller), where
	z0 = c, where c is the representative point in square[i][j] (typically the lower left point defining the square)
	zk = (zk-1)2 + c.
	Your client maps each element of the count array (i.e., elements in the set {0, 1, 2, ..., iterationLimit }) to a Color object, and displays the n X n array of colors, 1 pixel per Color.
*/
	
}
