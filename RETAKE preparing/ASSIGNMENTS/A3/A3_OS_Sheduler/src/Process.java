/**
 * Created by Petr on 23.03.2017.
 */
public class Process {
    private String name;
    private int event_time;
    private int CPU_time;
    private int priority;

    protected Process(String name, int event_time, int CPU_time, int priority){
        this.name = name;
        this.event_time = event_time;
        this.CPU_time = CPU_time;
        this.priority = priority;
    }

    protected String getName(){
        return name;
    }

    protected int getEventTime(){
        return event_time;
    }

    protected int getCPU_time(){
        return CPU_time;
    }

    protected int getPriority(){
        return priority;
    }
}
