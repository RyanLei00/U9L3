import java.util.UUID;

public class Name {

    private String name;
    private UUID u;

    public Name(String name){
        this.name = name;
        u = UUID.randomUUID();
    }

    public String getName() {
        return name;
    }

    public UUID getU() {
        return u;
    }
}
