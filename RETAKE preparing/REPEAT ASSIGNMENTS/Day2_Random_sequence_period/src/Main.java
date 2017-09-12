import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by petr on 11.08.17.
 */
public class Main {
    public static String seed;

    public static void main(String[] args) {
        readFile();
        String result = determinePeriod(seed);
        writeResult(result);
    }

    private static String determinePeriod(String seed){
        AVLTree tree = new AVLTree();
        if(seed.isEmpty()){
            return "";
        }else{
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
    }

    private static void readFile(){
        try{
            Scanner sc = new Scanner(new File("input.txt"));
            if(sc.hasNextInt()){
                seed = sc.nextLine();
            }
        }catch (FileNotFoundException ex){
            System.out.println("File not found");
        }
    }

    private static void writeResult(String result){
        try (FileWriter writer = new FileWriter("output.txt")) {
            writer.write(result);
        } catch (IOException ignored) {
        }
    }
}
