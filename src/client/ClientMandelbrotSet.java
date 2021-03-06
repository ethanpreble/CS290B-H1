package client;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import tasks.TaskMandelbrotSet;

/**
 *
 * @author Peter Cappello
 */
public class ClientMandelbrotSet extends Client<Integer[][]>
{

    private static final long serialVersionUID = 233L;


    private static final double LOWER_LEFT_X = -2.0;
    private static final double LOWER_LEFT_Y = -2.0;
    private static final double EDGE_LENGTH = 4.0;
    private static final int N_PIXELS = 512;
    private static final int ITERATION_LIMIT = 64;
    




    public ClientMandelbrotSet(String hostname) throws RemoteException, NotBoundException, MalformedURLException 
    { 
        super( "Mandelbrot Set Visualizer", hostname, //"localhost",
               new TaskMandelbrotSet( LOWER_LEFT_X, LOWER_LEFT_Y, EDGE_LENGTH, N_PIXELS, ITERATION_LIMIT) ); 
    }
    
    /**
     * Run the MandelbrotSet visualizer client.
     * @param args unused 
     * @throws java.rmi.RemoteException 
     */
    public static void main( String[] args ) throws Exception
    {  
        
        //First argument should be hostname
        if(args.length == 0)
        {
            System.out.println("Usage: [hostname]");
            return;

        }

        System.setSecurityManager( new SecurityManager() );

        final ClientMandelbrotSet client = new ClientMandelbrotSet(args[0]);
        client.begin();


        Integer[][] value = client.runTask();

        if(value != null)
            client.add( client.getLabel( value ) );


        client.end();
    }
    
    public JLabel getLabel( Integer[][] counts )
    {
        final Image image = new BufferedImage( N_PIXELS, N_PIXELS, BufferedImage.TYPE_INT_ARGB );
        final Graphics graphics = image.getGraphics();
        for ( int i = 0; i < counts.length; i++ )
            for ( int j = 0; j < counts.length; j++ )
            {
                graphics.setColor( getColor( counts[i][j] ) );
                graphics.fillRect( i, N_PIXELS - j, 1, 1 );
            }
        final ImageIcon imageIcon = new ImageIcon( image );
        return new JLabel( imageIcon );
    }
    
    private Color getColor( int iterationCount )
    {
		if(iterationCount == ITERATION_LIMIT) return Color.BLACK;
        else if(iterationCount > 3*ITERATION_LIMIT/4) return Color.GRAY;
		else if(iterationCount > ITERATION_LIMIT/2) return Color.LIGHT_GRAY;
		else return Color.WHITE;
		
		//return iterationCount == ITERATION_LIMIT ? Color.BLACK : Color.WHITE;
    }
}