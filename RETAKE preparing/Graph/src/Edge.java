public class Edge{
    private boolean isDirected = false;
    private Vertex vertex1;
    private Vertex vertex2;
    private int weight;

    public Edge(Vertex vertex1, Vertex vertex2){
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
    }

    public Edge(Vertex vertex1, Vertex vertex2,boolean isDirected,int weight){
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.weight = weight;
        this.isDirected = isDirected;
    }

    public Edge(Vertex  vertex1, Vertex vertex2,boolean isDirected){
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.isDirected = isDirected;
    }

    public Vertex getVertex1(){
        return vertex1;
    }

    public Vertex getVertex2(){
        return vertex2;
    }

    public int getWeight(){
        return weight;
    }

    public Vertex getOpposite(Vertex vertex){
        if (vertex.equals(vertex1)){
            return vertex2;
        }else{
            return vertex1;
        }
    }

    public boolean isDirected(){
        return isDirected;
    }
}