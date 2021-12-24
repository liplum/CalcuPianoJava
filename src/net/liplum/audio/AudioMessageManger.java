package net.liplum.audio;

import net.liplum.audio.AudioMessage.AUDIOMESSAGE;
import net.liplum.main.Resources;
import net.liplum.main.Resources.AudioList;
import net.liplum.music.MusicBase;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class AudioMessageManger implements Runnable {

    private static final BlockingQueue<AudioMessage> messageQueue = new LinkedBlockingQueue<>();

    private static AudioMessageManger audioMessageManger;

    public static AudioMessageManger Instance() {
        if (audioMessageManger == null)
            audioMessageManger = new AudioMessageManger();
        return audioMessageManger;
    }

    public static void sendMessage(AudioMessage message) {
        try {
            messageQueue.put(message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void playAudio(AudioList index) {
        MusicBase m = Resources.getAudioAt(index);
        m.clone().play();
    }

    private static void loopAudio(AudioList index) {
        Resources.getAudioAt(index).clone().loop();
    }

    private static void stopAudio(AudioList index) {
        Resources.getAudioAt(index).clone().stop();
    }

    @Override
    public void run() {
        AudioMessage msg;
        while (true) {
            try {
                msg = messageQueue.take();
                AUDIOMESSAGE AudioMsg = msg.getMessage();

                switch (AudioMsg) {
                    case LOOP:
                        loopAudio(msg.getIndex());
                        break;
                    case PLAY:
                        playAudio(msg.getIndex());
                        break;
                    case STOP:
                        stopAudio(msg.getIndex());
                        break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}