import java.util.*;

public class Main {

	public static void main(String[] args) {
		//Slau[] slau = Tasks.getSystem();
		
		/**
		 * Task0
		 */
		//sor(slau[0], slau[1], slau[2], slau[3], slau[4], slau[5]);
		/**
		 * Task1
		 */
		//gauss(slau[0], slau[1], slau[2], slau[3], slau[4], slau[5]);
		
		/**
		 * Task2
		 */	
		
		/*
		Point[] points = Input.getPointsOfFunction(-10, 10, 30);
		
		double[] newY = Antialiasing.antialiasing(points, 10, 9);
		for(int i = 0; i < newY.length; ++i) {
			points[i].setY(newY[i]);
		}
		System.out.println(Point.toStringPoints(points));
		*/
		
		
		/**
		 * Task3
		 */	
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Введите a и b промежутка: [a, b]");
		double a = sc.nextDouble();
		double b = sc.nextDouble();
		
		System.out.println("Введите количество точек разбиения");
		int k = sc.nextInt();
		sc.close();
		
		double eps = Math.pow(0.1, 2);
		//System.out.println(eps);
		
		List<Double> roots = Roots.getRootsOfFunction(a, b, k, eps);
		Roots.printRoots(roots);
		Point[] points = new Point[roots.size()];
		for(int i = 0; i < roots.size(); ++i) {
			points[i] = new Point(roots.get(i), 0);
		}
		System.out.println(Point.toStringPoints(points));
		
		
 	}
	public static void printAnswer(Slau sl, double[] answer) {
		if(answer == null) {
			System.out.println("Система:\n" + sl + "\nРешений нет или бесконечное количество!");
			return;
		}
		int n = answer.length;
		
		double[] errors = sl.getErrorOfAnswer(answer);
		if(Main.maxInArray(errors) > Math.pow(10, 10)) {
			System.out.println("Система:\n" + sl + "\nРешение не сошлось!");
			return;
		}
				
		System.out.println("Система:\n" + sl + "\nРешение:");
		for(int i = 0; i < n; ++i) {
			System.out.println("x" + (i + 1) + " = " + answer[i]);
		}
		System.out.println("Погрешность:");
		for(int i = 0; i < n; ++i) {
			System.out.println((i + 1) + " = " + errors[i]);
		}
	}
	public static void printAnswer(Slau sl1, Slau sl2) {
		if(sl2 == null) {
			double[] arr = null;
			printAnswer(sl1, arr);
		}
		else {
			printAnswer(sl1, sl2.getB());
		}
	}
	public static void gauss(Slau sl1, Slau sl2, Slau sl3, Slau sl4, Slau sl5, Slau sl6) {

		String label = new String();
		for(int i = 0; i < 80; ++i) {
			label+= "=";
		}
		
		int x = -1;		
		
		Scanner sc = new Scanner(System.in);
		do
		{
			switch(x) {
			case (1):
				System.out.println(label);
				Main.printAnswer(sl1, sl1.gauss());
				System.out.println(label);
				break;
				
			case (2):
				System.out.println(label);
				Main.printAnswer(sl2, sl2.gauss());
				System.out.println(label);
			break;
			
			case (3):
				System.out.println(label);
				Main.printAnswer(sl3, sl3.gauss());
				System.out.println(label);
			break;
			
			case (4):
				System.out.println(label);
				Main.printAnswer(sl4, sl4.gauss());
				System.out.println(label);
			break;
			
			case (5):
				System.out.println(label);
				Main.printAnswer(sl5, sl5.gauss());
				System.out.println(label);
			break;
			
			case (6):
				System.out.println(label);
				Main.printAnswer(sl6, sl6.gauss());
				System.out.println(label);
			break;
				
			case (7):
				System.out.print(label + "\nВведите размерность\n> ");
				int n = sc.nextInt();
				
				
				List<Equation> l = new ArrayList<Equation>(n);
				
				
				System.out.print("Введите систему\n> ");
				for(int i = 0; i < n; ++i) {
					double[] arr = new double[n];
					for(int j = 0; j < n; ++j)  {
						arr[j] = sc.nextDouble();
					}
					double b = sc.nextDouble();
					l.add(new Equation(arr, b));
				}
				Slau sl = new Slau(l);
				
				
				
				System.out.println(label);
				Main.printAnswer(sl, sl.gauss());
				System.out.println(label);
			break;
			}
			System.out.println("1 - Решить систему порядка 3");
			System.out.println("2 - Решить систему порядка 4");
			System.out.println("3 - Решить систему2 порядка 4");
			System.out.println("4 - Решить систему порядка 5");
			System.out.println("5 - Решить несовместную систему порядка 3");
			System.out.println("6 - Решить систему2 порядка 3");
			System.out.println("7 - Ввести свою систему");
			System.out.println("0 - Завершить");
			System.out.print("> ");
			x = sc.nextInt();
		}while(x != 0);
		sc.close();
	}
	public static void sor(Slau sl1, Slau sl2, Slau sl3, Slau sl4, Slau sl5, Slau sl6) {
		String label = new String();
		for(int i = 0; i < 80; ++i) {
			label+= "=";
		}
		
		int x = -1;		
		
		Scanner sc = new Scanner(System.in);
		do
		{
			switch(x) {
			case (1):
				System.out.println(label);
				Main.printAnswer(sl1, sl1.sor());
				System.out.println(label);
				break;
				
			case (2):
				System.out.println(label);
				Main.printAnswer(sl2, sl2.sor());
				System.out.println(label);
			break;
			
			case (3):
				System.out.println(label);
				Main.printAnswer(sl3, sl3.sor());
				System.out.println(label);
			break;
			
			case (4):
				System.out.println(label);
				Main.printAnswer(sl4, sl4.sor());
				System.out.println(label);
			break;
			
			case (5):
				System.out.println(label);
				Main.printAnswer(sl5, sl5.sor());
				System.out.println(label);
			break;
			
			case (6):
				System.out.println(label);
				Main.printAnswer(sl6, sl6.sor());
				System.out.println(label);
			break;
				
			case (7):
				System.out.print(label + "\nВведите размерность\n> ");
				int n = sc.nextInt();
				
				
				List<Equation> l = new ArrayList<Equation>(n);
				
				
				System.out.print("Введите систему\n> ");
				for(int i = 0; i < n; ++i) {
					double[] arr = new double[n];
					for(int j = 0; j < n; ++j)  {
						arr[j] = sc.nextDouble();
					}
					double b = sc.nextDouble();
					l.add(new Equation(arr, b));
				}
				Slau sl = new Slau(l);
				
				
				
				System.out.println(label);
				Main.printAnswer(sl, sl.sor());
				System.out.println(label);
			break;
			}
			System.out.println("1 - Решить систему порядка 3");
			System.out.println("2 - Решить систему порядка 4");
			System.out.println("3 - Решить систему2 порядка 4");
			System.out.println("4 - Решить систему порядка 5");
			System.out.println("5 - Решить несовместную систему порядка 3");
			System.out.println("6 - Решить систему2 порядка 3");
			System.out.println("7 - Ввести свою систему");
			System.out.println("0 - Завершить");
			System.out.print("> ");
			x = sc.nextInt();
		}while(x != 0);
		sc.close();
	}
	public static double maxInArray(double[] array) {
		if(array == null) {
			return -Double.MAX_VALUE;
		}
		double max = array[0];
		for(double x: array) {
			if(x > max) {
				max = x;
			}
		}
		return max;
	}
}
