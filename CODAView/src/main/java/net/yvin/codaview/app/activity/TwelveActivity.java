package net.yvin.codaview.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import net.yvin.codaview.app.R;
import net.yvin.codaview.app.activity.base.MenuListAbstractActivity;

import static net.yvin.codaview.app.utils.Constants.*;

/**
 * Created by Юрий on 12.05.2014.
 */
public class TwelveActivity extends MenuListAbstractActivity {

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        String[] values = null;
        values = getTwelveArray(values);

        for(int i = 1 ; i <= values.length; i++) {
            values[i-1] = String.valueOf(i) + ". " + values[i-1];
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.text_list_item, values);
        setListAdapter(adapter);
    }

    private String[] getTwelveArray(String[] values) {
        switch(getIntent().getStringExtra("twelve")) {
            case STEPS :
                values = getResources().getStringArray(R.array.steps);
                break;
            case TRADITIONS :
                values = getResources().getStringArray(R.array.traditions);
                break;
            case PROMISES :
                values = getResources().getStringArray(R.array.promises);
                break;
        }
        return values;
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String) getListAdapter().getItem(position);
        Toast.makeText(this, item + " выбран", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, TextActivity.class);
        intent.putExtra("type", getIntent().getStringExtra("twelve"));
        intent.putExtra("number", position + 1);
        startActivity(intent);
    }
}
