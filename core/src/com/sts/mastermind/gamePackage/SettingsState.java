package com.sts.mastermind.gamePackage;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sts.mastermind.Main;
import com.sts.mastermind.bundelPackage.DataBundle;
import com.sts.mastermind.guiPackage.BasicImage;
import com.sts.mastermind.guiPackage.Button;
import com.sts.mastermind.guiPackage.CheckButton;

public class SettingsState extends GameState {

    private final int OFFSET = 300;
    private final int MIN_COLUMNS = 4;
    private final int MAX_COLUMNS = 6;
    private final int MIN_SIGNS = 6;
    private final int MAX_SIGNS = 8;
    private final int MIN_TIMER_VALUE = 0;
    private final int MAX_TIMER_VALUE = 4;


    /**
     * kad se cekira sviramo muziku i zvuke
     */

    private Texture soundsChecked;
    private Texture soundsUnchecked;

    private CheckButton soundsButton;

    /**
     *  kad se cekira ponavljaju se znaci
     */

    private Texture repeatSignsChecked;
    private Texture repeatSignsUnchecked;

    private CheckButton repeatSignsButton;


    /**
     * biranje kolona koliko ima
     */

    private Texture columnTexture;

    private Button incColumnButton;
    private Button decColumnButton;

    private BasicImage columnNumber;

    private BasicImage columnImage;

    /**
     * biranje znakova
     */

    private Texture signTexture;

    private Button incSignsButton;
    private Button decSignsButton;

    private BasicImage signNumber;

    private BasicImage signImage;

    /**
     * tajmer
     */

    private Texture timerTexture;

    private Button incTimerButton;
    private Button decTimerButton;

    private BasicImage timerNumber;

    private BasicImage timerImage;

    /**
     * meni taster
     */

    private Texture menuUp;
    private Texture menuDown;

    private Button menuButton;


    /**
     * ostale teksture
     */


    private Texture incUp;
    private Texture incDown;

    private Texture decUp;
    private Texture decDown;

    private Texture [] numberTextures;

    private Texture [] timerNumberTextures;


    public SettingsState(DataBundle bundle, float scaleX, float scaleY, int width, int height) {
        super(bundle, scaleX, scaleY, width, height);
    }

    @Override
    public void init() {


        soundsButton = new CheckButton(soundsChecked, soundsUnchecked, bundle.isSounds(), width / 2, height - scaleY*250, scaleX, scaleY);


        repeatSignsButton = new CheckButton(repeatSignsChecked, repeatSignsUnchecked, bundle.isRepeatSigns(), width / 2, height - scaleY*500, scaleX, scaleY);



        columnImage = new BasicImage(columnTexture, width/2 - 200*scaleX, height - 750*scaleY, scaleX, scaleY);

        incColumnButton = new Button(incUp, incDown, width/2 + 325*scaleX, height - 750*scaleY, scaleX, scaleY);

        decColumnButton = new Button(decUp, decDown, width/2 + 75*scaleX, height - 750*scaleY, scaleX, scaleY);

        columnNumber = new BasicImage(numberTextures[bundle.getAmountOfColumns()-4], width/2 + 200*scaleX, height - 750*scaleY, scaleX, scaleY);



        signImage = new BasicImage(signTexture, width/2 - 200*scaleX, height - 1000*scaleY, scaleX , scaleY);

        incSignsButton = new Button(incUp, incDown, width/2 + 325*scaleX, height - 1000*scaleY, scaleX, scaleY);

        decSignsButton = new Button(decUp, decDown, width/2 + 75*scaleX, height - 1000*scaleY, scaleX, scaleY);

        signNumber = new BasicImage(numberTextures[bundle.getAmountOfSigns()-4], width/2 + 200*scaleX, height - 1000*scaleY, scaleX, scaleY);



        timerImage = new BasicImage(timerTexture, width/2 - 200*scaleX, height - 1250*scaleY, scaleX, scaleY);

        incTimerButton = new Button(incUp, incDown, width/2 + 325*scaleX, height - 1250*scaleY, scaleX, scaleY);

        decTimerButton = new Button(decUp, decDown, width/2 + 75*scaleX, height - 1250*scaleY, scaleX, scaleY);

        timerNumber = new BasicImage(timerNumberTextures[bundle.getTimerValue()], width/2 + 200*scaleX, height - 1250*scaleY, scaleX, scaleY);



        menuButton = new Button(menuUp, menuDown, width / 2, height - 1650*scaleY, scaleX, scaleY);

    }

    @Override
    public void render(SpriteBatch batch, float alpha) {
        soundsButton.draw(batch, alpha);

        repeatSignsButton.draw(batch, alpha);

        columnImage.draw(batch, alpha);
        incColumnButton.draw(batch, alpha);
        decColumnButton.draw(batch, alpha);
        columnNumber.draw(batch,alpha);

        signImage.draw(batch, alpha);
        incSignsButton.draw(batch, alpha);
        decSignsButton.draw(batch, alpha);
        signNumber.draw(batch, alpha);

        timerImage.draw(batch, alpha);
        incTimerButton.draw(batch, alpha);
        decTimerButton.draw(batch, alpha);
        timerNumber.draw(batch, alpha);

        menuButton.draw(batch, alpha);
    }

    @Override
    public void touchDown(int x, int y) {
        incColumnButton.handleDown(x,y);
        decColumnButton.handleDown(x,y);

        incSignsButton.handleDown(x,y);
        decSignsButton.handleDown(x,y);

        incTimerButton.handleDown(x,y);
        decTimerButton.handleDown(x,y);

        menuButton.handleDown(x,y);
    }


    @Override
    public void touchUp(int x, int y) {
        if(soundsButton.handleUp(x,y)){
            if(listener != null){
                listener.playSound();
            }
            bundle.setSounds(soundsButton.isChecked());

            if(soundsButton.isChecked()){
                listener.playMusic();
            }else{
                listener.stopMusic();
            }
        }

        if(repeatSignsButton.handleUp(x,y)){
            if(listener != null){
                listener.playSound();
            }
            bundle.setRepeatSigns(repeatSignsButton.isChecked());
        }


        if(decColumnButton.handleUp(x,y)){

            if(listener != null){
                listener.playSound();
            }

            if(bundle.getAmountOfColumns() != MIN_COLUMNS){
                bundle.setAmountOfColumns(bundle.getAmountOfColumns()-1);
                columnNumber.setTexture(numberTextures[bundle.getAmountOfColumns()-4]);
            }
        }


        if(incColumnButton.handleUp(x,y)){

            if(listener != null){
                listener.playSound();
            }

            if(bundle.getAmountOfColumns() != MAX_COLUMNS){
                bundle.setAmountOfColumns(bundle.getAmountOfColumns()+1);
                columnNumber.setTexture(numberTextures[bundle.getAmountOfColumns()-4]);
            }
        }


        if(decSignsButton.handleUp(x,y)){

            if(listener != null){
                listener.playSound();
            }

            if(bundle.getAmountOfSigns() != MIN_SIGNS){
                bundle.setAmountOfSigns(bundle.getAmountOfSigns()-1);
                signNumber.setTexture(numberTextures[bundle.getAmountOfSigns()-4]);
            }
        }


        if(incSignsButton.handleUp(x,y)){

            if(listener != null){
                listener.playSound();
            }

            if(bundle.getAmountOfSigns() != MAX_SIGNS){
                bundle.setAmountOfSigns(bundle.getAmountOfSigns()+1);
                signNumber.setTexture(numberTextures[bundle.getAmountOfSigns()-4]);
            }
        }


        if(decTimerButton.handleUp(x, y)){

            if(listener != null){
                listener.playSound();
            }

            if(bundle.getTimerValue() != MIN_TIMER_VALUE){
                bundle.setTimerValue(bundle.getTimerValue()-1);
                timerNumber.setTexture(timerNumberTextures[bundle.getTimerValue()]);
            }
        }


        if(incTimerButton.handleUp(x, y)){

            if(listener != null){
                listener.playSound();
            }

            if(bundle.getTimerValue() != MAX_TIMER_VALUE){
                bundle.setTimerValue(bundle.getTimerValue()+1);
                timerNumber.setTexture(timerNumberTextures[bundle.getTimerValue()]);
            }
        }

        if(menuButton.handleUp(x,y)){
            if(listener != null){
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
        soundsChecked = new Texture("sounds2.png");
        soundsUnchecked = new Texture("sounds1.png");

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

        timerTexture = new Texture("timer.png");

        timerNumberTextures = new Texture[5];
        timerNumberTextures[DataBundle.INFINITE] = new Texture("inf.png");
        timerNumberTextures[DataBundle.T_30S] = new Texture("30s.png");
        timerNumberTextures[DataBundle.T_1M] = new Texture("1m.png");
        timerNumberTextures[DataBundle.T_2M] = new Texture("2m.png");
        timerNumberTextures[DataBundle.T_5M] = new Texture("5m.png");

    }

    @Override
    protected void disposeTextures() {
        soundsChecked.dispose();
        soundsUnchecked.dispose();

        repeatSignsChecked.dispose();
        repeatSignsUnchecked.dispose();

        for(int i = 0; i < 5;i++){
            numberTextures[i].dispose();
            timerNumberTextures[i].dispose();
        }

        incUp.dispose();
        incDown.dispose();

        decUp.dispose();
        decDown.dispose();

        menuUp.dispose();
        menuDown.dispose();

        signTexture.dispose();
        columnTexture.dispose();

        timerTexture.dispose();
    }


}
