package org.mcbain.scouting2018;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



public class DownloadPage {
    public static String downloadTeams(String event) throws IOException {


        URL url = new URL(" http://www.thebluealliance.com/api/v3/event/"+event+"/teams?X-TBA-Auth-Key=KAtHgAsYQgrSCUKHcl7jt0iRHjen2BFcYtzExuffElkV8lOHen2nPY2NXyYbnjLm");

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        InputStream is;
        is = con.getInputStream();

        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String ret = "";
        try {
            while (true) {
               ret = ret.concat(br.readLine());
            }
        } catch (Exception error_variable) {}

        return ret;

    }

}