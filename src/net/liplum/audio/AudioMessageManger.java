package net.liplum.audio;

import net.liplum.audio.AudioMessage.AudioMsg;
import net.liplum.Resources;
import net.liplum.Resources.AudioList;
import net.liplum.music.IMusic;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class AudioMessageManger {

    private static final BlockingQueue<AudioMessage> messageQueue = new LinkedBlockingQueue<>();

    public static void sendMessage(AudioMessage message) {
        try {
            messageQueue.put(message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void playAudio(AudioList index) {
        IMusic m = Resources.getAudioAt(index);
        m.clone().play();
    }

    private static void loopAudio(AudioList index) {
        Resources.getAudioAt(index).clone().loop();
    }

    private static void stopAudio(AudioList index) {
        Resources.getAudioAt(index).clone().stop();
    }

    public static void run() {
        AudioMessage msg;
        while (true) {
            try {
                if (messageQueue.isEmpty()) continue;
                msg = messageQueue.take();
                AudioMsg AudioMsg = msg.message;

                switch (AudioMsg) {
                    case LOOP:
                        loopAudio(msg.index);
                        break;
                    case PLAY:
                        playAudio(msg.index);
                        break;
                    case STOP:
                        stopAudio(msg.index);
                        break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}