import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by petr on 11.08.17.
 */
public class Main {
    private static Graph graph = new Graph(false);


    public static void main(String[] args) {
        graph.addEdge("A", "B", 6);
        graph.addEdge("A", "D", 1);
        graph.addEdge("B", "D", 2);
        graph.addEdge("B", "E", 2);
        graph.addEdge("B", "C", 5);
        graph.addEdge("C", "E", 4);
        graph.addEdge("D", "E", 1);
        for (Entry e: Dijkstra.shortestPath(graph, graph.getAllVertices().get(0))) {
            System.out.println(e.vertex.getData() + " - " + e.weight);
        }

    }

    private static void writeString(String s){
        try{
            FileWriter writer = new FileWriter("output.txt");
            writer.write(s);
        }catch(IOException ignored){
        }
    }

    /**
     * Print info about all vertices in given graph
     * @param graph
     */
    private static void printInfo(Graph graph){
        for (Vertex v: graph.getAllVertices()) {
            System.out.println(v.getData() + " degree: " + v.getDegree());
            System.out.printf("adjacent: ");
            for (Vertex adjVertices: v.getVertices()) {
                System.out.print(adjVertices.getData() + " ");
            }
            System.out.println("");
            for (Edge e: v.getEdges()) {
                System.out.println( "   (" + e.getVertex1().getData() + " <-> " + e.getVertex2().getData() + ", " + e.getWeight() + ")");
            }
            System.out.printf("\n");
        }
    }
}
