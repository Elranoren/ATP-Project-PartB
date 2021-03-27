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



    @Override
    public String toString() {
        return this.mazeP.toString();
    }
}
