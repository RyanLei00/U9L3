import java.util.*;

public class Runner {

    private LogicalVolume VG;
    private PhysicalVolume PV;
    private HardDrive HD;
    private Name name;
    private ArrayList<HardDrive> hdList = new ArrayList<HardDrive>();

    public void installDrive(String n, int s){
        for(int i = 0; i < hdList.size(); i++){
            if(hdList.get(i).getName().equals(n)){
                System.out.println("Could not add drive that is already installed.");
                break;
            }
        }
        HD = new HardDrive(n, s);
        hdList.add(HD);
        System.out.println("Drives installed successfully.");
    }

    public void listDrives(){
        for(int i = 0; i < hdList.size(); i++){
            System.out.println(hdList.get(i).getName() + " [" + hdList.get(i).getSize() + "G]");
        }
    }

    public void pvCreate(String name, String hdName){
        for(int i = 0; i < hdList.size(); i++){
            if(hdList.get(i).getName().equals(hdName)){
                HD = new HardDrive(hdList.get(i).getName(), hdList.get(i).getSize());
            }
        }
        if(HD.isPV()){
            System.out.println("This Hard Drive is already assigned to a Physical Volume.");
        }
        else {
            PV = new PhysicalVolume(name, HD);
        }
    }
}
