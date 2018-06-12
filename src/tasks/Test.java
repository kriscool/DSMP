package tasks;

public class Test {

	public static void main(String[] args) throws Exception {

		LoadFromFile db = new LoadFromFile();
		String results[][] = db.load();

		Zadanie_1 zad1 = new Zadanie_1(2);

		// zad1.fisherImpl_ND(1);
		// zad1.fisherImpl_1D();
		// System.out.println(Zadanie_1.fisherImpl_1D().get(0));

	}

}
