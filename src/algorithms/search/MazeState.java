package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState {
        private Position mazeP;
        private AState preSuccessor;

    public MazeState(Position mazeP , AState preSuccessor) {
        this.mazeP = mazeP;
        this.preSuccessor = preSuccessor;
    }
    public Position getMazeP() {
        return mazeP;
    }

    public void setMazeP(Position mazeP) {
        this.mazeP.setPosition(mazeP);
    }

    /**
     * @param o an object value to check if equal to this MazeState
     * @return true if equal, else: return false
     */
    public boolean equals(Object o)
    {
        if (this==o)
            return true;
        else{
            if (o instanceof MazeState){
                MazeState ms=(MazeState)o;
                return (ms.equals(this.mazeP));
            }
            return false;
        }
    }

    /**
     * @param ms a MazeState value to check if equal to this MazeState
     * @return true if equal, else: return false
     */
    public boolean equals(MazeState ms)
    {
        return (ms.equals(this.mazeP));
    }
    public boolean equals(Position p)
    {
        if (mazeP!=null)
            return ((this.mazeP).equals(p));
        return false;
    }

    @Override
    public String toString() {
        return this.mazeP.toString();
    }

    @Override
    public String getStateName() {
        return this.toString();
    }
}
