package android.com.br.dummyreminder.database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.stitch.android.core.Stitch;
import com.mongodb.stitch.android.core.StitchAppClient;
import com.mongodb.stitch.android.services.mongodb.local.LocalMongoDbService;

import org.bson.Document;

public abstract class ObjectDAO {

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
}
