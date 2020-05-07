package bearmaps;

import java.util.List;
import java.util.ArrayList;

public class NaivePointSet implements PointSet{
    private List<Point> points;
    public NaivePointSet(List<Point> points) {
        this.points = points;
    }

    @Override
    public Point nearest(double x, double y) {
        double nearestDistance = Double.MAX_VALUE;
        Point nearestPoint = null;
        Point thisPoint = new Point(x, y);
        for (Point p : points) {
            double curDistance = Point.distance(thisPoint, p);
            if (curDistance < nearestDistance) {
                nearestDistance = curDistance;
                nearestPoint = p;
            }
        }
        return nearestPoint;
    }

    public static void main(String[] args) {
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        NaivePointSet nn = new NaivePointSet(List.of(p1, p2, p3));
        Point ret = nn.nearest(3.0, 4.0); // returns p2
        ret.getX(); // evaluates to 3.3
        ret.getY(); // evaluates to 4.4
        System.out.println(""+ret.getX() + " " + ret.getY());
    }
}
