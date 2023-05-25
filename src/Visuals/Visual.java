package Visuals;

import Connectors.LogicToView;
import Connectors.ViewToLogic;
import Logic.FIleManager;
import Logic.Mode;
import Logic.Structure;
import Logic.TYPE;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
        this.setSize(new Dimension(1200,300));
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                vtl.frameClosed();
            }
        });
        vtl.createSTRUCTURE();
    }
    public static void setVtl(ViewToLogic vtl2){
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

    @Override
    public void dataUpdated(int id, int sent, int queue, TYPE type) {
        midPanel.dataUpdated(id,sent,queue,type);
    }

    @Override
    public void createBSClayer() {
        midPanel.createBSClayer();
    }

    @Override
    public void VRDdataChanged(int id) {
        rightPanel.VRDdataChanged(id);
    }

    @Override
    public void BTSdataChanged(int id) {
        midPanel.BTSdataChanged(id);
    }

    @Override
    public void BSCdataChanged(int id) {
        midPanel.BSCdataChanged(id);
    }

    @Override
    public void rmlayer(int layerLeastSms) {
        midPanel.rmlayer(layerLeastSms);
    }

}
