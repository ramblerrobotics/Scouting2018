package org.mcbain.scouting2018;

import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
//import android.content.res;
import android.view.View;
import android.widget.EditText;

import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.util.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Manual extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_manual);
        
            //File pdf = getApplicationContext().getAssets().open("2018FRCGameSeasonManual.pdf");



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
    public void onClick(View view){
        findViewById(R.id.pdfpagetext).setVisibility(View.INVISIBLE);
        findViewById(R.id.pdfbutton).setVisibility(View.INVISIBLE);
        EditText tmp = (EditText)findViewById(R.id.pdfpagetext);
        ((PDFView) findViewById(R.id.pdfview)).fromAsset("2018FRCGameSeasonManual.pdf")
                .defaultPage(Integer.parseInt(tmp.getText().toString()))
                .showMinimap(true)
                .enableSwipe(true)
                .load();
    }
}
