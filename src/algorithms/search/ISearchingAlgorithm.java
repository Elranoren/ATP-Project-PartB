package algorithms.search;

public interface ISearchingAlgorithm {
    String getName();
    int getNumberOfNodesEvaluated();
    Solution findSol(ISearchable obj);
}
