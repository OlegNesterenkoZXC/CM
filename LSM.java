
public class LSM {
	public static double[] lsm(double[] taskX, double[] taskY, int m, int N) {
		//if(m > N + 1) {
		//	return null;
		//}
		
		int n = N - 1;
		
		double[][] matrix = new double[m + 1][m + 1];
		double[] b = new double[m + 1];
		
		for(int k = 0; k <= m; ++k) {
			for(int l = 0; l <= m; ++l) {
				double temp = 0;
				for(int i = 0; i <= n; ++i) {
					temp += Math.pow(taskX[i], k + l);
				}
				matrix[k][l] = temp;
			}
			double temp = 0;
			for(int i = 0; i <= n; ++i) {
				temp += Math.pow(taskX[i], k) * taskY[i]; 
			}
			b[k] = temp;
		}
		Slau sl = new Slau(matrix, b);
		System.out.println(sl + "\n");
		sl = sl.gauss();
		System.out.println(sl);
		return sl.getB();
	}
	
	public static double[] lsm(Point[] points, int maxP) {
		int n = points.length;
		int m = maxP + 1;
		
		double[][] matrix = new double[m][m];
		double[] b = new double[m];
		
		for(int k = 0; k < m; ++k) {
			for(int l = 0; l < m; ++l) {
				double temp = 0;
				for(int i = 0; i < n; ++i) {
					temp += Math.pow(points[i].getX(), k + l);
				}
				matrix[k][l] = temp;
			}
			double temp = 0;
			for(int i = 0; i < n; ++i) {
				temp += Math.pow(points[i].getX(), k) * points[i].getY(); 
			}
			b[k] = temp;
		}
		Slau sl = new Slau(matrix, b);
		System.out.println(sl + "\n");
		sl = sl.gauss();
		//System.out.println(sl == null);
		return sl.getB();
	}
	public static String getPolynom(double[] koef) {
		String str = "";
		
		if(koef == null || koef.length <= 0) {
			return str;
		}
		
		str += (koef[0] == 0 ? "": (koef[0] + " "));
		for(int i = 1; i < koef.length; ++i) {
			if(koef[i] != 0) {
				str += (koef[i] < 0 ? "": " +") + koef[i] + "x^" + i + " ";
			}
		}
		return str;
	}
}
