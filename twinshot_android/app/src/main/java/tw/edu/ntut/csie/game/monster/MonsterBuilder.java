package tw.edu.ntut.csie.game.monster;

import java.util.ArrayList;
import java.util.List;

import tw.edu.ntut.csie.game.state.Arrow;

public class MonsterBuilder {
    private List<Monster> MonsterList;

    public MonsterBuilder(){
        MonsterList = new ArrayList<>();
    }

    public void add(int monsterNum, int x, int y){
        switch (monsterNum){
            case 1:
                Monster m = new Monster();
                m.initialize();
                m.setLocation(x, y);
                MonsterList.add(m);
                break;
            default:
                break;
        }
    }

    public void show(){
        for(Monster m: MonsterList){
            m.show();
        }
    }

    public void move(){
        for(Monster m: MonsterList){
            if(m != null){
                m.move();
                m.regular();
            }
        }
    }

    public void release(){
        for(Monster m: MonsterList){
            m.release();
            m = null;
        }
    }

    public boolean shootingMonster_Left(int x, int y, Arrow arrow){
        for(Monster monster: MonsterList){
            if(x > monster.getX()+23 || x < monster.getX()){
                continue;
            }else if(y > monster.getY()+23 || y < monster.getY()-10){
                continue;
            }else{
                if(!arrow.noPower){
                    monster.setIskilled();
                    arrow.hitMonster = true;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean shootingMonster_Right(int x, int y, Arrow arrow){
        for(Monster monster: MonsterList){
            if(x+46 > monster.getX()+10 || x+46 < monster.getX()){
                continue;
            }else if(y > monster.getY()+23 || y < monster.getY()-10){
                continue;
            }else{
                if(!arrow.noPower){
                    monster.setIskilled();
                    arrow.hitMonster = true;
                    return true;
                }
            }
        }
        return false;
    }

    public List<Monster> getMonsterList(){
        return MonsterList;
    }

    public int checkScore(){
        int score = 0;
        for(Monster m: MonsterList){
            if(m.iskilled){score += 100;}
        }
        return score;
    }
}
