package bearmaps;
import java.util.List;

public class KDTree implements PointSet {

    private Node root;

    private class Node {
        private Point p;
        public boolean vertical;
        public Node left;

        public Node right;

        public Node(Point p, boolean vertical) {
            this.p = p;
            this.vertical = vertical;
        }

        public boolean isVertical() {
            return vertical;
        }

        public boolean lessInVertical(Point point) {
            return point.getX() < p.getX();
        }

        public boolean lessInHorizontal(Point point) {
            return point.getY() < p.getY();
        }

        public Point getPoint() {
            return p;
        }

        public double distanceTo(Point p) {
            return Point.distance(p, this.p);
        }

    }

    public KDTree(List<Point> points) {
        if (points.size() == 0) {
            return;
        }

        root = new Node(points.get(0), true);// Need some optimization

        for (Point p : points) {
            if (!p.equals(root.p)) {
                insert(p, root);
            }
        }
    }

    public void insert(Point p, Node n) {
        if ((n.isVertical() && n.lessInVertical(p)) || (!n.isVertical() && n.lessInHorizontal(p))) {
            if (n.left != null) {
                insert(p, n.left);
            } else {
                n.left = new Node(p, !n.vertical);
            }
        } else {
            if (n.right != null) {
                insert(p, n.right);
            } else {
                n.right = new Node(p, !n.vertical);
            }
        }
    }

    @Override
    public Point nearest(double x, double y) {
        Point goal = new Point(x, y);
        Node best = nearestRecur(root, goal, root);
        return best.getPoint();
    }

    private Node nearestRecur(Node n, Point goal, Node best) {
        if (n == null) {
            return best;
        }

        double bestDistance = best.distanceTo(goal);
        if (n.distanceTo(goal) < bestDistance) {
            best = n;
        }
        Node goodSide, badSide;
        if ((n.isVertical() && n.lessInVertical(goal)) || (!n.isVertical() && n.lessInHorizontal(goal))) {
            goodSide = n.left;
            badSide = n.right;
        } else {
            goodSide = n.right;
            badSide  = n.left;
        }

        best = nearestRecur(goodSide, goal, best);
        if (badSideUseful(n, goal, bestDistance)) {
            best = nearestRecur(badSide, goal, best);
        }
        return best;
    }

    private boolean badSideUseful(Node curNode, Point goal, double bestDistance) {
        if (curNode.isVertical()) {
            return bestDistance > (curNode.getPoint().getX() - goal.getX()) * (curNode.getPoint().getX() - goal.getX());
        } else {
            return bestDistance >  (curNode.getPoint().getY() - goal.getY()) * (curNode.getPoint().getY() - goal.getY());
        }
    }

    public static void main(String[] args) {
        Point p1 = new Point(2, 3);
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4, 5);
        Point p4 = new Point(3, 3);
        Point p5 = new Point(4, 4);
        Point p6 = new Point(1, 5);

        KDTree kd = new KDTree(List.of(p1, p2, p3, p4, p5, p6));
    }
}
