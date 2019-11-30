import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javafx.geometry.Point2D;

public class DistanceMatrix {

    private final ConcurrentMap<PointsPair, Double> distances = new ConcurrentHashMap<>();

    public void setDistance(Point2D p1, Point2D p2, Double distance) {
        distances.put(new PointsPair(p1, p2), distance);
    }

    public double getDistance(Point2D p1, Point2D p2) {
        if(p1.equals(p2))
            return 0L;
        return distances.get(new PointsPair(p1, p2));
    }

    public void printMatrix() {
        if(distances.keySet().isEmpty())
            System.out.println("Result matrix is empty");

        Set<Point2D> points = new HashSet<>();
        for (PointsPair pair: distances.keySet()) {
            points.add(pair.getPoint1());
            points.add(pair.getPoint2());
        }

        Point2D[] arr = new Point2D[points.size()];
        arr = points.toArray(arr);

        for(int y = -1; y < arr.length; y++) {
            for (int x = -1; x < arr.length; x++) {
                if (y != -1) {
                    if (x != -1)
                        System.out.printf("%16.2f", getDistance(arr[x], arr[y]));
                    else
                        System.out.printf("%-14s","["+arr[y].getX()+","+arr[y].getY()+"]");
                }
                else if (x == -1)
                    System.out.printf("                      ");
                else
                    System.out.printf("%-16s","["+arr[x].getX()+","+arr[x].getY()+"]");


            }
            System.out.println();
        }

    }
}
