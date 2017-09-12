import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph(false);
        graph.addEdge("A", "B", 6);
        graph.addEdge("A", "D", 1);
        graph.addEdge("B", "D", 2);
        graph.addEdge("D", "E", 1);
        graph.addEdge("B", "E", 2);
        graph.addEdge("B", "C", 5);
        graph.addEdge("C", "E", 5);
        DijkstraShortestPath path = new DijkstraShortestPath();
        Map<Vertex, Integer> map = path.shortestPath(graph, "A");
        System.out.println("");
    }
}
