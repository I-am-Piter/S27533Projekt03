package Logic;

import java.util.ArrayList;

public class BTS extends Thread{
    ArrayList<byte[]> SMS;
    ArrayList<BTSTimer> timers;
    public int id;
    public int sent;
    public Mode mode;
    private static int lastIndex = 0;

    BTS(Mode mode){
        this.mode = mode;
        this.id = ++lastIndex;
        this.SMS = new ArrayList<>();
        this.timers = new ArrayList<>();
    }
    public int getSent(){
            return sent;
    }
    public int getSmsCount(){
            return SMS.size();
    }
    public void receiveSMS(byte[] sms){
        timers.add(new BTSTimer(this));
        SMS.add(sms);
    }

    public void sendSMS(){
        sent++;
        if(mode == Mode.LEFT) {
            Structure.bscReceiveSMS(SMS.get(0), 0);
            SMS.remove(0);
            timers.remove(0);
        }else{
            Structure.vrdReceiveSMS(SMS.get(0));
            SMS.remove(0);
            timers.remove(0);
        }
        Structure.BTSdataChagned(id);
    }

}
