import java.util.ArrayList;

/**
 * Created by Petr on 18.04.2017.
 */
public class Vertex {
    private String name;
    Entry inQueue;
    boolean visited;
    public ArrayList<Edge> incidentEdges;
    public ArrayList<Vertex> adjacencyVertex;

    public Vertex(String name) {
        incidentEdges = new ArrayList<>();
        adjacencyVertex = new ArrayList<>();
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
