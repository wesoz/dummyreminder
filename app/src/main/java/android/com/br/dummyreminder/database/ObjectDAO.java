package android.com.br.dummyreminder.database;

import android.com.br.dummyreminder.to.ObjectTO;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.stitch.android.core.Stitch;
import com.mongodb.stitch.android.core.StitchAppClient;
import com.mongodb.stitch.android.services.mongodb.local.LocalMongoDbService;
import org.bson.Document;
import org.bson.types.ObjectId;
import java.util.ArrayList;
import java.util.List;

public abstract class ObjectDAO implements IObjectDAO {

    String _collectionName;

    public ObjectDAO(String collectionName) {
        this._collectionName = collectionName;
    }

    private String getCollectionName() { return this._collectionName; }
    protected MongoCollection<Document> getCollection() {
        StitchAppClient stitchAppClient =Stitch.getDefaultAppClient();
        MongoClient mongoClient = stitchAppClient.getServiceClient(LocalMongoDbService.clientFactory);
        return mongoClient
                .getDatabase(MongoHelper.getDatabaseName())
                .getCollection(this.getCollectionName());
    }

    public List<ObjectTO> select() {

        List<ObjectTO> objects = new ArrayList<>();

        FindIterable<Document> documents = this.getCollection().find();

        for (Document document : documents) {
            objects.add(fromDocument(document));
        }

        return objects;
    }

    @Override
    public String insert(ObjectTO object) {
        Document newDocument = this.toDocument(object,false);

        this.getCollection().insertOne(newDocument);
        object.setID(newDocument.getObjectId("_id").toString());

        return object.getID();
    }

    public int update(ObjectTO object) {

        Document query = new Document();
        query.append("_id", object.getObjectId().toString());
        Document update = new Document();
        update.append("$set", this.toDocument(object, false));

        this.getCollection().updateOne(query,update);

        return 1;
    }

    public ObjectTO select(String ID) {
        Document query = new Document();
        query.append("_id", new ObjectId(ID));
        FindIterable<Document> documents = this.getCollection().find(query);

        return fromDocument(documents.first());
    }

    public void delete(String ID) {
        Document query = new Document();
        query.append("_id", new ObjectId(ID));
        this.getCollection().deleteOne(query);
    }
}
