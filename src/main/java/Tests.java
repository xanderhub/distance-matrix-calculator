//import java.awt.geom.Point2D;

import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;

public class Tests {

    public static void main(String[] args) throws InterruptedException {

        List<Point2D> points = new ArrayList<>();

        Point2D a = new Point2D(0, 0);
        Point2D b = new Point2D(4.5, 1.5);
        Point2D c = new Point2D(-12, 2.2);
        Point2D d = new Point2D(-1, -1);
        Point2D e = new Point2D(20, 15);
        Point2D f = new Point2D(-12, -3);


        points.add(a);
        points.add(b);
        points.add(c);
        points.add(d);
        points.add(e);
        points.add(f);



//        Point2D point4 = new Point2D(11, 5);

//        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
//        executor.setCorePoolSize(5);
//        executor.setMaximumPoolSize(5);
//        Thread.sleep(1000);
//        System.out.println(executor.getPoolSize());
//        System.out.println(executor.getCorePoolSize());

        DistanceCalculator calculator = new DistanceCalculator();
        DistanceMatrix matrix = calculator.calcDistanceMatrix(points, 1);

        System.out.println(matrix.getDistance(a,c));
        System.out.println(matrix.getDistance(a,d));
        System.out.println(matrix.getDistance(a,e));
        System.out.println(matrix.getDistance(a,f));
        System.out.println(matrix.getDistance(b,c));
        System.out.println(matrix.getDistance(b,d));
        System.out.println(matrix.getDistance(b,e));
        System.out.println(matrix.getDistance(b,f));
        System.out.println(matrix.getDistance(c,d));
        System.out.println(matrix.getDistance(c,e));
        System.out.println(matrix.getDistance(c,f));
        System.out.println(matrix.getDistance(d,e));
        System.out.println(matrix.getDistance(d,f));
        System.out.println(matrix.getDistance(e,f));

        //matrix.printMatrix();

//        System.out.println("------------------------------------ALL POINTS----------------------------------------");
//        System.out.println("p1 -> p2 : " + matrix.getDistance(point1, point2));
//        System.out.println("p1 -> p3 : " + matrix.getDistance(point1, point3));
//        System.out.println("p1 -> p4 : " + matrix.getDistance(point1, point4));
//        System.out.println("p2 -> p3 : " + matrix.getDistance(point2, point3));
//        System.out.println("p2 -> p4 : " + matrix.getDistance(point2, point4));
//        System.out.println("p3 -> p4 : " + matrix.getDistance(point3, point4));
//        System.out.println("------------------------------------CASES----------------------------------------");
//        System.out.println("p2 -> p1 : " + matrix.getDistance(point2, point1));
//        System.out.println("p2 -> p2 : " + matrix.getDistance(point2, point2));
//        System.out.println("p3 -> p3 : " + matrix.getDistance(point3, point3));
//        System.out.println("p4 -> p4 : " + matrix.getDistance(point4, point4));


    }


}