public class Sudoku implements SudokuSolver {
    private int[][] sudoku;
    //nio siffror som sedan har 9 positioner som kollar om en siffra finns på en row eller på en rad, representerat av
    //true eller false
    private boolean[][] booleanRow;
    private boolean[][] booleanCol;



    public Sudoku(){
        sudoku = new int[9][9];
        //index 1-9, 0-8 osv
        booleanRow = new boolean[10][9];
        booleanCol = new boolean[10][9];
    }

    @Override
    public boolean isOutOfBounds(int r, int c) {
        return !(0 <= r && r < 9 && 0 <= c && c < 9);
    }

    @Override
    public void setNumber(int r, int c, int nbr) {
        //-1 för att siffran 1 motsvarar pos 0. Behandla row och col med -1 vid i
        
        sudoku[r][c] = nbr;
        if(isOutOfBounds(r, c)){
            throw new IllegalArgumentException("r = " + r + ", c = " + c);
        }else{
            if(nbr < 1 || nbr > 9){
                throw new IllegalArgumentException("nbr = " + nbr);
            }else{
                booleanRow[nbr][r] = true;
                booleanCol[nbr][c] = true;
                sudoku[r][c] = nbr;
            }
        }
    }

    @Override
    public int getNumber(int r, int c) {
        if(isOutOfBounds(r, c)){
            throw new IllegalArgumentException("r = " + r + ", c = " + c);
        }else{
            return sudoku[r][c];
        }
    }

    @Override
    public void clearNumber(int r, int c) {
        if(isOutOfBounds(r, c)){
            throw new IllegalArgumentException("r = " + r + ", c = " + c);
        }else{
            booleanRow[sudoku[r][c]][r] = false;
            booleanCol[sudoku[r][c]][c] = false;
            sudoku[r][c] = 0;

        }

    }

    @Override
    public boolean isValid(int r, int c, int nbr) {
        return false;
    }

    @Override
    public boolean isAllValid() {
        return false;
    }

    @Override
    public boolean solve() {
        return false;
    }

    @Override
    public void clear() {
        sudoku = new int[9][9];
    }

    @Override
    public int[][] getMatrix() {
        return sudoku;
    }

    @Override
    public void setMatrix(int[][] nbrs) {
        sudoku = nbrs;
    }
}
