package net.liplum;

import net.liplum.music.IMusic;
import net.liplum.music.Music;
import net.liplum.util.ParallelArray;
import net.liplum.util.ResourceLoader;

import java.awt.image.BufferedImage;


public class Resources {

    public final static String MusicalNoteFolderName = "MusicalNotes";
    public final static String ImageFolderName = "Images";
    public static BufferedImage Icon, Cursor;
    private static final ParallelArray<String, IMusic> Address2Audio = new ParallelArray<>();
    private static final ParallelArray<String, BufferedImage> Address2Image = new ParallelArray<>();

    public static void loadImage() {
        loadImageList();
        loadAllImage();
    }


    public static void loadMusicalNote() {
        loadMusicalNoteList();
        loadMusicalNoteAudio();
    }

    public static IMusic getAudioAt(AudioList a) {
        return Address2Audio.getSecondAt(a.getAudio());
    }

    public static BufferedImage getImageAt(ImageList i) {
        return Address2Image.getSecondAt(i.getImage());
    }

    public static void loadUIIcon() {
        Icon = ResourceLoader.loadImage(ImageFolderName, "icon.png");
//		Cursor = ResourceLoader.loadImage(ImageFolderName, "cursor.png");
    }

    private static void loadImageList() {
        for (int i = 0; i < 3; ++i)
            Address2Image.addFirst("Loading" + i + ".png");

        for (int i = 1; i < 10; ++i)
            Address2Image.addFirst(i + ".png");

        Address2Image.addFirst("mul.png");
        Address2Image.addFirst("plus.png");
        Address2Image.addFirst("div.png");
        Address2Image.addFirst("minus.png");
        Address2Image.addFirst("eq.png");

        Address2Image.addFirst("more.png");

        Address2Image.addFirst("setting.png");
        Address2Image.addFirst("import.png");
        Address2Image.addFirst("save.png");
    }

    private static void loadAllImage() {

        for (int i = 0; i < Address2Image.size(); ++i) {
            String fileName = Address2Image.getFirstAt(i);
            Address2Image.setSecondAt(i, loadImage(fileName));
        }
    }

    private static void loadMusicalNoteList() {
        for (int i = 1; i < 10; i++) {
            Address2Audio.addFirst(i + ".wav");
        }

        Address2Audio.addFirst("mul.wav");
        Address2Audio.addFirst("plus.wav");
        Address2Audio.addFirst("div.wav");
        Address2Audio.addFirst("minus.wav");
        Address2Audio.addFirst("eq.wav");

    }

    private static void loadMusicalNoteAudio() {
        for (int i = 0; i < Address2Audio.size(); ++i) {
            String fileName = Address2Audio.getFirstAt(i);
            Address2Audio.setSecondAt(i, loadAudio(fileName));
        }
    }

    private static BufferedImage loadImage(String fileName) {
        return ResourceLoader.loadImage(ImageFolderName, fileName);
    }

    private static Music loadAudio(String fileName) {
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
        MUL(9),
        Plus(10),
        DIV(11),
        MINUS(12),
        EQ(13);

        private final int curAudio;

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
        TEXT_MUL(12),
        TEXT_Plus(13),
        TEXT_DIV(14),
        TEXT_MINUS(15),
        TEXT_EQ(16),
        MORE(17),
        SETTING(18),
        IMPORT(19),
        SAVE(20);

        private final int curImage;

        ImageList(int i) {
            this.curImage = i;
        }

        public static ImageList textOf(int value) {
            return values()[value + 3];
        }

        public int getImage() {
            return this.curImage;
        }
    }
}
