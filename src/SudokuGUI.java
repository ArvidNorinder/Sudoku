import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;

public class SudokuGUI {
    public SudokuGUI() {
        SwingUtilities.invokeLater(() -> createWindow());
    }

    private void createWindow() {
        JFrame frame = new JFrame("Sudoku");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = new Container();
        //vi vill skapa en ny kontainer där vi placerar knappar
        Container buttonPane = new Panel();
        Sudoku sudoku = new Sudoku();

        GridLayout grid = new GridLayout(9, 9);
        pane.setLayout(grid);

        JTextField[][] textFields = new JTextField[9][9];

        boolean orangeField;
        boolean orangeStart = true;
        int countX = 0;
        int countY = 0;

        for (int x = 0; x < 9; x++) {
            //när countY är 3 har loopen nedan skett tre gånger, och därför vill vi börja med orange = !orange
            if (countY == 3) {
                countY = 0;
                orangeStart = !orangeStart;
            }

            //ser till att count y är på siffran av den exekvering med start från 1 vi är på
            countY++;

            countX = 0;

            if (orangeStart)
                orangeField = true;
            else
                orangeField = false;

            for (int y = 0; y < 9; y++) {
                textFields[x][y] = new JTextField("");
                JTextField tf = textFields[x][y];
                tf.setHorizontalAlignment(JTextField.CENTER);
                tf.setFont(new Font("Verdana",
                        Font.CENTER_BASELINE,
                        20));
                tf.setDocument(new OneNumber());
                tf.setBorder(new LineBorder(Color.BLACK, 1));

                if (orangeField)
                    tf.setBackground(Color.ORANGE);

                countX++;

                //när tre rutor i rad har markerats oranga
                if (countX == 3) {
                    orangeField = !orangeField;
                    countX = 0;
                }
            }
        }

        //grid created. Add key listeners to update backend sudoku when keys are inserted
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                JTextField tf = textFields[x][y];
                pane.add(tf);
            }
        }

        //lägg till de två knapparna som behövs
        JButton solve = new JButton("Solve");
        JButton clear = new JButton("Clear");

        solve.addActionListener(e -> {
            //fixa så att den hämtar värden från text,fields, lägger till dem i sudoku, anropar solve, insertar värden som ges

            //uppdaterar vårt sudoku med det som faktiskt står på vår GUI
            for (int x = 0; x < 9; x++) {
                for (int y = 0; y < 9; y++) {
                    if(!textFields[x][y].getText().equals(""))
                        sudoku.setNumber(x, y, Integer.parseInt(textFields[x][y].getText()));
                }
            }

            //if the sudoku was solveable we update our GUI
            if (sudoku.solve()) {
                for (int x = 0; x < 9; x++) {
                    for (int y = 0; y < 9; y++) {
                        try {
                            if (sudoku.getMatrix()[x][y] + "" != "")
                                textFields[x][y].getDocument().insertString(0, sudoku.getMatrix()[x][y] + "", null);
                        }
                        catch (BadLocationException exception) {
                            exception.printStackTrace();
                        }
                    }
                }
            }

            else
                JOptionPane.showMessageDialog(new JFrame(), "No solution could be found.");
        });

        clear.addActionListener(e -> {
            //cleara sudokut i bakgrunden
            sudoku.clear();

            for (int x = 0; x < 9; x++) {
                for (int y = 0; y < 9; y++) {
                        textFields[x][y].setDocument(new OneNumber());
                }
            }
        });

        frame.add(pane);
        buttonPane.add(solve, BorderLayout.SOUTH);
        buttonPane.add(clear, BorderLayout.SOUTH);
        frame.add(buttonPane, BorderLayout.SOUTH);


        frame.setSize(500, 500);
        frame.setVisible(true);
    }

    private class OneNumber extends PlainDocument {
        public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
            if (str == null)
                return;
            if ((getLength() + str.length()) <= 1 && Character.isDigit(str.charAt(0)) && !str.equals("0")) {
                super.insertString(offset, str, attr);
            }
        }
    }

    public static void main(String[] args) {
        //SudokuGUI gui =
          new SudokuGUI();
    }
}
