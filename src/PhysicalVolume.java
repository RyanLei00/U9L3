import java.util.*;

public class PhysicalVolume extends Name{

    private HardDrive HD;
    private VolumeGroup VG;


    public PhysicalVolume(String name, HardDrive HD){
        super(name, HD.getSize());
        this.HD = HD;
        HD.setPV();
    }

    public boolean isAssigned()
    {
        return VG!=null;
    }

    public void set(VolumeGroup vg)
    {
        this.VG = vg;
    }

    public String getVG()
    {
        if(VG == null) {
            return null;
        }
        return VG.getName();
    }
}
