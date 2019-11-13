package android.com.br.dummyreminder.adapter;

import android.com.br.dummyreminder.R;
import android.com.br.dummyreminder.database.ItemDAO;
import android.com.br.dummyreminder.to.Item;
import android.com.br.dummyreminder.to.IObjectTO;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

public class ItemAdapter extends BaseAdapter {

    private final List<Item> items;
    private final Context context;

    public ItemAdapter(Context context, List<Item> items) {
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
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Item item = this.items.get(position);

        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.item_list_item, null);

        String itemTime = item.getFormattedTime();

        ((TextView) view.findViewById(R.id.item_name)).setText(item.getName());
        ((TextView) view.findViewById(R.id.item_detail)).setText(itemTime);
        Switch swActive = view.findViewById(R.id.item_swActive);
        swActive.setChecked(item.isActive());
        swActive.setOnCheckedChangeListener((buttonView, isChecked) ->
            {
                item.setActive(isChecked);
                ItemDAO dao = new ItemDAO();
                dao.update(item);
            }
        );

        return view;
    }
}
