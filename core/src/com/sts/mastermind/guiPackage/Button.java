package com.sts.mastermind.guiPackage;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Button {

    private Image upImage;
    private Image downImage;

    private boolean pressed;

    private float width;
    private float height;

    private float x;
    private float y;

    public Button(Texture t1,
                  Texture t2,
                  float x,
                  float y,
                  float scaleX,
                  float scaleY){
        upImage = new Image(t1);
        downImage = new Image(t2);

        upImage.setScale(scaleX,scaleY);
        downImage.setScale(scaleX,scaleY);

        width = upImage.getWidth()*scaleX;
        height = upImage.getHeight()*scaleY;

        this.x = x - width/2;
        this.y = y - height/2;

        upImage.setPosition(this.x, this.y);
        downImage.setPosition(this.x, this.y);

        pressed = false;

    }

    public void setPosition(float x, float y){
        this.x = x - width/2;
        this.y = y - height/2;

        upImage.setPosition(this.x, this.y);
        downImage.setPosition(this.x, this.y);
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
        return x >= this.x && x <= this.x + width && y >= this.y && y <= this.y + height;
    }

}
