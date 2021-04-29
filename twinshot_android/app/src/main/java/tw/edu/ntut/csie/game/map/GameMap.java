package tw.edu.ntut.csie.game.map;

import java.util.List;

import tw.edu.ntut.csie.game.GameObject;
import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.extend.Animation;
import tw.edu.ntut.csie.game.state.Arrow;
import tw.edu.ntut.csie.game.state.Monster;

public interface GameMap extends GameObject {
    public int getInitialPositionX();
    public int getInitialPositionY();
    public boolean isWalkable_down_left(int x, int y);
    public boolean isWalkable_up_right(int x, int y);
    public boolean arrowEnable_left(int x, int y, Arrow arrow);
    public boolean arrowEnable_right(int x, int y, Arrow arrow);
    public List<Monster> getMonsterList();
    public int getLevel();

}
