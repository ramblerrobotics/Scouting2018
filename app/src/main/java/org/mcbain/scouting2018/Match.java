package org.mcbain.scouting2018;

/**
 * Created by rmclean on 2/10/18.
 */

public class Match {
    private long time;
    private int r1;
    private int r2;

    public int getNum() {
        return num;
    }

    private int num;

    public long getTime() {
        return time;
    }

    public int getR1() {
        return r1;
    }

    public int getR2() {
        return r2;
    }

    public int getR3() {
        return r3;
    }

    public int getB1() {
        return b1;
    }

    public int getB2() {
        return b2;
    }

    public int getB3() {
        return b3;
    }

    private int r3;
    private int b1;
    private int b2;
    private int b3;
    public void init(long time, int r1, int r2, int r3, int b1, int b2, int b3, int num){
        this.time = time;
        this.r1 = r1;
        this.r2 = r2;
        this.r3 = r3;
        this.b1 = b1;
        this.b2 = b2;
        this.b3 = b3;
        this.num = num;
    }
    public Match(){
        this.time = 0;
        this.r1 = 0;
        this.r2 = 0;
        this.r3 = 0;
        this.b1 = 0;
        this.b2 = 0;
        this.b3 = 0;
        this.num = 0;
    }
}
