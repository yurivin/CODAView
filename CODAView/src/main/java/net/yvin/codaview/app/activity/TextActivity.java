package net.yvin.codaview.app.activity;

import android.os.Bundle;
import android.widget.TextView;
import net.yvin.codaview.app.R;
import net.yvin.codaview.app.activity.base.MenuAbstractActivity;
import net.yvin.codaview.app.utils.Constants;

import static net.yvin.codaview.app.utils.Constants.PROMISES;
import static net.yvin.codaview.app.utils.Constants.STEPS;
import static net.yvin.codaview.app.utils.Constants.TRADITIONS;

/**
 * Created by Юрий on 17.05.2014.
 */
public class TextActivity extends MenuAbstractActivity {

    TextView titleTv, firstTv, secondTv, thirdTv, fourthTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        titleTv = (TextView) findViewById(R.id.textTitleTv);
        firstTv = (TextView) findViewById(R.id.firstTv);
        secondTv = (TextView) findViewById(R.id.secondTv);
        thirdTv = (TextView) findViewById(R.id.thirdTv);
        fourthTv = (TextView) findViewById(R.id.forthTv);
        setTitle();
    }

    private void setTitle() {
        switch(getIntent().getStringExtra("type")) {
            case STEPS :
                titleTv.setText(getString(R.string.step) + Constants.SPACE + getIntent().getIntExtra("number", 20));
                break;
            case TRADITIONS :
                break;
            case PROMISES :
                break;
        }
    }
}
