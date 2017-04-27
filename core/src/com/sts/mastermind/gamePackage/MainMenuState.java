package com.sts.mastermind.gamePackage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.sts.mastermind.Main;
import com.sts.mastermind.bundelPackage.DataBundle;
import com.sts.mastermind.guiPackage.BasicImage;
import com.sts.mastermind.guiPackage.Button;

public class MainMenuState extends GameState {

    /*
        Sve texutre za meni

     */
    private Texture playUp;
    private Texture playDown;

    private Texture settingsUp;
    private Texture settingsDown;

    private Texture exitUp;
    private Texture exitDown;

    private Texture creditsUp;
    private Texture creditsDown;

    private Texture logoTexture;

    /*
        svi tasteri u meniju
     */

    private Button play;

    private Button settings;

    private Button exit;

    private Button credits;

    /*
        slika logoa
     */

    private BasicImage logoImage;

    /*
        objekat interfejsa, za promenu stanja
     */

    public MainMenuState(
            DataBundle bundle,
            float scaleX,
            float scaleY,
            int width,
            int height) {
        super(bundle, scaleX, scaleY, width, height);
    }

    @Override
    public void init() {

        float x = (width / 2);
        float y = height - scaleY*450;

        // pozicija logo slike je na standardnom ekranu sredina i 400 od gore

        logoImage = new BasicImage(logoTexture, x,y, scaleX*0.7f, scaleY*0.7f);

        y = height - scaleY*970;

        // pozicija play tastera je na standardnom ekranu sredina i 920 od gore
        play = new Button(playUp, playDown, x, y, scaleX, scaleY);

        y = height - scaleY*1190;

        // pozicija settings tastera je na standardnom ekranu sredina i 1170 od gore
        settings = new Button(settingsUp, settingsDown, x, y, scaleX, scaleY);

        y = height - scaleY*1410;

        //pozicija credits tastera ja na standardnom ekranu sredina i 1420 od gore

        credits = new Button(creditsUp,creditsDown,x,y, scaleX, scaleY);

        y = height - scaleY*1630;

        // pozicija exit tastera je na standardnom ekranu sredina i 1670 od gore
        exit = new Button(exitUp, exitDown, x, y, scaleX, scaleY);
    }

    @Override
    public void render(SpriteBatch batch, float alpha) {
        logoImage.draw(batch, alpha);
        play.draw(batch, alpha);
        settings.draw(batch, alpha);
        credits.draw(batch, alpha);
        exit.draw(batch, alpha);
    }

    @Override
    public void touchDown(int x, int y) {
        play.handleDown(x,y);
        settings.handleDown(x,y);
        credits.handleDown(x,y);
        exit.handleDown(x,y);
    }

    @Override
    public void touchUp(int x, int y) {
        if(play.handleUp(x,y)){
            if(listener != null){
                listener.playSound();
                listener.changeState(Main.PLAY_STATE);
            }
        }
        if(settings.handleUp(x,y)){
            if(listener != null){
                listener.playSound();
                listener.changeState(Main.SETTINGS_STATE);
            }
        }
        if(credits.handleUp(x,y))
            if(listener != null){
                listener.playSound();
                listener.changeState(Main.ABOUT_STATE);
            }

        if(exit.handleUp(x,y)){
            Gdx.app.exit();
        }
    }

    @Override
    public void backPressed() {
        Gdx.app.exit();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void initTextures(){
        playUp = new Texture("play1.png");
        playDown = new Texture("play2.png");

        settingsUp = new Texture("settings1.png");
        settingsDown = new Texture("settings2.png");

        exitUp = new Texture("exit1.png");
        exitDown = new Texture("exit2.png");

        creditsUp = new Texture("credits1.png");
        creditsDown = new Texture("credits2.png");

        logoTexture = new Texture("logo.png");
    }

    @Override
    protected void disposeTextures(){
        playUp.dispose();
        playDown.dispose();

        settingsUp.dispose();
        settingsDown.dispose();

        exitUp.dispose();
        exitDown.dispose();

        logoTexture.dispose();
    }


}
