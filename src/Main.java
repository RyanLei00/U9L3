public class Main {
    public static void main(String[] args) {
        Runner r = new Runner();
        r.installDrive("sda", 100);
        r.installDrive("sdb", 500);
        r.installDrive("sdc", 50);
        r.installDrive("sdd", 20);
        r.listDrives();

        r.pvCreate("pv1", "sda");
        r.pvCreate("pv2", "sdb");
        r.pvCreate("pv3", "sdd");
        r.pvCreate("pv4", "sdc");

        r.listPhysicalVolume();

        r.vgCreate("vg1", "pv1");
        r.vgExtend("vg1", "pv3");
        r.vgCreate("vg2", "pv2");


        r.listPhysicalVolume();
    }
}
