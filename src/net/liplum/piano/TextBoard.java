package net.liplum.piano;

import net.liplum.attribute.IMouseMotion;
import net.liplum.attribute.IRender;
import net.liplum.attribute.IUpdate;
import net.liplum.util.StringTools;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Map;

public class TextBoard implements IRender, IUpdate {

    /**
     * No remapping when null
     */
    private static Map<Character, Character> remappingRules = null;

    /**
     * the left-top x,y of this text board
     */
    private int textBoardX, textBoardY;

    /**
     * The width and height of whole text board including progress bar
     */
    private int textBoardWidth, textBoardHeight;

    /**
     * The width and height of text
     */
    private int textWidth, textHeight;

    private String originalText;

    private String[][] displayedText;

    private static String handleText(String text) {
        return text.replace('\n', '\0');
    }

    private static String remappingDisplayedText(String text) {
        String result = new String(text);
        StringTools.remappingChar(remappingRules, result);
        return result;
    }

    @Override
    public void update(long delta) {

    }

    @Override
    public void render(Graphics g) {

    }

    private class ProgressBar implements IUpdate, IRender, IMouseMotion {
        private int progressWidth;

        private int progressHeight;

        private int progressX, progressY;

        private Rectangle decisionRect;

        private Bar bar = null;


        public ProgressBar(int x, int y, int width, int height) {
            this.progressWidth = width;
            this.progressHeight = height;
            this.progressX = x;
            this.progressY = y;
            this.decisionRect = new Rectangle(textBoardX, textBoardY, textBoardWidth, textBoardHeight);
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


        private class Bar implements IUpdate, IRender {

            private volatile int barX, barY;

            private Color barColor;

            private int barWidth, barHeight;

            @Override
            public void render(Graphics g) {

            }

            @Override
            public void update(long delta) {

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
//	private ProgressBar progressBar;
//
//	private volatile boolean canWriter = false, displayMode = false;
//
//	private Map<Character, Character> remappingRegulation = null;
//
//	private int sellWidth, sellHeight;
//
//	private int rowHoldMaxCount;
//	private int rowCount;
//
//	private String[][] displayedTextStringArray;
//
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
//	public synchronized void setReplacedRegulation(Map<Character, Character> replacedRegulation) {
//		remappingRegulation = replacedRegulation;
//	}
//
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
//
//	private void initBoardWidthAndHeight() {
//		this.textBoardWidth = sellHeight * rowHoldMaxCount;
//		
//	}
//	
//
//	private synchronized void drawRenderImage() {
//
//		BufferedImage image = new BufferedImage(textBoardWidth, textBoardHeight, BufferedImage.TYPE_INT_ARGB);
//		Graphics g = image.getGraphics();
//		g.setColor(background);
//		g.fillRect(0, 0, textBoardWidth, textBoardHeight);
//
//		g.setColor(Color.BLACK);
//
//		for (int r = 1; r < rowCount; ++r) {
//			int tempWidth = r * sellWidth;
//			g.drawLine(tempWidth, 0, tempWidth, textBoardHeight);
//		}
//
//		for (int c = 1; c < rowHoldMaxCount; ++c) {
//			int tempHeight = c * sellHeight;
//			g.drawLine(0, tempHeight, textBoardWidth, tempHeight);
//		}
//
//
//		Pair<Integer, Integer> pair = iterator.getIndex(curIndexText);
//		int r = pair.getValue0(), c = pair.getValue1();
//		g.setColor(selection);
//		g.fillRect(r * sellWidth, c * sellHeight, sellWidth, sellHeight);
//
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
//	private synchronized void initIteratorAndRowCount() {
//		this.rowCount = displayedText.length() / rowHoldMaxCount + 1;
//		this.iterator = new Array2DIterator(rowCount, rowHoldMaxCount);
//	}
//
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
}
