import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tasks {
	public static Slau[] getSystem() {
		Slau[] slau = new Slau[6];
		
		double[][] arr1 = {
				{3, 1, 1},
				{2, 4, -2},
				{2, -3, 2},
		};
		double[] b1 = {8, 4, 2};
		
		Slau sl1 = new Slau(arr1, b1);
		slau[0] = sl1;
		
		double[][] arr2 = {
				{1, 4, -3, 5},
				{1, 4, -2, 1},
				{2, -1, 3, -4},
				{3, 3, 1, 3}
		};
		double[] b2 = {7, 4, 0, 10};
		
		/*double[][] arr2 = {
				{0, 0, -2, 2},
				{0, 0, 1, 1},
				{0, 0, 1, 3},
				{2, 4, -1, 2}
		};
		double[] b2 = {0, 2, 4, 1};*/
		
		Slau sl2 = new Slau(arr2, b2);
		slau[1] = sl2;
		
		double[][] arr3 = {
				{3, 2, 1, 1},
				{1, -1, 4, -1},
				{-2, -2, -3, 1},
				{1, 5, -1, 2}};
		double[] b3 = {-2, -1, 9, 4};
		
		/*double[][] arr3 = {
				{0, -2, 2, 1},
				{-1, 0, 2, 3},
				{2, 2, 0, 1},
				{1, 3, 1, 0}};
		double[] b3 = {3, 5, 1, 1};*/
		
		Slau sl3 = new Slau(arr3, b3);
		slau[2] = sl3;
				
		/*double[][] arr4 = {
				{0, -1, 1, -1, 1},
				{1, 0, 1, -2, 1},
				{2, 1, 1, -1, 3},
				{1, 0, 0, 0, 6},
				{0, 1, 0, 1, 0}
		};
		double[] b4 = {0, 1, 6, 7, 2};*/
		
		double[][] arr4 = {
				{1, 2, -1, 0, 2},
				{2, 1, 0, 3, 0},
				{-1, 0, 1, 0, 2},
				{0, 3, 0, 1, 0},
				{2, 0, 2, 0, 1}
		};
		double[] b4 = {4, 5, -2, 3, 4};
		
		Slau sl4 = new Slau(arr4, b4);
		slau[3] = sl4;
		
		double[][] arr5 = {
				{2, 2, -1},
				{1, -1, 1},
				{3, 1, 0}
		};
		double[] b5 = {3, 1, 4};
		Slau sl5 = new Slau(arr5, b5);
		slau[4] = sl5;
		
		/*double[][] arr6 = {
				{5, 2, -1},
				{-4, 7, 3},
				{2, -2, 4},
		};
		double[] b6 = {12, 24, 9};
		Slau sl6 = new Slau(arr6, b6);*/
		
		double[][] arr6 = {
				
				{-4, 7, 3},
				{2, -2, 4},
				{5, 2, -1},
		};
		double[] b6 = {24, 9, 12,};
		Slau sl6 = new Slau(arr6, b6);
		slau[5] = sl6;
		return slau;
	}
}
