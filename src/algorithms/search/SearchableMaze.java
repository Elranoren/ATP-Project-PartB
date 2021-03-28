package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;
import java.util.List;

public class SearchableMaze implements ISearchable {
    private Maze m;

    public SearchableMaze(Maze m) {
        this.m = m;
    }

    /**
     * @param s a state that we will find all the possible neighbors state
     * @return array of all the possible neighbors state (of target)
     */
    @Override
    public List<AState> getAllPossibleStates(AState s) {
        List<AState> stateNeighbor=new ArrayList<AState>();
        MazeState ms= (MazeState)s;
        Position p=ms.getMazeP();
        for (int i = p.getRowIndex()-1; i <p.getRowIndex()+2 && i>=0 && i<=m.getRows()-1 && p.getColumnIndex()<=m.getColumns()-1; i+=2) {
            if (m.getMaze()[i][p.getColumnIndex()]==0) {
                stateNeighbor.add(new MazeState(new Position(i, p.getColumnIndex())));
                if (p.getColumnIndex()-1 >=0)
                    if (m.getMaze()[i][p.getColumnIndex()-1]==0)
                        stateNeighbor.add(new MazeState(new Position(i, p.getColumnIndex()-1)));
                if (p.getColumnIndex()+1 <=m.getColumns()-1)
                    if (m.getMaze()[i][p.getColumnIndex()+1]==0)
                        stateNeighbor.add(new MazeState(new Position(i, p.getColumnIndex()+1)));
            }
        }
        for (int i = p.getColumnIndex()-1; i <p.getColumnIndex()+2 && i>=0 && i<=m.getColumns()-1 && p.getRowIndex()<=m.getRows()-1; i+=2) {
            if (m.getMaze()[p.getRowIndex()][i]==0) {
                stateNeighbor.add(new MazeState(new Position(p.getRowIndex(), i)));
                if (p.getRowIndex()-1 >=0)
                    if (m.getMaze()[p.getRowIndex()-1][i]==0)
                        stateNeighbor.add(new MazeState(new Position(p.getRowIndex()-1,i)));
                if (p.getRowIndex()+1 <=m.getRows()-1)
                    if (m.getMaze()[p.getRowIndex()+1][i]==0)
                        stateNeighbor.add(new MazeState(new Position(p.getRowIndex()+1,i)));
            }
        }
        if (p.getRowIndex()==0 && p.getColumnIndex()==0) {
            zeroStart(p,stateNeighbor);
        }
        return stateNeighbor;
    }

    public void zeroStart(Position p, List<AState>  stateNeighbor)
    {

        if (m.getColumns()>1) {
            p= new Position(0, 1);
            if (m.getMaze()[0][1] == 0)
                stateNeighbor.add(new MazeState(new Position(0,1)));
        }
        if(m.getRows()>1) {
            p = new Position(1, 0);
            if (m.getMaze()[1][0] == 0)
                stateNeighbor.add(new MazeState(new Position(1,0)));
        }
    }

    @Override
    public AState getSourceState() {
        Position p= this.m.getStartPosition();
        MazeState ms= new MazeState(p);
        return ms;
    }

    @Override
    public AState getTargetState() {
        Position p= this.m.getGoalPosition();
        MazeState ms= new MazeState(p);
        return ms;
    }
}
