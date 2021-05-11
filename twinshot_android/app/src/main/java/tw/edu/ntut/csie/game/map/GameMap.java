package tw.edu.ntut.csie.game.map;

import java.util.List;

import tw.edu.ntut.csie.game.GameObject;
import tw.edu.ntut.csie.game.monster.GameMonster;
import tw.edu.ntut.csie.game.monster.MonsterBuilder;
import tw.edu.ntut.csie.game.state.Arrow;
import tw.edu.ntut.csie.game.monster.GameMonster;

public interface GameMap extends GameObject {
    public int getInitialPositionX();
    public int getInitialPositionY();
    public boolean isWalkable_down(int x, int y);
    public boolean isWalkable_up(int x, int y);
    public boolean isWalkable_right(int x, int y);
    public boolean isWalkable_left(int x, int y);
    public boolean arrowEnable_left(int x, int y, Arrow arrow);
    public boolean arrowEnable_right(int x, int y, Arrow arrow);
    public List<GameMonster> getMonsterList();
    public MonsterBuilder getMonsterBuilder();
    public int getLevel();
    public default boolean superJump(int x, int y){ return false; }

}
