package tw.edu.ntut.csie.game.map;

import java.util.List;

import tw.edu.ntut.csie.game.GameObject;
import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.monster.GameMonster;
import tw.edu.ntut.csie.game.monster.MonsterBuilder;
import tw.edu.ntut.csie.game.state.Arrow;
import tw.edu.ntut.csie.game.monster.GameMonster;

public class Map3 implements GameObject, GameMap {
    private MovingBitmap block, block1, block2, block3, block4;
    private MovingBitmap pillar, pillar1, pillar2, pillar3;
    private MovingBitmap scores;
    private MonsterBuilder monsterBuilder;

    private int[][] map = {
            {0,0,1,8,0,0,0,0,8,8,8,8,8,8,8,8,8,8,8,8,8,0,0,0,0,8,1,0,0},
            {0,0,1,8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8,1,0,0},
            {0,0,1,8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8,1,0,0},
            {0,0,1,8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8,1,0,0},
            {0,0,1,8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8,1,0,0},
            {0,0,1,8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8,1,0,0},
            {0,0,1,8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8,1,0,0},
            {0,0,1,8,9,9,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,9,9,8,1,0,0},
            {0,0,1,8,8,8,8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8,8,8,8,1,0,0},
            {0,0,1,8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8,1,0,0},
            {0,0,1,8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8,1,0,0},
            {0,0,1,8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8,1,0,0},
            {0,0,1,8,0,0,0,0,0,0,0,0,1,3,2,1,1,0,0,0,0,0,0,0,0,8,1,0,0},
            {0,0,1,8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8,1,0,0},
            {0,0,1,8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8,1,0,0},
            {0,0,1,8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8,1,0,0},
            {0,0,1,8,0,0,0,0,8,8,8,8,8,8,8,8,8,8,8,8,8,0,0,0,0,8,1,0,0}};

    //大地圖左上角座標
    private final int X = 0;
    private final int Y = 0;
    //小地圖寬度與高度
    private final int MW = 23;
    private final int MH = 23;

    public Map3(){
        block = new MovingBitmap(R.drawable.block);
        block1 = new MovingBitmap(R.drawable.block1);
        block2 = new MovingBitmap(R.drawable.block2);
        block3 = new MovingBitmap(R.drawable.block3);
        block4 = new MovingBitmap(R.drawable.block4);
        pillar = new MovingBitmap(R.drawable.pillar);
        pillar1 = new MovingBitmap(R.drawable.pillar1);
        pillar2 = new MovingBitmap(R.drawable.pillar2);
        pillar3 = new MovingBitmap(R.drawable.pillar3);
        scores = new MovingBitmap(R.drawable.scores);
        monsterBuilder = new MonsterBuilder();
        monsterBuilder.add(3, 200,95, 130, 160);
        scores.setLocation(453,348);
    }

    @Override
    public int getInitialPositionX(){
        return 310;
    }

    @Override
    public int getInitialPositionY() {
        return 321;
    }

    @Override
    public void release(){
        block.release();
        block1.release();
        block2.release();
        block3.release();
        block4.release();
        pillar.release();
        pillar1.release();
        pillar2.release();
        pillar3.release();
        monsterBuilder.release();
        scores.release();

        block = null;
        block1 = null;
        block2 = null;
        block3 = null;
        block4 = null;
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
                    case 8:
                        block3.setLocation(X+(MW*j), Y+(MH*i));
                        block3.show();
                        break;
                    case 9:
                        block4.setLocation(X+(MW*j), Y+(MH*i));
                        block4.show();
                        break;
                    default:
                        break;
                }
            }
        }
        monsterBuilder.show();
    }


    public boolean isWalkable_right(int x, int y){
        if (x > 92 && x < 529) {
            if (y < 322 && y > 184) {
                return true;
            } else if (y > 23 && y < 161 - 43) {
                return true;
            } else {
                return false;
            }
        }else {
            return false;
        }
    }
    public boolean isWalkable_left(int x, int y){
        if (x > 92 && x < 529) {
            if (y < 322 && y > 184) {
                return true;
            }else if (y > 23 && y < 161 - 43) {
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }
    public boolean isWalkable_down(int x, int y) {
        if (y < 23 && (x > 460 || x < 184)){
            return true;
        }else if (y > 23 && y < 161-43){
            return true;
        }else if(y > 161){
            if ((x <= 253 && x > 161) || (x > 368 && x < 460)){
                if (y < 322) {
                    return true;
                }else {
                    return false;
                }
            }else if (x < 184 || x > 460){
                    return true;
            }else {
                if (y < 322 && y > 235){
                    return true;
                }else if (y < 231){
                    return true;
                }else {
                    return false;
                }
            }
        }else {
            return false;
        }
    }
    public boolean isWalkable_up(int x, int y) {
        if (y <= 23 && (x > 506 || x < 138)){
            return true;
        }else if (y > 23 && y < 161-43){
            return true;
        }else if(y > 161){
            return true;
        }else {
            return false;
        }
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
    public boolean superJump(int x, int y) {
        return y <= 161 - 43 && y >= 115 && ((x > 506 || x < 120));
    }
    public List<GameMonster> getMonsterList(){
        return monsterBuilder.getMonsterList();
    }

    @Override
    public MonsterBuilder getMonsterBuilder() {
        return monsterBuilder;
    }

    public int getLevel() { return 3; }
}