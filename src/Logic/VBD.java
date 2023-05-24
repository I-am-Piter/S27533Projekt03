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
        frequency = 1000;
        stillWork = true;
        serialNum = ++lastSerial;
        status = Status.ACTIVE;
    }

    public void setFrequency(int freq){
        synchronized (this) {
            frequency = freq;
        }
    }
    public void stopVBD(){
        stillWork = false;
    }
    public void setStatus(Status stat){
        synchronized (this) {
            status = stat;
        }
    }
    @Override
    public void run() {
        while(stillWork) {
            while(stillWork&&status.status) {
                Structure.leftReceiveSMS(SMS);
                try {
                    this.sleep(frequency);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                this.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
