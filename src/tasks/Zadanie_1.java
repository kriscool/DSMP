package tasks;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.math3.util.Combinations;

public class Zadanie_1 {

	Zadanie_1(int n) throws FileNotFoundException {
		if (n == 1) {
			fisherImpl_1D();
		} else {
			fisherImpl_ND(n);
		}

	}

	List<String> fisherImpl_ND(int liczba_cech) throws FileNotFoundException {

		String[][] baza = new LoadFromFile().load();

		double[][] matrixClassA = crtMatrixClassA(baza);
		double[][] matrixClassB = crtMatrixClassB(baza);

		Combinations combinations = new Combinations(matrixClassA.length, liczba_cech);

		Iterator<int[]> itr = combinations.iterator();
		itr.next();
		itr.next();
		System.out.println(itr.next()[0]);

		// while (itr.hasNext()) {
		// int[] element = itr.next();
		// System.out.println(element[1]);
		// }

		return null;
	}

	static List<String> fisherImpl_1D() throws FileNotFoundException {

		String[][] baza = new LoadFromFile().load();

		List<String> result = new ArrayList<>();

		int licznosc_klasy_a = 0;
		int licznosc_klasy_b = 0;

		for (int i = 0; i < baza[0].length; i++) {
			if (baza[0][i].matches("Acer(.*)")) {
				licznosc_klasy_a++;
			} else {
				licznosc_klasy_b++;
			}
		}

		double[] srednie_dla_A = new double[baza.length];
		double[] srednie_dla_B = new double[baza.length];

		for (int j = 1; j < baza.length; j++) {
			double suma_klasy_A_dla_cechy = 0;
			double suma_klasy_B_dla_cechy = 0;

			for (int i = 0; i < licznosc_klasy_a; i++) {
				suma_klasy_A_dla_cechy += Double.parseDouble((baza[j][i]));
			}

			for (int i = licznosc_klasy_a; i < baza[0].length; i++) {
				suma_klasy_B_dla_cechy += Double.parseDouble(baza[j][i]);
			}
			srednie_dla_A[j] = suma_klasy_A_dla_cechy / licznosc_klasy_a;
			srednie_dla_B[j] = suma_klasy_B_dla_cechy / licznosc_klasy_b;
		}

		double[] rozrzut_wewnatrz_klasowy_A = new double[baza.length];
		double[] rozrzut_wewnatrz_klasowy_B = new double[baza.length];

		for (int j = 1; j < baza.length; j++) {
			double suma_klasy_A_dla_cechy_licznik_rozrzutu = 0;
			double suma_klasy_B_dla_cechy_licznik_rozrzutu = 0;

			for (int i = 0; i < licznosc_klasy_a; i++) {
				suma_klasy_A_dla_cechy_licznik_rozrzutu += Math
						.pow((Double.parseDouble((baza[j][i])) - srednie_dla_A[j]), 2);
			}

			for (int i = licznosc_klasy_a; i < baza[0].length; i++) {
				suma_klasy_B_dla_cechy_licznik_rozrzutu += Math
						.pow((Double.parseDouble((baza[j][i])) - srednie_dla_B[j]), 2);
			}
			rozrzut_wewnatrz_klasowy_A[j] = Math.sqrt(suma_klasy_A_dla_cechy_licznik_rozrzutu / licznosc_klasy_a);
			rozrzut_wewnatrz_klasowy_B[j] = Math.sqrt(suma_klasy_B_dla_cechy_licznik_rozrzutu / licznosc_klasy_b);
		}
		double[] fisher = new double[baza.length];

		for (int i = 1; i < baza.length; i++) {
			double fisherr = (srednie_dla_A[i] - srednie_dla_B[i])
					/ (rozrzut_wewnatrz_klasowy_A[i] + rozrzut_wewnatrz_klasowy_B[i]);
			if (fisherr < 0) {
				fisher[i] = -1 * fisherr;
			} else {
				fisher[i] = fisherr;
			}

		}

		int bestFisherNo = 0;
		for (int i = 1; i < fisher.length; i++) {
			if (fisher[i] > fisher[bestFisherNo]) {
				bestFisherNo = i;
			}
		}
		String wynik = "Najwyzszy fisher dla cechy: " + bestFisherNo + "\nF(" + bestFisherNo + ") = "
				+ fisher[bestFisherNo];
		System.out.println(wynik);
		result.add(wynik);
		return result;
	}

	double[][] crtMatrixClassA(String[][] baza) {

		int licznosc_klasy_a = 0;

		for (int i = 0; i < baza[0].length; i++) {
			if (baza[0][i].matches("Acer(.*)")) {
				licznosc_klasy_a++;
			}
		}
		double[][] matrixClassA = new double[baza.length - 1][licznosc_klasy_a];
		for (int i = 1; i < baza.length; i++) {

			for (int j = 0; j < licznosc_klasy_a; j++) {
				matrixClassA[i - 1][j] = Double.parseDouble(baza[i][j]);
			}
		}
		return matrixClassA;
	}

	double[][] crtMatrixClassB(String[][] baza) {
		int licznosc_klasy_b = 0;
		for (int i = 0; i < baza[0].length; i++) {
			if (!(baza[0][i].matches("Acer(.*)"))) {
				licznosc_klasy_b++;
			}
		}
		double[][] matrixClassB = new double[baza.length - 1][licznosc_klasy_b];
		for (int i = 1; i < baza.length; i++) {
			for (int j = licznosc_klasy_b; j > 0; j--) {
				matrixClassB[i - 1][licznosc_klasy_b - j] = Double.parseDouble(baza[i][baza[0].length - j]);
			}
		}
		return matrixClassB;
	}

}
