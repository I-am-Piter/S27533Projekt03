package Visuals;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RightPanel extends JPanel {
    private JScrollPane jScrollPane;
    private JButton jButton;
    private JPanel scrollPanel;
    RightPanel(){
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
                RightPanelComponent rpc = new RightPanelComponent();
                scrollPanel.add(rpc);
                scrollPanel.revalidate();
                scrollPanel.repaint();
            }
        });
    }
}
