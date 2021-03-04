public class Sudoku implements SudokuSolver {
    private int[][] sudoku;
    //nio siffror som sedan har 9 positioner som kollar om en siffra finns på en row eller på en rad, representerat av
    //true eller false
    private boolean[][] booleanRow;
    private boolean[][] booleanCol;


    public Sudoku(){
        sudoku = new int[9][9];
        //index 0-9 där index 0 i de 10 första positionerna inte används. Så slipper vi ta -1 på alla nummer som skrivs in
        booleanRow = new boolean[10][9];
        booleanCol = new boolean[10][9];
    }

    @Override
    public boolean isOutOfBounds(int r, int c) {
        return false;
    }

    @Override
    public void setNumber(int r, int c, int nbr) {
        //-1 för att siffran 1 motsvarar pos 0. Behandla row och col med -1 vid i
        booleanRow[nbr][r] = true;
        booleanCol[nbr][c] = true;
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
        //kolla om det går med row och col att stoppa in ett nummer, om det går, kolla om det går att stoppa in numret
        //i en box

        //lägg till kontroll i boxen
        if (booleanRow[nbr][r] || booleanCol[nbr][c])
            return false;
        return true;
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
