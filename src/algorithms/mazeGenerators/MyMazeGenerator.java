package algorithms.mazeGenerators;
import java.util.*;

public class MyMazeGenerator extends AMazeGenerator{
    List<Position> validNeighbor = new ArrayList<Position>(); //the valid cells neighbor of spacific cell
    Map<String,Integer> cellVisitedMap = new HashMap<String,Integer>(); // the visited cells map

    @Override
    public Maze generate(int rows, int columns) {
        if(rows<2||columns<2){
            rows=2;
            columns=2;
        }
        Maze m = new Maze(rows,columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Position p = new Position(i,j);
                cellVisitedMap.put(p.toString(),0);
                m.getMaze()[i][j] = 1;

            }

        }
        Random r = new Random(); // randomaize the start position of the algoritem
        int r_col = r.nextInt(columns);
        int r_row = r.nextInt(rows);
        while(r_col!=0 && r_col!=columns-1 && r_row!=0 && r_row!=rows-1){ //find position that not in the maze frame
            r_col = r.nextInt(columns);
            r_row = r.nextInt(rows);
        }
        Position p = new Position(r_row,r_col);
        cellVisitedMap.replace(p.toString(),1);
        m.setStartPosition(p);
        m.getMaze()[r_row][r_col]=0;
        updateValidNeighbor(p,m);
        while (validNeighbor.isEmpty()==false){
            int index=r.nextInt(validNeighbor.size());
            Position visitN = validNeighbor.remove(index);
            cellVisitedMap.replace(visitN.toString(),1);
            if(countVisitedNeighbor(visitN ,m)==1){
                boolean isFrame = visitN.getRowIndex()==0 || visitN.getColumnIndex()==0 || visitN.getRowIndex()==m.getRows()-1 || visitN.getColumnIndex()==m.getColumns()-1;
                boolean notstart = visitN.getRowIndex()!=m.getStartPosition().getRowIndex() || visitN.getColumnIndex()!=m.getStartPosition().getColumnIndex();
                if(isFrame && notstart){
                    m.getMaze()[visitN.getRowIndex()][visitN.getColumnIndex()] = 0;
                    updateValidNeighbor(visitN,m);
                    m.setGoalPosition(visitN);
                }
                else{
                    m.getMaze()[visitN.getRowIndex()][visitN.getColumnIndex()] = 0;
                    updateValidNeighbor(visitN,m);
                    validNeighbor.remove(visitN);
                }
            }


        }
        return m;


    }

    /**
     * this func will update the valid neighbord list of p
     * @param p current position to be check
     * @param m our maze
     */
    public void updateValidNeighbor(Position p , Maze m){
        Position temp_p;
        //check cell columns neighbords and add the neighbords that not visited
        for (int i = p.getColumnIndex()-1; i < p.getColumnIndex()+2 && i>=0 && i<=m.getColumns()-1; i+=2) {
            temp_p = new Position(p.getRowIndex(),i);
            if(cellVisitedMap.get(temp_p.toString())==0)
                validNeighbor.add(temp_p);

        }

        //check cell rows neighbords and add the neighbords that not visited
        for (int i = p.getRowIndex()-1; i < p.getRowIndex()+2 && i>=0 && i<=m.getRows()-1; i+=2) {
            temp_p = new Position(i,p.getColumnIndex());
            if(cellVisitedMap.get(temp_p.toString())==0)
                validNeighbor.add(temp_p);

        }

    }

    /**
     * @param p current position to be check
     * @param m our maze
     * @return number of visited neighbords of current position
     */
    public int countVisitedNeighbor(Position p , Maze m){
        Position temp_p;
        int c = 0;
        //check cell columns neighbords and add the neighbords that not visited
        for (int i = p.getColumnIndex()-1; i < p.getColumnIndex()+2 && i>=0 && i<=m.getColumns()-1; i+=2) {
            temp_p = new Position(p.getRowIndex(),i);
            if(cellVisitedMap.get(temp_p.toString())==1)
                c++;

        }

        //check cell rows neighbords and add the neighbords that not visited
        for (int i = p.getRowIndex()-1; i < p.getRowIndex()+2 && i>=0 && i<=m.getRows()-1; i+=2) {
            temp_p = new Position(i,p.getColumnIndex());
            if(cellVisitedMap.get(temp_p.toString())==1)
                c++;

        }
        return c;

    }
}
