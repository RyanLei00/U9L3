import java.util.*;

public class Runner {

    private VolumeGroup VG;
    private PhysicalVolume PV;
    private HardDrive HD;
    private Name name;
    private ArrayList<HardDrive> hdList = new ArrayList<HardDrive>();
    private ArrayList<PhysicalVolume> pvList = new ArrayList<PhysicalVolume>();
    private ArrayList<VolumeGroup> vgList = new ArrayList<VolumeGroup>();
    private ArrayList<PhysicalVolume> pvListSorted = new ArrayList<PhysicalVolume>();


    public void installDrive(String n, int s) {
        for (int i = 0; i < hdList.size(); i++) {
            if (hdList.get(i).getName().equals(n)) {
                System.out.println("Could not add drive that is already installed.");
                System.exit(0);
            }
        }
        HD = new HardDrive(n, s);
        hdList.add(HD);
        System.out.println("Drives installed successfully.");
    }

    public void listDrives() {
        for (int i = 0; i < hdList.size(); i++) {
            System.out.println(hdList.get(i).getName() + " [" + hdList.get(i).getSize() + "G]");
        }
    }

    public void pvCreate(String name, String hdName) {
        for (int i = 0; i < pvList.size(); i++) {
            if (pvList.get(i).getName().equals(name)) {
                System.out.println("Could not add Physical Volume that is already installed.");
                System.exit(0);
            }
        }
        for (int i = 0; i < hdList.size(); i++) {
            if (hdList.get(i).getName().equals(hdName)) {
                HD = hdList.get(i);
            }
        }
        if (HD.isPV()) {
            System.out.println("This Hard Drive is already assigned to a Physical Volume.");
            System.exit(0);
        }
        else {
            System.out.println("Hard Drive successfully assigned to a Physical Volume.");
            PV = new PhysicalVolume(name, HD);
            pvList.add(PV);
            }
        }

    public void listPhysicalVolume(){
        for(int i = 0; i < pvList.size(); i++) {
            if (pvList.get(i).getSize() > pvList.get(i).getSize()) {
                pvListSorted.add(pvList.get(i));
            }
        }
        for(int i = 0; i < pvListSorted.size(); i++) {
            if (pvListSorted.get(i).isAssigned()){
                PhysicalVolume temp = pvListSorted.get(0);
                pvListSorted.set(0, pvListSorted.get(i));
                pvListSorted.set(i, temp);
            }
        }
        for(int i = 0; i < pvListSorted.size(); i++) {
            System.out.print("[" + pvListSorted.get(i).getSize() + "G] ");
            if(pvListSorted.get(i).isAssigned()){
                System.out.print("[" + pvListSorted.get(i).getVG() + "] ");
            }
            System.out.println("[" + pvListSorted.get(i).getUUID() + "]");
        }
    }

    public void vgCreate(String name, String pvName) {
    for (int i = 0; i < vgList.size(); i++) {
        if (vgList.get(i).getName().equals(name)) {
            System.out.println("Could not add Volume Group that is already installed.");
            System.exit(0);
        }
    }
    for (int i = 0; i < pvList.size(); i++) {
        if (pvList.get(i).getName().equals(pvName)) {
            PV = pvList.get(i);
        }
    }
    if (PV.isAssigned()) {
        System.out.println("This Physical Volume is already assigned to a Volume Group.");
        System.exit(0);
    }
    else {
        System.out.println("Physical Volume successfully assigned to a Volume Group.");
        VG = new VolumeGroup(name, PV);
        PV.isAssigned();
        vgList.add(VG);
       }
    }

    /*
    public void vgExtend(String vgName, String pvName){
        for (int i = 0; i < vgList.size(); i++) {
            if (vgList.get(i).getName().equals(vgName)) {
                VG = vgList.get(i);
            }
            else{
                System.out.println("Volume Group could not be found.");
                System.exit(0);
            }
        }
        for (int i = 0; i < pvList.size(); i++) {
            if (pvList.get(i).getName().equals(pvName)) {
                PV = pvList.get(i);
            }
            else{
                System.out.println("Physical Volume could not be found.");
                System.exit(0);
            }
        }
        if(!(PV.isAssigned())){
            VG.addNewPv(PV);
            System.out.println("Physical Volume successfully extended on Volume Group.");
        }
    }
     */
}

