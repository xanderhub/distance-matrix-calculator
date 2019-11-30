import javafx.geometry.Point2D;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DistanceCalculatorTest {

    private DistanceCalculator calculator = new DistanceCalculator();

    private Point2D a = new Point2D(0, 0);
    private Point2D b = new Point2D(4.5, 1.5);
    private Point2D c = new Point2D(-12, 2.2);
    private Point2D d = new Point2D(-1, -1);
    private Point2D e = new Point2D(20, 15);
    private Point2D f = new Point2D(-12, -3);

    private List<Point2D> shortList = new ArrayList<>(Arrays.asList(a));
    private List<Point2D> sampleList = new ArrayList<>(Arrays.asList(a,b,c,d,e,f));

    private DistanceMatrix singleThreadResult = calculator.calcDistanceMatrix(sampleList);
    private DistanceMatrix dualThreadsResult = calculator.calcDistanceMatrix(sampleList, 2);
    private DistanceMatrix tripleThreadsResult = calculator.calcDistanceMatrix(sampleList, 3);
    private DistanceMatrix multipleThreadsResult1 = calculator.calcDistanceMatrix(sampleList, 10);
    private DistanceMatrix multipleThreadsResult2 = calculator.calcDistanceMatrix(sampleList, 15);
    private DistanceMatrix multipleThreadsResult3 = calculator.calcDistanceMatrix(sampleList, 30);

    private List<DistanceMatrix> results = new ArrayList<>(Arrays.asList(
            singleThreadResult,
            dualThreadsResult,
            tripleThreadsResult,
            multipleThreadsResult1,
            multipleThreadsResult2,
            multipleThreadsResult3));

    @Test
    public void printResultsTest() {
        singleThreadResult.printMatrix();
    }

    @Test
    public void symmetricRelationTest() {
        for (DistanceMatrix result : results){
            Assert.assertEquals(result.getDistance(a, b), result.getDistance(b, a), 0.000000000001);
            Assert.assertEquals(result.getDistance(a, c), result.getDistance(c, a), 0.000000000001);
            Assert.assertEquals(result.getDistance(a, d), result.getDistance(d, a), 0.000000000001);
            Assert.assertEquals(result.getDistance(a, e), result.getDistance(e, a), 0.000000000001);
            Assert.assertEquals(result.getDistance(a, f), result.getDistance(f, a), 0.000000000001);
            Assert.assertEquals(result.getDistance(b, c), result.getDistance(c, b), 0.000000000001);
            Assert.assertEquals(result.getDistance(b, d), result.getDistance(d, b), 0.000000000001);
            Assert.assertEquals(result.getDistance(b, e), result.getDistance(e, b), 0.000000000001);
            Assert.assertEquals(result.getDistance(b, f), result.getDistance(f, b), 0.000000000001);
            Assert.assertEquals(result.getDistance(c, d), result.getDistance(d, c), 0.000000000001);
            Assert.assertEquals(result.getDistance(c, e), result.getDistance(e, c), 0.000000000001);
            Assert.assertEquals(result.getDistance(c, f), result.getDistance(f, c), 0.000000000001);
            Assert.assertEquals(result.getDistance(d, e), result.getDistance(e, d), 0.000000000001);
            Assert.assertEquals(result.getDistance(d, f), result.getDistance(f, d), 0.000000000001);
            Assert.assertEquals(result.getDistance(e, f), result.getDistance(f, e), 0.000000000001);

        }
    }

    @Test
    public void reflexiveRelationTest() {
        for (DistanceMatrix result : results) {
            Assert.assertEquals(result.getDistance(a, a), 0.0, 0.000000000001);
            Assert.assertEquals(result.getDistance(b, b), 0.0, 0.000000000001);
            Assert.assertEquals(result.getDistance(c, c), 0.0, 0.000000000001);
            Assert.assertEquals(result.getDistance(d, d), 0.0, 0.000000000001);
            Assert.assertEquals(result.getDistance(e, e), 0.0, 0.000000000001);
            Assert.assertEquals(result.getDistance(f, f), 0.0, 0.000000000001);

        }
    }

    @Test
    public void matrixDistancesTest() {
        for (DistanceMatrix result : results) {
            Assert.assertEquals(result.getDistance(a, b), 4.743416490252569, 0.000000000001);
            Assert.assertEquals(result.getDistance(a, c), 12.2, 0.000000000001);
            Assert.assertEquals(result.getDistance(a, d), 1.4142135623730951, 0.000000000001);
            Assert.assertEquals(result.getDistance(a, e), 25.0, 0.000000000001);
            Assert.assertEquals(result.getDistance(a, f), 12.36931687685298, 0.000000000001);
            Assert.assertEquals(result.getDistance(b, c), 16.514841809717705, 0.000000000001);
            Assert.assertEquals(result.getDistance(b, d), 6.041522986797286, 0.000000000001);
            Assert.assertEquals(result.getDistance(b, e), 20.554804791094465, 0.000000000001);
            Assert.assertEquals(result.getDistance(b, f), 17.10263137648707, 0.000000000001);
            Assert.assertEquals(result.getDistance(c, d), 11.456002793295749, 0.000000000001);
            Assert.assertEquals(result.getDistance(c, e), 34.46505476566083, 0.000000000001);
            Assert.assertEquals(result.getDistance(c, f), 5.2, 0.000000000001);
            Assert.assertEquals(result.getDistance(d, e), 26.40075756488817, 0.000000000001);
            Assert.assertEquals(result.getDistance(d, f), 11.180339887498949, 0.000000000001);
            Assert.assertEquals(result.getDistance(e, f), 36.71511950137164, 0.000000000001);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void malformedInputTest_onePointOnly() {
        calculator.calcDistanceMatrix(shortList);
    }

    @Test(expected = IllegalArgumentException.class)
    public void malformedInputTest_emptyList() {
        calculator.calcDistanceMatrix(new ArrayList<>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void malformedInputTest_wrongNumOfThreads() {
        calculator.calcDistanceMatrix(sampleList, -1);
    }
}
