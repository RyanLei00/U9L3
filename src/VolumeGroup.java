import java.util.*;

public class VolumeGroup{

    private int size;
    private String uuid;
    private String name;
    private ArrayList<PhysicalVolume> pvList;
    private ArrayList<LogicalVolume> lvList;
    private int sizeUsed;

    public VolumeGroup(String name, PhysicalVolume pV){
        this.pvList = new ArrayList<PhysicalVolume>();
        //addNewPv(pV);
        this.name = name;
        UUID u = UUID.randomUUID();
        this.uuid = u.toString();
        size = 0;
    }

    public boolean addNewLv(LogicalVolume LV)
    {
        if(sizeUsed+LV.getSize() > size){
            return false;
        }
        else {
            lvList.add(LV);
            sizeUsed += LV.getSize();
            return true;
        }
    }

    public String getUuid() {
        return uuid;
    }

    public int getSize() {
        return size;
    }

    public int getSizeUsed() {
        return sizeUsed;
    }

    public String getName() {
        return name;
    }

    public ArrayList<PhysicalVolume> getPvList() {
        return pvList;
    }

    public ArrayList<LogicalVolume> getLvList() {
        return lvList;
    }
}
/*
public void addNewPv(PhysicalVolume pv)
    {
        pvList.add(pv);
        pv.assign(this);
        storage += pv.getStorage();
    }
 */