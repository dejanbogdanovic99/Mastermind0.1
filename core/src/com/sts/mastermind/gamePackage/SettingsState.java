package com.sts.mastermind.gamePackage;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.sts.mastermind.Main;
import com.sts.mastermind.bundelPackage.DataBundle;
import com.sts.mastermind.guiPackage.CheckButton;

public class SettingsState extends GameState {

    private final int OFFSET = 100;


    private Texture playMusicChecked;
    private Texture playMusicUnchecked;

    private Texture repeatSignsChecked;
    private Texture repeatSignsUnchecked;




    private CheckButton musicButton;

    private CheckButton repeatSignsButton;


    public SettingsState(DataBundle bundle, float scaleX, float scaleY, int width, int height) {
        super(bundle, scaleX, scaleY, width, height);
    }

    @Override
    public void init() {
        super.init();

        float x = width / 2;

        float y = height - scaleY*OFFSET;

        musicButton = new CheckButton(playMusicChecked, playMusicUnchecked, bundle.getVolume(), x, y, scaleX, scaleY);

        y = height - 4*scaleY*OFFSET;

        repeatSignsButton = new CheckButton(repeatSignsChecked, repeatSignsUnchecked, bundle.getRepeatSigns(), x, y, scaleX, scaleY);
    }

    @Override
    public void render(SpriteBatch batch, float alpha) {
        musicButton.draw(batch, alpha);
        repeatSignsButton.draw(batch, alpha);
    }

    @Override
    public void touchDown(int x, int y) {

    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

    @Override
    public void touchUp(int x, int y) {
        if(musicButton.handleUp(x,y)){
            bundle.setVolume(musicButton.isChecked());
        }
        if(repeatSignsButton.handleUp(x,y)){
            bundle.setRepeatSigns(repeatSignsButton.isChecked());
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

        musicButton.dispose();
        repeatSignsButton.dispose();
    }

    @Override
    protected void initTextures() {
        playMusicChecked = new Texture("music2.png");
        playMusicUnchecked = new Texture("music1.png");

        repeatSignsChecked = new Texture("signs2.png");
        repeatSignsUnchecked = new Texture("signs1.png");

    }

    @Override
    protected void disposeTextures() {
        playMusicChecked.dispose();
        playMusicUnchecked.dispose();

        repeatSignsChecked.dispose();
        repeatSignsUnchecked.dispose();
    }


}
