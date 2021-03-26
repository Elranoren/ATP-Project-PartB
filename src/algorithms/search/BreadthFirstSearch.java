package algorithms.search;

import algorithms.search.ASearchingAlgorithm;

import java.util.*;

public class BreadthFirstSearch extends ASearchingAlgorithm {
    protected Queue<AState> q;
    protected int numberOfNodesEvaluated;

    public BreadthFirstSearch() {
        this.q = new LinkedList<AState>();
        this.numberOfNodesEvaluated = 0;
    }

    @Override
    public String getName() {
        return "BreadthFirstSearch";
    }

    @Override
    public int getNumberOfNodesEvaluated() {
        return this.numberOfNodesEvaluated;
    }

    /**
     * @param obj The object that we want to search a path on
     * @return the path solution between the start to the goal
     */
    @Override
    public Solution findSol(ISearchable obj) {
        List<AState> curStateNeighbors;
        Map<String,Integer> visit = new HashMap<String,Integer>();
        q.add(obj.getSourceState());
        this.numberOfNodesEvaluated++;
        visit.put(obj.getSourceState().getStateName(),1);

        while(!(q.isEmpty()))
        {
            AState frontState= q.poll();
            if(!frontState.equals(obj.getTargetState()))
            {
                curStateNeighbors=obj.getAllPossibleStates(frontState);
                for (AState neigState: curStateNeighbors
                     ) {
                    if(visit.get(neigState.getStateName()) == null){
                        neigState.setPreAState(frontState);
                    if(frontState.equals(obj.getTargetState())==false)
                    {
                        q.add(neigState);
                        this.numberOfNodesEvaluated++;
                        visit.put(neigState.getStateName(),1);
                    }
                    else {
                        return returnPath(frontState);
                    }
                    }
                }
            }
            else
            {
                return returnPath(frontState);
            }
        }
        Solution sol = new Solution();
        return sol;
    }

    /**
     * @param state current state
     * @return the path
     */
    private Solution returnPath(AState state) {
        Solution sol = new Solution();
        sol.getSol().add(0,state);
        while (state.getPreAState() !=null){
            state=state.getPreAState();
            sol.getSol().add(0,state);
        }
        return sol;
    }

    public void setQ(Queue<AState> q) {
        this.q = q;
    }
}
