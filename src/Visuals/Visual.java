package Visuals;

import Logic.CreateVBDEvent;
import Logic.Structure;
import Logic.VBDcreatorListener;
import Visuals.LeftPanel;
import Visuals.MidPanel;
import Visuals.RightPanel;

import javax.swing.*;
import java.awt.*;

public class Visual extends JFrame {

    public Visual(){
        LeftPanel leftPanel = new LeftPanel();
        RightPanel rightPanel = new RightPanel();
        MidPanel midPanel = new MidPanel();
        leftPanel.addListener(new VBDcreatorListener() {
        @Override
        public void VBDcreated(CreateVBDEvent cve) {
                Structure.VBDcreated(cve);
            }
        });

        this.setLayout(new BorderLayout());
        this.add(leftPanel,BorderLayout.LINE_START);
        this.add(rightPanel,BorderLayout.LINE_END);
        this.add(midPanel,BorderLayout.CENTER);


        this.setVisible(true);
        this.setSize(new Dimension(900,300));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
