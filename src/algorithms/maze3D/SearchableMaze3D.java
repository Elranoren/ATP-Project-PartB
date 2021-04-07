package algorithms.maze3D;

import algorithms.mazeGenerators.Position;
import algorithms.search.AState;
import algorithms.search.ISearchable;
import algorithms.search.MazeState;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchableMaze3D implements ISearchable {
    private Maze3D m;

    public SearchableMaze3D(Maze3D m) {
        this.m = m;
    }

    @Override
    public List<AState> getAllSuccessors(AState s) {
        List<AState> stateNeighbor=new ArrayList<AState>();
        Position3D p=((Maze3DState)s).getMazeP();
        int x = p.getRowIndex()-1;
        if(p.getRowIndex()-1==-1)
            x=1;
        for (int i=x; i <p.getRowIndex()+2 && i>=0 && i<=m.getRows()-1 && p.getColumnIndex()<=m.getColumns()-1; i+=2) {
            if (m.getMap()[p.getDepthIndex()][i][p.getColumnIndex()]==0) {
                if (p.getColumnIndex()-1 >=0)
                    if (m.getMap()[p.getDepthIndex()][i][p.getColumnIndex()-1]==0)
                        addToList3D(s,new Position3D(p.getDepthIndex(),i, p.getColumnIndex() - 1), 15,stateNeighbor);
                if (p.getColumnIndex()+1 <=m.getColumns()-1)
                    if (m.getMap()[p.getDepthIndex()][i][p.getColumnIndex()+1]==0)
                        addToList3D(s,new Position3D(p.getDepthIndex(),i, p.getColumnIndex() + 1), 15,stateNeighbor);
                addToList3D(s,new Position3D(p.getDepthIndex(),i, p.getColumnIndex()), 10,stateNeighbor);
            }
        }
        x = p.getColumnIndex()-1;
        if(p.getColumnIndex()-1==-1)
            x=1;
        for (int i = x; i <p.getColumnIndex()+2 && i>=0 && i<=m.getColumns()-1 && p.getRowIndex()<=m.getRows()-1; i+=2) {
            if (m.getMap()[p.getDepthIndex()][p.getRowIndex()][i]==0) {
                if (p.getRowIndex()-1 >=0)
                    if (m.getMap()[p.getDepthIndex()][p.getRowIndex()-1][i]==0)
                        addToList3D(s,new Position3D(p.getDepthIndex(),p.getRowIndex() - 1, i), 15,stateNeighbor);
                if (p.getRowIndex()+1 <=m.getRows()-1)
                    if (m.getMap()[p.getDepthIndex()][p.getRowIndex()+1][i]==0)
                        addToList3D(s,new Position3D(p.getDepthIndex(),p.getRowIndex()+1,i), 15,stateNeighbor);
                addToList3D(s,new Position3D(p.getDepthIndex(),p.getRowIndex(), i), 10,stateNeighbor);
            }
        }

        x = p.getDepthIndex()-1;
        if(p.getDepthIndex()-1==-1)
            x=1;
        for (int i = x; i <p.getDepthIndex()+2 && i>=0 && i<=m.getDepth()-1 && p.getDepthIndex()<=m.getDepth()-1; i+=2) {
            if (m.getMap()[i][p.getRowIndex()][p.getColumnIndex()]==0) {
                addToList3D(s,new Position3D(i,p.getRowIndex(), p.getColumnIndex()), 10,stateNeighbor);
            }
        }
        return stateNeighbor;
    }

    public void addToList3D(AState s ,Position3D p, double cost, List<AState> stateNeighbor){
        Maze3DState msTmp = new Maze3DState(p, s);
        msTmp.setCost(cost);
        if (s!= null)
            msTmp.setCost(cost+s.getCost());
        stateNeighbor.add(msTmp);
    }

    @Override
    public AState getSourceState() {
        Position3D p= this.m.getStartPosition();
        Maze3DState ms= new Maze3DState(p,null);
        return ms;
    }

    @Override
    public AState getTargetState() {
        Position3D p= this.m.getGoalPosition();
        Maze3DState ms= new Maze3DState(p,p.getPreAState());
        return ms;
    }
}
