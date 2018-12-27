package android.com.br.dummyreminder;

import android.com.br.dummyreminder.adapter.GroupAdapter;
import android.com.br.dummyreminder.database.DBHelper;
import android.com.br.dummyreminder.database.GroupDAO;
import android.com.br.dummyreminder.to.ObjectTO;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // DBHelper db = new DBHelper(this);

        Toolbar mainToolBar = findViewById(R.id.mainToolBar);

        setSupportActionBar(mainToolBar);

        this.setupListView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.tb_button_new:
                Toast.makeText(this, "New Group", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, GroupDetail.class));

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setupListView() {
        ListView lstGroups = findViewById(R.id.lstGroups);

        GroupDAO dao = new GroupDAO(getBaseContext());

        List<ObjectTO> groups = dao.select();
        dao.close();

        GroupAdapter adapter = new GroupAdapter(this, groups);

        /*
        List<String> items = new ArrayList<>();
        items.add("Med");
        items.add("Work");
        items.add("Others");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
*/

        lstGroups.setAdapter(adapter);

        lstGroups.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                String text = ((TextView)view).getText().toString();
                Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
