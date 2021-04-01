package algorithms.search;
import java.util.Comparator;

public class ComperBestFirstSearch implements Comparator<AState>{
    /**
     * @param o1 state 1
     * @param o2 state 2
     * @return 0 if distance of o1 is equal to distance of o2, 1 if o1>o2 else -1
     */
    @Override
    public int compare(AState o1, AState o2) {
        return Integer.compare(o1.getCost(),o2.getCost());
    }
}
