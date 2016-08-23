package org.meruvian.theme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Arrays;
import java.util.List;

/**
 * Created by miftakhul on 23/08/16.
 */
public class MainActivity extends AppCompatActivity {
    private static final int setting_code = 100;

    private SharedPreferences preferences;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String currentTheme = preferences.getString("pref_themesColor", "");
        if (!currentTheme.equalsIgnoreCase("")){
            List<String> themeList = Arrays.asList(getResources().getStringArray(R.array.theme_color));
            if (currentTheme.equalsIgnoreCase(themeList.get(0))){ // red
                setTheme(R.style.AppTheme_Red);
            }else if (currentTheme.equalsIgnoreCase(themeList.get(1))){ //blue
                setTheme(R.style.AppTheme_Blue);
            }else if (currentTheme.equalsIgnoreCase(themeList.get(2))){ //yellow
                setTheme(R.style.AppTheme_Yellow);
            }
        }else {
            setTheme(R.style.AppTheme_Blue);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.menu_setting){
            startActivityForResult(new Intent(this, SettingActivity.class), setting_code);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == setting_code && resultCode == RESULT_OK){
            recreate();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
