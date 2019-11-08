package android.com.br.dummyreminder.activitystates;

import android.app.Activity;
import android.com.br.dummyreminder.ItemDetail;
import android.com.br.dummyreminder.R;
import android.com.br.dummyreminder.activitystates.ActivityState;
import android.com.br.dummyreminder.database.GroupDAO;
import android.com.br.dummyreminder.to.Group;
import android.content.Intent;
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
            Intent intent = new Intent(super._context, ItemDetail.class);
            intent.putExtra("group", this._groupTO);
            super._context.setResult(0, intent);
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
        menu.findItem(R.id.tb_button_add).setVisible(false);
        return false;

    }

    @Override
    public boolean save() {
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
