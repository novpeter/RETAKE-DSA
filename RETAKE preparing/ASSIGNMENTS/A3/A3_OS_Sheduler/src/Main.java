import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Petr on 22.03.2017.
 */
public class Main {
    private static ArrayList<Process> processList = new ArrayList<Process>();

    public static void main(String[] args) {
        fillEventList();
        writeString(Scheduler.getLastEvent(processList));
    }

    /**
     * This method fill the given ArrayList with strings from .csv file.
     */
    private static void fillEventList() {
        try {
            Scanner sc = new Scanner(new File("input.csv"));
            sc.nextLine();
            while (sc.hasNextLine()) {
                String[] param = sc.nextLine().split(",");
                processList.add(new Process(param[0],Integer.parseInt(param[1]),Integer.parseInt(param[2]),Integer.parseInt(param[3])));
            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
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
