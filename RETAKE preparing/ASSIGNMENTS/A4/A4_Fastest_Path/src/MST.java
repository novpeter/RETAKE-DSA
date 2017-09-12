import java.util.*;

/**
 * Created by Petr on 20.04.2017.
 */
public class MST {
    protected Graph tree;
    protected PriorityQueue<Entry> queue;
    private final double INFINITE = 999999999;
    private ArrayList<Vertex> visited;
    private ArrayList<Vertex> path;
    private Vertex dest;
    private Vertex start;
    private double weight;
    private double totalTime;
    private double totalCost;

    public MST() {
        tree = new Graph();
        queue = new PriorityQueue<>((Entry e1, Entry e2) -> (e1.key > e2.key) ? 1 : -1);
    }

    /**
     * This method fills the Priority Queue with entries, which consist of vertex, key, edge.
     * It creates entries with key = INFINITE, edge = null, and vertex from given graph, except first vertex,
     * whose key is equal to 0.
     * @param graph
     */
    public void findMST(Graph graph){
        for (Vertex v: graph.vertices) {
            if (v.equals(graph.vertices.get(0))){
                Entry entry = new Entry(v, 0, null);
                v.inQueue = entry;
                queue.add(entry);
            }else{
                Entry entry = new Entry(v, INFINITE, null);
                v.inQueue = entry;
                queue.add(entry);
            }
        }

        while (!queue.isEmpty()){
            Entry u = queue.remove();
            tree.addVertex(u.vertex.getName());
            if (u.edge != null){
                tree.addEdge(u.vertex.getName(), u.edge.opposite(u.vertex).getName(), u.edge.getDist(), u.edge.getTime(), u.edge.getCost());
            }
            for (Edge edge: u.vertex.incidentEdges) {
                Entry inQueue = edge.opposite(u.vertex).inQueue;
                if (inQueue != null){
                    if (inQueue.key > edge.getDist()){
                        inQueue.key = edge.getDist();
                        inQueue.edge = edge;
                        queue.remove(inQueue);
                        queue.add(inQueue);
                    }
                }
            }
        }
    }

    /**
     * This method uses Prim's algorithm to find a path from one city (start) to
     * another cite (destination). Also it calculates total time and total cost of trip.
     * @param vertex
     * @return boolean value
     */
    private boolean DFS(Vertex vertex)
    {
        if (vertex == dest)
        {
            return true;
        }
        visited.add(vertex);
        for (Edge e: vertex.incidentEdges) {
            if(!visited.contains(e.opposite(vertex))){
                if (DFS(e.opposite(vertex))){
                    path.add(0,e.opposite(vertex));
                    totalTime += e.getTime();
                    totalCost += e.getCost();
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This method calls DFS method to estimate total cost and time of the trip and return all information about this trip
     * @param start - start point
     * @param dest - destination point
     * @param weight - weight of  thing
     * @return result info
     */
    public String calculate(String start, String dest, double weight){
        visited = new ArrayList<>();
        path = new ArrayList<>();
        this.dest = tree.map.get(dest);
        this.start = tree.map.get(start);
        this.weight = weight;
        DFS(this.start);
        String result = start + " " + dest + " " + weight + " " + String.format("%.1f", totalTime)+ " " + String.format("%.1f",totalCost*weight );
        totalCost = 0;
        totalTime = 0;
        return result;
    }

}
