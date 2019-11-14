package android.com.br.dummyreminder;

import android.com.br.dummyreminder.activitystates.ActivityState;
import android.com.br.dummyreminder.activitystates.group.GroupState;
import android.com.br.dummyreminder.activitystates.group.GroupViewState;
import android.com.br.dummyreminder.activitystates.group.GroupNewState;
import android.com.br.dummyreminder.to.Group;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class GroupDetailActivity extends AppCompatActivity {

    GroupState _groupState;
    Toolbar _mainToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_detail);

        this._mainToolBar = findViewById(R.id.group_detail_toolbar);
        setSupportActionBar(this._mainToolBar);

        this.resolveState(getIntent());
    }

    private void resolveState(Intent intent) {
        Group group = (Group) intent.getSerializableExtra("group");

        if (group != null)
        {
            this._groupState = new GroupViewState(this, group);

        } else {
            this._groupState = new GroupNewState(this);
            this._mainToolBar.setTitle(R.string.NewGroup);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        this._groupState.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.group_detail_menu, menu);
        this._groupState.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.tb_button_add:
                this._groupState.add();

                return true;

            case R.id.tb_button_save:
                if (this._groupState.save())
                    this.finish();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
