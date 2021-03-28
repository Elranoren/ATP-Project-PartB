package algorithms.mazeGenerators;


import java.util.Random;

public class SimpleMazeGenerator extends AMazeGenerator{
    @Override
    public Maze generate(int rows, int columns) {
        if(rows<2 && columns<2){
            rows=2;
            columns=2;
        }
        Maze m = new EmptyMazeGenerator().generate(rows,columns);
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                if(Math.random()>0.5)
                    m.getMaze()[i][j]=1;
            }

        }
        setStartGoalPos(m);
        setsol(m);
        return m;
    }
    public void setStartGoalPos(Maze m){
        Random r = new Random();
        int indexStart = r.nextInt(m.getColumns());
        int indexEnd = r.nextInt(m.getColumns());
        m.setStartPosition(new Position(0,indexStart));
        m.setGoalPosition(new Position(m.getRows()-1,indexEnd));

    }

    public void setsol(Maze m) {
        for (int i = 1; i <= m.getRows()/2; i++) {
                m.getMaze()[i][m.getStartPosition().getColumnIndex()]=0;
            }
        if (m.getStartPosition().getColumnIndex() <=m.getGoalPosition().getColumnIndex() ) {
            for (int i = m.getStartPosition().getColumnIndex(); i <= m.getGoalPosition().getColumnIndex(); i++) {
                m.getMaze()[m.getRows() / 2][i] = 0;
            }
        }
        else
        {
            for (int i = m.getGoalPosition().getColumnIndex(); i <= m.getStartPosition().getColumnIndex(); i++) {
                m.getMaze()[m.getRows() / 2][i] = 0;
            }
        }

        for (int i = (m.getRows()/2)+1; i < m.getRows(); i++) {
            m.getMaze()[i][m.getGoalPosition().getColumnIndex()] = 0;
        }

    }


}
