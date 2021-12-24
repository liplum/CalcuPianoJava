package net.liplum.main;

import net.liplum.music.Music;
import net.liplum.music.MusicBase;
import net.liplum.util.ParallelArray;
import net.liplum.util.ResourceLoader;

import java.awt.image.BufferedImage;


public class Resources {

    public final static String MusicalNoteFolderName = "musicalNote";
    public final static String ImageFolderName = "image";
    public static BufferedImage Icon, Cursor;
    /**
     * 用于存放所有的音频：地址——音频
     */
    private static volatile ParallelArray<String, MusicBase> Audios = new ParallelArray<>();
    /**
     * 用于存放所有的图片：地址——图片
     */
    private static volatile ParallelArray<String, BufferedImage> Images = new ParallelArray<>();

    public static synchronized void loadImage() {
        loadImageList();
        loadAllImage();
    }


    public static synchronized void loadMusicalNote() {
        loadMusicalNoteList();
        loadMusicalNoteAudio();
    }

    public static synchronized MusicBase getAudioAt(AudioList a) {
        return Audios.getSecondAt(a.getAudio());
    }

    public static BufferedImage getImageAt(ImageList i) {
        return Images.getSecondAt(i.getImage());
    }

    public static void loadUIIcon() {
        Icon = ResourceLoader.loadImage("image", "icon.png");
//		Cursor = ResourceLoader.loadImage("image", "cursor.png");
    }

    private static synchronized void loadImageList() {
        for (int i = 0; i < 3; ++i)
            Images.addFirst("Loading" + i + ".png");

        for (int i = 1; i < 10; ++i)
            Images.addFirst(i + ".png");

        Images.addFirst("+.png");
        Images.addFirst("-.png");
        Images.addFirst("mul.png");
        Images.addFirst("div.png");
        Images.addFirst("=.png");

        Images.addFirst("more.png");

        Images.addFirst("setting.png");
        Images.addFirst("import.png");
        Images.addFirst("save.png");
    }

    private static synchronized void loadAllImage() {

        for (int i = 0; i < Images.size(); ++i) {
            String fileName = Images.getFirstAt(i);
            Images.setSecondAt(i, loadImage(fileName));
        }
    }

    private static synchronized void loadMusicalNoteList() {
        for (int i = 1; i < 10; i++) {
            Audios.addFirst(i + ".wav");
        }

        Audios.addFirst("+.wav");
        Audios.addFirst("-.wav");
        Audios.addFirst("mul.wav");
        Audios.addFirst("div.wav");
        Audios.addFirst("=.wav");

    }

    private static synchronized void loadMusicalNoteAudio() {

        for (int i = 0; i < Audios.size(); ++i) {
            String fileName = Audios.getFirstAt(i);
            Audios.setSecondAt(i, loadAudio(fileName));
        }

    }

    private static synchronized BufferedImage loadImage(String fileName) {
        return ResourceLoader.loadImage(ImageFolderName, fileName);
    }

    private static synchronized Music loadAudio(String fileName) {
        return ResourceLoader.loadAudio(MusicalNoteFolderName, fileName);
    }


    public enum AudioList {
        NUM1(0),
        NUM2(1),
        NUM3(2),
        NUM4(3),
        NUM5(4),
        NUM6(5),
        NUM7(6),
        NUM8(7),
        NUM9(8),
        ADD(9),
        MINUS(10),
        MUL(11),
        DIV(12),
        EQ(13);

        private int curAudio;

        AudioList(int i) {
            this.curAudio = i;
        }

        public int getAudio() {
            return this.curAudio;
        }
    }


    public enum ImageList {
        LOADING1(0),
        LOADING2(1),
        LOADING3(2),
        TEXT1(3),
        TEXT2(4),
        TEXT3(5),
        TEXT4(6),
        TEXT5(7),
        TEXT6(8),
        TEXT7(9),
        TEXT8(10),
        TEXT9(11),
        TEXT_ADD(12),
        TEXT_MINUS(13),
        TEXT_MUL(14),
        TEXT_DIV(15),
        TEXT_EQ(16),
        MORE(17),
        SETTING(18),
        IMPORT(19),
        SAVE(20);

        private int curImage;

        ImageList(int i) {
            this.curImage = i;
        }

        public static ImageList textOf(int value) {
            switch (value) {
                case 0:
                    return TEXT1;
                case 1:
                    return TEXT2;
                case 2:
                    return TEXT3;
                case 3:
                    return TEXT4;
                case 4:
                    return TEXT5;
                case 5:
                    return TEXT6;
                case 6:
                    return TEXT7;
                case 7:
                    return TEXT8;
                case 8:
                    return TEXT9;
                case 9:
                    return TEXT_MUL;
                case 10:
                    return TEXT_ADD;
                case 11:
                    return TEXT_DIV;
                case 12:
                    return TEXT_MINUS;
                case 13:
                    return TEXT_EQ;
                default:
                    throw new IndexOutOfBoundsException("超过 整数定义域：[0,13]");
            }
        }

        public int getImage() {
            return this.curImage;
        }
    }


}
