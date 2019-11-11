package android.com.br.dummyreminder.database;

import android.com.br.dummyreminder.to.IObjectTO;

import java.util.List;

public interface IObjectDAO {

    String insert(IObjectTO object);
    int update(IObjectTO object);
    List<IObjectTO> select();
    IObjectTO select(String ID);
    void delete(String ID);
}
