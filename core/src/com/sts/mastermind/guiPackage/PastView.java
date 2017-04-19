package com.sts.mastermind.guiPackage;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;


public class PastView {
    private List<CombinationView> pastCombinations;
    private List<CheckView> pastChecks;

    public PastView(){
        pastCombinations = new ArrayList<CombinationView>();
        pastChecks = new ArrayList<CheckView>();
    }

    public void add(CombinationView x, CheckView y){
        pastCombinations.add(x);
        pastChecks.add(y);
    }
    public void draw(SpriteBatch batch, float alpha){
        for(int i = 0; i < pastCombinations.size(); i++){
            pastCombinations.get(i).draw(batch, alpha);
        }
        for(int i = 0; i < pastChecks.size(); i++){
            pastChecks.get(i).draw(batch, alpha);
        }
    }

    public void reset(){
        pastCombinations.clear();
        pastChecks.clear();
    }
}
