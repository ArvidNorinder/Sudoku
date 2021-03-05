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


        GridLayout grid = new GridLayout(9, 9);
        frame.setLayout(grid);

        //grid created
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                JTextField tf = new JTextField("");
                tf.setHorizontalAlignment(JTextField.CENTER);
                tf.setFont(new Font("Verdana",
                        Font.CENTER_BASELINE,
                        20));
                tf.setDocument(new OneNumber());
                tf.setBorder(new LineBorder(Color.BLACK, 1));
                pane.add(tf);
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
