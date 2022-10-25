public class Matrix {
	private double[][] arr;
	private double[] b;
 	private int n;
 	private boolean noSolution = false;
 	
	Matrix(double[][] arr, double[] b, int n) {
		this.arr = arr;
		this.b = b;
		this.n = n;
	}
	Matrix(double[][] arr, double[] b, int n, boolean noSolution) {
		this.arr = arr;
		this.b = b;
		this.n = n;
		this.noSolution = noSolution;
	}
	
	public Matrix gauss() {
		double[][] tempArr = getArrClone();
		double[] tempB = getBClone();
		
		for(int i = 0; i < n - 1; ++i) {
			for(int j = i + 1; j < n; ++j) {
				
				if(tempArr[i][i] == 0) {
					//System.out.println("!!" + tempArr[i][i] + " " + i);
					int tempI = findNotNullStr(i, tempArr);
					if(tempI != -1) {
						swapStr(i, tempI, tempArr, tempB);
					} 
					else {
						setNoSolution(true);
						return new Matrix(tempArr, tempB, n, true);
					}
				}
				
				if(tempArr[j][i] != 0) {
				
					double scale = tempArr[j][i] / tempArr[i][i];
				
					//System.out.print(scale + " ");
				
					for(int k = i; k < n; ++k) {
						tempArr[j][k] -= tempArr[i][k] * scale;
					}
					tempB[j] -= tempB[i] * scale;
				}
			}
		}
		
		if(tempArr[n - 1][n - 1] == 0) {
			setNoSolution(true);
			return new Matrix(tempArr, tempB, n, true);
		}
		
		for(int i = n - 1; i >= 0; --i) {
			tempB[i] /= tempArr[i][i];
			tempArr[i][i] /= tempArr[i][i];
			
			for(int j = i - 1; j >= 0; --j) {
				double scale = tempArr[j][i] / tempArr[i][i];
				
				//System.out.print(scale + " ");
				
				tempArr[j][i] -= tempArr[i][i] * scale;
				tempB[j] -= tempB[i] * scale;
			}
		}
		
		
		
		//System.out.print("\n");
		return new Matrix(tempArr, tempB, n);
	}
	private int findNotNullStr(int iCur, double[][] tempArr) {
		for(int i = iCur + 1; i < n; ++i) {
			if(tempArr[i][iCur] != 0) {
				return i;
			}
		}
		return -1;
	}
	private void swapStr(int iOne, int iTwo, double[][] array, double[] b) {
		double temp = b[iOne];
		b[iOne] = b[iTwo];
		b[iTwo] = temp;
		for(int i = 0; i < n; ++i) {
			temp = array[iOne][i];
			array[iOne][i] = array[iTwo][i];
			array[iTwo][i] = temp;
		}
		
	}
	public double[] getStr(int i) {
		return arr[i];
	}
	@Override
	public String toString() {
		String tempString = "";
		for(int i = 0; i < n; ++i) {
			for(int j = 0; j < n; ++j) {
				tempString += arr[i][j] + " ";
			}
			tempString += "|" + b[i];
			if(i != n - 1) {
				tempString += "\n";	
			}			
		}
		return tempString;
	}
	public double[][] getArr() {
		return arr;
	}
	public double[][] getArrClone() {
		double[][] arr = new double[n][n];
		for(int i = 0; i < n; ++i) {
			for(int j = 0; j < n; ++j) {
				arr[i][j] = this.arr[i][j];
			}
		}
		return arr;
	}
	public void setArr(double[][] arr) {
		this.arr = arr;
	}
	public double[] getBClone() {
		double[] b = new double[n];
		for(int i = 0; i < n; ++i) {
			b[i] = this.b[i];
		}
		return b;
	}
	public double[] getB() {
		return b;
	}
	public void setB(double[] b) {
		this.b = b;
	}
	public int getN() {
		return n;
	}
	public void setN(int n) {
		this.n = n;
	}
	public boolean isNoSolution() {
		return noSolution;
	}
	public void setNoSolution(boolean noSolution) {
		this.noSolution = noSolution;
	}
	
}
