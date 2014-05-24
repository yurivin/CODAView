package net.yvin.codaview.app.activity;

import android.os.Bundle;
import android.widget.TextView;
import net.yvin.codaview.app.R;
import net.yvin.codaview.app.activity.base.MenuAbstractActivity;
import net.yvin.codaview.app.utils.AssetsTxtReader;
import net.yvin.codaview.app.utils.Constants;

/**
 * Created by Юрий on 24.05.2014.
 */
public class TextActivity extends MenuAbstractActivity {

    TextView titleTv, mainTv;
    AssetsTxtReader txtReader = new AssetsTxtReader(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        titleTv = (TextView) findViewById(R.id.titleTextTv);
        titleTv.setText(getIntent().getStringExtra(Constants.TITLE));
        mainTv = (TextView) findViewById(R.id.mainTextTv);
        mainTv.setText(txtReader.getAssetsTxt((getIntent().getStringExtra(Constants.FIELD) + Constants.SLASH + getIntent().getStringExtra(Constants.TEXT) + Constants.POINTTXT)));
    }
}
