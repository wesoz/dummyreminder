package android.com.br.dummyreminder.database;

import android.com.br.dummyreminder.to.Item;
import android.com.br.dummyreminder.to.ObjectTO;
import android.util.Log;

import org.bson.Document;
import org.bson.types.ObjectId;

public class ItemDAO extends MongoDAO {

    public static String FIELD_NAME = "name";
    public static String FIELD_DATE = "date";
    public static String FIELD_WEEKDAYS = "weekdays";
    public static String FIELD_HOUR = "hour";
    public static String FIELD_MINUTE = "minute";
    public static String FIELD_ISACTIVE = "isActive";
    public static String FIELD_ISTRIGGERED = "isTriggered";
    public static String FIELD_CREATIONDATE = "CreationDate";

    @Override
    public Document toDocument(ObjectTO objectTO, boolean includeID) {
        Item itemTO = (Item)objectTO;
        Document newDocument = new Document();
        if (includeID && itemTO.getID() != null)
            newDocument.put("_id", itemTO.getObjectId());

        newDocument.put(ItemDAO.FIELD_NAME, itemTO.getName());
        newDocument.put(ItemDAO.FIELD_DATE, itemTO.getDate());
        newDocument.put(ItemDAO.FIELD_WEEKDAYS, itemTO.getWeekdays());
        newDocument.put(ItemDAO.FIELD_HOUR, itemTO.getHour());
        newDocument.put(ItemDAO.FIELD_MINUTE, itemTO.getMinute());
        newDocument.put(ItemDAO.FIELD_ISACTIVE, itemTO.isActive());
        newDocument.put(ItemDAO.FIELD_ISTRIGGERED, itemTO.isTriggered());
        newDocument.put(ItemDAO.FIELD_CREATIONDATE, itemTO.getCreationDate());
        return newDocument;
    }

    @Override
    public ObjectTO fromDocument(Document document) {
        return new Item(
                document.getObjectId("_id").toString(),
                document.getString(FIELD_NAME),
                document.getDate(FIELD_DATE),
                document.getInteger(FIELD_WEEKDAYS),
                document.getInteger(FIELD_HOUR),
                document.getInteger(FIELD_MINUTE),
                document.getBoolean(FIELD_ISACTIVE),
                document.getBoolean(FIELD_ISTRIGGERED),
                document.getDate(FIELD_CREATIONDATE)
            );
    }

    public void insert(String groupID, Item item) {
        Document query = new Document();
        query.append("_id", new ObjectId(groupID));
        Document update = new Document();
        Document itemDocument = new Document(GroupDAO.FIELD_ITEMS, this.toDocument(item, true));
        update.append("$push", itemDocument);

        try {
            super.getCollection().updateOne(query, update);
        } catch (Exception ex) {
            Log.e("InsertItem", ex.getMessage());
        }
    }

    public int update(ObjectTO object){
        return 1;
    }
}
