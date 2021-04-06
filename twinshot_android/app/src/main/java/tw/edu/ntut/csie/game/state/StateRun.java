package tw.edu.ntut.csie.game.state;

import android.util.Log;

import java.sql.Time;
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
import tw.edu.ntut.csie.game.extend.Integer;


public class StateRun extends GameState {
    private Practice mPractice;

    public static final int DEFAULT_SCORE_DIGITS = 4;
    private MovingBitmap _background;
    private MovingBitmap _android;
    private MovingBitmap _android1;
    private MovingBitmap _cloud;
    private MovingBitmap _door;
    private MovingBitmap _message;
    private GameMap gameMap;

    private Animation _flower;

    private Integer _scores;

    private boolean _grab;
    private int _cx, _cy;

    private Audio _music;

    private Pointer _pointer1;
    private Pointer _pointer2;
    private int jumpheight = 5;
    private int _pointerDistance;
    private int _tempDistance;
    private int _androidWidth;
    private int _androidHeight;
    private int _newWidth;
    private int _newHeight;
    private Timer timer;
    private TimerTask timerTask;

    public StateRun(GameEngine engine) {
        super(engine);
    }

    @Override
    public void initialize(Map<String, Object> data) {
        _background = new MovingBitmap(R.drawable.background);
        _message = new MovingBitmap(R.drawable.message, 130, 150);

        _android = new MovingBitmap(R.drawable.android_green);
        _android.setLocation(100, 200);
        _android1 = new MovingBitmap(R.drawable.android_green);
        _android1.setLocation(500, 200);

        gameMap = new GameMap();
        timer = null;
        timerTask = null;

        _cloud = new MovingBitmap(R.drawable.cloud);
        _cx = 100;
        _cy = 50;
        _cloud.setLocation(_cx, _cy);

        _door = new MovingBitmap(R.drawable.door);
        _door.setLocation(300, 200);

        _scores = new Integer(DEFAULT_SCORE_DIGITS, 50, 550, 10);

        _flower = new Animation();
        _flower.setLocation(300, 240);
        _flower.addFrame(R.drawable.flower1);
        _flower.addFrame(R.drawable.flower2);
        _flower.addFrame(R.drawable.flower3);
        _flower.addFrame(R.drawable.flower4);
        _flower.addFrame(R.drawable.flower5);
        _flower.setDelay(2);

        _music = new Audio(R.raw.ntut);
        _music.setRepeating(true);
        _music.play();

        _grab = false;

        _pointer1 = null;
        _pointer2 = null;
        _pointerDistance = 0;
        _tempDistance = 0;
        _androidWidth = _android.getWidth();
        _androidHeight = _android.getHeight();
        _newHeight = _androidHeight;
        _newWidth = _androidWidth;

//        mPractice = new Practice();
//        mPractice.initialize();

    }

    @Override
    public void move() {
        _flower.move();
        _cloud.setLocation(_cx, _cy);
//        mPractice.move();
    }

    @Override
    public void show() {
        // 順序為貼圖順序
        _background.show();
        //_scores.show();
        _flower.show();
        _message.show();
        //_cloud.show();
        //_door.show();
        _android.show();
        _android1.show();
//        mPractice.show();
        gameMap.show();
    }

    @Override
    public void release() {
        _background.release();
        _scores.release();
        _android.release();
        _android1.release();
        _flower.release();
        _message.release();
        _cloud.release();
        _music.release();
        _door.release();
//        mPractice.release();
        gameMap.release();

        _background = null;
        _scores = null;
        _android = null;
        _android1 = null;
        _flower = null;
        _message = null;
        _cloud = null;
        _music = null;
        _door = null;
//        mPractice = null;
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
        if (roll > 15 && roll < 60 && _cx > 50)
            _cx -= 2;
        if (roll < -15 && roll > -60 && _cx + _cloud.getWidth() < 500)
            _cx += 2;
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
        if(touchX >=480 && touchX <= 550 &&
                touchY >= 150 && touchY <= 250){
            starttimer();
        }
        if(touchX > _android.getX() && touchX < _android.getX() + _android.getWidth() &&
        touchY > _android.getY() && touchY < _android.getY() + _android.getHeight()){
            _grab = true;
        }else{
            _grab = false;


            if(_pointer1 == null){
                _pointer1 = actionPointer;
            }else if(_pointer2 == null){
                _pointer2 = actionPointer;
            }

//            if(_pointer1 != null && _pointer2 != null){
//                _pointerDistance = Math.abs(_pointer1.getX() - _pointer2.getX());
//            }
        }
        return true;
    }

    @Override
    public boolean pointerMoved(Pointer actionPointer, List<Pointer> pointers) {
        if(_grab){
            int moveX = actionPointer.getX();
            int moveY = actionPointer.getY();
            if(moveX > _android.getX()){
                _flower.setLocation(_flower.getX()+5, _flower.getY());
            }else if(moveX < _android.getX() + _android.getWidth()){
                _flower.setLocation(_flower.getX()-5, _flower.getY());
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

    public void resizeAndroidIcon() {

    }

    private void starttimer(){
        if(timer == null){
            timer = new Timer();
        }
        if(timerTask == null) {
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if(jumpheight>0){
                        _flower.setLocation(_flower.getX(), _flower.getY() - 15);
                        jumpheight--;
                    }
                    if(jumpheight<=0 && jumpheight != -5){
                        _flower.setLocation(_flower.getX(), _flower.getY() + 15);
                        jumpheight--;
                    }
                    if(jumpheight == -5){
                        stopTimer();
                    }
                }

            }, 200, 100);
        }
    }

    private void stopTimer(){
        if(timer != null){
            timer.cancel();
            timer = null;
        }
        if(timerTask != null){
            timerTask.cancel();
            timerTask = null;
        }
        jumpheight = 5;

    }
    
    public void jump(int j){
        jumpheight = j;
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(timerTask = new TimerTask() {
            public void run() {
                _flower.setLocation(_flower.getX(), _flower.getY()-15);
            }
        }, 0, 500);

    }

    @Override
    public boolean pointerReleased(Pointer actionPointer, List<Pointer> pointers) {
        _grab = false;
        return false;
    }

    @Override
    public void pause() {
        _music.pause();
    }

    @Override
    public void resume() {
        _music.resume();
    }
}
