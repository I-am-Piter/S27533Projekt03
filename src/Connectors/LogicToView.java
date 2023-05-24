package Connectors;

import Logic.Mode;
import Logic.TYPE;

public interface LogicToView {
    public void BTScreated(Mode mode,int id);
    public void BSCcreated(int warstwa,int id);
    public void dataUpdated(int id, int sent, int queue, TYPE type);
}
