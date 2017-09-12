import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by petr on 12.08.17.
 */

public class Graph {
    private int[][] adjMatrix;
    private ArrayList<String> indexToVertex = new ArrayList<>();
    private HashMap<String, Integer> vertexToIndex;
    private boolean isDirected;
    private final int CONST = 9999;


    public Graph(boolean isDirected,int vertices){
        adjMatrix = new int[vertices][vertices];
        vertexToIndex = new HashMap();
        this.isDirected = isDirected;
        for (int i=0; i<adjMatrix.length; i++){
            for (int j=0; j<adjMatrix.length; j++){
                if (i!=j) {
                    adjMatrix[i][j] = CONST;
                }
            }
            System.out.printf("\n");
        }
    }

    public void addVertex(String name){
        if (!vertexToIndex.containsKey(name)){
            int index = vertexToIndex.size();
            vertexToIndex.put(name, index);
            indexToVertex.add(name);
        }
    }


    public void addEdge(String start, String end,int weight){
        addVertex(start);
        addVertex(end);
        if (isDirected){
            adjMatrix[vertexToIndex.get(start)][vertexToIndex.get(end)] = weight;
        }else{
            adjMatrix[vertexToIndex.get(start)][vertexToIndex.get(end)] = weight;
            adjMatrix[vertexToIndex.get(end)][vertexToIndex.get(start)] = weight;
        }
    }

    public ArrayList<String> getIndexToVertex() {
        return indexToVertex;
    }

    public int[][] getMatrix(){
        return adjMatrix;

    }

    public int[][] floydWarshall(){
        int dist[][] = new int[vertexToIndex.size()][vertexToIndex.size()];
        int i, j, k;

        /* Initialize the solution matrix same as input graph matrix.
           Or we can say the initial values of shortest distances
           are based on shortest paths considering no intermediate
           vertex. */
        for (i = 0; i < vertexToIndex.size(); i++)
            for (j = 0; j < vertexToIndex.size(); j++)
                dist[i][j] = adjMatrix[i][j];

        /* Add all vertices one by one to the set of intermediate
           vertices.
          ---> Before start of a iteration, we have shortest
               distances between all pairs of vertices such that
               the shortest distances consider only the vertices in
               set {0, 1, 2, .. k-1} as intermediate vertices.
          ----> After the end of a iteration, vertex no. k is added
                to the set of intermediate vertices and the set
                becomes {0, 1, 2, .. k} */
        for (k = 0; k < adjMatrix.length; k++)
        {
            // Pick all vertices as source one by one
            for (i = 0; i < adjMatrix.length; i++)
            {
                // Pick all vertices as destination for the
                // above picked source
                for (j = 0; j < adjMatrix.length; j++)
                {
                    // If vertex k is on the shortest path from
                    // i to j, then update the value of dist[i][j]
                    if (dist[i][k] + dist[k][j] < dist[i][j])
                        dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }
        return dist;
    }

}
