package algorithms.search;

import algorithms.search.ASearchingAlgorithm;

import java.util.*;

public class DepthFirstSearch extends ASearchingAlgorithm {
    private Stack<AState> s;

    public DepthFirstSearch() {
        this.s = new Stack<AState>(); ;
        numberOfNodesEvaluated=0;
    }

    @Override
    public String getName() {
        return "DepthFirstSearch";
    }


    /**
     * @param obj The object that we want to search a path on
     * @return the path solution between the start to the goal
     */
    @Override
    public Solution solve(ISearchable obj) {
        List<AState> curStateNeighbors;
        Map<String,AState> visit = new HashMap<String,AState>();
        s.push(obj.getSourceState());
        visit.put(obj.getSourceState().getStateName(),obj.getSourceState());

        while(!(s.isEmpty()))
        {
            numberOfNodesEvaluated++;
            AState topState= s.pop();
            if(!topState.equals(obj.getTargetState()))
            {
                curStateNeighbors=obj.getAllSuccessors(topState);
                for (AState neigState: curStateNeighbors
                ) {
                    if(visit.get(neigState.getStateName()) == null){
                        neigState.setPreAState(topState);
                        if(topState.equals(obj.getTargetState())==false)
                        {
                            s.push(neigState);
                            visit.put(neigState.getStateName(),neigState);
                        }
                        else {
                            return returnPath(topState);
                        }
                    }
                }
            }
            else
            {
                return returnPath(topState);
            }
        }
        Solution sol = new Solution();
        return sol;
    }
}
