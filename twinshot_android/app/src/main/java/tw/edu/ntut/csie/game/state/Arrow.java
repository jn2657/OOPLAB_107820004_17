package tw.edu.ntut.csie.game.state;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

import tw.edu.ntut.csie.game.Game;
import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.extend.Animation;

public class Arrow extends Animation {
    private Animation arrow;
    private Timer timer;
    private TimerTask timerTask;
    private String direction;
    private GameMap gameMap;
    private int fallCount;
    private int disappearCount;
    private boolean attack;
    boolean noPower;
    boolean hitMonster;
    private int mainx,mainy;

    public Arrow(GameMap map){
        arrow = new Animation();
        direction = null;
        gameMap = map;
        fallCount = 0;
        disappearCount = 30;
        arrow.setVisible(false);
        attack = false;
        hitMonster = false;
    }

    public void initializeLeft(){
        arrow.addFrame(R.drawable.arrowleft);
        arrow.setDelay(2);
        direction = "left";
        noPower = false;
    }

    public void initializeRight(){
        arrow.addFrame(R.drawable.arrowright);
        arrow.setDelay(2);
        direction = "right";
        noPower = false;
    }

    public void attack(boolean state, int x, int y, int delay){
        attack = state;
        mainx = x;
        mainy = y;
        arrow.setLocation(mainx, mainy+15);
        fallCount = delay;
        arrow.setVisible(true);
        noPower = false;
        hitMonster = false;
    }

    public void shot(){
        if(attack){
            if(direction.contains("left") || direction.contains("standingLeft")){
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
                        if(disappearCount%2==0){
                            arrow.setVisible(false);
                        }else{
                            arrow.setVisible(true);
                        }
                    }else{
                        attack = false;
                    }
                }
                if(arrow.getX() < -20){
                    arrow.setLocation(600, arrow.getY());
                }
            }
            if(direction.contains("right") || direction.contains("standingRight")){
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
                        if(disappearCount%2==0){
                            arrow.setVisible(false);
                        }else{
                            arrow.setVisible(true);
                        }
                    }else{
                        attack = false;
                    }
                }
                if(arrow.getX() > 600){
                    arrow.setLocation(0, arrow.getY());
                }
            }
        }

    }

    public boolean getAttackState(){
        return attack;
    }

    @Override
    public void move(){
        arrow.move();
        shot();
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