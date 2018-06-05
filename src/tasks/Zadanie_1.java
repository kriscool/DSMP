package tasks;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Zadanie_1 {

	double fisher;

	List<String> fisherImpl_ND(int liczba_cech) throws FileNotFoundException {

		List<String> result = new ArrayList<>();
		String[][] baza = new LoadFromFile().load();

		return null;
	}

	static List<String> fisherImpl_1D() throws FileNotFoundException {

		List<String> result = new ArrayList<>();
		String[][] baza = new LoadFromFile().load();

		int licznosc_klasy_a = 0;
		int licznosc_klasy_b = 0;

		for (int i = 1; i < baza[0].length; i++) {
			if (baza[0][i].matches("Acer(.*)")) {
				licznosc_klasy_a++;
			} else {
				licznosc_klasy_b++;
			}
		}

		double[] srednie_dla_A = new double[baza.length - 1];
		double[] srednie_dla_B = new double[baza.length - 1];

		for (int j = 1; j < baza.length; j++) {
			double suma_klasy_A_dla_cechy = 0;
			double suma_klasy_B_dla_cechy = 0;

			for (int i = 0; i < licznosc_klasy_a - 1; i++) {
				suma_klasy_A_dla_cechy += Double.parseDouble((baza[j][i]));
			}
			for (int i = licznosc_klasy_a; i < baza[0].length; i++) {
				suma_klasy_B_dla_cechy += Double.parseDouble(baza[j][i]);
			}
			srednie_dla_A[j - 1] = suma_klasy_A_dla_cechy / licznosc_klasy_a;
			srednie_dla_B[j - 1] = suma_klasy_B_dla_cechy / licznosc_klasy_b;
		}

		double[] rozrzut_wewnatrz_klasowy_A = new double[baza.length - 1];
		double[] rozrzut_wewnatrz_klasowy_B = new double[baza.length - 1];

		return result;
	}

}
