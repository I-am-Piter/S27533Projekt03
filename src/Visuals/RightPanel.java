package Visuals;

import Connectors.ViewToLogic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RightPanel extends JPanel {
    static ViewToLogic vtl;
    private JScrollPane jScrollPane;
    private JButton jButton;
    private JPanel scrollPanel;
    private ArrayList<RightPanelComponent> panele;
    RightPanel(){
        this.panele = new ArrayList<>();
        this.jButton = new JButton("add");
        this.scrollPanel = new JPanel();
        scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.PAGE_AXIS));
        this.jScrollPane = new JScrollPane(scrollPanel);
        jScrollPane.setPreferredSize(new Dimension(270,230));
        this.setLayout(new BorderLayout());
        this.add(jScrollPane,BorderLayout.PAGE_START);
        this.add(jButton,BorderLayout.PAGE_END);
        this.setPreferredSize(new Dimension(300,300));

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newRPC();
            }
        });
    }
    public void removeMe(RightPanelComponent rpc){
        scrollPanel.remove(rpc);
        scrollPanel.revalidate();
        scrollPanel.repaint();
    }
    public void newRPC(){
        RightPanelComponent rpc = new RightPanelComponent(vtl.createVRD(),this,vtl);
        panele.add(rpc);
        scrollPanel.add(rpc);
        scrollPanel.revalidate();
        scrollPanel.repaint();
    }
    public void VRDdataChanged(int id) {
        for(RightPanelComponent rpc:panele){
            if(rpc.id == id){
                rpc.VRDdataChanged();
            }
        }
    }
}
