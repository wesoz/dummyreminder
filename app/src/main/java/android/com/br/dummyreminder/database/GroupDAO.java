package android.com.br.dummyreminder.database;

import android.com.br.dummyreminder.to.Group;
import android.com.br.dummyreminder.to.Item;
import android.com.br.dummyreminder.to.IObjectTO;
import android.com.br.dummyreminder.to.ObjectTO;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.stitch.android.core.Stitch;
import com.mongodb.stitch.android.core.StitchAppClient;
import com.mongodb.stitch.android.services.mongodb.local.LocalMongoDbService;

import org.bson.BsonDocument;
import org.bson.BsonType;
import org.bson.BsonValue;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class GroupDAO extends ObjectDAO implements IObjectDAO {

    public GroupDAO () {
        super("Group");
    }

    @Override
    public String insert(IObjectTO object) {
        Document newDocument = object.toDocument();

        super.getCollection().insertOne(newDocument);
        object.setID(newDocument.getObjectId("_id").toString());

        return object.getID();
    }

    @Override
    public int update(ObjectTO object) {

        Document query = new Document();
        query.append("_id", object.getObjectId().toString());
        Document update = new Document();
        update.append("$set", object.toDocument(false));

        super.getCollection().updateOne(query,update);

        return 1;
    }

    @Override
    public List<IObjectTO> select() {

        List<IObjectTO> objects = new ArrayList<>();

        return objects;
    }

    public int getItemCount(long groupID) {

        int itemCount = 0;

        return itemCount;
    }

    public List<IObjectTO> getItems(long groupID) {

        List<IObjectTO> objects = new ArrayList<>();

        return objects;
    }

    @Override
    public IObjectTO select(String ID) {
        return null;
    }

    @Override
    public void delete(String ID) {

    }
}
