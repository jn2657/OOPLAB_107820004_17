package tw.edu.ntut.csie.game.state;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import tw.edu.ntut.csie.game.Game;
import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.engine.GameEngine;
import tw.edu.ntut.csie.game.extend.BitmapButton;
import tw.edu.ntut.csie.game.extend.ButtonEventHandler;
import tw.edu.ntut.csie.game.map.GameMap;
import tw.edu.ntut.csie.game.map.MapController;

public class StateReady extends AbstractGameState {

    private MovingBitmap _mode;
    private MovingBitmap _levels;
    private MovingBitmap _background;
    private MovingBitmap _about;

//    private BitmapButton _sound;

    private BitmapButton _play;
    private BitmapButton _back1, _back2, _back3;
    private BitmapButton _startButton, _aboutButton;
    private BitmapButton _level1, _level2, _level3;

    private Map<String, Object> level1, level2, level3;
    private Map<String, Object> chooseLevel;

    public StateReady(GameEngine engine) {
        super(engine);
    }

    @Override
    public void initialize(Map<String, Object> data) {
        addGameObject(_mode = new MovingBitmap(R.drawable.select));
        addGameObject(_background = new MovingBitmap(R.drawable.background3));
        addGameObject(_levels = new MovingBitmap(R.drawable.levels));
        addGameObject(_about = new MovingBitmap(R.drawable.about_info));
        _background.resize((int)(_background.getWidth()*1.124),(int)(_background.getHeight()*1.045));
        _about.resize((int)(_about.getWidth()*1.124),(int)(_about.getHeight()*1.045));
        _background.setLocation(55,0);
        _mode.setLocation(55,0);
        _levels.setLocation(55,0);
        _about.setLocation(55,0);
        initializeStartButton();
        initializeAboutButton();
        initializeBack1Button();
        initializePlayButton();
        initializeBack2Button();
        initializeBack3Button();
        initializeLevel1Button();
        initializeLevel2Button();
        initializeLevel3Button();
//        initializeSoundButton();
        setVisibility(false, false,false);

        level1 = new HashMap<>();
        level1.put("level", 2);
        level1.put("score", 0);

        level2 = new HashMap<>();
        level2.put("level", 3);
        level2.put("score", 0);

        level3 = new HashMap<>();
        level3.put("level", 4);
        level3.put("score", 0);
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
                setVisibility(true, false,false);
            }
        });
        addPointerEventHandler(_back2);
    }

    private void initializeBack3Button() {
        addGameObject(_back3 = new BitmapButton(R.drawable.back, R.drawable.back_pressed, 257, 280));
        _back3.addButtonEventHandler(new ButtonEventHandler() {
            @Override
            public void perform(BitmapButton button) {
                setVisibility(false, false, false);
            }
        });
        addPointerEventHandler(_back3);
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
                setVisibility(false, true,false);
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
                setVisibility(false, false,false);
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
//                changeState(Game.RUNNING_STATE);
                changeState(Game.RUNNING_STATE, level1);
            }
        });
        addPointerEventHandler(_level1);
    }

    private void initializeLevel2Button() {
        addGameObject(_level2 = new BitmapButton(R.drawable.level2, R.drawable.level2_pressed, 127, 112));
        _level2.addButtonEventHandler(new ButtonEventHandler() {
            @Override
            public void perform(BitmapButton button) {
                changeState(Game.RUNNING_STATE, level2);
            }
        });
        addPointerEventHandler(_level2);
    }

    private void initializeLevel3Button() {
        addGameObject(_level3 = new BitmapButton(R.drawable.level3, R.drawable.level3_pressed, 179, 112));
        _level3.addButtonEventHandler(new ButtonEventHandler() {
            @Override
            public void perform(BitmapButton button) {
                changeState(Game.RUNNING_STATE, level3);
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
                setVisibility(true, false,false);
            }
        });
        addPointerEventHandler(_startButton);
    }

    private void initializeAboutButton() {
        addGameObject(_aboutButton = new BitmapButton(R.drawable.about, R.drawable.about_pressed, 500, 320));
        _aboutButton.addButtonEventHandler(new ButtonEventHandler() {
            @Override
            public void perform(BitmapButton button) {
                setVisibility(false, false,true);
            }
        });
        addPointerEventHandler(_aboutButton);
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
    private void setVisibility(boolean showMode, boolean showLevels, boolean showAbout) {
        boolean showMenu = !showLevels && !showMode && !showAbout;
        _background.setVisible(showMenu);
        _mode.setVisible(showMode);
        _levels.setVisible(showLevels);
        _about.setVisible(showAbout);

        _startButton.setVisible(showMenu);
        _aboutButton.setVisible(showMenu);
        _play.setVisible(showMode);
        _back1.setVisible(showMode);
        _back2.setVisible(showLevels);
        _back3.setVisible(showAbout);
        _level1.setVisible(showLevels);
        _level2.setVisible(showLevels);
        _level3.setVisible(showLevels);
    }

}

