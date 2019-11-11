package android.com.br.dummyreminder.activitystates;

import android.app.Activity;
import android.com.br.dummyreminder.ItemDetail;
import android.com.br.dummyreminder.R;
import android.com.br.dummyreminder.adapter.ItemAdapter;
import android.com.br.dummyreminder.database.GroupDAO;
import android.com.br.dummyreminder.to.Group;
import android.com.br.dummyreminder.to.Item;
import android.com.br.dummyreminder.to.IObjectTO;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class GroupViewState extends GroupState {


    public GroupViewState(Activity context, Group groupTO) {
        super(context);
        super._groupTO = groupTO;
        this.onCreate();
    }

    @Override
    public void add() {
        super.add();
    }

    @Override
    public void onCreate() {
        super._mainToolBar.setTitle("Group: " + super._groupTO.getName());
        super._context.findViewById(R.id.txtName_Group).setVisibility(View.GONE);
    }

    @Override
    public void onResume() {
        this.setupListView();
    }

    private void setupListView() {
        ListView lstGroupItems = super._context.findViewById(R.id.lstGroupItems);

        GroupDAO dao = new GroupDAO(super._context.getBaseContext());
        final List<IObjectTO> items = dao.getItems(super._groupTO.getID());

        ItemAdapter adapter = new ItemAdapter(super._context, items);

        lstGroupItems.setAdapter(adapter);

        lstGroupItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                Item itemTO = (Item)items.get(position);

                Intent intent = new Intent(GroupViewState.super._context, ItemDetail.class);
                intent.putExtra("groupID", GroupViewState.super._groupTO.getID());
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
    public boolean save() {
        Toast.makeText(super._context, "Save", Toast.LENGTH_SHORT).show();
        if (!super.validateFields())
            return false;

        GroupDAO dao = new GroupDAO(super._context.getBaseContext());
        super._groupTO.setName(super._txtName.getText().toString());
        super._groupTO.setActive(true);
        dao.update(super._groupTO);
        return true;
    }
}
