package classes.subclasses;

import javax.swing.border.Border;
import java.awt.*;

public class RoundedBorder implements Border {

    private int radius;
    private Color color;
    private int thickness;

    public RoundedBorder(int radius) {
        this.radius = radius;
    }

    public RoundedBorder(int radius, Color color) {
        this.radius = radius;
        this.color = color;
    }

    public RoundedBorder(int radius, Color color, int thickness) {
        this.radius = radius;
        this.color = color;
        this.thickness = thickness;
    }

    public Insets getBorderInsets(Component c) {
        int t = thickness > 0 ? thickness : radius + 1;
        return new Insets(t, t, t + 1, t);
    }

    public boolean isBorderOpaque() {
        return false;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (color != null) {
            g2d.setColor(color);
        } else {
            g2d.setColor(c.getForeground());
        }

        int t = thickness > 0 ? thickness : radius + 1;
        g2d.setStroke(new BasicStroke(t));
        g2d.drawRoundRect(x + t / 2, y + t / 2, width - t, height - t, radius, radius);

        g2d.dispose();
    }
}
