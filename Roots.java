import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Roots {
	public static List<Double> getRootsOfFunction(double a, double b, int k, double eps) {		
		if(a > b) {
			double temp = a;
			a = b;
			b = temp;
		}
		
		Point[] points = Input.getPointsOfFunction(a, b, k);
		System.out.println(Point.toStringPoints(points));
		List<Double> roots = getRootsOfFunction(points);
		
		Point[] pointsdf = Input.getPointsOfDf(a, b, k);
		//System.out.println(Point.toStringPoints(pointsdf));
		List<Double> rootsOfdf = getRootsOfFunction(pointsdf);
		
		//int kr = 0;
		ListIterator<Double> it = roots.listIterator();
		while(it.hasNext()) {
			//kr++;
			double root = it.next();
			if(Math.abs(Input.f(root)) >= eps ) {
				it.remove();
			}
		}
		//System.out.println(kr + "\n" + roots);
		
		it = rootsOfdf.listIterator();
		while(it.hasNext()) {
			double rootdf = it.next();
			if(Math.abs(Input.f(rootdf)) < eps) {
				ListIterator<Double> itRoots = roots.listIterator();
				while(itRoots.hasNext()) {
					double temp = itRoots.next();
					if(temp > rootdf) {
						if(itRoots.hasPrevious()) {
							itRoots.previous();
							itRoots.add(rootdf);
						} else {
							roots.add(0, rootdf);
						}
						break;
					}
				}
				if(!itRoots.hasNext()) {
					roots.add(rootdf);
				}
			}
		}
		return roots;
	}
	
	private static List<Double> getRootsOfFunction(Point[] points) {		
		List<Double> answer = new LinkedList<Double>();
		
		int n = points.length;
		for(int i = 0; i < n - 2; ++i) {
			double x1 = points[i].getX();
			double y1 = points[i].getY();
			double x2 = points[i + 1].getX();
			double y2 = points[i + 1].getY();
			double x3 = points[i + 2].getX();
			double y3 = points[i + 2].getY();
			
			if(y1 == 0 || y2 == 0 || y3 == 0) {
				if(y1 == 0) answer.add(x1);
				boolean islastThreePoints = (i == n - 3);
				if(islastThreePoints && y2 == 0) answer.add(x2);
				if(islastThreePoints && y3 == 0) answer.add(x3);
				
				continue;
			}
			
			double[] roots;
			if(Roots.isDifBySign(y1, y2)) {
				Point[] pointsForInterp = Arrays.copyOfRange(points, i, i + 3);
				double[] koef = Interpolation.interpolation(pointsForInterp); 
				
				//System.out.println(LSM.getPolynom(koef));
				
				if(koef[2] == 0) {
					double[] linearKoef = Arrays.copyOfRange(koef, 0, koef.length - 1);
					double root = Roots.getRootOfLinear(linearKoef);
					roots = new double[1];
					roots[0] = root;
				} else {
					roots = Roots.getRootsOfParabola(koef);
				}
				for(double root: roots) {					
					boolean isBetween12 = x1 < root && root < x2;
					boolean isBetween23 = x2 < root && root < x3;
					if(isBetween12 || (i == n - 3 && isBetween23)) {
						answer.add(root);
					}
				}
			}			
		}		
		return answer;
	}
	private static double getRootOfLinear(double[] koef) {
		if(koef == null || koef.length != 2) {
			return Double.NaN;
		}
		// a + b*x
		// a*x + b
		double a = koef[1];
		double b = koef[0];
		return -b / a;
	}
	private static double[] getRootsOfParabola(double[] koef) {
		if(koef == null || koef.length != 3) {
			return null;
		}
		//a + b*x + c*x^2
		//a*x^2 + b*x + c
		double a = koef[2];
		double b = koef[1];
		double c = koef[0];
		
		double d = b * b - 4 * a * c;
		double[] answer;
		if(d < 0) {
			answer = new double[0];
		} else if(d == 0) {
			answer = new double[1];
			answer[0] =  -b / (2*a);
		} else {
			answer = new double[2];
			answer[0] = (-b - Math.sqrt(d)) / (2 * a);
			answer[1] = (-b + Math.sqrt(d)) / (2 * a);
		}
		return answer;
	}
	private static boolean isDifBySign(double a, double b) {
		return (a < 0 && b > 0 || a > 0 && b < 0);
	}
	public static void printRoots(double[] roots) {
		if(roots.length == 0) {
			System.out.println("Вещественных корней нет");
		}
		for(int i = 0; i < roots.length; ++i) {
			System.out.println("x" + (i + 1) + " = " + roots[i]);
		}
	}
	public static void printRoots(List<Double> roots) {
		int n = roots.size();
		if(n == 0) {
			System.out.println("Вещественных корней нет");
		}
		for(int i = 0; i < n; ++i) {
			System.out.println("x" + (i + 1) + " = " + roots.get(i));
		}
	}
}
