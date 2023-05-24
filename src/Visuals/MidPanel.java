package Visuals;

import Connectors.ViewToLogic;
import Logic.Mode;
import Logic.TYPE;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MidPanel extends JPanel {
    static ViewToLogic vtl;
    JPanel lBTS;
    JPanel rBTS;
    JPanel mainPanel;
    JScrollPane jScrollPane;
    JButton button1;
    JButton button2;
    MidPanel(){
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

        lBTS.setLayout(new BoxLayout(lBTS,BoxLayout.PAGE_AXIS));
        rBTS.setLayout(new BoxLayout(rBTS,BoxLayout.PAGE_AXIS));

        lBTS.setPreferredSize(new Dimension(100,230));
        rBTS.setPreferredSize(new Dimension(100,230));

        mainPanel.add(lBTS);
        mainPanel.add(rBTS);

        jScrollPane = new JScrollPane(mainPanel);
        jScrollPane.setPreferredSize(new Dimension(270,230));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        this.setLayout(new BorderLayout());
        this.add(jScrollPane,BorderLayout.PAGE_START);
        this.add(buttonPanel,BorderLayout.PAGE_END);
        this.setPreferredSize(new Dimension(300,300));

    }
    public void BTScreated(Mode mode, int id) {
        if(mode == Mode.LEFT){
            MidPanelComponent mpc = new MidPanelComponent(vtl,id, TYPE.BTS);
            lBTS.add(mpc);
            mainPanel.revalidate();
            mainPanel.repaint();
            System.out.println("created");
        }else{
            MidPanelComponent mpc = new MidPanelComponent(vtl,id, TYPE.BTS);
            rBTS.add(mpc);
            mainPanel.revalidate();
            mainPanel.repaint();
            System.out.println("created");
        }
    }

    public void BSCcreated(int warstwa, int id) {

    }
}
