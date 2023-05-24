
import Connectors.ViewToLogic;
import Logic.Structure;
import Visuals.Visual;

import javax.swing.*;

public class S27533Projekt03 {
    public static void main(String[] args) {
        Structure structure = new Structure();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Visual visual = new Visual();
            }
        });
        Visual.setVtl(structure);
    }
}
