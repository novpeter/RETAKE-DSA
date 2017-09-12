import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Petr on 21.02.2017.
 */
public class Main {
    private static String[] stopList = {"a", "the", "in", "at", "to", "on", "not", "for", "'s", "'d", "'re", "is", "are", "am", "has", "I", "we", "you"};
    private static MyMap<String, Integer> table = new MyMap<>();
    private static String[] answer = new String[2];

    public static void main(String[] args) throws IOException {
        fillMyMap();
        answer[0] = findWord();
        removeStopList();
        answer[1] =findWord();
        writeString(answer);
    }

    /**
     * This method fills HashMap with words from input.txt
     */
    private static void fillMyMap() {
        try {
            Scanner sc = new Scanner(new File("input.txt"));
            while (sc.hasNextLine()) {
                String line = sc.nextLine().toLowerCase().replaceAll("[.,!?;-]", " ").replaceAll("'"," '").replace("  "," ");
                String[] words = line.split(" ");
                for (String word : words) {
                    table.put(word);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method removes words from HashMap from given list;
     */
    private static void removeStopList() {
        for (String word : stopList) {
            table.remove(word);
        }
    }

    /**
     * This method finds word with maximum frequency
     * @return word with maximum frequency
     * @throws IOException
     */
    private static String findWord() throws IOException {
        String result = "";
        int max = 0;
        ArrayList<Entry> words = table.entrySet();
        for (Entry e : words) {
            if (max < (int) e.getValue()) {
                max = (int) e.getValue();
                result = e.getKey() + " " + String.valueOf(e.getValue());
            }
        }
        return result;
    }

    /**
     * This method writes answer in output.txt
     * @param s - answer
     */
    private static void writeString(String[] s) {
        try (FileWriter writer = new FileWriter("output.txt")) {
            writer.write(s[0] + "\n");
            writer.write(s[1]);
        } catch (IOException ex) {
        }
    }

}
