import javax.swing.*;
import java.awt.*;

public class RightPanel extends JPanel {
    private JScrollPane jScrollPane;
    private JButton jButton;
    RightPanel(){
        this.jButton = new JButton("add");
        this.jScrollPane = new JScrollPane();
        this.setLayout(new BorderLayout());
        this.add(jScrollPane,BorderLayout.PAGE_START);
        this.add(jButton,BorderLayout.PAGE_END);
        this.setPreferredSize(new Dimension(300,300));
    }
}
