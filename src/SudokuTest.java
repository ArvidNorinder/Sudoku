import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuTest {
    Sudoku sudoku;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        sudoku = new Sudoku();
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        sudoku = null;
    }

    @org.junit.jupiter.api.Test
    void setNumber() {
        assertThrows(IllegalArgumentException.class, () -> sudoku.setNumber(-20, 100, 8));
        assertThrows(IllegalArgumentException.class, () -> sudoku.setNumber(5, 2, 13));
        assertThrows(IllegalArgumentException.class, () -> sudoku.setNumber(10, 2, 1));
        assertThrows(IllegalArgumentException.class, () -> sudoku.setNumber(0, 2, 1));

        assertDoesNotThrow(() -> sudoku.setNumber(3, 7, 8));
        assertDoesNotThrow(() -> sudoku.setNumber(2, 5, 6));
    }

    @org.junit.jupiter.api.Test
    void getNumber() {
        assertThrows(IllegalArgumentException.class, () -> sudoku.getNumber(-20, 100));
        assertThrows(IllegalArgumentException.class, () -> sudoku.getNumber(10, 2));
        assertThrows(IllegalArgumentException.class, () -> sudoku.getNumber(0, 2));

        sudoku.setNumber(3, 7, 8);
        sudoku.setNumber(5, 2, 8);

        assertDoesNotThrow(() -> sudoku.getNumber(3, 7));
        assertTrue(sudoku.getNumber(3, 7) == 8);

        assertDoesNotThrow(() -> sudoku.getNumber(5, 2));
        assertTrue(sudoku.getNumber(5, 2) == 8);
    }

    @org.junit.jupiter.api.Test
    void clearNumber() {
        sudoku.setNumber(4, 4, 8);
        assertTrue(sudoku.getNumber(4, 4) == 8);

        sudoku.clearNumber(4, 4);
        assertTrue(sudoku.getNumber(4, 4) == 0);
    }

    @org.junit.jupiter.api.Test
    void isValidPut() {
        sudoku.setNumber(1, 1, 1);
        assertFalse(sudoku.isValidPut(1,2, 1));
        assertTrue(sudoku.isValidPut(1, 2, 2));
        assertFalse(sudoku.isValidPut(8,1, 1));
    }

    @org.junit.jupiter.api.Test
    void isValid() {
        sudoku.setNumber(1, 1, 1);
        assertTrue(sudoku.isValid(1,1));
        sudoku.setNumber(1, 8, 1);
        assertFalse(sudoku.isValid(1,1));

        sudoku.setNumber(8, 1, 1);
        sudoku.setNumber(2, 2, 1);


        assertFalse(sudoku.isValid(1, 8));
        assertFalse(sudoku.isValid(8,1));
        assertFalse(sudoku.isValid(2,2));
    }

    @org.junit.jupiter.api.Test
    void isAllValid() {
        sudoku.setNumber(1, 1, 3);
        sudoku.setNumber(1, 2, 4);
        sudoku.setNumber(1, 3, 5);
        sudoku.setNumber(1, 4, 6);

        assertTrue(sudoku.isAllValid());

        sudoku.setNumber(1, 5, 4);

        assertFalse(sudoku.isAllValid());
    }

    @org.junit.jupiter.api.Test
    void setMatrixAndGetMatrix() {
        int[][] testMatrix = {
                {0,0,8,0,0,9,0,6,2},
                {0,0,0,0,0,0,0,0,5},
                {1,0,2,5,0,0,0,0,0},
                {0,0,0,2,1,0,0,9,0},
                {0,5,0,0,0,0,6,0,0},
                {6,0,0,0,0,0,0,2,8},
                {4,1,0,6,0,8,0,0,0},
                {8,6,0,0,3,0,1,0,0},
                {0,0,0,0,0,0,4,0,0}
        };

        sudoku.setMatrix(testMatrix);

        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                assertTrue(sudoku.getMatrix()[x][y] == testMatrix[x][y]);
            }
        }
    }

    @org.junit.jupiter.api.Test
    void solveEmpty() {
        sudoku.solve();

        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                assertTrue(sudoku.getMatrix()[x][y] != 0);
            }
        }

        assertTrue(sudoku.isAllValid());
    }

    @Test
    void solveSolveable() {
        int[][] testMatrix = {
                {0,0,8,0,0,9,0,6,2},
                {0,0,0,0,0,0,0,0,5},
                {1,0,2,5,0,0,0,0,0},
                {0,0,0,2,1,0,0,9,0},
                {0,5,0,0,0,0,6,0,0},
                {6,0,0,0,0,0,0,2,8},
                {4,1,0,6,0,8,0,0,0},
                {8,6,0,0,3,0,1,0,0},
                {0,0,0,0,0,0,4,0,0}
        };

        sudoku.setMatrix(testMatrix);

        assertTrue(sudoku.solve());

        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                assertTrue(sudoku.getMatrix()[x][y] != 0);
            }
        }

        assertTrue(sudoku.isAllValid());
    }

    @Test void solveUnsolbeable() {
        int[][] testMatrix = new int[][]{
                {2,0,0,0,0,0,0,2,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0}
        };

        assertFalse(sudoku.solve());

        testMatrix = new int[][]{
                {1, 2, 0, 4, 5, 6, 7, 8, 9},
                {0, 0, 3, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        sudoku.setMatrix(testMatrix);

        assertFalse(sudoku.solve());
    }

    @org.junit.jupiter.api.Test
    void clear() {
        sudoku.solve();

        sudoku.clear();

        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                assertTrue(sudoku.getMatrix()[x][y] == 0);
            }
        }
    }
}