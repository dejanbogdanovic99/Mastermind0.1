package com.sts.mastermind.guiPackage;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class BasicImage {

    private Image image;

    private float width;
    private float height;

    private float x;
    private float y;

    private float scaleX;
    private float scaleY;

    public BasicImage(Texture t, float x, float y, float scaleX, float scaleY) {
        image = new Image(t);

        image.setScale(scaleX,scaleY);

        width = image.getWidth()*scaleX;
        height = image.getHeight()*scaleY;

        this.x = x;
        this.y = y;

        image.setPosition(x - width/2, y - height/2);

        this.scaleX = scaleX;
        this.scaleY = scaleY;

    }

    public void setTexture(Texture t){
        image = new Image(t);

        image.setScale(scaleX,scaleY);

        width = image.getWidth()*scaleX;
        height = image.getHeight()*scaleY;

        image.setPosition(x - width/2, y - height/2);

    }

    public void setPosition(float x, float y){
        this.x = x - width/2;
        this.y = y - height/2;

        image.setPosition(this.x, this.y);
    }

    public void draw(SpriteBatch batch, float alpha){
        image.draw(batch, alpha);
    }

}
