package Connectors;

import Logic.Status;
import Logic.TYPE;

public interface ViewToLogic {
    public int createVBD(String sms);
    public void deleteVBD(int serial);
    public void setVBDfreq(int serial,int freq);
    public void setStatusVBD(int serial, Status status);

    public int createVRD();
    public void deleteVRD(int id);
    public void delSMSVRD(int id,boolean isSelected);
    public int getBtsSmsCount(TYPE type,int id);
    public int getBtsSent(TYPE type,int id);
    public void createSTRUCTURE();

}
