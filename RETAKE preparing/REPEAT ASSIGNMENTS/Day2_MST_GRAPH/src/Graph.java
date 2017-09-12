import java.util.ArrayList;
import java.util.HashMap;

public class Graph {

    private ArrayList<Edge> allEdges;
    private HashMap<String, Vertex> allVertices;
    private boolean isDirected = false;

    public Graph(boolean isDirected) {
        allEdges = new ArrayList<>();
        allVertices = new HashMap<String, Vertex>();
        this.isDirected = isDirected;
    }

    public void addEdge(String start, String end, int weight){
        if(!allVertices.containsKey(start)){
            allVertices.put(start, new Vertex(start));
        }
        if(!allVertices.containsKey(end)){
            allVertices.put(end, new Vertex(end));
        }
        Vertex vertex1 = allVertices.get(start);
        Vertex vertex2 = allVertices.get(end);
        Edge newEdge;
        if (isDirected){
            if (!vertex1.adjVertex.containsKey(end)){
                newEdge = new Edge(true, vertex1, vertex2, weight);
                allEdges.add(newEdge);
                vertex1.adjVertex.put(end, vertex2);
                vertex1.edges.add(newEdge);
            }
        }else {
            if (!vertex1.adjVertex.containsKey(end)) {
                newEdge = new Edge(false,vertex1, vertex2, weight);
                allEdges.add(newEdge);
                vertex1.adjVertex.put(end, vertex2);
                vertex2.adjVertex.put(start, vertex1);
                vertex1.edges.add(newEdge);
                vertex2.edges.add(newEdge);
            }
        }
    }

    public Vertex addVertex(String data){
        if (!allVertices.containsKey(data)){
            Vertex vertex = new Vertex(data);
            allVertices.put(data, vertex);
            return vertex;
        }else{
            return allVertices.get(data);
        }
    }

    public Vertex getVertex(String data){
        return allVertices.getOrDefault(data, null);
    }

    public ArrayList<Vertex> getAllVertices(){
        ArrayList<Vertex> vertices = new ArrayList<>();
        vertices.addAll(allVertices.values());
        return vertices;
    }

    public boolean areAdjacent(String name1, String name2){
        if (!allVertices.containsKey(name1) || !allVertices.containsKey(name2)){
            return false;
        }else{
            if (allVertices.containsKey(name1)){
                return allVertices.get(name1).adjVertex.containsKey(name2);
            }
            if (allVertices.containsKey(name2)){
                return allVertices.get(name2).adjVertex.containsKey(name1);
            }
        }
        return false;
    }

    public boolean containVertex(String data){
        return allVertices.containsKey(data);
    }

    public boolean containVertex(Vertex vertex){
        return allVertices.containsValue(vertex);
    }
}
