package com.sts.mastermind.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Button {

    private Image upImage;
    private Image downImage;

    private boolean pressed;

    public Button(Texture t1, Texture t2, float x, float y, float scaleX, float scaleY){
        upImage = new Image(t1);
        downImage = new Image(t2);

        upImage.setScale(scaleX,scaleY);
        downImage.setScale(scaleX,scaleY);

        upImage.setPosition(x - scaleX*upImage.getWidth()/2,y - scaleY*upImage.getHeight()/2);
        downImage.setPosition(x - scaleX*downImage.getWidth()/2,y - scaleY*downImage.getHeight()/2);

        pressed = false;

    }

    public void handleDown(int x, int y){
        if(contains(x,y)){
            pressed = true;
        }else{
            pressed = false;
        }
    }

    public boolean handleUp(int x, int y){
        pressed = false;
        if(contains(x,y)){
            return true;
        }
        return false;
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
        return x >= upImage.getX() && x <= upImage.getX() + upImage.getWidth() && y >= upImage.getY() && y <= upImage.getY() + upImage.getHeight();
    }

}
