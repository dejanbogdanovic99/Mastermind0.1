package com.sts.mastermind.gamePackage;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sts.mastermind.Main;
import com.sts.mastermind.bundelPackage.DataBundle;

public class AboutState extends GameState {




    public AboutState(DataBundle bundle, float scaleX, float scaleY, int width, int height) {
        super(bundle, scaleX, scaleY, width, height);
    }

    @Override
    public void init() {

    }

    @Override
    public void render(SpriteBatch batch, float alpha) {

    }

    @Override
    public void touchDown(int x, int y) {

    }

    @Override
    public void touchUp(int x, int y) {

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

    }

    @Override
    protected void disposeTextures() {
        super.disposeTextures();
    }
}
