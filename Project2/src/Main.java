import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Main {

    public static void main(String[] args) {
        int V = 0; //initial number of nodes
        try{
            File file = new File("input2.txt"); //get the file
            Scanner NumberOfV = new Scanner(file);                                                        //scanner to count how many edges
            Scanner edges = new Scanner(file);                                                            //scanner to use add edges

            while(NumberOfV.hasNextLine()){                                                              //get the number of edges
                V ++;
                NumberOfV.nextLine();
            }
            NumberOfV.close();                                                                          //close scanner
            //System.out.println("Number of nodes: " + V);                                              //testing out number of nodes
            ArrayList<Integer> adj[] = new ArrayList[V];
            for(int i = 0; i < V; i++)
                adj[i] = new ArrayList<Integer>();
            for (int i = 0; i < V; i++){
                for (int j = 0; j < V; j++){
                    if (edges.nextInt() == 1){ //if next int is 1, connect the edges
                        adj[i].add(j);
                    }

                }
            }

            if (ReportCycle(adj, V))
                System.out.println("Is there cycle? Yes");
            else
                System.out.println("Is there cycle? No");

            System.out.println("Number of layers: " + printHeight(adj, V));
            edges.close();                                                                              //close scanner

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    static int printHeight(ArrayList<Integer> adj[], int V){//https://www.geeksforgeeks.org/level-node-tree-source-node-using-bfs/ source helped me out make height detection
        int noRepeat []  = new int [V];
        boolean visited[] = new boolean[V];
        boolean mark[] = new boolean[V];

        Arrays.fill(noRepeat, -1);
        Arrays.fill(visited, false);
        int height[] = new int[V];
        height[0] = 0;                      //initialize height as zero since root height is zero
        mark[0] = true;                     //mark node 0
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < V; i++){
            if (!visited[i]){
                visited[i] = true;          //make initial node is visited
                q.add(i);

                while(!q.isEmpty()){
                    int u = q.poll();
                    //traverse neighbors of node u
                    for(int j = 0; j < adj[i].size(); j++){
                        //v is neighbor of node u
                        int v = adj[i].get(j);

                        //
                        if(!mark[v] &&(noRepeat[u] != v)){
                            //enqueue v in queue
                            q.add(v);
                            //to make sure that it wont add extra height. For example, when reading node 1 and finds neighbor node 0, the height shouldn't be added.
                            noRepeat[v] = u;
                            //height[v] is level of u+1
                            height[v] = height[u]+1;

                            //mark v
                            mark[v] = true;


                        }

                    }//for j loop
                }//while loop
            }//for i loop
        }
        //return the last layer
    return height[V-1];
    }

    //https://www.geeksforgeeks.org/detect-cycle-in-an-undirected-graph-using-bfs/ helped the cycle detection and kinda modified it little bit
    static boolean CheckCycle(ArrayList<Integer> adj[], int s,
                              int V, boolean visited[])
    {

        // Set parent vertex for every vertex as -1.
        int parent[] = new int[V];
        Arrays.fill(parent, -1);

        // Create a queue for BFS
        Queue<Integer> q = new LinkedList<>();

        // Mark the current node as
        // visited and enqueue it
        visited[s] = true;
        q.add(s);

        while (!q.isEmpty())
        {

            /* Dequeue*/
            int u = q.poll();



            for (int i=0; i<adj[u].size();i++)
            {
                //get next node that's connected to adj[u]
                int v = adj[u].get(i);
                //check visited node
                if (!visited[v])
                {
                    visited[v] = true;
                    q.add(v);                   //add queue of next node
                    parent[v] = u;              //put adj[u] into parent so that it's not considered as cycle such as in file. For example, if node 0 is neighbor with 1, then node 1 is also neighbor with 0 but that doesnt mean it's a cycle.
                }
                else if (parent[u] != v)        //returns that there's a cycle
                    return true;
            }
        }
        return false;
    }


    static boolean ReportCycle(ArrayList<Integer> adj[], int V)
    {

        // Mark all the vertices as not visited
        boolean visited[] = new boolean[V];
        Arrays.fill(visited,false);

        for (int i = 0; i < V; i++)
            //return true when cycle is detected
            if (!visited[i] && CheckCycle(adj, i, V, visited))
                return true;
        return false;
    }
}
