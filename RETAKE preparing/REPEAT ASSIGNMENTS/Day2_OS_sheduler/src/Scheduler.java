import java.util.ArrayList;

public class Scheduler {
    private static MaxHeap heap;
    private static String lastApp;
    private static int currentTime;
    private static ArrayList<Process> execList;
    private static final int END = 120000;


    public static String getLastEvent(ArrayList<Process> processList) {
        currentTime = 0;
        heap = new MaxHeap(processList.size());
        execList = processList;
        if (execList.isEmpty()) {
            return "";
        } else {
            run();
            return lastApp;
        }
    }

    public static void run(){
        while ((currentTime <= END) && !(execList.isEmpty())){
            if (nextProcess()){
                while(!heap.isEmpty()){
                    Process process = heap.remove();
                    if (process.CPU_TIME_REQUIRED + currentTime <= END){
                        currentTime += process.CPU_TIME_REQUIRED;
                        lastApp = process.NAME;
                        execList.remove(process);
                    }else{
                        execList.remove(process);
                        break;
                    }
                }
            } else {
                currentTime = execList.get(0).EVENT;
            }
        }
    }

    private static boolean nextProcess() {
        for (Process process : execList) {
            if (process.EVENT <= currentTime) {
                heap.insert(process);
            }
        }
        return !heap.isEmpty();
    }

}
