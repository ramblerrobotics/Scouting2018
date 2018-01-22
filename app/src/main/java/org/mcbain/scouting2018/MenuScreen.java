package org.mcbain.scouting2018;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class MenuScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_screen);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void openManual(View view) {
        Intent intent = new Intent(this, Manual.class);
        startActivity(intent);
    }
    public void openList(View view) {
        Intent intent = new Intent(this, TeamList.class);
        startActivity(intent);
    }
    public void openQR(View view) {
        Intent intent = new Intent(this, QRData.class);
        startActivity(intent);
    }
    public void openMSch(View view) {
        Intent intent = new Intent(this, MatchSched.class);
        startActivity(intent);
    }
    public void openContacts(View view) {
        Intent intent = new Intent(this, Contacts.class);
        startActivity(intent);
    }
    public void openSSch(View view) {
        Intent intent = new Intent(this, ScoutSched.class);
        startActivity(intent);
    }public void openLicense(View view) {
        Intent intent = new Intent(this, License.class);
        startActivity(intent);
    }
}

