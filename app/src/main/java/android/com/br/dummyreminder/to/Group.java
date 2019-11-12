package android.com.br.dummyreminder.to;

import android.com.br.dummyreminder.Utils;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Group extends ObjectTO implements IObjectTO, Serializable {

    private String _ID;
    private String _name;
    private boolean _isActive;
    private Date _creationDate;

    private List<Item> _items;

    public Group() {}

    public Group(String ID, String name, boolean isActive, Date creationDate) {
        this(name, isActive, creationDate);
        this._ID = ID;
    }

    public Group(String name, boolean isActive, Date creationDate) {

        this(name, isActive);
        this._creationDate = creationDate;
    }

    public Group(String name, boolean isActive) {

        this._ID = null;
        this._name = name;
        this._isActive = isActive;
        this._creationDate = Utils.now();
    }

    public Group(String ID, String name, boolean isActive) {
        this(name, isActive);
        this._ID = ID;
    }

    public String getID() {
        return _ID;
    }

    public List<Item> getItems() { return _items; }

    public void setItems(List<Item> _items) { this._items = _items; }

    public void setID(String ID) {
        this._ID = ID;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        this._name = name;
    }

    public boolean isActive() {
        return _isActive;
    }

    public void setActive(boolean active) {
        this._isActive = active;
    }

    public Date getCreationDate() {
        return _creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this._creationDate = creationDate;
    }
}
