import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LeftPanel extends JPanel {
    private JScrollPane jScrollPane;
    private JButton jButton;
    LeftPanel(){
        this.jButton = new JButton("add");
        this.jScrollPane = new JScrollPane();
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

                    }
                });
            }
        });
    }
}
