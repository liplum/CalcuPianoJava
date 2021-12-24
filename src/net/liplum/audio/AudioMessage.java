package net.liplum.audio;

import net.liplum.main.Resources.AudioList;

public class AudioMessage {
    private AUDIOMESSAGE message;

    private AudioList index;
    private long sendTime;

    public AudioMessage(AUDIOMESSAGE m, AudioList index) {
        this.message = m;
        this.index = index;
        this.sendTime = System.nanoTime();
    }

    public AUDIOMESSAGE getMessage() {
        return message;
    }

    public AudioList getIndex() {
        return index;
    }

    public long getSendTime() {
        return sendTime;
    }

    // 定义消息类型
    public enum AUDIOMESSAGE {
        PLAY, STOP, LOOP
    }

}
