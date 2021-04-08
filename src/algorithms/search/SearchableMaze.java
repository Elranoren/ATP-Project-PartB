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
     * @return list of all the possible neighbors state (of state s)
     */
    @Override
    public List<AState> getAllSuccessors(AState s) {
        List<AState> stateNeighbor=new ArrayList<AState>();
        Position p=((MazeState)s).getMazeP();
        int x = p.getRowIndex()-1;
        if(p.getRowIndex()-1==-1)
            x=1;
        for (int i=x; i <p.getRowIndex()+2 && i>=0 && i<=m.getRows()-1 && p.getColumnIndex()<=m.getColumns()-1; i+=2) {
            if (m.getMaze()[i][p.getColumnIndex()]==0) {
                if (p.getColumnIndex()-1 >=0)
                    if (m.getMaze()[i][p.getColumnIndex()-1]==0)
                        addToList(s,new Position(i, p.getColumnIndex() - 1), 15,stateNeighbor);
                if (p.getColumnIndex()+1 <=m.getColumns()-1)
                    if (m.getMaze()[i][p.getColumnIndex()+1]==0)
                        addToList(s,new Position(i, p.getColumnIndex() + 1), 15,stateNeighbor);
                addToList(s,new Position(i, p.getColumnIndex()), 10,stateNeighbor);
            }
        }
        x = p.getColumnIndex()-1;
        if(p.getColumnIndex()-1==-1)
            x=1;
        for (int i = x; i <p.getColumnIndex()+2 && i>=0 && i<=m.getColumns()-1 && p.getRowIndex()<=m.getRows()-1; i+=2) {
            if (m.getMaze()[p.getRowIndex()][i]==0) {
                if (p.getRowIndex()-1 >=0)
                    if (m.getMaze()[p.getRowIndex()-1][i]==0)
                        addToList(s,new Position(p.getRowIndex() - 1, i), 15,stateNeighbor);
                if (p.getRowIndex()+1 <=m.getRows()-1)
                    if (m.getMaze()[p.getRowIndex()+1][i]==0)
                        addToList(s,new Position(p.getRowIndex()+1,i), 15,stateNeighbor);
                addToList(s,new Position(p.getRowIndex(), i), 10,stateNeighbor);
            }
        }

        return stateNeighbor;
    }

    /**
     * @param s the presuccessor of "p"
     * @param p  position value : possible neighbors state (of state s)
     * @param cost the cost of the movement from "s" to "p"
     * @param stateNeighbor list of all the possible neighbors state (of state s)
     */
    public void addToList(AState s ,Position p, double cost, List<AState> stateNeighbor){
        MazeState msTmp = new MazeState(p, s,p.toString());
        msTmp.setStateName(msTmp.getStateName());
        msTmp.setCost(cost);
        if (s!= null)
            msTmp.setCost(cost+s.getCost());
        stateNeighbor.add(msTmp);
    }


    @Override
    public AState getSourceState() {
        Position p= this.m.getStartPosition();
        MazeState ms= new MazeState(p,null,p.toString());
        return ms;
    }


    @Override
    public AState getTargetState() {
        Position p= this.m.getGoalPosition();
        MazeState ms= new MazeState(p,null,p.toString());
        return ms;
    }
}
