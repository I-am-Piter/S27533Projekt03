package Logic;

import java.util.ArrayList;

public class Structure{
    static ArrayList<VBD> vbds = new ArrayList<>();
    static ArrayList<VRD> vrds = new ArrayList<>();

    static public VBD VBDcreated(CreateVBDEvent cve) {
        VBD vbd = new VBD(cve.SMS);
        vbds.add(vbd);
        return vbd;
    }

}
