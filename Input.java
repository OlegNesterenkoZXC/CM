import java.util.Scanner;

public class Input {
	public static Point[] inputArray(int n) {
		Scanner sc = new Scanner(System.in);
		Point[] points = new Point[n];
		for(int i = 0; i < n; ++i) {
			points[i] = new Point(sc.nextDouble(), 0);
		}
		for(int i = 0; i < n; ++i) {
			points[i].setY(sc.nextDouble());
		}
		sc.close();
		return points;
	}
	public static Point[] getArrayPoints(double[] arrayX, double[] arrayY) {
		if(arrayX.length != arrayY.length) {
			return null;
		}
		
		int n = arrayX.length;
		
		Point[] points = new Point[n];
		for(int i = 0; i < n; ++i) {
			points[i] = new Point(arrayX[i], arrayY[i]);
		}
		return points;
	}
	public static Point[] getPointsOfFunction(double a, double b, int n) {
		Point[] points = new Point[n];
		
		double offset = (b - a) / (n - 1);
		double x = a;
		for(int i = 0; i < n; ++i) {
			points[i] = new Point(x, f(x));
			x += offset;
		}
		return points;
	}
	public static Point[] getPointsOfDf(double a, double b, int n) {
		Point[] points = new Point[n];
		
		double offset = (b - a) / (n - 1);
		double x = a;
		for(int i = 0; i < n; ++i) {
			points[i] = new Point(x, df(x));
			x += offset;
		}
		return points;
	}
	public static double f(double x) {
		return Math.pow(x, 5.0) - 5 * Math.pow(x, 3.0) + 4 * x + 1.419;
	}
	public static double df(double x) {
		return 5*Math.pow(x, 4.0) - 15 * Math.pow(x, 2.0) + 4;
	}
}
