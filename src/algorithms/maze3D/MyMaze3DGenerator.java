package algorithms.maze3D;
import java.util.*;


public class MyMaze3DGenerator extends AMaze3DGenerator {
    List<Position3D> validNeighbor = new ArrayList<Position3D>(); //the valid cells neighbor of spacific cell
    Map<String, Integer> cellVisitedMap = new HashMap<String, Integer>(); // the visited cells map

    /**
     * @param depth the depth of the maze
     * @param row the row of the maze
     * @param column the column of the maze
     * @return a 3D maze
     */
    @Override
    public Maze3D generate(int depth, int row, int column) {
        if ((depth < 2 && row < 2 && column < 2) || row == 0 || column == 0 || depth == 0) {
            depth = 2;
            row = 2;
            column = 2;
        }
        int[][][] map = new int[depth][row][column];
        Maze3D maze = new Maze3D(null,null,map);
        setAllWall3D(maze);
        Random r = new Random();
        Position3D p =randomPos3DOnFrame(depth,row,column);
        cellVisitedMap.replace(p.toString(),1);
        maze.setStartPosition(p);
        maze.getMap()[p.getDepthIndex()][p.getRowIndex()][p.getColumnIndex()]=0;
        updateValidNeighbor3D(p,maze);
        while (validNeighbor.isEmpty()==false){
            int index=r.nextInt(validNeighbor.size());
            Position3D visitN = validNeighbor.remove(index);
            cellVisitedMap.replace(visitN.toString(),1);
            if(countVisitedNeighbor3D(visitN ,maze)==1){
                boolean isFrame = visitN.getRowIndex()==0 || visitN.getColumnIndex()==0 || visitN.getRowIndex()==maze.getRows()-1 || visitN.getColumnIndex()==maze.getColumns()-1;
                boolean notstart = visitN.getDepthIndex()!=maze.getStartPosition().getDepthIndex() || visitN.getRowIndex()!=maze.getStartPosition().getRowIndex() || visitN.getColumnIndex()!=maze.getStartPosition().getColumnIndex();
                if(isFrame && notstart){
                    maze.getMap()[visitN.getDepthIndex()][visitN.getRowIndex()][visitN.getColumnIndex()] = 0;
                    updateValidNeighbor3D(visitN,maze);
                    maze.setGoalPosition(visitN);
                }
                else{
                    maze.getMap()[visitN.getDepthIndex()][visitN.getRowIndex()][visitN.getColumnIndex()] = 0;
                    updateValidNeighbor3D(visitN,maze);
                    validNeighbor.remove(visitN);
                }
            }
        }
        if ( maze.getGoalPosition()==null)
            checkNullGoalPos3D(maze);
        return maze;

    }
    /**
     * @param m oue maze : set all the maze to walls
     */
    public void setAllWall3D(Maze3D m) {
        for (int i = 0; i < m.getDepth(); i++) {
            for (int j = 0; j < m.getRows(); j++) {
                for (int k = 0; k < m.getColumns(); k++) {
                    Position3D p = new Position3D(i,j,k);
                    cellVisitedMap.put(p.toString(), 0);
                    m.getMap()[i][j][k] = 1;
                }
            }
        }

    }
    /**
     * @param row    int : number of rows ( of the maze)
     * @param column int : number of columns ( of the maze)
     * @param depth int : number of depths ( of the maze)
     * @return Position value of random row and random column
     */
    public Position3D randomPos3DOnFrame (int depth ,int row,int column)
    {
        Random r = new Random(); // randomaize the start position of the algoritem
        int r_col = r.nextInt(column);
        int r_row = r.nextInt(row);
        int r_dep = r.nextInt(depth);
        while(r_col!=0 && r_col!=column-1 && r_row!=0 && r_row!=row-1){ //find position that not in the maze frame
            r_col = r.nextInt(column);
            r_row = r.nextInt(row);
            r_dep = r.nextInt(depth);
        }
        Position3D p = new Position3D(r_dep,r_row,r_col);
        return p;
    }
    /**
     * this func will update the valid neighbord list of p
     *
     * @param p current position to be check
     * @param m our maze
     */
    public void updateValidNeighbor3D(Position3D p , Maze3D m){
        Position3D temp_p;
        //check cell columns neighbords and add the neighbords that not visited
        int x = p.getColumnIndex()-1;
        if(p.getColumnIndex()-1==-1)
            x=1;
        for (int i = x; i < p.getColumnIndex()+2 && i>=0 && i<=m.getColumns()-1; i+=2) {
            temp_p = new Position3D(p.getDepthIndex(),p.getRowIndex(),i);
            if(cellVisitedMap.get(temp_p.toString())==0)
                validNeighbor.add(temp_p);

        }

        //check cell rows neighbords and add the neighbords that not visited
        x = p.getRowIndex()-1;
        if(p.getRowIndex()-1==-1)
            x=1;
        for (int i = x; i < p.getRowIndex()+2 && i>=0 && i<=m.getRows()-1; i+=2) {
            temp_p = new Position3D(p.getDepthIndex(),i,p.getColumnIndex());
            if(cellVisitedMap.get(temp_p.toString())==0)
                validNeighbor.add(temp_p);

        }

        //check cell depth neighbords and add the neighbords that not visited
        x = p.getDepthIndex()-1;
        if(p.getDepthIndex()-1==-1)
            x=1;
        for (int i = x; i < p.getDepthIndex()+2 && i>=0 && i<=m.getDepth()-1; i+=2) {
            temp_p = new Position3D(i,p.getRowIndex(),p.getColumnIndex());
            if(cellVisitedMap.get(temp_p.toString())==0)
                validNeighbor.add(temp_p);

        }


    }
    /**
     * @param p current position to be check
     * @param m our maze
     * @return number of visited neighbords of current position
     */
    public int countVisitedNeighbor3D(Position3D p , Maze3D m){
        Position3D temp_p;
        int c = 0;
        //check cell columns neighbords and add the neighbords that not visited
        int x = p.getColumnIndex()-1;
        if(p.getColumnIndex()-1==-1)
            x=1;
        for (int i = x; i < p.getColumnIndex()+2 && i>=0 && i<=m.getColumns()-1; i+=2) {
            temp_p = new Position3D(p.getDepthIndex(),p.getRowIndex(),i);
            if(cellVisitedMap.get(temp_p.toString())==1)
                c++;

        }

        //check cell rows neighbords and add the neighbords that not visited
        x = p.getRowIndex()-1;
        if(p.getRowIndex()-1==-1)
            x=1;
        for (int i = x; i < p.getRowIndex()+2 && i>=0 && i<=m.getRows()-1; i+=2) {
            temp_p = new Position3D(p.getDepthIndex(),i,p.getColumnIndex());
            if(cellVisitedMap.get(temp_p.toString())==1)
                c++;

        }
        x = p.getDepthIndex()-1;
        if(p.getDepthIndex()-1==-1) x=1;
        for (int i = x; i < p.getDepthIndex()+2 && i>=0 && i<=m.getDepth()-1; i+=2) {
            temp_p = new Position3D(i,p.getRowIndex(),p.getColumnIndex());
            if(cellVisitedMap.get(temp_p.toString())==1)
                c++;

        }
        return c;

    }
    /**
     * @param m Maze to check if goalPosition is null
     */
    public void checkNullGoalPos3D (Maze3D m){
        if ( m.getGoalPosition()==null)
        {
            List<Position3D> neighbStart = new ArrayList<Position3D>();
            if ( m.getStartPosition().getRowIndex() +1 < m.getRows())
                neighbStart.add(new Position3D(m.getStartPosition().getDepthIndex(),m.getStartPosition().getRowIndex() +1,m.getStartPosition().getColumnIndex())) ;
            if ( m.getStartPosition().getRowIndex() -1 >= 0 )
                neighbStart.add(new Position3D(m.getStartPosition().getDepthIndex(),m.getStartPosition().getRowIndex() -1,m.getStartPosition().getColumnIndex())) ;
            if ( m.getStartPosition().getColumnIndex()+1 < m.getColumns())
                neighbStart.add(new Position3D(m.getStartPosition().getDepthIndex(),m.getStartPosition().getRowIndex() ,m.getStartPosition().getColumnIndex()+1)) ;
            if ( m.getStartPosition().getColumnIndex()-1 >= 0)
                neighbStart.add(new Position3D(m.getStartPosition().getDepthIndex(),m.getStartPosition().getRowIndex() ,m.getStartPosition().getColumnIndex()-1)) ;
            if ( m.getStartPosition().getDepthIndex()+1 < m.getDepth())
                neighbStart.add(new Position3D(m.getStartPosition().getDepthIndex()+1,m.getStartPosition().getRowIndex() ,m.getStartPosition().getColumnIndex()+1)) ;
            if ( m.getStartPosition().getDepthIndex()-1 >= 0)
                neighbStart.add(new Position3D(m.getStartPosition().getDepthIndex()-1,m.getStartPosition().getRowIndex() ,m.getStartPosition().getColumnIndex()-1)) ;
            Collections.shuffle(neighbStart);
            Position3D p = neighbStart.get(0);
            m.getMap()[p.getDepthIndex()][p.getRowIndex()][p.getColumnIndex()] = 0;
            m.setGoalPosition(p);
        }
    }
}
