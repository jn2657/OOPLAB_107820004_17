package tw.edu.ntut.csie.game.map;

import java.util.List;

import tw.edu.ntut.csie.game.GameObject;
import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.monster.GameMonster;
import tw.edu.ntut.csie.game.monster.MonsterBuilder;
import tw.edu.ntut.csie.game.state.Arrow;
import tw.edu.ntut.csie.game.monster.GameMonster;

public class Map1 implements GameObject, GameMap {
    private MovingBitmap block, block1, block2;
    private MovingBitmap pillar, pillar1, pillar2, pillar3;
    private MovingBitmap scores;
    private MonsterBuilder monsterBuilder;

    private int[][] map = {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,3,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,4,0,4,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,1,3,2,2,3,1,2,3,1,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0},
            {0,8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8,0},
            {0,8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8,0},
            {0,8,0,0,0,0,0,0,0,0,1,3,1,1,2,2,1,3,1,0,0,0,0,0,0,0,0,8,0},
            {0,8,0,0,0,0,0,0,0,0,6,0,0,6,0,6,0,0,6,0,0,0,0,0,0,0,0,8,0},
            {0,1,3,1,3,1,3,2,1,2,0,0,0,0,0,0,0,0,0,1,1,3,3,1,2,2,3,1,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};

    //大地圖左上角座標
    private final int X = 0;
    private final int Y = 0;
    //小地圖寬度與高度
    private final int MW = 23;
    private final int MH = 23;

    public Map1(){
        block = new MovingBitmap(R.drawable.block);
        block1 = new MovingBitmap(R.drawable.block1);
        block2 = new MovingBitmap(R.drawable.block2);
        pillar = new MovingBitmap(R.drawable.pillar);
        pillar1 = new MovingBitmap(R.drawable.pillar1);
        pillar2 = new MovingBitmap(R.drawable.pillar2);
        pillar3 = new MovingBitmap(R.drawable.pillar3);
        scores = new MovingBitmap(R.drawable.scores);
        monsterBuilder = new MonsterBuilder();
        monsterBuilder.add(1, 80,435, 290, 323);
        monsterBuilder.add(1, 80,43, 290, 323);
        scores.setLocation(453,348);
    }

    @Override
    public int getInitialPositionX(){
        return 300;
    }

    @Override
    public int getInitialPositionY() {
        return 230;
    }

    @Override
    public void release(){
        block.release();
        block1.release();
        block2.release();
        pillar.release();
        pillar1.release();
        pillar2.release();
        pillar3.release();
        monsterBuilder.release();
        scores.release();

        block = null;
        block1 = null;
        block2 = null;
        pillar = null;
        pillar1 = null;
        pillar2 = null;
        pillar3 = null;
        scores = null;
    }

    @Override
    public void move(){
        monsterBuilder.move();
    }

    @Override
    public void show(){
        scores.show();
        for(int i = 0; i < 17; i++){
            for(int j = 0; j<29; j++){
                switch(map[i][j]){
                    case 1:
                        block.setLocation(X+(MW*j), Y+(MH*i));
                        block.show();
                        break;
                    case 2:
                        block1.setLocation(X+(MW*j), Y+(MH*i));
                        block1.show();
                        break;
                    case 3:
                        block2.setLocation(X+(MW*j), Y+(MH*i));
                        block2.show();
                        break;
                    case 4:
                        pillar.setLocation(X+(MW*j), Y+(MH*i));
                        pillar.show();
                        break;
                    case 5:
                        pillar1.setLocation(X+(MW*j), Y+(MH*i));
                        pillar1.show();
                        break;
                    case 6:
                        pillar2.setLocation(X+(MW*j), Y+(MH*i));
                        pillar2.show();
                        break;
                    case 7:
                        pillar3.setLocation(X+(MW*j), Y+(MH*i));
                        pillar3.show();
                        break;
                    default:
                        break;
                }
            }
        }
        monsterBuilder.show();
    }


    public boolean isWalkable_right(int x, int y){
        if (x > 35 && x < 587) {
            if (x >= 200 && x <= 430 && y < 322-43 && y > 276-43){
                return false;
            }else {
                return true;
            }
        }else {
            return false;
        }
    }
    public boolean isWalkable_left(int x, int y){
        if (x > 35 && x < 587) {
            if (x > 200 && x <= 437 && y < 322-43 && y > 276-43) {
                return false;
            }else {
                return true;
            }
        }else {
            return false;
        }
    }
    public boolean isWalkable_down(int x, int y){
        if ((x <= 200 || x >= 432) && y < 322-43){
            return true;
        }else if (x > 200 && x < 432 && y < 276-43){
            return true;
        }else {
            return false;
        }
    }
    public boolean isWalkable_up(int x, int y){
        return true;
    }

    public boolean arrowEnable_left(int x, int y, Arrow arrow){// map and monster check
        int i = y/23;
        int j = x/23+1;
        return !monsterBuilder.shootingMonster_Left(x, y, arrow) && map[i][j] == 0;
    }

    public boolean arrowEnable_right(int x, int y, Arrow arrow){
        int i = y/23;
        int j = x/23+1;
        return !monsterBuilder.shootingMonster_Right(x, y, arrow) && map[i][j] == 0;
    }

    @Override
    public List<GameMonster> getMonsterList(){
        return monsterBuilder.getMonsterList();
    }

    @Override
    public MonsterBuilder getMonsterBuilder(){
        return monsterBuilder;
    }

    public int getLevel() { return 1; }
}