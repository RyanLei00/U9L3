public class Main {
    public static void main(String[] args) {
        Runner r = new Runner();
        r.installDrive("sda", 100);
        r.installDrive("sdb", 500);
        r.installDrive("sdc", 50);
        r.listDrives();
    }
}
