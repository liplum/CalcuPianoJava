package net.liplum.piano;

import net.liplum.audio.AudioMessage;
import net.liplum.audio.AudioMessage.AUDIOMESSAGE;
import net.liplum.audio.AudioMessageManger;
import net.liplum.main.Resources.AudioList;

public class AllPianoKeysTask {

    private static CanExecuteTask GenPianoKeyPlaySoundTask(AudioList item) {
        return () -> AudioMessageManger.sendMessage(new AudioMessage(AUDIOMESSAGE.PLAY, item));
    }

    public static CanExecuteTask Number1 = GenPianoKeyPlaySoundTask(AudioList.NUM1);
    public static CanExecuteTask Number2 = GenPianoKeyPlaySoundTask(AudioList.NUM2);
    public static CanExecuteTask Number3 = GenPianoKeyPlaySoundTask(AudioList.NUM3);
    public static CanExecuteTask Number4 = GenPianoKeyPlaySoundTask(AudioList.NUM4);
    public static CanExecuteTask Number5 = GenPianoKeyPlaySoundTask(AudioList.NUM5);
    public static CanExecuteTask Number6 = GenPianoKeyPlaySoundTask(AudioList.NUM6);
    public static CanExecuteTask Number7 = GenPianoKeyPlaySoundTask(AudioList.NUM7);
    public static CanExecuteTask Number8 = GenPianoKeyPlaySoundTask(AudioList.NUM8);
    public static CanExecuteTask Number9 = GenPianoKeyPlaySoundTask(AudioList.NUM9);

    public static CanExecuteTask Multiple = GenPianoKeyPlaySoundTask(AudioList.MUL);
    public static CanExecuteTask Add = GenPianoKeyPlaySoundTask(AudioList.ADD);
    public static CanExecuteTask Divide = GenPianoKeyPlaySoundTask(AudioList.DIV);
    public static CanExecuteTask Minus = GenPianoKeyPlaySoundTask(AudioList.MINUS);
    public static CanExecuteTask Equal = GenPianoKeyPlaySoundTask(AudioList.EQ);
}
