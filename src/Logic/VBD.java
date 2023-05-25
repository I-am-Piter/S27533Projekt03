package Logic;

public class VBD extends Thread{

    String SMS;
    int sent;
    int frequency; //sms/min
    boolean stillWork;
    int serialNum;
    Status status;
    static int lastSerial = 0;
    static int[] vrds;
    PduConverter converter;

    VBD(String SMS){
        this.sent = 0;
        this.converter = new PduConverter();
        this.vrds = Structure.getVRDnumbers();
        this.SMS = SMS;
        frequency = 1000;
        stillWork = true;
        serialNum = ++lastSerial;
        status = Status.ACTIVE;
    }
    public static void vrdsChanged(int[] tab){
        vrds = tab;
    }

    public void setFrequency(int freq){
        frequency = freq;
    }
    public void stopVBD(){
        stillWork = false;
    }
    public void setStatus(Status stat){
        status = stat;
    }
    @Override
    public void run() {
        int number;
        byte[] encoded;
        while(stillWork) {
            while(stillWork&&status.status&&(vrds.length>0)) {
                sent++;
                number = (int)(Math.random()*vrds.length);
                number = vrds[number];
                encoded = converter.encodeMessageToPdu(number,SMS);
                Structure.leftReceiveSMS(encoded);
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

    public String getSms() {
        return SMS;
    }


    public int getCount() {
        return sent;
    }
}
