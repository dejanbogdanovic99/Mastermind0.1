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

}
