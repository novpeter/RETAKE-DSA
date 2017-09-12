import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Petr on 22.04.2017.
 */
public class Main {
    public static Graph G = new Graph();

    public static void main(String[] args) {
        readEquations();
        solveEquations();
    }

    /**
     * This method calls "solveSystem" method in class Graph to get yhe value of "R".
     */
    private static void solveEquations(){
        writeString(G.solveSystem());
    }

    /**
     * This method reads requests from input.txt file;
     * @return
     */
    private static void readEquations(){
        try {
            Scanner sc = new Scanner(new File("input.txt"));
            while (sc.hasNextLine()){
                String inputString = sc.nextLine();
                if(!inputString.equals("")) {
                    String[] equation = inputString.split("=");
                    Vertex vertex = new Vertex(equation[0]);
                    String equal = equation[1];
                    if (!G.vertexMap.containsKey(vertex.getName())) {
                        G.addVertex(vertex);
                    }
                    if (isNumber(equal)) {
                        G.vertexMap.get(vertex.getName()).value = Integer.parseInt(equal);
                    } else {
                        if (equal.contains("*")) {
                            Vertex v1 = new Vertex(equal.split("\\*")[0]);
                            Vertex v2 = new Vertex(equal.split("\\*")[1]);
                            if (!G.vertexMap.containsKey(v1.getName())) {
                                G.addVertex(v1);
                            }
                            if (isNumber(v1.getName())) {
                                G.vertexMap.get(v1.getName()).value = Integer.parseInt(v1.getName());
                            }
                            if (!G.vertexMap.containsKey(v2.getName())) {
                                G.addVertex(v2);
                            }
                            if (isNumber(v2.getName())) {
                                G.vertexMap.get(v2.getName()).value = Integer.parseInt(v2.getName());
                            }
                            G.addEdge(v1.getName(), vertex.getName(), "*");
                            G.addEdge(v2.getName(), vertex.getName(), "*");
                        }
                        if (equal.contains("+")) {
                            Vertex v1 = new Vertex(equal.split("\\+")[0]);
                            Vertex v2 = new Vertex(equal.split("\\+")[1]);
                            if (!G.vertexMap.containsKey(v1.getName())) {
                                G.addVertex(v1);
                            }
                            if (isNumber(v1.getName())) {
                                G.vertexMap.get(v1.getName()).value = Integer.parseInt(v1.getName());
                            }
                            if (!G.vertexMap.containsKey(v2.getName())) {
                                G.addVertex(v2);
                            }
                            if (isNumber(v2.getName())) {
                                G.vertexMap.get(v2.getName()).value = Integer.parseInt(v2.getName());
                            }
                            G.addEdge(v1.getName(), vertex.getName(), "+");
                            G.addEdge(v2.getName(), vertex.getName(), "+");
                        }
                    }
                }
            }
        } catch (FileNotFoundException ex){
            System.out.println("File not found");
        }
    }

    private static boolean isNumber(String string) {
        try {
            Integer.parseInt(string);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * This method writes answer in output.txt
     * @param s - answer
     */
    private static void writeString(String s) {
        try (FileWriter writer = new FileWriter("output.txt")) {
            writer.write(s);
        } catch (IOException ex) {
        }
    }

}
