package Logic;

import Connectors.LogicToView;
import Connectors.ViewToLogic;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class Structure implements ViewToLogic {
    static LogicToView ltv;
    static ArrayList<VBD> vbds = new ArrayList<>();
    static ArrayList<VRD> vrds = new ArrayList<>();
    static ArrayList<BTS> leftBTSs = new ArrayList<>();
    static ArrayList<BTS> rightBTSs = new ArrayList<>();
    static ArrayList<BSC>[] BSCs = new ArrayList[1];

    public static void setLtv(LogicToView ltv2){
        ltv = ltv2;
    }

    @Override
    public int createVBD(String sms) {
        VBD vbd = new VBD(sms);
        vbd.start();
        vbds.add(vbd);
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
    public int getBtsSmsCount(TYPE type, int id) {
        for (BTS b:leftBTSs) {
            if(b.id == id){
                return b.SMS.size();
            }
        }
        for (BTS b:rightBTSs) {
            if(b.id == id){
                return b.SMS.size();
            }
        }
        return 0;
    }

    @Override
    public int getBtsSent(TYPE type, int id) {
        for (BTS b:leftBTSs) {
            if(b.id == id){
                return b.sent;
            }
        }
        for (BTS b:rightBTSs) {
            if(b.id == id){
                return b.sent;
            }
        }
        return 0;
    }

    @Override
    public void createSTRUCTURE() {
        BTS bts = new BTS(Mode.LEFT);
        leftBTSs.add(bts);
        ltv.BTScreated(Mode.LEFT,bts.id);
        bts = new BTS(Mode.RIGHT);
        rightBTSs.add(bts);
        ltv.BTScreated(Mode.RIGHT,bts.id);
        BSC bsc = new BSC(0);
        ArrayList<BSC> bscsLine = new ArrayList<>();
        bscsLine.add(bsc);
        ltv.BSCcreated(bsc.line,bsc.id);
        BSCs[0] = bscsLine;
    }

    public void createNewBSCLine(){
        BSC bsc = new BSC(BSCs.length);
        ArrayList<BSC>[] tmp = new ArrayList[BSCs.length+1];
        for (int i = 0; i < BSCs.length; i++) {
            tmp[i] = BSCs[i];
        }
        ArrayList<BSC> bscsLine = new ArrayList<>();
        bsc.start();
        bscsLine.add(bsc);
        tmp[tmp.length-1] = bscsLine;
    }
    public static void bscReceiveSMS(String sms,int warstwa){
        int leastSMSindex = 0;
        for (int i = 1; i < BSCs[warstwa].size(); i++) {
            if(BSCs[warstwa].get(i).SMS.size() < BSCs[warstwa].get(leastSMSindex).SMS.size()){
                leastSMSindex = i;
            }
        }
        BSCs[warstwa].get(leastSMSindex).SMS.add(sms);
        ltv.dataUpdated(BSCs[warstwa].get(leastSMSindex).id,BSCs[warstwa].get(leastSMSindex).sent,BSCs[warstwa].get(leastSMSindex).SMS.size(),TYPE.BSC);
        if(BSCs[warstwa].get(leastSMSindex).SMS.size() > 5){
            createNewBSC(warstwa);
        }
    }

    private static void createNewBSC(int warstwa) {
        BSC newBSC = new BSC(warstwa);
        newBSC.start();
        ltv.BSCcreated(warstwa, newBSC.id);
        BSCs[warstwa].add(newBSC);
    }

    public static void leftReceiveSMS(String sms){
        int leastSMSindex = 0;
        for (int i = 1; i < leftBTSs.size(); i++) {
            if(leftBTSs.get(i).SMS.size() < leftBTSs.get(leastSMSindex).SMS.size()){
                leastSMSindex = i;
            }
        }
        leftBTSs.get(leastSMSindex).SMS.add(sms);
        ltv.dataUpdated(leftBTSs.get(leastSMSindex).id,leftBTSs.get(leastSMSindex).sent,leftBTSs.get(leastSMSindex).SMS.size(),TYPE.BTS);
        if(leftBTSs.get(leastSMSindex).SMS.size() > 5){
            createNewLeftBTS();
        }

    }
    public static void createNewLeftBTS(){
        BTS bts = new BTS(Mode.LEFT);
        bts.start();
        leftBTSs.add(bts);
        ltv.BTScreated(Mode.LEFT,bts.id);
    }

    public static void rightReceiveSMS(String sms){
        System.out.println("sms received");
        int leastSMSindex = 0;
        for (int i = 1; i < rightBTSs.size(); i++) {
            if(rightBTSs.get(i).SMS.size() < rightBTSs.get(leastSMSindex).SMS.size()){
                leastSMSindex = i;
            }
        }
        rightBTSs.get(leastSMSindex).SMS.add(sms);
        ltv.dataUpdated(rightBTSs.get(leastSMSindex).id,rightBTSs.get(leastSMSindex).sent,rightBTSs.get(leastSMSindex).SMS.size(),TYPE.BTS);
        if(rightBTSs.get(leastSMSindex).SMS.size() > 5){
            createNewRightBTS();
        }
    }
    public static void createNewRightBTS(){
        BTS bts = new BTS(Mode.RIGHT);
        bts.start();
        rightBTSs.add(bts);
        ltv.BTScreated(Mode.RIGHT,bts.id);
    }

    public static void vrdReceiveSMS(String sms){

    }

}
