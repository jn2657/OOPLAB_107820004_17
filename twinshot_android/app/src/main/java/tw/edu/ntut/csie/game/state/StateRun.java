package tw.edu.ntut.csie.game.state;

import android.util.Log;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import tw.edu.ntut.csie.game.Game;
import tw.edu.ntut.csie.game.Pointer;
import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.core.Audio;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.engine.GameEngine;
import tw.edu.ntut.csie.game.extend.Animation;
import tw.edu.ntut.csie.game.extend.BitmapButton;
import tw.edu.ntut.csie.game.extend.ButtonEventHandler;
import tw.edu.ntut.csie.game.extend.Integer;
import tw.edu.ntut.csie.game.map.MapController;
import tw.edu.ntut.csie.game.map.GameMap;


public class StateRun extends GameState {
    public static final int DEFAULT_SCORE_DIGITS = 4;
    private MovingBitmap _background;
    private MovingBitmap _button;
    private MovingBitmap _message;
    private GameMap gameMap;
    private MapController mapController;
    private List<Monster> MonsterList;

    private MovingBitmap _life1;
    private MovingBitmap _life2;
    private MovingBitmap _life3;
    private MovingBitmap _black1;
    private MovingBitmap _black2;
    private MovingBitmap _black3;

    private Character character;

    private Integer _scores;
    private int currentLevel;

    private boolean _grab;

    private Audio _music;

    private Pointer _pointer1;
    private Pointer _pointer2;
    private int jumpheight = 5;

    private Map<String, Object> changelevel;

    public StateRun(GameEngine engine) {
        super(engine);
    }

    @Override
    public void initialize(Map<String, Object> data) {
        mapController = new MapController();
        mapController.initialize();
        if(data != null){
            currentLevel = (int) data.get("level");
            gameMap = mapController.goToLevel(currentLevel);
        }else{
            gameMap = mapController.FirstLevel();
        }

        _background = new MovingBitmap(R.drawable.levelbackground1);
        _background.setLocation(60, 0);
        _message = new MovingBitmap(R.drawable.message, 130, 150);

        _button = new MovingBitmap(R.drawable.button);
        _button.setLocation(100, 200);

        changelevel = new HashMap<>();
        changelevel.put("level", gameMap.getLevel()+1);

        MonsterList = new ArrayList<Monster>();
        MonsterList = gameMap.getMonsterList();

        _scores = new Integer(DEFAULT_SCORE_DIGITS, 0, 460, 350);

        character = new Character();
        character.initialize(gameMap);

        _music = new Audio(R.raw.ntut);
        _music.setRepeating(true);
        _music.play();

        _grab = false;

        _pointer1 = null;
        _pointer2 = null;

        _black3 = new MovingBitmap(R.drawable.blacklife);
        _black3.setLocation(600, 350);

        _black2 = new MovingBitmap(R.drawable.blacklife);
        _black2.setLocation(577, 350);

        _black1 = new MovingBitmap(R.drawable.blacklife);
        _black1.setLocation(554, 350);

        _life3 = new MovingBitmap(R.drawable.lifewithoutframe);
        _life3.setLocation(603, 353);

        _life2 = new MovingBitmap(R.drawable.lifewithoutframe);
        _life2.setLocation(580, 353);

        _life1 = new MovingBitmap(R.drawable.lifewithoutframe);
        _life1.setLocation(557, 353);
    }

    @Override
    public void move() {
        character.move();
        character.hurt(checkCollide());
        gameMap.move();
        checkState();

    }

    @Override
    public void show() {
        // 順序為貼圖順序
        _background.show();
        character.show();
        _message.show();
        _button.show();
        gameMap.show();
        _scores.show();
        _black1.show();
        _black2.show();
        _black3.show();
        _life1.show();
        _life2.show();
        _life3.show();
    }

    @Override
    public void release() {
        _background.release();
        _scores.release();
        _button.release();
        character.release();
        _message.release();
        _music.release();
        gameMap.release();
        _black1.release();
        _black2.release();
        _black3.release();
        _life1.release();
        _life2.release();
        _life3.release();

        _background = null;
        _scores = null;
        _button = null;
        character = null;
        _message = null;
        _music = null;
        gameMap = null;
        changelevel = null;
        _black1 = null;
        _black2 = null;
        _black3 = null;
        _life1 = null;
        _life2 = null;
        _life3 = null;
    }

    @Override
    public void keyPressed(int keyCode) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyReleased(int keyCode) {
        // TODO Auto-generated method stub
    }

    @Override
    public void orientationChanged(float pitch, float azimuth, float roll) {
//        if (roll > 15 && roll < 60 && _cx > 50)
//            _cx -= 2;
//        if (roll < -15 && roll > -60 && _cx + _cloud.getWidth() < 500)
//            _cx += 2;
    }

    @Override
    public void accelerationChanged(float dX, float dY, float dZ) {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean pointerPressed(Pointer actionPointer, List<Pointer> pointers) {
        _message.setVisible(false);
        int touchX = actionPointer.getX();
        int touchY = actionPointer.getY();
//        if(touchX > 560 && touchX < 590 && touchY < 20){
//            resume();
//        }
//        if(touchX > 590 && touchY < 20){
//            pause();
//        }
        if(touchX > 325 && touchY > 185){
            if(character.getHeight() == 5){
                character.jump(5);
            }
        }
        if(touchX > 325 && touchY < 185 && touchY > 20){
            character.shot();
        }

        if(touchX > _button.getX() && touchX < _button.getX() + _button.getWidth() &&
                touchY > _button.getY() && touchY < _button.getY() + _button.getHeight()){
            _grab = true;
        }else{
            _grab = false;


            if(_pointer1 == null){
                _pointer1 = actionPointer;
            }else if(_pointer2 == null){
                _pointer2 = actionPointer;
            }
        }
        return true;
    }

    @Override
    public boolean pointerMoved(Pointer actionPointer, List<Pointer> pointers) {
        if(_grab){
            int moveX = actionPointer.getX();
            int moveY = actionPointer.getY();
            if(moveX > _button.getX()){
                if(gameMap.isWalkable_right(character.getX()+5, character.getY())){
                    character.setLocation(character.getX()+5, character.getY());
                    character.setDirection("right");
                }
                if(gameMap.isWalkable_down(character.getX(), character.getY()+5)){
                    if(!character.isJumping()){
                        character.jump(0);
                        System.out.println(character.getX()+","+character.getY());
                    }
                }
            }else if(moveX < _button.getX() + _button.getWidth()){
                if(gameMap.isWalkable_left(character.getX()-5, character.getY())){
                    character.setLocation(character.getX()-5, character.getY());
                    System.out.println(character.getX()+","+character.getY());
                    character.setDirection("left");
                }
                if(gameMap.isWalkable_down(character.getX(), character.getY()+5)){
                    if(!character.isJumping()){
                        character.jump(0);
                    }
                }
            }
        }

        if(_pointer1 != null && _pointer2 != null){
            if(actionPointer.getID() == _pointer1.getID()){
                _pointer1 = actionPointer;
            }else if(actionPointer.getID() == _pointer2.getID()){
                _pointer2 = actionPointer;
            }
        }
        return false;
    }

    @Override
    public boolean pointerReleased(Pointer actionPointer, List<Pointer> pointers) {
        _grab = false;
        if(character.getDirection().contains("right") || character.getDirection().contains("standingRight")){
            character.setDirection("standingRight");
        }else{
            character.setDirection("standingLeft");
        }
        return false;
    }

    @Override
    public void pause() {
        _music.pause();
        pause();
    }

    @Override
    public void resume() {
        _music.resume();
        resume();
    }

    public boolean checkCollide(){
        for(Monster monster: MonsterList){
            if(monster.getScore()){
                _scores.add(10);
            }
            if(character.getX() > monster.getX()+23 || character.getX() < monster.getX()-23){
                return false;
            }else if(character.getY() > monster.getY()+23 || character.getY() < monster.getY()-23){
                return false;
            }else{
                if(!monster.iskilled){
                    switch (character.life){
                        case 2:
                            if(_life1 != null){
                                _life1.release();
                                _life1 = null;
                            }
                        case 1:
                            if(_life1 != null) {
                                _life2.release();
                                _life2 = null;
                            }
                        case 0:
                            if(_life1 != null) {
                                _life3.release();
                                _life3 = null;
                            }
                            //change state
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public void checkState(){
        int i = 0;
        for(Monster moster: MonsterList){
            if(!moster.iskilled){
                i += 1;
            }
        }
        if(i == 0){
            changeState(Game.CHANGE_STATE, changelevel);
        }
    }

}