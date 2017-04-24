package com.sts.mastermind.guiPackage;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class TimerView {

    private Image image;

    private float x;
    private float y;

    private float scaleX;
    private float standardScaleY;

    private float maxTime;

    private float currentTime;

    private float currentScaleY;

    private boolean paused;

    private boolean finished;

    public TimerView(Texture texture, float maxTime, float x, float y, float scaleX, float scaleY){
        image = new Image(texture);
        image.setPosition(x,y);

        image.setScale(scaleX, scaleY);
        this.x = x;
        this.y = y;

        this.scaleX = scaleX;
        this.standardScaleY = scaleY;
        this.currentScaleY = scaleY;

        this.maxTime = maxTime;

        paused = false;
        finished = false;
    }

    public void pause(){
        paused = true;
    }

    public void start(){
        paused = false;
    }

    public void update(float delta){
        if(!paused && !finished){
            currentTime += delta;

            if(currentTime >= maxTime){
                finished = true;
                currentTime = maxTime;
            }

            currentScaleY = ((maxTime - currentTime)/maxTime)*standardScaleY;

            image.setScaleY(currentScaleY);

        }

    }

    public boolean isFinished(){
        return finished;
    }

    public void draw(SpriteBatch batch, float alpha){
        image.draw(batch,alpha);
    }


}
