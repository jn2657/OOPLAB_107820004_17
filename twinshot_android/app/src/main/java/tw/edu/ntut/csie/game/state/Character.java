package tw.edu.ntut.csie.game.state;

import java.util.Timer;
import java.util.TimerTask;

import android.os.CountDownTimer;
import android.os.Handler;

import tw.edu.ntut.csie.game.GameObject;
import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.extend.Animation;
import tw.edu.ntut.csie.game.map.MapController;
import tw.edu.ntut.csie.game.map.GameMap;

public class Character implements GameObject {
    private Animation main;
    public Arrow arrowLeft, arrowRight;

    private Timer timer;
    private TimerTask timerTask;

    public int life, score;
    private int godModeCount, jumpHeight;
    private GameMap gameMap;
    private String direction;
    private boolean jumping, falling, shooting, godMode, onArrow;
    public boolean dead;


    public Character(){
        main = new Animation();
        arrowLeft = null;
        arrowRight = null;
        timer = null;
        timerTask = null;
        jumpHeight = 5;
        godModeCount = 50;
        life = 3;
        score = 0;
        direction = "standingRight";
        godMode = false;
        falling = false;
        shooting = false;
        dead = false;
    }

    public void initialize(GameMap map){
        main.setLocation(map.getInitialPositionX(), map.getInitialPositionY());
        main.addFrame(R.drawable.mainright);
        main.addFrame(R.drawable.mainright);
        main.addFrame(R.drawable.mainright1);
        main.addFrame(R.drawable.mainright2);
        main.addFrame(R.drawable.mainright3);
        main.addFrame(R.drawable.mainright4);
        main.addFrame(R.drawable.mainright5);
        main.addFrame(R.drawable.mainright6);
        main.addFrame(R.drawable.mainright7);
        main.addFrame(R.drawable.mainleft);
        main.addFrame(R.drawable.mainleftshake);
        main.addFrame(R.drawable.mainleft1);
        main.addFrame(R.drawable.mainleft2);
        main.addFrame(R.drawable.mainleft3);
        main.addFrame(R.drawable.mainleft4);
        main.addFrame(R.drawable.mainleft5);
        main.addFrame(R.drawable.mainleft6);
        main.addFrame(R.drawable.mainleft7);
        main.addFrame(R.drawable.shoot_left1);
        main.addFrame(R.drawable.shoot_left2);
        main.addFrame(R.drawable.shoot_left3);
        main.addFrame(R.drawable.shoot_left4);
        main.addFrame(R.drawable.shoot_right1_1);
        main.addFrame(R.drawable.shoot_right2_1);
        main.addFrame(R.drawable.shoot_right3_1);
        main.addFrame(R.drawable.shoot_right4_1);
        main.addFrame(R.drawable.jumpleft);
        main.addFrame(R.drawable.jumpright);
        main.addFrame(R.drawable.fallleft);
        main.addFrame(R.drawable.fallright);
        main.setDelay(2);
        gameMap = map;
    }

    public boolean isJumping(){
        return jumping;
    }

    public void jump(int height){
        jumping = true;
        onArrow = false;
        jumpHeight = height*5;
        if(timer == null){ timer = new Timer(); }
        timer.scheduleAtFixedRate(timerTask = new TimerTask() {
            @Override
            public void run() {
                if(jumping){
                    if(jumpHeight>0){
                        if(gameMap.isWalkable_up(main.getX(), main.getY()-5)){
                            main.setLocation(main.getX(), main.getY() - 5);
                            jumpHeight--;
                        }else{
                            jumpHeight = 0;
                        }

                    }else{
                        falling = true;
                        if(gameMap.isWalkable_down(main.getX(), main.getY()+5)){
                            main.setLocation(main.getX(), main.getY()+5);
                            jumpHeight--;
                            if(checkIfLeftArrowOnWall(main.getX(), main.getY())){
                                jumping = false;
                                falling = false;
                                onArrow = true;
                                direction = "standingLeft";
                                stopTimer();
                            }
                            if(checkIfRightArrowOnWall(main.getX(), main.getY())){
                                jumping = false;
                                falling = false;
                                onArrow = true;
                                direction = "standingRight";
                                stopTimer();
                            }

                        }else{
                            jumping = false;
                            falling = false;
                            onArrow = false;
                            stopTimer();
                        }
                    }
                }
            }
        }, 100, 20);
    }

    public void shoot(){
        shooting = true;
        if(direction.contains("Right")){
            arrowRight = new Arrow(gameMap);
            arrowRight.initializeRight();
            arrowRight.attack(main.getX(), main.getY(), 20);
            direction = "shootingRight";
        }
        if(direction.contains("Left")){
            arrowLeft = new Arrow(gameMap);
            arrowLeft.initializeLeft();
            arrowLeft.attack(main.getX(), main.getY(), 20);
            direction = "shootingLeft";
        }
    }

    public void checkGravity(){
        if(!jumping && onArrow){
            if(!checkIfLeftArrowOnWall(main.getX(), main.getY()) && !checkIfRightArrowOnWall(main.getX(), main.getY())){
                jump(0);
                onArrow = false;
            }
        }
    }

    public void setDirection(String d){
        direction = d;
    }

    @Override
    public void move(){
        main.move();
        if(arrowRight != null){
            arrowRight.move();
            if(!arrowRight.getAttackState()){
                arrowRight.release();
                arrowRight = null;
            }
        }
        if(arrowLeft != null){
            arrowLeft.move();
            if(!arrowLeft.getAttackState()){
                arrowLeft.release();
                arrowLeft = null;
            }
        }
        animePlay(direction);
        if(main.getY() > 400){
            main.setLocation(main.getX(), 0);
        }
        if (!falling && main.getY() < 10){
            main.setLocation(main.getX(), 391);
        }
        if (gameMap.superJump(main.getX(),main.getY())){
            jump(11);
        }
        checkGravity();
    }

    @Override
    public void show(){
        main.show();
        if(arrowRight != null){ arrowRight.show(); }
        if(arrowLeft != null){ arrowLeft.show(); }
    }

    @Override
    public void release(){
        main.release();
        main = null;
    }

    public int getX(){
        return main.getX();
    }

    public int getY(){
        return main.getY();
    }

    public int getWidth(){
        return main.getWidth();
    }

    public int getHeight(){
        return main.getHeight();
    }

    public void setLocation(int x, int y){
        main.setLocation(x, y);
    }

    public String getDirection() {return direction;}

    public int getJumpHeight(){
        return jumpHeight;
    }

    public void reset(){
        main.reset();
    }

    public void animePlay(String s){
        if(godMode && godModeCount != 0){
            if(main.getVisible()){
                main.setVisible(false);
            }else{
                main.setVisible(true);
            }
            godModeCount--;
            if(godModeCount == 0){
                godMode = false;
                godModeCount = 50;
            }
        }
        if(falling && !shooting){
            if(s.contains("Left")){
                if(main.getCurrentFrameIndex() != 28){
                    main.setCurrentFrameIndex(28);
                }
            }else{
                main.setCurrentFrameIndex(29);
            }
        }else if(jumping && !shooting && s.contains("Left")){
            if(main.getCurrentFrameIndex() != 26){
                main.setCurrentFrameIndex(26);
            }
        }else if(jumping && !shooting && s.contains("Right")){
            if(main.getCurrentFrameIndex() != 27){
                main.setCurrentFrameIndex(27);
            }
        }else if(s.equals("Right")){
            if(main.getCurrentFrameIndex() >= 8 || main.getCurrentFrameIndex() < 2){
                main.setCurrentFrameIndex(2);
            }
        }else if(s.equals("Left")){
            if(main.getCurrentFrameIndex() >= 17 || main.getCurrentFrameIndex() < 11){
                main.setCurrentFrameIndex(11);
            }
        }else if(s.equals("standingRight")){
            if(main.getCurrentFrameIndex() >= 2){
                main.setCurrentFrameIndex(0);
            }
        }else if(s.equals("standingLeft")){
            if(main.getCurrentFrameIndex() != 9){
                main.setCurrentFrameIndex(9);
            }
        }else if(s.equals("shootingLeft")){
            if(main.getCurrentFrameIndex() < 18 || main.getCurrentFrameIndex() >= 22){
                main.setCurrentFrameIndex(18);
            }
            if(main.getCurrentFrameIndex() == 21){
                shooting = false;
                direction = "standingLeft";
            }
        }else if(s.equals("shootingRight")){
            if(main.getCurrentFrameIndex() < 22 || main.getCurrentFrameIndex() >= 26){
                main.setCurrentFrameIndex(22);
            }
            if(main.getCurrentFrameIndex() == 25){
                shooting = false;
                direction = "standingRight";
            }
        }
    }


    private void stopTimer(){
        if(timer != null){
            timer.cancel();
            timer = null;
        }
        if(timerTask != null){
            timerTask.cancel();
            timerTask = null;
        }
        jumpHeight = 5;
    }

    public void hurt(boolean collide){
        if(collide){
            if(!godMode && life > 0){
                jump(5);
                godMode = true;
                life--;
            }
        }
        if(life == 0){
            godMode = true;
            if(main.getY() < 390){
                main.setLocation(main.getX(), main.getY()+5);
                if(main.getY() > 385){
                    dead = true;
                }
            }
        }
    }

    public boolean checkIfLeftArrowOnWall(int mainX, int mainY){
        int ArrowX;
        int ArrowY;
        if(arrowLeft != null && arrowLeft.onWall){
            ArrowX = arrowLeft.getX();
            ArrowY = arrowLeft.getY();
            if(mainX+46 > ArrowX && mainX <= ArrowX+46 && mainY+40 < ArrowY && mainY+40 > ArrowY - 10){
                return true;
            }
        }
        return false;
    }

    public boolean checkIfRightArrowOnWall(int mainX, int mainY){
        int ArrowX;
        int ArrowY;
        if(arrowRight != null && arrowRight.onWall){
            ArrowX = arrowRight.getX();
            ArrowY = arrowRight.getY();
            if(mainX+46 > ArrowX && mainX <= ArrowX+46 && mainY+40 < ArrowY && mainY+40 > ArrowY - 10){
                return true;
            }
        }
        return false;
    }
}