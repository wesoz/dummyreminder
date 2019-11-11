package android.com.br.dummyreminder.activitystates.item;

import android.app.Activity;
import android.com.br.dummyreminder.R;
import android.com.br.dummyreminder.database.ItemDAO;
import android.com.br.dummyreminder.to.Item;
import android.view.Menu;

public class ItemViewState extends ItemState {

    public ItemViewState(Activity context, Item itemTO) {
        super(context, itemTO);
        super._txtName = super._context.findViewById(R.id.txtName_Item);
        super._timePicker = super._context.findViewById(R.id.timePIcker_Item);

        super._txtName.setText(this._itemTO.getName());
        super._timePicker.setHour(this._itemTO.getHour());
        super._timePicker.setMinute(this._itemTO.getMinute());
        super._swActive.setChecked(this._itemTO.isActive());
        super.setCheckboxesFromBitwise(this._itemTO.getWeekdays());
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
    public boolean save() {
        if (!super.validateFields()) return false;

        ItemDAO dao = new ItemDAO();

        this._itemTO = new Item();
        this.updateObjectTO();
        dao.update(this._itemTO);

        return true;
    }
}
