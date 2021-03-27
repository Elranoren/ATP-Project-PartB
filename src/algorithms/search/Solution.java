package algorithms.search;

import java.util.ArrayList;

public class Solution {
    ArrayList<AState> sol;

    public Solution() {
        this.sol = new ArrayList<AState>();
    }


    public ArrayList<AState> getSolutionPath() {
        return sol;
    }

    public void setSol(ArrayList<AState> sol) {
        this.sol = sol;
    }
}
