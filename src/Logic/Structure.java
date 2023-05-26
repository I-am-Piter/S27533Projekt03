package Logic;

import Connectors.LogicToView;
import Connectors.ViewToLogic;

import java.util.ArrayList;

public class Structure implements ViewToLogic {
    static LogicToView ltv;
    static ArrayList<VBD> vbds = new ArrayList<>();
    static ArrayList<VBD> vbdinfo = new ArrayList<>();
    static ArrayList<VRD> vrds = new ArrayList<>();
    static ArrayList<BTS> leftBTSs = new ArrayList<>();
    static ArrayList<BTS> rightBTSs = new ArrayList<>();
    static ArrayList<BSC>[] BSCs = new ArrayList[1];
    static PduConverter converter = new PduConverter();

    public static void setLtv(LogicToView ltv2){
        ltv = ltv2;
    }



    @Override
    public int createVBD(String sms) {
        VBD vbd = new VBD(sms);
        vbd.start();
        vbds.add(vbd);
        vbdinfo.add(vbd);
        return vbd.serialNum;
    }

    @Override
    public void deleteVBD(int serial) {
        for (VBD vbd:vbds) {
            if(vbd.serialNum == serial){
                vbd.stopVBD();
                vbds.remove(vbd);
                break;
            }
        }
    }

    @Override
    public void setVBDfreq(int serial, int freq) {
        for (VBD vbd:vbds) {
            if(vbd.serialNum == serial){
                vbd.setFrequency(freq);
                break;
            }
        }
    }

    public void setStatusVBD(int serial, Status status){
        for (VBD vbd:vbds) {
            if(vbd.serialNum == serial){
                vbd.setStatus(status);
                break;
            }
        }
    }

    @Override
    public int createVRD() {
        VRD vrd = new VRD();
        vrd.start();
        vrds.add(vrd);
        VBD.vrdsChanged(getVRDnumbers());
        return vrd.id;
    }
    public void deleteVRD(int id){
        for (VRD vrd:vrds) {
            if(vrd.id == id){
                vrd.stopVRD();
                vrds.remove(vrd);
                break;
            }
        }
        VBD.vrdsChanged(getVRDnumbers());
    }

    @Override
    public void delSMSVRD(int id, boolean isSelected) {
        for (VRD vrd:vrds) {
            if(vrd.id == id){
                vrd.setDeleter(isSelected);
                break;
            }
        }
    }

    @Override
    public synchronized int getVrdSmsCount(int id) {
        for (VRD vrd:vrds) {
            if(vrd.id == id){
                return vrd.SMSnum;
            }
        }
        return 0;
    }

    @Override
    public synchronized int getBtsSmsCount(TYPE type, int id) {
            for (BTS b : leftBTSs) {
                if (b.id == id) {
                    return b.getSmsCount();
                }
            }
            for (BTS b : rightBTSs) {
                if (b.id == id) {
                    return b.getSmsCount();
                }
            }
        return 0;
    }

    @Override
    public synchronized int getBtsSent(TYPE type, int id) {
            for (BTS b : leftBTSs) {
                if (b.id == id) {
                    return b.getSent();
                }
            }
            for (BTS b : rightBTSs) {
                if (b.id == id) {
                    return b.getSent();
                }
            }
        return 0;
    }

    @Override
    public void createSTRUCTURE() {
        BTS bts = new BTS(Mode.LEFT);
        bts.start();
        leftBTSs.add(bts);
        ltv.BTScreated(Mode.LEFT,bts.id);
        bts = new BTS(Mode.RIGHT);
        bts.start();
        rightBTSs.add(bts);
        ltv.BTScreated(Mode.RIGHT,bts.id);
        BSC bsc = new BSC(0);
        bsc.start();
        ArrayList<BSC> bscsLine = new ArrayList<>();
        bscsLine.add(bsc);
        ltv.BSCcreated(bsc.line,bsc.id);
        BSCs[0] = bscsLine;
    }

    @Override
    public void rmLayer() {
        int layerLeastSms = Integer.MAX_VALUE;
        int layerWithLeastSMS = 0;
        int tmpSMSCount;
        for (int i = 0; i < BSCs.length; i++) {
            tmpSMSCount = 0;
            for (BSC b : BSCs[i]) {
                tmpSMSCount += b.SMS.size();
            }
            if (layerLeastSms > tmpSMSCount) {
                layerWithLeastSMS = i;
                layerLeastSms = tmpSMSCount;
            }
        }
        boolean stillrm = true;
        while(stillrm){
            BSC tmp = BSCs[layerWithLeastSMS].get(0);
            tmp.stillrun = false;
            BSCs[layerWithLeastSMS].remove(tmp);
            tmp.pushALl();
            if(!(BSCs[layerWithLeastSMS].size()>0)){
                stillrm = false;
            }
        }
//        for (BSC bsc : BSCs[layerWithLeastSMS]) {
//            BSCs[layerLeastSms].remove(bsc);
//            bsc.stillrun = false;
//            bsc.pushALl();
////            BSCs[layerWithLeastSMS].remove(bsc);
////            b.pushALl();
//        }
        for (int i = layerWithLeastSMS; i < BSCs.length - 1; i++) {
            BSCs[i] = BSCs[i + 1];
        }
        ArrayList<BSC>[] tmp = new ArrayList[BSCs.length - 1];
        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = BSCs[i];
        }
        BSCs = tmp;
        System.out.println(layerWithLeastSMS);
        ltv.rmlayer(layerWithLeastSMS);
    }

    @Override
    public synchronized int getBscSent(TYPE bsc, int id) {
            for (ArrayList<BSC> als : BSCs) {
                for (BSC bsc2 : als) {
                    if (bsc2.id == id) {
                        return bsc2.getSent();
                    }
                }
            }
        return 0;
    }

    @Override
    public synchronized int getBscSmsCount(TYPE bsc, int id) {
            for (ArrayList<BSC> als : BSCs) {
                for (BSC bsc2 : als) {
                    if (bsc2.id == id) {
                        return bsc2.getCount();
                    }
                }
            }
        return 0;
    }

    @Override
    public int frameClosed() {
        FIleManager.writeObjectsToFile(vbdinfo,"vbds.bin");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.exit(0);

        return 0;
    }

    public void addLayer(){
        BSC bsc = new BSC(BSCs.length);
        ArrayList<BSC>[] tmp = new ArrayList[BSCs.length+1];
        for (int i = 0; i < BSCs.length; i++) {
            tmp[i] = BSCs[i];
        }
        ArrayList<BSC> bscsLine = new ArrayList<>();
        bsc.start();
        ltv.createBSClayer();
        ltv.BSCcreated(tmp.length-1,bsc.id);
        bscsLine.add(bsc);
        tmp[tmp.length-1] = bscsLine;
        BSCs = tmp;
    }
    public synchronized static void bscReceiveSMS(byte[] sms,int warstwa){
        int leastSMSindex = 0;
        for (int i = 1; i < BSCs[warstwa].size(); i++) {
            if (BSCs[warstwa].get(i).SMS.size() < BSCs[warstwa].get(leastSMSindex).SMS.size()) {
                leastSMSindex = i;
            }
        }
        BSCs[warstwa].get(leastSMSindex).SMS.add(sms);

        ltv.dataUpdated(BSCs[warstwa].get(leastSMSindex).id, BSCs[warstwa].get(leastSMSindex).sent, BSCs[warstwa].get(leastSMSindex).SMS.size(), TYPE.BSC);
        if (BSCs[warstwa].get(leastSMSindex).SMS.size() >= 5) {
            createNewBSC(warstwa);
        }
    }

    private static void createNewBSC(int warstwa) {
        BSC newBSC = new BSC(warstwa);
        newBSC.start();
        ltv.BSCcreated(warstwa, newBSC.id);
        BSCs[warstwa].add(newBSC);
    }

    public synchronized static void leftReceiveSMS(byte[] sms){
        int leastSMSindex = 0;
        for (int i = 1; i < leftBTSs.size(); i++) {
            if(leftBTSs.get(i).SMS.size() < leftBTSs.get(leastSMSindex).SMS.size()){
                leastSMSindex = i;
            }
        }
        leftBTSs.get(leastSMSindex).SMS.add(sms);
        ltv.dataUpdated(leftBTSs.get(leastSMSindex).id, leftBTSs.get(leastSMSindex).sent, leftBTSs.get(leastSMSindex).SMS.size(), TYPE.BTS);
        if(leftBTSs.get(leastSMSindex).SMS.size() >= 5){
            createNewLeftBTS();
        }

    }
    public static void createNewLeftBTS(){
        BTS bts = new BTS(Mode.LEFT);
        bts.start();
        leftBTSs.add(bts);
        ltv.BTScreated(Mode.LEFT,bts.id);
    }

    public synchronized static void rightReceiveSMS(byte[] sms){
        int leastSMSindex = 0;
        for (int i = 1; i < rightBTSs.size(); i++) {
            if(rightBTSs.get(i).SMS.size() < rightBTSs.get(leastSMSindex).SMS.size()){
                leastSMSindex = i;
            }
        }
        rightBTSs.get(leastSMSindex).SMS.add(sms);
        ltv.dataUpdated(rightBTSs.get(leastSMSindex).id, rightBTSs.get(leastSMSindex).sent, rightBTSs.get(leastSMSindex).SMS.size(), TYPE.BTS);
        if(rightBTSs.get(leastSMSindex).SMS.size() >= 5){
            createNewRightBTS();
        }
    }
    public static void createNewRightBTS(){
        BTS bts = new BTS(Mode.RIGHT);
        bts.start();
        rightBTSs.add(bts);
        ltv.BTScreated(Mode.RIGHT,bts.id);
    }

    public synchronized static void vrdReceiveSMS(byte[] sms){
        int number = PduConverter.extractPhoneNumberFromPdu(sms);
        for (VRD v:vrds) {
            if(v.id == number){
                v.receiveSMS(sms);
                ltv.VRDdataChanged(v.id);
            }
        }
    }
    public static int[] getVRDnumbers(){
        int[] numbers = new int[vrds.size()];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = vrds.get(i).id;
        }
        return numbers;
    }
    public static void BTSdataChagned(int id){
        ltv.BTSdataChanged(id);
    }
    public static void BSCdataChagned(int id){
        ltv.BSCdataChanged(id);
    }


}
