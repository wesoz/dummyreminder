package android.com.br.dummyreminder.activitystates.group;

import android.app.Activity;
import android.com.br.dummyreminder.ItemDetail;
import android.com.br.dummyreminder.R;
import android.com.br.dummyreminder.adapter.ItemAdapter;
import android.com.br.dummyreminder.database.GroupDAO;
import android.com.br.dummyreminder.to.Group;
import android.com.br.dummyreminder.to.Item;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GroupViewState extends GroupState {


    public GroupViewState(Activity context, Group groupTO) {
        super(context, groupTO);
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
        this.updateObjectTO();
        this.setupListView();
    }

    private void updateObjectTO() {
        GroupDAO dao = new GroupDAO();
        super._groupTO = dao.select(super._groupTO.getID());
    }

    private void setupListView() {
        ListView lstGroupItems = super._context.findViewById(R.id.lstGroupItems);

        GroupDAO dao = new GroupDAO();
        final List<Item> items = dao.getItems(super._groupTO.getID());
        Collections.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return (o1.getHour() + o1.getMinute()) - (o2.getHour() + o2.getMinute());
            }
        });
        ItemAdapter adapter = new ItemAdapter(super._context, items);

        lstGroupItems.setAdapter(adapter);

        lstGroupItems.setOnItemClickListener((parent, view, position, id) ->
            {
                Item itemTO = (Item)items.get(position);

                Intent intent = new Intent(GroupViewState.super._context, ItemDetail.class);
                intent.putExtra("groupID", GroupViewState.super._groupTO.getID());
                intent.putExtra("item", itemTO);
                GroupViewState.super._context.startActivity(intent);
            }
        );
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.findItem(R.id.tb_button_save).setVisible(false);
        return false;
    }

    @Override
    public boolean save() {
        if (!super.validateFields())
            return false;

        GroupDAO dao = new GroupDAO();
        super._groupTO.setName(super._txtName.getText().toString());
        super._groupTO.setActive(true);
        dao.update(super._groupTO);
        return true;
    }
}
