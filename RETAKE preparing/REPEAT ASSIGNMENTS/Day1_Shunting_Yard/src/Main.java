import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) {
        Calculator calc = new Calculator(readFile());
        writeResult(calc.calculate());

    }

    private static String readString(){
        try {
            Scanner sc = new Scanner(new File("input.txt"));
            return sc.nextLine();
        } catch (FileNotFoundException ex) {
            return null;
        }
    }


    private static StringTokenizer readFile(){
        try {
            Scanner sc = new Scanner(new File("input.txt"));
            return new StringTokenizer(sc.nextLine(), "+-*/() ", true);
        } catch (FileNotFoundException ex) {
            return null;
        }
    }

    private static void writeResult(String result){
        try (FileWriter writer = new FileWriter("output.txt")) {
            writer.write(result);
        } catch (IOException ignored) {
        }
    }
}
