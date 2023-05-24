package Logic;

public class VRD extends Thread{
    private static int lastindex;
    public final int id;
    private boolean stillWork;
    public int SMSnum;
    private boolean delSMSinterval;
    private VRDSMSDELETER deleter;

    public VRD(){
        this.deleter = new VRDSMSDELETER(this);
        deleter.start();
        this.stillWork = true;
        this.SMSnum = 0;
        this.delSMSinterval = false;
        this.id = ++lastindex;
    }
    public void stopVRD(){
        synchronized (this) {
            stillWork = false;
        }
    }
    public void deletesms(){
        SMSnum = 0;
    }
    public void setDeleter(boolean isSelected){
        deleter.work = isSelected;
    }
    @Override
    public void run() {
        while(stillWork) {
            System.out.println("vrd " + id);
            try {
                this.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
