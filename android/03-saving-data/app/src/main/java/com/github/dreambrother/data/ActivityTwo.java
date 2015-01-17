package com.github.dreambrother.data;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;


public class ActivityTwo extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activyty_two);

        SharedPreferences sharedPreferences = getSharedPreferences(ActivityOne.PREFERENCES_NAME, Context.MODE_PRIVATE);
        String text = sharedPreferences.getString(ActivityOne.TEXT_PARAM_NAME, "");

        TextView textView = (TextView) findViewById(R.id.activity_two_text);
        textView.setText(text);
    }
}
