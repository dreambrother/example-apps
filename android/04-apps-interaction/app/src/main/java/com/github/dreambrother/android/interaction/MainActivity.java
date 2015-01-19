package com.github.dreambrother.android.interaction;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onMapButtonClick(View view) {
        Uri geo = Uri.parse("geo:0,0?q=Saint+Petersburg,+Russia");
        Intent intent = new Intent(Intent.ACTION_VIEW, geo);
        startActivity(intent);
    }

    public void onSmsButtonClick(View view) {
        Uri phone = Uri.parse("sms:+792112345678");
        Intent intent = new Intent(Intent.ACTION_VIEW, phone);
        startActivity(intent);
    }
}
