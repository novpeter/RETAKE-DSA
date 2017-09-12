public class Process {
    public String NAME;
    public int EVENT;
    public int CPU_TIME_REQUIRED;
    public int PRIORITY;

    public Process(String NAME, int EVENT, int CPU_TIME_REQUIRED, int PRIORITY) {
        this.NAME = NAME;
        this.EVENT = EVENT;
        this.CPU_TIME_REQUIRED = CPU_TIME_REQUIRED;
        this.PRIORITY = PRIORITY;
    }
}
