package Visuals;

import Connectors.ViewToLogic;
import Logic.Mode;
import Logic.TYPE;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MidPanel extends JPanel {
    static ViewToLogic vtl;
    ArrayList<MidPanelComponent> components;
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
    MidPanel(){
        components = new ArrayList<>();
        button1 = new JButton("-");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        //TODO do zrobien

        button2 = new JButton("+");

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.LINE_AXIS));
        mainPanel.setSize(new Dimension(270,230));

        lBTS = new JPanel();
        rBTS = new JPanel();
        BSCs = new JPanel();

        lBTSscroll = new JScrollPane(lBTS);
        rBTSscroll = new JScrollPane(rBTS);
        BSCscroll = new JScrollPane(BSCs);
        lBTSscroll.setPreferredSize(new Dimension(100,230));
        rBTSscroll.setPreferredSize(new Dimension(100,230));
        BSCscroll.setPreferredSize(new Dimension(100,230));

        lBTS.setLayout(new BoxLayout(lBTS,BoxLayout.PAGE_AXIS));
        rBTS.setLayout(new BoxLayout(rBTS,BoxLayout.PAGE_AXIS));
        BSCs.setLayout(new BoxLayout(BSCs,BoxLayout.PAGE_AXIS));

//        lBTS.setPreferredSize(new Dimension(100,230));
//        rBTS.setPreferredSize(new Dimension(100,230));

        mainPanel.add(lBTSscroll);
        mainPanel.add(BSCscroll);
        mainPanel.add(rBTSscroll);

        jScrollPane = new JScrollPane(mainPanel);
        jScrollPane.setPreferredSize(new Dimension(270,230));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        this.setLayout(new BorderLayout());
        this.add(jScrollPane,BorderLayout.PAGE_START);
        this.add(buttonPanel,BorderLayout.PAGE_END);
        this.setPreferredSize(new Dimension(600,300));

    }
    public void BTScreated(Mode mode, int id) {
        if(mode == Mode.LEFT){
            MidPanelComponent mpc = new MidPanelComponent(vtl,id, TYPE.BTS);
            components.add(mpc);
            lBTS.add(mpc);
            mainPanel.revalidate();
            mainPanel.repaint();
            System.out.println("created");
        }else{
            MidPanelComponent mpc = new MidPanelComponent(vtl,id, TYPE.BTS);
            components.add(mpc);
            rBTS.add(mpc);
            mainPanel.revalidate();
            mainPanel.repaint();
            System.out.println("created");
        }
    }

    public void BSCcreated(int warstwa, int id) {
        MidPanelComponent mpc = new MidPanelComponent(vtl,id, TYPE.BSC);
        components.add(mpc);
        BSCs.add(mpc);
        mainPanel.revalidate();
        mainPanel.repaint();
        System.out.println("created");
    }
    public void dataUpdated(int id, int sent, int queue, TYPE type) {
        for(MidPanelComponent comp:components){
            if((comp.id == id)&&(comp.type == type)){
                comp.refreshData(sent,queue);
            }
        }
    }
}
