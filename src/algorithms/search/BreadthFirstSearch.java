package algorithms.search;

import algorithms.search.ASearchingAlgorithm;

import java.util.*;

public class BreadthFirstSearch extends ASearchingAlgorithm {
    protected Queue<AState> q;


    public BreadthFirstSearch() {
        this.q = new LinkedList<AState>();
        numberOfNodesEvaluated = 0;
    }

    @Override
    public String getName() {
        return "BreadthFirstSearch";
    }


    /**
     * @param obj The object that we want to search a path on
     * @return the path solution between the start to the goal
     */
    @Override
    public Solution solve(ISearchable obj) {
        this.q.clear();
        List<AState> curStateNeighbors;

        Map<String, AState> visit = new HashMap<String, AState>();
        q.add(obj.getSourceState());
        this.numberOfNodesEvaluated++;
        visit.put(obj.getSourceState().getStateName(), obj.getSourceState());


        while (!(q.isEmpty())) {
            AState frontState = q.poll();
            if (!frontState.equals(obj.getTargetState())) {
                curStateNeighbors = obj.getAllSuccessors(frontState);
                for (AState neigState : curStateNeighbors
                ) {
                    if (visit.get(neigState.getStateName()) == null) {
                        neigState.setPreAState(frontState);

                        if (frontState.equals(obj.getTargetState()) == false) {
                            q.add(neigState);
                            this.numberOfNodesEvaluated++;
                            visit.put(neigState.getStateName(), neigState);

                        } else {

                            return returnPath(frontState);

                        }
                    }
                }
            }
            else {

                return returnPath(frontState);
            }
        }
        Solution sol = new Solution();
        return sol;
    }


}