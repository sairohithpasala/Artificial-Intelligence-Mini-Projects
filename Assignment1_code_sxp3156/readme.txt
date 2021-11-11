Name: Sai Rohith Pasala
UTA ID: 1001873156

Programming language: Java 

Structure of the code:

 
1)The input arguments are declared and the input text file is read & stored in an array list.
2) A seperate class nodeProperties is created to store the properties of a node that is pathCost, depth and the presentState.
3) Initialize these properties and add the nodes to the fringeArray using an array list.
4) The first element from the fringeArray is removed and added to the currentNode and also to the goalNode.
5) The array is now checked and all the successor nodes are added and also the cost is calculated.
6) The loop created checks all the nodes to find the destination_city.
7) Once the goal state is reached it re-traces back in the same order and the final cost is displayed.
8) The fringe list is then sorted to obtain an optimal solution.

Compilation Instructions:

javac find_route.java

Run the code in the following way if it's on cmd prompt/omega :

(1) Uninformed Search

java find_route input1.txt origin_city destination_city	

E.g: java find_route input1.txt Brumen Kassel 

(2) Informed Search with heuristic file as input

java find_route input1.txt origin_city destination_city h_kassel.txt		

E.g: java find_route input1.txt Brumen Kassel h_kassel.txt

Links used for reference: 

https://www.geeksforgeeks.org/arraylist-in-java/
https://dzone.com/articles/sorting-java-arraylist



