package tasks;

import api.Task;
import java.io.Serializable;
import java.lang.Math;

/**
* This is a class that implements the Task.java interface so that it may be run by classes that implement the Computer interface.  
* The TaskMandelBrotSet takes in parameters that specify a square in the complex bumber plane.
* It will return a 2x2 matrix representing the iteration limits for the mandelbrot set in order to aid with visualization.  For more information see execute()
* @author  EthanPreble & GregParsons
* @see http://www.cs.ucsb.edu/~cappello/290B/homework/1/
*/
public class TaskMandelbrotSet implements Task<Integer[][]>, Serializable
{
    private static final long serialVersionUID = 229L;

    private final double _lower_left_x;
    private final double _lower_left_y;
    private final double _edge_length;
    private final int _n_pixels;
    private final int _iteration_limit;
	
	/**
	* Constructor
	* @param  double for lower left x square coordinate for a square in the complex plane x represents real number axis
	* @param  double for lower left y square coordinate for a square in the complex plane y represents imaginary number axis
	* @param  double for edge length of the square.
	* @param  int for the number of subsquares where the square region will be divided into nXn squares where n is the parameter.
	* @param int for the iteration limit, ie the numebr of times that the iterative calculation will be performed
	*/
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
		
		public void square()
		{
			double prevReal = _real;
			double prevImaginary = _imaginary;
			_real = Math.pow(prevReal,2)-Math.pow(prevImaginary,2);
			_imaginary = 2*prevReal*prevImaginary;
		}
		
		public void add(Complex a)
		{
			_real = _real+a._real;
			_imaginary = _imaginary+a._imaginary;
		}
	}

	/**
	* Execute the constructed Mandelbrot task.
	* The execute method returns an Integer[n][n] count array, where count[i][j] = k, where |zk| > 2 or k is the iteration limit (whichever is smaller), where
	* z0 = c, where c is the representative point in square[i][j] (typically the lower left point defining the square)
	* zk = (zk-1)2 + c.
	*/
	public Integer[][] execute()
	{
		System.out.println("[TaskMandelbrotSet.execute()]");
		System.out.println("llx:"+_lower_left_x);
		System.out.println("lly:"+_lower_left_y);
		System.out.println("edge:"+_edge_length);
		System.out.println("n_pixels:"+_n_pixels);
		System.out.println("iteration:"+_iteration_limit);

		Integer[][] result = new Integer[_n_pixels][_n_pixels];
		for(int i=0; i<_n_pixels; i++){
			for(int j=0; j<_n_pixels; j++){
				
				//z0 = c; ie zk=c when k=0
				double offset_x = i*(_edge_length/_n_pixels);
				double offset_y = j*(_edge_length/_n_pixels);
				Complex c = new Complex(_lower_left_x + offset_x, _lower_left_y + offset_y);
				Complex zk = new Complex(_lower_left_x + offset_x, _lower_left_y + offset_y); //didn't want to deal with deep copy stuff but this is same a 'c'
								
				int iterations=0;
				while(	(iterations <= _iteration_limit) && (distance(zk._real,zk._imaginary) <2)	)
				{
					//find new zk
					zk.square();
					zk.add(c);
					iterations++;
				}
				result[i][j] = iterations;
			}
		}
		System.out.println("[TaskMandelbrotSet.finished()]");
		
		return result;
	}

	
	
}
