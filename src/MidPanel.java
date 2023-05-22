import javax.swing.*;
import java.awt.*;

public class MidPanel extends JPanel {
    MidPanelComponent midPanelComponent;
    JButton button1;
    JButton button2;
    MidPanel(){
        midPanelComponent = new MidPanelComponent();
        button1 = new JButton("-");
        button2 = new JButton("+");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        this.setLayout(new BorderLayout());
        this.add(buttonPanel,BorderLayout.PAGE_END);
        this.add(midPanelComponent,BorderLayout.CENTER);
        this.setPreferredSize(new Dimension(300,300));

    }
}
