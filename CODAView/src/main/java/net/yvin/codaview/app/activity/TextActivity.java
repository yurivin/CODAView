package net.yvin.codaview.app.activity;

import android.os.Bundle;
import android.widget.TextView;
import net.yvin.codaview.app.R;
import net.yvin.codaview.app.activity.base.MenuAbstractActivity;
import net.yvin.codaview.app.context.LanguageContext;
import net.yvin.codaview.app.service.PathService;
import net.yvin.codaview.app.utils.AssetsReader;
import net.yvin.codaview.app.utils.Constants;

/**
 * Created by Юрий on 24.05.2014.
 */
public class TextActivity extends MenuAbstractActivity {

    TextView titleTv, mainTv;
    AssetsReader txtReader = new AssetsReader(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        titleTv = (TextView) findViewById(R.id.titleTextTv);
        titleTv.setText(getIntent().getStringExtra(Constants.TITLE));
        mainTv = (TextView) findViewById(R.id.mainTextTv);
        mainTv.setText(txtReader.getAssets(PathService.text(getIntent().getStringExtra(Constants.FIELD), getIntent().getStringExtra(Constants.TEXT))));
    }
}
