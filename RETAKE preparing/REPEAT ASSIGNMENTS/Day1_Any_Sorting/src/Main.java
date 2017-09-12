
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Created by petr on 10.08.17.
 */
public class Main {
    static long[] data;
    static String result = "";

    public static void main(String[] args) throws FileNotFoundException {
        readArray();
        mergeSort(data);
        transferResult();
        writeData(result);
    }

    private static void mergeSort(long[] array) {
        int size = array.length;
        if (size < 2) {
            return;
        }
        int mid = size/2;
        long[] left = new long[mid];
        long[] right = new long[size-mid];
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

    private static void merge(long[] left, long[] right, long[] array){
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


    private static void transferResult(){
        for (int i = 0; i < data.length; i++ ){
            result += String.valueOf(data[i]) + " ";
        }
    }

    private static void readArray() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input.txt"));
        if (sc.hasNextLine()) {
            String[] numbers = sc.nextLine().split(" ");
            data = new long[numbers.length];
            for (int i = 0; i < numbers.length; i++) {
                data[i] = Long.valueOf(numbers[i]);
            }
        }else{
            writeData("");}
    }

    private static void writeData(String result){
        try (FileWriter writer = new FileWriter("output.txt")) {
            writer.write(result);
        } catch (IOException ignored) {
        }
    }
    }

