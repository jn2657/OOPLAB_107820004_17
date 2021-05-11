package tw.edu.ntut.csie.game.monster;

public interface GameMonster {
    public void initialize(int x);
    public void setIskilled();
    public void regular();
    public void animePlay();
    public int getX();
    public int getY();
    public void show();
    public void move();
    public void release();
    public boolean getIfDead();
}
