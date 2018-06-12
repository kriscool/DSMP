package tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoadFromFile {

	private String path = "C:\\Users\\wiku\\Desktop\\DSMP\\Maple_Oak.txt";
	private File database = new File(path);

	String[][] load() throws FileNotFoundException {

		Scanner sc = new Scanner(database);

		List<String> lines = new ArrayList<String>();
		while (sc.hasNextLine()) {
			lines.add(sc.nextLine());
		}

		String[] arr = lines.toArray(new String[0]);

		String[][] loaded = new String[arr[0].split(",").length][arr.length];

		for (int i = 0; i < arr.length; i++) {

			for (int j = 0; j < arr[0].split(",").length; j++) {
				String[] actual = arr[i].split(",");
				loaded[j][i] = actual[j];
			}
		}
		sc.close();

		return loaded;
	}

}
