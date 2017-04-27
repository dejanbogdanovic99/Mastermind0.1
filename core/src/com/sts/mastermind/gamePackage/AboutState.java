package com.sts.mastermind.gamePackage;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sts.mastermind.Main;
import com.sts.mastermind.bundelPackage.DataBundle;
import com.sts.mastermind.guiPackage.BasicImage;
import com.sts.mastermind.guiPackage.Button;

public class AboutState extends GameState {


    /**
     * slicica za logo
     */

    private Texture creditTexture;

    private BasicImage creditImage;


    /**
     * meni taster
     */

    private Texture menuUp;
    private Texture menuDown;

    private Button menu;



    public AboutState(DataBundle bundle, float scaleX, float scaleY, int width, int height) {
        super(bundle, scaleX, scaleY, width, height);
    }

    @Override
    public void init() {

        creditImage = new BasicImage(creditTexture, width / 2, height / 2, scaleX, scaleY);


        menu = new Button(menuUp, menuDown, width / 2, height - 1650*scaleY, scaleX, scaleY);

    }

    @Override
    public void render(SpriteBatch batch, float alpha) {

        creditImage.draw(batch, alpha);

        menu.draw(batch, alpha);
    }

    @Override
    public void touchDown(int x, int y) {
        menu.handleDown(x,y);
    }

    @Override
    public void touchUp(int x, int y) {
        if(menu.handleUp(x,y)){
            if(listener != null) {
                listener.playSound();
                listener.changeState(Main.MAIN_MENU_STATE);
            }
        }
    }

    @Override
    public void backPressed() {
        if(listener != null) {
            listener.changeState(Main.MAIN_MENU_STATE);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void initTextures() {
        menuUp = new Texture("menu1.png");
        menuDown = new Texture("menu2.png");

        creditTexture = new Texture("credits.png");
    }

    @Override
    protected void disposeTextures() {
        menuUp.dispose();
        menuDown.dispose();

        creditTexture.dispose();
    }
}
