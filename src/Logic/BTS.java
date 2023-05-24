package Logic;

import java.util.ArrayList;

public class BTS extends Thread{
    ArrayList<String> SMS;
    public int id;
    public int sent;
    public Mode mode;
    private static int lastIndex = 0;

    BTS(Mode mode){
        this.mode = mode;
        this.id = ++lastIndex;
        this.SMS = new ArrayList<>();
    }

    @Override
    public void run() {
        while(true){
            try {
                this.sleep(1000*(int)((Math.random()*10)+5));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(SMS.size()>0){
                sent++;
                if(mode == Mode.LEFT) {
                    Structure.bscReceiveSMS(SMS.get(0), 0);
                    SMS.remove(0);
                }else{
                    Structure.vrdReceiveSMS(SMS.get(0));
                    SMS.remove(0);
                }
            }
        }
    }

}
