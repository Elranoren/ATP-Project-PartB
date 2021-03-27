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


    @Override
    public Solution solve(ISearchable obj) {
        List<AState> curStateNeighbors;
        Map<String,Integer> visit = new HashMap<String,Integer>();
        s.push(obj.getSourceState());
        visit.put(obj.getSourceState().getStateName(),1);

        while(!(s.isEmpty()))
        {
           // numberOfNodesEvaluated++;
            AState topState= s.pop();
            if(!topState.equals(obj.getTargetState()))
            {
                curStateNeighbors=obj.getAllPossibleStates(topState);
                for (AState neigState: curStateNeighbors
                ) {
                    if(visit.get(neigState.getStateName()) == null){
                        neigState.setPreAState(topState);
                        if(topState.equals(obj.getTargetState())==false)
                        {
                            s.push(neigState);
                            visit.put(neigState.getStateName(),1);
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
