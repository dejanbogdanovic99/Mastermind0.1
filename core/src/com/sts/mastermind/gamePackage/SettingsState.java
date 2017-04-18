package com.sts.mastermind.gamePackage;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.sts.mastermind.Main;
import com.sts.mastermind.bundelPackage.DataBundle;
import com.sts.mastermind.guiPackage.Button;
import com.sts.mastermind.guiPackage.CheckButton;

public class SettingsState extends GameState {

    private final int OFFSET = 100;
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

    private Image rowImage;
    private Image signImage;

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

        y -= 300;




        x += 100;

        incRowsButton = new Button(incUp, incDown, x, y, scaleX, scaleY);

        x-= 200;

        decRowsButton = new Button(decUp, decDown, x, y, scaleX, scaleY);

        x = width / 2;

        rowImage = new Image(numberTextures[bundle.getAmountOfRows()-4]);

        rowImage.setScale(scaleX,scaleY);

        x -= ((rowImage.getWidth()*scaleX)/2);
        y -= ((rowImage.getHeight()*scaleY)/2);
        rowImage.setPosition(x,y);
    }

    @Override
    public void render(SpriteBatch batch, float alpha) {
        musicButton.draw(batch, alpha);
        repeatSignsButton.draw(batch, alpha);
        incRowsButton.draw(batch, alpha);
        decRowsButton.draw(batch, alpha);
        rowImage.draw(batch,alpha);
    }

    @Override
    public void touchDown(int x, int y) {
        incRowsButton.handleDown(x,y);
        decRowsButton.handleDown(x,y);
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

        float xI = rowImage.getX();
        float yI = rowImage.getY();

        if(decRowsButton.handleUp(x,y)){
            if(bundle.getAmountOfRows() != MIN_ROWS){
                bundle.setAmountOfRows(bundle.getAmountOfRows()-1);
                rowImage = new Image(numberTextures[bundle.getAmountOfRows()-4]);
                rowImage.setScale(scaleX,scaleY);
                rowImage.setPosition(xI,yI);
            }
        }

        if(incRowsButton.handleUp(x,y)){
            if(bundle.getAmountOfRows() != MAX_ROWS){
                bundle.setAmountOfRows(bundle.getAmountOfRows()+1);
                rowImage = new Image(numberTextures[bundle.getAmountOfRows()-4]);
                rowImage.setScale(scaleX,scaleY);
                rowImage.setPosition(xI,yI);
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
    protected void initTextures() {
        playMusicChecked = new Texture("music2.png");
        playMusicUnchecked = new Texture("music1.png");

        repeatSignsChecked = new Texture("signs2.png");
        repeatSignsUnchecked = new Texture("signs1.png");

        numberTextures = new Texture[5];
        for(int i = 0; i < 5;i++){
            numberTextures[i] = new Texture(Integer.toString(i+4)+".png");
        }

        incUp = new Texture("1r.png");
        incDown = new Texture("2r.png");

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
