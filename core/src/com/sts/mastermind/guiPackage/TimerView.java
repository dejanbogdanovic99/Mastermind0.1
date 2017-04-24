package com.sts.mastermind.guiPackage;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class TimerView {

    private Image image;

    private Image bgImage;

    private float x;
    private float y;

    private float upY;

    private float scaleX;
    private float standardScaleY;

    private float maxTime;

    private float currentTime;

    private float currentScaleY;

    private boolean paused;

    private boolean finished;

    public TimerView(Texture texture, Texture bgTexture, float maxTime, float x, float y, float scaleX, float scaleY){


        bgImage = new Image(bgTexture);
        bgImage.setPosition(x,y);
        bgImage.setScale(scaleX, scaleY);


        image = new Image(texture);
        image.setPosition(x + 10*scaleX,y + 10*scaleY);
        image.setScale(scaleX, scaleY);


        this.x = x;
        this.y = y;

        this.upY = y + (10 + image.getHeight())*scaleY;

        this.scaleX = scaleX;
        this.standardScaleY = scaleY;
        this.currentScaleY = scaleY;

        this.maxTime = maxTime;

        paused = false;
        finished = false;

        currentTime = 0;
    }

    public void pause(){
        paused = true;
    }

    public void resume(){
        paused = false;
    }

    public void reset(){
        this.currentScaleY = standardScaleY;
        currentTime = 0;
        paused = false;
        finished = false;
    }

    public void update(float delta){
        if(!paused && !finished){
            currentTime += delta;

            if(currentTime >= maxTime){
                finished = true;
                currentTime = maxTime;
            }

            currentScaleY = ((maxTime - currentTime)/maxTime)*standardScaleY;

            float currentY = upY - image.getHeight()*currentScaleY;

            image.setScaleY(currentScaleY);

            image.setY(currentY);

        }

    }

    public boolean isFinished(){
        return finished;
    }

    public void draw(SpriteBatch batch, float alpha){
        bgImage.draw(batch, alpha);
        image.draw(batch,alpha);
    }


}
