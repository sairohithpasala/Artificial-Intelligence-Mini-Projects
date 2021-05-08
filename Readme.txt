Name: Gopi Krishna
UTA ID: 1001677986

Programming language: Java 

Code Structure:
Task 1:
Class compute_a_posteriori
This class implements a system that (a)Can determine the posterior probability of different hypotheses, given priors for these hypotheses, and given a sequence of observations.
(b)Can determine the probability that the next observation will be of a specific type, priors for different hypotheses, and given a sequence of observations.

Task 2:
Class bnet
This class implements a program that computes and prints out the probability of any combination of events given any other combination of events,given a Bayesian network establishing relations 
between events on the burglary-earthquake-alarm domain together with complete specifications of all probability distributions.

Task 2 Functions: 
public static double callCompute(int bc,int ec,int ac,int jc,int mc,ArrayList<String> arrayProc)
This function calls computeProbability function to compute the probability by considering different combination of values for the missing elements in the numerator and denominitor.

public static double computeProbability(boolean b, boolean e, boolean a, boolean j, boolean m)
This function has arguments which are boolean,specifying if the corresponding event (burglary, earthquake, alarm, john-calls, mary-calls) is true or false.

Compilation Instructions:

Task1:
javac compute_a_posteriori.java
Task2:
javac bnet.java


Execution instruction:

Task 1:  
java compute_a_posteriori observations
Task 2 example:
java bnet Bt Af given Mf

Referred Links:

https://www.javatpoint.com/array-in-java
https://answers.unity.com/questions/272993/loop-through-array-until-certain-value-is-found.html
http://www.baeldung.com/java-write-to-file
https://stackoverflow.com/questions/4885254/string-format-to-format-double-in-java
https://beginnersbook.com/2013/12/java-arraylist-contains-method-example/
https://beginnersbook.com/2013/12/java-arraylist-addallcollection-c-method-example/