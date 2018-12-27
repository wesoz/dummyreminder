package android.com.br.dummyreminder.to;

public class Group implements ObjectTO {

    private int ID;
    private String name;
    private String description;
    private boolean isActive;

    public Group() {}

    public Group(int ID, String name, String description, boolean isActive) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.isActive = isActive;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }
}
