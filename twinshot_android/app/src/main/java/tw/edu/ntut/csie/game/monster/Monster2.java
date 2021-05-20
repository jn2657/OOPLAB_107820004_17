package tw.edu.ntut.csie.game.monster;

import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.extend.Animation;

public class Monster2 extends Animation implements GameMonster {
    private Animation monster;
    public boolean iskilled;
    private int step;
    private int initStep;
    private int direction;
    private double speed;
    boolean turned;
    private int jumpStep, fallStep;
    private boolean firstShot, godMode;
    private boolean shotAgain;
    private int stay, basicHeight;
    private int initY;

    public Monster2() {
        monster = new Animation();
        monster.setLocation(0, 0);
        monster.addFrame(R.drawable.monster1_2);
        monster.addFrame(R.drawable.monster1_1);
        monster.addFrame(R.drawable.monster1_2);
        monster.addFrame(R.drawable.monster1_3);
        monster.addFrame(R.drawable.monster1_shot1); //4
        monster.addFrame(R.drawable.monster1_shot2);
        monster.addFrame(R.drawable.monster1_shot3);
        monster.addFrame(R.drawable.monster1_shot4);
        monster.addFrame(R.drawable.monster1_shot5);
        monster.addFrame(R.drawable.monster1_shot6);
        monster.addFrame(R.drawable.monster1_shot7);
        monster.addFrame(R.drawable.monster1_shot8);
        monster.addFrame(R.drawable.monster1_shot9);
        monster.addFrame(R.drawable.monster1_shot10); //13
        monster.addFrame(R.drawable.monster1_shot11);
        monster.addFrame(R.drawable.monster1_shot12);
        monster.addFrame(R.drawable.monster1_shot13);
        monster.addFrame(R.drawable.monster1_shot14);
        monster.addFrame(R.drawable.d1); //18
        monster.addFrame(R.drawable.d2);
        monster.addFrame(R.drawable.d3);
        monster.addFrame(R.drawable.d4);
        monster.addFrame(R.drawable.d5);
        monster.addFrame(R.drawable.d6);
        monster.addFrame(R.drawable.d7);
        monster.addFrame(R.drawable.d8);
        monster.addFrame(R.drawable.d9);
        monster.addFrame(R.drawable.d10);
        monster.addFrame(R.drawable.d11);
        monster.addFrame(R.drawable.d12);
        monster.addFrame(R.drawable.d13);
        monster.addFrame(R.drawable.d14);
        monster.addFrame(R.drawable.d15);
        monster.addFrame(R.drawable.d16);
        monster.addFrame(R.drawable.d17);
        monster.addFrame(R.drawable.d18);
        monster.addFrame(R.drawable.d19);
        monster.addFrame(R.drawable.d20);
        monster.addFrame(R.drawable.d21);
        monster.addFrame(R.drawable.d22);
        monster.addFrame(R.drawable.d23);
        monster.addFrame(R.drawable.d24);

        monster.setDelay(2);
    }

    public void initialize(int monsterStep, int height) {
        iskilled = false;
        step = monsterStep;
        initStep = monsterStep;
        basicHeight = height;
        jumpStep = 5;
        fallStep = 30;
        direction = 0;
        turned = false;
        firstShot = false;
        shotAgain = false;
        godMode = false;
        stay = 40;
    }

    public void setIskilled() {
        iskilled = true;
    }

    public void setFirstShot() {
        firstShot = true;
    }

    public boolean getFirstShot() {
        return firstShot;
    }

    public boolean isKilled() {
        return iskilled;
    }

    public void setLocation(int x, int y) {
        monster.setLocation(x, y);
    }

    public int getX() {
        return monster.getX();
    }

    public int getY() {
        return monster.getY();
    }

    @Override
    public void release() {
        monster.release();
        monster = null;
    }

    @Override
    public void move() {
        if (monster != null) {
            monster.move();
            animePlay();
            adjustHeight();
        }
    }

    @Override
    public void show() {
        monster.show();
    }

    @Override
    public void setDirection(int direction) {

    }

    @Override
    public void setCurrentFrame(int frame) {

    }

    public void regular() {
        if (!godMode){
            if (monster != null && !iskilled) {
                speed = 2;
                if (step <= 0 && direction == 0) {
                    direction = 1;
                    step = initStep;
                } else if (step <= 0 && direction == 1) {
                    direction = 0;
                    step = initStep;
                } else {
                    if (direction == 0) {
                        monster.setLocation((int) (monster.getX() + speed), monster.getY());
                    } else {
                        monster.setLocation((int) (monster.getX() - speed), monster.getY());
                    }
                }
                step--;
            }
        }
    }

    public void setGodMode(){
        godMode = true;
    }

    public boolean isGodMode(){
        return godMode;
    }

    public void animePlay() {
        if (iskilled) {
            if (monster.getCurrentFrameIndex() <= 21) {
                monster.setCurrentFrameIndex(22);
            }
            speed = 5;
            if (jumpStep <= 0) {
                fallStep--;
                monster.setLocation(monster.getX(), (int) (monster.getY() + speed));
                if (monster.getY() + speed >= 368) {
                    monster.setVisible(false);
                    monster.setLocation(650, 400);
                }
            } else {
                jumpStep--;
                monster.setLocation(monster.getX(), (int) (monster.getY() - speed));
            }
        } else if (godMode) {
            if (stay > 0) {
                stay--;
                if (monster.getCurrentFrameIndex() >= 12) {
                    firstShot = true;
                    monster.setCurrentFrameIndex(12);
                }
            } else {
                firstShot = false;
                if(monster.getCurrentFrameIndex() <= 12){
                    monster.setCurrentFrameIndex(13);
                }
                if (monster.getCurrentFrameIndex() >= 17) {
                    monster.setCurrentFrameIndex(0);
                    godMode = false;
                    stay = 40;
                }
            }
        } else {
            if (monster.getCurrentFrameIndex() >= 3) {
                monster.setCurrentFrameIndex(0);
            }
        }
    }

    public void adjustHeight(){
        if(!iskilled) {
            monster.setLocation(monster.getX(), basicHeight - monster.getHeight());
        }
    }
}