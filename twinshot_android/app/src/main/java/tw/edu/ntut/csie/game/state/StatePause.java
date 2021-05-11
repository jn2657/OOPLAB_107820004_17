package tw.edu.ntut.csie.game.state;

import java.util.HashMap;
import java.util.Map;

import tw.edu.ntut.csie.game.Game;
import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.engine.GameEngine;
import tw.edu.ntut.csie.game.extend.BitmapButton;
import tw.edu.ntut.csie.game.extend.ButtonEventHandler;

public class StatePause extends AbstractGameState {

    private MovingBitmap _pause;
    private MovingBitmap _background;

//    private BitmapButton _sound;
    private BitmapButton _resume;
    private BitmapButton _end;

    private GameState temp;

    private int currentLevel;

    public StatePause(GameEngine engine) {
        super(engine);
    }

    @Override
    public void initialize(Map<String, Object> data) {
        temp = (GameState) data.get("state");
        addGameObject(_pause = new MovingBitmap(R.drawable.paused));
        addGameObject(_background = new MovingBitmap(R.drawable.levelbackground3));
        _background.setLocation(0,0);
        _pause.setLocation(300,80);
        initializeResumeButton();
        initializeEndButton();
//        initializeSoundButton();
    }

    /**
     * ��l�ơyExit�z�����s�C
     */
    private void initializeResumeButton() {
        addGameObject(_resume = new BitmapButton(R.drawable.resume, R.drawable.resume_pressed, 250, 190));
        _resume.addButtonEventHandler(new ButtonEventHandler() {
            @Override
            public void perform(BitmapButton button) {
                resumeState(2, null);
                temp.resume();
            }
        });
        addPointerEventHandler(_resume);
    }

    private void initializeEndButton() {
        addGameObject(_end = new BitmapButton(R.drawable.endgame, R.drawable.endgame_pressed, 250, 230));
        _end.addButtonEventHandler(new ButtonEventHandler() {
            @Override
            public void perform(BitmapButton button) {
                changeState(Game.INITIAL_STATE);
            }
        });
        addPointerEventHandler(_end);
    }

//    private void initializeSoundButton() {
//        addGameObject(_startButton = new BitmapButton(R.drawable.sound, R.drawable.sound_off, 220, 331));
//        _startButton.addButtonEventHandler(new ButtonEventHandler() {
//            @Override
//            public void perform(BitmapButton button) {
//                setVisibility(true, false);
//            }
//        });
//        addPointerEventHandler(_startButton);
//    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
}

