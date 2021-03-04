import java.util.Arrays;

public class Sudoku implements SudokuSolver {
    private int[][] sudoku;
    //nio siffror som sedan har 9 positioner som kollar om en siffra finns på en row eller på en rad, representerat av
    //true eller false
    private boolean[][] booleanRow;
    private boolean[][] booleanCol;
    private boolean[][][] booleanBox;



    public Sudoku(){
        sudoku = new int[9][9];
        //index 1-9, 0-8 osv
        booleanRow = new boolean[10][9];
        booleanCol = new boolean[10][9];
        booleanBox = new boolean[10][3][3];
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
                booleanBox[nbr][r / 3][c / 3] = true;
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
            booleanBox[sudoku[r][c]][r / 3][c / 3] = false;
            sudoku[r][c] = 0;
        }

    }

    @Override
    public boolean isValid(int r, int c, int nbr) {
        if(isOutOfBounds(r, c))
            throw new IllegalArgumentException("r = " + r + ", c = " + c);
        
        return !(booleanRow[nbr][r] || booleanCol[nbr][c] || booleanBox[nbr][r / 3][c / 3]);
    }

    @Override
    public boolean isAllValid() {
        int[][]temp = new int[9][9];

        //kopiera över elementen i gamla till nya
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                temp[x][y] = sudoku[x][y];
            }
        }

        //töm vårt sudoku
        this.clear();

        boolean allValid = true;

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                int nbr = temp[r][c];
                if (nbr != 0) {
                    if (!isValid(r, c, nbr))
                        allValid = false;
                    setNumber(r, c, nbr);
                }
            }
        }

        return allValid;
    }

    @Override
    public boolean solve() {
        return false;
    }

    @Override
    public void clear() {
        sudoku = new int[9][9];
        booleanRow = new boolean[10][9];
        booleanCol = new boolean[10][9];
        booleanBox = new boolean[10][3][3];
    }

    @Override
    public int[][] getMatrix() {
        int[][]temp = new int[9][9];

        //kopiera över elementen i gamla till nya
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                temp[x][y] = sudoku[x][y];
            }
        }
        return temp;
    }

    @Override
    public void setMatrix(int[][] nbrs) {
        sudoku = nbrs;
    }
}
