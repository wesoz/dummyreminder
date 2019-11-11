package android.com.br.dummyreminder.activitystates.item;

import android.app.Activity;
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
    public void onCreate() {}

    @Override
    public void onResume() {}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    @Override
    public boolean save() {
        if (!super.validateFields()) return false;

        ItemDAO dao = new ItemDAO();

        this._itemTO = new Item();
        this.updateObjectTO();
        dao.insert(this._itemTO);

        return true;
    }
}
