import java.awt.geom.Point2D;
import java.nio.channels.Pipe;

/**
 * Created by Petr on 05.02.2017.
 */
public class Point extends Point2D {
    public double x = 0.0;
    public double y = 0.0;

    public Point( double x, double y){
        this.x = x;
        this.y = y;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void setLocation(double x, double y) {

    }
}
