package android.com.br.dummyreminder.to;

import java.io.Serializable;

public class Group implements ObjectTO, Serializable {

    private int ID;
    private String name;
    private boolean isActive;

    public Group() {}

    public Group(int ID, String name, boolean isActive) {
        this.ID = ID;
        this.name = name;;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }
}
