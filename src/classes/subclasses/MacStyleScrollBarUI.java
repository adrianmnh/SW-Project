package classes.subclasses;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class MacStyleScrollBarUI extends BasicScrollBarUI {

    private final int THUMB_SIZE = 30;
    private final int WIDTH = 15;
    private final int HEIGHT = 15;
    private final Color BACKGROUND_COLOR = Color.BLACK;
    private final Color ACCENT_COLOR = Color.PINK;

    @Override
    protected Dimension getMaximumThumbSize() {
        if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
            return new Dimension(0, THUMB_SIZE);
        } else {
            return new Dimension(THUMB_SIZE, 0);
        }
    }

    @Override
    protected Dimension getMinimumThumbSize() {
        if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
            return new Dimension(0, THUMB_SIZE);
        } else {
            return new Dimension(THUMB_SIZE, 0);
        }
    }

    @Override
    protected JButton createIncreaseButton(int i) {
        return new ScrollBarButton();
    }

    @Override
    protected JButton createDecreaseButton(int i) {
        return new ScrollBarButton();
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int x = trackBounds.x;
        int y = trackBounds.y;
        int width = trackBounds.width;
        int height = trackBounds.height;

        if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
            // Decrease the track's width to 13 pixels and add 2 pixels to the left and right
            int trackWidth = WIDTH - 8;

            // Center the track within the original bounds
            x += (width - trackWidth) / 2;
            width = trackWidth;
        } else {
            // Decrease the track's height to 13 pixels and add 2 pixels to the top and bottom
            int trackHeight = HEIGHT - 8;

            // Center the track within the original bounds
            y += (height - trackHeight) / 2;
            height = trackHeight;
        }


        g2.setColor(ACCENT_COLOR);
        g2.fillRoundRect(x , y, width, height, 10, 10);
        g2.dispose();
        c.repaint();
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        Graphics2D g2 = (Graphics2D) g.create();
//        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int x = thumbBounds.x;
        int y = thumbBounds.y;
        int width = thumbBounds.width;
        int height = thumbBounds.height;

        if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
            // Set the thumb's width to 15 pixels and add 2 pixels to the left and right
            int thumbWidth = WIDTH - 2;

            // Center the thumb within the original bounds
            x += (width - thumbWidth) / 2;
            width = thumbWidth;

            g2.setColor(BACKGROUND_COLOR);
            g2.fillRoundRect(x, y, width, height, 10, 10);

            g2.setColor(ACCENT_COLOR);
            g2.drawRoundRect(x, y, width, height-1, 10, 10);


        } else {
            // Set the thumb's height to 15 pixels and add 2 pixels to the top and bottom
            int thumbHeight = HEIGHT - 2;

            // Center the thumb within the original bounds
            y += (height - thumbHeight) / 2;
            height = thumbHeight;

            g2.setColor(BACKGROUND_COLOR);
            g2.fillRoundRect(x, y, width, height, 10, 10);

            g2.setColor(ACCENT_COLOR); // Use a different color for the border
            g2.drawRoundRect(x, y, width -1 , height, 10, 10);

        }


        g2.dispose();
        c.repaint();
    }



    @Override
    public Dimension getPreferredSize(JComponent c) {
        Dimension dim = super.getPreferredSize(c);

        // Adjust the preferred size to half its size
//        if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
//            dim.width = WIDTH;
//        } else {
//            dim.height = HEIGHT;
//        }

        return dim;
    }


    private class ScrollBarButton extends JButton {

        public ScrollBarButton() {
//            setBorder(BorderFactory.createEmptyBorder());
//            setBorder(BorderFactory.createLineBorder(Color.RED, 1));
        }

        @Override
        public void paint(Graphics grphcs) {
        }
    }
}
