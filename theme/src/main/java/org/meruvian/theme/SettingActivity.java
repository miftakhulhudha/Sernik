package org.meruvian.theme;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import java.util.Arrays;
import java.util.List;

/**
 * Created by miftakhul on 23/08/16.
 */
public class SettingActivity extends PreferenceActivity {

    private boolean isThemeUpdated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingFragment()).commit();
    }

    @Override
    public void onBackPressed() {
        if (isThemeUpdated){
            setResult(RESULT_OK);
            finish();
        }else {
            finish();
        }
    }

    protected void themeUpdated(){
        this.isThemeUpdated = true;
    }

    public static class SettingFragment extends PreferenceFragment{

        private SharedPreferences preferences;

        private ListPreference themesColor;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.setting);

            preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

            themesColor = (ListPreference) findPreference("pref_themesColor");
            themesColor.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object o) {
                    List<String> color = Arrays.asList(getResources().getStringArray(R.array.theme_color));
                    themesColor.setSummary(color.get(color.indexOf(o.toString())));
                    ((SettingActivity)getActivity()).themeUpdated();
                    return true;
                }
            });

            String prefThemesColor = preferences.getString("pref_themesColor", "");
            if (!prefThemesColor.equalsIgnoreCase("")) {
                List<String> color = Arrays.asList(getResources().getStringArray(R.array.theme_color));
                themesColor.setSummary(color.get(color.indexOf(prefThemesColor)));
            }
        }
    }


}
