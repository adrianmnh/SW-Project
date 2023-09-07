package panels;

import classes.Monster;
import classes.MonsterDB;
import classes.RuneDB;
import classes.subclasses.MonsterImageIcon;
import runes.Rune;
import runes.SubStat;
import subpanel.MonsterScrollPanel;
import subpanel.RuneScrollPanel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MainAppPanel extends MyPanel {

    private JPanel mainPanel;
    private JPanel monsterPanel;
    private JLabel pos1;
    private JLabel pos2;
    private JLabel pos3;
    private JLabel pos4;
    private JLabel pos5;
    private JLabel pos6;
    private JTable OLDrune_table;
    private JPanel left_panel;
    private JButton load_runes_button;
    private JButton add_new_rune_button;
    private JComboBox p3;
    private JComboBox p2;
    private JComboBox p1;
    public JLabel selected_monster_label;
    private JButton equipRuneButton;
    private JButton removeAllButton;
    private JButton findBestSetButton;
    private JComboBox s1;
    private JLabel left_panel_label;
    public JScrollPane OLDrune_scroll_panel;
    private JButton selectMonsterButton;
    private JPanel after_load_panel;
    public JTable OLDmonster_table;
    public JPanel bottom_panel;
    private JButton load_monsters_button;
    private JScrollPane OLDmonster_scroll_panel;
    private JPanel OLDmonster_panel;
    private JPanel OLDrune_panel;
    public JLabel monster_name_label;
    private JTextPane current_stats_label;
    private JScrollPane monster_scroll_pane;
    private JPanel monster_pane;
    private JPanel center_panel;
    private JScrollPane rune_scroll_panel;
    private JPanel rune_pane;
    private JPanel rune_left;
    private JPanel rune_right;
    private JPanel stat_opt_pane;
    private JPanel OLD;
    private ImageIcon runeoff = getImg("ui/runeoff.png");
    private ImageIcon left_panel_cover_img = getImg("ui/ifrit.png");
    private ImageIcon thumbs = getImg("ui/thumbs.png");
    private ImageIcon runeon = getImg("ui/runeon.png");

    private int currentRune = 0;


    private ImageIcon getImg(String s){
        return new ImageIcon(getClass().getClassLoader().getResource(s));
    }
    private ImageIcon getMonsterImage(String s){
        return new ImageIcon(getClass().getClassLoader().getResource("monsters/"+s+".jpg"));
    }

    public ArrayList<Integer> indexOfRunePositions = new ArrayList();

    public ArrayList<Rune> offlineRuneBag;
    private void startSetup(){
        frame.setSizeTo(0,0);

        frame.setCurrentUserID(1);
//        parentFrame.setCurrentUserID(10);

        setImagesNText("", runeoff);
        after_load_panel.setVisible(false);

        add_new_rune_button.setVisible(false);
//        OLDrune_scroll_panel.setVisible(false);

        fetchMonsterDataFromDB();

        createMonsterTable(createMonsterImages());

        // padding for monster selected image
        int padding = 10;
        Border paddingBorder = BorderFactory.createEmptyBorder(padding, padding, padding, padding);
        selected_monster_label.setBorder(paddingBorder);

        current_stats_label.setFocusable(false);
        current_stats_label.setBackground(new Color(242,242,242));
        OLDrune_scroll_panel.getVerticalScrollBar().setUnitIncrement(12);
        OLDmonster_scroll_panel.getHorizontalScrollBar().setUnitIncrement(8);
        OLDrune_table.setBackground(new Color(242,242,242));
        selectMonsterButton.setVisible(false);
    }
    public MainAppPanel(MainFrame f){
        super(f);
        System.out.println("Starting Main App panel...");
//        setParent(f);

        startSetup();
        createRuneTable();

        OLDmonster_table.setFocusable(false);
        OLDmonster_table.setSelectionMode(0);


        load_runes_button.addActionListener(e -> {

//            for (int i = 0; i < parentFrame.monsterResources.size(); i++) {
//                System.out.println(parentFrame.monsterResources.get(i).monster);
//            }

            addAllUserRunes(frame.getCurrentUserID());
            load_runes_button.setVisible(false);
            after_load_panel.setVisible(true);
            add_new_rune_button.setVisible(true);
//            System.out.println(parentFrame.monsterAssetFiles);
//                OLDrune_scroll_panel.setVisible(true);
//                System.out.println(offlineRuneBag);
//                System.out.println(String.format("%s%s%s%s%s%s",runesEquipped[0],runesEquipped[1]
//                        ,runesEquipped[2],runesEquipped[3],runesEquipped[4],runesEquipped[5] ));
            selectMonsterButton.setVisible(true);

            RuneScrollPanel temp = (RuneScrollPanel) rune_scroll_panel;

            temp.loadAssetsIntoPanels();
            frame.reframe();
        });
        add_new_rune_button.addActionListener( e-> {
                frame.changePanel_NewRune();
                frame.reframe();

        });
//        selectMonsterButton.addActionListener(e -> {
//
//            if(monsterFocusedString.equals(""))
//                JOptionPane.showMessageDialog(parentFrame, "Please select a monster first", "Monster Selected", JOptionPane.OK_OPTION);
//            else{
//
//                JOptionPane.showMessageDialog(parentFrame, "Selected " + monsterFocusedString.toUpperCase(), "Monster Selected", JOptionPane.OK_OPTION, thumbs);
//                monsterPanelLabel.setIcon(thumbs);
//                System.out.println(monsterList.get(monsterSelected));
//                currentMonster = monsterBox.get(monsterSelected);
//                monster_name_label.setText(monsterFocusedString);
//            }
//        });
        findBestSetButton.addActionListener(e -> {

            if(currentMonster.getID()==0){
                JOptionPane.showMessageDialog(frame, "Please select a monster", "Not enough data", 2, thumbs);
            }else{
                if(p1.getSelectedIndex()>0 && p2.getSelectedIndex()>0
                        && p3.getSelectedIndex()>0) {
                    optimize();
                    for(int i = 0; i<6;i++){
                        System.out.print(runesEquipped[i]+" ");
                    }
                }

                else JOptionPane.showMessageDialog(frame, "Please select all attribute priorities", "Not enough data", 2, thumbs);
            }


        });
        equipRuneButton.addActionListener(e -> {

            if(currentRunePosition>0){
                if(currentRunePosition==1)pos1.setIcon(runeon);
                if(currentRunePosition==2)pos2.setIcon(runeon);
                if(currentRunePosition==3)pos3.setIcon(runeon);
                if(currentRunePosition==4)pos4.setIcon(runeon);
                if(currentRunePosition==5)pos5.setIcon(runeon);
                if(currentRunePosition==6)pos6.setIcon(runeon);

                currentRunePosition--;


                if (runesEquipped[currentRunePosition] != -1) {
                    int c = runesEquipped[currentRunePosition];
                    offlineRuneBag.get(c).setIsEquipped(false);
                    OLDrune_table.setValueAt(runeon,runesEquipped[currentRunePosition],0);
                }
                offlineRuneBag.get(currentRune).setIsEquipped(true);
                OLDrune_table.setValueAt(runeoff,currentRune,0);
                runesEquipped[currentRunePosition] = currentRune;
                setMonsterLabelText(false);
            }


        });

        removeAllButton.addActionListener(e -> {
            unselectAllRunes();
            for(int y = 0; y< OLDrune_table.getRowCount(); y++){
                OLDrune_table.setValueAt(runeon, y, 0);
            }
        });
        OLDrune_table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JTable t = (JTable)e.getSource();
                int r = t.getSelectedRow();
                resetRuneImage(r);
                setRuneFocused(r);
                currentRunePosition = indexOfRunePositions.get(r);
                currentRune = r;

            }
        });
        OLDmonster_table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                OLDmonster_table.setSurrendersFocusOnKeystroke(false);

                super.mousePressed(e);
                System.out.println(e.getClickCount());
                if(e.getClickCount() == 2){
                    if(monsterFocusedString.equals(""))
                        JOptionPane.showMessageDialog(frame, "Please select a monster first", "Monster Selected", JOptionPane.OK_OPTION);
                    else{
                        JOptionPane.showMessageDialog(frame, "Selected " + monsterFocusedString.toUpperCase(), "Monster Selected", JOptionPane.OK_OPTION, thumbs);
                        selected_monster_label.setIcon(thumbs);
                        System.out.println(monsterStringDataArray.get(monsterSelectedID));
                        currentMonster = monsterBox.get(monsterSelectedID);
                        monster_name_label.setText(monsterFocusedString);
                        setMonsterLabelText(false);
//                        JTable target = (JTable)e.getSource();
//                        int row = target.getSelectedRow();
//                        int column = target.getSelectedColumn();
//                        resetMonsterImg(row, column);

                    }

                }

            }
        });
        OLDmonster_table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                OLDmonster_table.setSelectionBackground(Color.BLACK);
                OLDmonster_table.setSelectionForeground(Color.BLACK);
                JTable target = (JTable)e.getSource();
                int row = target.getSelectedRow();
                int column = target.getSelectedColumn();
                resetMonsterImg(row, column);
                setMonsterSelected(row*8+column);
                monsterSelectedID = row*8 + column;
                System.out.println("Reseting");
            }
        });
    }


    private String createImages(int a){
        String s="";
        switch(a){
            case 0: s = "Artamiel"; break;
            case 1: s = "Barbara"; break;
            case 2: s = "Bastet"; break;
            case 3: s = "Bellenus"; break;
            case 4: s = "Chiwu"; break;
            case 5: s = "Ethna"; break;
//            case 6: s = "Kahli"; break;
            case 6: s = "Laika"; break;
            case 7: s = "Monkey"; break;
            case 8: s = "Oberon"; break;
            case 9: s = "Perna"; break;
            case 10: s = "Ryu"; break;
            case 11: s = "Seara"; break;
            case 12: s = "Susano"; break;
            case 13: s = "Sylvia"; break;
            case 14: s = "Theomars"; break;
            case 15: s = "Tiana"; break;
        }
        return s;
    }
    private void resetMonsterImg(int r, int c){
        String s = createImages(r*8+c);
        System.out.println(s);

        ImageIcon l = getMonsterImage(s);
        OLDmonster_table.setValueAt(l,r,c);
        monsterFocusedString = s;

    }

    private ImageIcon[][] createMonsterImages(){
        ImageIcon[][] d = new ImageIcon[2][8];
        for(int i = 0; i< 8; i++){
            d[0][i] = getMonsterImage(createImages(i));

        }
        for(int i = 0; i< 8; i++){
            d[1][i] = getMonsterImage(createImages(8+i));
        }

        return d;

    }

    private void setImagesNText(String s, ImageIcon i){
        pos1.setIcon(i);
        pos2.setIcon(i);
        pos3.setIcon(i);
        pos4.setIcon(i);
        pos5.setIcon(i);
        pos6.setIcon(i);
        pos1.setText(s);
        pos2.setText(s);
        pos3.setText(s);
        pos4.setText(s);
        pos5.setText(s);
        pos6.setText(s);
        left_panel_label.setIcon(left_panel_cover_img);
        left_panel_label.setText("");
        monster_name_label.setText("");
        selected_monster_label.setText("");
    }

    private void createRuneTable(){

        String[] columnNames = {"Picture", "Description"};

        DefaultTableModel model = new DefaultTableModel(null, columnNames) {
            //  Returning the Class of each column will allow different
            //  renderers to be used based on Class
            public Class getColumnClass(int column)
            {
                return getValueAt(0, column).getClass();
            }
        };



        OLDrune_table.setModel(model);

        OLDrune_table.setRowHeight(120);

        OLDrune_table.setDefaultRenderer(String.class, new MultiLineCellRenderer());

        OLDrune_table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );

    }

    private void createMonsterTable(Object[][] data){
        ImageIcon a=new ImageIcon();
        String[] cols = {"Ne","Ne","Na","Ne","Ne","Ne","Na","Na"};
        DefaultTableModel model = new DefaultTableModel(data, cols){
            //  Returning the Class of each column will allow different
            //  renderers to be used based on Class
            public Class getColumnClass(int column)
            {
                //return a.getClass();
                return getValueAt(0, column).getClass();
            }
        };

        OLDmonster_table.setRowHeight(60);

        OLDmonster_table.setModel(model);

        OLDmonster_table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
    }

    private void addAllUserRunes(int userid){
        System.out.printf("**********************\nUserID : %s\n**********************\n", userid);
        RuneDB connct = new RuneDB();
        System.out.println("Getting user runes");
        offlineRuneBag = connct.getUserRunes(userid);
//        System.out.println(offlineRuneBag);
        for(Rune r: offlineRuneBag){
//            System.out.println(r);
            addRowToTable(r);
        }
        connct.closeConnection();
    }

    public JPanel getMain() {
        return mainPanel;
    }

    public void addRowToTable(Rune r){
        DefaultTableModel row = (DefaultTableModel) OLDrune_table.getModel();
        row.addRow(new Object[]{runeon, r.toStringGUI()});
        indexOfRunePositions.add(r.getPosInt());

        //System.out.println("intList size: " + intlist.size());


    }

    private void resetRuneImage(int r){
        OLDrune_table.setValueAt(runeon, r, 0);
    }

    private void optimize(){

        String p1s = subStatFormat(p1);
        String p2s = subStatFormat(p2);
        String p3s = subStatFormat(p3);
        String set = subStatFormat(s1);
        int i;
        i = checkPos(1, p1s,p2s, p3s, set);
        equipRune(i,0);

        i = checkPos(2, p1s,p2s, p3s, set);
        equipRune(i,1);

        i = checkPos(3, p1s,p2s, p3s,set);
        equipRune(i,2);//////////////////problem here

        i = checkPos(4, p1s,p2s, p3s,set);
        equipRune(i,3);

        i = checkPos(5, p1s,p2s, p3s,set);
        equipRune(i,4);

        i = checkPos(6, p1s,p2s, p3s,set);
        equipRune(i,5);

        setMonsterLabelText(false);

        ArrayList<Integer> o = new ArrayList();
        for(int y = 0; y<6; y++)if(runesEquipped[y]!=-1)o.add(runesEquipped[y]);
        ArrayList<String> st = new ArrayList();
        for(int y=0; y<o.size();y++)st.add(offlineRuneBag.get(o.get(y)).toStringGUI());

        ArrayList<Object> display = new ArrayList<>();
        for(int y = 0; y<st.size(); y++){
            display.add(String.format("TableRow: %s\n%s\n",o.get(y)+1,st.get(y)));
        }

        for(int y = 0; y<display.size();y++)
            JOptionPane.showMessageDialog(frame, display.get(y).toString(),"Runes Equipped", 2, thumbs);

        removeRuneImages();


    }

    private void removeRuneImages(){
        for(int y=0; y<6; y++){
            if(runesEquipped[y]>-1){
                OLDrune_table.setValueAt(runeoff, runesEquipped[y], 0);
            }
        }
    }
    private int checkPos(int position, String a, String b, String c, String set){

        System.out.printf("\n\nChecking for: %s %s %s %s\n",a, b, c, set);


        ArrayList<Rune> tmp = new ArrayList<>();
        ArrayList<Rune> picks1 = new ArrayList<>();
        ArrayList<Rune> picks2 = new ArrayList<>();

        for(Rune r: offlineRuneBag){
            if(r.getPosInt() == position){
                //System.out.println(r.getPosInt() + " -> " + i);
                tmp.add(r);
                //System.out.printf("Pos %s added %s\n", i, r);
            }
        }

        for(Rune r: tmp){
            if(position == 1 || position == 3 || position == 5){
                if(r.hasSubStats(a,b,c)){
                    picks1.add(r);

                }else if(r.hasSubStats(a,b) || r.hasSubStats(a,c)){
                    picks2.add(r);

                }else if(r.hasSubStat(a) || r.hasSubStat(b) || r.hasSubStat(c)){
                    picks2.add(r);

                }
            }
            else if(position == 2 || position == 4 || position == 6){     /*          POS 2              */
                if((r.hasMainStat(a) && r.hasSubStats(b,c)) || (r.hasMainStat(b)) && r.hasSubStats(a,c)
                                    || (r.hasMainStat(c)&&r.hasSubStats(a,b))){
                    picks1.add(r);
                }
                else if(r.hasSubStats(a,b,c)){
                    picks1.add(r);
                }
                else if(r.hasSubStats(a,b) || r.hasSubStats(b,c) || r.hasSubStats(a,c)){
                    picks2.add(r);
                }



            }
//            else if(position == 4){     /*          POS 4              */
//
//
//
//            }
//            else if(position == 6){     /*          POS 6              */
//
//
//
//            }

        }
        ArrayList<Integer> values = new ArrayList<>();

//        System.out.println("Picks1: "+picks1);
//        System.out.println("Picks2: "+picks2);
        int i;

        for(Rune r : picks1){
            i = getSubsSum(r);
            values.add(i);
//            System.out.println("Rune substats value : " + i);
        }

        for(Rune r : picks2){
            i = getSubsSum(r);
            values.add(i);
            //System.out.println("Rune substats value : " + i);
        }
//        System.out.printf("Size of Picks1: %s   ---  Size of Picks2: %s\n", picks1.size(), picks2.size());
//
//        System.out.println("All substat values in: "+ values);
//        System.out.println("Highest: "+getHighestValue(values));

        if(picks1.size()==0 && picks2.size()==0){
            for(Rune r : tmp){
                i = getSubsSum(r);
                values.add(i);
            }
            for(int y=0; y<values.size();y++){
                if(values.get(y) == getHighestValue(values)){
                    picks1.add(tmp.get(y));
                }
            }
        }

        tmp = new ArrayList<Rune>(); // final runes to use for algorithm
        for(int y = 0; y<values.size(); y++){
            if(values.get(y) == getHighestValue(values)){
                if(y<picks1.size()){
                    tmp.add(picks1.get(y));
                }if(y>=picks1.size()){
                    tmp.add(picks2.get(y-picks1.size()));
                }
            }
        }

        //System.out.println("To return: " + tmp);
        Rune toReturn = null;
        for(Rune r:tmp){
            if(r.getSetString().equals(set)){
                System.out.println("Final check 1: " + r);
                toReturn = r;
            }
        }
        System.out.println("Returned Index1: " + offlineRuneBag.indexOf(toReturn));
        if(offlineRuneBag.indexOf(toReturn)!=-1) return offlineRuneBag.indexOf(toReturn);

        //System.out.println("Final check 2: " + tmp.get(0));
        if(!tmp.isEmpty()) {
            toReturn = tmp.get(0);
            System.out.println("Returned Index2: " + offlineRuneBag.indexOf(toReturn));
            return offlineRuneBag.indexOf(toReturn);
        }
        return -1;


    }
    private int getSubsSum(Rune r){
        int i = 0;
        for(SubStat sub : r.getSubStats()){
            if(sub.getSubStat().equals("atk")) {
                i += sub.getSubValueInt() / 8.5;
            }else if(sub.getSubStat().equals("hp")){
                i += sub.getSubValueInt()/100;
            }else if (sub.getSubStat().equals("def")){
                i += sub.getSubValueInt()/3;
            }else{
                i += sub.getSubValueInt();
            }
        }
        return i;

    };
    private int getHighestValue(ArrayList<Integer> values){
        int highest=0;
        for(Integer i:values){
            if(i>highest) highest = i;
        }
        return highest;
    }
    private void equipRuneImage(int i, boolean b){
        if(b){
            if(i==1)pos1.setIcon(runeon);
            if(i==2)pos2.setIcon(runeon);
            if(i==3)pos3.setIcon(runeon);
            if(i==4)pos4.setIcon(runeon);
            if(i==5)pos5.setIcon(runeon);
            if(i==6)pos6.setIcon(runeon);
        }
        else{
            if(i==1)pos1.setIcon(runeoff);
            if(i==2)pos2.setIcon(runeoff);
            if(i==3)pos3.setIcon(runeoff);
            if(i==4)pos4.setIcon(runeoff);
            if(i==5)pos5.setIcon(runeoff);
            if(i==6)pos6.setIcon(runeoff);
        }

    }
    private void equipRune(int runeID, int runePosition){

        if(runesEquipped[runePosition]!=-1){
            int temp = runesEquipped[runePosition];
            offlineRuneBag.get(temp).setIsEquipped(false);
        }
        if(runeID!=-1){
            offlineRuneBag.get(runeID).setIsEquipped(true);
            runesEquipped[runePosition] = runeID;
            equipRuneImage(runePosition+1, true);
        }
    }
    private String subStatFormat(JComboBox j){
        String s = j.getSelectedItem().toString().replace("%","");
        if(s.equals("CRte")||s.equals("CDmg")||s.equals("RES")||s.equals("ACC")||s.equals("SPD"))
            s = s.toLowerCase();
        return s;
    }
    public int monsterSelectedID;

    public ArrayList<String> monsterStringDataArray;
    public ArrayList<Monster> monsterBox; // want to not use this
    public MonsterImageIcon selectedMonster;
    public Monster currentMonster = new Monster();

    private void fetchMonsterDataFromDB(){
        MonsterDB connect = new MonsterDB();
        this.monsterStringDataArray = connect.getAllMonsters();
        connect.closeConnection();
        createMonsters(monsterStringDataArray);
    }
    private void createMonsters(ArrayList<String> dataArr){
        System.out.println("Creating monsters\n\n***********************");
        monsterBox = new ArrayList<>(); // want to not use this
//        for(String s : this.monsterDataArr){
//            monsterBox.add(new Monster(s));
//        }
        for (int i = 0; i < dataArr.size(); i++) {
            monsterBox.add(new Monster(dataArr.get(i))); // want to not use this
            frame.monsterResources.get(i).monster = monsterBox.get(i);
            frame.monsterResources.get(i).index = i;
//            System.out.println(parentFrame.monsterResources.get(i).name + " " + monsterBox.get(i).getName());
        }
    }
    public void setSelectedMonster(MonsterImageIcon monster){
        this.selectedMonster = monster;
        this.monsterSelectedID = monster.index;
        this.selected_monster_label.setIcon(monster.img);

    }
    public void setSelectedMonsterImage(ImageIcon imageIcon){
//        selected_monster_label.setIcon(imageIcon);
    }
    private int monsterFocused;
    public void setMonsterSelected(int i){
//        this.monsterSelectedID = i;
    }
//    private int getMonsterFocused(){return this.monsterFocused;}
    private String monsterFocusedString = "";
    private void unselectAllRunes(){
        pos1.setIcon(runeoff);
        pos2.setIcon(runeoff);
        pos3.setIcon(runeoff);
        pos4.setIcon(runeoff);
        pos5.setIcon(runeoff);
        pos6.setIcon(runeoff);
        for(int i=0; i<6;i++)runesEquipped[i]=-1;
//        equipRuneButton.doClick();
        currentRunePosition = -1;
        setMonsterLabelText(false);

    }
    public void setMonsterLabelText(boolean booleanVariable){

        if(currentMonster.getID()-1 != -1){

            String s;

            Monster mon = new Monster();
            s = "";

            int ragecount = 0;
            int bladecount = 0;
            int swiftcount = 0;

            ArrayList<Rune> temp = new ArrayList<>();
            for(int i = 0; i<6; i++) if(runesEquipped[i]!=-1)temp.add(offlineRuneBag.get(runesEquipped[i]));
            for(Rune r:temp){
                // Adding main stat
                if(r.isEquipped) {
                    s = r.getMainStat().getMainStatAttribute();
                    //System.out.println(s +  " + " + r.getMainStat().getValue());
                    switch(s){
                        case "HP": mon.setHP((currentMonster.getHP()*r.getMainStat().getValue()/100)+mon.getHP()); break;
                        case "ATK": mon.setATK((currentMonster.getATK()*r.getMainStat().getValue()/100)+mon.getATK()); break;
                        case "DEF": mon.setDEF((currentMonster.getDEF()*r.getMainStat().getValue()/100)+mon.getDEF()); break;
                        case "spd": mon.setSPD(mon.getSPD() + r.getMainStat().getValue()); break;
                        case "crte": mon.setCR(mon.getCR() + r.getMainStat().getValue()); break;
                        case "cdmg": mon.setCD(mon.getCD() + r.getMainStat().getValue()); break;
                        case "res": mon.setRES(mon.getRES() + r.getMainStat().getValue()); break;
                        case "acc": mon.setACC(mon.getACC() + r.getMainStat().getValue()); break;
                        case "hp": mon.setHP(mon.getHP() + r.getMainStat().getValue()); break;
                        case "atk": mon.setATK(mon.getATK() + r.getMainStat().getValue()); break;
                        case "def": mon.setDEF(mon.getDEF() + r.getMainStat().getValue()); break;
                    }
                    if(r.getSetString().equals("Rage")) ragecount++;
                    if(r.getSetString().equals("Blade")) bladecount++;
                    if(r.getSetString().equals("Swift")) swiftcount++;

                    for(SubStat sub : r.getSubStats()){
                        s = sub.getSubStat();
                        //System.out.println(s + " + " + sub.getSubValueInt());

                        switch(s){
                            case "HP": mon.setHP((currentMonster.getHP()*sub.getSubValueInt()/100)+mon.getHP()); break;
                            case "ATK": mon.setATK((currentMonster.getATK()*sub.getSubValueInt()/100)+mon.getATK()); break;
                            case "DEF": mon.setDEF((currentMonster.getDEF()*sub.getSubValueInt()/100)+mon.getDEF()); break;
                            case "spd": mon.setSPD(mon.getSPD() + sub.getSubValueInt()); break;
                            case "crte": mon.setCR(mon.getCR() + sub.getSubValueInt()); break;
                            case "cdmg": mon.setCD(mon.getCD() + sub.getSubValueInt()); break;
                            case "res": mon.setRES(mon.getRES() + sub.getSubValueInt()); break;
                            case "acc": mon.setACC(mon.getACC() + sub.getSubValueInt()); break;
                            case "hp": mon.setHP(mon.getHP() + sub.getSubValueInt()); break;
                            case "def": mon.setDEF(mon.getDEF() + sub.getSubValueInt()); break;
                            case "atk": mon.setATK(mon.getATK() + sub.getSubValueInt()); break;
                        }
                    }

                }
            }
            if(ragecount>=4) {mon.setCD(mon.getCD()+40);}
            if(swiftcount>=4) {mon.setSPD((currentMonster.getSPD()*25/100) + mon.getSPD());}
            if(bladecount>=2){
                int a = bladecount/2;
                mon.setCR(mon.getCR()+12*a);
            }

//            System.out.println("CURRENT\n"+currentMonster);

            String plus = " + ";
            StringBuilder sb = new StringBuilder();
            if(!booleanVariable){
                plus = " + ";

                sb.append(String.format("HP  : %s %s %s\n",currentMonster.getHP(),      plus, mon.getHP() ));
                sb.append(String.format("ATK  :  %s %s %s\n",currentMonster.getATK(),   plus, mon.getATK() ));
                sb.append(String.format("DEF  : %s %s %s\n",currentMonster.getDEF(),    plus, mon.getDEF() ));
                sb.append(String.format("SPD  : %s %s %s\n",currentMonster.getSPD(),    plus, mon.getSPD() ));
                sb.append(String.format("CRte : %s %s %s\n",currentMonster.getCR(),     plus, mon.getCR() ));
                sb.append(String.format("CDmg: %s %s %s\n",currentMonster.getCD(),      plus, mon.getCD() ));
                sb.append(String.format("RES   : %s %s %s\n",currentMonster.getRES(),   plus, mon.getRES() ));
                sb.append(String.format("ACC   : %s %s %s\n",currentMonster.getACC(),   plus, mon.getACC() ));

            }
            current_stats_label.setText(sb.toString());
        }

    }

    private int runeFocused;
    private void setRuneFocused(int i){
        this.runeFocused = i;
    }
    private int getRuneFocused(){
        return runeFocused;
    }
    public JButton getLoadRunes(){
       return this.load_runes_button;
    }

    private void setParent(MainFrame f) {
        this.frame = f;
    }

    public MainFrame getFrame(){
        return this.frame;
    }

    private void testScript1(){
        p1.setSelectedIndex(1);
        p2.setSelectedIndex(2);
        p3.setSelectedIndex(3);
        monsterSelectedID = 10;
        selectMonsterButton.doClick();
    }
    private int currentRunePosition;

    private int[] runesEquipped = new int[] {-1,-1,-1,-1,-1,-1};


    private void createUIComponents() {
        // TODO: place custom component creation code here
//        System.out.println(this.parentFrame.localAssetList);
        monster_pane = new JPanel();
        monster_scroll_pane = new MonsterScrollPanel(this, monster_pane);

        rune_left = new JPanel();
        rune_right = new JPanel();
        rune_pane = new JPanel();
        rune_scroll_panel = new RuneScrollPanel(this, rune_pane, rune_left, rune_right);

    }
}














