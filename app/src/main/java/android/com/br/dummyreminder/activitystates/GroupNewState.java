package android.com.br.dummyreminder.activitystates;

import android.app.Activity;
import android.com.br.dummyreminder.R;
import android.com.br.dummyreminder.database.GroupDAO;
import android.com.br.dummyreminder.to.Group;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class GroupNewState extends ActivityState {

    public GroupNewState(Activity context) {
        super(context);
    }

    @Override
    public void add() {

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    @Override
    public void save() {
        Toast.makeText(super._context, "Save", Toast.LENGTH_SHORT).show();
        TextView txtName = super._context.findViewById(R.id.txtName_Group);

        GroupDAO dao = new GroupDAO(super._context.getBaseContext());

        Group group = new Group(0, txtName.getText().toString(), true);
        dao.insert(group);

        dao.close();
    }
}
