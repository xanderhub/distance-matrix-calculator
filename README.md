# distance-matrix-calculator
 App that calculates distance matrix. <br />
 Distance defined between 2 points in 2D as euclidean distance: <br /> 
 https://www.cut-the-knot.org/pythagoras/DistanceFormula.shtml 

## Description
The main thread gets 2 inputs: List of points (x,y) and a number of threads (integer).
It splits the points into groups, calls the threads, and once all calculation is done, it responds with the calculated distance matrix.

Example: <br />
*calcDistanceMatrix([P1(x1,y1),P2(x2,y2),P3(x3,y3)], 10)*  <br />
returns all distances as follows:

![image](https://user-images.githubusercontent.com/33380175/69906544-83e0e980-13cd-11ea-9042-7df78a32e074.png)


## Implementation details

### Point2D
In order to define a point in 2D environment I've reused Java's [`Point2D`](https://docs.oracle.com/javase/8/javafx/api/javafx/geometry/Point2D.html) class from `javafx.geometry.Point2D` <br />
It has all properties needed and even has a method to calculate the distance between two points in 2D:

```
public double distance(double x1, double y1) {
        double a = getX() - x1;
        double b = getY() - y1;
        return Math.sqrt(a * a + b * b);
    }
```

### Distance matrix implementation

![image](https://user-images.githubusercontent.com/33380175/69906656-7af11780-13cf-11ea-8ce9-cf0dacaf0896.png)

There is no need to store the whole matrix in memory, because the pink area is basically a reflection of the green one. <br />
Since distance from __C__ to __B__ is the same as from __B__ to __C__ (= 4.95) we can store *dist(B,C)* only once and treat pairs of points <br />
(__B__ , __C__) or (__C__ , __B__) as one unique pair. <br />
Thre is also no need to store zero value distances between same points (yellow area). <br />

The data structure we can use here in order to store the green area is a *Map* where __key__ is defined by unique pair of points <br />
and __value__ - a distance between them:

```
{A,B} -> 0.71
{B,C} -> 4.95
{C,D} -> 2.24
...
```
class `PointsPair` represents unique pair - a key
In concurrent environment, where each pair of points can be calculated in different thread we must guarantee thread safety. <br />
For this we can use Java's `ConcurrentMap` (see `DistanceMatrix` class implementation)
