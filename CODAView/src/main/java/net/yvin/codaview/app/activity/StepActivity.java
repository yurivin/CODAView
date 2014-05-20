package net.yvin.codaview.app.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import net.yvin.codaview.app.R;
import net.yvin.codaview.app.activity.base.MenuAbstractActivity;
import net.yvin.codaview.app.repository.PropertiesRepository;
import net.yvin.codaview.app.repository.TextRepository;
import net.yvin.codaview.app.utils.Constants;

import static net.yvin.codaview.app.utils.Constants.PROMISES;
import static net.yvin.codaview.app.utils.Constants.STEPS;
import static net.yvin.codaview.app.utils.Constants.TRADITIONS;

/**
 * Created by Юрий on 17.05.2014.
 */
public class StepActivity extends MenuAbstractActivity {

    TextView titleTv, stepTv, extensionTitleTv, extensionTv, fourthTv;
    Button extensionBtn;
    PropertiesRepository textRepo = new TextRepository();
    boolean extension = false;
    String[] values = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);

        titleTv = (TextView) findViewById(R.id.stepTitleTv);
        stepTv = (TextView) findViewById(R.id.stepTv);
        extensionTitleTv = (TextView) findViewById(R.id.extensionTitleTv);
        extensionTv = (TextView) findViewById(R.id.extensionTv);
        extensionBtn = (Button) findViewById(R.id.btnExtension);
        setData();

    }

    private void setData() {
        switch(getIntent().getStringExtra(Constants.TWELVE)) {
            case STEPS :
                values = getResources().getStringArray(R.array.steps);
                titleTv.setText(getString(R.string.step) + Constants.SPACE + getIntent().getIntExtra(Constants.NUMBER, 20) + " " + values[getIntent().getIntExtra(Constants.NUMBER, 20) - 1]);
                stepTv.setText(textRepo.find(Constants.STEPS + Constants.SLASH + getIntent().getIntExtra(Constants.NUMBER, 20) + Constants.MAIN, this));

                break;
            case TRADITIONS :
                break;
            case PROMISES :
                break;
        }
    }

    public void clickBtnExtension(View v) {
        if(!extension){
            extensionTitleTv.setText("Выдержка из буклета Шаг " +  getIntent().getIntExtra(Constants.NUMBER, 20) + " Анонимных Созависимых");
            extensionTitleTv.setTextSize(14f);
            extensionTv.setText(textRepo.find(Constants.STEPS + Constants.SLASH + getIntent().getIntExtra(Constants.NUMBER, 20) + Constants.EXTENSION, this));
            extensionTitleTv.setVisibility(View.VISIBLE);
            extensionTv.setVisibility(View.VISIBLE);
            extensionBtn.setVisibility(View.GONE);
        }
    }
}
