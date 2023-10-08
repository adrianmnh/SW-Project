package panels.subpanel;

import static tools.HelperMethods.*;

import classes.subclasses.*;
import panels.MainAppPanel;
import panels.MainFrame;
import panels.MyPanel;
import runes.Rune;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MonsterScrollPanel extends JScrollPane{

    private final JPanel monsterPanel;
    public MainAppPanel parentPanel;

    private Component outerComponent;
    private final MainFrame parentFrame;
    public MonsterImageIcon monsterObjectClicked;
    public MonsterImageIcon monsterObjectSelected;
    final int REPEAT = 1;
    int ICON_DIMENSION = 88;
    int LABEL_DIMENSION = 22;
    int BOX_DIMENSION = ICON_DIMENSION + LABEL_DIMENSION;
    final int MAX_ROWS = 2;

    private int size;

    int COLUMN_SIZE;
    int COLUMNS;

    //        public leftScrollPanel(MainFrame PF, JPanel left1, JPanel left2) throws IOException {
    public MonsterScrollPanel(MyPanel parentPanel, JPanel panel)  {

        this.monsterPanel = panel;
        this.parentPanel = (MainAppPanel) parentPanel;
        this.parentFrame = parentPanel.frame;

        this.monsterPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        this.monsterPanel.setBackground(parentFrame.baseColor);
        this.parentPanel.setBackground(parentFrame.baseColor);
        this.setBackground(parentFrame.baseColor);





        JScrollBar customScrollBar = new MacStyleScrollBar(Adjustable.HORIZONTAL);
        customScrollBar.setBackground(new Color(0x1F160B));
        this.setHorizontalScrollBar(customScrollBar);
        this.getHorizontalScrollBar().setUnitIncrement(ICON_DIMENSION);

        resizeComponent(this, ICON_DIMENSION * 5, ((ICON_DIMENSION + LABEL_DIMENSION) * MAX_ROWS) + 10 + 15);

        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        this.setBorder(BorderFactory.createEmptyBorder());
    }

    public void loadAssetsIntoPanels() {

        createMonsterImages();

        monsterPanel.addMouseListener(  new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e2) {

                try {
                    size = parentFrame.baseMonsters.size();
                    int row = e2.getY()/BOX_DIMENSION;
                    int col = e2.getX()/ICON_DIMENSION;
                    int pos = row * COLUMNS + col ;
                    int assetPosInList = pos;

                    MonsterBoxImage selected;
                    selected = (MonsterBoxImage)(monsterPanel.getComponent(pos));

                    System.out.println("[[[row:" + row + " " + "col:" +col + "]]]");
                    System.out.println(selected.monsterImageIcon.summonId + " " + selected.monsterImageIcon.alias + " " + parentPanel.monsterRuneMap.getBySummonId(selected.monsterImageIcon.summonId));

                    ImageIcon thumbs = parentFrame.uiResources.getImageIcon("thumbs.png");
                    ImageIcon img = selected.monsterImageIcon.imgResource;

                    monsterObjectClicked = selected.monsterImageIcon;

                    String title = "Watcher";
                    CenteredLabel monsterSelect1 = new CenteredLabel("Monster Selection..");
                    CenteredLabel monsterImage = new CenteredLabel(img);
                    CenteredLabel monsterSelect2 = new CenteredLabel("Select '" + monsterObjectClicked.summonId + "' ?");
//                    CenteredLabel monsterSelect2 = new CenteredLabel("Select '" + monsterObjectClicked.alias + "' ?");

                    Object[] options = {"OK", "Cancel"}; // Custom button labels

                    int result = JOptionPane.showOptionDialog(
                            parentFrame, // Use null for the parent frame
                            new Object[]{monsterSelect1, monsterImage, monsterSelect2}, // Your custom content
                            title, // Title
                            JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.PLAIN_MESSAGE,
                            null, // Use null for the custom icon
                            options, // Custom button labels
                            options[0] // Default selection
                    );

                    if (result == JOptionPane.OK_OPTION) {
                        selectMonsterFromClick(assetPosInList);

                    } else {

                    }



                } catch (Exception e) {
                    System.out.println(e.getLocalizedMessage());
                } finally {
                    monsterObjectClicked = null;

                }



            }
        });



    }
    public void createMonsterImages(){
        monsterPanel.removeAll();

        MonsterRuneMap ref = parentPanel.monsterRuneMap;

        this.size = ref.size();
//        System.out.println(parentPanel.monsterRuneMap);

        for (MonsterImageIcon monsterImageIcon : ref.getSortedByBaseID()) {
                MonsterBoxImage l = new MonsterBoxImage(monsterImageIcon, ICON_DIMENSION);
                monsterPanel.add(l);
        }

        resize();
    }
    private void selectMonsterFromClick(int assetPosInList){

        System.out.println("Click From ::::::::::::::::::Monster Scroll Panel::::::::::::::::::");
        System.out.println("MonsterID Before: " + parentPanel.currentMonster.getSummonId());

        this.monsterObjectSelected = this.monsterObjectClicked;

        this.monsterObjectSelected.imgResource = scaleImage(monsterObjectSelected.imgResource, 100,100);

        this.parentPanel.setSelectedMonster(monsterObjectSelected);

//        System.out.println("::::::::::::::::::\n::::::Testing here::::::");
//        System.out.println(monsterObjectSelected);
//        System.out.println("--------------------");
//        System.out.println(parentPanel.monsterRuneMap.getBySummonId(monsterObjectSelected.summonId));
//        System.out.println("::::::End Testing::::::\n::::::::::::::::::");

        parentPanel.monster_name_label.setText(monsterObjectSelected.monster.getName());

        parentPanel.currentMonster = monsterObjectSelected.monster;

        parentPanel.updateStatDisplay(false);

        this.setRunesEquipped();
    }

    private void setRunesEquipped(){
        parentPanel.resetRuneArrayAndEngravedRuneDisplay();
        int summonId = parentPanel.monsterSelectedSummonId;
        parentPanel.runesEngraved[0] = summonId;


        // this will set the runes equipped for the current monster on the rune_scroll_panel
        // and the parent panel
        RuneSet runeSet = parentPanel.monsterRuneMap.getBySummonId(summonId);

//        System.out.println(runeSet);
//        System.out.println(parentPanel.monsterRuneMap);

        for(Rune r : runeSet){
            parentPanel.runesEngraved[r.getPosInt()] = r.runeId;
        }

        parentPanel.rune_scroll_pane.updateDisplay();

        parentPanel.updateStatDisplay(false);

    }
    private void resize(){
        if(size <= 10) {
            COLUMNS = 5;
        } else {

            COLUMNS = size / MAX_ROWS;
            if(size%2==1)   this.COLUMNS += 1;
    //        COLUMNS = (testCount * REPEAT) / HEIGHT;
    //        if(testCount%2==1)   this.COLUMNS += 1;
        }

        COLUMN_SIZE = ICON_DIMENSION * COLUMNS;

        resizeComponent(monsterPanel, COLUMN_SIZE, (ICON_DIMENSION + LABEL_DIMENSION) * MAX_ROWS);

    }






}
