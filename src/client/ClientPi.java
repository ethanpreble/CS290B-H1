package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.math.BigDecimal;
import api.Computer;

public class ClientPi 
{
    public static void main(String args[]) 
    {
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
            BigDecimal pi = comp.executeTask(task);
            System.out.println(pi);
        }
        catch (Exception e) 
        {
            System.err.println("ClientPi exception:");
            e.printStackTrace();
        }
    }    
}