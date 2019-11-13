package android.com.br.dummyreminder.database;

import android.com.br.dummyreminder.to.ObjectTO;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.stitch.android.core.Stitch;
import com.mongodb.stitch.android.core.StitchAppClient;
import com.mongodb.stitch.android.services.mongodb.local.LocalMongoDbService;

import org.bson.Document;

public abstract class MongoDAO {

    protected String _collectionName = "Group";

    public abstract ObjectTO fromDocument(Document document);
    public abstract Document toDocument(ObjectTO objectTO, boolean includeID);

    private String getCollectionName() { return this._collectionName; }

    protected MongoCollection<Document> getCollection() {
        StitchAppClient stitchAppClient = Stitch.getDefaultAppClient();
        MongoClient mongoClient = stitchAppClient.getServiceClient(LocalMongoDbService.clientFactory);
        return mongoClient
                .getDatabase(MongoHelper.getDatabaseName())
                .getCollection(this.getCollectionName());
    }
}
