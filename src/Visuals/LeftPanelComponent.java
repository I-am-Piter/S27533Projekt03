package Visuals;

import Logic.Status;
import Logic.VBD;

import javax.swing.*;
import java.awt.*;

public class LeftPanelComponent extends JPanel{
    private int freq = 1;
    private final int maxFreq = 10;
    private final int minFreq = 1/10;
    private JSlider jSlider;
    private JTextField jTextField;
    private JComboBox<Status> jComboBox;
    private JButton endVBD;

    LeftPanelComponent(){
        this.jSlider = new JSlider(minFreq,maxFreq,freq);
        jSlider.setMinorTickSpacing(1/10);
        jSlider.setMajorTickSpacing(1);
        jSlider.setPaintTicks(true);
        jSlider.setPaintLabels(true);

        this.jTextField = new JTextField("serial");
        jTextField.setEditable(false);

        this.jComboBox = new JComboBox<>(Status.values());

        this.endVBD = new JButton("End VBD");

        this.setLayout(new FlowLayout());
        JPanel tmp = new JPanel();
        tmp.setLayout(new BoxLayout(tmp,BoxLayout.PAGE_AXIS));
        tmp.setPreferredSize(new Dimension(120,80));
        tmp.add(jTextField);
        tmp.add(endVBD);
        this.add(tmp);
        tmp = new JPanel();
        tmp.setLayout(new BoxLayout(tmp,BoxLayout.PAGE_AXIS));
        tmp.setPreferredSize(new Dimension(130,80));
        tmp.add(jSlider);
        tmp.add(jComboBox);
        jComboBox.setSelectedIndex(1);
        this.add(tmp);
        this.setPreferredSize(new Dimension(270,80));
        this.setBorder(BorderFactory.createLineBorder(Color.black));
    }
}
