package algorithms.search;

import java.util.List;

public interface ISearchable {
    List<AState> getAllPossibleStates(AState target);
    AState getSourceState();
    AState getTargetState();
}
