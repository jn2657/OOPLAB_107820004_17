package tw.edu.ntut.csie.game.map;

import java.util.ArrayList;
import java.util.List;

import tw.edu.ntut.csie.game.GameObject;
import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.monster.MonsterBuilder;
import tw.edu.ntut.csie.game.state.Arrow;
import tw.edu.ntut.csie.game.monster.Monster;

public class Map2 implements GameObject, GameMap {
    private MovingBitmap block;
    private MovingBitmap block1;
    private MovingBitmap block2;
    private MovingBitmap pillar;
    private MovingBitmap pillar1;
    private MovingBitmap pillar2;
    private MovingBitmap pillar3;
    private MovingBitmap pillar4;
    private MonsterBuilder monsterBuilder;
    private MovingBitmap scores;

    private int[][] map = {
            {0,0,1,1,3,1,1,3,1,1,3,3,2,2,1,3,3,1,1,1,3,1,2,3,1,1,1,0,0},
            {0,0,8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8,0,0},
            {0,0,9,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,9,0,0},
            {0,0,9,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,9,0,0},
            {0,0,9,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,9,0,0},
            {0,0,9,0,0,0,0,0,0,1,1,1,1,3,2,1,2,1,1,1,0,0,0,0,0,0,9,0,0},
            {0,0,9,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,9,0,0},
            {0,0,2,2,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,2,3,1,0,0},
            {0,0,8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8,0,0},
            {0,0,9,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,9,0,0},
            {0,0,9,0,0,0,0,0,0,1,1,2,3,3,2,1,1,1,3,1,0,0,0,0,0,0,9,0,0},
            {0,0,9,0,0,0,0,0,0,4,0,0,0,0,0,0,0,0,0,4,0,0,0,0,0,0,9,0,0},
            {0,0,9,0,0,0,0,0,0,9,0,0,0,0,0,0,0,0,0,9,0,0,0,0,0,0,9,0,0},
            {0,0,9,0,0,0,0,0,0,9,0,0,0,0,0,0,0,0,0,9,0,0,0,0,0,0,9,0,0},
            {0,0,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,1,1,3,3,1,2,2,3,0,0},
            {0,0,5,0,0,0,0,0,0,5,0,0,0,0,0,0,0,0,0,5,0,0,0,0,0,0,5,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};

    //大地圖左上角座標
    private final int X = 0;
    private final int Y = 0;
    //小地圖寬度與高度
    private final int MW = 23;
    private final int MH = 23;

    public Map2(){
        block = new MovingBitmap(R.drawable.block);
        block1 = new MovingBitmap(R.drawable.block1);
        block2 = new MovingBitmap(R.drawable.block2);
        pillar = new MovingBitmap(R.drawable.pillar);
        pillar1 = new MovingBitmap(R.drawable.pillar1_1);
        pillar2 = new MovingBitmap(R.drawable.pillar2);
        pillar3 = new MovingBitmap(R.drawable.pillar3);
        pillar4 = new MovingBitmap(R.drawable.pillar4);
        scores = new MovingBitmap(R.drawable.scores);
        monsterBuilder = new MonsterBuilder();
        monsterBuilder.add(1, 500, 285);
        monsterBuilder.add(1, 100, 285);
        scores.setLocation(453,348);
    }

    @Override
    public int getInitialPositionX(){
        return 300;
    }

    @Override
    public int getInitialPositionY() {
        return 182;
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
        pillar4.release();
        monsterBuilder.release();
        scores.release();

        block = null;
        block1 = null;
        block2 = null;
        pillar = null;
        pillar1 = null;
        pillar2 = null;
        pillar3 = null;
        pillar4 = null;
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
                        pillar4.setLocation(X+(MW*j), Y+(MH*i));
                        pillar4.show();
                        break;
                    default:
                        break;
                }
            }
        }
        monsterBuilder.show();
    }

    public boolean isWalkable_down_left(int x, int y){
//        int i = y/23+2;
//        int j;
//        if(x%23 > 12.5){
//            j = x/23;
//        }else{
//            j = x/23+1;
//        }
//        if(map[i][j] != 0){
//            return false;
//        }else{
//            return true;
//        }
        if (x <= 621 && x > 500 && y < 161-46){
            return true;
        }else if (x <= 621 && x > 500 && y < 322-46 && y > 184){
            return true;
        }else if (x <= 506 && x > 450 && y < 322-46){
            return true;
        }else if (x <= 455 && x > 175 && y < 232-46 && y > 138){
            return true;
        }else if (x <= 455 && x > 175 && y < 115-46){
            return true;
        }else if (x <= 175 && x > 138 && y < 322-46){
            return true;
        }if (x <= 155 && x > 63 && y < 161-46){
            return true;
        }else if (x <= 155 && x > 63 && y < 322-46 && y > 186){
            return true;
        }else {
            return false;
        }
    }

    public boolean isWalkable_up_right(int x, int y){
//        int i = y/23+1;
//        int j = x/23+2;
//        if(map[i][j] != 0){
//            return false;
//        }else{
//            return true;
//        }
        if (x <= 556 && x > 500 && y < 162-46){
            return true;
        }else if (x <= 556 && x > 500 && y < 323-46 && y > 184){
            return true;
        }else if (x <= 506 && x > 450 && y < 323-46){
            return true;
        }else if (x <= 455 && x > 165 && y < 232-46){
            return true;
        }else if (x <= 168 && x > 138 && y < 323-46){
            return true;
        }if (x <= 155 && x > 46 && y < 162-46){
            return true;
        }else if (x <= 155 && x > 46 && y < 323-46 && y > 186){
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
    public List<Monster> getMonsterList(){
        return monsterBuilder.getMonsterList();
    }

    @Override
    public MonsterBuilder getMonsterBuilder(){
        return monsterBuilder;
    }

    public int getLevel() { return 2; }
}