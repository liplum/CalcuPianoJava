package net.liplum.audio;

import net.liplum.Resources.AudioList;

public class AudioMessage {
    public final AudioMsg message;

    public final AudioList index;
    public final long sendTime;

    public AudioMessage(AudioMsg m, AudioList index) {
        this.message = m;
        this.index = index;
        this.sendTime = System.nanoTime();
    }

    public enum AudioMsg {
        PLAY, STOP, LOOP
    }

}
