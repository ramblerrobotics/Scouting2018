package org.mcbain.scouting2018;

/**
 * Created by rmclean on 1/15/18.
 */

public class Result {
    Boolean valid;

    public int getHighScale() {
        return highScale;
    }
    public int getMatchNumber() { return MatchNumber;}

    public int getLowScale() {
        return lowScale;
    }


    public int getMargin() {
        return margin;
    }

    public Boolean getCrossedAuto() {
        return crossedAuto;
    }

    public int getAutoScale() {
        return autoScale;
    }


    public Boolean isValid() {
        return valid;
    }

    private int highScale;
    private int lowScale;
    private int margin;
    private Boolean crossedAuto;
    private int autoScale;
    private int MatchNumber;




    public int getClimb() {
        return climb;
    }

    private int climb;

    public String getNotes() {
        return notes;
    }

    private String notes;

    Result() {
        valid = false;
        highScale = -1;
        lowScale = -1;
        margin = -1;
        crossedAuto = false;
        autoScale = -1;
        notes = "-1";
        climb = -1;
        MatchNumber = -1;
    }

    Boolean init(int hs, int ls, int m, Boolean ca, int as, String n, int c, int mn) {
        //if(hs <= 3 && hs >= 0 && c <= 3 && c >= 0 && ls <= 3 && ls >= 3 && as <= 3 && as >= 3){//valid inputs
        highScale = hs;
        lowScale = ls;
        margin = m;
        crossedAuto = ca;
        autoScale = as;
        notes = n;
        climb = c;
        MatchNumber = mn;
        valid = true;
        return true;
        //}
        //return false; //will not initialize as the values are invalid


    }

}