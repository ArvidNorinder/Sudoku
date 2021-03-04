public interface SudokuSolver {

    /**
     * Returns false if the position r,c is outside the sudoku game borders
     * 
     * @param r row
     * 
     * @param c col
     *
     * @return as said in description
     * 
     */
    public boolean isOutOfBounds(int r, int c);


    /**
     * Sets the number nbr in box r, c.
     *
     * @param r
     *            The row
     * @param c
     *            The column
     * @param nbr
     *            The number to insert in box r, c
     * @throws IllegalArgumentException
     *             if r or c is outside [0..getDimension()-1] or
     *             number is outside [1..9]
     */
    public void setNumber(int r, int c, int nbr);

    /**
     * Returns the number in box r,c. If the box i empty 0 is returned.
     *
     * @param r
     *            The row
     * @param c
     *            The column
     * @return the number in box r,c or 0 if the box is empty.
     *
     * @throws IllegalArgumentException
     *             if r or c is outside [0..getDimension()-1]
     */
    public int getNumber(int r, int c);

    /**
     * Clears the number in box r,c. If the box is empty nothing happens.
     * @param r The row
     * @param c The column
     *
     * @throws IllegalArgumentException if r or c is outside [0..getDimension()-1]
     */
    public void clearNumber(int r, int c);

    /**
     * Checks if a number to be inserted into a box is valid according to sudoku rules
     *
     * @param r The row
     * @param c The column
     * @param nbr The number to check if valid
     *
     * @return true if the number would be valid in the box, otherwise false
     *
     * @throws IllegalArgumentException if r or c is outside [0..getDimension()-1]
     */
    public boolean isValid(int r, int c, int nbr);

    /**
     * Checks to see if all insertions are valid so far
     *
     * @return true if all is valid so far, otherwise false
     */
    public boolean isAllValid();

    /**
     * Checks to see if the sudoku is solveable, otherwise returns false
     *
     * @return true if the puzzle is solveable, otherwise false
     */
    public boolean solve();

    /**
     * Empties all the boxes in the sudoku puzzle.
     */
    public void clear();

    /**
     * Returns the numbers in the grid. An empty box i represented
     * by the value 0.
     *
     * @return the numbers in the grid
     */
    public int[][] getMatrix();

    /**
     * Fills the grid with the numbers in nbrs.
     *
     * @param nbrs the matrix with the numbers to insert
     * @throws IllegalArgumentException
     *             if nbrs have wrong dimension or containing values not in [0..9]
     */
    public void setMatrix(int[][] nbrs);


    /**
     * Returns the dimension of the grid
     *
     * @return the dimension of the grid
     */
    public default int getDimension() {
        return 9;
    }

}