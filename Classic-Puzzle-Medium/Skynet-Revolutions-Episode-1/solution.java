import java.util.*;
import java.io.*;
import java.math.*;


class Node{
    int id;
    boolean gateway;
    List<Edge> edges;
    
    public Node (int i){
        this.id = i;
        this.gateway = false;
        this.edges = new ArrayList<Edge>();
    }
    
    public void addEdge(Edge e){
        this.edges.add(e);
    }
}

class Edge{
    Node start;
    Node end;
    
    public Edge(Node start, Node end){
        this.start = start;
        this.end = end;
    }
}

class Graph{
    Node[] nodes;
    
    public Graph(int n){
        this.nodes = new Node[n];
        for (int i = 0; i < n; i++)
            this.nodes[i] = new Node(i);
    }
}




class Player {
    private static int N; // the total number of nodes in the level, including the gateways
    private static int L; // the number of links
    private static int E; // the number of exit gateways
    private static Graph graph;
    private static List<Node> gateways;
    
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        init(in);
        
        for (int i = 0; i < N; i++){
            Node n = graph.nodes[i];
            System.err.println("Node: "+i);
            System.err.println("Edges: "+n.edges.size());
            for (Edge e : n.edges)
                System.err.println(e.start.id + " -> "+e.end.id);
            System.err.println();
        }

        // game loop
        while (true) {
            int SI = in.nextInt(); // The index of the node on which the Skynet agent is positioned this turn
            String out = findEdge(SI);
            // Example: 0 1 are the indices of the nodes you wish to sever the link between
            System.out.println(out);
        }
    }
    
    private static void init(Scanner in){
        N = in.nextInt(); 
        L = in.nextInt(); 
        E = in.nextInt();
        graph = new Graph(N);
        gateways = new ArrayList<Node>();
        
        for (int i = 0; i < L; i++) {
            int N1 = in.nextInt(); // N1 and N2 defines a link between these nodes
            int N2 = in.nextInt();
            linkNodes(N1, N2);
        }
        for (int i = 0; i < E; i++) {
            int EI = in.nextInt(); // the index of a gateway node
            setGateway(EI);
        }
    }
    
    private static void linkNodes(int s, int e){
        Node start = graph.nodes[s];
        Node end = graph.nodes[e];
        
        Edge ed = new Edge(start, end);
        start.addEdge(ed);
        end.addEdge(ed);
    }
    
    private static void setGateway(int e){
        Node n = graph.nodes[e];
        n.gateway = true;
        gateways.add(n);
    }
    
    private static String findEdge(int sky){
        List<List<Node>> frontier = new ArrayList<List<Node>>();
        Node skyn = graph.nodes[sky];
        List<Node> path = new ArrayList<Node>();
        path.add(skyn);
        frontier.add(path);
        while (!frontier.isEmpty()){
            List<Node> l = frontier.remove(0);
            String err = "Size="+l.size()+", Nodes: ";
            for (int i=0; i < l.size(); i++)
                err += " "+l.get(i).id;
            System.err.println(err);
            System.err.println("Checking node "+l.get(l.size()-1).id);
            Node n = l.get(l.size()-1);
            if (n.gateway){
                return l.get(0).id+" "+l.get(1).id;
            }
            else {
                for (Edge e : n.edges){
                    List<Node> ln = copyList(l);
                    Node nk = (e.start.id == n.id) ? e.end : e.start;
                    ln.add(nk);
                    frontier.add(ln);
                }
            }
        }
        return "path not found";
    }
    
    private static List<Node> copyList(List<Node> l){
        List<Node> ln = new ArrayList<Node>();
        for(int i = 0; i < l.size(); i++){
            ln.add(l.get(i));
        }
        return ln;
    }
        
}
