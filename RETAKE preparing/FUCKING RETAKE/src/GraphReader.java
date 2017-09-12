
import java.io.*;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author PETER_NOVOSELOV________________
 */
public class GraphReader {
    
    //TODO: implement your adjacency matrix graph
    static class MyGraph {
        int[][] Matrix;
        ArrayList<String> visited = new ArrayList<>();
        HashMap<String, Integer> stringToIndex;
        ArrayList<String> vertices;
        ArrayList<String> result = new ArrayList<>();
        HashMap<String, String> parent = new HashMap<>();

        /**
         * Fills data structure with data
         * @param rawData 
         */
        public MyGraph(Map<String, List<String>> rawData) {
           Matrix = new int[rawData.size()][rawData.size()];
           stringToIndex = new HashMap<>();
           addEdges(rawData);
        } 


        public void addEdges(Map<String, List<String>> rawData){
            vertices = new ArrayList<>();
            vertices.addAll(rawData.keySet());
            for (int i = 0; i<vertices.size(); i++){
                String current = vertices.get(i);
                stringToIndex.put(current, i);
            }
            for (String vertex : vertices) {
                int from = stringToIndex.get(vertex);
                List<String> adjacent = rawData.get(vertex);
                for (String adjVertex: adjacent) {
                    int to = stringToIndex.get(adjVertex);
                    Matrix[from][to] = 1;
                }
            }
        }

        /**
         * Implementation of any suitable shorted path algorithm for unweighted graph
         * @param from
         * @param to
         */
        public void shortestPath(String from, String to) {
            ArrayList<String> queue = new ArrayList<>();
            queue.add(from);
            visited.add(from);
            parent.put(from, from);
            while (!queue.isEmpty()){
                String current = queue.remove(0);
                int currentIndex = stringToIndex.get(current);
                for (int i = 0; i<Matrix[0].length; i++) {
                    if (Matrix[currentIndex][i] == 1){
                        if (!visited.contains(vertices.get(i))){
                            parent.put(vertices.get(i), current);
                            queue.add(vertices.get(i));
                            visited.add(vertices.get(i));
                        }
                    }
                }
            }
            String current = to;
            String previous = "";
            while (!previous.equals(from)){
                previous = parent.get(current);
                System.out.printf(previous + " <- ");
                current = previous;
            }
            System.out.printf(from);
        }


        /**
         * Prints routing table for a given IP address
         * @param ip - source IP address
         */
        public void printRoutingTable(String ip) {
            //TODO: Replace this code with your code for printing routing table
            System.out.println(ip + ": STUB");
        }
    
    }
    
    /**
     * This code reads data file with network graph
     * @param filename - name of the graph file
     * @return - key is an address, and value is a list of directly connected nodes
     */
    public static Map<String, List<String>> readFile(String filename) {
        try {
            Scanner sc = new Scanner(new File(filename));
            HashMap<String, List<String>> r = new HashMap<>();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.isEmpty()) continue;
                String[] parts = line.split("\\s");
                String from = parts[0].substring(0, parts[0].length() - 1);
                List<String> toNodes = new LinkedList<>();
                for (int i = 1; i < parts.length; i++) toNodes.add(parts[i]);
                r.put(from, toNodes);
            }
            return r;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }   
        
    }
     
    /**
     * Program entry point
     * @param params not used
     * @throws IOException 
     */
    public static void main(String[] params) throws IOException {
        Map<String, List<String>> rawData = readFile("input.txt");
        System.out.print("All nodes: ");
        for (String s: rawData.keySet()) System.out.print(s + " ");
        System.out.println();
        MyGraph g = new MyGraph(rawData);

        g.shortestPath("2.0.0.2", "3.0.0.1");
        /**
         * Here you perform shortest path searches for each pair to fill routing table.
         * Hint: the simplest way is to run nested loop for each node.
         */

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String ip;
        do {
            System.out.println("\n" + "Enter IP for test of ENTER to exit:> ");
            ip = reader.readLine().trim();
            if (ip.isEmpty()) break;
            g.printRoutingTable(ip);
        }  while (true);
    }
}