package org.mcbain.scouting2018;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class TeamList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teamlistslayout);

            for (Team t : Globals.teams) {
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
                    teamnumber.setText(Integer.toString(t.getNum()));
                    teamname.setText(t.getName());
                    climb.setText(Float.toString(((float) c) / ((float) valids)));
                    highscale.setText(Float.toString(((float) hs) / ((float) valids)));
                    lowscale.setText(Float.toString(((float) ls) / (float) valids));
                    winloss.setText(Float.toString(((float) wl) / (float) valids));
                    autoscale.setText(Float.toString(((float) as) / (float) valids));
                    autoline.setText(String.valueOf(al));
                }else {
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
                ((TableLayout) findViewById(R.id.StatsTable)).addView(tr);
            }
            TextView nothing = new TextView(getApplicationContext());
            nothing.setText("Boring placeholder text");
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
}
