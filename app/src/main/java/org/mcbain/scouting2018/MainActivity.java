package org.mcbain.scouting2018;


import android.content.DialogInterface;
import android.os.StrictMode;
import java.io.BufferedReader;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.JsonToken;
import android.view.View;
import android.widget.TextView;
import org.json.JSONObject;

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
        if(false){
            Globals.teams = new Team[2];
            Globals.teams[0] = new Team(123, "ABC");
            Globals.teams[0].initResult(0, 2, 3, 1, true, 1, "Failure", 1, 2);
            Globals.teams[0].initResult(1, 1, 2, 1, false, 2, "Success", 2, 3);
            Globals.teams[1] = new Team(456, "DEF");
            Globals.teams[1].initResult(0, 2, 3, 2, false, 2, "adsf", 1, 4);
            FileIO.save(getApplicationContext());
        }
        //initData();
        //((TextView)findViewById(R.id.textView1)).setText(Globals.teams[0].getName());
        //((TextView)findViewById(R.id.textView2)).setText(Globals.teams[1].getName());
        //((TextView)findViewById(R.id.textView3)).setText(Integer.toString(Globals.teams[0].getResult(0).getHighScale()));
    }
    public void OpenMenu(View view) {
        Intent intent = new Intent(this, MenuScreen.class);
        startActivity(intent);
    }
    public void initData(View view) {
        if (FileIO.load(getApplicationContext())) {//saved data
            //return true;
        } else {
            String data;
            Globals.teams = new Team[50];
            for (int i = 0; i < 50; i++)
                Globals.teams[i] = new Team(99999, "INVALID TEAM");
            try{
            data = DownloadPage.downloadTeams("2018mike2"); // get from Victor's code
            int i = 0;
            int tmpNum = -1;
            String tmpStr = "FAKE";
                for (String line : data.split("\n")) {
                    if (line.contains("team_number"))
                        tmpNum = Integer.parseInt(line.split(": ")[1].split(",")[0]);
                    if (line.contains("nickname"))
                        tmpStr = line.split(":")[1].split("\"")[1];
                    if (tmpNum != -1 && !tmpStr.equals("FAKE")) {
                        Globals.teams[i] = new Team(tmpNum, tmpStr);
                        tmpNum = -1;
                        tmpStr = "FAKE";
                        i++;
                    }
                }
            } catch (Exception e) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setCancelable(false);
                dialog.setTitle("Error");
                dialog.setMessage("An error has occurred" );
                dialog.setPositiveButton("Okay, but I'm upset. :(", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //Action for "Delete".
                    }
                })
                        .setNegativeButton("Shoopdawhoop!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Action for "Cancel".
                            }
                        });

                final AlertDialog alert = dialog.create();
                alert.show();
            }
            FileIO.save(getApplicationContext());
        }
    }
    public void initData2(View view){
        if(FileIO.load(getApplicationContext())){//saved data
            //return true;
        }else{
            String data;
            Globals.teams = new Team[50];
            for(int i = 0; i < 50; i++)
                Globals.teams[i] = new Team(99999, "INVALID TEAM");
            try{
            data = DownloadPage.downloadTeams("2018mitvc"); // get from Victor's code
            int i = 0;
            int tmpNum = -1;
            String tmpStr = "FAKE";

                for (String line : data.split("\n")) {
                    if (line.contains("team_number"))
                        tmpNum = Integer.parseInt(line.split(": ")[1].split(",")[0]);
                    if (line.contains("nickname"))
                        tmpStr = line.split(":")[1].split("\"")[1];
                    if (tmpNum != -1 && !tmpStr.equals("FAKE")) {
                        Globals.teams[i] = new Team(tmpNum, tmpStr);
                        tmpNum = -1;
                        tmpStr = "FAKE";
                        i++;
                    }
                }
            }catch(Exception e){
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setCancelable(false);
                dialog.setTitle("Error");
                dialog.setMessage("An error has occurred" );
                dialog.setPositiveButton("Okay, but I'm upset. :(", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //Action for "Delete".
                    }
                })
                        .setNegativeButton("Shoopdawhoop!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Action for "Cancel".
                            }
                        });

                final AlertDialog alert = dialog.create();
                alert.show();
            }
            FileIO.save(getApplicationContext());
        }
    }

}
