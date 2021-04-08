package algorithms.maze3D;
import algorithms.search.AState;


public class Maze3DState extends AState {
    private Position3D mazeP;
    public Maze3DState(Position3D mazeP , AState preSuccessor , String statename3D) {
        super(preSuccessor,statename3D);
        this.mazeP = mazeP;
    }
    public Position3D getMazeP() {
        return mazeP;
    }

    /**
     * @param o an object to check if o equal to this
     * @return true if equal, false if not equal
     */
    public boolean equals(Object o)
    {
        if (this==o)
            return true;
        else{
            if (o instanceof Maze3DState){
                Maze3DState ms=(Maze3DState)o;
                return (ms.equals(this.mazeP));
            }
            return false;
        }
    }

    /**
     * @param p postion3D to check if p is equal to this.mazeP
     * @return
     */
    public boolean equals(Position3D p)
    {
        if (mazeP!=null)
            return ((this.mazeP).equals(p));
        return false;
    }

    @Override
    public String toString() {
        return stateName;
    }

    @Override
    public String getStateName() {
        return stateName;
    }




}
