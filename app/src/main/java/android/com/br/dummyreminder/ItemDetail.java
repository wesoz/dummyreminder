package android.com.br.dummyreminder;

import android.com.br.dummyreminder.activitystates.ActivityState;
import android.com.br.dummyreminder.activitystates.item.ItemNewState;
import android.com.br.dummyreminder.activitystates.item.ItemViewState;
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

    private Item _item;
    private ActivityState _itemState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        Intent intent = getIntent();
        this._item = (Item) intent.getSerializableExtra("item");

        if (this._item != null) {
            this._itemState = new ItemViewState(this, this._item);
        } else {
            this._itemState = new ItemNewState(this);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.tb_button_save:
                if (this._itemState.save())
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
