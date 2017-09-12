import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Petr on 20.02.2017.
 */
public class Main {
    private static int[] cost;
    private static int[] time;

    public static void main(String[] args) {
        String[] inputString = readString(1).split(" ");
        int budget = (int) (Double.parseDouble(readString(2)) * 100);
        time_cost(inputString);
        writeString(String.valueOf(knapsack(time, cost, budget)));
    }

    /**
     * This method fill two arrays (time and cost)
     *
     * @param input - given data with repeating pairs (time1 cost1...)
     */
    private static void time_cost(String[] input) {
        time = new int[input.length / 2];
        cost = new int[input.length / 2];
        for (int i = 0; i < input.length; i++) {
            if (i % 2 == 0) {
                time[i / 2] = timeConverter(input[i]);
            } else {
                cost[i / 2] = (int) (Double.parseDouble(input[i]) * 100);
            }
        }
    }

    /**
     * This method convert given time in more convinient way - minutes
     *
     * @param time
     * @return given time in minutes
     */
    private static int timeConverter(String time) {
        int minutes = 0;
        String[] hm = time.split(":");
        minutes = Integer.parseInt(hm[0]) * 60 + Integer.parseInt(hm[1]);
        return minutes;
    }

    /**
     * This method use the algorithm (Knapsack problem) to estimate how many minutes can bye Muzzy in best case.
     *
     * @param value (time)
     * @param weights (costs)
     * @param W (budget)
     * @return max possible number of minutes
     */
    private static int knapsack(int value[], int weights[], int W) {
        int items = weights.length;
        int[][] table = new int[items + 1][W + 1];
        for (int col = 0; col <= W; col++) {
            table[0][col] = 0;
        }
        for (int row = 0; row <= items; row++) {
            table[row][0] = 0;
        }
        for (int item = 1; item <= items; item++) {
            for (int weight = 1; weight <= W; weight++) {
                if (weights[item - 1] <= weight) {
                    table[item][weight] = Math.max(value[item - 1] + table[item - 1][weight - weights[item - 1]], table[item - 1][weight]);
                } else {
                    table[item][weight] = table[item - 1][weight];
                }
            }
        }
        return table[items][W];
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
