package com.github.dreambrother.data;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ActivityOne extends Activity {

    public static final String PREFERENCES_NAME = "com.github.dreambrother.data.preferences";
    public static final String TEXT_PARAM_NAME = "text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
    }

    public void onButtonClick(View view) {
        EditText editText = (EditText) findViewById(R.id.activity_one_text);

        SharedPreferences preferences = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(TEXT_PARAM_NAME, editText.getText().toString());
        editor.apply();

        Intent intent = new Intent(this, ActivityTwo.class);
        startActivity(intent);
    }
}
