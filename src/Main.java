import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Runner r = new Runner();
        String command = "";
        String userInput = "";

        System.out.println("Welcome to the LVM system! Enter your commands: ");
        while(!userInput.equals("exit"))
        {
            System.out.print("cmd# ");
            command = input.next();
            userInput = input.nextLine();
            userInput = (userInput.length()>0)? userInput.substring(1): userInput;
            r.running(command, userInput);
        }



/*
        r.installDrive("sda", 100);
        r.installDrive("sdb", 500);
        r.installDrive("sdc", 50);
        r.installDrive("sdd", 20);
        //r.listDrives();

        r.pvCreate("pv1", "sda");
        r.pvCreate("pv2", "sdb");
        r.pvCreate("pv3", "sdd");
        r.pvCreate("pv4", "sdc");

        //r.listPhysicalVolume();

        r.vgCreate("vg1", "pv1");
        r.vgExtend("vg1", "pv3");
        r.vgCreate("vg2", "pv2");

        //r.listPhysicalVolume();

        r.listVolumeGroup();

        r.lvCreate("lv1", 50, "vg1");
        r.lvCreate("lv2", 100, "vg2");
        r.lvCreate("lv3", 65, "vg1");
        r.listVolumeGroup();

        r.listLogicalVolume();
*/

    }
}
