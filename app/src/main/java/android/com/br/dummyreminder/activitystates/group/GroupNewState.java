package android.com.br.dummyreminder.activitystates.group;

import android.app.Activity;
import android.com.br.dummyreminder.ItemDetail;
import android.com.br.dummyreminder.R;
import android.com.br.dummyreminder.database.GroupDAO;
import android.com.br.dummyreminder.to.Group;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;

public class GroupNewState extends GroupState {

    public GroupNewState(Activity context) {
        super(context, null);
        super._txtName.requestFocus();
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
        try {
            if (!super.validateFields()) return false;

            GroupDAO dao = new GroupDAO();

            super._groupTO = new Group(this._txtName.getText().toString(), true);
            dao.insert(super._groupTO);

            return true;
        } catch (Exception ex) {
            Log.e("Group.Save", ex.getMessage());
            Log.e("Group.Save", ex.getStackTrace().toString());
        }
        return false;
    }
}
