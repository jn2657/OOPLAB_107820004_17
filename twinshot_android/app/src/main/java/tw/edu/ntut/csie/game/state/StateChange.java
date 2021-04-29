package tw.edu.ntut.csie.game.state;

import java.util.Map;

import tw.edu.ntut.csie.game.Game;
import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.engine.GameEngine;
import tw.edu.ntut.csie.game.extend.BitmapButton;
import tw.edu.ntut.csie.game.extend.ButtonEventHandler;

public class StateChange extends AbstractGameState {

    private MovingBitmap _mode;
    private MovingBitmap _levels;
    private MovingBitmap _background;

//    private BitmapButton _sound;

    private BitmapButton _play;
    private BitmapButton _back1;
    private BitmapButton _back2;
    private BitmapButton _startButton;
    private BitmapButton _level1;
    private BitmapButton _level2;
    private BitmapButton _level3;

    private boolean _showMode;
    private boolean _showLevels;

    public StateChange(GameEngine engine) {
        super(engine);
    }

    @Override
    public void initialize(Map<String, Object> data) {
        addGameObject(_mode = new MovingBitmap(R.drawable.select));
        addGameObject(_background = new MovingBitmap(R.drawable.statechange_background));
        addGameObject(_levels = new MovingBitmap(R.drawable.levels));
//        _background.resize((int)(_background.getWidth()*1.124),(int)(_background.getHeight()*1.045));
        _background.setLocation(55,0);
        _mode.setLocation(55,0);
        _levels.setLocation(55,0);
        initializeStartButton();
        initializeBack1Button();
        initializePlayButton();
        initializeBack2Button();
        initializeLevel1Button();
        initializeLevel2Button();
        initializeLevel3Button();
//        initializeSoundButton();
        setVisibility(false, false);
    }

    /**
     * ��l�ơyAbout�z�����s�C
     */
    // �}�o²��
    private void initializeBack2Button() {
        addGameObject(_back2 = new BitmapButton(R.drawable.back, R.drawable.back_pressed, 257, 280));
        _back2.addButtonEventHandler(new ButtonEventHandler() {
            @Override
            public void perform(BitmapButton button) {
                setVisibility(true, false);
            }
        });
        addPointerEventHandler(_back2);
    }

    /**
     * ��l�ơyHelp�z�����s�C
     */
    // �C������
    private void initializePlayButton() {
        addGameObject(_play = new BitmapButton(R.drawable.play, R.drawable.play_pressed, 492, 120));
        _play.addButtonEventHandler(new ButtonEventHandler() {
            @Override
            public void perform(BitmapButton button) {
                setVisibility(false, true);
            }
        });
        addPointerEventHandler(_play);
    }

    /**
     * ��l�ơyMenu�z�����s�C
     */
    private void initializeBack1Button() {
        addGameObject(_back1 = new BitmapButton(R.drawable.back, R.drawable.back_pressed, 257, 319));
        _back1.addButtonEventHandler(new ButtonEventHandler() {
            @Override
            public void perform(BitmapButton button) {
                setVisibility(false, false);
            }
        });
        addPointerEventHandler(_back1);
    }

    /**
     * ��l�ơyExit�z�����s�C
     */
    private void initializeLevel1Button() {
        addGameObject(_level1 = new BitmapButton(R.drawable.level1, R.drawable.level1_pressed, 76, 112));
        _level1.addButtonEventHandler(new ButtonEventHandler() {
            @Override
            public void perform(BitmapButton button) {
                changeState(Game.RUNNING_STATE);
            }
        });
        addPointerEventHandler(_level1);
    }

    private void initializeLevel2Button() {
        addGameObject(_level2 = new BitmapButton(R.drawable.level2, R.drawable.level2_pressed, 127, 112));
        _level2.addButtonEventHandler(new ButtonEventHandler() {
            @Override
            public void perform(BitmapButton button) {
                changeState(Game.RUNNING_STATE);
            }
        });
        addPointerEventHandler(_level2);
    }

    private void initializeLevel3Button() {
        addGameObject(_level3 = new BitmapButton(R.drawable.level3, R.drawable.level3_pressed, 179, 112));
        _level3.addButtonEventHandler(new ButtonEventHandler() {
            @Override
            public void perform(BitmapButton button) {
                changeState(Game.RUNNING_STATE);
            }
        });
        addPointerEventHandler(_level3);
    }

    /**
     * ��l�ơyStart�z�����s�C
     */
    private void initializeStartButton() {
        addGameObject(_startButton = new BitmapButton(R.drawable.clickstart, R.drawable.clickstart_pressed, 220, 331));
        _startButton.addButtonEventHandler(new ButtonEventHandler() {
            @Override
            public void perform(BitmapButton button) {
                setVisibility(true, false);
            }
        });
        addPointerEventHandler(_startButton);
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

    /**
     * �]�w�e���W���ǹϤ�����ܡA���ǹϤ������áC
     *
     * @param showMode  ���Help�e��
     * @param showLevels ���About�e��
     */
    private void setVisibility(boolean showMode, boolean showLevels) {
        _showMode = showMode;
        _showLevels = showLevels;
        boolean showMenu = !_showLevels && !_showMode;
        _background.setVisible(showMenu);
        _mode.setVisible(_showMode);
        _levels.setVisible(_showLevels);

        _startButton.setVisible(showMenu);
        _play.setVisible(_showMode);
        _back1.setVisible(_showMode);
        _back2.setVisible(_showLevels);
        _level1.setVisible(_showLevels);
        _level2.setVisible(_showLevels);
        _level3.setVisible(_showLevels);
    }
}

