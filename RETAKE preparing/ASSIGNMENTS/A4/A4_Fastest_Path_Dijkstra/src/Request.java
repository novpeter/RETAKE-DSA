/**
 * Created by Petr on 19.04.2017.
 */
public class Request {
    private String start;
    private String destination;
    private double weight;

    public Request(String start, String destination, double weight) {
        this.start = start;
        this.destination = destination;
        this.weight = weight;
    }

    public String getStart() {
        return start;
    }

    public String getDestination() {
        return destination;
    }

    public double getWeight() {
        return weight;
    }
}
