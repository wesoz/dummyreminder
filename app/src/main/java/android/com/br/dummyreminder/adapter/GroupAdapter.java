package android.com.br.dummyreminder.adapter;

import android.com.br.dummyreminder.R;
import android.com.br.dummyreminder.database.GroupDAO;
import android.com.br.dummyreminder.to.Group;
import android.com.br.dummyreminder.to.ObjectTO;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class GroupAdapter extends BaseAdapter {

    private final List<ObjectTO> groups;
    private final Context context;

    public GroupAdapter(Context context, List<ObjectTO> groups) {
        this.context = context;
        this.groups = groups;
    }

    @Override
    public int getCount() {
        return this.groups.size();
    }

    @Override
    public Object getItem(int position) {
        return this.groups.get(position);
    }

    @Override
    public long getItemId(int position) {
        return ((Group)this.groups.get(position)).getID();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Group group = (Group) this.groups.get(position);

        GroupDAO dao = new GroupDAO(this.context);
        int itemCount = dao.getItemCount(group.getID());

        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.list_item, null);

        ((TextView) view.findViewById(R.id.item_name)).setText(group.getName());
        ((TextView) view.findViewById(R.id.item_detail)).setText(itemCount + " alarms");

        return view;
    }
}
