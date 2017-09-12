import java.util.ArrayList;

public class Vertex{
    private String data;
    private ArrayList<Edge> edges = new ArrayList<>();
    private ArrayList<Vertex> adjacentVertex = new ArrayList<>();

    public Vertex(String data){
        this.data = data;
    }

    public void setData(String data){
        this.data = data;
    }

    public String getData(){
        return data;
    }

    public void addAdjacentVertex(Edge e, Vertex v){
        edges.add(e);
        adjacentVertex.add(v);
    }

    public java.lang.String toString(){
        return java.lang.String.valueOf(data);
    }

    public ArrayList<Vertex> getAdjacentVertexes(){
        return adjacentVertex;
    }

    public ArrayList<Edge> getEdges(){
        return edges;
    }

    public int getDegree(){
        return edges.size();
    }
}