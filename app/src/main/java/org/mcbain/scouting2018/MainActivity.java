package org.mcbain.scouting2018;


import android.os.StrictMode;
import java.io.BufferedReader;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.io.StringReader;

import static org.mcbain.scouting2018.Globals.teams;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        //init test data
        if(true){
            Globals.teams = new Team[2];
            Globals.teams[0] = new Team(123, "ABC");
            Globals.teams[0].initResult(0, 2, 3, 1, true, 1, "Failure", 1);
            Globals.teams[0].initResult(1, 1, 2, 1, false, 2, "Success", 2);
            Globals.teams[1] = new Team(456, "DEF");
            Globals.teams[1].initResult(0, 2, 3, 2, false, 2, "adsf", 1);
            FileIO.save(getApplicationContext());
        }
        initData();
        //((TextView)findViewById(R.id.textView1)).setText(Globals.teams[0].getName());
        //((TextView)findViewById(R.id.textView2)).setText(Globals.teams[1].getName());
        //((TextView)findViewById(R.id.textView3)).setText(Integer.toString(Globals.teams[0].getResult(0).getHighScale()));
    }
    public void OpenMenu(View view) {
        Intent intent = new Intent(this, MenuScreen.class);
        startActivity(intent);
    }
    private void initData(){
        if(FileIO.load(getApplicationContext())){//saved data

        }else{
            String data = "nothing"; // get from Victor's code
            JsonReader reader = new JsonReader(new StringReader(data));
            String tmp = "FAKE";
            try {
                for (int i = 0; ; i++) {
                    switch (reader.nextName()) {
                        case "team_number":
                            if(tmp.equals("FAKE"))
                                tmp = reader.nextString();
                            else {//assume name is set
                                Globals.teams[i] = new Team(reader.nextInt(), tmp);
                                tmp = "FAKE";
                            }
                            break;
                        case "name":
                            if(tmp.equals("FAKE"))
                                tmp = reader.nextString();
                            else {//assume name is set
                                Globals.teams[i] = new Team(Integer.parseInt(tmp), reader.nextString());
                                tmp = "FAKE";
                            }

                    }
                }
            }catch(IOException e){
                //decide how to handle better
            }
            //teams processed (hopefully) successfully
            //match schedule processing
            //ours are 2018mitvc and 2018 mike2
            
        }
    }

}
