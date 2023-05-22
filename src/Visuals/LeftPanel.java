package Visuals;

import Logic.CreateVBDEvent;
import Logic.VBD;
import Logic.VBDcreatorListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LeftPanel extends JPanel {
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
                String sms = "";
                JTextField textField = new JTextField(sms);
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
                        fireCreateObject(sms);
                        newVBD();
                    }
                });
            }
        });
    }
    public void newVBD(){
        LeftPanelComponent lpc = new LeftPanelComponent();
        scrollPanel.add(lpc);
        scrollPanel.revalidate();
        scrollPanel.repaint();
    }
    private ArrayList<VBDcreatorListener> listeners = new ArrayList<>();
    public void addListener(VBDcreatorListener vcl){
        this.listeners.add(vcl);
    }
    public void removeListener(VBDcreatorListener vcl){
        this.listeners.remove(vcl);
    }
    public void fireCreateObject(String sms){
        CreateVBDEvent cve = new CreateVBDEvent(this,sms);
        for(VBDcreatorListener vcl:listeners){
            vcl.VBDcreated(cve);
        }
    }
}
