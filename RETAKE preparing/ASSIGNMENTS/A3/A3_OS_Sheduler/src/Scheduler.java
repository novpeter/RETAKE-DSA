import java.util.ArrayList;

/**
 * Created by Petr on 23.03.2017.
 */
public class Scheduler {
    private static MaxHeap heap;
    private static ArrayList<Process> exList; //executing list of processes
    private static int time;
    private static String lastProcess;

    /**
     * This method returns last process which was executed.
     * @param processList - given list of processes
     * @return name of last process
     */
    public static String getLastEvent(ArrayList<Process> processList) {
        time = 0;
        heap = new MaxHeap(processList.size());
        exList = processList;
        if (exList.isEmpty()) {
            return "";
        } else {
            runProcesses();
            return lastProcess;
        }
    }

    /**
     * This method runs processes which can be executed in 2 minutes.
     */
    private static void runProcesses() {
        while ((time <= 120000) && !(exList.isEmpty())) {
            if (nextProcess()) {
                while (!heap.isEmpty()) {
                    Process process = heap.getMax();
                    if (process.getCPU_time() + time <= 120000) {
                        time += process.getCPU_time();
                        lastProcess = process.getName();
                        exList.remove(process);
                    } else {
                        exList.remove(process);
                        break;
                    }
                }
            } else {
                time = exList.get(0).getEventTime();
            }
        }
    }

    /**
     * This method checks if there are any processes which can be executed in current time.
     * If it found such processes, it inserts them in heap.
     * @return boolean value
     */
    private static boolean nextProcess() {
        for (Process process : exList) {
            if (process.getEventTime() <= time) {
                heap.insert(process);
            }
        }
        return !heap.isEmpty();
    }
}
