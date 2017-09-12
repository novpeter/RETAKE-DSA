import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class DijkstraShortestPath {

    public Map<Vertex, Integer> shortestPath(Graph graph, String sourceVertex) {
        Vertex source = graph.getVertex(sourceVertex);

        //heap + map data structure
        BinaryMinHeap minHeap = new BinaryMinHeap();

        //stores shortest distance from root to every vertex
        Map<Vertex, Integer> distance = new HashMap<>();

        //stores parent of every vertex in shortest distance
        Map<Vertex, Vertex> parent = new HashMap<>();

        //initialize all vertices with infinity weight
        for (Vertex vertex: graph.getAllVertex()) {
            Entry entry = new Entry(vertex, Integer.MAX_VALUE);
            minHeap.insert(entry);
        }

        //put sourceVertex in minHeap with 0 weight
        minHeap.updateWeight(source, 0);

        //distance of source vertex is 0
        distance.put(source, 0);

        //source vertex parent is null
        parent.put(source, null);

        while (!minHeap.isEmpty()){
            Entry entry = minHeap.remove();
            Vertex current = entry.vertex;

            //update distance
            distance.put(current, entry.weight);

            for (Edge edge : current.getEdges()) {
                Vertex adjacent = edge.getOpposite(current);

                if (!minHeap.contains(adjacent)){
                    continue;
                }

                //if heap does not contain adjacent vertex means adjacent vertex already has shortest distance from source vertex
                int newDistance = distance.get(current) + edge.getWeight();

                //see if this above calculated distance is less than current distance stored for adjacent vertex from source vertex
                if(minHeap.getWeight(adjacent) > newDistance) {
                    minHeap.updateWeight(adjacent, newDistance);
                    parent.put(adjacent, current);
                }
            }
        }

        return distance;
    }
}