package panels.subpanel;

import static tools.HelperMethods.*;

import classes.subclasses.MacStyleScrollBar;
import classes.subclasses.MonsterImageIcon;
import panels.MainAppPanel;
import panels.MainFrame;
import panels.MyPanel;
import runes.Rune;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;

public class MonsterScrollPanel extends JScrollPane{

    private final JPanel monsterPanel;
    public MainAppPanel parentPanel;

    private Component outerComponent;
    private final MainFrame parentFrame;
    public MonsterImageIcon monsterObjectClicked;
    public MonsterImageIcon monsterObjectSelected;
    final int REPEAT = 1;
    int ICON_DIMENSION = 80;
    final int HEIGHT = 2;

    int COLUMN_SIZE;
    int COLUMNS;

    //        public leftScrollPanel(MainFrame PF, JPanel left1, JPanel left2) throws IOException {
    public MonsterScrollPanel(MyPanel parentPanel, JPanel panel)  {

        this.monsterPanel = panel;
        this.parentPanel = (MainAppPanel) parentPanel;
        this.parentFrame = parentPanel.frame;

        this.monsterPanel.setBackground(parentFrame.baseColor);
        this.parentPanel.setBackground(parentFrame.baseColor);
        this.setBackground(parentFrame.baseColor);



        JScrollBar customScrollBar = new MacStyleScrollBar(Adjustable.HORIZONTAL);
        customScrollBar.setBackground(new Color(0x1F160B));
        this.setHorizontalScrollBar(customScrollBar);
        this.getHorizontalScrollBar().setUnitIncrement(ICON_DIMENSION );

        resizeComponent(this, ICON_DIMENSION * 5, (80 * HEIGHT) + 10 + 15);

        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        this.setBorder(BorderFactory.createEmptyBorder());
    }

    public void loadAssetsIntoPanels() {

        int size = this.parentPanel.frame.baseMonsters.size();
        System.out.println(size);
        int count = -1;
        int testCount=0;


        for (MonsterImageIcon monsterImageIcon : this.parentFrame.baseMonsters) {
//            System.out.println(monsterImageIcon);
//            if(testCount==15) break;
            ImageIcon resized = scaleImage(monsterImageIcon.imgResource, ICON_DIMENSION-2, ICON_DIMENSION-2);
            JLabel l = new JLabel(resized);
            l.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 1));

            ++testCount;
            l.setFocusable(false);

            monsterPanel.add(l);
        }
        COLUMNS = (size * REPEAT) / HEIGHT;
        if(size%2==1)   this.COLUMNS += 1;
//        COLUMNS = (testCount * REPEAT) / HEIGHT;
//        if(testCount%2==1)   this.COLUMNS += 1;


        COLUMN_SIZE = ICON_DIMENSION * COLUMNS;

        resizeComponent(monsterPanel, COLUMN_SIZE, ICON_DIMENSION * HEIGHT);


        monsterPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));


//        this.parentPanel.frame.pack();

        monsterPanel.addMouseListener(  new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e2) {

                try {
                    int size = parentFrame.baseMonsters.size();
                    int row = e2.getY()/ICON_DIMENSION;
                    int col = e2.getX()/ICON_DIMENSION;
                    int pos = row * COLUMNS + col ;
                    int assetPosInList = pos;
                    System.out.println("\n\nrow:" + row + " " + "col:" +col + " " + parentFrame.baseMonsters.get(assetPosInList).imgName);

                    ImageIcon thumbs = parentFrame.uiResources.getImage("thumbs.png");
//                thumbs = scaleImage(thumbs, (int) (thumbs.getIconWidth()*.8), (int) (thumbs.getIconHeight()*.8));
                    ImageIcon img = parentFrame.baseMonsters.get(assetPosInList).imgResource;

                    monsterObjectClicked = parentFrame.baseMonsters.get(assetPosInList);

                    JLabel monsterImage = new JLabel(img);
                    JLabel monsterSelect = new JLabel("              Select " + monsterObjectClicked.monster.getName() + "?");
                    String title = "Select " + monsterObjectClicked.monster.getName() + "?";

                    Object[] options = {"OK", "Cancel"}; // Custom button labels



                    int result = JOptionPane.showOptionDialog(
                            parentFrame, // Use null for the parent frame
                            new Object[]{monsterImage, monsterSelect}, // Your custom content
                            "Monster info", // Title
                            JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.PLAIN_MESSAGE,
                            null, // Use null for the custom icon
                            options, // Custom button labels
                            options[0] // Default selection
                    );

                    if (result == JOptionPane.OK_OPTION) {
                        selectMonsterFromClick(assetPosInList);
                        monsterObjectClicked = null;

//                        parentFrame.reframe();
                    } else {
                        monsterObjectClicked = null;
                    }
//                    Thread.sleep(100);
                } catch (Exception e) {
                    System.out.println(e.getLocalizedMessage());
                }



            }
        });



    }
    private void createMonsterImage(int assetPosInList){

//        this.selectedMonster = parentFrame.monsterResources.get(assetPosInList);
//        this.selectedMonster.monster = parentPanel.monsterBox.get(assetPosInList);
//        this.selectedMonster.img = scaleImage(selectedMonster.img, 90,90);
    }
    private void selectMonsterFromClick(int assetPosInList){

        System.out.println("MonsterID Before: " + parentPanel.currentMonster.getBaseId());

        this.monsterObjectSelected = this.monsterObjectClicked;

        this.monsterObjectSelected.imgResource = scaleImage(monsterObjectSelected.imgResource, 100,100);

        this.parentPanel.setSelectedMonster(monsterObjectSelected);

//        System.out.println(monsterObjectSelected);

        parentPanel.monster_name_label.setText(monsterObjectSelected.monster.getName());

        parentPanel.currentMonster = monsterObjectSelected.monster;

        parentPanel.updateStatDisplay(false);

        if(parentPanel.monsterRuneMap.get(monsterObjectSelected.monster.getBaseId())==null){

            parentPanel.monsterRuneMap.put(monsterObjectSelected.monster, new HashSet<Rune>());
        }

        this.setRunesEquipped();


    }

    private void setRunesEquipped(){
        int id = parentPanel.currentMonster.getBaseId();
        parentPanel.resetRuneArray();

        RuneScrollPanel temp = (RuneScrollPanel) parentPanel.abstract_rune_scroll_panel;

        temp.updateDisplay();
        for(Rune r : parentPanel.monsterRuneMap.get(parentPanel.currentMonster.getBaseId())){
            parentPanel.runesEquipped[r.getPosInt()] = r.runeId;
        }
        parentPanel.updateStatDisplay(false);


    }





}
