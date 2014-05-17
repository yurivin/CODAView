package net.yvin.codaview.app.activity;

import android.os.Bundle;
import net.yvin.codaview.app.R;
import net.yvin.codaview.app.activity.base.MenuAbstractActivity;

/**
 * Created by Юрий on 17.05.2014.
 */
public class StepActivity extends MenuAbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
    }
}
