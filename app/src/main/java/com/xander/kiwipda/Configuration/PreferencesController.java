package com.xander.kiwipda.Configuration;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class PreferencesController {
    private static final String MY_PREFS_NAME = "KiwiPDAPreferences";

    public static void SavePreferents(Context context, Preference preferences) {
        SharedPreferences.Editor editor = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString("servername",preferences.ServerName);
        editor.putString("database",  preferences.Database);
        editor.putString ("user",  preferences.User);
        editor.putString ("password",  preferences.Password);
        editor.apply();
    }
    public static Preference LoadPreferents(Context context) {
        Preference preference = new Preference();
        SharedPreferences prefs = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        if (prefs!=null) {
            preference.ServerName = prefs.getString("servername", "");
            preference.Database = prefs.getString("database", "");
            preference.User = prefs.getString("user", "");
            preference.Password = prefs.getString("password", "");
        }else {
            preference.ServerName = "";
            preference.Database = "";
            preference.User = "";
            preference.Password = "";
        }
        return  preference;
    }
}