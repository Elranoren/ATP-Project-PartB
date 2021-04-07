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
            if (o instanceof Maze3DState){
                Maze3DState ms=(Maze3DState)o;
                return (ms.equals(this.mazeP));
            }
            return false;
        }
    }

    public boolean equals(Maze3DState ms)
    {
        return (ms.equals(this.mazeP));
    }
    public boolean equals(Position3D p)
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
