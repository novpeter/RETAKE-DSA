/**
 * Created by Petr on 21.04.2017.
 */
public class Entry {
    Vertex vertex;
    double time; //use to find fastest path from vertex to previous_vertex.
    double cost;
    Vertex previous_vertex;

    public Entry(Vertex vertex, double time, double cost, Vertex previous_vertex) {
        this.vertex = vertex;
        this.time = time;
        this.cost = cost;
        this.previous_vertex = previous_vertex;
    }
}
