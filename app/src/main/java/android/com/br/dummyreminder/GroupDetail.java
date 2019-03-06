package android.com.br.dummyreminder;

import android.com.br.dummyreminder.adapter.GroupAdapter;
import android.com.br.dummyreminder.adapter.ItemAdapter;
import android.com.br.dummyreminder.database.GroupDAO;
import android.com.br.dummyreminder.database.ItemDAO;
import android.com.br.dummyreminder.to.Group;
import android.com.br.dummyreminder.to.Item;
import android.com.br.dummyreminder.to.ObjectTO;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class GroupDetail extends AppCompatActivity {

    Group group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_detail);

        Toolbar mainToolBar = findViewById(R.id.group_detail_toolbar);
        setSupportActionBar(mainToolBar);

        Intent intent = getIntent();
        this.group = (Group) intent.getSerializableExtra("group");

        if (this.group != null)
        {
            TextView txtName = findViewById(R.id.txtName_Group);
            TextView txtDesctiption = findViewById(R.id.txtDescription_Group);
            txtName.setText(this.group.getName());
            txtDesctiption.setText(this.group.getDescription());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (this.group != null) {
            this.setupListView();
        }
    }

    private void setupListView() {
        ListView lstGroupItems = findViewById(R.id.lstGroupItems);

        GroupDAO dao = new GroupDAO(getBaseContext());
        final List<ObjectTO> items = dao.getItems(this.group.getID());

        ItemAdapter adapter = new ItemAdapter(this, items);

        lstGroupItems.setAdapter(adapter);

        lstGroupItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                Item itemTO = (Item)items.get(position);

                Intent intent = new Intent(GroupDetail.this, ItemDetail.class);
                intent.putExtra("groupID", GroupDetail.this.group.getID());
                intent.putExtra("item", itemTO);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.group_detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.tb_button_add:
                Intent intent = new Intent(GroupDetail.this, ItemDetail.class);
                intent.putExtra("groupID", this.group.getID());
                startActivity(intent);

                return true;

            case R.id.tb_button_save:
                Toast.makeText(this, "Save", Toast.LENGTH_SHORT).show();
                TextView txtName = findViewById(R.id.txtName_Group);
                TextView txtDesctiption = findViewById(R.id.txtDescription_Group);

                GroupDAO dao = new GroupDAO(getBaseContext());

                if (this.group == null) {
                    Group group = new Group(0, txtName.getText().toString(), txtDesctiption.getText().toString(), true);
                    dao.insert(group);
                } else {
                    this.group.setName(txtName.getText().toString());
                    this.group.setDescription(txtDesctiption.getText().toString());
                    this.group.setActive(true);
                    dao.update(this.group);
                }
                dao.close();
                this.finish();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
