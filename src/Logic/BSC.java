package Logic;

import java.util.ArrayList;

public class BSC extends Thread{
    ArrayList<String> SMS;
    int id;
    int sent;
    private static int lastIndex = 0;
     int line;

    BSC(int line){
        this.SMS = new ArrayList<>();
        this.id = ++lastIndex;
        this.line = line;
    }

    @Override
    public void run() {
        while(true){
            try {
                this.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(SMS.size()>0){
                sent++;
                if(Structure.BSCs.length>line+1){
                    Structure.bscReceiveSMS(SMS.get(0),line+1);
                    SMS.remove(0);
                }else{
                    Structure.rightReceiveSMS(SMS.get(0));
                    SMS.remove(0);
                }
            }
        }
    }
}
