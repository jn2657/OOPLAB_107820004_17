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
    private int mainx,mainy;
    boolean noPower;

    public Arrow(GameMap map){
        arrow = new Animation();
        direction = null;
        gameMap = map;
        fallCount = 0;
        disappearCount = 30;
        arrow.setVisible(false);
        attack = false;
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

//    public void shot(int x, int y, int delay){
//        fallCount = delay;
//        arrow.setLocation(x, y);
//        System.out.println(x);
//        System.out.println(y);
//        arrow.move();
//        arrow.show();
//        if(timer == null){
//            timer = new Timer();
//        }
//        timer.scheduleAtFixedRate(timerTask = new TimerTask() {
//            @Override
//            public void run() {
//                if(direction.contains("left")){
//                    if(gameMap.isWalkable_down_left(arrow.getX()-15, arrow.getY()) && fallCount > 0){
//                        arrow.setLocation(arrow.getX()-15, arrow.getY());
//                        fallCount--;
//                    }else if(gameMap.isWalkable_down_left(arrow.getX()-15, arrow.getY()+5) && fallCount <= 0){
//                        arrow.setLocation(arrow.getX()-15, arrow.getY()+5);
//                        fallCount--;
//                    }else{
//                        disappearCount--;
//                        if(disappearCount != 0){
//                            int i = (int)(Math.random()*99);
//                            if(i%2==0){
//                                arrow.setVisible(true);
//                            }else{
//                                arrow.setVisible(false);
//                            }
//                        }else{
//                            stopTimer();
//                            release();
//                        }
//                    }
//                }
//                if(direction.contains("right")){
//                    if(gameMap.isWalkable_up_right(arrow.getX()+15, arrow.getY()) && fallCount > 0){
//                        arrow.setLocation(arrow.getX()+15, arrow.getY());
//                        fallCount--;
//                    }else if(gameMap.isWalkable_up_right(arrow.getX()+15, arrow.getY()+5) && fallCount <= 0){
//                        arrow.setLocation(arrow.getX()+15, arrow.getY()+5);
//                        fallCount--;
//                    }else{
//                        disappearCount--;
//                        if(disappearCount != 0){
//                            int i = (int)(Math.random()*99);
//                            if(i%2==0){
//                                arrow.setVisible(true);
//                            }else{
//                                arrow.setVisible(false);
//                            }
//                        }else{
//                            stopTimer();
//                            release();
//                        }
//                    }
//                }
//            }
//        }, 100, 500);
//    }
    public void attack(boolean state, int x, int y, int delay){
        attack = state;
        mainx = x;
        mainy = y;
        arrow.setLocation(mainx, mainy+15);
        fallCount = delay;
        arrow.setVisible(true);
    }

    public void shot(){
        if(attack){
            if(direction.contains("left")){
                if(gameMap.isWalkable_up_right(arrow.getX()-20, arrow.getY()) && fallCount > 0){
                    arrow.setLocation(arrow.getX()-20, arrow.getY());
                    fallCount--;
                }else if(gameMap.isWalkable_up_right(arrow.getX()-20, arrow.getY()+5)){
                    arrow.setLocation(arrow.getX()-20, arrow.getY()+5);
                }else if(gameMap.isWalkable_up_right(arrow.getX()-20, arrow.getY())){
                    arrow.setLocation(arrow.getX()-20, arrow.getY());
                    noPower = true;
                }else{
                    noPower = true;
                    disappearCount--;
                    if(disappearCount != 0){
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
            if(direction.contains("right")){
                if(gameMap.isWalkable_up_right(arrow.getX()+20, arrow.getY()) && fallCount > 0){
                    arrow.setLocation(arrow.getX()+20, arrow.getY());
                    fallCount--;
                }else if(gameMap.isWalkable_up_right(arrow.getX()+20, arrow.getY()+5)){
                    arrow.setLocation(arrow.getX()+20, arrow.getY()+5);
                }else if(gameMap.isWalkable_up_right(arrow.getX()+20, arrow.getY())){
                    arrow.setLocation(arrow.getX()+20, arrow.getY());
                    noPower = true;
                }else{
                    noPower = true;
                    disappearCount--;
                    if(disappearCount != 0){
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

    public void release(){
        arrow.release();
        arrow = null;
    }
}
