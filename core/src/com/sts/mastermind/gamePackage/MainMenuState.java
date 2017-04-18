package com.sts.mastermind.gamePackage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.sts.mastermind.Main;
import com.sts.mastermind.bundelPackage.DataBundle;
import com.sts.mastermind.guiPackage.Button;

public class MainMenuState extends GameState {

    private static final int OFFSET = 60;

    /*
        Sve texutre za meni

     */
    private Texture playUp;
    private Texture playDown;

    private Texture settingsUp;
    private Texture settingsDown;

    private Texture exitUp;
    private Texture exitDown;

    private Texture logoTexture;

    /*
        svi tasteri u meniju
     */

    private Button play;

    private Button settings;

    private Button exit;

    /*
        slika logoa
     */

    private Image logoImage;

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

        float x = (width / 2) - scaleX*logoTexture.getWidth()/2;
        float y = height - scaleY*logoTexture.getHeight();

        logoImage = new Image(logoTexture);

        logoImage.setScale(scaleX,scaleY);
        logoImage.setPosition(x,y);

        x = width /2;
        y -= OFFSET*scaleY;

        play = new Button(playUp, playDown, x, y, scaleX, scaleY);

        y -= (OFFSET*scaleY+playUp.getHeight()*scaleY);

        settings = new Button(settingsUp, settingsDown, x, y, scaleX, scaleY);

        y -= (OFFSET*scaleY+settingsUp.getHeight()*scaleY);

        exit = new Button(exitUp, exitDown, x, y, scaleX, scaleY);
    }

    @Override
    public void render(SpriteBatch batch, float alpha) {
        logoImage.draw(batch, alpha);
        play.draw(batch, alpha);
        settings.draw(batch, alpha);
        exit.draw(batch, alpha);
    }

    @Override
    public void touchDown(int x, int y) {
        play.handleDown(x,y);
        settings.handleDown(x,y);
        exit.handleDown(x,y);
    }

    @Override
    public void touchUp(int x, int y) {
        if(play.handleUp(x,y)){
            if(listener != null){
                listener.changeState(Main.PLAY_STATE);
            }
        }
        if(settings.handleUp(x,y)){
            if(listener != null){
                listener.changeState(Main.SETTINGS_STATE);
            }
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
