package com.sts.mastermind.gamePackage;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.sts.mastermind.Main;
import com.sts.mastermind.bundelPackage.DataBundle;
import com.sts.mastermind.combinationPackage.Combination;
import com.sts.mastermind.guiPackage.Button;
import com.sts.mastermind.guiPackage.ColorButton;
import com.sts.mastermind.guiPackage.CombinationView;

import java.util.ArrayList;
import java.util.List;

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

    private Texture menuUp;
    private Texture menuDown;

    private Texture newUp;
    private Texture newDown;

    private Texture checkUp;
    private Texture checkDown;

    /**
     * kombinacija
     */

    private Combination combination;

    /**
     * tasteri za boje
     */

    private CombinationView combinationView;

    private ColorButton [] colorButtons;

    private Button menuButton;

    private Button newButton;
    /**
     * broj boja
     */
    private int signsToGuess = 5;
    /**
     * broj pokusaja
    */
    private int attempts = 0;
    private int maxAttempts = 6;

    /**
     * Uneta kombinacija
     */
    private List<ColorButton> enteredCombination = new ArrayList<ColorButton>();
    private List<Image> checkedCombinations = new ArrayList<Image>();




    public PlayState(DataBundle bundle, float scaleX, float scaleY, int width, int height) {
        super(bundle, scaleX, scaleY, width, height);
    }

    @Override
    public void init() {
        super.init();

        initColorButtons();

        float x = 200*scaleX;
        float y = 100*scaleY;

        menuButton = new Button(menuUp, menuDown, x, y, scaleX, scaleY);

        x = width - 200*scaleX;

        newButton = new Button(newUp, newDown, x, y, scaleX, scaleY);

        combinationView = new CombinationView(bundle.getAmountOfRows(), signBack, scaleX, scaleY);
        combinationView.setPosition(100, 900);
    }

    @Override
    public void render(SpriteBatch batch, float alpha) {

        menuButton.draw(batch, alpha);

        newButton.draw(batch, alpha);

        combinationView.draw(batch, alpha);

        for(int i = 0; i < bundle.getAmountOfSigns();i++){
            colorButtons[i].draw(batch, alpha);
        }
        for(int i = 0; i < enteredCombination.size();i++){
            enteredCombination.get(i).draw(batch, alpha);
        }
        for(int i = 0; i < checkedCombinations.size();i++){
            checkedCombinations.get(i).draw(batch, alpha);
        }
    }

    @Override
    public void touchDown(int x, int y) {

        menuButton.handleDown(x,y);
        newButton.handleDown(x,y);

        for(int i = 0;i < colorButtons.length;i++){
            if(i == GEAR_CODE){
                if (colorButtons[i].isPressed(x, y) && enteredCombination.size() == signsToGuess){
                    /**
                     * Ovde treba da ide provera da li je tacna kombinacija
                     */
                    for(ColorButton button:enteredCombination){
                        checkedCombinations.add(new Image(signs[button.getTextureCode()]));
                        checkedCombinations.get(checkedCombinations.size()-1).setX(button.getX());
                        checkedCombinations.get(checkedCombinations.size()-1).setY(button.getY());
                        checkedCombinations.get(checkedCombinations.size()-1).setScale(scaleX,scaleY);
                        attempts++;
                    }
                    for(ColorButton colorButton:enteredCombination){
                        colorButton.dispose();
                    }
                    enteredCombination.clear();
                }
            }else if(colorButtons[i].isPressed(x, y) && enteredCombination.size() < signsToGuess)
                enteredCombination.add(new ColorButton(signs[colorButtons[i].getTextureCode()],
                        colorButtons[i].getTextureCode(),
                        width / 12 + width / 12 * enteredCombination.size(),
                        height * scaleY - height / 50 - (height / 50) * attempts,
                        scaleX, scaleY));
        }
        if(enteredCombination.size()>0) {
            if (enteredCombination.get(enteredCombination.size() - 1).isPressed(x, y))
                enteredCombination.remove(enteredCombination.size() - 1);
        }
    }

    @Override
    public void touchUp(int x, int y) {
        if(menuButton.handleUp(x,y)){
            if(listener != null){
                listener.changeState(Main.MAIN_MENU_STATE);
            }
        }

        if(newButton.handleUp(x,y)){

        }

        combinationView.handleInput(x,y);
        for(int i = 0; i < bundle.getAmountOfSigns();i++){
            if(colorButtons[i].isPressed(x,y)){
                int index = combinationView.getCombination().getFirstEmpty();
                if(index != -1){
                    combinationView.setSign(index, signs[i], i);
                }
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

        for(ColorButton colorButton:colorButtons){
            colorButton.dispose();
        }
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

        menuUp = new Texture("menu1.png");
        menuDown = new Texture("menu2.png");

        newUp = new Texture("new1.png");
        newDown = new Texture("new2.png");

        checkUp = new Texture("check1.png");
        checkDown = new Texture("check2.png");
    }

    @Override
    protected void disposeTextures() {
        for(Texture sign:signs){
            sign.dispose();
        }
        signBack.dispose();
        fullHit.dispose();
        halfHit.dispose();

        menuUp.dispose();
        menuDown.dispose();

        newUp.dispose();
        newDown.dispose();

        checkUp.dispose();
        checkDown.dispose();
    }


    private void initColorButtons(){
        colorButtons = new ColorButton[8];

        float x = width / 9;
        float y = 250*scaleY;

        colorButtons[CLUB_CODE] = new ColorButton(
                signs[CLUB_CODE],
                CLUB_CODE,
                x,
                y,
                scaleX,
                scaleY
        );

        x += width / 9;

        colorButtons[SPADES_CODE] = new ColorButton(
                signs[SPADES_CODE],
                SPADES_CODE,
                x,
                y,
                scaleX,
                scaleY
        );

        x += width / 9;

        colorButtons[HEART_CODE] = new ColorButton(
                signs[HEART_CODE],
                HEART_CODE,
                x,
                y,
                scaleX,
                scaleY
        );

        x += width / 9;

        colorButtons[DIAMOND_CODE] = new ColorButton(
                signs[DIAMOND_CODE],
                DIAMOND_CODE,
                x,
                y,
                scaleX,
                scaleY
        );

        x += width / 9;

        colorButtons[BULB_CODE] = new ColorButton(
                signs[BULB_CODE],
                BULB_CODE,
                x,
                y,
                scaleX,
                scaleY
        );

        x += width / 9;

        colorButtons[STAR_CODE] = new ColorButton(
                signs[STAR_CODE],
                STAR_CODE,
                x,
                y,
                scaleX,
                scaleY
        );

        x += width / 9;

        colorButtons[FLOWER_CODE] = new ColorButton(
                signs[FLOWER_CODE],
                FLOWER_CODE,
                x,
                y,
                scaleX,
                scaleY
        );

        x += width / 9;

        colorButtons[GEAR_CODE] = new ColorButton(
                signs[GEAR_CODE],
                GEAR_CODE,
                x,
                y,
                scaleX,
                scaleY
        );
    }

}
