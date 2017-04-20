package com.sts.mastermind.bundelPackage;

public class DataBundle {

    /*
        Da li je ukljucen zvuk
     */
    private boolean volume;

    /*
        Da li se ponavljaju boje
     */

    private boolean repeatSigns;

    /*
        koliko imamo redova koji se pogadjaju
     */

    private int amountOfRows;

    /*
        sa koliko boja/znakova pogadjamo
     */

    private int amountOfSigns;

    /*
        boja pozadine
     */

    private int bgColor = 2;


    public DataBundle(boolean volume,
                      boolean repeatSigns,
                      int amountOfRows,
                      int amountOfSigns) {
        this.volume = volume;
        this.repeatSigns = repeatSigns;
        this.amountOfRows = amountOfRows;
        this.amountOfSigns = amountOfSigns;
    }

    public boolean getVolume() {
        return volume;
    }

    public boolean getRepeatSigns() {
        return repeatSigns;
    }

    public int getAmountOfRows() {
        return amountOfRows;
    }

    public int getAmountOfSigns() {
        return amountOfSigns;
    }

    public void setVolume(boolean volume) {
        this.volume = volume;
    }

    public void setRepeatSigns(boolean repeatSigns) {
        this.repeatSigns = repeatSigns;
    }

    public void setAmountOfRows(int amountOfRows) {
        this.amountOfRows = amountOfRows;
    }

    public void setAmountOfSigns(int amountOfSigns) {
        this.amountOfSigns = amountOfSigns;
    }

    public int getBgColor() {
        return bgColor;
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
    }
}
