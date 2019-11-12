package android.com.br.dummyreminder;

import android.com.br.dummyreminder.adapter.GroupAdapter;
import android.com.br.dummyreminder.database.GroupDAO;
import android.com.br.dummyreminder.to.Group;
import android.com.br.dummyreminder.to.ObjectTO;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;

import com.mongodb.stitch.android.core.Stitch;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<ObjectTO> groups;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mainToolBar = findViewById(R.id.mainToolBar);

        setSupportActionBar(mainToolBar);
        Stitch.initializeDefaultAppClient(getResources().getString(R.string.my_app_id));
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
                startActivity(new Intent(MainActivity.this, GroupDetail.class));

                return true;
            case R.id.tb_button_test:

                try {
                    Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
                    Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                    r.play();

                } catch (Exception e) {
                    e.printStackTrace();
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setupListView() {
        ListView lstGroups = findViewById(R.id.lstGroups);

        GroupDAO dao = new GroupDAO();

        this.groups = dao.select();

        GroupAdapter adapter = new GroupAdapter(this, this.groups);

        lstGroups.setAdapter(adapter);

        lstGroups.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                Group group = (Group)groups.get(position);
                Intent intent = new Intent(MainActivity.this, GroupDetail.class);
                intent.putExtra("group", group);
                startActivity(intent);
            }
        });
    }
}
