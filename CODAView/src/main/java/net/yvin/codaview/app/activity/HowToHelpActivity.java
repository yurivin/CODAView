package net.yvin.codaview.app.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import net.yvin.codaview.app.R;
import net.yvin.codaview.app.activity.base.MenuAbstractActivity;

/**
 * Created by Yuriy.Vinogradov on 01.08.2014.
 */
public class HowToHelpActivity extends MenuAbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_howtohelp);
    }

    public void rateOnMarket(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("market://details?id=net.yvin.codaview.app"));
        try {
            startActivity(intent);
        } catch (Exception e) {
            String appPackageName = "net.yvin.codaview.app";
            String url = "https://play.google.com/store/apps/details?id=" + appPackageName;
            intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            try {
                startActivity(intent);
            } catch (Exception ee) {
                Toast.makeText(this, "Some problem happens", Toast.LENGTH_LONG).show();
            }
        }
    }
}
