package Visuals;

import Connectors.LogicToView;
import Connectors.ViewToLogic;
import Logic.Mode;
import Logic.Structure;

import javax.swing.*;
import java.awt.*;

public class Visual extends JFrame implements LogicToView {
    static ViewToLogic vtl;
    static LeftPanel leftPanel;
    static RightPanel rightPanel;
    static MidPanel midPanel;
    public Visual(){
        Structure.setLtv(this);
        leftPanel = new LeftPanel();
        rightPanel = new RightPanel();
        midPanel = new MidPanel();

        this.setLayout(new BorderLayout());
        this.add(leftPanel,BorderLayout.LINE_START);
        this.add(rightPanel,BorderLayout.LINE_END);
        this.add(midPanel,BorderLayout.CENTER);

        this.setVisible(true);
        this.setSize(new Dimension(900,300));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vtl.createSTRUCTURE();
    }
    public static void setVtl(ViewToLogic vtl2){
        System.out.println("vtl set");
        vtl = vtl2;
        LeftPanel.vtl = vtl2;
        MidPanel.vtl = vtl2;
        RightPanel.vtl = vtl2;
    }

    @Override
    public void BTScreated(Mode mode, int id) {
        midPanel.BTScreated(mode,id);
    }

    @Override
    public void BSCcreated(int warstwa, int id) {
        midPanel.BSCcreated(warstwa,id);
    }
}
