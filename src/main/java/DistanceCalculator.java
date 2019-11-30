import javafx.geometry.Point2D;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class DistanceCalculator {
    private ThreadPoolExecutor executor;
    private final int DEFAULT_NUM_OF_THREADS = 1;

    public DistanceCalculator() {
        this.executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(DEFAULT_NUM_OF_THREADS);
    }

    public DistanceMatrix calcDistanceMatrix(List<Point2D> points) {
        return calcDistanceMatrix(points, DEFAULT_NUM_OF_THREADS);
    }

    public DistanceMatrix calcDistanceMatrix(List<Point2D> points, int threads) {
        if(points == null || points.size() < 2 || threads < 1)
            throw new IllegalArgumentException();

        CountDownLatch latch = new CountDownLatch(numOfUniquePairs(points.size()));
        DistanceMatrix matrix = new DistanceMatrix();

        prepareExecutor(threads);

        for(int i = 0; i < points.size() - 1; i++) {
            for (int j = i + 1; j < points.size(); j++) {
                executor.submit(new CalcTask(points.get(i), points.get(j), matrix, latch));
            }
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return matrix;
    }

    private int numOfUniquePairs(int n) {
        return n*(n-1)/2;
    }

    private void prepareExecutor(int numOfThreads) {
        executor.setCorePoolSize(numOfThreads);
        executor.setMaximumPoolSize(numOfThreads);
    }
}
