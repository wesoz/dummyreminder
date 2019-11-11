package android.com.br.dummyreminder.database;

import android.com.br.dummyreminder.Utils;
import android.com.br.dummyreminder.to.Group;
import android.com.br.dummyreminder.to.IObjectTO;
import android.com.br.dummyreminder.to.Item;
import android.com.br.dummyreminder.to.ObjectTO;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class GroupDAO extends ObjectDAO implements IObjectDAO {

    public static String FIELD_NAME = "name";
    public static String FIELD_ISACTIVE = "isActive";
    public static String FIELD_CREATIONDATE = "CreationDate";

    public GroupDAO () {
        super("Group");
    }

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
                itemDocuments.add(itemDAO.toDocument(item, false));
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

    public int getItemCount(String groupID) {


        int itemCount = 0;

        return itemCount;
    }

    public List<IObjectTO> getItems(String groupID) {

        List<IObjectTO> objects = new ArrayList<>();

        return objects;
    }
}
