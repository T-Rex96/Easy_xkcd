package de.tap.easy_xkcd.Activities;

import android.app.ActivityManager;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;

import com.tap.xkcd_reader.R;

import de.tap.easy_xkcd.database.DatabaseManager;
import de.tap.easy_xkcd.utils.PrefHelper;
import de.tap.easy_xkcd.utils.ThemePrefs;

public abstract class BaseActivity extends AppCompatActivity {
    protected PrefHelper prefHelper;
    protected ThemePrefs themePrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        prefHelper = new PrefHelper(this);
        themePrefs = new ThemePrefs(this);

        setTheme(themePrefs.getNewTheme());
        super.onCreate(savedInstanceState);
    }

    protected void setupToolbar(Toolbar toolbar) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Bitmap ic = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_easy_xkcd_recents);
            int color = themePrefs.getPrimaryColor(false);
            ActivityManager.TaskDescription description = new ActivityManager.TaskDescription("Easy xkcd", ic, color);
            setTaskDescription(description);

            if (!(this instanceof MainActivity))
                getWindow().setStatusBarColor(themePrefs.getPrimaryDarkColor());
            if (prefHelper.colorNavbar())
                getWindow().setNavigationBarColor(themePrefs.getPrimaryColor(false));
        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setBackgroundColor(themePrefs.getPrimaryColor(false));
    }

}
