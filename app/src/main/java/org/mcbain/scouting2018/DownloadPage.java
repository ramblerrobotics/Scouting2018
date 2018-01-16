package org.mcbain.scouting2018;
import android.security.keystore.KeyInfo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
/**
 * Created by vromero on 1/15/18.
 */

public class DownloadPage {
    public static String getData() throws IOException {






        {

            URL url = new URL("https://www.thebluealliance.com/api/v3");

            URLConnection con = url.openConnection();
            InputStream is = con.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line = null;

            while ((line = br.readLine()) != null) {
                System.out.println(line);


            }
            return "Thanks";

        }
    }
}