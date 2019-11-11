package android.com.br.dummyreminder.to;

import org.bson.Document;

import java.io.Serializable;
import java.time.ZonedDateTime;

public class Group extends ObjectTO implements IObjectTO, Serializable {

    private String ID;
    private String name;
    private boolean isActive;
    private ZonedDateTime CreationDate;

    public Group() {}

    public Group(String ID, String name, boolean isActive) {
        this.ID = ID;
        this.name = name;;
        this.isActive = isActive;
        this.CreationDate = ZonedDateTime.now();
    }

    public String getID() {
        return ID;
    }


    @Override
    public Document toDocument(boolean includeID) {
        Document newDocument = new Document();
        if (includeID && this.ID != null)
            newDocument.put("_id", super.getObjectId());

        newDocument.put("name", this.getName());
        newDocument.put("isActive", this.isActive());
        if (this.CreationDate == null)
            this.CreationDate = ZonedDateTime.now();
        newDocument.put("CreationDate", this.getCreationDate());
        return newDocument;
    }

    public void setID(String ID) {
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

    public ZonedDateTime getCreationDate() {
        return CreationDate;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.CreationDate = creationDate;
    }
}
