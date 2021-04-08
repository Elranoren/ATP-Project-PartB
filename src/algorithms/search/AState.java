package algorithms.search;

public abstract class AState {
    protected String stateName;
    private double cost;
    private AState preAState;

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public AState(AState preSuccessor , String stateName ) {
        this.cost = 0;
        this.preAState = preSuccessor;
        this.stateName = stateName;

    }

    public abstract String getStateName() ;

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }


    public AState getPreAState() {
        return preAState;
    }

    public void setPreAState(AState preAState) {
        this.preAState = preAState;
    }


}
