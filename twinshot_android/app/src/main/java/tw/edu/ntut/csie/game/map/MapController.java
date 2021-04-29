package tw.edu.ntut.csie.game.map;

import java.util.ArrayList;
import java.util.List;

public class MapController {
    private List<GameMap> mapList;
    private int currentLevel;

    public MapController(){
        mapList = new ArrayList<>();
    }

    public void initialize(){
        GameMap map1 = new Map1();
        GameMap map2 = new Map2();
        mapList.add(map1);
        mapList.add(map2);
    }

    public void release(){
        for(GameMap map: mapList){
            map.release();
            map = null;
        }
        mapList = null;
    }

    public GameMap FirstLevel(){
        currentLevel = 1;
        return mapList.get(currentLevel-1);
    }

    public GameMap NextLevel(){
        currentLevel += 1;
        return mapList.get(currentLevel-1);
    }

    public GameMap goToLevel(int level){
        currentLevel = level;
        return mapList.get(currentLevel-1);
    }

    public List<GameMap> getMapList(){
        return mapList;
    }
}
