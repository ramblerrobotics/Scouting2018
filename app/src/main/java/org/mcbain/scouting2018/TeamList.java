package org.mcbain.scouting2018;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.DecimalFormat;

public class TeamList extends AppCompatActivity implements View.OnClickListener{
    Team[] teams;
    float[][] avgs;
    Boolean[] lines;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teamlistslayout);
        teams = Globals.teams;
        avgs = new float[Globals.teams.length][5];
        lines = new Boolean[Globals.teams.length];
            for (int j = 0; j < Globals.teams.length; j++) {
                Team t = Globals.teams[j];
                if (t.getNum() == 99999)
                    continue;
                TableRow tr = new TableRow(getApplicationContext());
                TextView teamnumber = new TextView(getApplicationContext());
                TextView teamname = new TextView(getApplicationContext());
                TextView climb = new TextView(getApplicationContext());
                TextView highscale = new TextView(getApplicationContext());
                TextView lowscale = new TextView(getApplicationContext());
                TextView winloss = new TextView(getApplicationContext());
                TextView autoline = new TextView(getApplicationContext());
                TextView autoscale = new TextView(getApplicationContext());
                int c = 0, hs = 0, ls = 0, wl = 0, as = 0;
                Boolean al = false;
                int valids = 0;
                for (int i = 0; i < 13; i++) {
                    if (t.getResult(i).isValid()) {
                        c += t.getResult(i).getClimb();
                        hs += t.getResult(i).getHighScale();
                        ls += t.getResult(i).getLowScale();
                        wl += t.getResult(i).getMargin();
                        as += t.getResult(i).getAutoScale();
                        if (t.getResult(i).getCrossedAuto())
                            al = true;
                        valids++;
                    }
                }
                if(valids != 0) {
                    DecimalFormat df = new DecimalFormat("#.##");
                    avgs[j][0] = ((float) c) / ((float) valids);
                    avgs[j][1] = ((float) hs) / ((float) valids);
                    avgs[j][2] = ((float) ls) / ((float) valids);
                    avgs[j][3] = ((float) wl) / ((float) valids);
                    avgs[j][4] = ((float) as) / ((float) valids);
                    teamnumber.setText(Integer.toString(t.getNum()));
                    teamname.setText(t.getName());
                    climb.setText(df.format(avgs[j][0]));
                    highscale.setText(df.format(avgs[j][1]));
                    lowscale.setText(df.format(avgs[j][2]));
                    winloss.setText(df.format(avgs[j][3]));
                    autoscale.setText(df.format(avgs[j][4]));
                    lines[j] = al;
                    autoline.setText(String.valueOf(al));
                }else {
                    avgs[j][0] = -1f;
                    avgs[j][1] = -1f;
                    avgs[j][2] = -1f;
                    avgs[j][3] = -1f;
                    avgs[j][4] = -1f;
                    lines[j] = false;
                    teamnumber.setText(Integer.toString(t.getNum()));
                    teamname.setText(t.getName());
                    climb.setText("N/A");
                    highscale.setText("N/A");
                    lowscale.setText("N/A");
                    winloss.setText("N/A");
                    autoscale.setText("N/A");
                    autoline.setText("N/A");
                }
                tr.addView(teamnumber);
                tr.addView(teamname);
                tr.addView(climb);
                tr.addView(highscale);
                tr.addView(lowscale);
                tr.addView(winloss);
                tr.addView(autoline);
                tr.addView(autoscale);
                tr.setOnClickListener(this);
                ((TableLayout) findViewById(R.id.StatsTable)).addView(tr);
            }
            TextView nothing = new TextView(getApplicationContext());
            nothing.setText(".");
            nothing.setVisibility(View.INVISIBLE);
            TableRow nothing2 = new TableRow(getApplicationContext());
            nothing2.addView(nothing);
        ((TableLayout)findViewById(R.id.StatsTable)).addView(nothing2);
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
    public void bubbleSort(int category){
        Boolean moved = true;
        while(moved) {
            moved = false;
            for (int i = 1; i < teams.length; i++) {
                if (teams[i].getNum() == 99999)
                    continue;
                if (avgs[i][category] > avgs[i - 1][category]) {
                    moved = true;
                    Team num1 = teams[i];
                    Team num2 = teams[i - 1];
                    teams[i] = num2;
                    teams[i - 1] = num1;
                    float[] f1 = avgs[i];
                    float[] f2 = avgs[i - 1];
                    avgs[i] = f2;
                    avgs[i - 1] = f1;
                    Boolean b1 = lines[i];
                    Boolean b2 = lines[i - 1];
                    lines[i] = b2;
                    lines[i - 1] = b1;
                }
            }
        }
    }
    public void update(){
        for(int i = 0; i < teams.length; i++){
            if(teams[i].getNum() == 99999)
                continue;
            DecimalFormat df = new DecimalFormat("#.##");
            TableRow tr = (TableRow)((TableLayout)findViewById(R.id.StatsTable)).getChildAt(i + 1);
            ((TextView)tr.getChildAt(0)).setText(Integer.toString(teams[i].getNum()));
            ((TextView)tr.getChildAt(1)).setText(teams[i].getName());
            ((TextView)tr.getChildAt(2)).setText(Float.toString(avgs[i][0]).equals("-1.0") ? "N/A" : df.format(avgs[i][0]));
            ((TextView)tr.getChildAt(3)).setText(Float.toString(avgs[i][1]).equals("-1.0") ? "N/A" : df.format(avgs[i][1]));
            ((TextView)tr.getChildAt(4)).setText(Float.toString(avgs[i][2]).equals("-1.0") ? "N/A" : df.format(avgs[i][2]));
            ((TextView)tr.getChildAt(5)).setText(Float.toString(avgs[i][3]).equals("-1.0") ? "N/A" : df.format(avgs[i][3]));
            ((TextView)tr.getChildAt(6)).setText(String.valueOf(lines[i]));
            ((TextView)tr.getChildAt(7)).setText(Float.toString(avgs[i][4]).equals("-1.0") ? "N/A" : df.format(avgs[i][4]));
        }
    }
    public void climbSort(View v){
        bubbleSort(0);
        update();
    }
    public void highSort(View v){
        bubbleSort(1);
        update();
    }
    public void lowSort(View v){
        bubbleSort(2);
        update();
    }
    public void marginSort(View v){
        bubbleSort(3);
        update();
    }
    public void autoSort(View v){
        bubbleSort(4);
        update();
    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, details.class);
        intent.putExtra("NUM", Integer.parseInt(((TextView)((TableRow)v).getChildAt(0)).getText().toString()));
        startActivity(intent);
    }
}
