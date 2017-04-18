package com.sts.mastermind.guiPackage;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.sts.mastermind.combinationPackage.Combination;
import com.sts.mastermind.gamePackage.PlayState;

public class CombinationView {

    private static final int OFFSET = 10;

    private Image[] back;

    private Image[] signs;

    private Combination combination;

    private float width;
    private float height;

    private float scaleX;
    private float scaleY;

    public CombinationView(int amount, Texture back, float scaleX, float scaleY){
        combination = new Combination(amount);
        this.signs = new Image[amount];
        this.back = new Image[amount];
        for(int i = 0; i < amount; i++){
            this.back[i] = new Image(back);
            this.back[i].setScale(scaleX, scaleY);
        }

        this.width = scaleX * this.back[0].getWidth();
        this.height = scaleY * this.back[0].getHeight();

        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }



    public void setPosition(float x, float y){

        float xP = x;
        float yP = y;

        for(int i = 0; i < combination.size();i++){
            back[i].setPosition(xP, yP);
            if(signs[i] != null){
                signs[i].setPosition(xP, yP);
            }
            xP += (width + OFFSET*scaleX);
        }
    }

    public void setSign(int i, Texture t, int code){
        signs[i] = new Image(t);
        signs[i].setScale(scaleX, scaleY);
        signs[i].setPosition(back[i].getX(), back[i].getY());
        combination.setSign(i, code);
    }

    public void handleInput(int x, int y){
        for(int i = 0; i < combination.size();i++){
            if(isBackPressed(x,y,i)){
                combination.setSign(i,PlayState.NO_SIGN);
            }
        }
    }

    public void draw(SpriteBatch batch, float alpha){
        for(int i = 0; i < combination.size();i++){
            back[i].draw(batch, alpha);
            if(combination.getSign(i) != PlayState.NO_SIGN){
                signs[i].draw(batch,alpha);
            }
        }
    }

    public Combination getCombination() {
        return combination;
    }

    private boolean isBackPressed(int x, int y, int i){
        return x >= back[i].getX() && x <= back[i].getX() + width && y >= back[i].getY() && y <= back[i].getY() + height;
    }
}
