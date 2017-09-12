import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by Petr on 18.04.2017.
 */
public class Edge {
    private String operation;
    private Vertex startVertex;
    private Vertex endVertex;

    public Edge(Vertex v1, Vertex v2, String operation) {
        startVertex = v1;
        endVertex = v2;
        this.operation = operation;
    }

    public String getOperation() {
        return operation;
    }

    public Vertex getStartVertex() {
        return startVertex;
    }

    public Vertex getEndVertex() {
        return endVertex;
    }
}
