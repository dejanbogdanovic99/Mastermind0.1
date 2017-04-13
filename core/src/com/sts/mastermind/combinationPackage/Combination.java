package com.sts.mastermind.combinationPackage;

import com.sts.mastermind.gamePackage.PlayState;

public class Combination {

    private int [] colors;

    public Combination(int amount){
        colors = new int[amount];
        for(int i = 0; i < colors.length;i++){
            colors[i] = PlayState.NO_SIGN;
        }
    }

    public int getColor(int i){
        return colors[i];
    }

    public void setColor(int i, int color){
        colors[i] = color;
    }

    public void reset(){
        for(int i = 0; i < colors.length;i++){
            colors[i] = PlayState.NO_SIGN;
        }
    }

    public int getFirstEmpty(){
        for(int i = 0; i < colors.length;i++){
            if(colors[i] == PlayState.NO_SIGN){
                return i;
            }
        }
        return -1;
    }

}
