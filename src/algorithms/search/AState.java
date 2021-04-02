package algorithms.search;

public abstract class AState {
    private String stateName;
    private int cost;
    private AState preAState;

    public AState() {
        this.cost = 0;
        this.preAState = null;
    }

    public abstract String getStateName() ;

    public void setPositionName(String positionName) {
        this.stateName = positionName;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }


    public AState getPreAState() {
        return preAState;
    }

    public void setPreAState(AState preAState) {
        this.preAState = preAState;
    }
}
