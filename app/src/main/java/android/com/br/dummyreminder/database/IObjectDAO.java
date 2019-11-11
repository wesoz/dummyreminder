package android.com.br.dummyreminder.database;

import android.com.br.dummyreminder.to.IObjectTO;
import android.com.br.dummyreminder.to.ObjectTO;

import org.bson.Document;

import java.util.List;

public interface IObjectDAO {

    Document toDocument(ObjectTO objectTO, boolean includeID);
    ObjectTO fromDocument(Document document);
    String insert(ObjectTO object);
    int update(ObjectTO object);
    List<ObjectTO> select();
    ObjectTO select(String ID);
    void delete(String ID);
}
