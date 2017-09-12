import sun.dc.pr.PRError;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static ArrayList<Process> entries;

    public static void main(String[] args) {
        readFile();
        String result = Scheduler.getLastEvent(entries);
        writeString(result);
    }

    private static void readFile(){
        try{
            entries = new ArrayList<>();
            Scanner sc = new Scanner(new File("input.csv"));
            sc.nextLine();
            while (sc.hasNextLine()){
                String[] line = sc.nextLine().split(",");
                String NAME = line[0];
                int EVENT = Integer.parseInt(line[1]);
                int CPU_TIME = Integer.parseInt(line[2]);
                int PRIORITY = Integer.parseInt(line[3]);
                entries.add(new Process(NAME, EVENT, CPU_TIME, PRIORITY));
            }
        }catch (FileNotFoundException e){
            System.out.println("File not found");
        }
    }

    private static void writeString(String result){
        try (FileWriter writer = new FileWriter("output.txt")) {
            writer.write(result);
        } catch (IOException ignored) {
        }
    }
}
