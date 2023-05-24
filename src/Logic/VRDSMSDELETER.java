package Logic;

public class VRDSMSDELETER extends Thread{
    VRD vrd;
    boolean work;
    boolean end;
    VRDSMSDELETER(VRD vrd){
        this.vrd = vrd;
        this.end = false;
        this.work = false;
    }

    @Override
    public void run() {
        while(!end) {
            while(work) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (vrd) {
                    vrd.deletesms();
                }
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
