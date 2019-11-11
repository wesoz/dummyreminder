package android.com.br.dummyreminder.activitystates.item;

import android.app.Activity;
import android.com.br.dummyreminder.R;
import android.com.br.dummyreminder.activitystates.ActivityState;
import android.com.br.dummyreminder.to.Item;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

public abstract class ItemState extends ActivityState {

    protected Item _itemTO;
    protected TextView _txtName;
    protected CheckBox _chkSunday;
    protected CheckBox _chkMonday;
    protected CheckBox _chkTuesday;
    protected CheckBox _chkWednesday;
    protected CheckBox _chkThursday;
    protected CheckBox _chkFriday;
    protected CheckBox _chkSaturday;
    protected Switch _swActive;
    protected TimePicker _timePicker;

    public ItemState(Activity context, Item itemTO) {
        super(context);
        this._txtName = super._context.findViewById(R.id.txtName_Item);
        this._chkSunday = super._context.findViewById(R.id.sunday_Item);
        this._chkMonday = super._context.findViewById(R.id.monday_Item);
        this._chkTuesday = super._context.findViewById(R.id.tuesday_Item);
        this._chkWednesday = super._context.findViewById(R.id.wednesday_Item);
        this._chkThursday = super._context.findViewById(R.id.thursday_Item);
        this._chkFriday = super._context.findViewById(R.id.friday_Item);
        this._chkSaturday = super._context.findViewById(R.id.saturday_Item);
        this._swActive = super._context.findViewById(R.id.swActive_Item);
        this._timePicker = super._context.findViewById(R.id.timePIcker_Item);

        this._chkMonday.setChecked(true);
        this._chkTuesday.setChecked(true);
        this._chkWednesday.setChecked(true);
        this._chkThursday.setChecked(true);
        this._chkFriday.setChecked(true);

        this._swActive.setOnCheckedChangeListener(
            (buttonView, isChecked) ->
            {
                if (isChecked) {
                    buttonView.setText(R.string.ItemActiveSwitch);
                } else {
                    buttonView.setText(R.string.ItemInactiveSwitch);
                }
            }
        );

        this._itemTO = itemTO;
    }

    protected void updateObjectTO() {
        this._itemTO.setName(this._txtName.getText().toString());
        this._itemTO.setHour(this._timePicker.getHour());
        this._itemTO.setMinute(this._timePicker.getMinute());
        this._itemTO.setActive(this._swActive.isChecked());
        this._itemTO.setTriggered(false);
        this._itemTO.setWeekdays(this.getBitwiseValue());
        this._itemTO.setDate(null);
    }

    protected void setCheckboxesFromBitwise(int bitwise) {
        this._chkSunday.setChecked((bitwise & 1) > 0);
        this._chkMonday.setChecked((bitwise & 2) > 0);
        this._chkTuesday.setChecked((bitwise & 4) > 0);
        this._chkWednesday.setChecked((bitwise & 8) > 0);
        this._chkThursday.setChecked((bitwise & 16) > 0);
        this._chkFriday.setChecked((bitwise & 32) > 0);
        this._chkSaturday.setChecked((bitwise & 64) > 0);
    }

    private int getBitwiseValue() {
        int bitwise = 0;

        if (this._chkSunday.isChecked()) bitwise = 1;
        if (this._chkMonday.isChecked()) bitwise |= 2;
        if (this._chkTuesday.isChecked()) bitwise |= 4;
        if (this._chkWednesday.isChecked()) bitwise |= 8;
        if (this._chkThursday.isChecked()) bitwise |= 16;
        if (this._chkFriday.isChecked()) bitwise |= 32;
        if (this._chkSaturday.isChecked()) bitwise |= 64;

        return bitwise;
    }

    boolean validateFields() {

        if (this._txtName.getText().toString().trim().isEmpty()) {
            this._txtName.setError(super._context.getString(R.string.Required));
            this._txtName.requestFocus();
            return false;
        }

        if (this._swActive.isChecked()) {
            if (this.getBitwiseValue() == 0) {
                this._swActive.setError("AAA");
                return false;
            }
        }

        return true;
    }
}
