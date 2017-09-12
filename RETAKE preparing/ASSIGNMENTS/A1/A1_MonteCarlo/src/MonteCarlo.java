import java.awt.geom.Point2D;
import java.text.DecimalFormat;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Petr on 05.02.2017.
 */
public class MonteCarlo {
    public static MyArrayList<Point> polygon = new MyArrayList<Point>();
    static double RightX, UpY, LeftX, DownY; //borders of limited frame
    static double frameArea; //area of the frame


    public double findArea(String[] points){
        fillArrayList(points);
        limitedFrame();
        return calcArea();
    }

    /**
     * This method calculate area of polygon using Monte Carlo algorithm.
     * @return area of polygon
     */
    private static double calcArea (){
        Point test = new Point(RightX,UpY);
        double counter = 0;
        int intersection = 0;
        double inside = 0;
        while (counter < 100000000){
            double x = ThreadLocalRandom.current().nextDouble(LeftX,RightX);
            double y = ThreadLocalRandom.current().nextDouble(DownY,UpY);
            Point randomPoint = new Point(x,y);
            for (int i = 0; i<polygon.size(); i++){
                if(i == polygon.size()-1){
                    if(intersects(polygon.get(0), polygon.get(i), test, randomPoint)) intersection ++;
                }else{
                    if(intersects(polygon.get(i), polygon.get(i+1), test, randomPoint)) intersection ++;
                }
            }
            if (intersection % 2 == 1) inside ++;
            intersection = 0;
            counter++;
        }
        double area;
        double outside = counter - inside;
        if (outside == 0){
            return frameArea;
        }else{
            area = frameArea * (inside / counter);
        }
        /*System.out.println(inside + " " + counter);
        System.out.println(area);*/

        return area;
    }

    private static double distance(double x1, double y1, double x2, double y2){
        return Math.sqrt( (x2-x1) * (x2-x1) + (y2-y1) * (y2-y1) );
    }

    /**
     * This method fills the ArrayList by splitting given array of strings.
     * @param points - given Array of strings.
     */
    private static void fillArrayList(String[] points){
        int j = 0;
        for (int i = 0; i<points.length; i+=2){
            double x = Double.valueOf(points[i]);
            double y = Double.valueOf(points[i+1]);
            Point point = new Point(x,y);
            polygon.add(j, point);
            j+=1;
        }
    }

    /**
     * This method estimate coordinates of frame
     */
    private static void limitedFrame(){
        UpY = polygon.get(0).getY();
        RightX = polygon.get(0).getX();
        DownY = polygon.get(0).getY();
        LeftX = polygon.get(0).getX();
        for (int i = 1; i < polygon.size(); i++) {
            if (polygon.get(i).getX() > RightX) {
                RightX = (polygon.get(i).getX());
            }
            if (polygon.get(i).getY() > UpY) {
                UpY = (polygon.get(i).getY());
            }
            if (polygon.get(i).getX() < LeftX) {
                LeftX = (polygon.get(i).getX());
            }
            if (polygon.get(i).getY() < DownY) {
                DownY = (polygon.get(i).getY());
            }
        }
        LeftX -= 0.1;
        RightX += 0.1;
        UpY += 0.1;
        DownY -=0.1;


        frameArea = distance(LeftX, DownY, LeftX, UpY) * distance(LeftX, DownY, RightX,DownY);
        /*System.out.println(LeftX + " " + RightX + " " + UpY + " " + DownY);
        System.out.println(frameArea);*/
    }

    /**
     * This method check intersection of two lines.
     * @param a
     * @param b
     * @param c
     * @param d
     * @return true/false;
     */
    private static boolean intersects(Point2D a, Point2D b, Point2D c, Point2D d) {
        // We describe the section AB as A+(B-A)*u and CD as C+(D-C)*v
        // then we solve A + (B-A)*u = C + (D-C)*v
        // let's use Kramer's rule to solve the task (Ax = B) were x = (u, v)^T
        // build a matrix for the equation
        double[][] A = new double[2][2];
        A[0][0] = b.getX() - a.getX();
        A[1][0] = b.getY() - a.getY();
        A[0][1] = c.getX() - d.getX();
        A[1][1] = c.getY() - d.getY();
        // calculate determinant
        double det0 = A[0][0] * A[1][1] - A[1][0] * A[0][1];
        // substitute columns and calculate determinants
        double detU = (c.getX() - a.getX()) * A[1][1] - (c.getY() - a.getY()) * A[0][1];
        double detV = A[0][0] * (c.getY() - a.getY()) - A[1][0] * (c.getX() - a.getX());
        // calculate the solution
        // even if det0 == 0 (they are parallel) this will return NaN and comparison will fail -> false
        double u = detU / det0;
        double v = detV / det0;
        return u > 0 && u < 1 && v > 0 && v < 1;
    }

}
