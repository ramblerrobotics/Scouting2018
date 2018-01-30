package org.mcbain.scouting2018;

import android.content.Context;

import java.io.InputStream;
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
                saved.write(ByteBuffer.allocate(4).putInt(team.getName().getBytes().length).array());
                saved.write(team.getName().getBytes());
                for(int i = 0; i < 13 /*number of results*/; i++){
                    Result result = team.getResult(i);
                    saved.write(result.isValid() ? 0x01 : 0x00);
                    saved.write(ByteBuffer.allocate(4).putInt(result.getAutoScale()).array());
                    saved.write(ByteBuffer.allocate(4).putInt(result.getHighScale()).array());
                    saved.write(ByteBuffer.allocate(4).putInt(result.getLowScale()).array());
                    saved.write(ByteBuffer.allocate(4).putInt(result.getMargin()).array());
                    saved.write(ByteBuffer.allocate(4).putInt(result.getClimb()).array());
                    saved.write(result.getCrossedAuto() ? 0x01 : 0x00);
                    saved.write(ByteBuffer.allocate(4).putInt(result.getNotes().getBytes().length).array());
                    saved.write(result.getNotes().getBytes());
                }
            }
        }catch(Exception e){
            return false;
        }
        return true;
    }
    public static Boolean load(Context context){
        try {
            InputStream saved = context.openFileInput("save");
            byte[] data = new byte[10000];
            if(saved.read(data) > -1){
                ByteBuffer buf = ByteBuffer.wrap(data);
                Globals.teams = new Team[buf.getInt()];
                for(int i = 0; i < Globals.teams.length; i++){
                    int num = buf.getInt();
                    int len = buf.getInt();
                    byte[] tmp = new byte[len];
                    buf.get(tmp, 0, len);
                    Globals.teams[i] = new Team(num, new String(tmp));
                    for(int j = 0; j < 13; j++) {
                        Boolean valid = buf.get() == 0x01;
                        int as = buf.getInt();
                        int hs = buf.getInt();
                        int ls = buf.getInt();
                        int m = buf.getInt();
                        int c = buf.getInt();
                        Boolean ca = buf.get() == 0x01;
                        int len2 = buf.getInt();
                        byte[] tmp2 = new byte[len2];
                        buf.get(tmp2, 0, len2);
                        String n = new String(tmp2);
                        if(valid)
                            Globals.teams[i].getResult(j).init(hs, ls, m, ca, as, n, c);
                    }
                }
            }else{return false;}

        }catch(Exception e){
            return false;
        }
        return true;
    }
}
