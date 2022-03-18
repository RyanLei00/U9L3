import java.util.*;

public class Name {

    private String uuid;
    private String name;
    private int size;

    public Name(String name, int size){
        this.name = name;
        this.size = size;
        UUID u = UUID.randomUUID();
        this.uuid = u.toString();
    }

    public String getName() {
        return name;
    }

    public String getUUID() {
        return uuid;
    }

    public int getSize() {
        return size;
    }
}
