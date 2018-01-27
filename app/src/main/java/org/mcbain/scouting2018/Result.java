package org.mcbain.scouting2018;

/**
 * Created by rmclean on 1/15/18.
 */

public class Result {
    Boolean valid;

    public int getHighScale() {
        return highScale;
    }

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
    public Boolean isValid(){
        return valid;
    }
    int highScale;
    int lowScale;
    int margin;
    Boolean crossedAuto;
    int autoScale;
    Result(){
        valid = false;
    }
}
