package Logic;

public class VBD extends Thread{

    String SMS;
    int frequency; //sms/min
    boolean stillWork;
    int serialNum;
    Status status;
    static int lastSerial = 0;

    VBD(String SMS){
        this.SMS = SMS;
        frequency = 1;
        stillWork = true;
        serialNum = ++lastSerial;
        status = Status.ACTIVE;
    }

    public void setFrequency(int freq){
        frequency = freq;
    }
    public void setStillWork(boolean work){
        stillWork = work;
    }
    public void setStatus(Status stat){
        status = stat;
    }
    public int getFrequency(){
        return frequency;
    }
    public Status getStatus(){
        return status;
    }
    public int getSerial(){
        return serialNum;
    }

}
