package api;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Computer extends Remote {
				<T> T executeTask(Task <T> t) throws RemoteException;
}
