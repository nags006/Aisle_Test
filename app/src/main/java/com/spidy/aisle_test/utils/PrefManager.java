package com.spidy.aisle_test.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public abstract class PrefManager
{
    private static final String PREF_LOG_KEY = "pref_log_key";
    private static final String PREF_TOK_KEY = "pref_tok_key";
    private static SharedPreferences prefs;
    private static SharedPreferences reviewsPrefs;

    public static void clearAll() {
        SharedPreferences.Editor editor = reviewsPrefs.edit();
        editor.clear();
        editor.apply();
    }

    public static void init(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        reviewsPrefs = context.getSharedPreferences("review-lessons_prefs", Context.MODE_PRIVATE);
    }

    public static void setPrefLog(String tranNotify) { prefs.edit().putString(PREF_LOG_KEY, tranNotify).apply(); }

    public static String getPrefLog() {
        return prefs.getString(PREF_LOG_KEY, "false");
    }

    public static void setPrefTokKey(String tranNotify) { prefs.edit().putString(PREF_TOK_KEY, tranNotify).apply(); }

    public static String getPrefTokKey() {
        return prefs.getString(PREF_TOK_KEY, "");
    }


}
