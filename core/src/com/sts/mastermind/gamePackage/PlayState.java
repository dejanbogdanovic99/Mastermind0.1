package com.sts.mastermind.gamePackage;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sts.mastermind.bundelPackage.DataBundle;
import com.sts.mastermind.combinationPackage.Combination;
import com.sts.mastermind.guiPackage.ColorButton;
import com.sts.mastermind.guiPackage.ColorView;
import com.sts.mastermind.listenerPackage.ChangeState;
import com.sts.mastermind.listenerPackage.LoadCombination;

import java.util.ArrayList;
import java.util.Random;

public class PlayState extends GameState {



    /**
     * konstante
     */


    public static final int NO_SIGN = -1;
    public static final int CLUB_CODE = 0;
    public static final int SPADES_CODE = 1;
    public static final int HEART_CODE = 2;
    public static final int DIAMOND_CODE = 3;
    public static final int BULB_CODE = 4;
    public static final int STAR_CODE = 5;
    public static final int FLOWER_CODE = 6;
    public static final int GEAR_CODE = 7;

    /**
     * texture
     */

    private Texture [] signs;

    private Texture signBack;

    private Texture fullHit;
    private Texture halfHit;

    /**
     * kombinacija
     */

    private Combination combination;

    /**
     * tasteri za boje
     */

    private ColorButton [] colorButtons;

    /**
     *  listener za
     */



    private LoadCombination loadListener = null;

    public PlayState(DataBundle bundle, float scaleX, float scaleY, int width, int height) {
        super(bundle, scaleX, scaleY, width, height);
    }

    @Override
    public void init() {
        super.init();

        initColorViews();
    }

    @Override
    public void render(SpriteBatch batch, float alpha) {
        for(int i = 0; i < bundle.getAmountOfColors();i++){
            s
        }
    }

    @Override
    public void touchDown(int x, int y) {

    }

    @Override
    public void touchUp(int x, int y) {

    }

    @Override
    public void dispose() {
        super.dispose();

        for(ColorButton colorButton:colorButtons){
            colorButton.dispose();
        }
    }

    public void setLoadListener(LoadCombination loadListener){
        this.loadListener = loadListener;
    }

    @Override
    protected void initTextures() {
        signs = new Texture[8];
        signs[CLUB_CODE] = new Texture("club.png");
        signs[SPADES_CODE] = new Texture("spades.png");
        signs[HEART_CODE] = new Texture("heart.png");
        signs[DIAMOND_CODE] = new Texture("diamond.png");
        signs[BULB_CODE] = new Texture("bulb.png");
        signs[STAR_CODE] = new Texture("star.png");
        signs[FLOWER_CODE] = new Texture("flower.png");
        signs[GEAR_CODE] = new Texture("gear.png");

        signBack = new Texture("back.png");

        fullHit = new Texture("full.png");

        halfHit = new Texture("half.png");
    }

    @Override
    protected void disposeTextures() {
        for(Texture sign:signs){
            sign.dispose();
        }
        signBack.dispose();
        fullHit.dispose();
        halfHit.dispose();
    }


    private void initColorViews(){
        colorButtons = new ColorButton[8];

        float x = width / 8;
        float y = 100;

        colorButtons[CLUB_CODE] = new ColorButton(
                signs[CLUB_CODE],
                x,
                y,
                scaleX,
                scaleY
        );

        x += width / 8;

        colorButtons[SPADES_CODE] = new ColorButton(
                signs[SPADES_CODE],
                x,
                y,
                scaleX,
                scaleY
        );

        x += width / 8;

        colorButtons[HEART_CODE] = new ColorButton(
                signs[HEART_CODE],
                x,
                y,
                scaleX,
                scaleY
        );

        x += width / 8;

        colorButtons[DIAMOND_CODE] = new ColorButton(
                signs[DIAMOND_CODE],
                x,
                y,
                scaleX,
                scaleY
        );

        x += width / 8;

        colorButtons[BULB_CODE] = new ColorButton(
                signs[BULB_CODE],
                x,
                y,
                scaleX,
                scaleY
        );

        x += width / 8;

        colorButtons[STAR_CODE] = new ColorButton(
                signs[STAR_CODE],
                x,
                y,
                scaleX,
                scaleY
        );

        x += width / 8;

        colorButtons[FLOWER_CODE] = new ColorButton(
                signs[FLOWER_CODE],
                x,
                y,
                scaleX,
                scaleY
        );

        x += width / 8;

        colorButtons[GEAR_CODE] = new ColorButton(
                signs[GEAR_CODE],
                x,
                y,
                scaleX,
                scaleY
        );
    }

}
