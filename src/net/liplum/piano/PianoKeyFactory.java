package net.liplum.piano;

import net.liplum.animation.Animation;
import net.liplum.animation.GradualAnimeFactory;
import net.liplum.controls.Button;
import net.liplum.controls.ButtonGroup;
import org.javatuples.Pair;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PianoKeyFactory {

    private PianoKeyFactory() {
    }

    public static Button newPianoKey(
            int x, int y, int width, int height,
            IExecuteTask task,
            Color colorUp,
            BufferedImage paster, int dx, int dy, int pasterWidth,
            int pasterHeight) {
        Animation anime = GradualAnimeFactory.newAnime(4, x, y, width, height, colorUp, paster, dx, dy, pasterWidth, pasterHeight);
        Button pianoKey = new Button(anime, x, y, width, height);
        pianoKey.setTask(task);
        return pianoKey;
    }


    /**
     * Order: 1, 2, 3, 4, 5, 6, 7, 8, 9, mul, plus, div, minus, eq
     */
    public static ButtonGroup<Button> newPiano(
            int leftUpX, int leftUpY, int keyWidth, int keyHeight,
            int interval, Color colorUp, BufferedImage[] pasters,
            IExecuteTask[] tasks,
            int[] triggerKeyCodes
    ) {
        ButtonGroup<Button> newGroup;

        {
            // Create piano key 1 - 9
            Button[] PianoKeys_1_to_9 = new Button[9];

            Pair<ArrayList<Integer>, ArrayList<Integer>> PianoKeysXY_1_to_9 = calculateLayout(leftUpX, leftUpY, keyWidth,
                    keyHeight, interval, 9, 3);
            ArrayList<Integer> PianoKey_Xs = PianoKeysXY_1_to_9.getValue0();
            ArrayList<Integer> PianoKey_Ys = PianoKeysXY_1_to_9.getValue1();

            for (int i = 0; i < 9; ++i) {
                Button key = newPianoKey(PianoKey_Xs.get(i), PianoKey_Ys.get(i), keyWidth, keyHeight,
                        tasks[i], colorUp, pasters[i], 0, 0, keyWidth, keyHeight);
                key.setHotkeyCode(triggerKeyCodes[i]);
                PianoKeys_1_to_9[i] = key;
            }

            newGroup = new ButtonGroup<>(PianoKeys_1_to_9);
        }

        {
            // Create piano key mul
            int mulLeftUpX = leftUpX + 3 * (keyWidth + interval);
            int mulLeftUpY = leftUpY;
            Button key = newPianoKey(mulLeftUpX, mulLeftUpY, keyWidth, keyHeight, tasks[9],
                    colorUp, pasters[9], 0, 0, keyWidth, keyHeight);
            key.setHotkeyCode(triggerKeyCodes[9]);
            newGroup.add(key);

        }

        {
            // Create piano key plus
            int addLeftUpX = leftUpX + 3 * (keyWidth + interval);
            int addLeftUpY = leftUpY + keyHeight + interval;
            int addHeight = 2 * keyHeight + interval;
            Button key = newPianoKey(addLeftUpX, addLeftUpY, keyWidth, addHeight, tasks[10],
                    colorUp, pasters[10], 0, (addHeight - keyHeight) / 2, keyWidth, keyHeight);
            key.setHotkeyCode(triggerKeyCodes[10]);
            newGroup.add(key);
        }

        {
            // Create piano key div, minus and eq
            Button[] PianoKeys_div_minus_eq = new Button[3];

            int div_minus_eq_leftUpX = leftUpX + 4 * (keyWidth + interval);
            int div_minus_eq_leftUpY = leftUpY;

            Pair<ArrayList<Integer>, ArrayList<Integer>> PianoKeysXY_div_minus_eq = calculateLayout(
                    div_minus_eq_leftUpX, div_minus_eq_leftUpY, keyWidth, keyHeight, interval, 3, 1);

            ArrayList<Integer> PianoKey_Xs = PianoKeysXY_div_minus_eq.getValue0();
            ArrayList<Integer> PianoKey_Ys = PianoKeysXY_div_minus_eq.getValue1();

            for (int i = 0; i < 3; ++i) {
                Button key = newPianoKey(PianoKey_Xs.get(i), PianoKey_Ys.get(i), keyWidth, keyHeight,
                        tasks[13 - i], colorUp, pasters[13 - i], 0, 0, keyWidth,
                        keyHeight);
                key.setHotkeyCode(triggerKeyCodes[13 - i]);
                PianoKeys_div_minus_eq[i] = key;
            }

            newGroup.append(new ButtonGroup<>(PianoKeys_div_minus_eq));

        }

        return newGroup;
    }

    public static ButtonGroup<Button> newNeatPianoKeyGroup(
            int leftUpX, int leftUpY, int width, int height,
            int standoffDistance, Color colorUp, BufferedImage[] pasters,
            IExecuteTask[] CanExecuteTasks,
            int PianoKeyCount, int singleRowMaxCount) {

        Button[] PianoKeys = new Button[PianoKeyCount];

        Pair<ArrayList<Integer>, ArrayList<Integer>> PianoKeysXY = calculateLayout(leftUpX, leftUpY, width, height,
                standoffDistance, PianoKeyCount, singleRowMaxCount);

        ArrayList<Integer> PianoKey_Xs = PianoKeysXY.getValue0();
        ArrayList<Integer> PianoKey_Ys = PianoKeysXY.getValue1();

        for (int i = 0; i < PianoKeyCount; ++i) {
            PianoKeys[i] = newPianoKey(
                    PianoKey_Xs.get(i), PianoKey_Ys.get(i), width, height, CanExecuteTasks[i],
                    colorUp, pasters[i], 0, 0, width, height
            );
        }

        return new ButtonGroup<>(PianoKeys);
    }

    /**
     * @return ([x1,x2,x3,x4,...],[y1,y2,y3,y4,...])
     */
    private static Pair<ArrayList<Integer>, ArrayList<Integer>>
    calculateLayout(
            int leftUpX, int leftUpY, int keyWidth,
            int keyHeight, int interval,
            int keyCount, int maxNumARow
    ) {

        if (keyCount % maxNumARow != 0)
            throw new RuntimeException(keyCount + " can't be divisible by "+maxNumARow);

        ArrayList<Integer> xs = new ArrayList<>(keyCount);
        ArrayList<Integer> ys = new ArrayList<>(keyCount);

        int lineCount = keyCount / maxNumARow;

        for (int curLine = lineCount - 1; curLine >= 0; --curLine) {
            for (int curColumn = 0; curColumn < maxNumARow; ++curColumn) {

                xs.add(leftUpX + curColumn * (interval + keyWidth));
                ys.add(leftUpY + curLine * (interval + keyHeight));

            }
        }

        return new Pair<>(xs, ys);
    }
}
