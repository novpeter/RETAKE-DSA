import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Petr on 22.03.2017.
 */
public class Main {
    public static void main(String[] args){
        writeString(determinePeriod());
    }

    /**
     * This method determines the period of given random function.
     * @return period
     */
    private static String determinePeriod(){
        AVLTree tree = new AVLTree();
        String seed = readString(1);
        if (!seed.isEmpty()){
            MyRandom random = new MyRandom(Long.parseLong(seed));
            int index = 0;
            while (true){
                double key = random.nextDouble();
                tree.root = tree.insert(tree.root, key, index);
                if (tree.equalKey){
                    return String.valueOf(index - tree.resultIndex);
                }
                index += 1;
            }
        }
        return "";

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
