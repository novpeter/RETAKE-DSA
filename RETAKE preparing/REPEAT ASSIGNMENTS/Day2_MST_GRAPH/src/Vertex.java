import java.util.ArrayList;
import java.util.HashMap;

public class Vertex {
    private String data;
    public boolean visited;
    public ArrayList<Edge> edges = new ArrayList<>();
    public HashMap<String, Vertex> adjVertex = new HashMap();

    public Vertex(String data) {
        visited = false;
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public ArrayList<Vertex> getVertices() {
        ArrayList<Vertex> vertices = new ArrayList<>();
        vertices.addAll(adjVertex.values());
        return vertices;
    }

    public int getDegree(){
        return adjVertex.size();
    }
}
