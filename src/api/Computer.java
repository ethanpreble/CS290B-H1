package api;

/**
* The Computer interface allows to set up an RMI service that is able to accept class objects that implement the task interface and returns the generic type given.  
* Classes that implement this interface are expected to be able to accept tasks somehow (ie, via setting up a server or by other means) and then passing these tasks into the execute function.
* @author  EthanPreble & GregParsons
* @see https://docs.oracle.com/javase/tutorial/rmi/designing.html
*/

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Computer extends Remote {

	public static String SERVICE_NAME = "Computer";
	public static String PORT = "1099";

	/**
	* A function for performing the task that has been passed into the Computer
	* @param  A class object that implements the Task interface and returns a generic type specified by the Task class.
	* @throws RemoteException
	*/
	<T> T execute(Task <T> t) throws RemoteException;
}
