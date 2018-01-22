package org.mcbain.scouting2018;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static org.mcbain.scouting2018.Globals.teams;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        teams = new Team[2];
        teams[0]= new Team(6066, "RamblerRobotics");
        teams[1]= new Team(2323, "SuperSmashClash");
        startActivity(new Intent(this, TeamList.class));
    }
}
