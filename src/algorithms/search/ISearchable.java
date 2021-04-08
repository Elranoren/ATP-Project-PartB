package algorithms.search;

import java.util.List;

public interface ISearchable {
    List<AState> getAllSuccessors(AState target);
    AState getSourceState();
    AState getTargetState();
}
