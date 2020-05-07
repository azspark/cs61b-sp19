package bearmaps;

import org.junit.Test;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import edu.princeton.cs.algs4.Stopwatch;

import static org.junit.Assert.assertEquals;

public class KDTreeTest {

    @Test
    public void randomnizedTest() {
        Random r = new Random(23);
        List<Point> ps = generateRandomPoint(1000, r);

        NaivePointSet nPS = new NaivePointSet(ps);
        KDTree kPS = new KDTree(ps);

        for (int i = 0; i < 100; i++) {
            double x = 3 * r.nextDouble();
            double y = 3 * r.nextDouble();
            assertEquals(kPS.nearest(x, y), kPS.nearest(x, y));
        }
    }

    public List<Point> generateRandomPoint(int number, Random r) {
        List<Point> ps = new LinkedList<>();

        for (int i = 0; i < number; i++) {
            ps.add(new Point(3 * r.nextDouble(), 3 * r.nextDouble()));
        }
        return ps;
    }

    @Test
    public void kdTreeTimeCompareWithNaivePointSet() {
        Random r = new Random(25);
        List<Point> ps = generateRandomPoint(100000, r);
        List<Point> ts = generateRandomPoint(10000, r);

        KDTree kdTS = new KDTree(ps);
        NaivePointSet nPS = new NaivePointSet(ps);

        Stopwatch sw = new Stopwatch();
        for (Point p : ts) {
            kdTS.nearest(p.getX(), p.getY());
        }
        double kdTreeTime = sw.elapsedTime();
        System.out.println("KDTree total time elapsed: " + kdTreeTime +  " seconds.");

        sw = new Stopwatch();
        for (Point p : ts) {
            nPS.nearest(p.getX(), p.getY());
        }
        double navieTime = sw.elapsedTime();
        System.out.println("Naive approach total time elapsed: " + navieTime +  " seconds.");

        System.out.printf("KDTree is %.1f times faster", navieTime / kdTreeTime);
    }
}
