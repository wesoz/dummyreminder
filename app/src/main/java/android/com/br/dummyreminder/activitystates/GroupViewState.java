package android.com.br.dummyreminder.activitystates;

import android.app.Activity;
import android.com.br.dummyreminder.ItemDetail;
import android.com.br.dummyreminder.R;
import android.com.br.dummyreminder.adapter.ItemAdapter;
import android.com.br.dummyreminder.database.GroupDAO;
import android.com.br.dummyreminder.to.Group;
import android.com.br.dummyreminder.to.Item;
import android.com.br.dummyreminder.to.ObjectTO;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class GroupViewState extends ActivityState {

    Group _groupTO;
    public GroupViewState(Activity context, Group groupTO) {
        super(context);
        this._groupTO = groupTO;
    }

    @Override
    public void add() {
        Intent intent = new Intent(super._context, ItemDetail.class);
        intent.putExtra("groupID", this._groupTO.getID());
        super._context.startActivity(intent);
    }

    @Override
    public void onCreate() {
        Toolbar mainToolBar = super._context.findViewById(R.id.group_detail_toolbar);
        mainToolBar.setTitle("Group: " + this._groupTO.getName());
        super._context.findViewById(R.id.txtName_Group).setVisibility(View.GONE);
    }

    @Override
    public void onResume() {
        this.setupListView();
    }
    private void setupListView() {
        ListView lstGroupItems = super._context.findViewById(R.id.lstGroupItems);

        GroupDAO dao = new GroupDAO(super._context.getBaseContext());
        final List<ObjectTO> items = dao.getItems(this._groupTO.getID());

        ItemAdapter adapter = new ItemAdapter(super._context, items);

        lstGroupItems.setAdapter(adapter);

        lstGroupItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                Item itemTO = (Item)items.get(position);

                Intent intent = new Intent(GroupViewState.super._context, ItemDetail.class);
                intent.putExtra("groupID", GroupViewState.this._groupTO.getID());
                intent.putExtra("item", itemTO);
                GroupViewState.super._context.startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.findItem(R.id.tb_button_save).setVisible(false);
        return false;
    }

    @Override
    public void save() {
        Toast.makeText(super._context, "Save", Toast.LENGTH_SHORT).show();
        TextView txtName = super._context.findViewById(R.id.txtName_Group);

        GroupDAO dao = new GroupDAO(super._context.getBaseContext());
        this._groupTO.setName(txtName.getText().toString());
        this._groupTO.setActive(true);
        dao.update(this._groupTO);
    }
}
