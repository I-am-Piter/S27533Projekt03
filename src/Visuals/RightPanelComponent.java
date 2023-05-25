package Visuals;

import Connectors.ViewToLogic;
import Logic.Structure;

import javax.naming.ldap.Control;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RightPanelComponent extends JPanel {
    static ViewToLogic vtl;
    private JButton endVRD;
    private JLabel smsCount;
    private JCheckBox delSMS10;
    public int id;
    private RightPanel rp;
    RightPanelComponent(int id,RightPanel rp,ViewToLogic vtl){
        this.vtl = vtl;
        this.rp = rp;
        this.id = id;

        this.endVRD = new JButton("Del vrd");
        endVRD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vtl.deleteVRD(id);
                rp.removeMe(RightPanelComponent.this);
            }
        });

        this.smsCount = new JLabel("received 0");

        this.delSMS10 = new JCheckBox("10s del");
        delSMS10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vtl.delSMSVRD(id,delSMS10.isSelected());
            }
        });


        this.setLayout(new FlowLayout());
        this.add(endVRD);
        this.add(smsCount);
        this.add(delSMS10);
        this.setPreferredSize(new Dimension(270,80));
        this.setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public void VRDdataChanged(){
        smsCount.setText("received " + vtl.getVrdSmsCount(id));
    }

}
