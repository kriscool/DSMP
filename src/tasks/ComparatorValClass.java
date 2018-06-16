package tasks;
import java.util.Comparator;

public class ComparatorValClass implements Comparator<valClassDomain>{
	@Override
	public int compare(valClassDomain p1, valClassDomain p2) {
		if (p1.getVal() < p2.getVal()) return -1;
        if (p1.getVal() > p2.getVal()) return 1;
		return 0;
   	}    
}
