package algorithms.search;

import java.util.PriorityQueue;

public class BestFirstSearch extends BreadthFirstSearch {

    public BestFirstSearch() {
        q = new PriorityQueue<>(new ComperBestFirstSearch());
        numberOfNodesEvaluated = 0;
    }

    public String getName() {
        return "BestFirstSearch";
    }

}
