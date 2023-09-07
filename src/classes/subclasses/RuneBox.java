package classes.subclasses;

import runes.Rune;

import javax.swing.*;
import java.awt.*;

public class RuneBox extends Box {

    public Box runeStats;
    public Rune rune;

    public RuneBox(Rune r, Color FONT_COLOR, int RIGHT_WIDTH, int ROW_HEIGHT) {
        super(BoxLayout.Y_AXIS);
        this.rune = r;
        runeStats = new Box(BoxLayout.Y_AXIS);
        runeStats.setBorder(BorderFactory.createLineBorder(FONT_COLOR));
        JLabel innate = new JLabel(), setGrade = new JLabel(), posMainstat = new JLabel(),
                stat1 = new JLabel(), stat2 = new JLabel(), stat3 = new JLabel(), stat4 = new JLabel();

        setGrade.setText(String.format("%s %s ★", r.getSetString(), r.getGrade()));
        runeStats.add(setGrade);
        posMainstat.setText(String.format("Slot %s %s", r.getPos(), r.getMainStat()));
        runeStats.add(posMainstat);
        if(r.getRuneInnate()){
            innate.setText(String.format("%s", r.getSubStats().get(4)));
            runeStats.add(innate);
        }
        stat1.setText(String.format("%s", r.getSubStats().get(0)));
        runeStats.add(stat1);
        stat2.setText(String.format("%s", r.getSubStats().get(1)));
        runeStats.add(stat2);
        stat3.setText(String.format("%s", r.getSubStats().get(2)));
        runeStats.add(stat3);
        stat4.setText(String.format("%s", r.getSubStats().get(3)));
        runeStats.add(stat4);

        for (Component comp:runeStats.getComponents()) {
            comp.setForeground(FONT_COLOR);
            ((JComponent) comp).setAlignmentY(Component.CENTER_ALIGNMENT);
        }
        runeStats.setPreferredSize(new Dimension(RIGHT_WIDTH-2, ROW_HEIGHT-2));
        //set padding left and right to 10
        runeStats.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        this.setBorder(BorderFactory.createLineBorder(FONT_COLOR,1));
        this.add(runeStats);

//        return outerBox;

    }

    @Override
    public String toString() {
        String toReturn = ("RuneBox{\n" +
                "rune=" + rune.toStringGUI() +
                '}');
        return toReturn;
    }
}

/**
 * Creates a <code>Box</code> that displays its components
 * along the specified axis.
 *
 * @param axis can be {@link BoxLayout#X_AXIS},
 *             {@link BoxLayout#Y_AXIS},
 *             {@link BoxLayout#LINE_AXIS} or
 *             {@link BoxLayout#PAGE_AXIS}.
 * @throws AWTError if the <code>axis</code> is invalid
 * @see #createHorizontalBox
 * @see #createVerticalBox
 */