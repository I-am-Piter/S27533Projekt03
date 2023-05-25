package Visuals;

import Connectors.ViewToLogic;

import javax.swing.*;
import java.awt.*;

public class BSCPanel extends JPanel {
    ViewToLogic vtl;

    BSCPanel(ViewToLogic vtl){
        this.vtl = vtl;
        this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
    }

}
