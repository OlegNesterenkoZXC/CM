import java.util.Arrays;

public class Antialiasing {
	public static double[] antialiasing(Point[] points, int k, int m) {
		if(k <= 0 && k % 2 != 0) {
			return null;
		}
		
		int initialOffset = k / 2;
		
		int n = points.length;
		double[] answer = new double[n];
		answer[0] = points[0].getY();
		answer[n - 1] = points[n - 1].getY();
		int offset = initialOffset;
		int power = m;
		for(int i = 1; i < n - 1; ++i) {
			int kElemLeft = i;
			int kElemRight = n - 1 - i;
			
			if(kElemLeft < initialOffset || initialOffset > kElemRight) {
				offset = (kElemLeft < kElemRight ? kElemLeft : kElemRight);
				power = offset * 2;
				
			} else {
				offset = initialOffset;
				power = m;
			}
			
			Point[] cPoints = Arrays.copyOfRange(points, i - offset, i + offset + 1);
			double[] koefs = LSM.lsm(cPoints, power);
			
			//System.out.println(LSM.getPoints(cPoints));
			//System.out.println(points[i]);
			//System.out.println(calcPolynom(koefs, points[i].getX()));
			//System.out.println(LSM.getPolynom(koefs) + "\n");
			
			answer[i] = calcPolynom(koefs, points[i].getX());
			
		}
		return answer;
	}
	public static double calcPolynom(double[] koefs, double x) {
		double result = 0;
		for(int i = 0; i < koefs.length; ++i) {
			result += koefs[i] * Math.pow(x, i);
		}
		return result;
	}
}
