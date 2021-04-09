package tw.edu.ntut.csie.game.state;

import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.extend.Animation;

public class Monster extends Animation {
    private Animation monster;
    private boolean iskilled;
    private int xmax;
    private int xmin;

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
    }

    public void initialize(){
        iskilled = false;
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

    public void setMoveBorder(int xleft, int xright){
        xmax = xright;
        xmin = xleft;
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

    public void regularmove(){

    }


}
