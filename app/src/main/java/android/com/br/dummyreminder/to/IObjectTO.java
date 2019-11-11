package android.com.br.dummyreminder.to;

import org.bson.Document;

public interface IObjectTO {
    void setID(String ID);
    String getID();
    Document toDocument(boolean includeID);
}
