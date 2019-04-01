import org.w3c.dom.html.HTMLObjectElement;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {

    public static void main (String[] args) {
        JFrame frame = new JFrame();
        GridBagLayout grid = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        frame.setLayout(grid);
        frame.setTitle("calc_ui_v1.0");


        final JTextField textField = new JTextField();
        textField.setBounds(new Rectangle(100, 100));

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
            }
        }

        JButton[] visibleButtons = {percent, div, szam0, szam1, szam2, szam3, szam4, szam5, szam6, szam7, szam8, szam9, mult, sum, sub, tizedesesszo};
        //nyomkodós gombok jelenjenek meg
        for (JButton i: visibleButtons) {
            i.addActionListener(e -> textField.setText(textField.getText() + i.getText()));
        }

        //string torlese
        deletestring.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText("");
            }
        });

        //egy karakter torlese
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int caretPosition = textField.getCaretPosition();
                String text = textField.getText().
                        substring(textField.getCaretPosition()) + textField.getText().
                        substring(0, textField.getCaretPosition() - 1);
                textField.setText(text);
                textField.setCaretPosition(caretPosition -1);
            }
        });
        //uj ablak az uj muveleteknek
        more.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SideWindow sideWindow = new SideWindow();
            }
        });


        frame.setBounds(300, 200, 500, 700);

        frame.setVisible(true);

        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
