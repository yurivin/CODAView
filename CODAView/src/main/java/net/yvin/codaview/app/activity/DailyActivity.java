package net.yvin.codaview.app.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.format.Time;
import android.util.Log;
import net.yvin.codaview.app.activity.base.MenuAbstractActivity;
import android.os.Bundle;
import android.widget.TextView;
import net.yvin.codaview.app.R;
import net.yvin.codaview.app.context.LanguageContext;
import net.yvin.codaview.app.repository.DiaryTitlesRepository;
import net.yvin.codaview.app.utils.AssetsReader;
import net.yvin.codaview.app.utils.Constants;
import net.yvin.codaview.app.utils.DateUtils;
import net.yvin.codaview.app.service.PathService;

import java.util.Locale;


public class DailyActivity extends MenuAbstractActivity {

    TextView dayTitleTv, dateTv, quoteTv, mainTv, sumTv;
    DiaryTitlesRepository diaryTitlesRepo = new DiaryTitlesRepository();
    AssetsReader txtReader = new AssetsReader(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);
        checkLanguage(this);
        String dailyId = DateUtils.nowToDailyId();
        if (getIntent() != null) {
            Intent intent = getIntent();
            if (intent.getExtras() != null) {
                if (intent.getExtras().containsKey(Constants.DAILYID)) {
                    dailyId = intent.getStringExtra(Constants.DAILYID);
                    Log.d(Constants.DAILYID, dailyId);
                }
            }
        }

        Time now = new Time();
        now.setToNow();

        dayTitleTv = (TextView) findViewById(R.id.dayTitleTv);
        dayTitleTv.setText(diaryTitlesRepo.find(dailyId, PathService.diaryTitle(), this));
        dateTv = (TextView) findViewById(R.id.dateTv);
        dateTv.setText(now.monthDay + "." + String.valueOf(now.month + 1) + "." + now.year);
        quoteTv = (TextView) findViewById(R.id.quoteTv);
        quoteTv.setText(txtReader.getAssets(PathService.dailyQuote(dailyId)));
        mainTv = (TextView) findViewById(R.id.contentTv);
        mainTv.setText(txtReader.getAssets(PathService.dailyMain(dailyId)));
        sumTv = (TextView) findViewById(R.id.sumTv);
        sumTv.setText(txtReader.getAssets(PathService.dailySum(dailyId)));

    }

    private void checkLanguage(final Context context) {
        if (!LanguageContext.isSupportedlanguage(LanguageContext.getDefaultLanguage()))
            checkPreferencesLanguage(context);
    }

    private void checkPreferencesLanguage(final Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        if ("".equals(sp.getString("language", "")))
                showSetLanguageDialog(context);
        LanguageContext.setLanguage(this);
    }

    private void showSetLanguageDialog(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Alert!")
                .setMessage("Set your preferred language Please!")
                .setIcon(R.drawable.codasun)
                .setCancelable(false)
                .setNegativeButton("ОК",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                startActivity(new Intent(context, SettingsActivity.class));
                                dialog.cancel();
                                ((Activity) context).finish();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
