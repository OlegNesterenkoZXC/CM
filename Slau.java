import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Slau {
	List<Equation> equations;
	private static double ERROR = 1.0E-20;

	public Slau() {
		this.equations = new ArrayList<Equation>();
	}
	public Slau(List<Equation> sl) {
		this.equations = sl;
	}
	public Slau(Equation[] sl) {
		this.equations = Arrays.asList(sl);
	}
	public Slau(double[][] arr, double[] b) {
		this.equations = new ArrayList<Equation>();
		for(int i = 0; i < arr.length && i < b.length; ++i) {
			this.equations.add(new Equation(arr[i], b[i]));
		}
	}
	
	public Slau gaussV1() {
		
		Slau slau = this.clone();
		List<Equation> equations = slau.getEquations();
		
		int n = equations.size();
		
		for(int i = 0; i < n - 1; ++i) {
			for(int j = i + 1; j < n; ++j) {
				if(equations.get(i).getCoef()[i] == 0) {
					int i1 = findNotNullStr(i, equations);
					if(i1 != -1) {
						slau.swap(i, i1);
					}
					else {
						return null;
					}
				}
				
				if(equations.get(j).getCoef()[i] != 0) {
					double scale = equations.get(j).getCoef()[i] / equations.get(i).getCoef()[i];
					slau.subtract(j, i, scale);
				}
				
			}
		}
		
		if(equations.get(n - 1).getCoef()[n - 1] == 0) {
			return null;
		}
		
		for(int i = n - 1; i >= 0; --i) {
			slau.devide(i, equations.get(i).getCoef()[i]);
			
			for(int j = i - 1; j >= 0; --j) {
				double scale = equations.get(j).getCoef()[i] / equations.get(i).getCoef()[i];
				slau.subtract(j, i, scale);
			}
		}
		
		return slau;
	}
	
public Slau gauss() {
		
		Slau slau = this.clone();
		List<Equation> equations = slau.getEquations();
		
		int n = equations.size();
		
		for(int i = 0; i < n - 1; ++i) {
			
			//System.out.println(slau + "\n");
			
			slau.swap(i, slau.getIndexOfMaxAbsElemOnColumn(i));
			double leadElem = equations.get(i).getCoef()[i];
			if(Math.abs(leadElem - 0) < ERROR) {
				return null;
			}
			
			//System.out.println(slau + "\n" + leadElem + "\n");
			
			for(int j = i + 1; j < n; ++j) {
				if(Math.abs(equations.get(j).getCoef()[i] - 0) >= ERROR) {
					//double scale = equations.get(j).getCoef()[i] / leadElem;
					//slau.subtract(j, i, scale);
					
					double scale = leadElem / equations.get(j).getCoef()[i];
					slau.multiply(j, scale);
					
					//System.out.println(slau + "\n" + leadElem + "\n");
					
					slau.subtract(j, i);
				}
				
			}
			
			//System.out.println(slau + "\n" + leadElem + "\n");
		}
		
		if(Math.abs(equations.get(n - 1).getCoef()[n - 1] - 0) < ERROR) {
			return null;
		}
		
		for(int i = n - 1; i >= 0; --i) {
			slau.devide(i, equations.get(i).getCoef()[i]);
			double leadElem = equations.get(i).getCoef()[i];
			
			for(int j = i - 1; j >= 0; --j) {
				double scale = equations.get(j).getCoef()[i] / leadElem;
				slau.subtract(j, i, scale);
			}
		}
		
		return slau;
	}
	
	public double[] sor() {
		return sor(0.5);
	}
	public double[] sor(double o) {
		return sor(o, 0.01);
	}
	public double[] sor(double o, double eps) {
		o = Math.abs(o);
		eps = Math.abs(eps);
		
		if(o < 0 && 1 < o) {
			return null;
		}
		
		int n = this.equations.size();
		for(int i = 0; i < n; ++i) {
			int j = this.maxElemOnColumn(i);
			if(j != i) {
				this.swap(i, j);
			}
		}
		
		for(int i = 0; i < n; ++i) {
			if(this.equations.get(i).getCoef()[i] == 0) {
				return null;
			}
		}
		
		
		
		double[] b = this.getB();
		double[] answer = b.clone();
		double[] answerOld = new double[n];
		
		double[] errors = new double[n];
		
		for(int i = 0; i < n; ++i) {
			answerOld[i] = 0;
			errors[i] = Math.abs(answer[i]);
		}
		
		int k = 0;
		double maxError = Main.maxInArray(errors);
		for(k = 0; k < 1000000 && eps < maxError && maxError < Math.pow(10, 10); ++k) {
			for(int i = 0; i < n; ++i) {
				double[] coefI = this.equations.get(i).getCoef();
				for(int j = 0; j < n; ++j) {
					if(j < i) {
						answer[i] -= coefI[j] * answer[j];
					}
					if(j > i) {
						answer[i] -= coefI[j] * answerOld[j];
					}
				}
				answer[i] /= coefI[i];
			}
			
			for(int i = 0; i < n; ++i) {
				answer[i] = o * answer[i] + (1 - o) * answerOld[i];
			}
			
			errors = this.getErrorOfAnswer(answer);
			
			answerOld = answer;
			answer = b.clone();
			maxError = Main.maxInArray(errors);
		}
		return answerOld;
	}
	
	private int maxElemOnColumn(int j1) {
		int n = this.equations.size();
		for(int i = j1; i < n; ++i) {
			double sum = 0;
			double[] coefI = this.equations.get(i).getCoef();
			for(int j = 0; j < n; ++j) {
				if(j != j1) {
					sum += Math.abs(coefI[j]);
				}
			}
			if(Math.abs(coefI[j1]) >= sum) {
				return i;
			}
		}
		
		double max = Math.abs(this.equations.get(j1).getCoef()[j1]);
		for(int i = j1 + 1; i < n; ++i) {
			if(max < Math.abs(this.equations.get(i).getCoef()[j1])) {
				return i;
			}
		}
		return j1;
	}
	private int getIndexOfMaxAbsElemOnColumn(int cColumn) {
		int n = this.equations.size();
		
		int answerI = cColumn;
		double max = Math.abs(this.equations.get(cColumn).getCoef()[cColumn]);
		for(int i = cColumn + 1; i < n; ++i) {
			double elem = Math.abs(this.equations.get(i).getCoef()[cColumn]);
			if(elem > max) {
				max = elem;
				answerI = i;
			}
		}
		return answerI;
	}
	private int findNotNullStr(int i1, List<Equation> equations) {
		int n = equations.size();
		for(int i = i1 + 1; i < n; ++i) {
			if(equations.get(i).getCoef()[i1] != 0) {
				return i;
			}
		}
		return -1;
	}
	public void multiply(int i1, double scale) {
		Equation e = equations.get(i1);
		double[] coef = e.getCoef();
		for(int i = 0; i < coef.length; ++i) {
			coef[i] *= scale;
		}
		e.setB(e.getB() * scale);
	}
	public void devide(int i1, double scale) {
		Equation e = equations.get(i1);
		double[] coef = e.getCoef();
		for(int i = 0; i < coef.length; ++i) {
			coef[i] /= scale;
		}
		e.setB(e.getB() / scale);
	}
	public void sum(int i1, int i2) {
		sum(i1, i2, 1);
	}
	public void sum(int i1, int i2, double scale) {
		Equation e1 = equations.get(i1);
		double[] coef1 = e1.getCoef();
		
		Equation e2 = equations.get(i2);
		double[] coef2 = e2.getCoef();
		
		for(int i = 0; i < coef1.length; ++i) {
			coef1[i] += coef2[i] * scale;
		}
		e1.setB(e1.getB() - e2.getB() * scale);
	}
	public void subtract(int i1, int j1) {
		Equation e1 = equations.get(i1);
		double[] coef1 = e1.getCoef();
		
		Equation e2 = equations.get(j1);
		double[] coef2 = e2.getCoef();
		
		for(int i = 0; i < coef1.length; ++i) {
			coef1[i] -= coef2[i];
		}
		e1.setB(e1.getB() - e2.getB());
	}
	public void subtract(int i1, int j1, double scale) {
		Equation e1 = equations.get(i1);
		double[] coef1 = e1.getCoef();
		
		Equation e2 = equations.get(j1);
		double[] coef2 = e2.getCoef();
		
		for(int i = 0; i < coef1.length; ++i) {
			coef1[i] -= coef2[i] * scale;
		}
		e1.setB(e1.getB() - e2.getB() * scale);
	}
	public void swap(int i, int j) {
		if(i != j) {
			Collections.swap(this.equations, i, j);
		}
	}
	public double[] getErrorOfAnswer(double[] answer) {
		if(answer.length != this.equations.size()) {
			return null;
		}
		
		int n = answer.length;
		
		double[] error = this.getB();
		for(int i = 0; i < n; ++i) {
			double[] coefI = this.equations.get(i).getCoef();
			for(int j = 0; j < n; ++j) {
				error[i] -= coefI[j] * answer[j];
			}
			error[i] = Math.abs(error[i]);
		}
		
		return error;
	}
	@Override
	public String toString() {
		String str = new String("");
		int n = this.equations.size();
		
		for(int i = 0; i < n; ++i) {
			str += this.equations.get(i).toString();
			if(i != n - 1) {
				str += "\n";
			}
		}
		return str;
	}
	public double getMatrixElem(int i, int j) {
		return this.equations.get(i).getCoef()[j];
	}
	public Slau clone() {
		List<Equation> equationTemp = new ArrayList<Equation>();
		for(Equation e: this.equations) {
			equationTemp.add(e.clone());
		}
		return new Slau(equationTemp);
	}
	public List<Equation> getEquations() {
		return equations;
	}
	public void setEquations(List<Equation> sl) {
		this.equations = sl;
	}
	public double[] getB() {
		int n = this.equations.size();
		
		double[] b = new double[n];
		
		for(int i = 0; i < n; ++i) {
			b[i] = this.equations.get(i).getB();
		}
		return b;
	}
	public static double getERROR() {
		return ERROR;
	}
	public static void setERROR(double eRROR) {
		ERROR = eRROR;
	}
}
