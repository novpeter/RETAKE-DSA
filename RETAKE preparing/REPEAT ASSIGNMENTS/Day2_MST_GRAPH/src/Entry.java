public class Entry{
    Vertex vertex;
    Vertex previous_vertex;
    int weight;

    public Entry(Vertex vertex, int weight, Vertex previous_vertex) {
        this.vertex = vertex;
        this.previous_vertex = previous_vertex;
        this.weight = weight;
    }
}