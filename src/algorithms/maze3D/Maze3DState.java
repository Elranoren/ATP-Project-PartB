package algorithms.maze3D;

import algorithms.mazeGenerators.Position;
import algorithms.search.AState;
import algorithms.search.MazeState;

public class Maze3DState extends AState {
    private Position3D mazeP;
    private AState preSuccessor;
    public Maze3DState(Position3D mazeP , AState preSuccessor) {
        this.mazeP = mazeP;
        this.preSuccessor = preSuccessor;
    }
    public Position3D getMazeP() {
        return mazeP;
    }

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
        return null;
    }




}
