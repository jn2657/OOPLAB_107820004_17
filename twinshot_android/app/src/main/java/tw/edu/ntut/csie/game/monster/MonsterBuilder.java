package tw.edu.ntut.csie.game.monster;

import java.util.ArrayList;
import java.util.List;

import tw.edu.ntut.csie.game.state.Arrow;

public class MonsterBuilder {
    private List<GameMonster> MonsterList;

    public MonsterBuilder(){
        MonsterList = new ArrayList<>();
    }

    public void add(int monsterNum, int monsterStep, int x, int y, int height){
        switch (monsterNum){
            case 1:
                GameMonster m = new Monster1();
                m.initialize(monsterStep, height);
                m.setLocation(x, y);
                MonsterList.add(m);
                break;
            case 2:
                GameMonster m2 = new Monster2();
                m2.initialize(monsterStep, height);
                m2.setLocation(x, y);
                MonsterList.add(m2);
                break;
            case 3:
                GameMonster m3 = new Monster3();
                GameMonster m3_1 = new Monster3();
                GameMonster m3_2 = new Monster3();
                GameMonster m3_3 = new Monster3();
                GameMonster m3_4 = new Monster3();
                GameMonster m3_5 = new Monster3();
                GameMonster m3_6 = new Monster3();
                MonsterList.add(m3);
                MonsterList.add(m3_1);
                MonsterList.add(m3_2);
                MonsterList.add(m3_3);
                MonsterList.add(m3_4);
                MonsterList.add(m3_5);
                MonsterList.add(m3_6);
                for(int i=0; i<MonsterList.size(); i++) {
                    MonsterList.get(i).initialize(monsterStep, height);
                    MonsterList.get(i).setLocation(x, y);
                    if(i != 0){
                        MonsterList.get(i).setVisible(false);
                    }
                }
                break;
            default:
                break;
        }
    }

    public void show(){
        for(GameMonster m: MonsterList){
            m.show();
            if(m instanceof Monster3){
                monster3_split();
            }
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
                        monster.setIskilled();
                        arrow.hitMonster = true;
                        return true;
                    }else{
                        ((Monster2) monster).setGodMode();
                        arrow.hitMonster = true;
                        if(((Monster2) monster).getFirstShot()) {
                            monster.setIskilled();
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
                    if (!(monster instanceof Monster2)){
                        monster.setIskilled();
                        arrow.hitMonster = true;
                        return true;
                    }else{
                        ((Monster2) monster).setGodMode();
                        arrow.hitMonster = true;
                        if(((Monster2) monster).getFirstShot()) {
                            monster.setIskilled();
                            return true;
                        }
                        return false;
                    }
                }
            }
        }
        return false;
    }

    public void monster3_split(){
        for(int i=0; i<MonsterList.size(); i++){
            if(i==0 && MonsterList.get(i).isKilled() && !MonsterList.get(i+1).isKilled()){
                if(!MonsterList.get(i+1).isKilled() && !MonsterList.get(i+2).isKilled()){
                    MonsterList.get(i+1).setVisible(true);
                    MonsterList.get(i+1).setDirection(0);
                    MonsterList.get(i+3).setDirection(0);
                    MonsterList.get(i+4).setDirection(0);
                    MonsterList.get(i+3).setLocation(MonsterList.get(i+1).getX(), MonsterList.get(i+1).getY());
                    MonsterList.get(i+4).setLocation(MonsterList.get(i+1).getX(), MonsterList.get(i+1).getY());
                    MonsterList.get(i+2).setVisible(true);
                    MonsterList.get(i+2).setDirection(1);
                    MonsterList.get(i+5).setDirection(1);
                    MonsterList.get(i+6).setDirection(1);
                    MonsterList.get(i+5).setLocation(MonsterList.get(i+2).getX(), MonsterList.get(i+2).getY());
                    MonsterList.get(i+6).setLocation(MonsterList.get(i+2).getX(), MonsterList.get(i+2).getY());
                }
            }else if(i == 1 && MonsterList.get(i).isKilled()){
                if(!MonsterList.get(i+2).getVisible()){
                    MonsterList.get(i+2).setVisible(true);
                    MonsterList.get(i+3).setVisible(true);
                    MonsterList.get(i+2).setDirection(0);
                    MonsterList.get(i+3).setDirection(1);
                }
            }else if(i == 2 && MonsterList.get(i).isKilled()){
                if(!MonsterList.get(i+3).getVisible()){
                    MonsterList.get(i+3).setVisible(true);
                    MonsterList.get(i+4).setVisible(true);
                    MonsterList.get(i+3).setDirection(0);
                    MonsterList.get(i+4).setDirection(1);
                }
            }
        }
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
