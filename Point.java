import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Objects;

public class Point {
	private double x = 0;
	private double y = 0;
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public static Point[] getArrayPoints(double ... ds) {
		int n = ds.length;
		if(n % 2 != 0 || n <= 0) {
			return null;
		}
		
		Point[] points = new Point[n];
		
		int j = 0;
		for(int i = 0; i < n; ++i, j += 2) {
			points[i] = new Point(ds[j], ds[j + 1]);
		}
		return points;
	}

	public static String toStringPoints(Point[] points) {
		if(points.length <= 0) {
			return "";
		}
		String str = points[0].toString();
		for(int i = 1; i < points.length; ++i) {
			str += ", " + points[i].toString();
		}
		return str;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}



	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null || this.getClass() != obj.getClass()) return false;
		Point p = (Point) obj;
		return (this.x == p.getX() && this.y == p.getY());
	}



	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new Point(this.x, this.y);
	}



	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("#.###");
		df.setRoundingMode(RoundingMode.CEILING);
		return "(" + df.format(this.x) +", " + df.format(this.y) + ")";
	}
	

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	
}
