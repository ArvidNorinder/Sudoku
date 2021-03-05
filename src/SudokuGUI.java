import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.util.Scanner;

public class SudokuGUI {
    public SudokuGUI() {
        SwingUtilities.invokeLater(() -> createWindow());
    }

    private void createWindow() {
        JFrame frame = new JFrame("Sudoku");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = frame.getContentPane();

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

        //kom ihåg att lägga till textfields till pane


        GridLayout grid = new GridLayout(9, 9);
        frame.setLayout(grid);

        //grid created
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                pane.add(textFields[x][y]);
            }
        }



        frame.setSize(500, 500);
        frame.setVisible(true);
    }

    private class OneNumber extends PlainDocument {
        public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
            if (str == null)
                return;
            if ((getLength() + str.length()) <= 1 && Character.isDigit(str.charAt(0))) {
                super.insertString(offset, str, attr);
            }
        }
    }

    public static void main(String[] args) {
        //SudokuGUI gui =
          new SudokuGUI();
    }
}
