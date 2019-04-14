package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame {

    private boolean isLastCharANumber = true;
    public MainFrame () {
        JFrame frame = new JFrame();
        GridBagLayout grid = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        frame.setLayout(grid);
        frame.setTitle("calc_ui_v1.0");
        frame.setUndecorated(true);
        frame.setBackground(new Color(0x0, true));
        frame.setShape(new RoundRectangle2D.Double(0, 0, 500, 700, 90, 90));
        class FrameDragListener extends MouseAdapter {

            private final JFrame frame;
            private Point mouseDownCompCoords = null;

            public FrameDragListener(JFrame frame) {
                this.frame = frame;
            }

            public void mouseReleased(MouseEvent e) {
                mouseDownCompCoords = null;
            }

            public void mousePressed(MouseEvent e) {
                mouseDownCompCoords = e.getPoint();
            }

            public void mouseDragged(MouseEvent e) {
                Point currCoords = e.getLocationOnScreen();
                frame.setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
            }
        }
        FrameDragListener frameDragListener = new FrameDragListener(frame);
        frame.addMouseListener(frameDragListener);
        frame.addMouseMotionListener(frameDragListener);








        final JTextField textField = new JTextField();
        textField.setBounds(new Rectangle(100, 100));
        textField.setBackground(Color.DARK_GRAY);
        textField.setForeground(Color.LIGHT_GRAY);
        textField.setFont(textField.getFont().deriveFont(34.0f));
        try {
            textField.setFont(Font.createFont(Font.TRUETYPE_FONT, MainFrame.class.getResourceAsStream("Roboto-Regular.ttf")));
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        textField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;

        frame.add(textField, gbc);

        JButton szam1 = new JButton("1");
        JButton szam2 = new JButton("2");
        JButton szam3 = new JButton("3");
        JButton szam4 = new JButton("4");
        JButton szam5 = new JButton("5");
        JButton szam6 = new JButton("6");
        JButton szam7 = new JButton("7");
        JButton szam8 = new JButton("8");
        JButton szam9 = new JButton("9");
        JButton szam0 = new JButton("0");
        JButton more = new JButton("...");
        JButton tizedesesszo = new JButton(",");
        JButton sum = new JButton("+");
        JButton sub = new JButton("-");
        JButton mult = new JButton("*");
        JButton div = new JButton("/");
        JButton percent = new JButton("%");
        JButton delete = new JButton("C");
        JButton equals = new JButton("=");
        JButton deletestring = new JButton("CE");

        gbc.gridwidth = 1;

        JButton[] buttons = {delete, deletestring, percent, div, szam7, szam8, szam9, mult, szam4, szam5, szam6, sum,
                szam1, szam2, szam3, sub, more, szam0, tizedesesszo, equals};

        for (int i=0; i<5; i++){
            for (int j = 0; j < 4; j++) {
                gbc.gridx = j;
                gbc.gridy = i+1;
                frame.add(buttons[i*4+j], gbc);
                buttons[i*4+j].setBackground(Color.DARK_GRAY);
                buttons[i*4+j].setForeground(Color.LIGHT_GRAY);
                buttons[i*4+j].setFont(buttons[i*4+j].getFont().deriveFont(34.0f));
                buttons[i*4+j].setBorder(BorderFactory.createEmptyBorder());
            }
        }
        StringBuilder input = new StringBuilder();
        JButton[] visibleButtons = {szam0, szam1, szam2, szam3, szam4, szam5, szam6, szam7, szam8, szam9};
        JButton[] operationButtons = {percent, div, mult, sum, sub, tizedesesszo};

        //nyomkodós gombok jelenjenek meg
        for (JButton i: visibleButtons) {
            i.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    textField.setText(input.append(i.getText()).toString());
                    isLastCharANumber = true;
                }
            });
        }
        /*
         * / utan johet: -, +
         * * utan johet: -, +
         *
         */
        /////////////////////////////////////////////////////////

        for (JButton operations: operationButtons
        ) {
            operations.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(isLastCharANumber){
                        textField.setText(input.append(operations.getText()).toString());
                        isLastCharANumber = false;
                    }
                    else {
                        input.setCharAt(textField.getText().length()-1, operations.getText().charAt(0));
                        textField.setText(input.toString());
                    }

                }
            });
        }

        more.addActionListener(e -> {
            SideWindow sideWindow = new SideWindow(textField);
        });


        ///////////////////////////////////////////////////////////

        //string torlese
        deletestring.addActionListener(e -> textField.setText(input.delete(0, textField.getText().length()).toString()));
        /////////////////////////////////////////////////////////
        //egy karakter torlese
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*int caretPosition = textField.getCaretPosition();
                String text = textField.getText().
                        substring(textField.getCaretPosition()) + textField.getText().
                        substring(0, textField.getCaretPosition() - 1);
                textField.setText(text);
                textField.setCaretPosition(caretPosition - 1);*/
                textField.setText(input.deleteCharAt(textField.getText().length()-1).toString());
                isLastCharANumber = true;
            }
        });
        frame.setBounds(300, 200, 500, 700);

        frame.setVisible(true);

        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
