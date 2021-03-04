public class Sudoku implements SudokuSolver {
    private int[][] sudoku;

    public Sudoku(){
        sudoku = new int[9][9];
    }

    @Override
    public boolean isOutOfBounds(int r, int c) {
        
        return 0 <= r && r < 9 && 0 <= c && c < 9;
    }

    @Override
    public void setNumber(int r, int c, int nbr) {
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
