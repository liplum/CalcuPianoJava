package net.liplum.piano;

import net.liplum.attribute.MouseMotionListener;
import net.liplum.attribute.IRender;
import net.liplum.attribute.IUpdate;
import net.liplum.util.StringTools;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Map;

public class TextBoard implements IRender, IUpdate {

    /**
     * <pre>
     * 重映射规则
     * 为null，则表示不进行重映射
     */
    private static Map<Character, Character> remappingRegulation = null;

    /**
     * 整个文本框的左上角x、y坐标
     */
    private int textBoardX, textBoardY;

    /**
     * 整个文本框的宽度、高度（包括进度条）
     */
    private int totalTextBoardWidth, totalTextBoardHeight;

    /**
     * <pre>
     * 文本部分的宽度
     */
    private int textBoardWidth;

    /**
     * <pre>
     * 文本部分的高度
     * 必须保证 此值={@linkplain #totalTextBoardHeight 整个文本框高度}
     */
    private int textBoardHeight;

    /**
     * <pre>
     * 原始文本
     * 键入钢琴键时，与此值进行比对
     * 需在使用前进行{@linkplain #handleText(String) 简单处理}
     */
    private String originalText;

    /**
     * <pre>
     * 用于显示的文本
     * <font color=red>row = 当前屏幕上显示的完整行数+1</font>
     * column = 当前屏幕上显示的完整列数
     * 可在转化为数组格式前，进行 {@linkplain #remappingDisplayedText(String) 字符重映射}
     */
    private String[][] displayedText;

    /**
     * @param text 待处理的String
     * @return 简单处理过的String
     */
    private static String handleText(String text) {
        return text.replace('\n', '\0');
    }

    /**
     * @param text 待重映射的String
     * @return 一个新的经过重映射的String
     */
    private static String remappingDisplayedText(String text) {
        String result = new String(text);
        StringTools.remappingChar(remappingRegulation, result);
        return result;
    }

    @Override
    public void update(long delta) {
        // TODO Auto-generated method stub

    }

    @Override
    public void render(Graphics g) {
        // TODO Auto-generated method stub

    }

    /**
     * @author 普冷姆plum
     * TextBoard所持有的 进度条
     */
    private class ProgressBar implements IUpdate, IRender, MouseMotionListener {
        /**
         * <pre>
         * 整个进度条的宽度
         * 必须保证 此值={@linkplain TextBoard#totalTextBoardWidth 整个文本框的宽度}-{@linkplain TextBoard#textBoardWidth 文本部分宽度}
         */
        private int progressWidth;

        /**
         * <pre>
         * 整个进度条的高度
         * 必须保证 此值={@linkplain TextBoard#textBoardHeight}
         */
        private int progressHeight;

        /**
         * 整个进度条的x、y坐标
         */
        private int progressX, progressY;

        private Rectangle decisionRect;

        /**
         * 滑块
         */
        private Bar bar = null;


        /**
         * @param x      整个进度条的x坐标
         * @param y      整个进度条的y坐标
         * @param width  整个进度条的宽度
         * @param height 整个进度条的高度
         */
        public ProgressBar(int x, int y, int width, int height) {
            this.progressWidth = width;
            this.progressHeight = height;
            this.progressX = x;
            this.progressY = y;
            this.decisionRect = new Rectangle(textBoardX, textBoardY, totalTextBoardWidth, totalTextBoardHeight);
        }


        @Override
        public void render(Graphics g) {
            // TODO Auto-generated method stub

        }


        @Override
        public void update(long delta) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (decisionRect.contains(e.getX(), e.getY())) {
                bar.barY = e.getY();
            }
        }


        /**
         * @author 普冷姆plum
         * ProgressBar所持有的 滑块
         */
        private class Bar implements IUpdate, IRender {

            /**
             * 滑块所在的x、y坐标
             */
            private volatile int barX, barY;

            /**
             * 滑块的颜色
             */
            private Color barColor;

            /**
             * 滑块的长度与宽度
             */
            private int barWidth, barHeight;

            @Override
            public void render(Graphics g) {
                // TODO Auto-generated method stub

            }

            @Override
            public void update(long delta) {
                // TODO Auto-generated method stub

            }


        }

    }


//	private int x, y;
//
//	private String originalText, displayedText;
//
//	private int curIndexText = 0;
//
//	private int curDisplayedIndex = 0;
//
//	/**
//	 * 进度条
//	 */
//	private ProgressBar progressBar;
//
//	private volatile boolean canWriter = false, displayMode = false;
//
//	private Map<Character, Character> remappingRegulation = null;
//
//	private int sellWidth, sellHeight;
//
//	/**
//	 * 一行可显示的最大字符数
//	 */
//	private int rowHoldMaxCount;
//	/**
//	 * 总行数
//	 */
//	private int rowCount;
//
//	private String[][] displayedTextStringArray;
//
//	/**
//	 * 用于渲染的谱子图片
//	 */
//	private BufferedImage renderImage;
//
//	private int textBoardWidth, textBoardHeight;
//
//	private Color selection, background;
//
//	private Array2DIterator iterator;
//
//	public TextBoard(int x, int y, int sellWidth, int sellHeight, int rowHoldMaxCount, Color selection,
//			Color background, String text, Map<Character, Character> remappingRegulation) {
//
//		this.sellWidth = sellWidth;
//		this.sellHeight = sellHeight;
//		
//		this.rowHoldMaxCount = rowHoldMaxCount;
//		
//		this.selection = selection;
//		this.background = background;
//		
//		this.remappingRegulation = remappingRegulation;
//		
//		this.setText(text);
//		this.initIteratorAndRowCount();
//		
//		
//		this.transformString();
//
//
//		
//		
//
//	}
//
//	@Override
//	public synchronized void update(long delta) {
//
//	}
//
//	@Override
//	public synchronized void render(Graphics g) {
//		if (displayMode) {
//
//		} else {
//			curDisplayedIndex = iterator.getIndex(curIndexText).getValue0() * sellHeight;
//		}
//		
//		BufferedImage img = renderImage.getSubimage(0, curDisplayedIndex, textBoardWidth, textBoardHeight);
//		g.drawImage(img, x, y, null);
//
//	}
//
//	/**
//	 * @param replacedRegulation 替换规则
//	 */
//	public synchronized void setReplacedRegulation(Map<Character, Character> replacedRegulation) {
//		remappingRegulation = replacedRegulation;
//	}
//
//	/**
//	 * @param t 待设置的文本
//	 */
//	public synchronized void setText(String t) {
//		this.originalText = handleText(t);
//		this.displayedText = new String(originalText);
//		StringTools.remappingChar(remappingRegulation, displayedText);
//	}
//	
//	public synchronized void resetIndex() {
//		this.curIndexText = 0;
//		this.curDisplayedIndex = 0;
//		drawRenderImage();
//	}
//	
//////////////////////////////////////////////////////////////////////////
////	priavte
//	
//	private void initBoardWidthAndHeight() {
//		this.textBoardWidth = sellHeight * rowHoldMaxCount;
//		
//	}
//	
//
//	/**
//	 * <pre>
//	 * 前置条件：{@link #displayedTextStringArray}、{@link #iterator}必须为已知
//	 * 后置条件：准备一副图片，并标明当前选中的色块，赋值给{@link #renderImage}
//	 * </pre>
//	 */
//	private synchronized void drawRenderImage() {
//
//		BufferedImage image = new BufferedImage(textBoardWidth, textBoardHeight, BufferedImage.TYPE_INT_ARGB);
//		Graphics g = image.getGraphics();
//		g.setColor(background);
//		g.fillRect(0, 0, textBoardWidth, textBoardHeight);
//
//		g.setColor(Color.BLACK);
//
////		绘制竖线
//		for (int r = 1; r < rowCount; ++r) {
//			int tempWidth = r * sellWidth;
//			g.drawLine(tempWidth, 0, tempWidth, textBoardHeight);
//		}
//
////		绘制横线
//		for (int c = 1; c < rowHoldMaxCount; ++c) {
//			int tempHeight = c * sellHeight;
//			g.drawLine(0, tempHeight, textBoardWidth, tempHeight);
//		}
//
////		为选中的方块上色
//
//		Pair<Integer, Integer> pair = iterator.getIndex(curIndexText);
//		int r = pair.getValue0(), c = pair.getValue1();
//		g.setColor(selection);
//		g.fillRect(r * sellWidth, c * sellHeight, sellWidth, sellHeight);
//
////		渲染文字
//		int displayedTextLength = displayedText.length();
//		for (int index = 0; index < displayedTextLength; ++index) {
//			Pair<Integer, Integer> textPair = iterator.getIndex(index);
//			int row = textPair.getValue0(), column = textPair.getValue1();
//
//			String tempStr = displayedTextStringArray[row][column];
//			if (tempStr != null)
//				g.drawString(tempStr, row * sellWidth, row * sellHeight);
//
//		}
//
//	}
//
//	/**
//	 * <pre>
//	 * 前置条件：{@link #displayedText}、{@link #rowHoldMaxCount}必须为已知
//	 * 后置条件：初始化{@link #iterator}和{@link #rowCount}
//	 * </pre>
//	 */
//	private synchronized void initIteratorAndRowCount() {
//		this.rowCount = displayedText.length() / rowHoldMaxCount + 1;
//		this.iterator = new Array2DIterator(rowCount, rowHoldMaxCount);
//	}
//
//	/**
//	 * <pre>
//	 * 前置条件：已知{@link #iterator}
//	 * 后置条件：将生成好的对象，赋值给{@link #displayedTextStringArray}
//	 * </pre>
//	 */
//	private synchronized void transformString() {
//
//		displayedTextStringArray = new String[rowCount][rowHoldMaxCount];
//
//		for (int i = 0; i < displayedText.length(); ++i) {
//			Pair<Integer, Integer> pair = iterator.getIndex(i);
//			displayedTextStringArray[pair.getValue0()][pair.getValue1()] = String.valueOf(displayedText.charAt(i));
//		}
//
//	}
//


}
