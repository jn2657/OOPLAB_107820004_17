package tw.edu.ntut.csie.game.state;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tw.edu.ntut.csie.game.Game;
import tw.edu.ntut.csie.game.Pointer;
import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.core.Audio;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.engine.GameEngine;
import tw.edu.ntut.csie.game.extend.Integer;
import tw.edu.ntut.csie.game.map.MapController;
import tw.edu.ntut.csie.game.map.GameMap;
import tw.edu.ntut.csie.game.monster.GameMonster;
import tw.edu.ntut.csie.game.monster.Monster1;
import tw.edu.ntut.csie.game.monster.MonsterBuilder;


public class StateRun extends GameState {
    public static final int DEFAULT_SCORE_DIGITS = 4;
    private MovingBitmap _background, _button, _message, _pauseButton;
    private GameMap gameMap;
    private MonsterBuilder monsterBuilder;
    private List<GameMonster> MonsterList;

    private Character character;
    private MovingBitmap _life1, _life2, _life3;
    private MovingBitmap _black1, _black2, _black3;
    private MovingBitmap s, t, a, g, e, level;

    private Integer _scores;
    private int currentScore, beginWordsAnimationCount;

    private boolean _grab;
    public boolean pausing;

    private Audio _music;
    private Pointer _pointer1, _pointer2;
    private Map<String, Object> changelevel;

    public StateRun(GameEngine engine) {
        super(engine);
    }

    @Override
    public void initialize(Map<String, Object> data) {
        MapController mapController = new MapController();
        mapController.initialize();
        if(data != null){
            gameMap = mapController.goToLevel((int) data.get("level"));
            currentScore = (int) data.get("score");
        }else{
            gameMap = mapController.FirstLevel();
            currentScore = 0;
        }

        _background = new MovingBitmap(R.drawable.levelbackground1);
        _background.setLocation(55, 0);
        _message = new MovingBitmap(R.drawable.message, 130, 150);
        s = new MovingBitmap(R.drawable.s);
        t = new MovingBitmap(R.drawable.t);
        a = new MovingBitmap(R.drawable.a);
        g = new MovingBitmap(R.drawable.g);
        e = new MovingBitmap(R.drawable.e);
        s.setLocation(571, 80);//271
        t.setLocation(596, 80);//296
        a.setLocation(621, 80);//321
        g.setLocation(646, 80);//346
        e.setLocation(671, 80);//371
        beginWordsAnimationCount = 30;
        switch(gameMap.getLevel()){
            case 1:
                level = new MovingBitmap(R.drawable.one);
                break;
            case 2:
                level = new MovingBitmap(R.drawable.two);
                break;
            case 3:
                level = new MovingBitmap(R.drawable.three);
                break;
        }
        level.setLocation(696, 80);//396

        _button = new MovingBitmap(R.drawable.button);
        _button.setLocation(100, 200);

        _pauseButton = new MovingBitmap(R.drawable.pause);
        _pauseButton.setLocation(590, 10);

        changelevel = new HashMap<>();
        changelevel.put("level", gameMap.getLevel()+1);

        monsterBuilder = gameMap.getMonsterBuilder();
        MonsterList = monsterBuilder.getMonsterList();

        _scores = new Integer(DEFAULT_SCORE_DIGITS, 0, 460, 350);

        character = new Character();
        character.initialize(gameMap);

        _music = new Audio(R.raw.ntut);
        _music.setRepeating(true);
        _music.play();

        _grab = false;
        pausing = false;

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
        if(!pausing){
            character.move();
            character.hurt(checkCollide());
            gameMap.move();
            checkState();
            if(monsterBuilder != null){
                _scores.setValue(monsterBuilder.checkScore() + currentScore);
            }
            levelBeginWordsAnimationPlay();
        }
    }

    @Override
    public void show() {
        // 順序為貼圖順序
        _background.show();
        character.show();
        if(gameMap.getLevel() == 1){
            _message.show();
        }
        _button.show();
        gameMap.show();
        _pauseButton.show();
        _scores.show();
        showLife();
        showLevelBeginWords();
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
        _pauseButton.release();
        _black1.release();
        _black2.release();
        _black3.release();
        if(character.life == 3){
            _life1.release();
            _life2.release();
            _life3.release();
        }else{
            if(_life2 != null){
                _life2.release();
            }
            if(_life3 != null){
                _life3.release();
            }
        }
        s.release();
        t.release();
        a.release();
        g.release();
        e.release();
        level.release();

        _background = null;
        _scores = null;
        _button = null;
        character = null;
        _message = null;
        _music = null;
        gameMap = null;
        _pauseButton = null;
        changelevel = null;
        _black1 = null;
        _black2 = null;
        _black3 = null;
        _life1 = null;
        _life2 = null;
        _life3 = null;
        s = null;
        t = null;
        a = null;
        g = null;
        e = null;
        level = null;
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
        if(touchX > 560 && touchX < 590 && touchY < 20){
            character.setLocation(300, 182);
        }
        if(touchX > 590 && touchY < 20){
            pause();
        }
        if(touchX > 325 && touchY > 185){
            if(character.getHeight() == 5){
                character.jump(5);
            }
        }
        if(touchX > 325 && touchY < 185 && touchY > 20){
            character.shoot();
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
                    character.setDirection("Right");
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
                    character.setDirection("Left");
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
        String d = character.getDirection();
        if(d.contains("Right") && !d.equals("shootingRight")){
            character.setDirection("standingRight");
        }else if(d.contains("Left") && !d.equals("shootingLeft")){
            character.setDirection("standingLeft");
        }
        return false;
    }

    @Override
    public void pause() {
        _music.pause();
        pauseState(5, this, null);
        pausing = true;
    }

    @Override
    public void resume() {
        _music.resume();
        pausing = false;
    }

    public boolean checkCollide(){
        for(GameMonster monster: MonsterList){
            if(character.getX() > monster.getX()+23 || character.getX() < monster.getX()-23){
                continue;
            }else if(character.getY() > monster.getY()+23 || character.getY() < monster.getY()-23){
                continue;
            }else{
                if(!monster.isKilled()){
                    switch (character.life){
                        case 2:
                            if(_life1 != null){
                                _life1.release();
                                _life1 = null;
                            }
                            break;
                        case 1:
                            if(_life2 != null) {
                                _life2.release();
                                _life2 = null;
                            }
                            break;
                        case 0:
                            if(_life3 != null) {
                                _life3.release();
                                _life3 = null;
                            }
                            break;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    private void checkState(){
        int i = 0;
        for(GameMonster monster: MonsterList){
            if(!monster.isKilled()){ i += 1; }
        }
        if(i == 0){
            _scores.setValue(monsterBuilder.checkScore() + currentScore);
            changelevel.put("score", _scores.getValue());
            changeState(Game.CHANGE_STATE, changelevel);
        }
        if(character.dead){
            changelevel.put("score", currentScore);
            changeState(Game.OVER_STATE, changelevel);
        }
    }

    private void showLife(){
        _black1.show();
        _black2.show();
        _black3.show();
        if(_life1 != null){
            _life1.show();
        }
        if(_life2 != null){
            _life2.show();
        }
        if(_life3 != null) {
            _life3.show();
        }
    }

    private void showLevelBeginWords(){
        s.show();
        t.show();
        a.show();
        g.show();
        e.show();
        level.show();
    }

    private void levelBeginWordsAnimationPlay(){
        if(beginWordsAnimationCount > 0){
            if(beginWordsAnimationCount <= 30 && s != null && s.getX() > 271){
                s.setLocation(s.getX()-15, s.getY());
            }
            if(beginWordsAnimationCount <= 28 && t != null && t.getX() > 296){
                t.setLocation(t.getX()-15, t.getY());
            }
            if(beginWordsAnimationCount <= 26 && a != null && a.getX() > 321){
                a.setLocation(a.getX()-15, a.getY());
            }
            if(beginWordsAnimationCount <= 24 && g != null && g.getX() > 346){
                g.setLocation(g.getX()-15, g.getY());
            }
            if(beginWordsAnimationCount <= 22 && e != null && e.getX() > 371){
                e.setLocation(e.getX()-15, e.getY());
            }
            if(beginWordsAnimationCount <= 20 && level != null && level.getX() > 396){
                level.setLocation(level.getX()-15, level.getY());
            }
            beginWordsAnimationCount--;
        }else if(beginWordsAnimationCount > -80){
            if(beginWordsAnimationCount <= -10 && s != null && s.getX() > -30){
                s.setLocation(s.getX()-15, s.getY());
            }
            if(beginWordsAnimationCount <= -12 && t != null && t.getX() > -30){
                t.setLocation(t.getX()-15, t.getY());
            }
            if(beginWordsAnimationCount <= -14 && a != null && a.getX() > -30){
                a.setLocation(a.getX()-15, a.getY());
            }
            if(beginWordsAnimationCount <= -16 && g != null && g.getX() > -30){
                g.setLocation(g.getX()-15, g.getY());
            }
            if(beginWordsAnimationCount <= -18 && e != null && e.getX() > -30){
                e.setLocation(e.getX()-15, e.getY());
            }
            if(beginWordsAnimationCount <= -20 && level != null && level.getX() > -30){
                level.setLocation(level.getX()-15, level.getY());
            }
            beginWordsAnimationCount--;
        }

    }

}