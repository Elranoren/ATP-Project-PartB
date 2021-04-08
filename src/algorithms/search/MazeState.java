package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState {
        private Position mazeP;


    public MazeState(Position mazeP , AState preSuccessor,String stateName) {
        super(preSuccessor,stateName);
        this.mazeP = mazeP;
    }
    public Position getMazeP() {
        return mazeP;
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
     * @param p a position value to check if equal to this.mazeP
     * @return true if equal, else: return false
     */

    public boolean equals(Position p)
    {
        if (mazeP!=null)
            return ((this.mazeP).equals(p));
        return false;
    }

    @Override
    public String toString() {
        return this.stateName;
    }

    @Override
    public String getStateName() {
        return this.stateName;
    }
}
