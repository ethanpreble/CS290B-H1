# CS290B-H1
Homework1


  


Status  
  

From copying the tutorial I was able to get the sample Pi task working.

 > cd src
 window 1> rmiregistry
  
 window 2> java -Djava.security.policy=policy.txt computer.ComputerImpl  
  
 window 3> java -Djava.security.policy=policy.txt client.ClientPi localhost 10  
  
 (10 is the number of decimal places for Pi)  