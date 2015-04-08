package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import api.Computer;

public class ClientPi extends Client<BigDecimal>
{

    public ClientPi(String hostname , int digits) throws RemoteException, NotBoundException, MalformedURLException
    {

        super("Pi Calculator", hostname, new tasks.Pi(digits));


    }



    /**
     * main()
     * @param [hostname] [pi decimal places]
     */
    public static void main(String args[]) 
    {
        System.setSecurityManager(new SecurityManager());

        try{
/*
            String name = "Computer";
            Registry registry = LocateRegistry.getRegistry(args[0]);
            Computer comp = (Computer) registry.lookup(name);

*/

            final ClientPi client = new ClientPi(args[0], Integer.parseInt(args[1]));
            client.begin(); //start timer

            BigDecimal result_value = client.runTask();
            System.out.println(result_value);

            client.end();

        }
        catch(Exception e){
            System.err.println("ClientPi exception:");
            e.printStackTrace();


        }


        /*


        if (System.getSecurityManager() == null) 
        {
            System.setSecurityManager(new SecurityManager());
        }

        try 
        {
            String name = "Computer";
            Registry registry = LocateRegistry.getRegistry(args[0]);
            Computer comp = (Computer) registry.lookup(name);
            tasks.Pi task = new tasks.Pi(Integer.parseInt(args[1]));
            BigDecimal pi = comp.execute(task);
            System.out.println(pi);
        }
        catch (Exception e) 
        {
            System.err.println("ClientPi exception:");
            e.printStackTrace();
        }
        */
    }    
}