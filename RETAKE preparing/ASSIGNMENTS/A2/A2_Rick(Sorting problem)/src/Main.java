import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Petr on 23.02.2017.
 */
public class Main {

    private static ArrayList<Double> column = new ArrayList<Double>();
    private  static int columns;

    public static void main(String[] args) throws FileNotFoundException {
        numberOfColumns();
        writeString(findColumn());
    }

    /**
     * This method finds the column with time measurement.
     * @return number of time measurement column.
     */
    private static String findColumn() {
        for (int i = 0; i < columns ; i++){
            column.clear();
            getColumn(i);
            if (Check.checkColumn(column)){
                return String.valueOf(i);
            }
        }
        return "";
    }

    /**
     * This method gives number of columns in CSV document.
     */
    private  static void numberOfColumns(){
        String[] line = readString(1).split(",");
        columns = line.length;
    }

    /**
     * This method fill the array with column of measurements from CSV document.
     */
    private static void getColumn(int i) {
        ArrayList<Double> input = new ArrayList<Double>();
        try {
            File file = new File("input.csv");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            String[] data = line.split(",");
            input.add(Double.parseDouble(data[i]));
            while (true) {
                line = reader.readLine();
                if (line == null){
                    break;
                }else {
                    data = line.split(",");
                    input.add(Double.parseDouble(data[i]));
                }
            }
            column.addAll(input.subList(0,input.size()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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
            Scanner sc = new Scanner(new File("input.csv"));
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
     * Writes the string in the file
     *
     * @param s the string which has to be written
     */
    private static void writeString(String s) {
        try (FileWriter writer = new FileWriter("output.txt")) {
            writer.write(s);
        } catch (IOException ex) {
        }
    }
}
