package org.mcbain.scouting2018;

/**
 * Created by rmclean on 1/15/18.
 */

public class Team {
    private int num;
    private String name;
    private Result[] data;
    Team(int num, String name){
        this.num = num;
        this.name = name;
        this.data = new Result[13];
        for(int i = 0; i < 13; i++)
            data[i] = new Result();
    }
    public int getNum(){
        return num;
    }
    public String getName(){
        return name;
    }
    public Result getResult(int i){return data[i];}
}
