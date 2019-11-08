package android.com.br.dummyreminder.activitystates;

import android.view.Menu;

public interface IActivityState {

    void add();
    void onCreate();
    void onResume();
    boolean onCreateOptionsMenu(Menu menu);
    boolean save();
}
