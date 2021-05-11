package tw.edu.ntut.csie.game.state;

import java.util.HashMap;
import java.util.Map;

import tw.edu.ntut.csie.game.Game;
import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.engine.GameEngine;
import tw.edu.ntut.csie.game.extend.BitmapButton;
import tw.edu.ntut.csie.game.extend.ButtonEventHandler;

public class StateOver extends AbstractGameState {
    private MovingBitmap _levelFailed;
    private MovingBitmap _background;

    private BitmapButton _end;
    private BitmapButton _restartButton;

    private int currentLevel;
    private Map<String, Object> Nextlevel;

    public StateOver(GameEngine engine) {
        super(engine);
    }

    @Override
    public void initialize(Map<String, Object> data) {
        currentLevel = (int) data.get("level");
        Nextlevel = new HashMap<>();
        Nextlevel.put("level", currentLevel);
        Nextlevel.put("score", data.get("score"));

        addGameObject(_levelFailed = new MovingBitmap(R.drawable.levelfailed));
        addGameObject(_background = new MovingBitmap(R.drawable.levelbackground1));
        _background.setLocation(55,0);
        _levelFailed.setLocation(249,80);
        initializeRestartButton();
        initializeEndButton();
    }

    private void initializeRestartButton() {
        addGameObject(_restartButton = new BitmapButton(R.drawable.restartlevel, R.drawable.restartlevel_pressed, 219, 190));
        _restartButton.addButtonEventHandler(new ButtonEventHandler() {
            @Override
            public void perform(BitmapButton button) {
                changeState(Game.RUNNING_STATE, Nextlevel);
            }
        });
        addPointerEventHandler(_restartButton);
    }

    private void initializeEndButton() {
        addGameObject(_end = new BitmapButton(R.drawable.endgame2, R.drawable.endgame2_pressed, 219, 230));
        _end.addButtonEventHandler(new ButtonEventHandler() {
            @Override
            public void perform(BitmapButton button) {
                changeState(Game.INITIAL_STATE);
            }
        });
        addPointerEventHandler(_end);
    }

    @Override
    public void move() {
        super.move();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
}