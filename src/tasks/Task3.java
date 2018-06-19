package tasks;

import java.util.ArrayList;

public class Task3 {
	public String MN(double[][] a, double[][] b, double[] point) {
		double[] uA = getMean(a);
		double[] uB = getMean(b);
		double sum = 0;
		for (int i = 0; i < point.length; i++) {
			sum += Math.pow(point[i] - uA[i], 2);
		}
		double lengthA = Math.sqrt(sum);
		sum = 0;
		for (int i = 0; i < point.length; i++) {
			sum += Math.pow(point[i] - uB[i], 2);
		}
		double lengthB = Math.sqrt(sum);

		if (lengthA > lengthB) {
			return "b";
		} else if (lengthA < lengthB) {
			return "a";
		} else {
			return "c";
		}
	}

	private double[] getMean(double[][] a2) {
		double[] mean = new double[a2.length];
		double sum = 0;
		for (int i = 0; i < a2.length; i++) {
			for (int j = 0; j < a2[i].length; j++) {
				sum += a2[i][j];
			}
			mean[i] = sum / a2[i].length;
			sum = 0;
		}

		return mean;
	}

	public ArrayList<valClassDomain> addTwoTab(double[] a, double[] b) {
		ArrayList<valClassDomain> array = new ArrayList<valClassDomain>();
		for (int i = 0; i < a.length; i++) {
			valClassDomain v = new valClassDomain();
			v.setVal(a[i]);
			v.setClas("a");
			array.add(v);
		}

		for (int j = 0; j < b.length; j++) {
			valClassDomain v = new valClassDomain();
			v.setVal(b[j]);
			v.setClas("b");
			array.add(v);
		}

		return array;
	}

	public String KNN(double[][] a, double[][] b, int k, double[] p) {
		double[] result = new double[a[0].length];
		double temp = 0.0;
		for (int j = 0; j < a[0].length; j++) {
			for (int i = 0; i < a.length; i++) {
				temp += Math.pow(a[i][j] - p[i], 2);
			}
			result[j] = Math.sqrt(temp);
			temp = 0.0;
		}

		double[] resultb = new double[b[0].length];
		double tempb = 0.0;
		for (int j = 0; j < b[0].length; j++) {
			for (int i = 0; i < b.length; i++) {
				tempb += Math.pow(b[i][j] - p[i], 2);
			}
			resultb[j] = Math.sqrt(tempb);
			tempb = 0.0;
		}

		if (k == 1) {
			return findMinFromTwoTab(result, resultb);
		} else {
			ArrayList<valClassDomain> array = addTwoTab(result, resultb);
			ComparatorValClass comparator = new ComparatorValClass();
			array.sort(comparator);
			int ofA = 0;
			int ofB = 0;
			for (int i = 0; i < k; i++) {
				if (array.get(i).getClas().equals("a")) {
					ofA++;
				} else {
					ofB++;
				}
			}

			if (ofA > ofB) {
				return "a";
			} else if (ofA < ofB) {
				return "b";
			} else {
				return "c";
			}
		}
	}

	public double findMin(double[] tab) {
		double min = tab[0];
		for (int l = 1; l < tab.length; l++) {
			if (min > tab[l]) {
				min = tab[l];
			}
		}
		return min;
	}

	public String findMinFromTwoTab(double[] tab, double[] tabb) {
		if (findMin(tab) > findMin(tabb)) {
			return "b";
		} else {
			return "a";
		}
	}
}
