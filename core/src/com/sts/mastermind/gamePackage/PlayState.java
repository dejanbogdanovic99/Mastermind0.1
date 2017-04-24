package com.sts.mastermind.gamePackage;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sts.mastermind.Main;
import com.sts.mastermind.bundelPackage.DataBundle;
import com.sts.mastermind.combinationPackage.Combination;
import com.sts.mastermind.guiPackage.BasicImage;
import com.sts.mastermind.guiPackage.Button;
import com.sts.mastermind.guiPackage.CheckView;
import com.sts.mastermind.guiPackage.CombinationView;
import com.sts.mastermind.guiPackage.PastView;
import com.sts.mastermind.guiPackage.SignButton;
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
     * ostale texture
     */

    private Texture [] signs;

    private Texture signBack;

    private Texture fullHit;
    private Texture halfHit;




    /**
     * tajna kombinacija
     */


    private Combination secretCombination;

    private CombinationView secretView;

    /**
     * menu taster
     */

    private Texture menuUp;
    private Texture menuDown;

    private Button menuButton;

    /**
     * new taster
     */

    private Texture newUp;
    private Texture newDown;

    private Button newButton;


    /**
     * check taster
     */

    private Texture checkUp;
    private Texture checkDown;

    private Button checkButton;


    /**
     * tasteri za znakove
     */

    private SignButton[] signButtons;

    /**
     * kombinacija sa kojom se radi
     * #combinationY -> prati trenutnu poziciju kombinacije
     */

    private CombinationView combinationView;

    private float combinationY;


    /**
     * pracenje dosadasnjih pokusaja
     */

    private PastView pastView;

    private int numberOfAttempts;


    /**
     * tajmer
     * dozvola za tajmer
     */

    private Texture timerTexture;

    private Texture timerBackTexture;

    private TimerView timerView;

    private boolean timerEnabled;

    /**
     * pauza i pitanje
     */

    private final int NO_CODE = -1;
    private final int NEW_GAME = 0;
    private final int MAIN_MENU = 1;

    private boolean questionVisible;

    private Texture sureTexture;

    private Texture yesUp;
    private Texture yesDown;

    private Texture noUp;
    private Texture noDown;

    private BasicImage sureImage;

    private Button yesButton;
    private Button noButton;

    private int futureCode;

    /**
     * za izgubljeni
     */
    private boolean lost;

    private Texture lostTexture;

    private BasicImage lostImage;

    /**
     * za pobedjeni
     */

    private boolean won;

    private Texture wonTexture;

    private BasicImage wonImage;

    /**
     * potamljena pozadina
     */

    private Texture bgDarkTexture;

    private BasicImage bgDarkImage;


    public PlayState(DataBundle bundle, float scaleX, float scaleY, int width, int height) {
        super(bundle, scaleX, scaleY, width, height);
    }

    @Override
    public void init() {

        createCombination();

        secretView = new CombinationView(bundle.getAmountOfColumns(), signBack, scaleX, scaleY);
        secretView.setPosition((width - 100 * bundle.getAmountOfColumns() * scaleX -
                        10 * (bundle.getAmountOfColumns()-1) * scaleX) / 2,
                height - 780 * scaleY);

        for(int i = 0; i < bundle.getAmountOfColumns();i++){
            secretView.setSign(i, signs[secretCombination.getSign(i)], secretCombination.getSign(i));
        }




        menuButton = new Button(menuUp, menuDown, 150*scaleX, 100*scaleY, scaleX, scaleY);

        newButton = new Button(newUp, newDown, width - 150*scaleX, 100*scaleY, scaleX, scaleY);

        checkButton = new Button(checkUp, checkDown, width / 2, 100*scaleY, scaleX, scaleY);



        initSignButtons();


        combinationY = height - 180*scaleY;

        combinationView = new CombinationView(bundle.getAmountOfColumns(), signBack, scaleX, scaleY);
        combinationView.setPosition(70*scaleX, combinationY);


        pastView = new PastView();

        numberOfAttempts = 0;



        setUpTimer();

        setUpQuestion();


        lost = false;

        lostImage = new BasicImage(lostTexture, width /2, height - 700*scaleY, scaleX, scaleY);



        won = false;

        wonImage = new BasicImage(wonTexture, width /2, height - 700*scaleY, scaleX, scaleY);

        bgDarkImage = new BasicImage(bgDarkTexture, width /2, height/2, scaleX, scaleY);
    }

    @Override
    public void render(SpriteBatch batch, float alpha) {

        checkButton.draw(batch, alpha);

        for(int i = 0; i < bundle.getAmountOfSigns();i++){
            signButtons[i].draw(batch, alpha);
        }

        pastView.draw(batch, alpha);

        if(timerEnabled) {
            timerView.draw(batch, alpha);
        }

        if(!won && !lost) {
            combinationView.draw(batch, alpha);
        }

        if(won){
            bgDarkImage.draw(batch, alpha);
            wonImage.draw(batch, alpha);
            secretView.draw(batch, alpha);

        }

        if(lost){
            bgDarkImage.draw(batch, alpha);
            lostImage.draw(batch, alpha);
            secretView.draw(batch, alpha);
        }

        menuButton.draw(batch, alpha);

        newButton.draw(batch, alpha);

        if(questionVisible){
            bgDarkImage.draw(batch, alpha);
            sureImage.draw(batch, alpha);
            yesButton.draw(batch, alpha);
            noButton.draw(batch, alpha);
        }
    }

    @Override
    public void touchDown(int x, int y) {

        if(questionVisible){
            yesButton.handleDown(x,y);
            noButton.handleDown(x,y);
        }else if(won || lost){
            menuButton.handleDown(x,y);
            newButton.handleDown(x,y);
        }else{
            checkButton.handleDown(x,y);
        }

    }

    @Override
    public void touchUp(int x, int y) {

        if(questionVisible){


            if(noButton.handleUp(x,y)){

                if(listener != null){
                    listener.playSound();
                }

                questionVisible = false;
                futureCode = NO_CODE;

                if(timerEnabled){
                    timerView.resume();
                }
            }

            if(yesButton.handleUp(x,y)){

                questionVisible = false;

                if(listener != null){
                    listener.playSound();
                }

                if(futureCode == MAIN_MENU){
                    if(listener != null){
                        listener.changeState(Main.MAIN_MENU_STATE);
                    }
                }

                if(futureCode == NEW_GAME){

                    numberOfAttempts = 0;
                    lost = false;
                    won = false;
                    combinationY = height - 180*scaleY;
                    combinationView = new CombinationView(bundle.getAmountOfColumns(), signBack, scaleX, scaleY);
                    combinationView.setPosition(70 * scaleX, combinationY);
                    createCombination();
                    pastView.reset();
                    secretView.getCombination().reset();
                    for(int i = 0; i < bundle.getAmountOfColumns();i++){
                        secretView.setSign(i, signs[secretCombination.getSign(i)], secretCombination.getSign(i));
                    }

                    menuButton.setPosition(150*scaleX, 100*scaleY);
                    newButton.setPosition(width - 150*scaleX, 100*scaleY);

                    if(timerEnabled){
                        timerView.reset();
                    }

                }
            }




        }else if(!won && !lost){



            if(checkButton.handleUp(x,y)){

                if(listener != null){
                    listener.playSound();
                }

                if(combinationView.getCombination().getFirstEmpty() == -1) {

                    ArrayList<Integer> full = new ArrayList<Integer>();
                    ArrayList<Integer> half = new ArrayList<Integer>();

                    for(int i = 0; i < bundle.getAmountOfColumns();i++){
                        if(combinationView.getCombination().getSign(i) == secretCombination.getSign(i)){
                            full.add(i);
                        }
                    }

                    for(int i = 0; i < bundle.getAmountOfColumns();i++){
                        int j = 0;
                        boolean found = false;
                        while(j < bundle.getAmountOfColumns() && !found){
                            if(combinationView.getCombination().getSign(i) == secretCombination.getSign(j) && !full.contains(i) && !full.contains(j) && !half.contains(j)){
                                half.add(j);
                                found = true;
                            }
                            j++;
                        }
                    }

                    numberOfAttempts++;

                    if (full.size() == bundle.getAmountOfColumns()){
                        won = true;

                        menuButton.setPosition(300*scaleX, height - 950*scaleY);
                        newButton.setPosition(width - 300*scaleX, height - 950*scaleY);

                    }else if (numberOfAttempts == 10){
                        lost = true;

                        menuButton.setPosition(300*scaleX, height - 950*scaleY);
                        newButton.setPosition(width - 300*scaleX, height - 950*scaleY);
                    }

                    CheckView checkView = new CheckView(fullHit, halfHit, scaleX, scaleY, bundle.getAmountOfColumns(), full.size(), half.size());
                    checkView.setPosition(width - 300 * scaleX, combinationY + 35 * scaleY);
                    pastView.add(combinationView, checkView);
                    combinationY -= 150 * scaleY;
                    combinationView = new CombinationView(bundle.getAmountOfColumns(), signBack, scaleX, scaleY);
                    combinationView.setPosition(70 * scaleX, combinationY);

                }
            }

            combinationView.handleInput(x, y);

            for (int i = 0; i < bundle.getAmountOfSigns(); i++) {
                if (signButtons[i].isPressed(x, y)) {

                    int index = combinationView.getCombination().getFirstEmpty();
                    if (index != -1) {
                        combinationView.setSign(index, signs[i], i);
                    }
                }
            }


            if(newButton.handleUp(x,y)){

                if(listener != null){
                    listener.playSound();
                }

                if(timerEnabled){
                    timerView.pause();
                }

                questionVisible = true;

                futureCode = NEW_GAME;

            }

            if(menuButton.handleUp(x,y)){
                if(listener != null){
                    listener.playSound();
                }

                if(timerEnabled){
                    timerView.pause();
                }

                questionVisible = true;

                futureCode = MAIN_MENU;

            }

        }else{


            if(newButton.handleUp(x,y)){

                if(listener != null){
                    listener.playSound();
                }

                numberOfAttempts = 0;
                lost = false;
                won = false;
                combinationY = height - 180*scaleY;
                combinationView = new CombinationView(bundle.getAmountOfColumns(), signBack, scaleX, scaleY);
                combinationView.setPosition(70 * scaleX, combinationY);
                createCombination();
                pastView.reset();
                secretView.getCombination().reset();
                for(int i = 0; i < bundle.getAmountOfColumns();i++){
                    secretView.setSign(i, signs[secretCombination.getSign(i)], secretCombination.getSign(i));
                }

                menuButton.setPosition(150*scaleX, 100*scaleY);
                newButton.setPosition(width - 150*scaleX, 100*scaleY);

                if(timerEnabled){
                    timerView.reset();
                }
            }


            if(menuButton.handleUp(x,y)) {

                if (listener != null) {
                    listener.playSound();
                }

                if (listener != null) {
                    listener.changeState(Main.MAIN_MENU_STATE);
                }
            }

        }

    }

    @Override
    public void update(float delta) {
        if(timerEnabled){
            timerView.update(delta);
            if(timerView.isFinished()){
                lost = true;

                menuButton.setPosition(300*scaleX, height - 950*scaleY);
                newButton.setPosition(width - 300*scaleX, height - 950*scaleY);
            }
        }
    }

    @Override
    public void backPressed() {

        if(won || lost){
            if(listener != null){
                listener.changeState(Main.MAIN_MENU_STATE);
            }
        }else if(!questionVisible){
            questionVisible = true;
            futureCode = MAIN_MENU;
            if(timerEnabled) {
                timerView.pause();
            }
        }else{
            futureCode = NO_CODE;
            questionVisible = false;
            if(timerEnabled) {
                timerView.resume();
            }
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

        bgDarkTexture = new Texture("end.png");

        sureTexture = new Texture("areusure.png");

        yesUp = new Texture("yes1.png");
        yesDown = new Texture("yes2.png");

        noUp = new Texture("no1.png");
        noDown = new Texture("no2.png");

        timerTexture = new Texture("timerline.png");
        timerBackTexture = new Texture("timerback.png");
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
        bgDarkTexture.dispose();

        sureTexture.dispose();
        yesUp.dispose();
        yesDown.dispose();
        noUp.dispose();
        noDown.dispose();

        timerTexture.dispose();
        timerBackTexture.dispose();
    }

    private void createCombination(){

        secretCombination = new Combination(bundle.getAmountOfColumns());
        Random random = new Random();

        if(bundle.isRepeatSigns()){
            for(int i = 0; i < bundle.getAmountOfColumns();i++){
                int sign = random.nextInt(bundle.getAmountOfSigns());
                secretCombination.setSign(i,sign);
            }
        }else{
            ArrayList <Integer> number = new ArrayList<Integer>();
            for(int i = 0; i < bundle.getAmountOfSigns();i++){
                number.add(i);
            }

            for(int i = 0; i < bundle.getAmountOfColumns();i++){
                int sign = random.nextInt(number.size());
                secretCombination.setSign(i, number.get(sign));
                number.remove(sign);
            }
        }
    }

    private void setUpTimer() {

        float time = 0;

        timerEnabled = true;

        if(bundle.getTimerValue() == DataBundle.INFINITE){
            timerEnabled = false;
        }else if(bundle.getTimerValue() == DataBundle.T_30S){
            time = 30;
        }else if(bundle.getTimerValue() == DataBundle.T_1M){
            time = 60;
        }else if(bundle.getTimerValue() == DataBundle.T_2M){
            time = 120;
        }else if(bundle.getTimerValue() == DataBundle.T_5M){
            time = 300;
        }

        timerView = new TimerView(timerTexture, timerBackTexture, time, 17*scaleX, height - 1530*scaleY, scaleX, scaleY);

    }

    private void setUpQuestion(){

        questionVisible = false;

        yesButton = new Button(yesUp, yesDown, 300*scaleX, height - 850*scaleY, scaleX, scaleY);
        noButton = new Button(noUp, noDown, width - 300*scaleX, height - 850*scaleY, scaleX, scaleY);


        sureImage = new BasicImage(sureTexture, width /2, height - 700*scaleY, scaleX, scaleY);

        futureCode = NO_CODE;

    }

    private void initSignButtons(){
        signButtons = new SignButton[8];

        float x = width / (bundle.getAmountOfSigns() +1);
        float y = 280*scaleY;

        signButtons[CLUB_CODE] = new SignButton(
                signs[CLUB_CODE],
                CLUB_CODE,
                x,
                y,
                scaleX,
                scaleY
        );

        x += width / (bundle.getAmountOfSigns() +1);

        signButtons[SPADES_CODE] = new SignButton(
                signs[SPADES_CODE],
                SPADES_CODE,
                x,
                y,
                scaleX,
                scaleY
        );

        x += width / (bundle.getAmountOfSigns() +1);

        signButtons[HEART_CODE] = new SignButton(
                signs[HEART_CODE],
                HEART_CODE,
                x,
                y,
                scaleX,
                scaleY
        );

        x += width / (bundle.getAmountOfSigns() +1);

        signButtons[DIAMOND_CODE] = new SignButton(
                signs[DIAMOND_CODE],
                DIAMOND_CODE,
                x,
                y,
                scaleX,
                scaleY
        );

        x += width / (bundle.getAmountOfSigns() +1);

        signButtons[BULB_CODE] = new SignButton(
                signs[BULB_CODE],
                BULB_CODE,
                x,
                y,
                scaleX,
                scaleY
        );

        x += width / (bundle.getAmountOfSigns() +1);

        signButtons[STAR_CODE] = new SignButton(
                signs[STAR_CODE],
                STAR_CODE,
                x,
                y,
                scaleX,
                scaleY
        );

        x += width / (bundle.getAmountOfSigns() +1);

        signButtons[FLOWER_CODE] = new SignButton(
                signs[FLOWER_CODE],
                FLOWER_CODE,
                x,
                y,
                scaleX,
                scaleY
        );

        x += width / (bundle.getAmountOfSigns() +1);

        signButtons[GEAR_CODE] = new SignButton(
                signs[GEAR_CODE],
                GEAR_CODE,
                x,
                y,
                scaleX,
                scaleY
        );
    }

}
