import java.io.BufferedReader; 
import java.io.File;    
import java.io.FileReader;  
import java.io.IOException; 
import java.util.*;


class Node  // creating node
{
    int cost;  
    int hCost;  
    String cct_city;    
    Node prt_city;    
    
    Node(String cct_city, Node prt_city, int cost)   
    {
        this.cost = cost;
        this.prt_city = prt_city;
        this.cct_city = cct_city;
    }    /// stores node properties
    
    Node(String cct_city, Node prt_city, int cost, int hCost)    
    {
        this.cost = cost;
        this.prt_city = prt_city;
        this.cct_city = cct_city;
        this.hCost = hCost;
    }
}


class checkComparison implements Comparator<Node>
{
    public int compare(Node n1,Node n2)
    {
        if (n1.cost>n2.cost) return 1;
        else if (n1.cost<n2.cost) return -1;
        else return 0;
    }
}

class checkHComparison implements Comparator<Node>
{

    @Override
    public int compare(Node n1, Node n2) {
        if (n1.hCost>n2.hCost) return 1;
        else  if (n1.hCost<n2.hCost) return -1;
        return 0;
    }
}

public class find_route 
{
    
    Hashtable<String, ArrayList<String[]>> city_details = new Hashtable<String, ArrayList<String[]>>();
    Hashtable<String, Integer> heu_cityDetails = new Hashtable<String,Integer>();
    Hashtable<String, Object[]> route = new Hashtable<String, Object[]>();
    int nodes=0;

    private void trackMap(String origin_city, String destination, String distance)
    {
        String[] entry = {destination, distance};
        if (city_details.containsKey(origin_city))
            city_details.get(origin_city).add(entry);
        else 
        {
            ArrayList<String[]> temp = new ArrayList<String[]>();
            temp.add(entry);
            city_details.put(origin_city,temp);
        }
    }

    

    private void split_HFile(String h_file) throws IOException  //splitting heuristic input file
    {
        File file = new File(h_file);
        Scanner sc = new Scanner(new FileReader(file.getPath()));
        String line;
        while (!(line = sc.nextLine()).equals("END OF INPUT"))
        {
            heu_cityDetails.put(line.split(" ")[0].toString(),Integer.parseInt(line.split(" ")[1].toString()));
        }
    }
    
    private void split_File(String inputFile) throws IOException      //splitting input file
    {
        File file = new File(inputFile);    
        BufferedReader br = new BufferedReader(new FileReader(file.getPath()));
        String line;
        while (!(line=br.readLine()).equals("END OF INPUT"))    
        {
            String origin_city = line.split(" ")[0];
            String destination = line.split(" ")[1];
            String distance = line.split(" ")[2];
            trackMap(origin_city, destination,distance);     
            trackMap(destination, origin_city, distance);    
        }
    }

    
    
    
    public void routeTrace(String dest)
    {
        String total_Dist = "infinity";
        Stack<String> Route = new Stack<String>();
        if (route.containsKey(dest))
        {
            total_Dist = route.get(dest)[1]+".0 km";
            String prt_city = (String) route.get(dest)[0];
            while (prt_city!=null){
                float distance = (Integer)route.get(dest)[1] - (Integer)route.get(prt_city)[1];
                Route.push(prt_city+" to "+dest+ ", "+distance+" km");
                dest=prt_city;
                prt_city = (String)route.get(dest)[0];
            }
        }
        StringBuffer sb = new StringBuffer();
        //sb.append("nodes expanded: "+nodes+"\n");
        sb.append("distance: "+total_Dist+"\n");
        sb.append("route:\n");
        

        if (Route.isEmpty()) sb.append("none");
        else {
            while (!Route.isEmpty()){
                sb.append(Route.pop());
                sb.append("\n");
            }
        }
        System.out.println(sb.toString());
    }

    public void pathFollowed(Node node)
    
    {
    	
        if (!route.containsKey(node.cct_city) || (Integer) route.get(node.cct_city)[1]>node.cost) //containsKey() method used of Hashtable
        {
            Object[] valueRoute = {node.prt_city!=null ? node.prt_city.cct_city : null, node.cost};
            
            route.put(node.cct_city,valueRoute);
        }
    }
    
    public void findRoute(String inputFile, String src, String dest) throws IOException   //uninformed search
    {
        int nodeGenerated = 0;
        int maxvalue = 0;
        split_File(inputFile);
        HashSet<String> node_Visited = new HashSet<String>();
        PriorityQueue<Node> fringe = new PriorityQueue<Node>(1,new checkComparison());    //PriorityQueue Initialized
        fringe.add(new Node(src,null,0));
        while (!fringe.isEmpty())
        {   
            Node current_Node = fringe.poll();
            nodes++;
            pathFollowed(current_Node);
            if (current_Node.cct_city.equals(dest)) 
            {
                break;
            }
            if(node_Visited.contains(current_Node.cct_city))
            {
                continue;
            }
            ArrayList<String[]> childInfo = city_details.get(current_Node.cct_city);
            Iterator<String[]> iteration = childInfo.iterator();								//uninformed iteration
            while (iteration.hasNext())
            {
                String[] s = iteration.next();
                Node n = new Node(s[0],current_Node,current_Node.cost+Integer.parseInt(s[1]));
                fringe.add(n);
                nodeGenerated++;
              
            }
            if(maxvalue < fringe.size())
            {
            	maxvalue = fringe.size();
            
           }
            
            node_Visited.add(current_Node.cct_city);
        }
        //System.out.println("Maximum nodes in memory:"+maxvalue+"\n");
        System.out.println("nodes expanded: "+nodes);
        System.out.println("nodes generated:"+(nodeGenerated+1));
        
        routeTrace(dest);    //prints the elements of the route
    }

   
    private void findRoute(String inputFile, String src, String dest, String h_file) throws IOException   //Informed Search
    {   
    	int nodesGenerated = 0;
        int maxvalue = 0;
        split_HFile(h_file);
        split_File(inputFile);
        HashSet<String> node_Visited = new HashSet<String>();
        PriorityQueue<Node> fringe = new PriorityQueue<Node>(1,new checkHComparison());
        fringe.add(new Node(src,null,0,0));
        while (!fringe.isEmpty())
        {   
            Node current_Node = fringe.poll();
            nodes++;
            pathFollowed(current_Node);
            if (current_Node.cct_city.equals(dest)) 
                break;
            if(node_Visited.contains(current_Node.cct_city))
                continue;
            ArrayList<String[]> childInfo = city_details.get(current_Node.cct_city);   //ArrayList initialized
            Iterator<String[]> HIteration = childInfo.iterator();							//Heuristic Iteration
            while (HIteration.hasNext())
            {
                String[] str = HIteration.next();
                Node node = new Node(str[0],current_Node, current_Node.cost+Integer.parseInt(str[1]),current_Node.cost+Integer.parseInt(str[1])+heu_cityDetails.get(str[0]));
                fringe.add(node);
                nodesGenerated++;
            
            }
            if(maxvalue < fringe.size())
            {
            	maxvalue = fringe.size();
            
           }
            node_Visited.add(current_Node.cct_city);
        }
        //System.out.println("Maximum nodes in memory:"+maxvalue+"\n");
        System.out.println("nodes expanded: "+nodes);
        System.out.println("nodes generated:"+(nodesGenerated+1));
        routeTrace(dest);
    }

    public static void main(String[] args) throws IOException								    //main function

    {
        find_route findRoute = new find_route();
        // finds the shortest path without using heu_cityDetails file.
        if (args.length==3 && (args[0]!=null || args[1]!=null || args[2]!=null))
            findRoute.findRoute(args[0],args[1],args[2]);
        // finds the shortest path using heu_cityDetails file.
        else if (args.length==4 && (args[0]!=null || args[1]!=null || args[2]!=null || args[3]!=null))
            findRoute.findRoute(args[0],args[1],args[2],args[3]);
        else
            System.out.println("Please enter valid number of arguments!");
    }
}
