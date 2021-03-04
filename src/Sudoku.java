public class Sudoku implements SudokuSolver {
    private int[][] sudoku;

    public Sudoku(){
        sudoku = new int[9][9];
    }

    @Override
    public boolean isOutOfBounds(int r, int c) {
        return !(0 <= r && r < 9 && 0 <= c && c < 9);
    }

    @Override
    public void setNumber(int r, int c, int nbr) {
        if(isOutOfBounds(r, c)){
            throw new IllegalArgumentException("r = " + r + ", c = " + c);
        }else{
            if(nbr < 1 || nbr > 9){
                throw new IllegalArgumentException("nbr = " + nbr);
            }else{
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
