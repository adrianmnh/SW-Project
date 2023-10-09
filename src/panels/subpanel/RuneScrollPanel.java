package panels.subpanel;

import classes.subclasses.*;
import database.RuneDB;
import panels.MainAppPanel;
import panels.MainFrame;
import panels.MyPanel;
import runes.Rune;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

import static tools.HelperMethods.*;

public class RuneScrollPanel extends JScrollPane{

    private final JPanel runePanel;

    public JPanel innerPanel;

    public JPanel right;
    public MainAppPanel parentPanel;
    public MainFrame parentFrame;

    final int REPEAT = 1;
    int ICON_DIMENSION = 80;
    final int ROW_HEIGHT = 150;
    final int LEFT_WIDTH = 130 + 20;
    final int RIGHT_WIDTH = 122 + 5;
    final Color FONT_COLOR = new Color(0xD1DCB5);
    int ROWS;
    int runeCount = 0;

    MonsterImageIcon selectedMonster;

    public ImageIcon runeAvailable;
    public ImageIcon runeEquippedOnCurrent;
    public ImageIcon runeNotAvailable;

    public RuneScrollPanel(MyPanel parentPanel, JPanel panel, JPanel l1, JPanel l2)  {
        this.parentFrame = parentPanel.frame;

        this.runePanel = panel;

        resizeComponent(this,LEFT_WIDTH + RIGHT_WIDTH + 15 + 5, (ROW_HEIGHT * 4) + 1);

        this.setBorder(BorderFactory.createEmptyBorder());

        this.innerPanel = l1;
        this.innerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, parentFrame.baseRed));
//        this.left.setBackground(parentFrame.baseRed);

//        this.right = l2;
//        this.right.setBorder(BorderFactory.createEmptyBorder());

//        this.left2 = l2;
        this.parentPanel = (MainAppPanel) parentPanel;
//        this.outerComponent = component;

        JScrollBar customScrollBar = new MacStyleScrollBar(Adjustable.VERTICAL);
        customScrollBar.setBackground(this.parentFrame.baseColor);
        this.setVerticalScrollBar(customScrollBar);
        this.getVerticalScrollBar().setUnitIncrement(ROW_HEIGHT / 2);

        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        runePanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));

        innerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
//        right.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        runeAvailable = scaleImage(parentFrame.uiResources.getImageIcon("runeon.png"), ROW_HEIGHT-2, ROW_HEIGHT-2);
        runeEquippedOnCurrent = scaleImage(parentFrame.uiResources.getImageIcon("runeoff2.png"), ROW_HEIGHT-2, ROW_HEIGHT-2);
        runeNotAvailable = scaleImage(parentFrame.uiResources.getImageIcon("runered.png"), ROW_HEIGHT-2, ROW_HEIGHT-2);

    }

    public void loadAssetsIntoPanels() {

        int size = this.parentPanel.offlineRuneBag.size();
        RuneBag ref = this.parentPanel.offlineRuneBag;
        ref.sortByPos();

        for (int i = 0; i < REPEAT; i++) {
            for (Rune r : ref ) {
                addRow(r);
            }
        }

        runePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {

                System.out.println("Click From :::::: Rune Scroll Panel ::::::::");


                JLabel message1 = new JLabel("To engrave runes");
                JLabel message2 = new JLabel("Please selected a monster first.              ");

                selectedMonster = parentPanel.selectedMonster;
                if(selectedMonster == null){
                    JOptionPane.showMessageDialog(parentFrame, new Object[] { message1, message2 },
                            "Watcher", JOptionPane.INFORMATION_MESSAGE, runeAvailable);
                    return;
                }

                int col = e.getX()/LEFT_WIDTH;
                int row = e.getY()/ROW_HEIGHT;
                System.out.print("row:" + row + " " + "col:" +col + " ----- " );

                JPanel temp;
                UIRuneBoxImage boxSelected = null;
                Rune runeSelected = null;

                boxSelected = (UIRuneBoxImage)((JPanel) innerPanel.getComponent(row)).getComponent(0);

                runeSelected = ((UIRuneBoxImage)((JPanel) innerPanel.getComponent(row)).getComponent(0)).rune;

                handleClickOnRune(boxSelected);





            }
        });

        parentFrame.reframe();

    }
    public void addRow(Rune r){
//        JLabel rune = new JLabel(image);
//        UIRuneBoxImage runeBoxImage;
//        if(!r.isEquipped){
//            runeBoxImage = new UIRuneBoxImage(r, runeAvailable);
//        } else {
////            runeBoxImage = new RuneBoxImage(r, runeEquipped);
//            runeBoxImage = new UIRuneBoxImage(r, runeNotAvailable);
//        }
//
//        UIRuneBoxLabel runeBoxLabel = new UIRuneBoxLabel(r, FONT_COLOR, RIGHT_WIDTH, ROW_HEIGHT);
//
//        JPanel row = new JPanel();
//        row.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 0, parentFrame.baseRed));
//        row.add(runeBoxImage);
//        row.add(runeBoxLabel);
//        row.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
//        row.setAlignmentY(Component.CENTER_ALIGNMENT);
//        resizeComponent(row, LEFT_WIDTH + RIGHT_WIDTH + 5, ROW_HEIGHT );
        UIRuneBox row = new UIRuneBox(r, runeAvailable, runeNotAvailable, runeEquippedOnCurrent,  parentFrame, FONT_COLOR, LEFT_WIDTH, RIGHT_WIDTH, ROW_HEIGHT);

        this.innerPanel.add(row);
        changeSize();
    }

    public void changeSize() {
        this.runeCount++;
        resizeComponent(innerPanel, LEFT_WIDTH + RIGHT_WIDTH + 5, (ROW_HEIGHT * runeCount) + 1 );
        parentFrame.reframe();
    }

    public void updateDisplay(){
        System.out.println("MonsterID After: " + parentPanel.monsterSelectedSummonId);
        int currentMonsterId = parentPanel.monsterSelectedSummonId;
        System.out.println(Arrays.toString(parentPanel.runesEngraved));

        MonsterRuneMap monsterRuneMap = parentPanel.monsterRuneMap;
        RuneSet runeSet = monsterRuneMap.getBySummonId(currentMonsterId);
        UIRuneBoxImage runeBoxImage;
        for(Component component: innerPanel.getComponents()){
            runeBoxImage = (UIRuneBoxImage)((JPanel)component).getComponent(0);
            Rune rune = runeBoxImage.rune;
            System.out.print("\trune: " + rune.runeId + " isEquipped: " + rune.isEquipped + " ");
            if(runeSet.runeIn(rune)){
                System.out.println("runeIn current monster");
                runeBoxImage.setIcon(runeEquippedOnCurrent);
            } else {
                if(rune.isEquipped){
                    System.out.println("runeNotIn current monster");
                    runeBoxImage.setIcon(runeNotAvailable);
                }
                else{
                    System.out.println("rune available");
                    runeBoxImage.setIcon(runeAvailable);

                }
//                boxSelected.setIcon(runeOn);
            }
        }
    }

    public void handleClickOnRune(UIRuneBox boxSelected){

        selectedMonster = parentPanel.selectedMonster;

        Rune runeSelected = boxSelected.runeBoxLabel.rune;
        Object[] options = {"Yes", "Cancel"}; // Custom button labels

        int result;

        int summonId = selectedMonster.summonId;

        boolean equippedOnCurrent = parentPanel.monsterRuneMap.runeIn(summonId, runeSelected);

        JLabel engraveRune = new JLabel("Do you want to Engrave Rune on " +  selectedMonster.imgName + "?              ");
        JLabel removeRuneFromCurrent = new JLabel("Do you want to Remove Rune from " +  selectedMonster.imgName + "?              ");
        JLabel removeRuneFromAnother = new JLabel("Do you want to Remove Rune from another monster?              ");


        System.out.println("runeSelected.isEquipped: " + runeSelected.isEquipped);
        System.out.println("RuneIN: " + parentPanel.monsterRuneMap.runeIn(summonId, runeSelected));

        int switchCase = -1;

        /******************** Case 1********************: Rune is equipped by current monster*/
        if(runeSelected.isEquipped && equippedOnCurrent){


            result = JOptionPane.showOptionDialog(parentFrame, new Object[] { removeRuneFromCurrent, runeSelected.toStringGUI()}, "Watcher", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE, runeEquippedOnCurrent, options, options[0]);
            switchCase = 1;
        }
        /******************** Case 2********************: Rune is equipped by another monster*/
        else if(runeSelected.isEquipped && !equippedOnCurrent){

            String s = runeSelected.toStringGUI();
            s = s.substring(0, s.length()-1);
            JLabel label = new JLabel(s + " is equipped by another monster.              ");


            result = JOptionPane.showOptionDialog(parentFrame, new Object[] { removeRuneFromAnother, runeSelected.toStringGUI()}, "Watcher", JOptionPane.OK_CANCEL_OPTION,                            JOptionPane.PLAIN_MESSAGE, runeNotAvailable, options, options[0]);
        }
        /******************** Case 1********************: Rune is not equipped*/
        else {

            String s = runeSelected.toStringGUI();
            s = s.substring(0, s.length()-1);
            JLabel label = new JLabel(s + " is equipped by another monster.              ");



            result = JOptionPane.showOptionDialog(parentFrame, new Object[] { engraveRune, runeSelected.toStringLabels()}, "Watcher", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE, runeAvailable, options, options[0]);
            switchCase = 3;
        }


        if (result == JOptionPane.OK_OPTION) {

            int runePos = runeSelected.getPosInt();
            System.out.println("runePos: " + runePos + " runeID: " + runeSelected.runeId);

            switch (switchCase) {
                case 1:
                    System.out.println("Case 1: Rune is equipped by current monster");

                    break;
                case 2:
                    System.out.println("Case 2");


                    break;
                case 3:
                    System.out.println("Case 3: Rune is not equipped");
                    parentPanel.runesEngraved[runePos] = runeSelected.runeId;
                    applyRune(boxSelected.runeBoxImage);

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

    }

    public void applyRune(UIRuneBoxImage boxSelected){
        boxSelected.rune.isEquipped = true;
        boxSelected.rune.summonAlias = parentPanel.selectedMonster.alias;
        boxSelected.rune.summonId = parentPanel.selectedMonster.summonId;
        boxSelected.updateDisplay();
        boxSelected.setIcon(runeEquippedOnCurrent);
        parentPanel.applyRune(boxSelected.rune.runeId, boxSelected.rune.getPosInt());

        System.out.println("monsterSelectedID: " + parentPanel.monsterSelectedSummonId);
        parentPanel.monsterRuneMap.getBySummonId(parentPanel.monsterSelectedSummonId).add(boxSelected.rune);
//        parentPanel.monsterRuneMap2.get()

        RuneDB runeDB = new RuneDB();
        int result = runeDB.execEngraveRuneQuery(parentFrame.getCurrentUserID(), parentPanel.currentMonster, boxSelected.rune);
        runeDB.closeConnection();
        if (result != -1) {
            System.out.println("Rune Engraved");
            System.out.println(result + " rows affected");
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
