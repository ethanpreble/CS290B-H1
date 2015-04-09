package api;

/**
* The Task interface is intended to allow support for the execution arbitrary of tasks while returning a generic object.  
* Computing programs can allow for support of running the execute function of classes that implement the Task interface
* @author  EthanPreble & GregParsons
* @see https://docs.oracle.com/javase/tutorial/rmi/designing.html
*/

public interface Task<T>
{
	T execute();
}
