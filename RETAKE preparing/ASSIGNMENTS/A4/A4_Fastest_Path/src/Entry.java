/**
 * Created by Petr on 20.04.2017.
 */
public class Entry {
    Vertex vertex;
    double key;
    Edge edge;

    public Entry(Vertex vertex, double key, Edge edge) {
        this.vertex = vertex;
        this.key = key;
        this.edge = edge;
    }
}
