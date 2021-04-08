package algorithms.search;

import algorithms.maze3D.Maze3D;

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
    /**
     * @param is the object to check on
     * @return how much time the search takes
     */
    public long measureAlgorithmTimeMillisOnSearchingAlgorithm(ISearchable is) {
        long start = System.currentTimeMillis();
        this.solve(is);
        long end = System.currentTimeMillis();
        return end - start;
    }


}
