package Logic;

public class BSCTimer extends Thread{
    BSC bsc;
    BSCTimer(BSC bsc){
        this.bsc = bsc;
        this.start();
    }

    @Override
    public void run() {
        try {
            this.sleep(1000*(int)((Math.random()*10)+5));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        bsc.pushSMS();
    }
}
