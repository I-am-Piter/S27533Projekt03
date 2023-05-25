package Logic;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class FIleManager {
    public static void writeObjectsToFile(ArrayList<VBD> VBDs, String path) {
        try{
            FileOutputStream fos = new FileOutputStream(path);
            DataOutputStream dos = new DataOutputStream(fos);
            for(VBD v:VBDs) {
                byte[] smsBytes = v.getSms().getBytes();
                dos.writeInt(smsBytes.length);
                dos.write(smsBytes);
                dos.writeInt(v.getCount());
            }
            dos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
