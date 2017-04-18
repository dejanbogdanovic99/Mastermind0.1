package com.sts.mastermind.guiPackage;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by momci on 4/18/2017.
 */

public class PastView {
    private List<CombinationView> pastCombinations;
    private List<CheckView> pastChecks = new ArrayList<CheckView>();

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
}
