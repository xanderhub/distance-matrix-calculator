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
class `PointsPair` represents unique pair - a key <br />
<br />
In concurrent environment, where each pair of points can be calculated in different thread we must guarantee thread safety.
For this we can use Java's `ConcurrentMap` (see `DistanceMatrix` class implementation)

### Calculation Task
`CalcTask` is a simple *Runnable* task defined to calculatethe distance between two points (Point2D)
It gets these two points, a reference to distance matrix provided by main thread and `CountDownLatch` for synchronization.
Once this task calculates the distance it stores it in the distance matrix (*PointsPair* - as a key and *distance* - as a value)

### Main Thread
`DistanceCalculator` class has API method `calcDistanceMatrix()`, which can be called with defined amount of threads to run.
An instance of DistanceCalculator utilizes a [ThreadPoolExecutor](https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/ThreadPoolExecutor.html) which initialized with fixed amount of threads.
But it can be changed by calling `calcDistanceMatrix()` with differnt amount of threads to execute. Once this method called, it starts to loop through the list of points, create unique pairs from these points, wraps them as `CalcTask`s and submits them to the *ThreadPoolExecutor*. Tasks that can't be processeed right away will be waiting in the queue, that comes with the Executor:

![image](https://user-images.githubusercontent.com/33380175/69907561-b72c7400-13df-11ea-894c-0b7cd6fc353c.png)

### Synchronization
Since main thread returns a result to the client (distance matrix) it needs to know when it can return it, i.e. when all calculation tasks finished. One of the ways to let the main thread know that all its child threads has finished is by using `CountDownLatch` mechanism (https://www.baeldung.com/java-countdown-latch)
