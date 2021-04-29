package tw.edu.ntut.csie.game.map;

import java.util.ArrayList;
import java.util.List;

public class MapController {
    private List<GameMap> mapList;
    private int currentLevel;

    public MapController(){
        mapList = new ArrayList<>();
    }

    //須在stateReady也加入map
    public void initialize(){
        GameMap map1 = new Map1();
        GameMap map2 = new Map2();
        GameMap map3 = new Map3();
        mapList.add(map1);
        mapList.add(map2);
        mapList.add(map3);
    }

    public void release(){
        for(GameMap map: mapList){
            if(map != null){
                map.release();
                map = null;
            }
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
        currentLevel = level-1; // need to be fix
        for(GameMap map: mapList){
            if(map != null && map.getLevel() == currentLevel){
                return map;
            }
        }
//        return mapList.get(currentLevel-1);
        return null;
    }

    public List<GameMap> getMapList(){
        return mapList;
    }
}
