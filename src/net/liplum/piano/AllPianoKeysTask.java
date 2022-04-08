package net.liplum.piano;

import net.liplum.audio.AudioMessage;
import net.liplum.audio.AudioMessage.AudioMsg;
import net.liplum.audio.AudioMessageManger;
import net.liplum.Resources.AudioList;

public class AllPianoKeysTask {

    private static IExecuteTask genTask(AudioList item) {
        return () -> AudioMessageManger.sendMessage(new AudioMessage(AudioMsg.PLAY, item));
    }

    public static IExecuteTask
            Number1 = genTask(AudioList.NUM1),
            Number2 = genTask(AudioList.NUM2),
            Number3 = genTask(AudioList.NUM3),
            Number4 = genTask(AudioList.NUM4),
            Number5 = genTask(AudioList.NUM5),
            Number6 = genTask(AudioList.NUM6),
            Number7 = genTask(AudioList.NUM7),
            Number8 = genTask(AudioList.NUM8),
            Number9 = genTask(AudioList.NUM9);

    public static IExecuteTask
            Multiple = genTask(AudioList.MUL),
            Add = genTask(AudioList.Plus),
            Divide = genTask(AudioList.DIV),
            Minus = genTask(AudioList.MINUS),
            Equal = genTask(AudioList.EQ);
}
