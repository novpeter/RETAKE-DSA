import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * Created by petr on 13.08.17.
 */
public class Dijkstra {
    private static final int INF = Integer.MAX_VALUE;

    public static Collection<Entry> shortestPath(Graph graph, Vertex source){
        //create visited set of vertices
        ArrayList<Vertex> visited = new ArrayList<>();

        //create map for Priority Queue
        HashMap<String, Entry> map = new HashMap<>();

        //create Priority Queue
        PriorityQueue<Entry> minHeap = new PriorityQueue<>((Entry e1, Entry e2) -> (e1.weight > e2.weight) ? 1 : -1);

        //fill all entries with INF weight except entry with source vertex
        for (Vertex v : graph.getAllVertices()) {
            if (v.equals(source)){
                Entry entry = new Entry(v, 0, null);
                minHeap.add(entry);
                map.put(v.getData(), entry);
            }else{
                Entry entry = new Entry(v, INF, null);
                minHeap.add(entry);
                map.put(v.getData(), entry);
            }
        }

        /*While algorithm don't visit every vertex, do next steps:
                -Take a vertex with minimum time from the table (priority queue) - current vertex.
                -For each adjacent vertex, if it isn't visited, update time and previous_vertex values, according to the current vertex.
                -Put current vertex into "visited" set.
        */

        while (visited.size() != graph.getAllVertices().size()){
            Vertex current = minHeap.poll().vertex;
            for (Edge e: current.getEdges()) {
                Vertex adjacent = e.getOpposite(current);
                if (!visited.contains(adjacent)){
                    Entry entry = map.get(adjacent.getData());
                    if (entry.weight > e.getWeight() + map.get(current.getData()).weight){
                        entry.previous_vertex = current; //update previous_vertex
                        entry.weight = e.getWeight() + map.get(current.getData()).weight; //update weight
                        minHeap.remove(entry);
                        minHeap.add(entry);
                    }
                }
            }
            visited.add(current);
        }

        return map.values();
    }
}
