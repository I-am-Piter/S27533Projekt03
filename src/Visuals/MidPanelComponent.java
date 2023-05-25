package Visuals;

import Connectors.ViewToLogic;
import Logic.TYPE;

import javax.swing.*;
import java.awt.*;

public class MidPanelComponent extends JPanel {
    TYPE type;
    static ViewToLogic vtl;
    int id;
    JLabel Jid;
    JLabel SMScount;
    JLabel SMSqueue;
    MidPanelComponent(ViewToLogic vtl2, int id, TYPE type){
        this.type = type;
        this.vtl = vtl2;
        this.id = id;
        this.Jid = new JLabel("id: " + String.valueOf(id));
        this.SMScount = new JLabel("sent: " + String.valueOf(vtl.getBtsSent(type,id)));
        this.SMSqueue = new JLabel("queue: " + String.valueOf(vtl.getBtsSmsCount(type,id)));

        this.setSize(new Dimension(100,50));
        this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        this.add(Jid);
        this.add(SMScount);
        this.add(SMSqueue);
    }
    public void refreshData(int sent, int queue){
        SMScount.setText("sent: " + String.valueOf(sent));
        SMSqueue.setText("queue: " + String.valueOf(queue));
    }
}
