import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    private static double ACCURACY = 0.0001;
    private static ArrayList<Double> data;
    private static boolean isFound = false;
    private static int nColumns = 0;

    public static void main(String[] args) {
        getSize();
        int result = checkColumns();
        writeResult(String.valueOf(result));
    }

    private static int checkColumns(){
        for (int i=0; i < nColumns; i++){
            data = getColumn(i);
            double[] tmp = copyData(data);
            mergeSort(tmp);
            checkDifference(tmp);
            if (isFound){
                return i;
            }
        }
        return 0;
    }

    private static void checkDifference(double[] tmp){
        double difference = (tmp[tmp.length-1] - tmp[0])/(tmp.length-1);
        for (int i = 0; i < tmp.length-1; i++){
            if (difference == 0){
                return;
            }else {
                double current = tmp[i + 1] - tmp[i];
                if ((difference - ACCURACY < current) && (difference + ACCURACY > current)) {
                    isFound = true;
                } else {
                    isFound = false;
                    break;
                }
            }
        }
    }

    private static double[] copyData(ArrayList<Double> list) {
        double[] tmp = new double[list.size()];
        for (int i = 0; i < list.size(); i++) {
            tmp[i] = list.get(i);
        }
        return tmp;
    }

    private static void mergeSort(double[] array) {
        int size = array.length;
        if (size < 2) {
            return;
        }
        int mid = size/2;
        double[] left = new double[mid];
        double[] right = new double[size-mid];
        for (int i=0; i<mid; i++){
            left[i] = array[i];
        }
        for (int i=mid; i<size; i++){
            right[i-mid] = array[i];
        }
        mergeSort(left);
        mergeSort(right);
        merge(left,right,array);
    }

    private static void merge(double[] left, double[] right, double[] array){
        int nL = left.length;
        int nR = right.length;
        int i = 0; //position of unpicked element in LEFT array
        int j = 0; //position of unpicked element in RIGHT array
        int k = 0; //position of free slot in array

        while (i<nL && j<nR){
            if(left[i] <= right[j]){
                array[k] = left[i];
                i += 1;
            }else{
                array[k] = right[j];
                j += 1;
            }
            k += 1;
        }
        while(i < nL){
            array[k] = left[i];
            i += 1;
            k += 1;
        }
        while(j < nR){
            array[k] = right[j];
            j += 1;
            k += 1;
        }
    }

    private static void getSize(){
        try {
            Scanner sc = new Scanner(new File("input.csv"));
            if (sc.hasNextLine()){
                nColumns = sc.nextLine().split(",").length;
            }

        } catch (FileNotFoundException ex) {
            System.out.println("File is not found!");
        }
    }

    private static ArrayList<Double> getColumn(int index){
        try {
            Scanner sc = new Scanner(new File("input.csv"));
            ArrayList<Double> tmp = new ArrayList<>();
            while (sc.hasNextLine()){
                tmp.add(Double.valueOf(sc.nextLine().split(",")[index]));
            }
            return tmp;
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

