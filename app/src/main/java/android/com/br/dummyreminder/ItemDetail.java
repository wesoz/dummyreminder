package android.com.br.dummyreminder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class ItemDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

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
                Toast.makeText(this, "Save", Toast.LENGTH_SHORT).show();

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
