package net.yvin.codaview.app.activity.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import net.yvin.codaview.app.R;
import net.yvin.codaview.app.activity.MenuActivity;

/**
 * Created by Yuriy on 24.04.2014.
 */
public class MenuAbstractActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = null;
         switch(item.getItemId()){
             case R.id.action_menu :
                 intent = new Intent(this, MenuActivity.class);
                 this.startActivity(intent);
                 this.finish();
                 break;
         }

        return super.onOptionsItemSelected(item);
    }

}
