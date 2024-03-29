package tw.edu.ntut.csie.game.state;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import tw.edu.ntut.csie.game.GameObject;
import tw.edu.ntut.csie.game.KeyEventHandler;
import tw.edu.ntut.csie.game.PointerEventHandler;
import tw.edu.ntut.csie.game.ReleasableResource;
import tw.edu.ntut.csie.game.SensorEventHandler;
import tw.edu.ntut.csie.game.engine.GameEngine;

/**
 * <code>GameState</code>定義遊戲中各種不同狀態所需要負責的行為，遊戲狀態
 * 必須繼承<code>GameState</code>並實作必要的行為，並註冊到遊戲引擎中。
 *
 * @author <a href="http://www.csie.ntut.edu.tw/labsdtl/">Lab SDT</a>
 * @version 2.0
 * @since 1.0
 */
public abstract class GameState implements GameObject, KeyEventHandler, SensorEventHandler, PointerEventHandler {

    protected GameEngine _engine;
    private List<PointerEventHandler> _pointerHandlers;
    private List<GameObject> _objects;
    private List<ReleasableResource> _resources;



    /**
     * 建構一個<code>GameState</code>實體。
     *
     * @param engine 執行狀態處理者的引擎
     */
    protected GameState(GameEngine engine) {
        _engine = engine;
        _objects = new ArrayList<GameObject>();
        _resources = new ArrayList<ReleasableResource>();
        _pointerHandlers = new ArrayList<PointerEventHandler>();
    }

    /**
     * 更換狀態。
     *
     * @param state 新狀態的代碼
     */
    public void changeState(int state) {
        _engine.setGameState(state);
    }

    /**
     * 更換狀態。
     *
     * @param state      新狀態的代碼
     * @param parameter  給新狀態的參數
     */
    public void changeState(int state, Map<String, Object> parameter) {
        _engine.setGameState(state, parameter);
    }

    public void pauseState(int state, GameState gameState, Map<String, Object> parameter){
        _engine.setGameStatePause(state, gameState, parameter);
    }

    public void resumeState(int state, Map<String, Object> parameter){
        _engine.setGameStateResume(state, parameter);
    }

    @Override
    public void move() {
        moveAllGameObjects();
    }

    @Override
    public void show() {
        showAllGameObjects();
    }

    @Override
    public void release() {
        releaseAllResources();
    }

    protected void addReleasableResource(ReleasableResource resource) {
        if (!_resources.contains(resource)) {
            _resources.add(resource);
        }
    }

    protected void releaseAllResources() {
        for (ReleasableResource resource : _resources) {
            resource.release();
        }
        _objects.clear();
        _resources.clear();
        _pointerHandlers.clear();
    }

    protected void addGameObject(GameObject object) {
        if (!_objects.contains(object)) {
            _objects.add(object);
        }
        addReleasableResource(object);
    }

    protected void removeGameObject(GameObject object) {
        _objects.remove(object);
    }

    protected void moveAllGameObjects() {
        for (GameObject object : _objects) {
            object.move();
        }
    }

    protected void showAllGameObjects() {
        for (GameObject object : _objects) {
            object.show();
        }
    }

    protected void addPointerEventHandler(PointerEventHandler handler) {
        if (!_pointerHandlers.contains(handler)) {
            _pointerHandlers.add(handler);
        }
    }

    protected void removePointerEventHandler(PointerEventHandler handler) {
        _pointerHandlers.remove(handler);
    }

    /**
     * 清除所有除錯資訊。
     */
    public void clearDebugInfo() {
        _engine.clearDebugInfo();
    }

    /**
     * 列印除錯資訊。
     *
     * @param info 除錯資訊
     */
    public void printDebugInfo(String info) {
        _engine.addDebugInfo(info);
    }

    /**
     * 初始化所有必要的資源。這個函式會在狀態處理者切換後立即被呼叫。
     */
    public abstract void initialize(Map<String, Object> data);

    /**
     * 通知遊戲引擎目前被暫停中。
     */
    public abstract void pause();

    /**
     * 通知遊戲引擎已回復並繼續處理畫面更新。
     */
    public abstract void resume();

}
