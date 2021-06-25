package tw.edu.ntut.csie.game.audio;

import java.util.ArrayList;
import java.util.List;

import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.core.Audio;

public class AudioController {
    private Audio hurt, jump, attack, die, onWall, background;
    private List<Audio> audioList;

    public AudioController(){
        hurt = new Audio(R.raw.hurt);
        jump = new Audio(R.raw.jump);
        attack = new Audio(R.raw.shoot);
        die = new Audio(R.raw.gameover);
        onWall = new Audio(R.raw.towall);
        background = new Audio(R.raw.ntut);
        createAudioList();
    }

    public void play(AudioType audioType){
        switch (audioType){
            case HURT:
                hurt.play();
                break;
            case JUMP:
                jump.play();
                break;
            case ATTACK:
                attack.play();
                break;
            case DIE:
                die.play();
                break;
            case OnWall:
                onWall.play();
                break;
            case BACKGROUND:
                background.setRepeating(true);
                background.play();
                break;
        }
    }

    public void release(){
        for(Audio audio: audioList){
            audio.release();
        }
    }

    public void pause(){
        background.pause();
    }

    public void resume(){
        background.resume();
    }

    private void createAudioList(){
        audioList = new ArrayList<>();
        audioList.add(hurt);
        audioList.add(jump);
        audioList.add(attack);
        audioList.add(die);
        audioList.add(onWall);
        audioList.add(background);
    }

//    private void play_with_thread(){
//        Thread t = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                jump.setRepeating(true);
//                jump.play();
//            }
//        });
//        t.start();
//    }
}
