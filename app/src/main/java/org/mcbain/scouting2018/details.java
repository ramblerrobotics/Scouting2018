package org.mcbain.scouting2018;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class details extends AppCompatActivity {
    private int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        //tmp code
        index = 0;
        TableLayout tl = (TableLayout)findViewById(R.id.resultTable);
        ((TextView)findViewById(R.id.TeamNum)).setText(Integer.toString(Globals.teams[index].getNum()));
        ((TextView)findViewById(R.id.TeamName)).setText(" " + Globals.teams[index].getName());
        for(int i = 0; i < 13; i++){
            if(Globals.teams[index].getResult(i).isValid()){
                TableRow tr = new TableRow(getApplicationContext());
                TextView hs = new TextView(getApplicationContext());
                TextView ls = new TextView(getApplicationContext());
                TextView m = new TextView(getApplicationContext());
                TextView ca = new TextView(getApplicationContext());
                TextView as = new TextView(getApplicationContext());
                TextView n = new TextView(getApplicationContext());
                TextView c = new TextView(getApplicationContext());
                TextView mn = new TextView(getApplicationContext());
                hs.setText(Integer.toString(Globals.teams[index].getResult(i).getHighScale()));
                ls.setText(Integer.toString(Globals.teams[index].getResult(i).getLowScale()));
                m.setText(Integer.toString(Globals.teams[index].getResult(i).getMargin()));
                ca.setText(Globals.teams[index].getResult(i).getCrossedAuto() ? "Yes" : "No");
                as.setText(Integer.toString(Globals.teams[index].getResult(i).getAutoScale()));
                n.setText(Globals.teams[index].getResult(i).getNotes());
                c.setText(Integer.toString(Globals.teams[index].getResult(i).getClimb()));
                mn.setText(Integer.toString(Globals.teams[index].getResult(i).getMatchNumber()));
                n.setGravity(Gravity.CENTER);
                tl.addView(tr);
                tr.addView(mn);
                tr.addView(hs);
                tr.addView(ls);
                tr.addView(m);
                tr.addView(ca);
                tr.addView(as);
                tr.addView(c);
                tr.addView(n);
            }
        }
    }
}
