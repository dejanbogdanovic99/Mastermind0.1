package com.sts.mastermind.combinationPackage;

import com.sts.mastermind.gamePackage.PlayState;

public class Combination {

    private int [] signs;

    public Combination(int amount){
        signs = new int[amount];
        for(int i = 0; i < signs.length;i++){
            signs[i] = PlayState.NO_SIGN;
        }
    }

    public int getSign(int i){
        return signs[i];
    }

    public void setSign(int i, int sign){
        signs[i] = sign;
    }

    public int size(){
        return signs.length;
    }

    public void reset(){
        for(int i = 0; i < signs.length;i++){
            signs[i] = PlayState.NO_SIGN;
        }
    }

    public int getFirstEmpty(){
        for(int i = 0; i < signs.length;i++){
            if(signs[i] == PlayState.NO_SIGN){
                return i;
            }
        }
        return -1;
    }

}
