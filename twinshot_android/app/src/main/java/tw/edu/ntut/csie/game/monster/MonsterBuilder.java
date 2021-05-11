package tw.edu.ntut.csie.game.monster;

import java.util.ArrayList;
import java.util.List;

import tw.edu.ntut.csie.game.state.Arrow;

public class MonsterBuilder {
    private List<GameMonster> MonsterList;

    public MonsterBuilder(){
        MonsterList = new ArrayList<>();
    }

    public void add(int monsterNum, int monsterStep, int x, int y){
        switch (monsterNum){
            case 1:
                GameMonster m = new Monster1();
                m.initialize(monsterStep);
                m.setLocation(x, y);
                MonsterList.add(m);
                break;
            case 2:
                GameMonster m2 = new Monster2();
                m2.initialize(monsterStep);
                m2.setLocation(x, y);
                MonsterList.add(m2);
                break;
            default:
                break;
        }
    }

    public void show(){
        for(GameMonster m: MonsterList){
            m.show();
        }
    }

    public void move(){
        for(GameMonster m: MonsterList){
            if(m != null){
                m.move();
                m.regular();
            }
        }
    }

    public void release(){
        for(GameMonster m: MonsterList){
            m.release();
            m = null;
        }
    }

    public boolean shootingMonster_Left(int x, int y, Arrow arrow){
        for(GameMonster monster: MonsterList){
            if(x > monster.getX()+23 || x < monster.getX()){
                continue;
            }else if(y > monster.getY()+23 || y < monster.getY()-10){
                continue;
            }else{
                if(!arrow.noPower){
                    if (!(monster instanceof Monster2)){
                        System.out.println("true");
                        monster.setIskilled();
                        arrow.hitMonster = true;
                        return true;
                    }else{
                        ((Monster2) monster).setGodMode();
                        arrow.hitMonster = true;
                        if(((Monster2) monster).getFirstShot()) {
//                            ((Monster2) monster).setFirstShot();
                            System.out.println("false");
                            monster.setIskilled();
//                        continue;
                            return true;
                        }
                        return false;
                    }
                }
            }
        }
        return false;
    }

    public boolean shootingMonster_Right(int x, int y, Arrow arrow){
        for(GameMonster monster: MonsterList){
            if(x+46 > monster.getX()+10 || x+46 < monster.getX()){
                continue;
            }else if(y > monster.getY()+23 || y < monster.getY()-10){
                continue;
            }else{
                if(!arrow.noPower){
                    if(monster instanceof Monster2 && !((Monster2) monster).getFirstShot()){
                        ((Monster2) monster).setFirstShot();
                    }else if (!(monster instanceof Monster2) || ((Monster2) monster).getFirstShot()){
                        monster.setIskilled();
                        arrow.hitMonster = true;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public List<GameMonster> getMonsterList(){
        return MonsterList;
    }

    public int checkScore(){
        int score = 0;
        for(GameMonster m: MonsterList){
            if(m.isKilled()){score += 100;}
        }
        return score;
    }
}
