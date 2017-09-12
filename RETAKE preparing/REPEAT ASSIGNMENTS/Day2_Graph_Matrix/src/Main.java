import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by petr on 12.08.17.
 */
public class Main {
    private static Graph graph;

    public static void main(String[] args) {
        readFile();
        printMatrix();
        int[][] matrix = graph.floydWarshall();
        printDist(matrix);
    }

    private static void printDist(int[][] matrix){
        for (int i=0; i<matrix[0].length; i++){
            for (int j=0; j<matrix.length; j++){
                System.out.printf(matrix[i][j] + " ");
            }
            System.out.printf("\n");
        }
    }

    private static void printMatrix() {
        int[][] matrix = graph.getMatrix();
        ArrayList<String> indexToVertex = graph.getIndexToVertex();
        for (String name: indexToVertex) {
            System.out.printf("  " + name);
        }
        System.out.printf("\n");
        for (int i=0; i<matrix[0].length; i++){
            System.out.printf(indexToVertex.get(i));
            for (int j=0; j<matrix.length; j++){
                System.out.printf("  " + matrix[i][j]);
            }
            System.out.printf("\n");
        }
    }

    private static void readFile(){
        try{
            Scanner sc = new Scanner(new File("input.txt"));
            if(sc.hasNextLine()){
                String[] vertices = sc.nextLine().split(" ");
                graph = new Graph(false, vertices.length);
                for (String name: vertices) {
                    graph.addVertex(name);
                }
            }
            while (sc.hasNextLine()){
                String[] edge = sc.nextLine().split(" ");
                graph.addEdge(edge[0], edge[1], Integer.valueOf(edge[2]));
            }
        }catch (FileNotFoundException ex){
            System.out.println("File not found");
        }
    }
}
