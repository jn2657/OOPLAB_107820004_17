package tw.edu.ntut.csie.game.state;

import java.util.HashMap;
import java.util.Map;

import tw.edu.ntut.csie.game.Game;
import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.engine.GameEngine;
import tw.edu.ntut.csie.game.extend.BitmapButton;
import tw.edu.ntut.csie.game.extend.ButtonEventHandler;

public class StateChange extends AbstractGameState {

    private MovingBitmap _complete;
    private MovingBitmap _background;

//    private BitmapButton _sound;
    private BitmapButton _continue;
    private BitmapButton _end;

    private Map<String, Object> Nextlevel;
    private int currentLevel;

    public StateChange(GameEngine engine) {
        super(engine);
    }

    @Override
    public void initialize(Map<String, Object> data) {
        currentLevel = (int) data.get("level");
        Nextlevel = new HashMap<>();
        Nextlevel.put("level", currentLevel+1);
        Nextlevel.put("score", data.get("score"));

        addGameObject(_complete = new MovingBitmap(R.drawable.complete));
        addGameObject(_background = new MovingBitmap(R.drawable.levelbackground3));
        _background.setLocation(0,0);
        _complete.setLocation(225,80);
        initializeContinueButton();
        initializeEndButton();
//        initializeSoundButton();
    }
    /**
     * ��l�ơyExit�z�����s�C
     */
    private void initializeContinueButton() {
        addGameObject(_continue = new BitmapButton(R.drawable.continue_button, R.drawable.continue_pressed, 250, 190));
        _continue.addButtonEventHandler(new ButtonEventHandler() {
            @Override
            public void perform(BitmapButton button) {
                changeState(Game.RUNNING_STATE, Nextlevel);
            }
        });
        addPointerEventHandler(_continue);
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

