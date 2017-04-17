package com.sts.mastermind.guiPackage;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class CheckButton {

    private Image checkImage;
    private Image unCheckImage;

    private boolean checked;

    private float width;
    private float height;

    private float x;
    private float y;

    public CheckButton(Texture checkTexture, Texture unCheckTexture,boolean checked, float x, float y, float scaleX, float scaleY) {
        checkImage = new Image(checkTexture);
        unCheckImage = new Image(unCheckTexture);

        checkImage.setScale(scaleX,scaleY);
        unCheckImage.setScale(scaleX, scaleY);

        width = checkImage.getWidth()*scaleX;
        height = checkImage.getHeight()*scaleY;

        this.x = x - width/2;
        this.y = y - height/2;

        checkImage.setPosition(this.x, this.y);
        unCheckImage.setPosition(this.x, this.y);

        this.checked = checked;

    }

    public boolean handleUp(int x, int y){
        if(contains(x,y)){
            checked = !checked;
            return true;
        }
        return false;
    }

    public boolean isChecked() {
        return checked;
    }

    public void draw(SpriteBatch batch, float alpha){
        if(checked){
            checkImage.draw(batch, alpha);
        }else{
            unCheckImage.draw(batch, alpha);
        }
    }

    public void dispose(){
        checkImage = null;
        unCheckImage = null;
    }

    private boolean contains(int x, int y){
        return x >= this.x && x <= this.x + width && y >= this.y && y <= this.y + height;
    }
}
