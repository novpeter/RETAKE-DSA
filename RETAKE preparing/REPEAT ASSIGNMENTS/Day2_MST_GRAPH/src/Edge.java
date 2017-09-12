/**
 * Created by petr on 11.08.17.
 */
public class Edge {
    private boolean isDirected = false;
    private Vertex vertex1;
    private Vertex vertex2;
    private int weight;

    public Edge(Vertex vertex1, Vertex vertex2) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
    }

    public Edge(boolean isDirected, Vertex vertex1, Vertex vertex2, int weight) {
        this.isDirected = isDirected;
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.weight = weight;

    }

    public Edge(boolean isDirected, Vertex vertex1, Vertex vertex2) {
        this.isDirected = isDirected;
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
    }

    public Vertex getOpposite(Vertex current){
        if (vertex1.equals(current) || vertex2.equals(current)){
            if (vertex1.equals(current)){
                return vertex2;
            }else{
                return vertex1;
            }
        }
        return null;
    }

    public boolean isDirected() {
        return isDirected;
    }

    public Vertex getVertex1() {
        return vertex1;
    }

    public Vertex getVertex2() {
        return vertex2;
    }

    public int getWeight() {
        return weight;
    }
}
