package android.com.br.dummyreminder.database;

import android.com.br.dummyreminder.to.Group;
import android.com.br.dummyreminder.to.Item;
import android.com.br.dummyreminder.to.ObjectTO;
import android.util.Log;

import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.Date;

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

        super.getCollection().updateOne(query, update);
    }

    public long update(String groupID, Item item){
        /*Document query = new Document();
        query.put("_id", new ObjectId(groupID));
        query.put("items._id", new ObjectId(item.getID()));

        FindIterable<Document> documents = super.getCollection().find(query);

        for (Document document : documents) {
            String a = document.getString(GroupDAO.FIELD_NAME) ;
            boolean b = document.getBoolean(GroupDAO.FIELD_ISACTIVE);
            Date c =  document.getDate(GroupDAO.FIELD_CREATIONDATE);
        }*/
        Bson filter = Filters.and(Filters.eq("_id", new ObjectId(groupID)), Filters.eq("items._id", new ObjectId(item.getID())));

        FindIterable<Document> documents = super.getCollection().find(filter);

        for (Document document : documents) {
            String a = document.getString(GroupDAO.FIELD_NAME) ;
            boolean b = document.getBoolean(GroupDAO.FIELD_ISACTIVE);
            Date c =  document.getDate(GroupDAO.FIELD_CREATIONDATE);
        }

        Document update = new Document();
        Document itemDocument = new Document(GroupDAO.FIELD_ITEMS + ".$", this.toDocument(item, false));
        update.append("$set", itemDocument);

        UpdateResult result = super.getCollection().updateOne(filter, update);

        return result.getModifiedCount();
    }
}
