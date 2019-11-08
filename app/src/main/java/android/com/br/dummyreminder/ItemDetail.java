package android.com.br.dummyreminder;

import android.com.br.dummyreminder.database.ItemDAO;
import android.com.br.dummyreminder.to.Item;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class ItemDetail extends AppCompatActivity {

    private long _groupID;
    private Item _item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        Intent intent = getIntent();
        this._groupID = (long)intent.getSerializableExtra("groupID");
        this._item = (Item) intent.getSerializableExtra("item");

        Switch swActive = findViewById(R.id.swActive_Item);

        swActive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    buttonView.setText(R.string.ItemActiveSwitch);
                } else {
                    buttonView.setText(R.string.ItemInactiveSwitch);
                }
            }
        });

        if (this._item != null) {
            TextView txtName = findViewById(R.id.txtName_Item);
            TimePicker timePicker = findViewById(R.id.timePIcker_Item);

            txtName.setText(this._item.getName());
            timePicker.setHour(this._item.getHour());
            timePicker.setMinute(this._item.getMinute());
            swActive.setChecked(this._item.isActive());
            this.setCheckboxesFromBitwise(this._item.getWeekdays());
        }

        Toolbar itemDetailToolbar = findViewById(R.id.item_detail_toolbar);
        setSupportActionBar(itemDetailToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.item_detail_menu, menu);
        return true;
    }

    private void setCheckboxesFromBitwise(int bitwise) {
        CheckBox chkSunday = findViewById(R.id.sunday_Item);
        CheckBox chkMonday = findViewById(R.id.monday_Item);
        CheckBox chkTuesday = findViewById(R.id.tuesday_Item);
        CheckBox chkWednesday = findViewById(R.id.wednesday_Item);
        CheckBox chkThursday = findViewById(R.id.thursday_Item);
        CheckBox chkFriday = findViewById(R.id.friday_Item);
        CheckBox chkSaturday = findViewById(R.id.saturday_Item);

        chkSunday.setChecked((bitwise & 1) > 0);
        chkMonday.setChecked((bitwise & 2) > 0);
        chkTuesday.setChecked((bitwise & 4) > 0);
        chkWednesday.setChecked((bitwise & 8) > 0);
        chkThursday.setChecked((bitwise & 16) > 0);
        chkFriday.setChecked((bitwise & 32) > 0);
        chkSaturday.setChecked((bitwise & 64) > 0);
    }

    private int getBitwiseValue() {
        CheckBox chkSunday = findViewById(R.id.sunday_Item);
        CheckBox chkMonday = findViewById(R.id.monday_Item);
        CheckBox chkTuesday = findViewById(R.id.tuesday_Item);
        CheckBox chkWednesday = findViewById(R.id.wednesday_Item);
        CheckBox chkThursday = findViewById(R.id.thursday_Item);
        CheckBox chkFriday = findViewById(R.id.friday_Item);
        CheckBox chkSaturday = findViewById(R.id.saturday_Item);

        int bitwise = 0;

        if (chkSunday.isChecked()) {
            bitwise = 1;
        }

        if (chkMonday.isChecked()) {
            bitwise |= 2;
        }

        if (chkTuesday.isChecked()) {
            bitwise |= 4;
        }

        if (chkWednesday.isChecked()) {
            bitwise |= 8;
        }

        if (chkThursday.isChecked()) {
            bitwise |= 16;
        }

        if (chkFriday.isChecked()) {
            bitwise |= 32;
        }

        if (chkSaturday.isChecked()) {
            bitwise |= 64;
        }

        return bitwise;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.tb_button_save:
                Toast.makeText(this, "Save", Toast.LENGTH_SHORT).show();

                TextView txtName = findViewById(R.id.txtName_Item);
                Switch swActive = findViewById(R.id.swActive_Item);
                TimePicker timePicker = findViewById(R.id.timePIcker_Item);

                ItemDAO dao = new ItemDAO(getBaseContext());

                Item itemTO = new Item();
                itemTO.setGroupID(this._groupID);
                itemTO.setName(txtName.getText().toString());
                itemTO.setHour(timePicker.getHour());
                itemTO.setMinute(timePicker.getMinute());
                itemTO.setActive(swActive.isChecked());
                itemTO.setTriggered(false);
                itemTO.setWeekdays(this.getBitwiseValue());
                itemTO.setDate(null);

                if (this._item == null) {
                    dao.insert(itemTO);
                } else {
                    itemTO.setID(this._item.getID());
                    dao.update(itemTO);
                }

                dao.close();

                this.finish();

                return true;

            case R.id.tb_button_cancel:
                Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show();
                this.finish();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
