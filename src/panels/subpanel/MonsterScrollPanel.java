package subpanel;

import static tools.HelperMethods.*;

import classes.subclasses.MonsterImageIcon;
import classes.subclasses.MyImageIcon;
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
        this.getHorizontalScrollBar().setUnitIncrement(16);

        loadAssetsIntoPanels();
    }

    public void loadAssetsIntoPanels() {

        int size = this.parentPanel.frame.monsterFiles.size();
        int count = -1;
        int testCount=0;
        monsterPanel.setFocusable(false);

        for (int i = 0; i < REPEAT; i++) {
            for (MyImageIcon img : this.parentFrame.monsterResources) {
                JLabel l = new JLabel(img.img);

                ++testCount;
                l.setFocusable(false);

                monsterPanel.add(l);
//                System.out.println(++count + " - Added " + img.img.getDescription());
            }
        }
        COLUMNS = (size * REPEAT) / 2;
        if(size%2==1)   this.COLUMNS += 1;
//        System.out.println(size);
//        System.out.println(COLUMNS);

        COLUMN_SIZE = ICON_DIMENSION * COLUMNS;

        resizeComponent(monsterPanel, COLUMN_SIZE, ICON_DIMENSION * HEIGHT);


        monsterPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));


        this.parentPanel.frame.pack();

        monsterPanel.addMouseListener(  new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e2) {

                int size = parentFrame.monsterResources.size();
                int row = e2.getY()/ICON_DIMENSION;
                int col = e2.getX()/ICON_DIMENSION;
                int pos = row * COLUMNS + col ;
                int assetPosInList = pos;
                System.out.println("\n\nrow:" + row + " " + "col:" +col + " " + parentFrame.monsterResources.get(assetPosInList).name);

                ImageIcon thumbs = parentFrame.uiResources.getImage("thumbs.png");
//                thumbs = scaleImage(thumbs, (int) (thumbs.getIconWidth()*.8), (int) (thumbs.getIconHeight()*.8));
                ImageIcon img = parentFrame.monsterResources.get(assetPosInList).img;

                monsterObjectClicked = parentFrame.monsterResources.get(assetPosInList);


                Object[] options = {"OK", "Cancel"}; // Custom button labels
                int result = JOptionPane.showOptionDialog(
                        parentFrame, // Use null for the parent frame
                        new Object[]{new JLabel(img)}, // Your custom content
                        "Select " + monsterObjectClicked.monster.getName() + "?",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null, // Use null for the custom icon
                        options, // Custom button labels
                        options[0] // Default selection
                );

                if (result == JOptionPane.OK_OPTION) {
                    selectMonsterFromClick(assetPosInList);
                    monsterObjectClicked = null;
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

        System.out.println("MonsterID Before: " + parentPanel.currentMonster.getId());

        this.monsterObjectSelected = this.monsterObjectClicked;

        this.monsterObjectSelected.img = scaleImage(monsterObjectSelected.img, 100,100);

        this.parentPanel.setSelectedMonster(monsterObjectSelected);

//        System.out.println(monsterObjectSelected);

        parentPanel.monster_name_label.setText(monsterObjectSelected.monster.getName());

        parentPanel.currentMonster = monsterObjectSelected.monster;

        parentPanel.updateStatDisplay(false);

        if(parentPanel.monsterRuneMap.get(monsterObjectSelected.monster.getId())==null)
            parentPanel.monsterRuneMap.put(monsterObjectSelected.monster.getId(), new HashSet<Rune>());

        this.setRunesEquipped();


    }

    private void setRunesEquipped(){
        int id = parentPanel.currentMonster.getId();
        parentPanel.resetRuneArray();

        RuneScrollPanel temp = (RuneScrollPanel) parentPanel.rune_scroll_panel;

        temp.updateDisplay();
        for(Rune r : parentPanel.monsterRuneMap.get(parentPanel.currentMonster.getId())){
            parentPanel.runesEquipped[r.getPosInt()] = r.runeId;
        }
        parentPanel.updateStatDisplay(false);


    }





}
