import java.util.*;
import java.util.function.LongFunction;
import static java.lang.Integer.parseInt;

public class Runner {

    private VolumeGroup VG;
    private PhysicalVolume PV;
    private HardDrive HD;
    private LogicalVolume LV;
    private ArrayList<HardDrive> hdList = new ArrayList<HardDrive>();
    private ArrayList<PhysicalVolume> pvList = new ArrayList<PhysicalVolume>();
    private ArrayList<VolumeGroup> vgList = new ArrayList<VolumeGroup>();
    private ArrayList<LogicalVolume> lvList = new ArrayList<LogicalVolume>();


    public void running(String command, String input){
        String[] cmdArr = input.split(" ");
        if(command.equals("install-drive")){
            String name = cmdArr[0];
            int size = Integer.parseInt(cmdArr[1].substring(0,cmdArr[1].length()));
            installDrive(name, size);
        }
        else if(command.equals("list-drives")){
            listDrives();
        }
        else if(command.equals("pvcreate")){
            String name = cmdArr[0];
            String drive = cmdArr[1];
            pvCreate(name, drive);
        }
        else if(command.equals("pvlist")){
            listPhysicalVolume();
        }
        else if(command.equals("vgcreate")){
            String name = cmdArr[0];
            String pvName = cmdArr[1];
            vgCreate(name, pvName);
        }
        else if(command.equals("vgextend")){
            String name = cmdArr[0];
            String pvName = cmdArr[1];
            vgExtend(name,pvName);
        }
        else if(command.equals("vglist")){
            listVolumeGroup();
        }
        else if(command.equals("lvcreate")){
            String name = cmdArr[0];
            int size = Integer.parseInt(cmdArr[1].substring(0,cmdArr[1].length()-1));
            String vgName = cmdArr[2];
            lvCreate(name, size, vgName);
        }
        else if(command.equals("lvlist")){
            listLogicalVolume();
        }
        else if(command.equals("exit")){
            System.out.println("Good-bye!");
            System.exit(0);
        }
        else{
            System.out.println("Invalid Input");
        }
    }

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
            System.out.println(n + " drive installed successfully.");
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
            System.out.println(hdName + " Hard Drive successfully assigned to " + name + " Physical Volume.");
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
            System.out.print(pvList.get(i).getName());
            System.out.print(" [" + pvList.get(i).getSize() + "G] ");
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
            System.out.println(pvName + " Physical Volume successfully assigned to " + name + " Volume Group.");
            VG = new VolumeGroup(name, PV);
            VG.addNewPv(PV);
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
            PV.isAssigned();
            System.out.println(pvName + " Physical Volume successfully extended on " + vgName + " Volume Group.");
        }
    }

    public void listVolumeGroup(){
        for(int i = 0; i < vgList.size(); i++){
            System.out.print(vgList.get(i).getName() + ": ");
            System.out.print("total: [" + vgList.get(i).getSize() + "G] ");
            int available = vgList.get(i).getSize() -  vgList.get(i).getSizeUsed();
            System.out.print("available: [" + available + "G] [");
            for(int j = 0; j < pvList.size(); j++) {
                if(pvList.get(j).getVG() == vgList.get(i).getName()) {
                    System.out.print(pvList.get(j).getName() + " ");
                }
            }
            System.out.print("] ");
            System.out.print("[" + vgList.get(i).getUuid() + "]");
            System.out.println();
        }
    }

    public void lvCreate(String name, int size, String vgName){
        boolean isAssigned = false;
        for (int i = 0; i < lvList.size(); i++) {
            if (lvList.get(i).getName().equals(name)) {
                System.out.println("Could not add Logical Volume that is already installed.");
                isAssigned = true;
            }
        }
        if(!isAssigned) {
            for (int i = 0; i < vgList.size(); i++) {
                if (vgList.get(i).getName().equals(vgName)) {
                    VG = vgList.get(i);
                }
            }
            LV = new LogicalVolume(name, size, VG);
            lvList.add(LV);
            if(VG.addNewLv(LV))
            System.out.println(name + " Logical Volume successfully installed");
        }
    }

    public void listLogicalVolume(){
        for(int i = 0; i < lvList.size(); i++){
            System.out.print(lvList.get(i).getName() + ": ");
            System.out.print("[" + lvList.get(i).getSize() + "G] ");
            for(int j = 0; j < vgList.size(); j++) {
                if(lvList.get(i).getVG() == vgList.get(j).getName()) {
                    System.out.print("[" + vgList.get(j).getName() + "] ");
                }
            }
            System.out.print("[" + lvList.get(i).getUUID() + "] ");
            System.out.println();
        }
    }

}