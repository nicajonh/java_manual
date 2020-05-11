package com.tida.manual.pattern;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2020/5/11.
 * Description ${TEXT}
 */
public class AudioPlayerTest {

    @Test
    public void AudioPlayerTest(){
        AudioPlayer audioPlayer = new AudioPlayer();

        audioPlayer.play("mp3", "beyond the horizon.mp3");
        audioPlayer.play("mp4", "alone.mp4");
        audioPlayer.play("vlc", "far far away.vlc");
        audioPlayer.play("avi", "mind me.avi");
    }

}