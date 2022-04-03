package YL.KeyTrain;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;

public class MainUI extends JFrame {

    int keyToPress = 0;
    int keysPressed = 0;
    Long timeStarted;
    HashMap<Integer, String> keys = new HashMap<>();
    JTextPane keyToPressArea = new JTextPane();
    JTextPane results = new JTextPane();
    JButton startButton = new JButton("Start");

    private boolean started;

    int[] res = new int[] {1280, 720};

    MainUI() {
        displayScreen();
        fillHashMap();
    }

    private void fillHashMap() {
        keys.put(8, "Backspace");
        keys.put(9, "Tab");
        keys.put(10, "Enter");
        keys.put(16, "Shift");
        keys.put(17, "Ctrl");
        keys.put(18, "Alt");
        keys.put(20, "Caps Lock");
        keys.put(27, "Escape");
        keys.put(32, "Space");
        keys.put(37, "Left");
        keys.put(38, "Up");
        keys.put(39, "Right");
        keys.put(40, "Down");
        keys.put(44, "Comma");
        keys.put(45, "Minus");
        keys.put(47, "Slash");
        keys.put(48, "0");
        keys.put(49, "1");
        keys.put(50, "2");
        keys.put(51, "3");
        keys.put(52, "4");
        keys.put(53, "5");
        keys.put(54, "6");
        keys.put(55, "7");
        keys.put(56, "8");
        keys.put(57, "9");
        keys.put(59, "Semicolon");
        keys.put(61, "Equals");
        keys.put(65, "A");
        keys.put(66, "B");
        keys.put(67, "C");
        keys.put(68, "D");
        keys.put(69, "E");
        keys.put(70, "F");
        keys.put(71, "G");
        keys.put(72, "H");
        keys.put(73, "I");
        keys.put(74, "J");
        keys.put(75, "K");
        keys.put(76, "L");
        keys.put(77, "M");
        keys.put(78, "N");
        keys.put(79, "O");
        keys.put(80, "P");
        keys.put(81, "Q");
        keys.put(82, "R");
        keys.put(83, "S");
        keys.put(84, "T");
        keys.put(85, "U");
        keys.put(86, "V");
        keys.put(87, "W");
        keys.put(88, "X");
        keys.put(89, "Y");
        keys.put(90, "Z");
        keys.put(96, "NumPad 0");
        keys.put(97, "NumPad 1");
        keys.put(98, "NumPad 2");
        keys.put(99, "NumPad 3");
        keys.put(100, "NumPad 4");
        keys.put(101, "NumPad 5");
        keys.put(102, "NumPad 6");
        keys.put(103, "NumPad 7");
        keys.put(104, "NumPad 8");
        keys.put(105, "NumPad 9");
        keys.put(106, "NumPad *");
        keys.put(107, "NumPad +");
        keys.put(108, "NumPad ,");
        keys.put(109, "NumPad -");
        keys.put(110, "NumPad .");
        keys.put(111, "NumPad /");
    }

    private int cx(double a) {
        return (int) (a * res[0]);
    }

    private int cy(double a) {
        return (int) (a * res[1]);
    }

    private void adjustSize() {
        startButton.setBounds(cx(0.4), cy(0.8), cx(0.2), cy(0.1));
        keyToPressArea.setBounds(cx(0), cy(0), cx(1), cy(0.45));
        results.setBounds(cx(0), cy(0.5), cx(1), cy(0.25));
    }

    private void displayScreen() {
        adjustSize();
        startButton.addActionListener(e -> {
            if (started) { stop(); } else {start();}
        });


        keyToPressArea.setEditable(false);
        StyledDocument doc = keyToPressArea.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        keyToPressArea.setFont(new Font("Arial", Font.BOLD, 100));


        results.setEditable(false);
        StyledDocument doc2 = results.getStyledDocument();
        SimpleAttributeSet center2 = new SimpleAttributeSet();
        StyleConstants.setAlignment(center2, StyleConstants.ALIGN_CENTER);
        doc2.setParagraphAttributes(0, doc.getLength(), center, false);
        results.setFont(new Font("Arial", Font.PLAIN, 20));

        this.add(startButton);
        this.add(keyToPressArea);
        this.add(results);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(res[0], res[1]);
        this.setLayout(null);
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println(e.getKeyCode());
                if (started && e.getKeyCode() == keyToPress) {
                    keysPressed++;
                    randomizeKeyToPress();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        this.setVisible(true);
    }


    private void start() {
        started = true;
        timeStarted = System.currentTimeMillis();
        keysPressed = 0;
        startButton.setText("Stop");
        randomizeKeyToPress();
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    private void stop() {
        started = false;
        startButton.setText("Start");
        keyToPressArea.setText("");
        if(keysPressed != 0) {
            results.setText("Your result is " + ((System.currentTimeMillis() - timeStarted) / keysPressed) + " ms per symbol");
        }
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    private void randomizeKeyToPress() {
        ArrayList<Integer> keyKeys = new ArrayList<>(keys.keySet());
        keyToPress = keyKeys.get(randint(0, keyKeys.size()));
        keyToPressArea.setText(keys.get(keyToPress));
    }

    public int randint(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static void main(String[] args) {
        MainUI main = new MainUI();
    }
}
