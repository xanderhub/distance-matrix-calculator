import javafx.geometry.Point2D;

public final class PointsPair {
    private final Point2D point1;
    private final Point2D point2;

    public PointsPair(Point2D point1, Point2D point2) {
        this.point1 = point1;
        this.point2 = point2;
    }

    public Point2D getPoint1() {
        return point1;
    }

    public Point2D getPoint2() {
        return point2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PointsPair that = (PointsPair) o;

        if(point1 == null || point2 == null || that.point1 == null || that.point2 == null)
            return false;

        if(point1.equals(that.point1))
            return point2.equals(that.point2);

        else if(point1.equals(that.point2))
            return point2.equals(that.point1);
        else
            return false;
    }

    @Override
    public int hashCode() {
        int result = point1 != null ? point1.hashCode() + (point2 != null ? point2.hashCode() : 0) : 0;
        result = 31 * result;
        return result;
    }
}
