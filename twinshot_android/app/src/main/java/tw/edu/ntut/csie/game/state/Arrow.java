package tw.edu.ntut.csie.game.state;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

import tw.edu.ntut.csie.game.Game;
import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.extend.Animation;
import tw.edu.ntut.csie.game.map.GameMap;
import tw.edu.ntut.csie.game.map.MapController;

public class Arrow extends Animation {
    private Animation arrow;
    private Timer timer;
    private TimerTask timerTask;
    private String direction;
    private GameMap gameMap;
    private int fallCount;
    private int disappearCount;
    private boolean attack;
    public boolean noPower;
    public boolean onWall;
    public boolean hitMonster;

    public Arrow(GameMap map){
        arrow = new Animation();
        direction = null;
        gameMap = map;
        fallCount = 0;
        disappearCount = 50;
        arrow.setVisible(false);
        attack = false;
        hitMonster = false;
    }

    public void initializeLeft(){
        arrow.addFrame(R.drawable.arrowleft);
        arrow.setDelay(2);
        direction = "Left";
        noPower = false;
        onWall = false;
    }

    public void initializeRight(){
        arrow.addFrame(R.drawable.arrowright);
        arrow.setDelay(2);
        direction = "Right";
        noPower = false;
        onWall = false;
    }

    public void attack(int mainX, int mainY, int delay){
        attack = true;
        arrow.setLocation(mainX, mainY+15);
        fallCount = delay;
        arrow.setVisible(true);
        noPower = false;
        hitMonster = false;
    }

    public void shoot(){
        if(attack){
            if(direction.contains("Left")){
                if(gameMap.arrowEnable_left(arrow.getX()-20, arrow.getY(), this) && fallCount > 0 && !hitMonster){
                    arrow.setLocation(arrow.getX()-20, arrow.getY());
                    fallCount--;
                }else if(gameMap.arrowEnable_left(arrow.getX()-20, arrow.getY()+18, this) && !hitMonster){
                    arrow.setLocation(arrow.getX()-20, arrow.getY()+6);
                }else if(gameMap.arrowEnable_left(arrow.getX()-20, arrow.getY(), this) && !hitMonster){
                    arrow.setLocation(arrow.getX()-20, arrow.getY());
                    noPower = true;
                }else{
                    noPower = true;
                    disappearCount--;
                    if(hitMonster){
                        arrow.setVisible(false);
                        attack = false;
                    }else if(disappearCount != 0){
                        onWall = true;
                        if(disappearCount%2==0){
                            arrow.setVisible(false);
                        }else{
                            arrow.setVisible(true);
                        }
                    }else{
                        attack = false;
                        onWall = false;
                    }
                }
                if(arrow.getX() < -20){
                    arrow.setLocation(600, arrow.getY());
                }
            }
            if(direction.contains("Right")){
                if(gameMap.arrowEnable_right(arrow.getX()+20, arrow.getY(), this) && fallCount > 0 && !hitMonster){
                    arrow.setLocation(arrow.getX()+20, arrow.getY());
                    fallCount--;
                }else if(gameMap.arrowEnable_right(arrow.getX()+20, arrow.getY()+18, this) && !hitMonster){
                    arrow.setLocation(arrow.getX()+20, arrow.getY()+6);
                }else if(gameMap.arrowEnable_right(arrow.getX()+20, arrow.getY(), this) && !hitMonster){
                    arrow.setLocation(arrow.getX()+20, arrow.getY());
                    noPower = true;
                }else{
                    noPower = true;
                    disappearCount--;
                    if(hitMonster){
                        arrow.setVisible(false);
                        attack = false;
                    }else if(disappearCount != 0){
                        onWall = true;
                        if(disappearCount%2==0){
                            arrow.setVisible(false);
                        }else{
                            arrow.setVisible(true);
                        }
                    }else{
                        attack = false;
                        onWall = false;
                    }
                }
                if(arrow.getX() > 600){
                    arrow.setLocation(0, arrow.getY());
                }
            }
        }

    }

    @Override
    public int getX(){
        return arrow.getX();
    }

    @Override
    public int getY(){
        return arrow.getY();
    }

    @Override
    public int getWidth(){
        return arrow.getWidth();
    }

    @Override
    public int getHeight(){
        return arrow.getHeight();
    }

    public boolean getAttackState(){
        return attack;
    }

    @Override
    public void move(){
        arrow.move();
        shoot();
    }

    @Override
    public void show(){
        arrow.show();
    }


    private void stopTimer(){
        if(timerTask!= null){
            timerTask.cancel();
            timerTask = null;
        }
        if(timer != null){
            timer.cancel();
            timer = null;
        }
    }

    @Override
    public void release(){
        arrow.release();
        arrow = null;
    }

}