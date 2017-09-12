import java.util.ArrayList;

/**
 * Created by Petr on 23.02.2017.
 */
public class Check {
    private static ArrayList<Double> column;

    protected static boolean checkColumn(ArrayList<Double> data) {
        column = data;
        double difference, currentDif;
        boolean found = false;
        quickSort(0, column.size() - 1);
        difference = column.get(1) - column.get(0);
        for (int j = 0; j < column.size() - 1; j++) {
            if (column.get(j).equals(column.get(j+1))){
                found = false;
                break;
            }else{
                currentDif = column.get(j + 1) - column.get(j);
                if (isEqual(difference, currentDif)) {
                    found = true;
                } else {
                    found = false;
                    break;
                }
            }
        }
        return found;
    }

    /**
     * This method sorts given array.
     * @param start
     * @param end
     */
    private static void quickSort(int start, int end) {
        if (start >= end)
            return;
        int i = start, j = end;
        int cur = i - (i - j) / 2;
        while (i < j) {
            while (i < cur && (column.get(i) <= column.get(cur))) {
                i++;
            }
            while (j > cur && (column.get(cur) <= column.get(j))) {
                j--;
            }
            if (i < j) {
                swap(column, i, j);
                if (i == cur)
                    cur = j;
                else if (j == cur)
                    cur = i;
            }
        }
        quickSort(start, cur);
        quickSort(cur + 1, end);
    }

    /**
     * This method swaps tow elements in array.
     * @param array - given array.
     * @param x - index of first element.
     * @param y - index of second element.
     */
    private static void swap(ArrayList<Double> array, int x, int y) {

        double temp = array.get(x);
        array.set(x, array.get(y));
        array.set(y, temp);
    }

    /**
     * This method compares two numbers.
     * @param a - first number.
     * @param b - second number.
     * @return true/false.
     */
    private static boolean isEqual(double a, double b) {
        return (Math.abs(a - b) < 0.00000001);
    }
}
