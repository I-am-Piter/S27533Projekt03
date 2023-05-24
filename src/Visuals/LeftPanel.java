package Visuals;

import Connectors.ViewToLogic;
import Logic.VBD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LeftPanel extends JPanel {
    static ViewToLogic vtl;
    private JScrollPane jScrollPane;
    private JPanel scrollPanel;
    private JButton jButton;
    LeftPanel(){
        this.scrollPanel = new JPanel();
        scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.PAGE_AXIS));
        this.jButton = new JButton("add");
        this.jScrollPane = new JScrollPane(scrollPanel);
        jScrollPane.setPreferredSize(new Dimension(270,230));
        this.setLayout(new BorderLayout());
        this.add(jScrollPane,BorderLayout.PAGE_START);
        this.add(jButton,BorderLayout.PAGE_END);
        this.setPreferredSize(new Dimension(300,300));
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                JTextField textField = new JTextField();
                JButton button = new JButton("OK.");
                frame.setLayout(new BorderLayout());
                frame.add(textField,BorderLayout.PAGE_START);
                frame.add(button,BorderLayout.PAGE_END);
                frame.setVisible(true);
                frame.setSize(new Dimension(300,200));
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.setVisible(false);
                        String sms = textField.getText();
                        newVBD(vtl.createVBD(sms));
                    }
                });
            }
        });
    }
    public void removeMe(LeftPanelComponent lpc){
        scrollPanel.remove(lpc);
        scrollPanel.revalidate();
        scrollPanel.repaint();
    }
    public void newVBD(int serial){
        LeftPanelComponent lpc = new LeftPanelComponent(serial,this,vtl);
        scrollPanel.add(lpc);
        scrollPanel.revalidate();
        scrollPanel.repaint();
    }
}
