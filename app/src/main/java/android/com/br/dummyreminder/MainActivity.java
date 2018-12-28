package android.com.br.dummyreminder;

import android.com.br.dummyreminder.adapter.GroupAdapter;
import android.com.br.dummyreminder.database.DBHelper;
import android.com.br.dummyreminder.database.GroupDAO;
import android.com.br.dummyreminder.to.Group;
import android.com.br.dummyreminder.to.ObjectTO;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<ObjectTO> groups;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mainToolBar = findViewById(R.id.mainToolBar);

        setSupportActionBar(mainToolBar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.setupListView();
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

        this.groups = dao.select();
        dao.close();

        GroupAdapter adapter = new GroupAdapter(this, this.groups);

        lstGroups.setAdapter(adapter);

        lstGroups.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                Group group = (Group)groups.get(position);
                Toast.makeText(parent.getContext(), group.getName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, GroupDetail.class);
                intent.putExtra("group", group);
                startActivity(intent);
            }
        });
    }
}
