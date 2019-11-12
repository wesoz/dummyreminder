package android.com.br.dummyreminder.activitystates.item;

import android.app.Activity;
import android.com.br.dummyreminder.database.GroupDAO;
import android.com.br.dummyreminder.database.ItemDAO;
import android.com.br.dummyreminder.to.Item;
import android.view.Menu;

public class ItemNewState extends ItemState {

    public ItemNewState(Activity context) {
        super(context, null);
        this._txtName.requestFocus();
    }

    @Override
    public void add() {}

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onResume() {}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    @Override
    public boolean save() {
        if (!super.validateFields()) return false;

        GroupDAO dao = new GroupDAO();

        super._itemTO = new Item();
        this.updateObjectTO();
        super._groupTO.addItem(this._itemTO);
        dao.update(super._groupTO);

        return true;
    }
}
