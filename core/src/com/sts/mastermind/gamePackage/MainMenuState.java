package com.sts.mastermind.gamePackage;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.sts.mastermind.bundelPackage.DataBundle;
import com.sts.mastermind.utils.Button;

public class MainMenuState extends GameState {

    /*
        Sve texutre za meni

     */
    private Texture playSignsUp;
    private Texture playSignsDown;

    private Texture playColorsUp;
    private Texture playColorsDown;

    private Texture settingsUp;
    private Texture settingsDown;

    private Texture exitUp;
    private Texture exitDown;

    private Texture logoImageTexture;

    /*
        svi tasteri u meniju
     */

    private Button playSigns;

    private Button playColors;

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

    }

    @Override
    public void render(SpriteBatch batch, float alpha) {
        logoImage.draw(batch, alpha);
        playSigns.draw(batch, alpha);
        playColors.draw(batch, alpha);
        settings.draw(batch, alpha);
        exit.draw(batch, alpha);
    }

    @Override
    public void touchDown(int x, int y) {
        playSigns.handleDown(x,y);
        playColors.handleDown(x,y);
        settings.handleDown(x,y);
        exit.handleDown(x,y);
    }

    @Override
    public void touchUp(int x, int y) {
        if(playSigns.handleUp(x,y)){

        }
        if(playColors.handleUp(x,y)){

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
        playSigns.handleDown(x,y);
        playColors.handleDown(x,y);
        settings.handleDown(x,y);
        exit.handleDown(x,y);
    }

    @Override
    public void dispose() {
        disposeTextures();
        playSigns.dispose();
        playColors.dispose();
        settings.dispose();
        exit.dispose();
    }

    private void initTextures(){
        playSignsUp = new Texture("");
        playSignsDown = new Texture("");

        playColorsUp = new Texture("");
        playColorsDown = new Texture("");

        settingsUp = new Texture("");
        settingsDown = new Texture("");

        exitUp = new Texture("");
        exitDown = new Texture("");

        logoImageTexture = new Texture("");
    }

    private void disposeTextures(){
        playSignsUp.dispose();
        playSignsDown.dispose();

        playColorsUp.dispose();
        playColorsDown.dispose();

        settingsUp.dispose();
        settingsDown.dispose();

        exitUp.dispose();
        exitDown.dispose();

        logoImageTexture.dispose();
    }


}
