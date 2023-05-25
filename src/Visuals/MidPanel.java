package Visuals;

import Connectors.ViewToLogic;
import Logic.Mode;
import Logic.TYPE;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MidPanel extends JPanel {
    static ViewToLogic vtl;
    ArrayList<MidPanelComponent> components;
    ArrayList<BSCPanel> BSClayers;
    JPanel lBTS;
    JScrollPane lBTSscroll;
    JPanel rBTS;
    JScrollPane rBTSscroll;
    JPanel BSCs;
    JScrollPane BSCscroll;
    JPanel mainPanel;
    JScrollPane jScrollPane;
    JButton button1;
    JButton button2;

    MidPanel() {
        components = new ArrayList<>();
        BSClayers = new ArrayList<>();
        button1 = new JButton("-");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vtl.rmLayer();
            }
        });

        //TODO do zrobien

        button2 = new JButton("+");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vtl.addLayer();
            }
        });

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setSize(new Dimension(270, 230));

        lBTS = new JPanel();
        rBTS = new JPanel();
        BSCs = new JPanel();

        lBTS.setLayout(new BoxLayout(lBTS, BoxLayout.PAGE_AXIS));
        rBTS.setLayout(new BoxLayout(rBTS, BoxLayout.PAGE_AXIS));
        BSCs.setLayout(new BoxLayout(BSCs, BoxLayout.LINE_AXIS));

        lBTSscroll = new JScrollPane(lBTS);
        rBTSscroll = new JScrollPane(rBTS);
        BSCscroll = new JScrollPane(BSCs);

        lBTSscroll.setSize(new Dimension(50, 230));
        rBTSscroll.setSize(new Dimension(50, 230));
        BSCscroll.setPreferredSize(new Dimension(200, 230));

        mainPanel.add(lBTSscroll, BorderLayout.LINE_START);
        mainPanel.add(rBTSscroll, BorderLayout.LINE_END);
        mainPanel.add(BSCscroll, BorderLayout.CENTER);

        jScrollPane = new JScrollPane(mainPanel);
        jScrollPane.setPreferredSize(new Dimension(270, 230));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        this.setLayout(new BorderLayout());
        this.add(jScrollPane, BorderLayout.PAGE_START);
        this.add(buttonPanel, BorderLayout.PAGE_END);
        this.setPreferredSize(new Dimension(600, 300));

        createBSClayer();

    }

    public void BTScreated(Mode mode, int id) {
        if (mode == Mode.LEFT) {
            MidPanelComponent mpc = new MidPanelComponent(vtl, id, TYPE.BTS);
            mpc.setBorder(BorderFactory.createLineBorder(Color.black));
            components.add(mpc);
            lBTS.add(mpc);
            mainPanel.revalidate();
            mainPanel.repaint();
        } else {
            MidPanelComponent mpc = new MidPanelComponent(vtl, id, TYPE.BTS);
            mpc.setBorder(BorderFactory.createLineBorder(Color.black));
            components.add(mpc);
            rBTS.add(mpc);
            mainPanel.revalidate();
            mainPanel.repaint();
        }
    }

    public void createBSClayer() {
        BSCPanel layer = new BSCPanel(vtl);
        BSCs.add(layer);
        BSClayers.add(layer);
    }

    public void BSCcreated(int warstwa, int id) {
        MidPanelComponent mpc = new MidPanelComponent(vtl, id, TYPE.BSC);
        mpc.setBorder(BorderFactory.createLineBorder(Color.black));
        components.add(mpc);
        BSClayers.get(warstwa).add(mpc);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public void dataUpdated(int id, int sent, int queue, TYPE type) {
        for (MidPanelComponent comp : components) {
            if ((comp.id == id) && (comp.type == type)) {
                comp.refreshData(sent, queue);
            }
        }
    }

    public void rmlayer(int layerLeastSms) {
        BSClayers.get(layerLeastSms).removeAll();
        BSCs.remove(BSClayers.get(layerLeastSms));
        {
            components.remove(BSClayers.get(layerLeastSms));
            BSClayers.remove(layerLeastSms);
            mainPanel.revalidate();
            mainPanel.repaint();
        }
    }

    public void BTSdataChanged(int id){
        for (MidPanelComponent mps : components) {
            if ((mps.type == TYPE.BTS) && (mps.id == id)) {
                mps.refreshData(vtl.getBtsSent(TYPE.BTS, id), vtl.getBtsSmsCount(TYPE.BTS, id));
            }
        }
    }


    public void BSCdataChanged (int id){
        for (MidPanelComponent mps : components) {
            if ((mps.type == TYPE.BSC) && (mps.id == id)) {
                mps.refreshData(vtl.getBscSent(TYPE.BTS, id), vtl.getBscSmsCount(TYPE.BSC, id));
            }
        }
    }

}
