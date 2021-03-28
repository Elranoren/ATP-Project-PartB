package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState {
        private Position mazeP;

    public MazeState(Position mazeP) {
        this.mazeP = mazeP;
    }
    public Position getMazeP() {
        return mazeP;
    }

    public void setMazeP(Position mazeP) {
        this.mazeP.setPosition(mazeP);
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
        return this.toString();
    }
}
