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
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
        Globals.matches = new Match[100];
        Globals.matches = new Match[100];
        for(int j = 0; j < 100; j++){
            Globals.matches[j] = new Match();
        }
        Globals.teams = new Team[50];
        FileIO.load(getApplicationContext());
        //Globals.teams[0].initResult(0, 0, 0, 0, true, 0, "hi", 2, 1);
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
        Toast toast = Toast.makeText(getApplicationContext(), "Download completed!", Toast.LENGTH_SHORT);
        toast.show();
            String data;
            Globals.teams = new Team[50];
            for (int i = 0; i < 50; i++)
                Globals.teams[i] = new Team(99999, "INVALID TEAM");
        try{
            data = DownloadPage.downloadTeams("2017mike2"); // get from Victor's code
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
        Globals.matches = new Match[100];
        for(int j = 0; j < 100; j++){
            Globals.matches[j] = new Match();
        }
        String data2 = DownloadPage.downloadSched("2018mike2");
        if(data2.equals("FAIL"))
            return;
        try{
            String comp_level = "FAIL";
            String match_number = "FAIL";
            Boolean isBlue = false;
            Boolean isRed = false;
            Boolean loadNext = false;
            String time = "FAIL";
            String[] blue = new String[3];
            String[] red = new String[3];
            int loaded = 0;
            int k = 0;
            for(String line : data2.split("\n")){
                if((isBlue || isRed) && !loadNext){
                    if(line.contains("\"team_keys")){
                        loadNext = true;
                        continue;
                    }
                }
                if(loadNext){
                    if(isBlue){
                        blue[loaded] = line.split("frc")[1].split("\"")[0];
                        loaded++;
                        if(loaded == 3) {
                            loadNext = false;
                            isBlue = false;
                            loaded = 0;
                        }
                    }else{
                        red[loaded] = line.split("frc")[1].split("\"")[0];
                        loaded++;
                        if(loaded == 3){
                            loadNext = false;
                            isRed = false;
                            loaded = 0;
                        }
                    }
                    continue;
                }
                if(line.contains("comp_level"))
                    comp_level = line.split(": ")[1].split("\"")[1];
                if(line.contains("match_number"))
                    match_number = line.split(": ")[1].split(",")[0];
                if(line.contains("predicted_time")) {
                    time = line.split(": ")[1].split(",")[0];
                    if(time.equals("null"))
                        time = "1";
                }
                if(line.contains("\"red\":"))
                    isRed = true;
                if(line.contains("\"blue\":"))
                    isBlue = true;
                if(!comp_level.equals("FAIL") && !match_number.equals("FAIL") && !time.equals("FAIL") && blue[2] != null){
                    if(comp_level.equals("qm")) {
                        Globals.matches[k].init(Long.parseLong(time), Integer.parseInt(red[0]), Integer.parseInt(red[1]), Integer.parseInt(red[2]), Integer.parseInt(blue[0]), Integer.parseInt(blue[1]), Integer.parseInt(blue[2]), Integer.parseInt(match_number));
                        k++;
                    }
                    comp_level = "FAIL";
                    match_number = "FAIL";
                    isBlue = false;
                    isRed = false;
                    loadNext = false;
                    time = "FAIL";
                    blue = new String[3];
                    red = new String[3];
                }
            }}catch(Exception e){
        }
            FileIO.save(getApplicationContext());
            FileIO.load(getApplicationContext());
    }
    public void initData2(View view){
        Toast toast = Toast.makeText(getApplicationContext(), "Download completed!", Toast.LENGTH_SHORT);
        toast.show();
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
            //match schedule
            Globals.matches = new Match[100];
            for(int j = 0; j < 100; j++){
                Globals.matches[j] = new Match();
            }
            String data2 = DownloadPage.downloadSched("2017mike2");
            if(data2.equals("FAIL"))
                return;
            try{
            String comp_level = "FAIL";
            String match_number = "FAIL";
            Boolean isBlue = false;
            Boolean isRed = false;
            Boolean loadNext = false;
            String time = "FAIL";
            String[] blue = new String[3];
            String[] red = new String[3];
            int loaded = 0;
            int k = 0;
            for(String line : data2.split("\n")){
                if((isBlue || isRed) && !loadNext){
                    if(line.contains("\"team_keys")){
                        loadNext = true;
                        continue;
                    }
                }
                if(loadNext){
                    if(isBlue){
                        blue[loaded] = line.split("frc")[1].split("\"")[0];
                        loaded++;
                        if(loaded == 3) {
                            loadNext = false;
                            isBlue = false;
                            loaded = 0;
                        }
                    }else{
                        red[loaded] = line.split("frc")[1].split("\"")[0];
                        loaded++;
                        if(loaded == 3){
                            loadNext = false;
                            isRed = false;
                            loaded = 0;
                        }
                    }
                    continue;
                }
                if(line.contains("comp_level"))
                    comp_level = line.split(": ")[1].split("\"")[1];
                if(line.contains("match_number"))
                    match_number = line.split(": ")[1].split(",")[0];
                if(line.contains("predicted_time")) {
                    time = line.split(": ")[1].split(",")[0];
                    if(time.equals("null"))
                        time = "1";
                }
                if(line.contains("\"red\":"))
                    isRed = true;
                if(line.contains("\"blue\":"))
                    isBlue = true;
                if(!comp_level.equals("FAIL") && !match_number.equals("FAIL") && !time.equals("FAIL") && blue[2] != null){
                    if(comp_level.equals("qm")) {
                        Globals.matches[k].init(Long.parseLong(time), Integer.parseInt(red[0]), Integer.parseInt(red[1]), Integer.parseInt(red[2]), Integer.parseInt(blue[0]), Integer.parseInt(blue[1]), Integer.parseInt(blue[2]), Integer.parseInt(match_number));
                        k++;
                    }
                    comp_level = "FAIL";
                    match_number = "FAIL";
                    isBlue = false;
                    isRed = false;
                    loadNext = false;
                    time = "FAIL";
                    blue = new String[3];
                    red = new String[3];
                }
            }}catch(Exception e){
            }
            FileIO.save(getApplicationContext());
            FileIO.load(getApplicationContext());
        }


}
