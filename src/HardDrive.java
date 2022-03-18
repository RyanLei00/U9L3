public class HardDrive{

    private int size;
    private String name;
    private boolean isPV;
    
    public HardDrive(String name, int size){
        this.name = name;
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    public boolean isPV() {
        return isPV;
    }

    public void setPV()
    {
        isPV = true;
    }
}
