package me.prantik.calculator;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesHelper {
    private final Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public SharedPreferencesHelper(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("calculator", Context.MODE_PRIVATE);
    }

    public void saveData(
            String number, String status, String history, String result,
            double firstNum,
            boolean operator, boolean equalClicked
    ) {
        editor = sharedPreferences.edit();

        editor.putString("number", number);
        editor.putString("status", status);
        editor.putString("history", history);
        editor.putString("result", result);

        editor.putFloat("firstNum", (float) firstNum);
        editor.putBoolean("operator", operator);
        editor.putBoolean("equalClicked", equalClicked);

        editor.apply();
    }
}
