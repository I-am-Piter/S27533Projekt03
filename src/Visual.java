import javax.swing.*;
import java.awt.*;

public class Visual extends JFrame {

    Visual(){
        LeftPanel leftPanel = new LeftPanel();
        RightPanel rightPanel = new RightPanel();
        MidPanel midPanel = new MidPanel();

        this.setLayout(new BorderLayout());
        this.add(leftPanel,BorderLayout.LINE_START);
        this.add(rightPanel,BorderLayout.LINE_END);
        this.add(midPanel,BorderLayout.CENTER);


        this.setVisible(true);
        this.setSize(new Dimension(900,300));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
