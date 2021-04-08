package tw.edu.ntut.csie.game.state;

import java.util.Timer;
import java.util.TimerTask;
import android.os.Handler;

import tw.edu.ntut.csie.game.GameObject;
import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.extend.Animation;

public class Charactor implements GameObject {
    private Animation main;
    private Animation arrowLeft;
    private Animation arrowRight;

    private Timer timer;
    private TimerTask timerTask;

    private int arrowMoveCount;
    private int disppearCount;
    private int jumpHeight;
    private int life;
    private int count;
    private GameMap gameMap;
    private String direction;
    private Handler handler;
    private Runnable runnable;


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
        life = 3;
    }

    public void initialize(GameMap map){
//        Context context = null;
//        String s = "flower";
//        for(int i = 1; i < 6; i++){
//            int id = context.getResources().getIdentifier("flower"+String.valueOf(i), "drawable", context.getPackageName());
//            main.addFrame(id);
//        }
        main.setLocation(500, 260);
        main.addFrame(R.drawable.flower1);
        main.addFrame(R.drawable.flower2);
        main.addFrame(R.drawable.flower3);
        main.addFrame(R.drawable.flower4);
        main.addFrame(R.drawable.flower5);
        main.setDelay(2);

        gameMap = map;
    }

    public void jump(int j){
        this.jumpHeight = j;
        if(timer == null){
            timer = new Timer();
        }
        timer.scheduleAtFixedRate(timerTask = new TimerTask() {
            @Override
            public void run() {
                if(jumpHeight>0){
                    if(gameMap.isWalkable(main.getX(), main.getY()-15)){
                        main.setLocation(main.getX(), main.getY() - 15);
                        jumpHeight--;
                    }else{
                        jumpHeight = 0;
                    }

                }
                if(jumpHeight<=0){
                    if(gameMap.isWalkable(main.getX(), main.getY()+15)){
                        main.setLocation(main.getX(), main.getY() + 15);
                        jumpHeight--;
                    }else{
                        stopTimer();
                    }
                }
            }

        }, 200, 100);
    }

//    public void jump(int j){
//        jumpHeight = j;
//        System.out.println("test");
//        if(handler == null){
//            handler = new Handler();
//        }
//        if(runnable == null){
//            runnable = new Runnable() {
//                @Override
//                public void run() {
//                    if(jumpHeight>0){
//                        if(gameMap.isWalkable(main.getX(), main.getY()-15)){
//                            main.setLocation(main.getX(), main.getY() - 15);
//                            jumpHeight--;
//                            handler.postDelayed(this, 100);
//                        }else{
//                            jumpHeight = 0;
//                            handler.postDelayed(this, 100);
//                        }
//
//                    }
//                    if(jumpHeight<=0){
//                        if(gameMap.isWalkable(main.getX(), main.getY()+15)){
//                            main.setLocation(main.getX(), main.getY() + 15);
//                            jumpHeight--;
//                            handler.postDelayed(this, 100);
//                        }else{
//                            System.out.println("over");
//                            handler.removeCallbacks(this);
//                        }
//                    }
//                }
//            };
//        }
//
//        handler.postDelayed(runnable, 200);
//    }


    public void arrowMove(int delay){
        arrowMoveCount = 10;
        count = delay;
        if(direction.equals("right")){
            arrowRight.setLocation(main.getX(), main.getY()-15);
            while(gameMap.isWalkable(arrowRight.getX()+15, arrowRight.getY())){ //無限遠限制
                System.out.println("test1");
                if(--delay <= 0){
                    if(arrowMoveCount <= 2){
                        if(gameMap.isWalkable(arrowRight.getX()+15, arrowRight.getY()+15)){
                            arrowRight.setLocation(arrowRight.getX()+15, arrowRight.getY()+15);
                        }else{
                            arrowRight.setLocation(arrowRight.getX()+15, arrowRight.getY());
                        }
                    }else{
                        arrowRight.setLocation(arrowRight.getX()+15, arrowRight.getY());
                    }
                    arrowMoveCount--;
                    delay = count;
                }
                System.out.println("test1-1");
            }
            System.out.println("test2");
            while(true){
                if(--delay <= 0){
                    if(--disppearCount <= 0){
                        if(arrowRight.getVisible()){
                            arrowRight.setVisible(false);
                        }else {
                            arrowRight.setVisible(true);
                        }
                    }
                    if(disppearCount == -10){
                        arrowRight.setVisible(false);
                        break;
                    }
                    delay = count;
                }

            }
            System.out.println("test3");

        }
    }

    public void shot(){
        if(direction.equals("right")){
            if(arrowRight == null){
                arrowRight = new Animation();
                arrowRight.addFrame(R.drawable.arrow_right);
            }
            arrowMove(20);
        }
        if(direction.equals("left")){
            if(arrowLeft == null){
                arrowLeft = new Animation();
                arrowLeft.addFrame(R.drawable.arrow_left);
            }
            arrowMove(20);
        }
    }

    public void setDirection(String d){
        direction = d;
    }

    @Override
    public void move(){
        main.move();
    }

    @Override
    public void show(){
        main.show();
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

    public int getHeight(){
        return jumpHeight;
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
}
