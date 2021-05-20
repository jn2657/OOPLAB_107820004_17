package tw.edu.ntut.csie.game.monster;

public interface GameMonster {
    public void initialize(int monsterStep, int height);
    public void setIskilled();
    public boolean isKilled();
    public void setLocation(int x, int y);
    public int getX();
    public int getY();
    public void regular();
    public void animePlay();
    public void release();
    public void move();
    public void show();
    public void setVisible(boolean visible);
    public void setDirection(int direction);
    public void setCurrentFrame(int frame);
}
