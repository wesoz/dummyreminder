package android.com.br.dummyreminder.activitystates;
import android.app.Activity;

public abstract class ActivityState implements IActivityState {

    Activity _context;
    public ActivityState(Activity context) { this._context = context; }
}
