import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph{

    private List<Edge> allEdges;
    private Map<String ,Vertex> allVertex;
    private boolean isDirected = false;

    public Graph(boolean isDirected){
        allEdges = new ArrayList<Edge>();
        allVertex = new HashMap<String,Vertex>();
        this.isDirected = isDirected;
    }

    public void addEdge(String data1, String data2){
        addEdge(data1, data2,0);
    }

    public Vertex addVertex(String data){
        if(allVertex.containsKey(data)){
            return allVertex.get(data);
        }
        Vertex v = new Vertex(data);
        allVertex.put(data, v);
        return v;
    }

    public Vertex getVertex(String data){
        return allVertex.get(data);
    }

    public void addEdge(String data1, String data2, int weight){
        Vertex vertex1 = null;
        if(allVertex.containsKey(data1)){
            vertex1 = allVertex.get(data1);
        }else{
            vertex1 = new Vertex(data1);
            allVertex.put(data1, vertex1);
        }
        Vertex vertex2 = null;
        if(allVertex.containsKey(data2)){
            vertex2 = allVertex.get(data2);
        }else{
            vertex2 = new Vertex(data2);
            allVertex.put(data2, vertex2);
        }

        Edge edge = new Edge(vertex1,vertex2,isDirected,weight);
        allEdges.add(edge);
        vertex1.addAdjacentVertex(edge, vertex2);
        if(!isDirected){
            vertex2.addAdjacentVertex(edge, vertex1);
        }

    }

    public List<Edge> getAllEdges(){
        return allEdges;
    }

    public Collection<Vertex> getAllVertex(){
        return allVertex.values();
    }

    @Override
    public String toString(){
        StringBuffer buffer = new StringBuffer();
        for(Edge edge : getAllEdges()){
            buffer.append(edge.getVertex1() + " " + edge.getVertex2() + " " + edge.getWeight());
            buffer.append("\n");
        }
        return buffer.toString();
    }
}