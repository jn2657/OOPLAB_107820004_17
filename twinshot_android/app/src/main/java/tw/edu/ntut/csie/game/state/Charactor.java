package tw.edu.ntut.csie.game.state;

import java.util.Timer;
import java.util.TimerTask;

import android.os.CountDownTimer;
import android.os.Handler;

import tw.edu.ntut.csie.game.GameObject;
import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.extend.Animation;

public class Charactor implements GameObject {
    private Animation main;
    private Arrow arrowLeft;
    private Arrow arrowRight;

    private Timer timer;
    private TimerTask timerTask;

    private int arrowMoveCount;
    private int disppearCount;
    private int jumpHeight;
    private int life;
    private int count;
    private int godModeCount;
    private GameMap gameMap;
    private String direction;
    private Handler handler;
    private Runnable runnable;
    private boolean jumping;
    private boolean godMode;


    public Charactor(){
        main = new Animation();
        handler = null;
        runnable = null;
        arrowLeft = null;
        arrowRight = null;
        timer = null;
        timerTask = null;
        count = 0;
        jumpHeight = 5;
        disppearCount = 10;
        godModeCount = 50;
        life = 3;
        direction = "right";
        godMode = false;
    }

    public void initialize(GameMap map){
        main.setLocation(300, 230);
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
        main.setDelay(2);
        gameMap = map;
    }

    public boolean isJumping(){
        return jumping;
    }

    public void jump(int j){
        jumping = true;
        jumpHeight = j;
        jumpHeight *= 5;
        if(timer == null){
            timer = new Timer();
        }
        timer.scheduleAtFixedRate(timerTask = new TimerTask() {
            @Override
            public void run() {
                if(jumpHeight>0){
                    if(gameMap.isWalkable_up_right(main.getX(), main.getY()-5)){
                        main.setLocation(main.getX(), main.getY() - 5);
                        jumpHeight--;
                    }else{
                        jumpHeight = 0;
                    }

                }
                if(jumpHeight<=0){
                    if(gameMap.isWalkable_down_left(main.getX(), main.getY()+5)){
                        main.setLocation(main.getX(), main.getY() + 5);
                        jumpHeight--;
                    }else{
                        jumping = false;
                        stopTimer();
                    }
                }
            }

        }, 100, 20);

    }


    public void shot(){
        if(direction.contains("right")){
            arrowRight = new Arrow(gameMap);
            arrowRight.initializeRight();
            arrowRight.attack(true, main.getX(), main.getY(), 20);

        }
        if(direction.contains("left")){
            arrowLeft = new Arrow(gameMap);
            arrowLeft.initializeLeft();
            arrowLeft.attack(true, main.getX(), main.getY(), 20);

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
    }

    @Override
    public void show(){
        main.show();
        if(arrowRight != null){
            arrowRight.show();
        }
        if(arrowLeft != null){
            arrowLeft.show();
        }
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

    public void setLocation(int x, int y){
        main.setLocation(x, y);
    }

    public String getDirection() {return direction;}

    public int getHeight(){
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
        if(s.equals("right")){
            if(main.getCurrentFrameIndex() > 8 || main.getCurrentFrameIndex() < 2){
                main.setCurrentFrameIndex(2);
            }
            if(main.getCurrentFrameIndex() == 8){
                main.setCurrentFrameIndex(2);
            }
        }else if(s.equals("left")){
            if(main.getCurrentFrameIndex() > 17 || main.getCurrentFrameIndex() < 11){
                main.setCurrentFrameIndex(11);
            }
            if(main.getCurrentFrameIndex() == 17){
                main.setCurrentFrameIndex(11);
            }
        }else if(s.equals("standl")){
            if(main.getCurrentFrameIndex() > 1){
                main.setCurrentFrameIndex(0);
            }
            if(main.getCurrentFrameIndex() == 1){
                main.setCurrentFrameIndex(0);
            }

        }else if(s.equals("standr")){
            if(main.getCurrentFrameIndex() > 10 || main.getCurrentFrameIndex() < 9){
                main.setCurrentFrameIndex(9);
            }
            if(main.getCurrentFrameIndex() == 10) {
                main.setCurrentFrameIndex(9);
            }
        }
    }

//    public void animeStop(){
//        if(direction.equals("right")){
//            main.reset();
//            //main.setRepeating(false);
//        }
//        if(direction.equals("left")){
//            main.setCurrentFrameIndex(8);
//            //main.setRepeating(false);
//        }
//    }


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


    public boolean isGodMode(){
        return godMode;
    }

    public void hurt(boolean collide){
        if(collide){
            if(!godMode) {
                life--;
                jump(5);
                godMode = true;
            }
        }
        if(life == 0){
            //change state
        }
    }
}
