import javafx.geometry.Point2D;

import java.util.concurrent.CountDownLatch;

public class CalcTask implements Runnable {

    private final Point2D p1;
    private final Point2D p2;
    private final DistanceMatrix results;
    private final CountDownLatch latch;

    public CalcTask(Point2D p1, Point2D p2, DistanceMatrix distanceMatrix, CountDownLatch latch) {
        this.p1 = p1;
        this.p2 = p2;
        this.results = distanceMatrix;
        this.latch = latch;
    }

    @Override
    public void run() {
        Double distance = p1.distance(p2);
        results.setDistance(p1, p2, distance);
        latch.countDown();
    }
}

