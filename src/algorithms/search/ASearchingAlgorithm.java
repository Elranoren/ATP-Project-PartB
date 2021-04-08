package algorithms.search;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {
    protected int numberOfNodesEvaluated;

    @Override
    public int getNumberOfNodesEvaluated() {
        return numberOfNodesEvaluated;
    }


    /**
     * @param state current state
     * @return the path
     */
    protected Solution returnPath(AState state) {
        Solution sol = new Solution();
        sol.getSolutionPath().add(0,state);
        //double cost = state.getCost();
        while (state.getPreAState() !=null){
            state=state.getPreAState();
            sol.getSolutionPath().add(0,state);
        }
        //System.out.println(cost);
        return sol;
    }
}
