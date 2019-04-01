import javax.swing.*;
import java.awt.*;

public class SideWindow {
    public SideWindow () {
        JFrame moreOperations = new JFrame();
        moreOperations.setBounds(170, 200, 50, 700);
        moreOperations.setVisible(true);
        moreOperations.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        moreOperations.setLayout(new GridLayout(2, 1));
        JButton valami = new JButton("valami");
        JButton valami2 = new JButton("valami2");
        moreOperations.add(valami);
        moreOperations.add(valami2);

    }
}
