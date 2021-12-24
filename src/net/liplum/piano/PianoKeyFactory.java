package net.liplum.piano;

import org.javatuples.Pair;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static net.liplum.animation.GradualAnimationFactory.newGradualAnimation;

public class PianoKeyFactory {

    private PianoKeyFactory() {
    }

    /**
     * <font color=red>注：使用KeyCode</font>
     *
     * @param x              当前钢琴键的X坐标
     * @param y              当前钢琴键的Y坐标
     * @param width          当前钢琴键的 宽度
     * @param height         当前钢琴键的 高度
     * @param CanExecuteTask 当前钢琴键 播放的 音频
     * @param triggerKeyCode 当前钢琴键 的按键触发KeyChar
     * @param colorUp        当前钢琴键 弹起时的颜色
     * @param colorDown      当前钢琴键 按下时的颜色
     * @param paster         当前钢琴键上 的贴纸图像
     * @param dx             贴纸的位置 的X坐标
     * @param dy             贴纸的位置 的Y坐标
     * @return 一个由以上参数构成的钢琴键
     */
    public static PianoKey newPianoKey(int x, int y, int width, int height, CanExecuteTask CanExecuteTask,
                                       int triggerKeyCode, Color colorUp, BufferedImage paster, int dx, int dy, int pasterZoomWidth,
                                       int pasterZoomHeight) {

        return new PianoKey(triggerKeyCode,
                newGradualAnimation(4, x, y, width, height, colorUp, paster, dx, dy, pasterZoomWidth, pasterZoomHeight),
                x, y, width, height, CanExecuteTask, true);
    }

    /**
     * <font color=red>注：没有KeyCode</font>
     *
     * @param x              当前钢琴键的X坐标
     * @param y              当前钢琴键的Y坐标
     * @param width          当前钢琴键的 宽度
     * @param height         当前钢琴键的 高度
     * @param CanExecuteTask 当前钢琴键 播放的 音频
     * @param triggerKeyCode 当前钢琴键 的按键触发KeyChar
     * @param colorUp        当前钢琴键 弹起时的颜色
     * @param colorDown      当前钢琴键 按下时的颜色
     * @param paster         当前钢琴键上 的贴纸图像
     * @param dx             贴纸的位置 的X坐标
     * @param dy             贴纸的位置 的Y坐标
     * @return 一个由以上参数构成的钢琴键
     */
    public static PianoKey newPianoKey(int x, int y, int width, int height, CanExecuteTask CanExecuteTask,
                                       Color colorUp, BufferedImage paster, int dx, int dy, int pasterZoomWidth, int pasterZoomHeight) {

        return new PianoKey(PianoKey.NULL_KEY_CODE,
                newGradualAnimation(4, x, y, width, height, colorUp, paster, dx, dy, pasterZoomWidth, pasterZoomHeight),
                x, y, width, height, CanExecuteTask, true);
    }

    /**
     * <font color=red>注：使用KeyCode</font>
     *
     * @param leftUpX          整个钢琴键组的最左上角的X坐标
     * @param leftUpY          整个钢琴键组的最左上角的Y坐标
     * @param width            每个钢琴键 的 宽度
     * @param height           每个钢琴键 的 高度
     * @param standoffDistance 每个钢琴键相隔的距离
     * @param colorUp          所有钢琴键 弹起时的颜色
     * @param colorDown        所有钢琴键 按下时的颜色
     * @param pasters          所有钢琴键上 的贴纸图像 列表 <font color=red>注：列表长度必须为14</font>
     * @param CanExecuteTasks  所有钢琴键的 播放的声音 列表 <font color=red>注：列表长度必须为14</font>
     * @param triggerKeyCodes  所有钢琴键的 键盘按键触发KeyCode 列表
     *                         <font color=red>注：列表长度必须为14</font>
     * @return 一个由上述参数组成的 新的钢琴键组 ，满足 钢琴的需求
     * 参数导入顺序：1,2,3,4,5,6,7,8,9,mul,add,div,minus,eq
     */
    public static PianoKeyGroup<PianoKey> newPiano(int leftUpX, int leftUpY, int width, int height,
                                                   int standoffDistance, Color colorUp, BufferedImage[] pasters, CanExecuteTask[] CanExecuteTasks,
                                                   int[] triggerKeyCodes) {

        PianoKeyGroup<PianoKey> newGroup;

//		1	
        {// 此代码块会创建 1到9 钢琴键
            PianoKey[] PianoKeys_1_to_9 = new PianoKey[9];

            Pair<ArrayList<Integer>, ArrayList<Integer>> PianoKeysXY_1_to_9 = calculateLayout(leftUpX, leftUpY, width,
                    height, standoffDistance, 9, 3);
            ArrayList<Integer> PianoKey_Xs = PianoKeysXY_1_to_9.getValue0();
            ArrayList<Integer> PianoKey_Ys = PianoKeysXY_1_to_9.getValue1();

            for (int i = 0; i < 9; ++i) {
                PianoKeys_1_to_9[i] = newPianoKey(PianoKey_Xs.get(i), PianoKey_Ys.get(i), width, height,
                        CanExecuteTasks[i], triggerKeyCodes[i], colorUp, pasters[i], 0, 0, width, height);
            }

            newGroup = new PianoKeyGroup<PianoKey>(PianoKeys_1_to_9);
        } // 此代码块会创建 1到9 钢琴键

//		2
        {// 此代码块会创建 mul 钢琴键
            int mulLeftUpX = leftUpX + 3 * (width + standoffDistance);
            int mulLeftUpY = leftUpY;

            newGroup.add(newPianoKey(mulLeftUpX, mulLeftUpY, width, height, CanExecuteTasks[9], triggerKeyCodes[9],
                    colorUp, pasters[9], 0, 0, width, height));

        } // 此代码块会创建 mul 钢琴键

//		3
        {// 此代码块会创建 add 钢琴键
            int addLeftUpX = leftUpX + 3 * (width + standoffDistance);
            int addLeftUpY = leftUpY + height + standoffDistance;
            int addHeight = 2 * height + standoffDistance;
            newGroup.add(newPianoKey(addLeftUpX, addLeftUpY, width, addHeight, CanExecuteTasks[10], triggerKeyCodes[10],
                    colorUp, pasters[10], 0, (addHeight - height) / 2, width, height));

        } // 此代码块会创建 add 钢琴键

//		4	
        {// 此代码块会创建 div,minus与eq 钢琴键
            PianoKey[] PianoKeys_div_minus_eq = new PianoKey[3];

            int div_minus_eq_leftUpX = leftUpX + 4 * (width + standoffDistance);
            int div_minus_eq_leftUpY = leftUpY;

            Pair<ArrayList<Integer>, ArrayList<Integer>> PianoKeysXY_div_minus_eq = calculateLayout(
                    div_minus_eq_leftUpX, div_minus_eq_leftUpY, width, height, standoffDistance, 3, 1);

            ArrayList<Integer> PianoKey_Xs = PianoKeysXY_div_minus_eq.getValue0();
            ArrayList<Integer> PianoKey_Ys = PianoKeysXY_div_minus_eq.getValue1();

            for (int i = 0; i < 3; ++i) {
                PianoKeys_div_minus_eq[i] = newPianoKey(PianoKey_Xs.get(i), PianoKey_Ys.get(i), width, height,
                        CanExecuteTasks[13 - i], triggerKeyCodes[13 - i], colorUp, pasters[13 - i], 0, 0, width,
                        height);
            }

            newGroup.append(new PianoKeyGroup<PianoKey>(PianoKeys_div_minus_eq));

        } // 此代码块会创建 div,minus与eq 钢琴键

        return newGroup;
    }

    /**
     * <font color=red>注：没有KeyCode</font>
     *
     * @param leftUpX          整个钢琴键组的最左上角的X坐标
     * @param leftUpY          整个钢琴键组的最左上角的Y坐标
     * @param width            每个钢琴键 的 宽度
     * @param height           每个钢琴键 的 高度
     * @param standoffDistance 每个钢琴键相隔的距离
     * @param colorUp          所有钢琴键 弹起时的颜色
     * @param colorDown        所有钢琴键 按下时的颜色
     * @param pasters          所有钢琴键上 的贴纸图像 列表 <font color=red>注：列表长度必须为14</font>
     * @param CanExecuteTasks  所有钢琴键的 播放的声音 列表 <font color=red>注：列表长度必须为14</font>
     * @param triggerKeyCodes  所有钢琴键的 键盘按键触发KeyCode 列表
     *                         <font color=red>注：列表长度必须为14</font>
     * @return 一个由上述参数组成的 新的钢琴键组 ，满足 钢琴的需求
     * 参数导入顺序：1,2,3,4,5,6,7,8,9,mul,add,div,minus,eq
     */
    public static PianoKeyGroup<PianoKey> newPiano(int leftUpX, int leftUpY, int width, int height,
                                                   int standoffDistance, Color colorUp, BufferedImage[] pasters, CanExecuteTask[] CanExecuteTasks) {

        PianoKeyGroup<PianoKey> newGroup;

//		1	
        {// 此代码块会创建 1到9 钢琴键
            PianoKey[] PianoKeys_1_to_9 = new PianoKey[9];

            Pair<ArrayList<Integer>, ArrayList<Integer>> PianoKeysXY_1_to_9 = calculateLayout(leftUpX, leftUpY, width,
                    height, standoffDistance, 9, 3);
            ArrayList<Integer> PianoKey_Xs = PianoKeysXY_1_to_9.getValue0();
            ArrayList<Integer> PianoKey_Ys = PianoKeysXY_1_to_9.getValue1();

            for (int i = 0; i < 9; ++i) {
                PianoKeys_1_to_9[i] = newPianoKey(PianoKey_Xs.get(i), PianoKey_Ys.get(i), width, height,
                        CanExecuteTasks[i], colorUp, pasters[i], 0, 0, width, height);
            }

            newGroup = new PianoKeyGroup<PianoKey>(PianoKeys_1_to_9);
        } // 此代码块会创建 1到9 钢琴键

//		2
        {// 此代码块会创建 mul 钢琴键
            int mulLeftUpX = leftUpX + 3 * (width + standoffDistance);
            int mulLeftUpY = leftUpY;

            newGroup.add(newPianoKey(mulLeftUpX, mulLeftUpY, width, height, CanExecuteTasks[9], colorUp, pasters[9], 0,
                    0, width, height));

        } // 此代码块会创建 mul 钢琴键

//		3
        {// 此代码块会创建 add 钢琴键
            int addLeftUpX = leftUpX + 3 * (width + standoffDistance);
            int addLeftUpY = leftUpY + height + standoffDistance;
            int addHeight = 2 * height + standoffDistance;
            newGroup.add(newPianoKey(addLeftUpX, addLeftUpY, width, addHeight, CanExecuteTasks[10], colorUp,
                    pasters[10], 0, (addHeight - height) / 2, width, height));

        } // 此代码块会创建 add 钢琴键

//		4	
        {// 此代码块会创建 div,minus与eq 钢琴键
            PianoKey[] PianoKeys_div_minus_eq = new PianoKey[3];

            int div_minus_eq_leftUpX = leftUpX + 4 * (width + standoffDistance);
            int div_minus_eq_leftUpY = leftUpY;

            Pair<ArrayList<Integer>, ArrayList<Integer>> PianoKeysXY_div_minus_eq = calculateLayout(
                    div_minus_eq_leftUpX, div_minus_eq_leftUpY, width, height, standoffDistance, 3, 1);

            ArrayList<Integer> PianoKey_Xs = PianoKeysXY_div_minus_eq.getValue0();
            ArrayList<Integer> PianoKey_Ys = PianoKeysXY_div_minus_eq.getValue1();

            for (int i = 0; i < 3; ++i) {
                PianoKeys_div_minus_eq[i] = newPianoKey(PianoKey_Xs.get(i), PianoKey_Ys.get(i), width, height,
                        CanExecuteTasks[13 - i], colorUp, pasters[13 - i], 0, 0, width, height);
            }

            newGroup.append(new PianoKeyGroup<PianoKey>(PianoKeys_div_minus_eq));

        } // 此代码块会创建 div,minus与eq 钢琴键

        return newGroup;
    }

    public static PianoKeyGroup<PianoKey> newNeatPianoKeyGroup(int leftUpX, int leftUpY, int width, int height,
                                                               int standoffDistance, Color colorUp, BufferedImage[] pasters, CanExecuteTask[] CanExecuteTasks,
                                                               int PianoKeyCount, int singleRowMaxCount) {

        PianoKeyGroup<PianoKey> newGroup = null;

        PianoKey[] PianoKeys = new PianoKey[PianoKeyCount];

        Pair<ArrayList<Integer>, ArrayList<Integer>> PianoKeysXY = calculateLayout(leftUpX, leftUpY, width, height,
                standoffDistance, PianoKeyCount, singleRowMaxCount);

        ArrayList<Integer> PianoKey_Xs = PianoKeysXY.getValue0();
        ArrayList<Integer> PianoKey_Ys = PianoKeysXY.getValue1();

        for (int i = 0; i < PianoKeyCount; ++i) {
            PianoKeys[i] = newPianoKey(PianoKey_Xs.get(i), PianoKey_Ys.get(i), width, height, CanExecuteTasks[i],
                    colorUp, pasters[i], 0, 0, width, height);
        }

        newGroup = new PianoKeyGroup<PianoKey>(PianoKeys);

        return newGroup;
    }

    /**
     * @param leftUpX           小钢琴键组 的最左上角的X坐标
     * @param leftUpY           小钢琴键组 的最左上角的Y坐标
     * @param width             每个钢琴键 的 宽度
     * @param height            每个钢琴键 的 高度
     * @param standoffDistance  每个钢琴键相隔的距离
     * @param PianoKeyCount     总的钢琴键的数量<font color=
     *                          red>注：必须能被singleRowMaxCount整除</font>
     * @param singleRowMaxCount 每行钢琴键的最大个数<font color=red>注：不能为0</font>
     * @return 返回一个Pair对象，包涵两个int数组，前者A是x的数组，后者B是y的数组
     */
    private static Pair<ArrayList<Integer>, ArrayList<Integer>> calculateLayout(int leftUpX, int leftUpY, int width,
                                                                                int height, int standoffDistance, int PianoKeyCount, int singleRowMaxCount) {

        if (PianoKeyCount % singleRowMaxCount != 0)
            throw new IndexOutOfBoundsException("钢琴键总数量 不能被 单行最大钢琴键数量 整除。");

        ArrayList<Integer> xs = new ArrayList<Integer>(PianoKeyCount);
        ArrayList<Integer> ys = new ArrayList<Integer>(PianoKeyCount);

        int lineCount = PianoKeyCount / singleRowMaxCount;

        for (int curLine = lineCount - 1; curLine >= 0; --curLine) {
            for (int curColumn = 0; curColumn < singleRowMaxCount; ++curColumn) {

                xs.add(leftUpX + curColumn * (standoffDistance + width));
                ys.add(leftUpY + curLine * (standoffDistance + height));

            }
        }

        return new Pair<ArrayList<Integer>, ArrayList<Integer>>(xs, ys);
    }
}
