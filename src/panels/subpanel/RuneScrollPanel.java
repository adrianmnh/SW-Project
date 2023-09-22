package subpanel;

import classes.subclasses.MonsterImageIcon;
import classes.subclasses.MonsterRuneMap;
import classes.subclasses.RuneBox;
import classes.subclasses.RuneLabel;
import database.RuneDB;
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

    public JPanel left;

    public JPanel right;
    public MainAppPanel parentPanel;
    public MainFrame parentFrame;

    final int REPEAT = 1;
    int ICON_DIMENSION = 80;
    final int ROW_HEIGHT = 140;
    final int LEFT_WIDTH = 130;
    final int RIGHT_WIDTH = 122;
    final Color FONT_COLOR = new Color(0xD1DCB5);
    int ROWS;
    int runeCount = 0;

    public ImageIcon runeAvailable;
    public ImageIcon runeEquipped;
    public ImageIcon runeNotAvailable;

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

        runeAvailable = scaleImage(parentFrame.uiResources.getImage("runeon.png"), ROW_HEIGHT-2, ROW_HEIGHT-2);
        runeEquipped = scaleImage(parentFrame.uiResources.getImage("runeoff2.png"), ROW_HEIGHT-2, ROW_HEIGHT-2);
        runeNotAvailable = scaleImage(parentFrame.uiResources.getImage("runered.png"), ROW_HEIGHT-2, ROW_HEIGHT-2);





//        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


//        loadAssetsIntoPanels();
//        resizeComponent(this, 300, 600);
//        resizeComponent(left, 150, 600);
//        resizeComponent(right, 150, 600);

    }

    public void loadAssetsIntoPanels() {

        int size = this.parentPanel.offlineRuneBag.size();

        for (int i = 0; i < REPEAT; i++) {
            for (Rune r : this.parentPanel.offlineRuneBag ) {
//                System.out.println(r.runePosition);
                addRow(r);
            }
        }

        System.out.println("*************************************************************************************");
//        this.ROWS = size * REPEAT;
//        resizeComponent(left, LEFT_WIDTH, ROW_HEIGHT * runeCount);
//        resizeComponent(right, RIGHT_WIDTH, ROW_HEIGHT * runeCount);
//        parentFrame.pack();

        runePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e2) {

                MonsterImageIcon monster = parentPanel.selectedMonster;
                if(monster == null){
                    JOptionPane.showMessageDialog(parentFrame,"To engrave runes,\nPlease selected a monster first.              ",
                            "Watcher", JOptionPane.INFORMATION_MESSAGE, runeAvailable);
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
                System.out.print("row:" + row + " " + "col:" +col + " ----- " );

                RuneLabel boxSelected = null;
                Rune runeSelected = null;

                    boxSelected = (RuneLabel)left.getComponentAt(0, e2.getY());
                    if(col == 0){
                        runeSelected = ((RuneLabel)left.getComponentAt(0, e2.getY())).rune;

                    }
                    else{
                        runeSelected = ((RuneBox)right.getComponentAt(0, e2.getY())).rune;
                    }

                Object[] options = {"Yes", "Cancel"}; // Custom button labels

                int result;

                int monsterid = monster.monster.getId();

                boolean equippedOnCurrent = parentPanel.monsterRuneMap.runeIn(monsterid, runeSelected);

                JLabel engraveRune = new JLabel("Do you want to Engrave Rune on " +  monster.name + "?              ");
                JLabel removeRuneFromCurrent = new JLabel("Do you want to Remove Rune from " +  monster.name + "?              ");
                JLabel removeRuneFromAnother = new JLabel("Do you want to Remove Rune from another monster?              ");


                System.out.println("runeSelected.isEquipped: " + runeSelected.isEquipped);
                System.out.println("RuneIN: " + parentPanel.monsterRuneMap.runeIn(monsterid, runeSelected));

                int switchCase = -1;

                /******************** Case 1********************: Rune is equipped by current monster*/
                if(runeSelected.isEquipped && equippedOnCurrent){
                    result = JOptionPane.showOptionDialog(parentFrame, new Object[] { removeRuneFromCurrent, runeSelected.toStringGUI()}, "Watcher", JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE, runeEquipped, options, options[0]);
                    switchCase = 1;
                }
                /******************** Case 2********************: Rune is equipped by another monster*/
                else if(runeSelected.isEquipped && !equippedOnCurrent){
                    result = JOptionPane.showOptionDialog(parentFrame, new Object[] { removeRuneFromAnother, runeSelected.toStringGUI()}, "Watcher", JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.PLAIN_MESSAGE, runeNotAvailable, options, options[0]);
                }
                /******************** Case 1********************: Rune is not equipped*/
                else {
                    result = JOptionPane.showOptionDialog(parentFrame, new Object[] { engraveRune, runeSelected.toStringGUI()}, "Watcher", JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.PLAIN_MESSAGE, runeAvailable, options, options[0]);
                    switchCase = 3;
                }


                if (result == JOptionPane.OK_OPTION) {

                    int runePos = runeSelected.getPosInt();
                    System.out.println("runePos: " + runePos + " runeID: " + runeSelected.runeId);

                    switch(switchCase){
                        case 1:
                            System.out.println("Case 1: Rune is equipped by current monster");



                            break;
                        case 2:
                            System.out.println("Case 2");


                            break;
                        case 3:
                            System.out.println("Case 3: Rune is not equipped");
                            parentPanel.runesEquipped[runePos] = runeSelected.runeId;
                            applyRune(boxSelected);

                            break;
                        default:
                            System.out.println("Default");


                            break;
                    }


//                    if(!runeSelected.isEquipped){
//                        parentPanel.runesEquipped[runePos] = runeSelected.runeID;
//                        System.out.println(Arrays.toString(parentPanel.runesEquipped));
//
//                        boxSelected.rune.isEquipped = true;
//                        boxSelected.setIcon(runeNotAvailable);
//                        parentPanel.applyRune(boxSelected.rune.runeID, boxSelected.rune.getPosInt());
//
//                        System.out.println("monsterSelectedID: " + parentPanel.monsterSelectedID);
//                        parentPanel.monsterRuneMap.get(parentPanel.monsterSelectedID).add(boxSelected.rune);
//                    } else {
//                        parentPanel.runesEquipped[runePos] = 0;
//                        System.out.println(Arrays.toString(parentPanel.runesEquipped));
//
//                        boxSelected.rune.isEquipped = false;
//                        boxSelected.setIcon(runeAvailable);
//                        parentPanel.removeRune(boxSelected.rune.runeID, boxSelected.rune.getPosInt());
//                        parentPanel.monsterRuneMap.get(parentPanel.monsterSelectedID).remove(boxSelected.rune);
//                    }

//                    runeSelected.isEquipped = true;


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
    public void addRow(Rune r){
//        JLabel rune = new JLabel(image);
        RuneLabel rune;
        if(!r.isEquipped){
            rune = new RuneLabel(r, runeAvailable);
//            rune.setBackground(Color.GREEN);
//            rune.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        } else {
            rune = new RuneLabel(r, runeEquipped);
        }
        rune.setBorder(BorderFactory.createLineBorder(FONT_COLOR));

        this.left.add(rune);
//        right.add(addRuneBox(r));
//        RuneBox runeBox = new RuneBox(r, FONT_COLOR, RIGHT_WIDTH, ROW_HEIGHT);
//        right.add(runeBox);
        this.right.add(new RuneBox(r, FONT_COLOR, RIGHT_WIDTH, ROW_HEIGHT));
        changeSize();
    }

    public void changeSize() {
        this.runeCount++;
        resizeComponent(left, LEFT_WIDTH, ROW_HEIGHT * runeCount);
        resizeComponent(right, RIGHT_WIDTH, ROW_HEIGHT * runeCount);
        parentFrame.reframe();
    }

    public void updateDisplay(){
        System.out.println("MonsterID After: " + parentPanel.currentMonster.getId());
        int currentMonsterId = parentPanel.currentMonster.getId();

        MonsterRuneMap map = parentPanel.monsterRuneMap;
        RuneLabel boxSelected;
        for(Component component: left.getComponents()){
            boxSelected = ((RuneLabel)component);
            Rune rune = boxSelected.rune;
            System.out.println("rune: " + rune.runeId + " isEquipped: " + rune.isEquipped);
            if(map.runeIn(currentMonsterId, rune)){
                boxSelected.setIcon(runeEquipped);
            } else {
                if(rune.isEquipped)
                    boxSelected.setIcon(runeNotAvailable);
                else
                    boxSelected.setIcon(runeAvailable);
//                boxSelected.setIcon(runeOn);
            }
        }
    }

    public void applyRune(RuneLabel boxSelected){
        boxSelected.rune.isEquipped = true;
        boxSelected.setIcon(runeEquipped);
        parentPanel.applyRune(boxSelected.rune.runeId, boxSelected.rune.getPosInt());

        System.out.println("monsterSelectedID: " + parentPanel.monsterSelectedID);
        parentPanel.monsterRuneMap.get(parentPanel.monsterSelectedID).add(boxSelected.rune);

        RuneDB runeDB = new RuneDB();
        boolean result = runeDB.execEngraveRuneQuery(parentFrame.getCurrentUserID(), parentPanel.currentMonster, boxSelected.rune);
        runeDB.closeConnection();
        if (result) {
            System.out.println("Rune Engraved");
        } else {
            System.out.println("Rune Not Engraved");
        }

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


}
