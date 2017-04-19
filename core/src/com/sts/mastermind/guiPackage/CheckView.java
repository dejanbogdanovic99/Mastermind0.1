package com.sts.mastermind.guiPackage;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by momci on 4/18/2017.
 */

public class CheckView {

    private static final int OFFSET = 40;

    private Image[] full;
    private Image[] half;
    private int amount;
    private int fullAmount;
    private int halfAmount;
    private float scaleX;
    private float scaleY;

    public CheckView(Texture full, Texture half,float scaleX, float scaleY,
                     int amount, int fullAmount, int halfAmount){
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        this.fullAmount = fullAmount;
        this.halfAmount = halfAmount;
        this.full = new Image[amount];
        this.half = new Image[amount];
        this.amount = amount;
        for (int i = 0; i < fullAmount; i++){
            this.full[i] = new Image(full);
            this.full[i].setScale(scaleX, scaleY);
        }
        for (int i = 0; i < halfAmount; i++){
            this.half[i] = new Image(half);
            this.half[i].setScale(scaleX, scaleY);
        }

    }
    public void setPosition(float posX, float posY){
        float x = posX;

        for(int i = 0; i < fullAmount ;i++){
            if(full[i] != null){
                full[i].setPosition(x, posY);
            }
            x += OFFSET*scaleX;
        }
        for(int i = 0; i < halfAmount ;i++){
            if(half[i] != null){
                half[i].setPosition(x, posY);
            }
            x += OFFSET*scaleX;
        }

    }

    public void draw(SpriteBatch batch, float alpha){
        for (int i = 0; i < fullAmount; i++){
            if(full[i] != null) full[i].draw(batch, alpha);
        }
        for (int i = 0; i < halfAmount; i++){
            if(half[i] != null) half[i].draw(batch, alpha);
        }
    }
}
