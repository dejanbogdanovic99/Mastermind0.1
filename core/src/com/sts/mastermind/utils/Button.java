package com.sts.mastermind.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Button {

    private Image upImage;
    private Image downImage;

    private boolean pressed;

    private float scale;

    private float x;
    private float y;

    public Button(Texture t1, Texture t2, float scale, float x, float y){
        upImage = new Image(t1);
        downImage = new Image(t2);

        this.scale = scale;
        this.x = x;
        this.y = y;

        upImage.setPosition(x,y);
        downImage.setPosition(x,y);

        upImage.setScale(scale);
        downImage.setScale(scale);

        pressed = false;

    }

    public void handleDown(int x, int y){
        if(contains(x,y)){
            pressed = true;
        }
    }

    public boolean handleUp(int x, int y){
        if(contains(x,y)){
            return true;
        }
        pressed = false;
        return false;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void draw(SpriteBatch batch, float alpha){
        if(pressed){
            downImage.draw(batch, alpha);
        }else{
            upImage.draw(batch, alpha);
        }
    }

    public void dispose(){
        upImage = null;
        downImage = null;
    }

    private boolean contains(int x, int y){
        return x >= this.x && x <= this.x + upImage.getWidth() && y >= this.y && y <= this.y + upImage.getHeight();
    }

}
