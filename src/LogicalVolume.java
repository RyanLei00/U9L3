import java.util.*;

public class LogicalVolume extends Name {
    private VolumeGroup VG;

    public LogicalVolume(String name, int size, VolumeGroup VG) {
        super(name, size);
        this.VG = VG;
    }

    public String getVG()
    {
        if(VG == null) {
            return null;
        }
        return VG.getName();
    }
}