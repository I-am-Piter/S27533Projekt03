package Connectors;

import Logic.Mode;
import Logic.TYPE;

public interface LogicToView {
    public void BTScreated(Mode mode,int id);
    public void BSCcreated(int warstwa,int id);
    public void dataUpdated(int id, int sent, int queue, TYPE type);
    public void createBSClayer();
    public void VRDdataChanged(int id);
    public void BTSdataChanged(int id);
    public void BSCdataChanged(int id);
    public void rmlayer(int layerLeastSms);
}
