package Visuals;
import Connectors.ViewToLogic;
import Logic.Status;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LeftPanelComponent extends JPanel{
    static ViewToLogic vtl;
    private int freq = 1000;
    private final int maxFreq = 10000;
    private final int minFreq = 100;
    private JSlider jSlider;
    private JTextField jTextField;
    private JComboBox<Status> jComboBox;
    private JButton endVBD;
    private int serial;
    private LeftPanel lp;

    LeftPanelComponent(int serial,LeftPanel lp,ViewToLogic vtl){
        this.vtl = vtl;
        this.serial = serial;
        this.jSlider = new JSlider(minFreq,maxFreq,freq);
        jSlider.setMinorTickSpacing(1000);
        jSlider.setMajorTickSpacing(3000);
        jSlider.setPaintTicks(true);
        jSlider.setPaintLabels(true);
        jSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                vtl.setVBDfreq(serial,jSlider.getValue());
            }
        });

        this.jTextField = new JTextField(String.valueOf(serial));
        jTextField.setEditable(false);

        this.jComboBox = new JComboBox<>(Status.values());
        jComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vtl.setStatusVBD(serial, (Status) jComboBox.getSelectedItem());
            }
        });

        this.endVBD = new JButton("End VBD");
        endVBD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vtl.deleteVBD(serial);
                lp.removeMe(LeftPanelComponent.this);
            }
        });

        this.setLayout(new FlowLayout());
        JPanel tmp = new JPanel();
        tmp.setLayout(new BoxLayout(tmp,BoxLayout.PAGE_AXIS));
        tmp.setPreferredSize(new Dimension(80,80));
        tmp.add(jTextField);
        tmp.add(endVBD);
        this.add(tmp);
        tmp = new JPanel();
        tmp.setLayout(new BoxLayout(tmp,BoxLayout.PAGE_AXIS));
        tmp.setPreferredSize(new Dimension(190,80));
        tmp.add(jSlider);
        tmp.add(jComboBox);
        jComboBox.setSelectedIndex(1);
        this.add(tmp);
        this.setPreferredSize(new Dimension(270,80));
        this.setBorder(BorderFactory.createLineBorder(Color.black));
    }
}
