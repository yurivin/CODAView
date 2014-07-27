package net.yvin.codaview.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import net.yvin.codaview.app.R;
import net.yvin.codaview.app.activity.base.MenuListAbstractActivity;
import net.yvin.codaview.app.context.LanguageContext;
import net.yvin.codaview.app.utils.Constants;

import static net.yvin.codaview.app.utils.Constants.*;

/**
 * Created by Юрий on 12.05.2014.
 */
public class TwelveActivity extends MenuListAbstractActivity {
    ArrayAdapter<String> adapter;

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        String[] values = null;
        values = getTwelveArray(values);

        for (int i = 1; i <= values.length; i++) {
            values[i - 1] = String.valueOf(i) + ". " + values[i - 1];
        }
        setListAdapter(adapter);
    }

    private String[] getTwelveArray(String[] values) {
        switch (getIntent().getStringExtra(Constants.TWELVE)) {
            case STEPS:
                values = getResources().getStringArray(R.array.steps);
                if ("en".equals(LanguageContext.getLanguage()))
                    adapter = new ArrayAdapter<String>(this,
                            R.layout.text_list_item, values);
                else
                    adapter = new ArrayAdapter<String>(this,
                            R.layout.clickable_text_list_item, values);
                break;
            case TRADITIONS:
                values = getResources().getStringArray(R.array.traditions);
                adapter = new ArrayAdapter<String>(this,
                        R.layout.text_list_item, values);
                break;
            case PROMISES:
                values = getResources().getStringArray(R.array.promises);
                adapter = new ArrayAdapter<String>(this,
                        R.layout.text_list_item, values);
                break;
        }
        return values;
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        switch (getIntent().getStringExtra(Constants.TWELVE)) {
            case Constants.STEPS:
                if ("en".equals(LanguageContext.getLanguage())) break;
                Intent intent = new Intent(this, StepActivity.class);
                intent.putExtra(Constants.TWELVE, getIntent().getStringExtra(Constants.TWELVE));
                intent.putExtra(Constants.NUMBER, position + 1);
                startActivity(intent);
                finish();
                break;
        }
    }
}
