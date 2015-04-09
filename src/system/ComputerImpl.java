
package computer;			// computer = "engine" in tutorial https://docs.oracle.com/javase/tutorial/rmi/implementing.html

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
//import compute.Compute;		// api = "compute"; Computer = "Compute" in tutorial
import api.Computer;
//import compute.Task;
import api.Task;


/**
 * ComputeImpl Class
 */
public class ComputerImpl implements Computer {

	/**
	 * ComputeImpl Constructor
	 */
    public ComputerImpl() {
        super();
    }


    /**
     * executeTask
     * @param  Task<T> t
     * @return   t.execute()
     */
    public <T> T execute(Task<T> t) {
        
        System.out.println("[ComputerImpl:execute()]");

        return t.execute();
    }


    /**
     * main()
     * @param args
     */
    public static void main(String[] args) 
    {

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            Computer computeImpl = new ComputerImpl();
            Computer stub = (Computer) UnicastRemoteObject.exportObject(computeImpl, 0);
            //Registry registry = LocateRegistry.getRegistry();
            Registry registry =  LocateRegistry.createRegistry(1099);
			registry.rebind(Computer.SERVICE_NAME, stub);
            System.out.println("ComputeImpl bound");
        } catch (Exception e) {
            System.err.println("ComputeImpl exception:");
            e.printStackTrace();
        }
    }
}