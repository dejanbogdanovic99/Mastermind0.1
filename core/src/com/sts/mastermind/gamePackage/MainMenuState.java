package com.sts.mastermind.gamePackage;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.sts.mastermind.bundelPackage.DataBundle;
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

    public MainMenuState(DataBundle bundle, float scaleX, float scaleY, int width, int height) {
        super(bundle, scaleX, scaleY, width, height);
    }

    @Override
    public void init() {
        initTextures();

        logoImage = new Image(logoTexture);


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

        }
        if(settings.handleUp(x,y)){

        }
        if(exit.handleUp(x,y)){
            if(listener != null){

            }
        }
    }

    @Override
    public void touchDragged(int x, int y) {
        play.handleDown(x,y);
        settings.handleDown(x,y);
        exit.handleDown(x,y);
    }

    @Override
    public void dispose() {
        disposeTextures();
        play.dispose();
        settings.dispose();
        exit.dispose();
    }

    private void initTextures(){
        playUp = new Texture("");
        playDown = new Texture("");

        settingsUp = new Texture("");
        settingsDown = new Texture("");

        exitUp = new Texture("");
        exitDown = new Texture("");

        logoTexture = new Texture("");
    }

    private void disposeTextures(){
        playUp.dispose();
        playDown.dispose();

        settingsUp.dispose();
        settingsDown.dispose();

        exitUp.dispose();
        exitDown.dispose();

        logoTexture.dispose();
    }


}
