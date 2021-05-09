package tw.edu.ntut.csie.game.map;

import java.util.ArrayList;
import java.util.List;

import tw.edu.ntut.csie.game.GameObject;
import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.state.Arrow;
import tw.edu.ntut.csie.game.state.Monster;

public class Map3 implements GameObject, GameMap {
    private MovingBitmap block;
    private MovingBitmap block1;
    private MovingBitmap block2;
    private MovingBitmap block3;
    private MovingBitmap block4;
    private MovingBitmap pillar;
    private MovingBitmap pillar1;
    private MovingBitmap pillar2;
    private MovingBitmap pillar3;
    private Monster monster;
//    private Monster monster1;
    private List<Monster> MonsterList;
    private MovingBitmap scores;
    private int monsterStep = 200;

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
        MonsterList = new ArrayList<Monster>();
        monster = new Monster();
//        monster1 = new Monster();
        monster.initialize(monsterStep);
        monster.setLocation(95, 130);
//        monster1.initialize(monsterStep);
//        monster1.setLocation(100, 285);
        MonsterList.add(monster);
//        MonsterList.add(monster1);
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
        monster.release();
//        monster1.release();
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
        monster = null;
//        monster1 = null;
        MonsterList = null;
        scores = null;
    }

    @Override
    public void move(){
        if(monster != null){
            monster.move();
            monster.regular();
        }
//        if(monster1 != null) {
//            monster1.move();
//            monster1.regular();
//        }
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
        monster.show();
//        monster1.show();
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
        if (x <= 184 && x > 92 && y < 161-46){
            return true;
        }else if (x <= 552 && x > 483 && y < 161-46) {
            return true;
        }else if (x <= 483 && x > 184 && y < 161-46 && y > 23){
            return true;
        }else if (x <= 386 && x > 253){
            if(y < 281-46 && y > 276-46){
                return false;
            }else if(y < 322 && y > 317){
                return false;
            }else{
                return true;
            }
//            return true;
//        }else if (x <= 391 && x > 253 && (y > 184 || y < 179)){
//            return true;
//        }else if (x <= 391 && x > 253 && (y > 258 || y < 253)){
//            return true;
        }else if (x <= 483 && x > 386 && y < 322 && y > 184){
            return true;
        }else if (x <= 529 && x > 483 && y > 184){
            return true;
        }else if (x <= 253 && x > 184 && y < 322 && y > 184){
            return true;
        }else if (x <= 184 && x > 92 && y < 322 && y > 184){
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
        if (x <= 184 && x > 92 && y < 161-46){
            return true;
        }else if (x <= 552 && x > 483 && y < 161-46) {
            return true;
        }else if (x <= 478 && x > 184 && y < 161-46 && y > 23){
            return true;
        }else if (x <= 391 && x > 276 && y < 322 && y > 173){
            return true;
        }else if (x <= 506 && x > 391 && y < 368-46 && y > 184){
            return true;
        }else if (x <= 529 && x > 478 && y > 207){
            return true;
        }else if (x <= 276 && x > 161 && y < 368-46 && y > 184){
            return true;
        }else if (x <= 161 && x > 92 && y < 368-46 && y > 207){
            return true;
        }else {
            return false;
        }
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
        for(Monster monster: MonsterList){
            if(monster != null){
                if(x > monster.getX()+23 || x < monster.getX()){
                    continue;
                }else if(y > monster.getY()+23 || y < monster.getY()-10){
                    continue;
                }else{
                    if(!arrow.noPower){
                        monster.setIskilled();
                        arrow.hitMonster = true;
                        return false;
                    }
                }
            }
        }
        if(map[i][j] != 0){
            return false;
        }else{
            return true;
        }
    }

    public boolean arrowEnable_right(int x, int y, Arrow arrow){
        int i = y/23;
        int j = x/23+1;
        for(Monster monster: MonsterList){
            if(monster != null){
                if(x+46 > monster.getX()+10 || x+46 < monster.getX()){
                    continue;
                }else if(y > monster.getY()+23 || y < monster.getY()-10){
                    continue;
                }else{
                    if(!arrow.noPower){
                        monster.setIskilled();
                        arrow.hitMonster = true;
                        return false;
                    }
                }
            }
        }
        if(map[i][j] != 0){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public boolean superJump(int x, int y) {
        if (y <= 161-43 && y >= 115 && ((x > 506 || x < 120))){
            return true;
        }else {
            return false;
        }
    }

    public List<Monster> getMonsterList(){
        return MonsterList;
    }

    public int getLevel() { return 3; }
}