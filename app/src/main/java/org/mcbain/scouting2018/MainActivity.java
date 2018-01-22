package org.mcbain.scouting2018;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.View;

import java.io.IOException;
import java.io.StringReader;

import static org.mcbain.scouting2018.Globals.teams;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
    }
    public void OpenMenu(View view) {
        Intent intent = new Intent(this, MenuScreen.class);
        startActivity(intent);
    }
    private void initData(){
        if(false){//saved data

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
