package com.sts.mastermind.gamePackage;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.sts.mastermind.Main;
import com.sts.mastermind.bundelPackage.DataBundle;
import com.sts.mastermind.guiPackage.BasicImage;
import com.sts.mastermind.guiPackage.Button;
import com.sts.mastermind.guiPackage.CheckButton;
import com.sts.mastermind.listenerPackage.MusicManager;

public class SettingsState extends GameState {

    private final int OFFSET = 300;
    private final int MIN_ROWS = 4;
    private final int MAX_ROWS = 6;
    private final int MIN_SIGNS = 6;
    private final int MAX_SIGNS = 8;
    private final int MAX_BGCOLOR = 4;
    private final int MIN_BGCOLOR = 0;

    private Texture [] numberTextures;

    private Texture playMusicChecked;
    private Texture playMusicUnchecked;

    private Texture repeatSignsChecked;
    private Texture repeatSignsUnchecked;

    private Texture signTexture;
    private Texture columnTexture;

    private Texture incUp;
    private Texture incDown;

    private Texture decUp;
    private Texture decDown;

    private Texture menuUp;
    private Texture menuDown;

    private Texture[] bgColors;

    private Button incColorButton;
    private Button decColorButton;

    private Button incRowsButton;
    private Button decRowsButton;

    private Button incSignsButton;
    private Button decSignsButton;

    private Button menuButton;

    private BasicImage rowNumber;
    private BasicImage signNumber;

    private BasicImage rowImage;
    private BasicImage signImage;

    private BasicImage colorImage;

    private CheckButton musicButton;

    private CheckButton repeatSignsButton;



    private MusicManager musicManager = null;


    public SettingsState(DataBundle bundle, float scaleX, float scaleY, int width, int height) {
        super(bundle, scaleX, scaleY, width, height);
    }

    public void setMusicManager(MusicManager musicManager){
        this.musicManager = musicManager;
    }

    @Override
    public void init() {

        float x = width / 2;

        float y = height - scaleY*OFFSET;

        musicButton = new CheckButton(playMusicChecked, playMusicUnchecked, bundle.getVolume(), x, y, scaleX, scaleY);

        y -= scaleY*OFFSET;

        repeatSignsButton = new CheckButton(repeatSignsChecked, repeatSignsUnchecked, bundle.getRepeatSigns(), x, y, scaleX, scaleY);

        y -= scaleY*OFFSET;

        x = width/2 - 200*scaleX;

        rowImage = new BasicImage(columnTexture, x , y , scaleX, scaleY);

        x = width/2 + 325*scaleX;

        incRowsButton = new Button(incUp, incDown, x, y, scaleX, scaleY);

        x = width/2 + 75*scaleX;

        decRowsButton = new Button(decUp, decDown, x, y, scaleX, scaleY);

        x = width/2 + 200*scaleX;

        rowNumber = new BasicImage(numberTextures[bundle.getAmountOfRows()-4], x, y, scaleX, scaleY);

        y -= scaleY*OFFSET;

        x = width/2 - 200*scaleX;

        signImage = new BasicImage(signTexture, x, y, scaleX , scaleY);

        x = width/2 + 325*scaleX;

        incSignsButton = new Button(incUp, incDown, x, y, scaleX, scaleY);

        x = width/2 + 75*scaleX;

        decSignsButton = new Button(decUp, decDown, x, y, scaleX, scaleY);

        x = width/2 + 200*scaleX;

        signNumber = new BasicImage(numberTextures[bundle.getAmountOfSigns()-4], x, y, scaleX, scaleY);

        x = width / 2;

        y -= scaleY*OFFSET;

        colorImage = new BasicImage(bgColors[bundle.getBgColor()],x,y,scaleX,scaleY);

        x = width/2 - 171*scaleX;

        decColorButton = new Button(decUp, decDown, x, y, scaleX, scaleY);

        x = width/2 + 171*scaleX;

        incColorButton = new Button(incUp, incDown, x, y, scaleX, scaleY);

        x = width / 2;

        y -= scaleY*OFFSET;

        menuButton = new Button(menuUp, menuDown, x, y, scaleX, scaleY);

    }

    @Override
    public void render(SpriteBatch batch, float alpha) {
        musicButton.draw(batch, alpha);
        repeatSignsButton.draw(batch, alpha);

        rowImage.draw(batch, alpha);
        incRowsButton.draw(batch, alpha);
        decRowsButton.draw(batch, alpha);
        rowNumber.draw(batch,alpha);

        signImage.draw(batch,alpha);
        incSignsButton.draw(batch,alpha);
        decSignsButton.draw(batch,alpha);
        signNumber.draw(batch,alpha);

        menuButton.draw(batch, alpha);

        colorImage.draw(batch, alpha);
        incColorButton.draw(batch, alpha);
        decColorButton.draw(batch, alpha);
    }

    @Override
    public void touchDown(int x, int y) {
        incRowsButton.handleDown(x,y);
        decRowsButton.handleDown(x,y);
        incSignsButton.handleDown(x,y);
        decSignsButton.handleDown(x,y);
        menuButton.handleDown(x,y);
        incColorButton.handleUp(x, y);
        decColorButton.handleUp(x, y);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

    @Override
    public void touchUp(int x, int y) {
        if(musicButton.handleUp(x,y)){
            bundle.setVolume(musicButton.isChecked());
            if(musicButton.isChecked()){
                musicManager.playMusic();
            }else{
                musicManager.stopMusic();
            }
        }
        if(repeatSignsButton.handleUp(x,y)){
            bundle.setRepeatSigns(repeatSignsButton.isChecked());
        }


        if(decRowsButton.handleUp(x,y)){
            if(bundle.getAmountOfRows() != MIN_ROWS){
                bundle.setAmountOfRows(bundle.getAmountOfRows()-1);
                rowNumber.setTexture(numberTextures[bundle.getAmountOfRows()-4]);
            }
        }

        if(incRowsButton.handleUp(x,y)){
            if(bundle.getAmountOfRows() != MAX_ROWS){
                bundle.setAmountOfRows(bundle.getAmountOfRows()+1);
                rowNumber.setTexture(numberTextures[bundle.getAmountOfRows()-4]);
            }
        }

        if(decSignsButton.handleUp(x,y)){
            if(bundle.getAmountOfSigns() != MIN_SIGNS){
                bundle.setAmountOfSigns(bundle.getAmountOfSigns()-1);
                signNumber.setTexture(numberTextures[bundle.getAmountOfSigns()-4]);
            }
        }

        if(incSignsButton.handleUp(x,y)){
            if(bundle.getAmountOfSigns() != MAX_SIGNS){
                bundle.setAmountOfSigns(bundle.getAmountOfSigns()+1);
                signNumber.setTexture(numberTextures[bundle.getAmountOfSigns()-4]);
            }
        }
        if(incColorButton.handleUp(x, y)){
            if(bundle.getBgColor() != MAX_BGCOLOR){
                bundle.setBgColor(bundle.getBgColor()+1);
                colorImage.setTexture(bgColors[bundle.getBgColor()]);
            }
        }
        if(decColorButton.handleUp(x, y)){
            if(bundle.getBgColor() != MIN_BGCOLOR){
                bundle.setBgColor(bundle.getBgColor()-1);
                colorImage.setTexture(bgColors[bundle.getBgColor()]);
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

        signTexture = new Texture("signs.png");
        columnTexture = new Texture("columns.png");

        bgColors = new Texture[5];
        bgColors[0] = new Texture("red.png");
        bgColors[1] = new Texture("blue.png");
        bgColors[2] = new Texture("white.png");
        bgColors[3] = new Texture("yellow.png");
        bgColors[4] = new Texture("green.png");

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

        signTexture.dispose();
        columnTexture.dispose();
    }


}
