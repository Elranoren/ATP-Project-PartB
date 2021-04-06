package algorithms.search;

import java.util.List;
import java.util.Map;

public interface ISearchable {
    List<AState> getAllSuccessors(AState target);
    AState getSourceState();
    AState getTargetState();
}
