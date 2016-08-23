package org.meruvian.preference;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import java.util.Arrays;
import java.util.List;


/**
 * Created by miftakhul on 23/08/16.
 */
public class SettingActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingFragment()).commit();
    }

    public static class SettingFragment extends PreferenceFragment {
        Preference prefAbout;
        ListPreference prefLocale;
        SharedPreferences preferences;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.setting);

            preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

            prefAbout = (Preference) findPreference("pref_about");
            prefLocale = (ListPreference) findPreference("pref_locale");

            prefLocale.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object o) {

                    List<String> localeValue = Arrays.asList(getResources().getStringArray(R.array.locale_value));

                    List<String> locale = Arrays.asList(getResources().getStringArray(R.array.locale));
                    prefLocale.setSummary(locale.get(localeValue.indexOf(o)));
                    return true;
                }
            });

            prefAbout.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Sample Preference");
                    builder.setMessage("Sample aplication using preference");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    builder.show();
                    return false;
                }
            });

            String localeString = preferences.getString("pref_locale", "");

            List<String> localeValue = Arrays.asList(getResources().getStringArray(R.array.locale_value));
            List<String> locale = Arrays.asList(getResources().getStringArray(R.array.locale));
            prefLocale.setSummary(locale.get(localeValue.indexOf(localeString)));
        }
    }

}
