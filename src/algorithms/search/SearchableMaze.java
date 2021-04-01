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
    public List<AState> getAllSuccessors(AState s) {
        List<AState> stateNeighbor=new ArrayList<AState>();
        Position p=((MazeState)s).getMazeP();
        for (int i = p.getRowIndex()-1; i <p.getRowIndex()+2 && i>=0 && i<=m.getRows()-1 && p.getColumnIndex()<=m.getColumns()-1; i+=2) {
            if (m.getMaze()[i][p.getColumnIndex()]==0) {
                addToList(s,new Position(i, p.getColumnIndex()), 10,stateNeighbor);
                if (p.getColumnIndex()-1 >=0)
                    if (m.getMaze()[i][p.getColumnIndex()-1]==0)
                        addToList(s,new Position(i, p.getColumnIndex() - 1), 15,stateNeighbor);
                if (p.getColumnIndex()+1 <=m.getColumns()-1)
                    if (m.getMaze()[i][p.getColumnIndex()+1]==0)
                        addToList(s,new Position(i, p.getColumnIndex() + 1), 15,stateNeighbor);
            }
        }
        for (int i = p.getColumnIndex()-1; i <p.getColumnIndex()+2 && i>=0 && i<=m.getColumns()-1 && p.getRowIndex()<=m.getRows()-1; i+=2) {
            if (m.getMaze()[p.getRowIndex()][i]==0) {
                addToList(s,new Position(p.getRowIndex(), i), 10,stateNeighbor);
                if (p.getRowIndex()-1 >=0)
                    if (m.getMaze()[p.getRowIndex()-1][i]==0)
                        addToList(s,new Position(p.getRowIndex() - 1, i), 15,stateNeighbor);
                if (p.getRowIndex()+1 <=m.getRows()-1)
                    if (m.getMaze()[p.getRowIndex()+1][i]==0)
                        addToList(s,new Position(p.getRowIndex()+1,i), 15,stateNeighbor);
            }
        }
        if (p.getRowIndex()==0 && p.getColumnIndex()==0) {
            zeroStart(s,p,stateNeighbor);
        }
        return stateNeighbor;
    }

    public void zeroStart(AState s,Position p, List<AState>  stateNeighbor)
    {

        if (m.getColumns()>1) {
            p= new Position(0, 1);
            if (m.getMaze()[0][1] == 0)
                addToList(s,new Position(0, 1), 10,stateNeighbor);
        }
        if(m.getRows()>1) {
            p = new Position(1, 0);
            if (m.getMaze()[1][0] == 0)
                addToList(s,new Position(1, 0), 10,stateNeighbor);
        }
    }

    @Override
    public AState getSourceState() {
        Position p= this.m.getStartPosition();
        MazeState ms= new MazeState(p,null);
        return ms;
    }

    public void addToList(AState s ,Position p, int cost, List<AState> stateNeighbor){
        MazeState msTmp = new MazeState(p, s);
        msTmp.setCost(cost);
        if (s!= null)
            msTmp.setCost(cost+s.getCost());
        stateNeighbor.add(msTmp);
    }

    @Override
    public AState getTargetState() {
        Position p= this.m.getGoalPosition();
        MazeState ms= new MazeState(p,p.getPreAState());
        return ms;
    }
}
