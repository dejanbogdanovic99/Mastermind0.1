package com.sts.mastermind.guiPackage;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class ColorButton {

    private Image image;

    private float x;
    private float y;

    private float width;
    private float height;

    public ColorButton(Texture texture,
                       float x,
                       float y,
                       float scaleX,
                       float scaleY) {
        image = new Image(texture);

        float sX = scaleX*1.5f;
        float sY = scaleY*1.5f;

        width = image.getWidth()*sX;
        height = image.getHeight()*sY;

        this.x = x - width/2;
        this.y = y - height/2;

        image.setScale(sX, sY);
        image.setPosition(this.x,this.y);
    }

    public boolean isPressed(int x, int y){
        if(contains(x,y)){
            return true;
        }
        return false;
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
