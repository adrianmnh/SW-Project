package subpanel;

import classes.Monster;
import classes.subclasses.MonsterImageIcon;
import classes.subclasses.RuneBox;
import classes.subclasses.RuneLabel;
import panels.MainAppPanel;
import panels.MainFrame;
import panels.MyPanel;
import runes.Rune;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
    final int LEFT_WIDTH = 130;
    final int RIGHT_WIDTH = 122;
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
        this.parentFrame = parentPanel.frame;

//        this.getHorizontalScrollBar().setUnitIncrement(16);

        this.getVerticalScrollBar().setUnitIncrement(14);

        runePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        left.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        right.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        image = scaleImage(parentFrame.uiResources.getImage("runeon.png"), ROW_HEIGHT-2, ROW_HEIGHT-2);





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

                MonsterImageIcon monster = parentPanel.selectedMonster;
                if(monster == null){
                    JOptionPane.showMessageDialog(parentFrame,"Please selected a monster first.              ",
                            "Watcher", JOptionPane.INFORMATION_MESSAGE, image);
                    return;
                }

//                for (int i = 0; i < REPEAT; i++) {
//                    for (Rune r : parentPanel.offlineRuneBag ) {
//                        runeCount++;
//                        addRow(left, right, r);
//                    }
//                }
//                resizeComponent(left, LEFT_WIDTH, ROW_HEIGHT * runeCount);
//                resizeComponent(right, RIGHT_WIDTH, ROW_HEIGHT * runeCount);
//                parentFrame.reframe();
//
                int col = e2.getX()/LEFT_WIDTH;
                int row = e2.getY()/ROW_HEIGHT;
                System.out.println("row:" + row + " " + "col:" +col + " " );

                Rune selected = null;

                try{
                    if(col == 0){
    //                    System.out.println(left.getComponentAt(0, e2.getY()));
                        selected = ((RuneLabel)left.getComponentAt(0, e2.getY())).rune;
                    }
                    else{
    //                    System.out.println(right.getComponentAt(0, e2.getY()));
                        selected = ((RuneBox)right.getComponentAt(0, e2.getY())).rune;
                    }

                } catch (Exception e){
                    System.out.println("No rune selected");
                    return;
                }


                String message = "Clicked on \n -- "
                        + e2.getX() + "  " + e2.getY() + "\n"
                        + row + " " + col;
//                JOptionPane.showMessageDialog(parentFrame,message, "Watcher", JOptionPane.INFORMATION_MESSAGE, image);

                JLabel title = new JLabel("Do you want to Engrave Rune on " +  monster.name + "?              ");

                Object[] options = {"Yes", "Cancel"}; // Custom button labels
                int result = JOptionPane.showOptionDialog(parentFrame, new Object[] { title, selected.toStringGUI()}, "Watcher", JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE, image, options, options[0]);

                if (result == JOptionPane.OK_OPTION) {

//                    if(monster.rune1 == null){
//                        monster.rune1 = selected;
//                    }
//                    else if(monster.rune2 == null){
//                        monster.rune2 = selected;
//                    }
//                    else if(monster.rune3 == null){
//                        monster.rune3 = selected;
//                    }
//                    else if(monster.rune4 == null){
//                        monster.rune4 = selected;
//                    }
//                    else if(monster.rune5 == null){
//                        monster.rune5 = selected;
//                    }
//                    else if(monster.rune6 == null){
//                        monster.rune6 = selected;
//                    }
//                    else{
//                        JOptionPane.showMessageDialog(parentFrame,"Monster already has 6 runes.              ",
//                                "Watcher", JOptionPane.INFORMATION_MESSAGE, image);
//                        return;
//                    }
//                    parentPanel.updateMonster(monster);
//                    parentPanel.updateMonsterList();
//                    parentPanel.updateMonsterInfo();

//                        System.out.println(right.getComponent(row));
//                    System.out.println(right.getComponent(row));
                }
//                    selectMonsterFromClick(assetPosInList);
//                    monsterObjectClicked = null;



            }
        });


    }
    public void addRow(JPanel left, JPanel right, Rune r){
//        JLabel rune = new JLabel(image);
        RuneLabel rune = new RuneLabel(r, image);
        rune.setBorder(BorderFactory.createLineBorder(FONT_COLOR));
        left.add(rune);
//        right.add(addRuneBox(r));
//        RuneBox runeBox = new RuneBox(r, FONT_COLOR, RIGHT_WIDTH, ROW_HEIGHT);
//        right.add(runeBox);
        right.add(new RuneBox(r, FONT_COLOR, RIGHT_WIDTH, ROW_HEIGHT));
    }

//    public Box addRuneBox(Rune r){
//        Box outerBox = new Box(BoxLayout.Y_AXIS);
//        Box runestats = new Box(BoxLayout.Y_AXIS);
//        runestats.setBorder(BorderFactory.createLineBorder(FONT_COLOR));
//        JLabel innate = new JLabel(), setGrade = new JLabel(), posMainstat = new JLabel(),
//                stat1 = new JLabel(), stat2 = new JLabel(), stat3 = new JLabel(), stat4 = new JLabel();
//
//        setGrade.setText(String.format("%s %s ★", r.getSetString(), r.getGrade()));
//        runestats.add(setGrade);
//        posMainstat.setText(String.format("Slot %s %s", r.getPos(), r.getMainStat()));
//        runestats.add(posMainstat);
//        if(r.getRuneInnate()){
//            innate.setText(String.format("%s", r.getSubStats().get(4)));
//            runestats.add(innate);
//        }
//        stat1.setText(String.format("%s", r.getSubStats().get(0)));
//        runestats.add(stat1);
//        stat2.setText(String.format("%s", r.getSubStats().get(1)));
//        runestats.add(stat2);
//        stat3.setText(String.format("%s", r.getSubStats().get(2)));
//        runestats.add(stat3);
//        stat4.setText(String.format("%s", r.getSubStats().get(3)));
//        runestats.add(stat4);
//
//        for (Component comp:runestats.getComponents()) {
//            comp.setForeground(FONT_COLOR);
//            ((JComponent) comp).setAlignmentY(Component.CENTER_ALIGNMENT);
//        }
//        runestats.setPreferredSize(new Dimension(RIGHT_WIDTH-2, ROW_HEIGHT-2));
//        //set padding left and right to 10
//        runestats.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
//        outerBox.setBorder(BorderFactory.createLineBorder(FONT_COLOR,1));
//        outerBox.add(runestats);
//
//        return outerBox;
//    }

    public void test() {
        System.out.println("test");
    }

}
