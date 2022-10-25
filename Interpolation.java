
public class Interpolation {
	public static double[] interpolation(Point[] points) {
		if(points.length != 3) {
			return null;
		}
		double[][] matrix = new double[3][3];
		double[] b = new double[3];
		for(int i = 0; i < 3; ++i) {
			double xi = points[i].getX();
			double yi = points[i].getY();
			
			matrix[i][0] = Math.pow(xi, 2);
			matrix[i][1] = xi;
			matrix[i][2] = 1;
			
			b[i] = yi;
		}		
		Slau sl = new Slau(matrix, b);

		//System.out.println(sl);
		
		
		double[] answer = sl.gauss().getB();
		reverse(answer);
		return answer;
	}
	private static void reverse(double[] array) {
		int n = array.length;
		for(int i = 0; i < n / 2; ++i) {
			double temp = array[i];
			array[i] = array[n - 1 - i];
			array[n - 1 - i] = temp;
		}
	}
}
