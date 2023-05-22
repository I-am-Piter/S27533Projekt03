package Visuals;

import javax.swing.*;
import java.awt.*;

public class RightPanelComponent extends JPanel {

    private JButton endVRD;
    private JLabel smsCount;
    private JCheckBox delSMS10;
    RightPanelComponent(){
        this.endVRD = new JButton("Del vrd");
        this.smsCount = new JLabel("count");
        this.delSMS10 = new JCheckBox("10s del");

        this.setLayout(new FlowLayout());
        this.add(endVRD);
        this.add(smsCount);
        this.add(delSMS10);
        this.setPreferredSize(new Dimension(270,80));
        this.setBorder(BorderFactory.createLineBorder(Color.black));
    }
}
