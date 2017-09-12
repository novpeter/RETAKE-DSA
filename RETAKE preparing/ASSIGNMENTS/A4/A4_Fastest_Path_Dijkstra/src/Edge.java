import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by Petr on 18.04.2017.
 */
public class Edge {
    ArrayList<Vertex> endVertices;
    private double dist;
    private double time;
    private double cost;

    public Edge(Vertex v1, Vertex v2, double dist, double time, double cost) {
        endVertices = new ArrayList<>();
        endVertices.add(v1);
        endVertices.add(v2);
        this.dist = dist;
        this.time = time;
        this.cost = cost;
    }

    public Vertex opposite(Vertex v){
        if (v.equals(endVertices.get(0))){
            return endVertices.get(1);
        }else{
            return endVertices.get(0);
        }
    }

    public Vertex getV1() {
        return endVertices.get(0);
    }

    public Vertex getV2() {
        return endVertices.get(1);
    }

    public double getDist() {
        return dist;
    }

    public double getTime() {
        return time;
    }

    public double getCost() {
        return cost;
    }


}
