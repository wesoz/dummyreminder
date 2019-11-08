package android.com.br.dummyreminder.to;

import java.io.Serializable;

public class Group implements ObjectTO, Serializable {

    private long ID;
    private String name;
    private boolean isActive;

    public Group() {}

    public Group(long ID, String name, boolean isActive) {
        this.ID = ID;
        this.name = name;;
        this.isActive = isActive;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }
}
