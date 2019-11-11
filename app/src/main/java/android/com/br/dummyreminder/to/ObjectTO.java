package android.com.br.dummyreminder.to;

import org.bson.types.ObjectId;

public abstract class ObjectTO implements IObjectTO {
    public ObjectId getObjectId () {
        return new ObjectId(this.getID());
    }
}
