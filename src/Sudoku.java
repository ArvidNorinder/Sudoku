public class Sudoku implements SudokuSolver {
    private int[][] sudoku = new int[9][9];
    //nio siffror som sedan har 9 positioner som kollar om en siffra finns på en row eller på en rad, representerat av
    //true eller false
    private int[][] counterRow = new int[10][9];
    private int[][] counterCol = new int[10][9];
    private int[][][] counterBox = new int[10][3][3];

    private boolean isOutOfBounds(int r, int c) {
        return !(0 <= r && r < 9 && 0 <= c && c < 9);
    }

    @Override
    public void setNumber(int r, int c, int nbr) {
        
        if(isOutOfBounds(r, c)){
            throw new IllegalArgumentException("r = " + r + ", c = " + c);
        }else{
            if(nbr < 0 || nbr > 9){
                throw new IllegalArgumentException("nbr = " + nbr);
            }else{
                clearNumber(r, c);
                counterRow[nbr][r]++;
                counterCol[nbr][c]++;
                counterBox[nbr][r / 3][c / 3]++;
                sudoku[r][c] = nbr;
            }
        }
    }

    @Override
    public int getNumber(int r, int c) {
        if(isOutOfBounds(r, c))
            throw new IllegalArgumentException("r = " + r + ", c = " + c);
        
        return sudoku[r][c];
        
    }

    @Override
    public void clearNumber(int r, int c) {
        if(isOutOfBounds(r, c)){
            throw new IllegalArgumentException("r = " + r + ", c = " + c);
        }else{
            counterRow[sudoku[r][c]][r]--;
            counterCol[sudoku[r][c]][c]--;
            counterBox[sudoku[r][c]][r / 3][c / 3]--;
            sudoku[r][c] = 0;
        }

    }

    @Override
    public boolean isValidPut(int r, int c, int nbr){

        if(isOutOfBounds(r, c))
            throw new IllegalArgumentException("r = " + r + ", c = " + c);

        if(nbr < 1 || nbr > 9)
            throw new IllegalArgumentException("nbr = " + nbr);

        return counterRow[nbr][r] == 0 && counterCol[nbr][c] == 0 && counterBox[nbr][r / 3][c / 3] == 0;
    }

    @Override
    public boolean isValid(int r, int c) {
        if(isOutOfBounds(r, c))
            throw new IllegalArgumentException("r = " + r + ", c = " + c);
        
        int nbr = sudoku[r][c];
        
        return nbr == 0 || (counterRow[nbr][r] == 1 && counterCol[nbr][c] == 1 && counterBox[nbr][r / 3][c / 3] == 1);
        
    }

    @Override
    public boolean isAllValid() {
        for(int r = 0; r < 9; r++){
            for(int c = 0; c < 9; c++){
                if(!isValid(r, c))
                    return false;
            }
        }
        return true;
    }

    @Override
    public boolean solve() {
        if(isAllValid()){
            return solve(0,0);
        }else{
            return false;
        }
        
    }

    private boolean solve(int r, int c){
        if(getNumber(r,c) != 0){
            if(isValid(r,c)){
                if(c == 8){
                    if(r == 8){
                        return true;
                    }else{
                        return solve(r + 1, 0);
                    }
                }else{
                    return solve(r, c + 1);
                }
            }else{
                System.out.println(r + ", " + c);
                return false;
            }
        }else{
            boolean success = false;

            for(int i = 1; i <= 9 && !success; i++){
                if(isValidPut(r, c, i)){
                    setNumber(r, c, i);
                    if(c == 8){
                        if(r == 8){
                            success = true;
                        }else{
                            success = solve(r + 1, 0);
                        }
                    }else{
                        success = solve(r, c + 1);
                    }
                }
            }
            if(!success)
                clearNumber(r, c);
            return success;
        }
    }

    @Override
    public void clear() {
        sudoku = new int[9][9];
        counterRow = new int[10][9];
        counterCol = new int[10][9];
        counterBox = new int[10][3][3];
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
        clear();
        for(int r = 0; r < 9; r++){
            for(int c = 0; c < 9; c++){
                setNumber(r, c, nbrs[r][c]);
            }
        }
    }

    @Override
    public void printMatrix(){
        for(int r = 0; r < 9; r++){
            if(r % 3 == 0)
                System.out.println();

            for(int c = 0; c < 9; c++){
                if(c % 3 == 0)
                    System.out.print("   ");
                if(sudoku[r][c] != 0){
                    System.out.print(sudoku[r][c]);
                }else{
                    System.out.print('.');
                }
            }
            System.out.println();
            
        }
        System.out.println();
    }
}