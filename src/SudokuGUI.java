import javax.swing.*;
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
        JFrame frame = new JFrame("Example with restricted input");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = frame.getContentPane();

        JTextField tf = new JTextField("");
        tf.setDocument(new OneNumber());

        /*tf.addActionListener(e -> {
            Scanner scan = new Scanner(System.in);
            System.out.println(scan.nextLine());
            scan.close();
        });*/

        pane.add(tf, BorderLayout.CENTER);

        frame.pack();
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
