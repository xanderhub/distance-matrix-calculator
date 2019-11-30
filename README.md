# distance-matrix-calculator
 App that calculates distance matrix. <br />
 Distance defined between 2 points in 2D as euclidean distance: <br /> 
 https://www.cut-the-knot.org/pythagoras/DistanceFormula.shtml 

## Description
The main thread gets 2 inputs: List of points (x,y) and a number of threads (integer).
It splits the points into groups, calls the threads, and once all calculation is done, it responds with the calculated distance matrix.

Example:
calcDistanceMatrix([P1(x1,y1),P2(x2,y2),P3(x3,y3)], 10)
returns all distances as follows:

![image](https://user-images.githubusercontent.com/33380175/69906544-83e0e980-13cd-11ea-9042-7df78a32e074.png)
