package tw.edu.ntut.csie.game.map;

import java.util.ArrayList;
import java.util.List;

import tw.edu.ntut.csie.game.GameObject;
import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.extend.Animation;
import tw.edu.ntut.csie.game.state.Arrow;
import tw.edu.ntut.csie.game.state.Monster;

public class Map2 implements GameObject, GameMap {
    private MovingBitmap block;
    private MovingBitmap block1;
    private MovingBitmap block2;
    private MovingBitmap pillar;
    private MovingBitmap pillar1;
    private MovingBitmap pillar2;
    private MovingBitmap pillar3;
    private MovingBitmap pillar4;
    private Monster monster;
    private Monster monster1;
    private List<Monster> MonsterList;
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
        pillar1 = new MovingBitmap(R.drawable.pillar1);
        pillar2 = new MovingBitmap(R.drawable.pillar2);
        pillar3 = new MovingBitmap(R.drawable.pillar3);
        pillar4 = new MovingBitmap(R.drawable.pillar4);
        scores = new MovingBitmap(R.drawable.scores);
        MonsterList = new ArrayList<Monster>();
        monster = new Monster();
        monster1 = new Monster();
        monster.initialize();
        monster.setLocation(500, 285);
        monster1.initialize();
        monster1.setLocation(100, 285);
        MonsterList.add(monster);
        MonsterList.add(monster1);
        scores.setLocation(453,348);
    }

    @Override
    public int getInitialPositionX(){
        return 300;
    }

    @Override
    public int getInitialPositionY() {
        return 170;
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
        monster.release();
        monster1.release();
        scores.release();

        block = null;
        block1 = null;
        block2 = null;
        pillar = null;
        pillar1 = null;
        pillar2 = null;
        pillar3 = null;
        pillar4 = null;
        monster = null;
        monster1 = null;
        MonsterList = null;
        scores = null;
    }

    @Override
    public void move(){
        if(monster != null){
            monster.move();
            monster.regular();
        }
        if(monster1 != null) {
            monster1.move();
            monster1.regular();
        }
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
        monster.show();
        monster1.show();
    }

    public boolean isWalkable_down_left(int x, int y){
        int i = y/23+2;
        int j;
        if(x%23 > 12.5){
            j = x/23;
        }else{
            j = x/23+1;
        }
        if(map[i][j] != 0){
            return false;
        }else{
            return true;
        }
//        if(x > 430 && x < 587 && y < 323-46){//345 - character height
//            return true;
//        }else if(x <= 430 && x > 204 && y < 279-46){
//            return true;
//        }else if(x <= 204 && x > 35 && y < 323-46){
//            return true;
//        }else{
//            return false;
//        }
    }

    public boolean isWalkable_up_right(int x, int y){
        int i = y/23+1;
        int j = x/23+2;
        if(map[i][j] != 0){
            return false;
        }else{
            return true;
        }
//        if(x > 413 && x < 587 && y < 323-46){//345 - character height
//            return true;
//        }else if(x <= 413 && x > 192 && y < 279-46 && y > 175-46){
//            return true;
//        }else if(x <= 192 && x > 35 && y < 323-46){
//            return true;
//        }else{
//            return false;
//        }
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

    public List<Monster> getMonsterList(){
        return MonsterList;
    }
}