import java.util.*;

public class Runner {

    private VolumeGroup VG;
    private PhysicalVolume PV;
    private HardDrive HD;
    private Name name;
    private ArrayList<HardDrive> hdList = new ArrayList<HardDrive>();
    private ArrayList<PhysicalVolume> pvList = new ArrayList<PhysicalVolume>();
    private ArrayList<VolumeGroup> vgList = new ArrayList<VolumeGroup>();

    public void installDrive(String n, int s) {
        boolean isCreated = false;
        for (int i = 0; i < hdList.size(); i++) {
            if (hdList.get(i).getName().equals(n)) {
                System.out.println("Could not add drive that is already installed.");
                isCreated = true;
            }
        }
        if(!isCreated) {
            HD = new HardDrive(n, s);
            hdList.add(HD);
            System.out.println("Drives installed successfully.");
        }
    }

    public void listDrives() {
        for (int i = 0; i < hdList.size(); i++) {
            System.out.println(hdList.get(i).getName() + " [" + hdList.get(i).getSize() + "G]");
        }
    }

    public void pvCreate(String name, String hdName) {
        boolean isAssigned = false;
        for (int i = 0; i < pvList.size(); i++) {
            if (pvList.get(i).getName().equals(name)) {
                System.out.println("Could not add Physical Volume that is already installed.");
                isAssigned = true;
            }
        }
        if(!isAssigned) {
            for (int i = 0; i < hdList.size(); i++) {
                if (hdList.get(i).getName().equals(hdName)) {
                    HD = hdList.get(i);
                }
            }
        }
        if (HD.isPV() && !isAssigned) {
            System.out.println("This Hard Drive is already assigned to a Physical Volume.");
        }
        else {
            System.out.println("Hard Drive successfully assigned to a Physical Volume.");
            PV = new PhysicalVolume(name, HD);
            pvList.add(PV);
            }
        }

    public void listPhysicalVolume(){
        int t = 0;
        int k = 0;
        for(int i = 0; i < pvList.size(); i++) {
            if (pvList.get(i).isAssigned()){
                PhysicalVolume temp = pvList.get(t);
                pvList.set(t, pvList.get(i));
                pvList.set(i, temp);
                t++;
            }
        }
        for(int i = 0; i < pvList.size(); i++) {
            System.out.print("[" + pvList.get(i).getSize() + "G] ");
            if(pvList.get(i).isAssigned()) {
                System.out.print("[" + pvList.get(i).getVG() + "] ");
            }
            System.out.println("[" + pvList.get(i).getUUID() + "]");
        }
    }

    public void vgCreate(String name, String pvName) {
        boolean isAssigned = false;
        for (int i = 0; i < vgList.size(); i++) {
            if (vgList.get(i).getName().equals(name)) {
                System.out.println("Could not add Volume Group that is already installed.");
                isAssigned = true;
            }
        }
        for (int i = 0; i < pvList.size(); i++) {
            if (pvList.get(i).getName().equals(pvName)) {
                PV = pvList.get(i);
            }
        }
        if (PV.isAssigned() && !isAssigned) {
            System.out.println("This Physical Volume is already assigned to a Volume Group.");
        }
        else {
            System.out.println("Physical Volume successfully assigned to a Volume Group.");
            VG = new VolumeGroup(name, PV);
            PV.isAssigned();
            vgList.add(VG);
            }
        }

    public void vgExtend(String vgName, String pvName){
        boolean isThereVG = false;
        boolean isTherePV = false;
        for (int i = 0; i < vgList.size(); i++) {
            if (vgList.get(i).getName().equals(vgName)) {
                VG = vgList.get(i);
                isThereVG = true;
            }
        }
        if(!isThereVG){
            System.out.println("Volume Group could not be found.");
        }
        for (int i = 0; i < pvList.size(); i++) {
            if (pvList.get(i).getName().equals(pvName)) {
                PV = pvList.get(i);
                isTherePV = true;
            }
        }
        if(!isTherePV){
            System.out.println("Physical Volume could not be found.");
        }
        if(!PV.isAssigned() && isTherePV && isThereVG){
            VG.addNewPv(PV);
            System.out.println("Physical Volume successfully extended on Volume Group.");
        }
    }
}

