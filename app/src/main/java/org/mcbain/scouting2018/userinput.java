package org.mcbain.scouting2018;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class userinput extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private int team;
    private int match;
    private int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userinput);
            Spinner climb = (Spinner) findViewById(R.id.climbspinner);
            climb.setOnItemSelectedListener(this);
// Create an ArrayAdapter using the string array and a default spinner layout
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.standardvalues_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
            climb.setAdapter(adapter);
            Spinner hs = (Spinner) findViewById(R.id.hs);
            hs.setOnItemSelectedListener(this);
            hs.setAdapter(adapter);
            Spinner ls = (Spinner) findViewById(R.id.ls);
            ls.setOnItemSelectedListener(this);
            ls.setAdapter(adapter);
            Spinner autospinner = (Spinner) findViewById(R.id.autospinner);
            autospinner.setOnItemSelectedListener(this);
            autospinner.setAdapter(adapter);
            team = getIntent().getIntExtra("TEAM", 99999);
            match = getIntent().getIntExtra("MATCH", 99999);
            ((TextView) findViewById(R.id.teamNum5)).setText(Integer.toString(team));
            for (int i = 0; i < 50; i++) {
                if (Globals.teams[i].getNum() == team) {
                    index = i;
                    break;
                }
                //((TextView)findViewById(R.id.tmp)).setText(((TextView)findViewById(R.id.tmp)).getText()+" "+Globals.teams[i].getNum());
            }

        //((TextView)findViewById(R.id.tmp)).setText(Integer.toString(index));
        //((EditText)findViewById(R.id.WinLossScore)).setText(Integer.toString(index));
    }
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        //((TextView)findViewById(R.id.climbText)).setText((String)((Spinner)findViewById(R.id.climbspinner)).getItemAtPosition(((Spinner)findViewById(R.id.climbspinner)).getSelectedItemPosition()));
        //((TextView)findViewById(R.id.hsText)).setText((String)((Spinner)findViewById(R.id.hs)).getItemAtPosition(((Spinner)findViewById(R.id.hs)).getSelectedItemPosition()));
        //((TextView)findViewById(R.id.lsText)).setText((String)((Spinner)findViewById(R.id.ls)).getItemAtPosition(((Spinner)findViewById(R.id.ls)).getSelectedItemPosition()));
        //((TextView)findViewById(R.id.autoText)).setText((String)((Spinner)findViewById(R.id.autospinner)).getItemAtPosition(((Spinner)findViewById(R.id.autospinner)).getSelectedItemPosition()));
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
    public void addResult(View v){
        int i;
        for(i = 0; i < 13; i++){
            if((Globals.teams[index].getResult(i).getMatchNumber() == match)){
                break;
            }
            if(!(Globals.teams[index].getResult(i).isValid()))
                break;
        }
        try {
            Globals.teams[index].initResult(i, Integer.parseInt((String) ((Spinner) findViewById(R.id.hs)).getItemAtPosition(((Spinner) findViewById(R.id.hs)).getSelectedItemPosition())),
                    Integer.parseInt((String) ((Spinner) findViewById(R.id.ls)).getItemAtPosition(((Spinner) findViewById(R.id.ls)).getSelectedItemPosition())),
                    Integer.parseInt(((EditText) findViewById(R.id.WinLossScore)).getText().toString().equals("") ? "0": ((EditText) findViewById(R.id.WinLossScore)).getText().toString()),
                    ((CheckBox) findViewById(R.id.crossed)).isChecked(),
                    Integer.parseInt((String) ((Spinner) findViewById(R.id.autospinner)).getItemAtPosition(((Spinner) findViewById(R.id.autospinner)).getSelectedItemPosition())),
                    ((EditText) findViewById(R.id.notesText)).getText().toString(),
                    Integer.parseInt((String) ((Spinner) findViewById(R.id.climbspinner)).getItemAtPosition(((Spinner) findViewById(R.id.climbspinner)).getSelectedItemPosition())),
                    match);
            Toast toast = Toast.makeText(getApplicationContext(), "Result added.", Toast.LENGTH_LONG);
            toast.show();
            //((TextView)findViewById(R.id.notesText)).setText(Integer.toString(i));
        }catch(Exception e){
            Toast toast = Toast.makeText(getApplicationContext(), "Result NOT added.", Toast.LENGTH_LONG);
            toast.show();
        }
        FileIO.save(getApplicationContext());
        //Globals.teams[index].initResult(i, 0, 0, 0, true, 0, "hi", 2, 1);
    }
}

