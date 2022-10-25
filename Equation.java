public class Equation {
	private double[] coef;
	private double b;
	
	public Equation(double[] coef, double b) {
		this.coef = coef;
		this.b = b;
	}	
	
	public Equation clone() {
		return new Equation(this.coef.clone(), this.b);
	}
	
	@Override
	public String toString() {
		String str = new String("");
		for(Double x: this.coef) {
			str += x + " ";
		}
		str += "|" + this.b;
		return str;
	}
	
	public double[] getCoef() {
		return coef;
	}
	public void setCoef(double[] coef) {
		this.coef = coef;
	}
	public double getB() {
		return b;
	}
	public void setB(Double b) {
		this.b = b;
	}	
}
