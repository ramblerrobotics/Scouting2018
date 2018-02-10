package org.mcbain.scouting2018;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



public class DownloadPage {

    public static String downloadTeams(String event){

        try {
            URL url = new URL(" http://www.thebluealliance.com/api/v3/event/" + event + "/teams?X-TBA-Auth-Key=KAtHgAsYQgrSCUKHcl7jt0iRHjen2BFcYtzExuffElkV8lOHen2nPY2NXyYbnjLm");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            InputStream is;
            is = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String ret = "";
            try {
                String tmp2;
                while (!(tmp2 = br.readLine()).equals("]")) {
                    ret = ret.concat(tmp2 + "\n");
                }
            } catch (Exception error_variable) {
            }
            return ret;
        }catch(Exception e){return "FAIL";}
    }
    public static String downloadSched(String event){

        try {
            URL url = new URL(" http://www.thebluealliance.com/api/v3/event/" + event + "/matches/simple?X-TBA-Auth-Key=KAtHgAsYQgrSCUKHcl7jt0iRHjen2BFcYtzExuffElkV8lOHen2nPY2NXyYbnjLm");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            InputStream is;
            is = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String ret = "";
            try {
                String tmp2;
                while (!(tmp2 = br.readLine()).equals("]")) {
                    ret = ret.concat(tmp2 + "\n");
                }
            } catch (Exception error_variable) {
            }
            return ret;
        }catch(Exception e){return "FAIL";}
    }
}