package algorithms.mazeGenerators;


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
                if(i==0 && j==0) {
                    m.setStartPosition(new Position(i, j));
                    continue;
                }
                if(i==rows-1 && j==columns-1) {
                    m.setGoalPosition(new Position(i, j));
                    continue;
                }
                if(Math.random()>0.5)
                    m.getMaze()[i][j]=1;
            }

        }
        setsol(m);
        return m;
    }
    public void setsol(Maze m) {
        for (int i = 1; i <= m.getRows()/2; i++) {
                m.getMaze()[i][0]=0;
            }
        for (int i = 0; i < m.getColumns(); i++) {
            m.getMaze()[m.getRows()/2][i]=0;
        }

        for (int i = (m.getRows()/2)+1; i < m.getRows(); i++) {
            m.getMaze()[i][m.getColumns()-1] = 0;
        }

    }


}
