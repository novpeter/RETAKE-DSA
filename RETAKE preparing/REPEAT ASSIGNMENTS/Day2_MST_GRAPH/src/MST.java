import java.util.*;

/**
 * Created by petr on 12.08.17.
 */
public class MST {
    private final double INFINITY = Double.MAX_VALUE;
    public double totalTime = 0, totalCost = 0;
    private static Graph tree;
    private static PriorityQueue<Edge> pq;
    private static ArrayList<Vertex> visited;
    private static ArrayList<Vertex> path;


    public static void primMST(Graph graph){
        visited = new ArrayList<>();
        tree = new Graph(false);
        pq = new PriorityQueue<>((Edge e1, Edge e2) -> (e1.getWeight() > e2.getWeight())? 1: -1);

        Vertex current = graph.getAllVertices().get(0);

        while (true){
            for (Edge edge: current.getEdges()) {
                if (!tree.containVertex(edge.getOpposite(current).getData())){
                    pq.add(edge);
                }
            }

            while(!pq.isEmpty()){
                Edge edge = pq.poll();
                if (!(tree.containVertex(edge.getVertex1().getData()) && tree.containVertex(edge.getVertex2().getData()))){
                    Vertex vertex1 = edge.getVertex1();
                    Vertex vertex2 = edge.getVertex2();
                    if (tree.containVertex(vertex1.getData())){
                        current = vertex2;
                    }else{
                        current = vertex1;
                    }
                    tree.addEdge(vertex1.getData(), vertex2.getData(), edge.getWeight());
                    break;
                }
            }

            if(tree.getAllVertices().size() == graph.getAllVertices().size()){
                break;
            }

        }
    }

    public static Graph getMST(){
        return tree;
    }

    public static void DFS(Graph graph){
        Set<String> visited = new HashSet<>();
        for(Vertex vertex : graph.getAllVertices()){
            if(!visited.contains(vertex.getData())){
                DFSUtil(vertex,visited);
            }
        }
    }

    private static void DFSUtil(Vertex v,Set<String> visited){
        visited.add(v.getData());
        System.out.print(v.getData() + " ");
        for(Vertex vertex : v.getVertices()){
            if(!visited.contains(vertex.getData()))
                DFSUtil(vertex,visited);
        }
    }


    public static void BFS(Graph graph){
        Set<String> visited = new HashSet<String>();
        Queue<Vertex> q = new LinkedList<Vertex>();

        for(Vertex vertex: graph.getAllVertices()){
            if(!visited.contains(vertex.getData())){
                q.add(vertex);
                visited.add(vertex.getData());
                while(q.size() != 0){
                    Vertex vq = q.poll();
                    System.out.print(vq.getData()+ " ");
                    for(Vertex v : vq.getVertices()){
                        if(!visited.contains(v.getData())){
                            q.add(v);
                            visited.add(v.getData());
                        }
                    }
                }
            }
        }

    }
}
