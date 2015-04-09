# Homework 1  
##### Implements a Java RMI server and client to calculate a Mandelbrot set and a traveling salesman shortest route.  
  
Ethan Preble and Greg Parsons  
CS290B Java-Centric Cluster Computing  
Spring 2015, UCSB  
  
  
To Build:  
  
> ant clean  
> ant dist  
  
To Run:  
> ant runComputer  (on server machine)  
> ant runEClient (or runMClient, run on client machine)  
  
To cause the client to connect to a server other than 'localhost', modify 'build.xml' in the lines that look like  
> arg value="localhost"
  
Put the IP address of the server in place of 'localhost'.
