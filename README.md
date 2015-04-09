# CS290B-H1
Homework1


  


Status:  

[gp] By copying the tutorial I was able to get the sample Pi task working. Next we need to fill in EuclideanTspTask.java and MandelbrotSetTask.java I guess.
 Also got the outline for the other two tasks running. Need logic for inside them now.  

 > cd src  
 window 1> rmiregistry  
 window 2> java -Djava.security.policy=policy.txt computer.ComputerImpl  
 window 3> java -Djava.security.policy=policy.txt client.ClientMandelbrotSet localhost 
 (10 is the number of decimal places for Pi)  


    javac -classpath ".:lib/combinatoricslib-2.1.jar" client/ClientEuclideanTsp.java  

       
client:  
    javac -classpath ".:lib/combinatoricslib-2.1.jar" client/ClientEuclideanTsp.java -Xlint:unchecked  
    java -Djava.security.policy=policy.txt -classpath ".:lib/combinatoricslib-2.1.jar" client/ClientEuclideanTsp localhost  


server:  
    java -Djava.security.policy=policy.txt -cp ".:lib/combinatoricslib-2.1.jar" computer.ComputerImpl