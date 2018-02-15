package org.mcbain.scouting2018;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MatchSched extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_sched);
        TableLayout tlayout = (TableLayout)findViewById(R.id.tLayout);
        for(int i = 2; i < tlayout.getChildCount(); i++){
            //start at 2 so we don't get the top text
            TableRow tRow = (TableRow)tlayout.getChildAt(i);
            for(int j = 2; j < 8 ; j++){
                //get red1-blue3
                tRow.getChildAt(j).setOnClickListener(this);
            }
        }
        for(Match match : Globals.matches){
            if(match.getNum() != 0){
                //((TextView)findViewById(R.id.TextViewr8c5)).setText(Integer.toString(match.getNum()));
                TableRow tr = (TableRow)tlayout.getChildAt(match.getNum() + 1);
                Date date = new Date(match.getTime() * 1000L);
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                ((TextView)tr.getChildAt(0)).setText(sdf.format(date));
                ((TextView)tr.getChildAt(1)).setText(Integer.toString(match.getNum()));
                ((TextView)tr.getChildAt(2)).setText(Integer.toString(match.getR1()));
                ((TextView)tr.getChildAt(3)).setText(Integer.toString(match.getR2()));
                ((TextView)tr.getChildAt(4)).setText(Integer.toString(match.getR3()));
                ((TextView)tr.getChildAt(5)).setText(Integer.toString(match.getB1()));
                ((TextView)tr.getChildAt(6)).setText(Integer.toString(match.getB2()));
                ((TextView)tr.getChildAt(7)).setText(Integer.toString(match.getB3()));
                for(int i = 0; i < tr.getChildCount(); i++){
                    ((TextView)tr.getChildAt(i)).setTextSize(TypedValue.COMPLEX_UNIT_SP,12);
                }
            }
        }
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

    @Override
    public void onClick(View v) {
        //open the userinputdesign page with match number and team number
        //to get match number, we can get the parent of view and get the appropriate child contents
        //similar for team number
        Intent intent = new Intent(getBaseContext(), userinput.class);
        intent.putExtra("MATCH", Integer.parseInt((String)((TextView)((TableRow)v.getParent()).getChildAt(1)).getText()));
        intent.putExtra("TEAM", Integer.parseInt((String)((TextView)v).getText()));
        startActivity(intent);
    }
}
