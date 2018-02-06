package org.mcbain.scouting2018;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class userinput extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

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
        Spinner winlossspinner = (Spinner) findViewById(R.id.winlossspinner);
        winlossspinner.setOnItemSelectedListener(this);
        winlossspinner.setAdapter(adapter);
        Spinner autospinner = (Spinner) findViewById(R.id.autospinner);
        autospinner.setOnItemSelectedListener(this);
        autospinner.setAdapter(adapter);
    }
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        ((TextView)findViewById(R.id.climbText)).setText((String)((Spinner)findViewById(R.id.climbspinner)).getItemAtPosition(((Spinner)findViewById(R.id.climbspinner)).getSelectedItemPosition()));
        ((TextView)findViewById(R.id.hsText)).setText((String)((Spinner)findViewById(R.id.hs)).getItemAtPosition(((Spinner)findViewById(R.id.hs)).getSelectedItemPosition()));
        ((TextView)findViewById(R.id.lsText)).setText((String)((Spinner)findViewById(R.id.ls)).getItemAtPosition(((Spinner)findViewById(R.id.ls)).getSelectedItemPosition()));
        ((TextView)findViewById(R.id.winlossText)).setText((String)((Spinner)findViewById(R.id.winlossspinner)).getItemAtPosition(((Spinner)findViewById(R.id.winlossspinner)).getSelectedItemPosition()));
        ((TextView)findViewById(R.id.autoText)).setText((String)((Spinner)findViewById(R.id.autospinner)).getItemAtPosition(((Spinner)findViewById(R.id.autospinner)).getSelectedItemPosition()));
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}

