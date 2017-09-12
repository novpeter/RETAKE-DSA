import java.util.ArrayList;

/**
 * Created by Petr on 18.04.2017.
 */
public class Vertex {
    private String name;
    int value;
    int inDegree;
    protected ArrayList<Vertex> parents;
    protected ArrayList<Vertex> children;

    public Vertex(String name) {
        parents = new ArrayList<>();
        children = new ArrayList<>();
        this.name = name;
    }

    public void  setInDegree(){
        inDegree = parents.size();
    }

    public String getName() {
        return name;
    }
}
