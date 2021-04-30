package algorithms.search;

import java.io.Serializable;
import java.util.ArrayList;

public class Solution implements Serializable {
    ArrayList<AState> sol;

    public Solution() {
        this.sol = new ArrayList<AState>();
    }


    public ArrayList<AState> getSolutionPath() {
        return sol;
    }
}
