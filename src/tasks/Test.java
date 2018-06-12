package tasks;

import java.io.FileNotFoundException;

public class Test {

	public static void main(String[] args) throws FileNotFoundException {

		LoadFromFile db = new LoadFromFile();
		String results[][] = db.load();

		System.out.println(Zadanie_1.fisherImpl_1D().get(0));

	}

}
