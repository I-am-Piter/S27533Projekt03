package Logic;

public class BTSTimer extends Thread{
    BTS bts;
    BTSTimer(BTS bts){
        this.bts = bts;
        this.start();
    }

    @Override
    public void run() {
        try {
            this.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        bts.sendSMS();
    }
}
