import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Petr on 02.02.2017.
 */
public class Main {

    public static void main(String[] args) {
        String result = "";
        String inputString = readString(1);
        String compareString = readString(2);
        if (inputString.isEmpty() && !compareString.isEmpty()) {
            writeString("false");
        }
        if (!inputString.isEmpty() && compareString.isEmpty()) {
            writeString("");
        }
        if (!inputString.isEmpty() && !compareString.isEmpty()) {
            String[] inputNums = inputString.split(" ");
            String[] compareNums = compareString.split(" ");
            MyBitSet bitSet = new MyBitSet();
            for (int i = 0; i < inputNums.length; i++) {
                bitSet.addElement(Integer.valueOf(inputNums[i]));
            }
            for (int i = 0; i < compareNums.length; i++) {
                result = result + bitSet.hasElement(Integer.valueOf(compareNums[i])) + " ";
            }
            writeString(result);


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
            Scanner sc = new Scanner(new File("input.txt"));
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
     * Writes the string in the file96
     * @param s the string which has to be written
     */
    private static void writeString(String s) {
        try (FileWriter writer = new FileWriter("output.txt")) {
            writer.write(s);
        } catch (IOException ex) {
        }
    }

}

