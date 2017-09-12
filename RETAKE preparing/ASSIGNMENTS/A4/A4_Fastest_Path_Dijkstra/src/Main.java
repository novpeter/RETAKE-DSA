import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Petr on 18.04.2017.
 */
public class Main {
    private static String pathname;
    public static Graph G = new Graph();
    public static ArrayList<Request> requests = new ArrayList();
    public static ArrayList<String> result = new ArrayList();

    public static void main(String[] args) {
        createGraph();
        readRequest();
        estimatePath();
    }

    /**
     * This method estimates delivery cost and time from one city to another.
     */
    private static void estimatePath() {
        for (Request req: requests) {
            result.add(G.fastestPath(req.getStart(), req.getDestination(), req.getWeight()));
        }
        writeString(result);
    }


    /**
     * This method creates the new graph.
     */
    private static void createGraph(){
        fillVertexSet();
        fillEdgeSet();
    }

    /**
     * This method fills the edge set in graph G from russia.txt file;
     */
    private static void fillEdgeSet() {
        pathname = "russia.txt";
        String[] edges = readString(2).split(" ");
        for (int i = 0; i < edges.length; i+=3){
            String vertex1 = edges[i];
            String vertex2 = edges[i+1];
            String[] info = edges[i+2].split(":");
                double dist = Double.parseDouble(info[0]);
                double time = Double.parseDouble(info[1]);
                double cost = Double.parseDouble(info[2]);
            G.addEdge(vertex1, vertex2, dist,time,cost);
        }
    }

    /**
     * This method fills the vertex set in graph G from russia.txt file;
     */
    private static void fillVertexSet(){
        pathname = "russia.txt";
        String[] vertices = readString(1).split(" ");
        for (String name: vertices) {
            G.addVertex(name);
        }
    }

    /**
     * This method reads requests from input.txt file;
     * @return
     */
    private static void readRequest(){
        try {
            Scanner sc = new Scanner(new File("input.txt"));
            while (sc.hasNextLine()){
                String[] req = sc.nextLine().split(" ");
                String start = req[0];
                String destination = req[1];
                double weight = Double.parseDouble(req[2]);
                requests.add(new Request(start, destination,weight));
            }
        } catch (FileNotFoundException ex){
            System.out.println("File not found");
        }

    }

    /**
     * Reads the string from file.
     *
     * @param stringNumber the number of string which has to be read.
     * @return needed string.
     */
    private static String readString(int stringNumber) {
        String result = "";
        try {
            Scanner sc = new Scanner(new File(pathname));
            for (int i = 0; i < stringNumber; i++) {
                if (sc.hasNextLine()) {
                    result = sc.nextLine();
                }
            }
            return result;
        } catch (FileNotFoundException ex) {
            return "";
        }
    }

    /**
     * This method writes answer in output.txt
     * @param s - answer
     */
    private static void writeString(ArrayList<String> s) {
        try (FileWriter writer = new FileWriter("output.txt")) {
            for (int i=0; i<s.size(); i++){
                if(i == s.size() - 1){
                    writer.write(s.get(i));
                }else{
                    writer.write(s.get(i) + "\n");
                }
            }
        } catch (IOException ex) {
        }
    }
}
