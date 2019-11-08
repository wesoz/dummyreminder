package android.com.br.dummyreminder.activitystates;

import android.app.Activity;
import android.com.br.dummyreminder.R;
import android.com.br.dummyreminder.activitystates.ActivityState;
import android.com.br.dummyreminder.database.GroupDAO;
import android.com.br.dummyreminder.to.Group;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class GroupNewState extends GroupState {

    public GroupNewState(Activity context) {
        super(context);
    }

    @Override
    public void add() {
        if (this.save()) {
            super.add();
        }
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.findItem(R.id.tb_button_edit_group).setVisible(false);
        return false;

    }

    @Override
    public boolean save() {
        Toast.makeText(super._context, "Save", Toast.LENGTH_SHORT).show();

        if (!super.validateFields()) {
            return false;
        }

        GroupDAO dao = new GroupDAO(super._context.getBaseContext());

        super._groupTO = new Group(0, this._txtName.getText().toString(), true);
        super._groupTO.setID(dao.insert(super._groupTO));

        dao.close();
        return true;
    }
}
