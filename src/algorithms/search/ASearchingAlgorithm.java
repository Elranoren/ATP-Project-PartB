package algorithms.search;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {
    // protected ISearchable a;
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
        sol.getSol().add(0,state);
        while (state.getPreAState() !=null){
            state=state.getPreAState();
            sol.getSol().add(0,state);
        }
        return sol;
    }
}
