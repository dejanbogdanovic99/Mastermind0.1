package com.sts.mastermind.guiPackage;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by Dejan on 24-Apr-17.
 */

public class SignButton {

    private Image image;

    private float x;
    private float y;

    private final int textureCode;

    private float width;
    private float height;

    public SignButton(Texture texture,
                       int textureCode,
                       float x,
                       float y,
                       float scaleX,
                       float scaleY) {
        this.textureCode = textureCode;
        image = new Image(texture);

        width = image.getWidth()*scaleX;
        height = image.getHeight()*scaleY;

        this.x = x - width/2;
        this.y = y - height/2;

        image.setScale(scaleX, scaleY);
        image.setPosition(this.x,this.y);
    }

    public boolean isPressed(int x, int y){
        if(contains(x,y)){
            return true;
        }
        return false;
    }

    public int getTextureCode() {
        return textureCode;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void draw(SpriteBatch batch, float alpha){
        image.draw(batch,alpha);
    }

    public void dispose(){
        image = null;
    }

    private boolean contains(int x, int y){
        return x >= this.x && x <= this.x + width && y >= this.y && y <= this.y + height;
    }

}
