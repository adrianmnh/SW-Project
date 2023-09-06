package subpanel;

import classes.subclasses.MyImageIcon;
import panels.MainAppPanel;
import panels.MainFrame;
import panels.MyPanel;
import runes.Rune;
import runes.SubStat;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

import static tools.HelperMethods.*;

public class RuneScrollPanel extends JScrollPane{

    private final JPanel runePanel;

    private JPanel left;

    private JPanel right;
    public MainAppPanel parentPanel;
    public MainFrame parentFrame;

    final int REPEAT = 2;
    int ICON_DIMENSION = 80;
    final int ROW_HEIGHT = 140;
    final int LEFT_WIDTH = 120;
    final int RIGHT_WIDTH = 120;
    final Color FONT_COLOR = new Color(0xD1DCB5);
    int ROWS;
    int runeCount = 0;

    public ImageIcon image;

    public RuneScrollPanel(MyPanel parentPanel, JPanel panel, JPanel l1, JPanel l2)  {

        this.runePanel = panel;

        this.left = l1;

        this.right = l2;

//        this.left2 = l2;
        this.parentPanel = (MainAppPanel) parentPanel;
//        this.outerComponent = component;
        this.parentFrame = parentPanel.parentFrame;

//        this.getHorizontalScrollBar().setUnitIncrement(16);

        this.getVerticalScrollBar().setUnitIncrement(14);

        runePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        left.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        right.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        image = scaleImage(parentFrame.resourceAssetIcons.getImage("runeon.png"), ROW_HEIGHT-2, ROW_HEIGHT-2);





//        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


//        loadAssetsIntoPanels();
//        resizeComponent(this, 300, 600);
//        resizeComponent(left, 150, 600);
//        resizeComponent(right, 150, 600);

    }

    public void loadAssetsIntoPanels() {

        int size = this.parentPanel.offlineRuneBag.size();
        int count = -1;

        for (int i = 0; i < REPEAT; i++) {
            for (Rune r : this.parentPanel.offlineRuneBag ) {
                runeCount++;
                addRow(left, right, r);

            }
        }
        this.ROWS = size * REPEAT;
        resizeComponent(left, LEFT_WIDTH, ROW_HEIGHT * runeCount);
        resizeComponent(right, RIGHT_WIDTH, ROW_HEIGHT * runeCount);
        parentFrame.pack();

        runePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e2) {

                for (int i = 0; i < REPEAT; i++) {
                    for (Rune r : parentPanel.offlineRuneBag ) {
                        runeCount++;
                        addRow(left, right, r);
                    }
                }


                resizeComponent(left, LEFT_WIDTH, ROW_HEIGHT * runeCount);
                resizeComponent(right, RIGHT_WIDTH, ROW_HEIGHT * runeCount);

                parentFrame.pack();
//
//                int row = e2.getY();
//                int col = e2.getX();
//                    System.out.println("row:" + row + " " + "col:" +col + " " );
//

//
//                String message = "Clicked on \n -- "
//                        + e2.getX() + " " + e2.getY()
//                        + row + " " + col;
//                System.out.println(row + " " + col + "\n");
//                JOptionPane.showMessageDialog(parentFrame,message, "Watcher", JOptionPane.INFORMATION_MESSAGE, image);



            }
        });


    }
    public void addRow(JPanel left, JPanel right, Rune r){
        JLabel rune = new JLabel(image);
        rune.setBorder(BorderFactory.createLineBorder(FONT_COLOR));
        left.add(rune);
        right.add(addRuneBox(r));
    }

    public Box addRuneBox(Rune r){
        Box outerBox = new Box(BoxLayout.Y_AXIS);
        SubStat[] subStats = r.getSubStats().toArray(new SubStat[0]);
        Box runestats = new Box(BoxLayout.Y_AXIS);
        runestats.setBorder(BorderFactory.createLineBorder(FONT_COLOR));
        JLabel innate = new JLabel(), setGrade = new JLabel(), posMainstat = new JLabel(),
                stat1 = new JLabel(), stat2 = new JLabel(), stat3 = new JLabel(), stat4 = new JLabel();

        setGrade.setText(String.format("%s %s ★", r.getSetString(), r.getGrade()));
        runestats.add(setGrade);
        posMainstat.setText(String.format("Slot %s %s", r.getPos(), r.getMainStat()));
        runestats.add(posMainstat);
        if(r.getRuneInnate()){
            innate.setText(String.format("%s", r.getSubStats().get(4)));
            runestats.add(innate);
        }
        stat1.setText(String.format("%s", r.getSubStats().get(0)));
        runestats.add(stat1);
        stat2.setText(String.format("%s", r.getSubStats().get(1)));
        runestats.add(stat2);
        stat3.setText(String.format("%s", r.getSubStats().get(2)));
        runestats.add(stat3);
        stat4.setText(String.format("%s", r.getSubStats().get(3)));
        runestats.add(stat4);

        System.out.println(r.getSubstatString());
        System.out.println(r.subs);

        for (SubStat s : subStats ) {
            System.out.println(s);
        }

        for (Component comp:runestats.getComponents()) {
            comp.setForeground(FONT_COLOR);
            ((JComponent) comp).setAlignmentY(Component.CENTER_ALIGNMENT);
        }
        runestats.setPreferredSize(new Dimension(RIGHT_WIDTH-2, ROW_HEIGHT-2));
        //set padding left and right to 10
        runestats.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        outerBox.setBorder(BorderFactory.createLineBorder(FONT_COLOR,1));
        outerBox.add(runestats);


        return outerBox;
    }

    public void test() {
        System.out.println("test");
    }

}
