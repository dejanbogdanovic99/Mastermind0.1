package com.sts.mastermind.gamePackage;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.sts.mastermind.Main;
import com.sts.mastermind.bundelPackage.DataBundle;
import com.sts.mastermind.combinationPackage.Combination;
import com.sts.mastermind.guiPackage.Button;
import com.sts.mastermind.guiPackage.CheckView;
import com.sts.mastermind.guiPackage.ColorButton;
import com.sts.mastermind.guiPackage.CombinationView;
import com.sts.mastermind.guiPackage.PastView;
import com.sts.mastermind.guiPackage.TimerView;

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

    private Texture menuUp;
    private Texture menuDown;

    private Texture newUp;
    private Texture newDown;

    private Texture checkUp;
    private Texture checkDown;

    /**
     * kombinacija
     */

    private Combination secretCombination;

    /**
     * tasteri za boje
     */

    private CombinationView combinationView;

    private CombinationView secretView;

    private ColorButton [] colorButtons;

    private Button menuButton;

    private Button newButton;

    private Button checkButton;

    private PastView pastView;

    private CheckView checkView;

    private float combinationY;



    private TimerView timerView;

    private Texture timerTexture;

    /**
        Sve vezano za pitanje
     */

    private final int NEW_GAME = 5;

    private boolean question;
    private Texture sureTexture;
    private Image sureImage;

    private Texture yesUp;
    private Texture yesDown;

    private Texture noUp;
    private Texture noDown;

    private Button yesButton;
    private Button noButton;

    private int futureCode;



    private boolean lost;
    private Texture lostTexture;
    private Image lostImage;

    private boolean won;
    private Texture wonTexture;
    private Image wonImage;

    private Texture endTexture;
    private Image endImage;

    private int numberOfAttempts;


    public PlayState(DataBundle bundle, float scaleX, float scaleY, int width, int height) {
        super(bundle, scaleX, scaleY, width, height);
    }

    @Override
    public void init() {

        initColorButtons();

        createCombination();

        float x = 150*scaleX;
        float y = 100*scaleY;

        won = false;
        lost = false;

        numberOfAttempts = 0;

        combinationY = height - 180*scaleY;

        menuButton = new Button(menuUp, menuDown, x, y, scaleX, scaleY);

        x = width - 150*scaleX;

        newButton = new Button(newUp, newDown, x, y, scaleX, scaleY);

        x = width / 2;

        checkButton = new Button(checkUp, checkDown, x, y, scaleX, scaleY);

        combinationView = new CombinationView(bundle.getAmountOfRows(), signBack, scaleX, scaleY);
        combinationView.setPosition(50*scaleX, combinationY);

        secretView = new CombinationView(bundle.getAmountOfRows(), signBack, scaleX, scaleY);
        secretView.setPosition((width - 100 * bundle.getAmountOfRows() * scaleX -
                        10 * (bundle.getAmountOfRows()-1) * scaleX) / 2,
                height - 780 * scaleY);

        for(int i = 0; i < bundle.getAmountOfRows();i++){
            secretView.setSign(i, signs[secretCombination.getSign(i)], secretCombination.getSign(i));
        }


        wonImage = new Image(wonTexture);
        wonImage.setScale(scaleX, scaleY);
        wonImage.setPosition(90 * scaleX,height - (400+ wonImage.getHeight()) * scaleY);

        lostImage = new Image(lostTexture);
        lostImage.setScale(scaleX, scaleY);
        lostImage.setPosition(90 * scaleX,height - (400+ lostImage.getHeight()) * scaleY);

        endImage = new Image(endTexture);
        endImage.setScale(scaleX, scaleY);

        endImage.setPosition(0,0);

        question = false;

        sureImage = new Image(sureTexture);
        sureImage.setScale(scaleX,scaleY);
        sureImage.setPosition(90 * scaleX,height - (500+ sureImage.getHeight()) * scaleY);

        yesButton = new Button(yesUp, yesDown, 300*scaleX, height - 950*scaleY, scaleX, scaleY);
        noButton = new Button(noUp, noDown, width - 300*scaleX, height - 950*scaleY, scaleX, scaleY);

        futureCode = -1;

        pastView = new PastView();

        timerView = new TimerView(timerTexture, 20, 0, height - 1530*scaleY, scaleX, scaleY);
    }

    @Override
    public void render(SpriteBatch batch, float alpha) {

        checkButton.draw(batch, alpha);

        if(!won && !lost) {
            combinationView.draw(batch, alpha);
        }

       // secretView.draw(batch,alpha);

        for(int i = 0; i < bundle.getAmountOfSigns();i++){
            colorButtons[i].draw(batch, alpha);
        }
        pastView.draw(batch, alpha);


        timerView.draw(batch, alpha);

        if(lost){
            endImage.draw(batch, alpha);
            lostImage.draw(batch, alpha);
            secretView.draw(batch, alpha);
        }else if(won){
            endImage.draw(batch, alpha);
            wonImage.draw(batch, alpha);
            secretView.draw(batch, alpha);
        }

        if(question){
            endImage.draw(batch,alpha);
            sureImage.draw(batch,alpha);
            yesButton.draw(batch, alpha);
            noButton.draw(batch, alpha);
        }

        menuButton.draw(batch, alpha);

        newButton.draw(batch, alpha);

    }

    @Override
    public void touchDown(int x, int y) {

        menuButton.handleDown(x,y);
        newButton.handleDown(x,y);
        if(!won && !lost){
            checkButton.handleDown(x,y);
        }

        if(question){
            yesButton.handleDown(x,y);
            noButton.handleDown(x,y);
        }

    }

    @Override
    public void touchUp(int x, int y) {

        if(menuButton.handleUp(x,y)){

            if(listener != null){
                listener.playSound();
            }

            if(won || lost) {
                if(listener != null){
                    listener.changeState(Main.MAIN_MENU_STATE);
                }
            }else {
                question = true;

                futureCode = Main.MAIN_MENU_STATE;
            }
        }

        if(question){
            if(noButton.handleUp(x,y)){

                if(listener != null){
                    listener.playSound();
                }

                question = false;
                futureCode = -1;
            }
            if(yesButton.handleUp(x,y)){

                question = false;

                if(listener != null){
                    listener.playSound();
                }

                if(futureCode == Main.MAIN_MENU_STATE){
                    if(listener != null){
                        listener.changeState(Main.MAIN_MENU_STATE);
                    }
                }

                if(futureCode == NEW_GAME){

                    numberOfAttempts = 0;
                    lost = false;
                    won = false;
                    combinationY = height - 180*scaleY;
                    combinationView = new CombinationView(bundle.getAmountOfRows(), signBack, scaleX, scaleY);
                    combinationView.setPosition(50 * scaleX, combinationY);
                    createCombination();
                    pastView.reset();
                    secretView.getCombination().reset();
                    for(int i = 0; i < bundle.getAmountOfRows();i++){
                        secretView.setSign(i, signs[secretCombination.getSign(i)], secretCombination.getSign(i));
                    }

                    menuButton.setPosition(150*scaleX, 100*scaleY);
                    newButton.setPosition(width - 150*scaleX, 100*scaleY);

                }
            }
        }

        if(checkButton.handleUp(x,y)){

            if(listener != null){
                listener.playSound();
            }

            if(combinationView.getCombination().getFirstEmpty() == -1) {

                ArrayList<Integer> full = new ArrayList<Integer>();
                ArrayList<Integer> half = new ArrayList<Integer>();

                for(int i = 0; i < bundle.getAmountOfRows();i++){
                    if(combinationView.getCombination().getSign(i) == secretCombination.getSign(i)){
                        full.add(i);
                    }
                }

                for(int i = 0; i < bundle.getAmountOfRows();i++){
                    int j = 0;
                    boolean found = false;
                    while(j < bundle.getAmountOfRows() && !found){
                        if(combinationView.getCombination().getSign(i) == secretCombination.getSign(j) && !full.contains(i) && !full.contains(j) && !half.contains(j)){
                            half.add(j);
                            found = true;
                        }
                        j++;
                    }
                }

                numberOfAttempts++;

                if (full.size() == bundle.getAmountOfRows()){
                    won = true;

                    menuButton.setPosition(300*scaleX, height - 950*scaleY);
                    newButton.setPosition(width - 300*scaleX, height - 950*scaleY);

                }else if (numberOfAttempts == 10){
                    lost = true;

                    menuButton.setPosition(300*scaleX, height - 950*scaleY);
                    newButton.setPosition(width - 300*scaleX, height - 950*scaleY);
                }

                checkView = new CheckView(fullHit, halfHit, scaleX, scaleY, bundle.getAmountOfRows(), full.size(), half.size());
                checkView.setPosition(width - 300 * scaleX, combinationY + 35 * scaleY);
                pastView.add(combinationView, checkView);
                combinationY -= 150 * scaleY;
                combinationView = new CombinationView(bundle.getAmountOfRows(), signBack, scaleX, scaleY);

                /*
                da sklonim combination view ako se ne gadja vise

                 */

                combinationView.setPosition(50 * scaleX, combinationY);
                /*if(won || lost){
                    combinationView.setPosition(50 * scaleX, -1000);
                }*/
            }
        }

        if(newButton.handleUp(x,y)){

            if(listener != null){
                listener.playSound();
            }

            if(won || lost) {
                numberOfAttempts = 0;
                lost = false;
                won = false;
                combinationY = height - 180*scaleY;
                combinationView = new CombinationView(bundle.getAmountOfRows(), signBack, scaleX, scaleY);
                combinationView.setPosition(50 * scaleX, combinationY);
                createCombination();
                pastView.reset();
                secretView.getCombination().reset();
                for(int i = 0; i < bundle.getAmountOfRows();i++){
                    secretView.setSign(i, signs[secretCombination.getSign(i)], secretCombination.getSign(i));
                }

                menuButton.setPosition(150*scaleX, 100*scaleY);
                newButton.setPosition(width - 150*scaleX, 100*scaleY);
            }else {
                question = true;

                futureCode = NEW_GAME;
            }
        }

        if(!question) {

            if (combinationView.handleInput(x, y)) {


                if (listener != null) {
                    listener.playSound();
                }


            }
            for (int i = 0; i < bundle.getAmountOfSigns(); i++) {
                if (colorButtons[i].isPressed(x, y)) {


                    if (listener != null) {
                        listener.playSound();
                    }


                    int index = combinationView.getCombination().getFirstEmpty();
                    if (index != -1) {
                        combinationView.setSign(index, signs[i], i);
                    }
                }
            }



        }
    }

    @Override
    public void update(float delta) {
        timerView.update(delta);
    }

    @Override
    public void backPressed() {

        if(won || lost){
            if(listener != null){
                listener.changeState(Main.MAIN_MENU_STATE);
            }
        }else if(!question){
            question = true;
            futureCode = Main.MAIN_MENU_STATE;
        }else{
            futureCode = -1;
            question = false;
        }
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void initTextures() {
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

        lostTexture = new Texture("lose.png");
        wonTexture = new Texture("win.png");

        endTexture = new Texture("end.png");

        sureTexture = new Texture("areusure.png");

        yesUp = new Texture("yes1.png");
        yesDown = new Texture("yes2.png");

        noUp = new Texture("no1.png");
        noDown = new Texture("no2.png");

        timerTexture = new Texture("timer.png");
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

        lostTexture.dispose();
        wonTexture.dispose();
        endTexture.dispose();

        sureTexture.dispose();
        yesUp.dispose();
        yesDown.dispose();
        noUp.dispose();
        noDown.dispose();

        timerTexture.dispose();
    }

    private void createCombination(){

        secretCombination = new Combination(bundle.getAmountOfRows());
        Random random = new Random();

        if(bundle.getRepeatSigns()){
            for(int i = 0; i < bundle.getAmountOfRows();i++){
                int sign = random.nextInt(bundle.getAmountOfSigns());
                secretCombination.setSign(i,sign);
            }
        }else{
            ArrayList <Integer> number = new ArrayList<Integer>();
            for(int i = 0; i < bundle.getAmountOfSigns();i++){
                number.add(i);
            }

            for(int i = 0; i < bundle.getAmountOfRows();i++){
                int sign = random.nextInt(number.size());
                secretCombination.setSign(i, number.get(sign));
                number.remove(sign);
            }
        }
    }

    private void initColorButtons(){
        colorButtons = new ColorButton[8];

        float x = width / (bundle.getAmountOfSigns() +1);
        float y = 280*scaleY;

        colorButtons[CLUB_CODE] = new ColorButton(
                signs[CLUB_CODE],
                CLUB_CODE,
                x,
                y,
                scaleX,
                scaleY
        );

        x += width / (bundle.getAmountOfSigns() +1);

        colorButtons[SPADES_CODE] = new ColorButton(
                signs[SPADES_CODE],
                SPADES_CODE,
                x,
                y,
                scaleX,
                scaleY
        );

        x += width / (bundle.getAmountOfSigns() +1);

        colorButtons[HEART_CODE] = new ColorButton(
                signs[HEART_CODE],
                HEART_CODE,
                x,
                y,
                scaleX,
                scaleY
        );

        x += width / (bundle.getAmountOfSigns() +1);

        colorButtons[DIAMOND_CODE] = new ColorButton(
                signs[DIAMOND_CODE],
                DIAMOND_CODE,
                x,
                y,
                scaleX,
                scaleY
        );

        x += width / (bundle.getAmountOfSigns() +1);

        colorButtons[BULB_CODE] = new ColorButton(
                signs[BULB_CODE],
                BULB_CODE,
                x,
                y,
                scaleX,
                scaleY
        );

        x += width / (bundle.getAmountOfSigns() +1);

        colorButtons[STAR_CODE] = new ColorButton(
                signs[STAR_CODE],
                STAR_CODE,
                x,
                y,
                scaleX,
                scaleY
        );

        x += width / (bundle.getAmountOfSigns() +1);

        colorButtons[FLOWER_CODE] = new ColorButton(
                signs[FLOWER_CODE],
                FLOWER_CODE,
                x,
                y,
                scaleX,
                scaleY
        );

        x += width / (bundle.getAmountOfSigns() +1);

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
