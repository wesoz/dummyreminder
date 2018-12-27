package android.com.br.dummyreminder.database;

import android.com.br.dummyreminder.to.ObjectTO;

import java.util.List;

public interface ObjectDAO {

    long insert(ObjectTO object);
    void update(ObjectTO object);
    List<ObjectTO> select();
    ObjectTO select(int ID);
    void delete(int ID);
}
