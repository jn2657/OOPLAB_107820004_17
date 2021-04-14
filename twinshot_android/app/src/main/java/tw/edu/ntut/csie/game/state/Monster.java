package tw.edu.ntut.csie.game.state;

import java.util.Timer;
import java.util.TimerTask;

import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.extend.Animation;

public class Monster extends Animation {
    private Animation monster;
    private boolean iskilled;
    private Timer timer;
    private TimerTask timerTask;
    private int step;
    private int direction;
    private double speed;
    private int selfX;
    private int selfY;

    public Monster(){
        monster = new Animation();
        monster.setLocation(0,0);
        monster.addFrame(R.drawable.monsterleft1);
        monster.addFrame(R.drawable.monsterleft2);
        monster.addFrame(R.drawable.monsterleft3);
        monster.addFrame(R.drawable.monsterleft4);
        monster.addFrame(R.drawable.monsterleft5);
        monster.addFrame(R.drawable.monsterleft6);
        monster.addFrame(R.drawable.monsterleft7);
        monster.addFrame(R.drawable.monsterleft8);
        monster.addFrame(R.drawable.monsterleft9);
        monster.addFrame(R.drawable.monsterleft10);
        monster.addFrame(R.drawable.monsterleft11);
        monster.setDelay(2);
        selfX = monster.getX();
        selfY = monster.getY();
    }

    public void initialize(){
        iskilled = false;
        step = 30;
        direction = 0;
    }

    public void setIskilled(){
        iskilled = true;
        monster.setVisible(false);
        monster.release();
        monster = null;
    }

    public void setLocation(int x, int y){
        monster.setLocation(x, y);
    }

    public int getX(){
        return monster.getX();
    }

    public int getY(){
        return monster.getY();
    }

    public void release(){
        monster.release();
        monster = null;
    }

    public void move(){
        monster.move();
    }

    public void show(){
        monster.show();
    }

    public void regular(){
        speed = 2;
        if (step <= 0 && direction == 0){
            direction = 1;
            step = 30;
        }else if (step <= 0 && direction == 1){
            direction = 0;
            step = 30;
        }else{
            if (direction == 0){
                monster.setLocation((int)(monster.getX() + speed), monster.getY());
            }else{
                monster.setLocation((int)(monster.getX() - speed), monster.getY());
            }
        }
        step--;
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

}
