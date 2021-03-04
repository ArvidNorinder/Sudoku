public class Sudoku implements SudokuSolver {
    private int[][] sudoku;
    //nio siffror som sedan har 9 positioner som kollar om en siffra finns på en row eller på en rad, representerat av
    //true eller false
    private boolean[][] booleanRow;
    private boolean[][] booleanCol;


    public Sudoku(){
        sudoku = new int[9][9];
        //index 0-8 osv
        booleanRow = new boolean[9][9];
        booleanCol = new boolean[9][9];
    }

    @Override
    public boolean isOutOfBounds(int r, int c) {
        return false;
    }

    @Override
    public void setNumber(int r, int c, int nbr) {
        //-1 för att siffran 1 motsvarar pos 0. Behandla row och col med -1 vid i
        booleanRow[nbr - 1][r] = true;
        booleanCol[nbr - 1][c] = true;
        sudoku[r][c] = nbr;
    }

    @Override
    public int getNumber(int r, int c) {
        return sudoku[r][c];
    }

    @Override
    public void clearNumber(int r, int c) {
        sudoku[r][c] = 0;
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
