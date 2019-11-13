package android.com.br.dummyreminder.database;

import android.com.br.dummyreminder.Utils;
import android.com.br.dummyreminder.to.Group;
import android.com.br.dummyreminder.to.Item;
import android.com.br.dummyreminder.to.ObjectTO;

import com.mongodb.client.FindIterable;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class GroupDAO extends MongoDAO {

    public static String FIELD_NAME = "name";
    public static String FIELD_ISACTIVE = "isActive";
    public static String FIELD_CREATIONDATE = "CreationDate";

    public GroupDAO () { }

    @Override
    public Document toDocument(ObjectTO objectTO, boolean includeID) {
        Group groupTO = (Group)objectTO;
        Document newDocument = new Document();
        if (includeID && groupTO.getID() != null)
            newDocument.put("_id", groupTO.getObjectId());

        newDocument.put(GroupDAO.FIELD_NAME, groupTO.getName());
        newDocument.put(GroupDAO.FIELD_ISACTIVE, groupTO.isActive());
        if (groupTO.getCreationDate() == null)
            groupTO.setCreationDate(Utils.now());
        newDocument.put(GroupDAO.FIELD_CREATIONDATE, groupTO.getCreationDate());
        if (groupTO.getItems() != null && groupTO.getItems().size() > 0) {
            List<Document> itemDocuments = new ArrayList<>();
            ItemDAO itemDAO = new ItemDAO();
            for (Item item : groupTO.getItems()) {
                itemDocuments.add(itemDAO.toDocument(item, true));
            }
            newDocument.put("items", itemDocuments);
        }
        return newDocument;
    }

    @Override
    public ObjectTO fromDocument(Document document) {
        Group group = new Group(document.getObjectId("_id").toString(),
                document.getString(FIELD_NAME),
                document.getBoolean(FIELD_ISACTIVE),
                document.getDate(FIELD_CREATIONDATE));

        List<Document> itemDocuments = (List<Document>)document.get("items");
        List<Item> items = new ArrayList<>();
        if (itemDocuments != null) {
            ItemDAO itemDAO = new ItemDAO();
            for (Document itemDocument : itemDocuments) {
                items.add((Item) itemDAO.fromDocument(itemDocument));
            }
            group.setItems(items);
        }
        return group;
    }

    public List<Item> getItems(String groupID) {
        Group group = this.select(groupID);
        return group.getItems();
    }

    public List<Group> select() {

        List<Group> objects = new ArrayList<>();

        FindIterable<Document> documents = super.getCollection().find();

        for (Document document : documents) {
            objects.add((Group)fromDocument(document));
        }

        return objects;
    }

    public void insert(Group object) {
        Document newDocument = this.toDocument(object,false);

        super.getCollection().insertOne(newDocument);
        object.setID(newDocument.getObjectId("_id").toString());
    }

    public void update(Group object) {

        Document query = new Document();
        query.append("_id", object.getObjectId());
        Document update = new Document();
        update.append("$set", this.toDocument(object, false));

        super.getCollection().updateOne(query,update);
    }

    public Group select(String ID) {
        Document query = new Document();
        query.append("_id", new ObjectId(ID));
        FindIterable<Document> documents = super.getCollection().find(query);

        return (Group)fromDocument(documents.first());
    }

    public void delete(String ID) {
        Document query = new Document();
        query.append("_id", new ObjectId(ID));
        super.getCollection().deleteOne(query);
    }
}
