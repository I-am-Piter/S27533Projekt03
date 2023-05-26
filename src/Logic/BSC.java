package Logic;

import java.util.ArrayList;

public class BSC extends Thread{
    ArrayList<byte[]> SMS;
    int id;
    int sent;
    private static int lastIndex = 0;
     int line;
     boolean stillrun;

    BSC(int line){
        this.stillrun = true;
        this.SMS = new ArrayList<>();
        this.id = ++lastIndex;
        this.line = line;
    }

    @Override
    public void run() {
        while(stillrun){
            try {
                this.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            pushSMS();
        }
    }
    public synchronized void pushSMS(){
        if(SMS.size()>0){
            sent++;
            if(Structure.BSCs.length>line+1){
                Structure.bscReceiveSMS(SMS.get(0),line+1);
                SMS.remove(0);
            }else{
                Structure.rightReceiveSMS(SMS.get(0));
                SMS.remove(0);
            }
            Structure.BSCdataChagned(id);
        }
    }
    public synchronized void pushALl(){
        for (int i = 0;i<SMS.size();i++) {
            if(Structure.BSCs.length>line+1){
                Structure.bscReceiveSMS(SMS.get(0),line+1);
                SMS.remove(0);
            }else{
                Structure.rightReceiveSMS(SMS.get(0));
                SMS.remove(0);
            }
        }
    }
    public int getSent(){
        return sent;
    }
    public int getCount(){
        return SMS.size();
    }
}
