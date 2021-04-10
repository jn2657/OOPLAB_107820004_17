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

    public Arrow(GameMap map){
        arrow = new Animation();
        direction = null;
        gameMap = map;
        fallCount = 0;
        disappearCount = 30;
    }

    public void initializeLeft(){
        arrow.addFrame(R.drawable.arrow_left);
        arrow.setDelay(2);
        direction = "left";
    }

    public void initializeRight(){
        arrow.addFrame(R.drawable.arrow_right);
        arrow.setDelay(2);
        direction = "right";
    }

    public void shot(int x, int y, int delay){
        fallCount = delay;
        arrow.setLocation(x, y);
        System.out.println(x);
        System.out.println(y);
        arrow.move();
        arrow.show();
        if(timer == null){
            timer = new Timer();
        }
        timer.scheduleAtFixedRate(timerTask = new TimerTask() {
            @Override
            public void run() {
                if(direction.contains("left")){
                    if(gameMap.isWalkable_down_left(arrow.getX()-15, arrow.getY()) && fallCount > 0){
                        arrow.setLocation(arrow.getX()-15, arrow.getY());
                        fallCount--;
                    }else if(gameMap.isWalkable_down_left(arrow.getX()-15, arrow.getY()+5) && fallCount <= 0){
                        arrow.setLocation(arrow.getX()-15, arrow.getY()+5);
                        fallCount--;
                    }else{
                        disappearCount--;
                        if(disappearCount != 0){
                            int i = (int)(Math.random()*99);
                            if(i%2==0){
                                arrow.setVisible(true);
                            }else{
                                arrow.setVisible(false);
                            }
                        }else{
                            stopTimer();
                            release();
                        }
                    }
                }
                if(direction.contains("right")){
                    if(gameMap.isWalkable_up_right(arrow.getX()+15, arrow.getY()) && fallCount > 0){
                        arrow.setLocation(arrow.getX()+15, arrow.getY());
                        fallCount--;
                    }else if(gameMap.isWalkable_up_right(arrow.getX()+15, arrow.getY()+5) && fallCount <= 0){
                        arrow.setLocation(arrow.getX()+15, arrow.getY()+5);
                        fallCount--;
                    }else{
                        disappearCount--;
                        if(disappearCount != 0){
                            int i = (int)(Math.random()*99);
                            if(i%2==0){
                                arrow.setVisible(true);
                            }else{
                                arrow.setVisible(false);
                            }
                        }else{
                            stopTimer();
                            release();
                        }
                    }
                }
            }
        }, 100, 500);
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

    public void release(){
        arrow.release();
        arrow = null;
    }
}
