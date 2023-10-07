package panels.subpanel;

import static tools.HelperMethods.*;

import classes.Monster;
import classes.subclasses.*;
import database.MonsterDB;
import panels.AddSummonPanel;
import panels.LoginPanel;
import panels.MainFrame;
import panels.MyPanel;
import runes.Rune;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.util.ArrayList;
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

//                    JLabel message = new JLabel("             Add " + monsterObjectClicked.monster.getName() + " to MonsterBox?              ");
                    CenteredLabel message = new CenteredLabel("Add " + monsterObjectClicked.monster.getName() + " to MonsterBox?");

                    CenteredLabel message2 = new CenteredLabel(monsterObjectClicked.monster.getName() + " - BaseId: " + monsterObjectClicked.monster.getBaseId());

                    CenteredLabel message3 = new CenteredLabel("Please enter monster alias below");

                    CenteredLabel imageLabel = new CenteredLabel(img);

                    JPanel panel = new JPanel();
                    JTextField aliasField = new JTextField();
                    aliasField.setToolTipText("Please enter monster alias");
                    aliasField.setText(monsterObjectClicked.monster.getName());
                    aliasField.setHorizontalAlignment(JTextField.CENTER);

                    resizeComponent(panel, 180, 40);
                    resizeComponent(aliasField, 180, 40);
                    panel.add(aliasField);
                    panel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
                    JOptionPane optionPane = new JOptionPane();


                    ToolTipManager.sharedInstance().setInitialDelay(0);

                    Object[] customContent = {message, message2, imageLabel, message3, panel};


                    Object[] options = {"OK", "Cancel"}; // Custom button labels

                    int result = optionPane.showOptionDialog(
                            parentFrame, // Use null for the parent frame
                            customContent, // Your custom content
                            title,
                            JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.PLAIN_MESSAGE,
                            null, // Use null for the custom icon
                            options, // Custom button labels
                            options[0] // Default selection
                    );

                    parentFrame.fixContentBleed();

                    String alias = aliasField.getText();

                    System.out.println("Alias: " + alias);

                    int generatedKey = -1;

                    if(alias.equals("")){
                        JOptionPane.showMessageDialog(parentFrame, "Please enter monster alias", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (result == JOptionPane.OK_OPTION) {

//                        selectMonsterFromClick(assetPosInList);

                        String customName = alias;
                        generatedKey = addSummon(monsterObjectClicked.monster, customName);



                        processAddSummon(generatedKey, customName);

//                        MonsterRuneMap2 mapRef = parentFrame.mainApp_panel.monsterRuneMap2;
//                        MonsterScrollPanel panelRef = parentFrame.mainApp_panel.monster_scroll_pane;
//                        if(generatedKey != -1){
//                            MonsterImageIcon monsterImageIcon = monsterObjectClicked.clone();
//                            monsterImageIcon.setSummonId(generatedKey);
//                            monsterImageIcon.setAlias(customName);
//                            mapRef.put(monsterImageIcon, new RuneSet());
//                            CenteredLabel label = new CenteredLabel("Success! Summon added to Account               ");
//                            JOptionPane.showMessageDialog(parentFrame,label, "Summon created", 2, parentFrame.uiResources.getImageIcon("thumbs.png"));
//                        } else {
//                            MonsterDB monsterDB = new MonsterDB();
//                            ArrayList<Object> data = monsterDB.selectAllSimilarSummon(parentFrame.getCurrentUserID(), monsterObjectClicked.monster.getBaseId());
//                            System.out.println(data.get(0));
//                            int usedSize = data.size();
//                            if(usedSize > 0) {
//                                Object[] aliasUsed = new Object[usedSize];
//                                message3.setText("Alias already used:");
//                                aliasUsed[0] = message3;
//                                for(int i=1; i< usedSize; i++){
//                                    // remove brackets from name
//                                    String name = data.get(i).toString().replaceAll("[\\[\\]]", "");
//                                    System.out.println(name);
//                                    JLabel label = new JLabel(name);
//                                    label.setHorizontalAlignment(JLabel.CENTER);
//                                    aliasUsed[i] = label;
//                                }
//                                JOptionPane.showConfirmDialog(parentFrame, aliasUsed, "Alias already used", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
//                                return;
//                            }
//                        }
//                        panelRef.createMonsterImages();


//                        panelRef.revalidate();

                    }
                } catch (Exception e) {
                    System.out.println(e.getLocalizedMessage());
                }

                monsterObjectClicked = null;


            }
        });



    }

    private void processAddSummon(int generatedKey, String customName){
        MonsterRuneMap2 mapRef = parentFrame.mainApp_panel.monsterRuneMap2;
        MonsterScrollPanel panelRef = parentFrame.mainApp_panel.monster_scroll_pane;
        if(generatedKey != -1){
            MonsterImageIcon monsterImageIcon = monsterObjectClicked.clone();
            monsterImageIcon.setSummonId(generatedKey);
            monsterImageIcon.setAlias(customName);
            mapRef.put(monsterImageIcon, new RuneSet());
            CenteredLabel label = new CenteredLabel("Success! Summon added to Account !              ");
            label.setVerticalAlignment(JLabel.CENTER);
            // Increase font size of label
            label.setFont(label.getFont().deriveFont(14f));
            JOptionPane.showMessageDialog(parentFrame,label, "Summon created", 2, parentFrame.uiResources.getImageIcon("thumbs.png"));
        } else {
            MonsterDB monsterDB = new MonsterDB();
            ArrayList<Object> data = monsterDB.selectAllSimilarSummon(parentFrame.getCurrentUserID(), monsterObjectClicked.monster.getBaseId());
            System.out.println(data.get(0));
            int usedSize = data.size();
            if(usedSize > 0) {
                Object[] aliasUsed = new Object[usedSize];
                CenteredLabel message = new CenteredLabel("Alias already used:");
                aliasUsed[0] = message;
                for(int i=1; i< usedSize; i++){
                    // remove brackets from name
                    String name = data.get(i).toString().replaceAll("[\\[\\]]", "");
                    System.out.println(name);
                    JLabel label = new JLabel(name);
                    label.setHorizontalAlignment(JLabel.CENTER);
                    aliasUsed[i] = label;
                }
                JOptionPane.showConfirmDialog(parentFrame, aliasUsed, "Alias already used", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
                return;
            }
        }
        panelRef.createMonsterImages();
    }
    private int addSummon(Monster monster, String name){
        MonsterDB monsterDB = new MonsterDB();
        ArrayList<Object> data = monsterDB.addUserSummon(parentFrame.getCurrentUserID(), monsterObjectClicked.monster, name);
        System.out.println("Data: " + data);
        return (int)data.get(0);
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
    private void createMonsterImage(int generatedKey){

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
