package com.sts.mastermind.bundelPackage;

public class DataBundle {

    /*
        Da li je ukljucen zvuk
     */
    private boolean sounds;

    /*
        Da li se ponavljaju boje
     */

    private boolean repeatSigns;

    /*
        koliko imamo redova koji se pogadjaju
     */

    private int amountOfColumns;

    /*
        sa koliko boja/znakova pogadjamo
     */

    private int amountOfSigns;

    /**
    vrednost tajmera

     0 -> beskonacno
     1 -> 30s
     2 -> 1m
     3 -> 2m
     4 -> 5m
     */

    public static final int INFINITE = 0;
    public static final int T_30S = 1;
    public static final int T_1M = 2;
    public static final int T_2M = 3;
    public static final int T_5M = 4;

    private int timerValue;




    public DataBundle(boolean sounds, boolean repeatSigns, int amountOfColumns, int amountOfSigns, int timerValue) {
        this.sounds = sounds;
        this.repeatSigns = repeatSigns;
        this.amountOfColumns = amountOfColumns;
        this.amountOfSigns = amountOfSigns;
        this.timerValue = timerValue;
    }

    public boolean isSounds() {
        return sounds;
    }

    public void setSounds(boolean sounds) {
        this.sounds = sounds;
    }

    public boolean isRepeatSigns() {
        return repeatSigns;
    }

    public void setRepeatSigns(boolean repeatSigns) {
        this.repeatSigns = repeatSigns;
    }

    public int getAmountOfColumns() {
        return amountOfColumns;
    }

    public void setAmountOfColumns(int amountOfColumns) {
        this.amountOfColumns = amountOfColumns;
    }

    public int getAmountOfSigns() {
        return amountOfSigns;
    }

    public void setAmountOfSigns(int amountOfSigns) {
        this.amountOfSigns = amountOfSigns;
    }

    public int getTimerValue() {
        return timerValue;
    }

    public void setTimerValue(int timerValue) {
        this.timerValue = timerValue;
    }
}
