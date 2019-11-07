package android.com.br.dummyreminder.activitystates;
import android.app.Activity;
import android.com.br.dummyreminder.to.ObjectTO;

public abstract class ActivityState implements IActivityState {

    Activity _context;
    public ActivityState(Activity context) { this._context = context; }
}
