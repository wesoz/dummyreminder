package android.com.br.dummyreminder.activitystates;

import android.app.Activity;
import android.com.br.dummyreminder.ItemDetail;
import android.com.br.dummyreminder.R;
import android.com.br.dummyreminder.to.Group;
import android.content.Intent;
import android.widget.TextView;

public abstract class GroupState extends ActivityState {

    Group _groupTO;
    TextView _txtName;

    public GroupState(Activity context) {
        super(context);
        this._txtName = super._context.findViewById(R.id.txtName_Group);
    }

    public void add() {
        Intent intent = new Intent(super._context, ItemDetail.class);
        intent.putExtra("groupID", this._groupTO.getID());
        super._context.startActivity(intent);
    }

    boolean validateFields() {

        if (this._txtName.getText().toString().trim().isEmpty()) {
            this._txtName.setError("Required");
            return false;
        }

        return true;
    }
}
