package org.mcbain.scouting2018;

import android.content.Context;
import java.io.OutputStream;
import java.nio.ByteBuffer;

/**
 * Created by rmclean on 1/25/18.
 */

public class FileIO {
    //if succeeds, true, else, false
    public static Boolean save(Context context){
        try{
            OutputStream saved  = context.openFileOutput("save", Context.MODE_PRIVATE);
            saved.write(ByteBuffer.allocate(4).putInt(Globals.teams.length).array());
            for(Team team : Globals.teams){
                saved.write(ByteBuffer.allocate(4).putInt(team.getNum()).array());
                saved.write(team.getName().getBytes());
                for(int i = 0; i < 13 /*number of results*/; i++){
                    Result result = team.getResult(i);
                    saved.write(ByteBuffer.allocate(4).putInt(result.getAutoScale()).array());
                    saved.write(ByteBuffer.allocate(4).putInt(result.getHighScale()).array());
                    saved.write(ByteBuffer.allocate(4).putInt(result.getLowScale()).array());
                    saved.write(ByteBuffer.allocate(4).putInt(result.getMargin()).array());
                    saved.write(result.isValid() ? 0x01 : 0x00);
                    saved.write(result.getCrossedAuto() ? 0x01 : 0x00);
                }
            }
        }catch(Exception e){
            return false;
        }
        return true;
    }
}
