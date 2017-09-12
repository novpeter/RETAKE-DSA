import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Created by Petr on 05.02.2017.
 */
public class Main {

    public static void main(String[] args) {
        String[] points = readString(1).split(" ");
        MonteCarlo mc = new MonteCarlo();
        double result = mc.findArea(points);
        if(Math.abs(result-Math.round(result))<0.1){
            writeString(round(Math.round(result)));
        } else
            writeString(round(result));
    }

    /**
     * This method rounds the given number
     * @param d
     * @return Rounded string (result)
     */
    private static String round (double d) {
        DecimalFormat f = new DecimalFormat("#0.00");
        return f.format(d);
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
     * Writes the string in the file
     * @param s the string which has to be written
     */
    private static void writeString(String s) {
        try (FileWriter writer = new FileWriter("output.txt")) {
            writer.write(s);
        } catch (IOException ex) {
        }
    }
}
