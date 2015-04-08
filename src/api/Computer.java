package api;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Computer extends Remote {

	public static String SERVICE_NAME = "ComputeService";
	public static String PORT = "1099";


	<T> T execute(Task <T> t) throws RemoteException;
}
