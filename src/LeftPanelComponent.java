import javax.swing.*;

public class LeftPanelComponent extends JPanel{
    private JSlider jSlider;
    private JTextField jTextField;
    private JComboBox<Status> jComboBox;

    LeftPanelComponent(){
        this.jSlider = new JSlider();
        this.jTextField = new JTextField("number");
        this.jComboBox = new JComboBox<>(Status.values());
    }
}
