package tasks;
import java.util.Comparator;

public class ComparatorDouble implements Comparator<Double> {
	@Override
	public int compare(Double p1, Double p2) {
		if (p1 < p2) return -1;
        if (p1 > p2) return 1;
		return 0;
   	}    
}


