package panels.subpanel;

import static tools.HelperMethods.*;

import classes.subclasses.MacStyleScrollBar;
import classes.subclasses.MonsterImageIcon;
import database.MonsterDB;
import panels.AddSummonPanel;
import panels.MainFrame;
import panels.MyPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.util.Arrays;

public class BaseMonsterScrollPanel extends JScrollPane{

    private final JPanel monsterPanel;
    public AddSummonPanel parentPanel;

    private Component outerComponent;
    private final MainFrame parentFrame;
    public MonsterImageIcon monsterObjectClicked;
    public MonsterImageIcon monsterObjectSelected;
    final int REPEAT = 1;
    int ICON_DIMENSION = 80;
    final int COLUMNS = 8;
    final int VIEW_ROWS = 3;

    final int HEIGHT = ICON_DIMENSION * VIEW_ROWS;

    int ROW_SIZE;
    int ROWS;

    //        public leftScrollPanel(MainFrame PF, JPanel left1, JPanel left2) throws IOException {
    public BaseMonsterScrollPanel(MyPanel parentPanel, JPanel panel)  {

        this.monsterPanel = panel;
        this.parentPanel = (AddSummonPanel) parentPanel;
        this.parentFrame = parentPanel.frame;

        JScrollBar customScrollBar = new MacStyleScrollBar(Adjustable.VERTICAL);
        customScrollBar.setBackground(parentFrame.baseColor);
        this.setVerticalScrollBar(customScrollBar);
        this.getVerticalScrollBar().setUnitIncrement( ICON_DIMENSION / 2 );

        resizeComponent(this, (ICON_DIMENSION*COLUMNS) + 10 + 15, (HEIGHT) + 0);

        loadAssetsIntoPanels();

    }

    public void loadAssetsIntoPanels() {

        int size = this.parentFrame.baseMonsters.size();
        System.out.println("Numbers of monsters: " + size);

//        System.out.println(Arrays.toString(this.parentFrame.baseMonsters.baseID_toIndex));

        for (MonsterImageIcon monsterImageIcon : this.parentFrame.baseMonsters) {
//            System.out.println(monsterImageIcon);
            ImageIcon resized = scaleImage(monsterImageIcon.imgResource, ICON_DIMENSION-2, ICON_DIMENSION-2);
            JLabel l = new JLabel(resized);
            l.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 1));

            l.setFocusable(false);

            monsterPanel.add(l);
        }
        ROWS = (size) / COLUMNS;
        if(size%COLUMNS>0)   this.ROWS += 1;

        System.out.println("size" + size + " " + "COLUMNS:" + COLUMNS);


        ROW_SIZE = ICON_DIMENSION * COLUMNS;

        resizeComponent(monsterPanel, ROW_SIZE , ICON_DIMENSION * ROWS);


        monsterPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));



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
                    System.out.println("\n\nrow:" + row + " " + "col:" +col + " " + parentFrame.baseMonsters.get(assetPosInList));



                    ImageIcon img = parentFrame.baseMonsters.get(assetPosInList).imgResource;

                    monsterObjectClicked = parentFrame.baseMonsters.get(assetPosInList);

                    String title = "Add " + monsterObjectClicked.monster.getName() + " to MonsterBox?";

                    JLabel message = new JLabel("              TitleTitleTitleTitleTitleTitleTitleTitleTitleTitle\nTitle              ");
                    message.setHorizontalAlignment(JLabel.CENTER);

                    JLabel message2 = new JLabel(monsterObjectClicked.monster.getName() + " - BaseId: " + monsterObjectClicked.monster.getBaseId());

                    JLabel imageLabel = new JLabel(img);
//                    resizeComponent(imageLabel, ICON_DIMENSION*2, ICON_DIMENSION*2);


                    Object[] options = {"OK", "Cancel"}; // Custom button labels
                    int result = JOptionPane.showOptionDialog(
                            parentFrame, // Use null for the parent frame
                            new Object[]{message, message2, imageLabel}, // Your custom content
                            title,
                            JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.PLAIN_MESSAGE,
                            null, // Use null for the custom icon
                            options, // Custom button labels
                            options[0] // Default selection
                    );

                    parentFrame.fixContentBleed();


                    if (result == JOptionPane.OK_OPTION) {
                        System.out.println(parentFrame.baseMonsters.get(assetPosInList));
                        selectMonsterFromClick(assetPosInList);
                        monsterObjectClicked = null;

                        doSomething();
                    }
                } catch (Exception e) {
                    System.out.println(e.getLocalizedMessage());
                }



            }
        });



    }

    private void doSomething(){

        //statement execute(sql) returns SQL Warning
        //int = statement executeUpdate(sql, RETURN_GE..) -> rowsUpdated
        // statement executeUpdate(sql, RETURN_GENERATED_KEYS),  ResultSet generated = statement.getGeneratedKeys().next(), generated.getInt(1)

        int rowsUpdated = -1;
        try {

        MonsterDB monsterDB = new MonsterDB();

//        monsterDB.getStatement().execute("PRINT('HELLO WORLD')");

        ResultSet resultSet = monsterDB.getStatement().executeQuery("SELECT Name FROM GameTool.Summon WHERE AccountId = 1 AND MonsterId = 104;");

        while (resultSet.next()) {
            System.out.println(resultSet.getString(1));
        }

        monsterDB.getStatement().close();
        monsterDB.closeConnection();



//        monsterDB.execQuery("insert into gametool.Summon VALUES (1, 104, 'Bellenus');");
//        rowsUpdated =  monsterDB.getStatement().executeUpdate("insert into gametool.Summon VALUES (1, 104, 'Bellenus');", Statement.RETURN_GENERATED_KEYS);


//        monsterDB.addMonsterToMonsterBox(monsterObjectClicked.monster.getId(), parentPanel.
//        currentMonsterBox.getId());

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        System.out.println("Rows updated: " + rowsUpdated);

    }
    private void createMonsterImage(int assetPosInList){

//        this.selectedMonster = parentFrame.monsterResources.get(assetPosInList);
//        this.selectedMonster.monster = parentPanel.monsterBox.get(assetPosInList);
//        this.selectedMonster.img = scaleImage(selectedMonster.img, 90,90);
    }
    private void selectMonsterFromClick(int assetPosInList){

//        System.out.println("MonsterID Before: " + parentPanel.currentMonster.getId());
//
//        this.monsterObjectSelected = this.monsterObjectClicked;
//
//        this.monsterObjectSelected.img = scaleImage(monsterObjectSelected.img, 100,100);
//
//        this.parentPanel.setSelectedMonster(monsterObjectSelected);
//
////        System.out.println(monsterObjectSelected);
//
//        parentPanel.monster_name_label.setText(monsterObjectSelected.monster.getName());
//
//        parentPanel.currentMonster = monsterObjectSelected.monster;
//
//        parentPanel.updateStatDisplay(false);
//
//        if(parentPanel.monsterRuneMap.get(monsterObjectSelected.monster.getId())==null)
//            parentPanel.monsterRuneMap.put(monsterObjectSelected.monster.getId(), new HashSet<Rune>());
//
//        this.setRunesEquipped();


    }

    private void setRunesEquipped(){
//        int id = parentPanel.currentMonster.getId();
//        parentPanel.resetRuneArray();
//
//        RuneScrollPanel temp = (RuneScrollPanel) parentPanel.rune_scroll_panel;
//
//        temp.updateDisplay();
//        for(Rune r : parentPanel.monsterRuneMap.get(parentPanel.currentMonster.getId())){
//            parentPanel.runesEquipped[r.getPosInt()] = r.runeId;
//        }
//        parentPanel.updateStatDisplay(false);


    }





}
