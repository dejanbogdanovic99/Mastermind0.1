package com.sts.mastermind.gamePackage;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.sts.mastermind.Main;
import com.sts.mastermind.bundelPackage.DataBundle;
import com.sts.mastermind.guiPackage.BasicImage;
import com.sts.mastermind.guiPackage.Button;
import com.sts.mastermind.guiPackage.CheckButton;

public class SettingsState extends GameState {

    private final int OFFSET = 300;
    private final int MIN_ROWS = 4;
    private final int MAX_ROWS = 6;
    private final int MIN_SIGNS = 6;
    private final int MAX_SIGNS = 8;

    private Texture [] numberTextures;

    private Texture playMusicChecked;
    private Texture playMusicUnchecked;

    private Texture repeatSignsChecked;
    private Texture repeatSignsUnchecked;

    private Texture incUp;
    private Texture incDown;

    private Texture decUp;
    private Texture decDown;

    private Texture menuUp;
    private Texture menuDown;

    private Button incRowsButton;
    private Button decRowsButton;

    private Button incSignsButton;
    private Button decSignsButton;

    private Button menuButton;

    private BasicImage rowImage;
    private BasicImage signImage;

    private CheckButton musicButton;

    private CheckButton repeatSignsButton;


    public SettingsState(DataBundle bundle, float scaleX, float scaleY, int width, int height) {
        super(bundle, scaleX, scaleY, width, height);
    }

    @Override
    public void init() {

        float x = width / 2;

        float y = height - scaleY*OFFSET;

        musicButton = new CheckButton(playMusicChecked, playMusicUnchecked, bundle.getVolume(), x, y, scaleX, scaleY);

        y -= scaleY*OFFSET;

        repeatSignsButton = new CheckButton(repeatSignsChecked, repeatSignsUnchecked, bundle.getRepeatSigns(), x, y, scaleX, scaleY);

        y -= scaleY*OFFSET;

        x += scaleX*OFFSET/2;

        incRowsButton = new Button(incUp, incDown, x, y, scaleX, scaleY);

        x-= scaleX*OFFSET;

        decRowsButton = new Button(decUp, decDown, x, y, scaleX, scaleY);

        x = width / 2;

        rowImage = new BasicImage(numberTextures[bundle.getAmountOfRows()-4], x, y, scaleX, scaleY);

        y -= scaleY*OFFSET;

        x += scaleX*OFFSET/2;

        incSignsButton = new Button(incUp, incDown, x, y, scaleX, scaleY);

        x -= scaleX*OFFSET;

        decSignsButton = new Button(decUp, decDown, x, y, scaleX, scaleY);

        x = width / 2;

        signImage = new BasicImage(numberTextures[bundle.getAmountOfSigns()-4], x, y, scaleX, scaleY);

        y -= scaleY*OFFSET;

        menuButton = new Button(menuUp, menuDown, x, y, scaleX, scaleY);
    }

    @Override
    public void render(SpriteBatch batch, float alpha) {
        musicButton.draw(batch, alpha);
        repeatSignsButton.draw(batch, alpha);

        incRowsButton.draw(batch, alpha);
        decRowsButton.draw(batch, alpha);
        rowImage.draw(batch,alpha);

        incSignsButton.draw(batch,alpha);
        decSignsButton.draw(batch,alpha);
        signImage.draw(batch,alpha);

        menuButton.draw(batch, alpha);
    }

    @Override
    public void touchDown(int x, int y) {
        incRowsButton.handleDown(x,y);
        decRowsButton.handleDown(x,y);
        incSignsButton.handleDown(x,y);
        decSignsButton.handleDown(x,y);
        menuButton.handleDown(x,y);
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


        if(decRowsButton.handleUp(x,y)){
            if(bundle.getAmountOfRows() != MIN_ROWS){
                bundle.setAmountOfRows(bundle.getAmountOfRows()-1);
                rowImage.setTexture(numberTextures[bundle.getAmountOfRows()-4]);
            }
        }

        if(incRowsButton.handleUp(x,y)){
            if(bundle.getAmountOfRows() != MAX_ROWS){
                bundle.setAmountOfRows(bundle.getAmountOfRows()+1);
                rowImage.setTexture(numberTextures[bundle.getAmountOfRows()-4]);
            }
        }

        if(decSignsButton.handleUp(x,y)){
            if(bundle.getAmountOfSigns() != MIN_SIGNS){
                bundle.setAmountOfSigns(bundle.getAmountOfSigns()-1);
                signImage.setTexture(numberTextures[bundle.getAmountOfSigns()-4]);
            }
        }

        if(incSignsButton.handleUp(x,y)){
            if(bundle.getAmountOfSigns() != MAX_SIGNS){
                bundle.setAmountOfSigns(bundle.getAmountOfSigns()+1);
                signImage.setTexture(numberTextures[bundle.getAmountOfSigns()-4]);
            }
        }

        if(menuButton.handleUp(x,y)){
            if(listener != null){
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
        playMusicChecked = new Texture("music2.png");
        playMusicUnchecked = new Texture("music1.png");

        repeatSignsChecked = new Texture("signs2.png");
        repeatSignsUnchecked = new Texture("signs1.png");

        numberTextures = new Texture[5];
        for(int i = 0; i < 5;i++){
            numberTextures[i] = new Texture(Integer.toString(i+4)+".png");
        }

        incUp = new Texture("r1.png");
        incDown = new Texture("r2.png");

        decUp = new Texture("1.png");
        decDown = new Texture("2.png");

        menuUp = new Texture("menu1.png");
        menuDown = new Texture("menu2.png");

    }

    @Override
    protected void disposeTextures() {
        playMusicChecked.dispose();
        playMusicUnchecked.dispose();

        repeatSignsChecked.dispose();
        repeatSignsUnchecked.dispose();

        for(int i = 0; i < 5;i++){
            numberTextures[i].dispose();
        }

        incUp.dispose();
        incDown.dispose();

        decUp.dispose();
        decDown.dispose();

        menuUp.dispose();
        menuDown.dispose();

    }


}
