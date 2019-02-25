package android.com.br.dummyreminder.adapter;

import android.com.br.dummyreminder.R;
import android.com.br.dummyreminder.to.Group;
import android.com.br.dummyreminder.to.Item;
import android.com.br.dummyreminder.to.ObjectTO;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ItemAdapter extends BaseAdapter {

    private final List<ObjectTO> items;
    private final Context context;

    public ItemAdapter(Context context, List<ObjectTO> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return this.items.size();
    }

    @Override
    public Object getItem(int position) {
        return this.items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return ((Item)this.items.get(position)).getID();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Item item = (Item) this.items.get(position);

        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.list_item, null);

        String itemTime =  item.getHour() + ":" + item.getMinute();

        ((TextView) view.findViewById(R.id.item_name)).setText(item.getName());
        ((TextView) view.findViewById(R.id.item_detail)).setText(item.getDescription() + " - " + itemTime);

        return view;
    }
}
