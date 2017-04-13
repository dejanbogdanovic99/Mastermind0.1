package com.sts.mastermind.bundelPackage;

public class DataBundle {

    /*
        Da li je ukljucen zvuk
     */
    private boolean volume;

    /*
        Da li se ponavljaju boje
     */

    private boolean repeatColors;

    /*
        koliko imamo redova koji se pogadjaju
     */

    private int amountOfRows;

    /*
        sa koliko boja/znakova pogadjamo
     */

    private int amountOfColors;


    public DataBundle(boolean volume, boolean repeatColors, int amountOfRows, int amountOfColors) {
        this.volume = volume;
        this.repeatColors = repeatColors;
        this.amountOfRows = amountOfRows;
        this.amountOfColors = amountOfColors;
    }

    public boolean getVolume() {
        return volume;
    }

    public boolean getRepeatColors() {
        return repeatColors;
    }

    public int getAmountOfRows() {
        return amountOfRows;
    }

    public int getAmountOfColors() {
        return amountOfColors;
    }

}
