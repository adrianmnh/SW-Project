package panels;

import classes.Monster;
import classes.subclasses.MonsterRuneMap;
import classes.subclasses.RoundedBorder;
import classes.subclasses.RuneBag;
import database.MonsterDB;
import database.RuneDB;
import classes.subclasses.MonsterImageIcon;
import panels.subpanel.BaseMonsterScrollPanel;
import runes.Rune;
import runes.SubStat;
import panels.subpanel.MonsterScrollPanel;
import panels.subpanel.RuneScrollPanel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static java.lang.Thread.sleep;
import static tools.HelperMethods.resizeComponent;

public class MainAppPanel extends MyPanel {

    private JPanel mainPanel;
    public JLabel pos1;
    public JLabel pos2;
    public JLabel pos3;
    public JLabel pos4;
    public JLabel pos5;
    public JLabel pos6;
    private JPanel left_panel;
    private JButton load_runes_button;
    private JButton add_new_rune_button;
    private JComboBox statPriority1;
    private JComboBox statPrioritry2;
    private JComboBox statPriority3;
    private JButton runeMap;
    private JButton removeAllButton;
    private JButton findBestSetButton;
    private JComboBox s1;
    private JLabel left_panel_label;
    private JButton addMonsterButton;
    private JPanel after_load_panel;
    public JPanel bottom_panel;
    private JButton load_monsters_button;
    public JLabel selected_monster_label;
    public JLabel monster_name_label;
    private JTextPane current_stats_label;
    private JScrollPane monster_scroll_pane;
    private JPanel monster_pane;
    private JPanel center_panel;
    public JScrollPane rune_scroll_panel;
    public RuneScrollPanel rune_scroll_pane;
    private JPanel rune_pane;
    private JPanel rune_left;
    private JPanel rune_right;
    private JPanel stat_opt_pane;
    private JPanel pos2_3pane;
//    private ImageIcon runeoff = getImg("ui/runeoff.png");
    private ImageIcon runeoff = frame.uiResources.getImage("runeoff.png");
    private ImageIcon left_panel_cover_img = getImg("ui/ifrit.png");
    private ImageIcon thumbs = getImg("ui/thumbs.png");
    private ImageIcon runeon = frame.uiResources.getImage("runeon.png");

    private int currentRune = 0;


    private ImageIcon getImg(String s){
        return new ImageIcon(getClass().getClassLoader().getResource(s));
    }
    private ImageIcon getMonsterImage(String s){
        return new ImageIcon(getClass().getClassLoader().getResource("monsters/"+s+".jpg"));
    }

    public ArrayList<Integer> indexOfRunePositions = new ArrayList();

    public RuneBag offlineRuneBag;

    public MonsterRuneMap monsterRuneMap = new MonsterRuneMap();

    private void UIsetup(){
    }
    private void startSetup(){
        frame.setSizeTo(0,0);

        frame.setCurrentUserID(1);
//        frame.setCurrentUserID(10);

        this.setBackground(new Color(0x1F160B));

        setImagesNText("", runeoff);
        after_load_panel.setVisible(false);

        add_new_rune_button.setVisible(false);

        fetchMonsterDataFromDB();

        // padding for monster selected image
        int padding = 10;
        Border paddingBorder = BorderFactory.createEmptyBorder(padding, padding, padding, padding);
        selected_monster_label.setBorder(paddingBorder);

        current_stats_label.setFocusable(false);
        current_stats_label.setBackground(new Color(242,242,242));
        addMonsterButton.setVisible(false);

    }
    public MainAppPanel(MainFrame f, int userID) {
        super(f);
        System.out.println("Starting Main App panel...");
//        setParent(f);

        startSetup();

        load_runes_button.addActionListener(e -> {

            monster_scroll_pane.setVisible(false);
//            for (int i = 0; i < parentFrame.monsterResources.size(); i++) {
//                System.out.println(parentFrame.monsterResources.get(i).monster);
//            }

            addAllUserRunes(frame.getCurrentUserID());
            load_runes_button.setVisible(false);
            after_load_panel.setVisible(true);
            add_new_rune_button.setVisible(true);
//            System.out.println(parentFrame.monsterAssetFiles);
//                System.out.println(offlineRuneBag);
//                System.out.println(String.format("%s%s%s%s%s%s",runesEquipped[0],runesEquipped[1]
//                        ,runesEquipped[2],runesEquipped[3],runesEquipped[4],runesEquipped[5] ));
            addMonsterButton.setVisible(true);

//            RuneScrollPanel temp = (RuneScrollPanel) rune_scroll_panel;
//            temp.loadAssetsIntoPanels();


            this.rune_scroll_pane = (RuneScrollPanel) rune_scroll_panel;
            this.rune_scroll_pane.loadAssetsIntoPanels();

            this.frame.reframe();
            this.monster_scroll_pane.setVisible(true);

//            this.frame.changePanel(this.mainPanel);


        });
        add_new_rune_button.addActionListener(e -> {
            frame.changePanel_NewRune();
//                frame.reframe();

        });
//        addMonsterButton.addActionListener(e -> {
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

            if (currentMonster.getId() == 0) {
                JOptionPane.showMessageDialog(frame, "Please select a monster", "Not enough data", 2, thumbs);
            } else {
                if (statPriority1.getSelectedIndex() > 0 &&
                        statPrioritry2.getSelectedIndex() > 0 &&
                        statPriority3.getSelectedIndex() > 0) {
                    optimize();
                    for (int i = 0; i < 6; i++) {
                        System.out.print(runesEquipped[i] + " ");
                    }
                } else
                    JOptionPane.showMessageDialog(frame, "Please select all attribute priorities              ", "Not enough data", 2, thumbs);
            }


        });
//        runeMap.addActionListener(e -> {
//
//            if (currentRunePosition > 0) {
//                if (currentRunePosition == 1) pos1.setIcon(runeon);
//                if (currentRunePosition == 2) pos2.setIcon(runeon);
//                if (currentRunePosition == 3) pos3.setIcon(runeon);
//                if (currentRunePosition == 4) pos4.setIcon(runeon);
//                if (currentRunePosition == 5) pos5.setIcon(runeon);
//                if (currentRunePosition == 6) pos6.setIcon(runeon);
//
//                currentRunePosition--;
//
//
//                if (runesEquipped[currentRunePosition] != -1) {
//                    int c = runesEquipped[currentRunePosition];
//                    offlineRuneBag.get(c).setIsEquipped(false);
////                    OLDrune_table.setValueAt(runeon, runesEquipped[currentRunePosition], 0);
//                }
////                offlineRuneBag.get(currentRune).setIsEquipped(true);
////                OLDrune_table.setValueAt(runeoff, currentRune, 0);
//                runesEquipped[currentRunePosition] = currentRune;
//                setMonsterLabelText(false);
//            }
//
//
//        });


        runeMap.addActionListener(e -> System.out.print(monsterRuneMap));

        addMonsterButton.addActionListener(e -> {


//            JPanel base_monster_panel = new JPanel();
//
//            JScrollPane base_monster_scroll_panel = new BaseMonsterScrollPanel(this, base_monster_panel);
//
//            resizeComponent(base_monster_scroll_panel, 400, 200);
//
//            this.frame.setContentPane(base_monster_scroll_panel.getParent());
//            this.frame.reframe();

        });
    }

    public void removeRune(int runeID, int runePosition){
        System.out.println("Removing rune " + runeID + " from position " + runePosition);
        if (runePosition == 1) pos1.setIcon(runeoff);
        if (runePosition == 2) pos2.setIcon(runeoff);
        if (runePosition == 3) pos3.setIcon(runeoff);
        if (runePosition == 4) pos4.setIcon(runeoff);
        if (runePosition == 5) pos5.setIcon(runeoff);
        if (runePosition == 6) pos6.setIcon(runeoff);

        currentRunePosition = runePosition;
        runesEquipped[currentRunePosition] = -1;
        updateStatDisplay(false);
    }

    public void applyRune(int runeID, int runePosition) {
        System.out.println("Applying rune " + runeID + " to monster " + currentMonster.getId() + " rune position " + runePosition);
        if (runePosition == 1) pos1.setIcon(runeon);
        if (runePosition == 2) pos2.setIcon(runeon);
        if (runePosition == 3) pos3.setIcon(runeon);
        if (runePosition == 4) pos4.setIcon(runeon);
        if (runePosition == 5) pos5.setIcon(runeon);
        if (runePosition == 6) pos6.setIcon(runeon);

        currentRunePosition = runePosition;
        runesEquipped[currentRunePosition] = runeID;
//        offlineRuneBag.get(runeID).setIsEquipped(true);
        updateStatDisplay(false);
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

    private void addAllUserRunes(int userid){
        System.out.printf("**********************\nUserID : %s\n**********************\n", userid);
        RuneDB connct = new RuneDB();
        System.out.println("Getting user runes");
        offlineRuneBag = connct.getUserRunes(userid);
        connct.closeConnection();
        System.out.println("Got user runes");
    }

    public JPanel getMain() {
        return mainPanel;
    }

//    private void resetRuneImage(int r){
//        OLDrune_table.setValueAt(runeon, r, 0);
//    }

    private void optimize(){

        String p1s = subStatFormat(statPriority1);
        String p2s = subStatFormat(statPrioritry2);
        String p3s = subStatFormat(statPriority3);
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

        updateStatDisplay(false);

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

//        removeRuneImages();


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

    public ArrayList<String> baseMonsterArrayFromDatabase;
    public ArrayList<Monster> baseMonsterBox; // want to not use this
    public MonsterImageIcon selectedMonster;
    public Monster currentMonster = new Monster();

    private void fetchMonsterDataFromDB(){
        MonsterDB connect = new MonsterDB();
        this.baseMonsterArrayFromDatabase = connect.getAllMonsters();
        connect.closeConnection();
//        System.out.println(baseMonsterArrayFromDatabase);
//        createMonsters(baseMonsterStringArray);
        createBaseMonsters();
    }
//    private void createMonsters(ArrayList<String> dataArr){
    private void createBaseMonsters(){
        System.out.println("\n***********************Creating " + baseMonsterArrayFromDatabase.size() + " monsters***********************\n");
        baseMonsterBox = new ArrayList<>(); // want to not use this
        for(String data : this.baseMonsterArrayFromDatabase){
            baseMonsterBox.add(new Monster(data));
        }
        addResourcesToBaseMonsters();
    }
    private void addResourcesToBaseMonsters(){
        for (Monster m : this.baseMonsterBox ) {

            try {
                frame.monsterResources.get(m.getName()).monster = m;

            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
                System.out.println("Error: " + m.getName() + " not found in monsterResources");

            }
//            monsterBox.add(new Monster(baseMonsterStringArray.get(i))); // want to not use this
//            System.out.println(monsterBox.get(i));
//            frame.monsterResources.get(m.getName()).monster = baseMonsterBox.get(i);
//            frame.monsterResources.get(i).index = i;
        }
    }
    public void setSelectedMonster(MonsterImageIcon monster){

        this.selected_monster_label.setBorder(new RoundedBorder(15, null, 3));
        this.selected_monster_label.setSize(100,100);
        this.selected_monster_label.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.selectedMonster = monster;
//        this.monsterSelectedID = monster.index;
        this.monsterSelectedID = monster.monster.getId();
        this.selected_monster_label.setIcon(monster.img);

    }
    private void unselectAllRunes(){
        pos1.setIcon(runeoff);
        pos2.setIcon(runeoff);
        pos3.setIcon(runeoff);
        pos4.setIcon(runeoff);
        pos5.setIcon(runeoff);
        pos6.setIcon(runeoff);
        for(int i=0; i<6;i++)runesEquipped[i]=-1;
//        runeMap.doClick();
        updateStatDisplay(false);

    }
    public void updateStatDisplay(boolean reset){

        if(currentMonster.getId()-1 != -1){

            String s;

            Monster mon = new Monster();
            s = "";

            int ragecount = 0;
            int bladecount = 0;
            int swiftcount = 0;

            ArrayList<Rune> temp = new ArrayList<>();
            for(int i = 1; i<7; i++) if(runesEquipped[i]!=-1)temp.add(offlineRuneBag.getRune(runesEquipped[i]));
            for(Rune r:temp){
                // Adding main stat
                if(r.isEquipped) {
                    s = r.getMainStat().getMainStatAttribute();
//                    System.out.println(s +  " + " + r.getMainStat().getValue());
                    switch(s){
                        case "HP%": mon.setHP((currentMonster.getHP()*r.getMainStat().getValue()/100)+mon.getHP()); break;
                        case "ATK%": mon.setATK((currentMonster.getATK()*r.getMainStat().getValue()/100)+mon.getATK()); break;
                        case "DEF%": mon.setDEF((currentMonster.getDEF()*r.getMainStat().getValue()/100)+mon.getDEF()); break;
                        case "SPD": mon.setSPD(mon.getSPD() + r.getMainStat().getValue()); break;
                        case "CRte": mon.setCR(mon.getCR() + r.getMainStat().getValue()); break;
                        case "CDmg": mon.setCD(mon.getCD() + r.getMainStat().getValue()); break;
                        case "RES": mon.setRES(mon.getRES() + r.getMainStat().getValue()); break;
                        case "ACC": mon.setACC(mon.getACC() + r.getMainStat().getValue()); break;
                        case "HP": mon.setHP(mon.getHP() + r.getMainStat().getValue()); break;
                        case "ATK": mon.setATK(mon.getATK() + r.getMainStat().getValue()); break;
                        case "DEF": mon.setDEF(mon.getDEF() + r.getMainStat().getValue()); break;
                    }
//                    if(r.getSetString().equals("Rage")) ragecount++;
//                    if(r.getSetString().equals("Blade")) bladecount++;
//                    if(r.getSetString().equals("Swift")) swiftcount++;

                    for(SubStat sub : r.getSubStats()){
                        s = sub.getSubStat();
                        switch(s){
                            case "HP%": mon.setHP((currentMonster.getHP()*sub.getSubValueInt()/100)+mon.getHP()); break;
                            case "ATK%": mon.setATK((currentMonster.getATK()*sub.getSubValueInt()/100)+mon.getATK()); break;
                            case "DEF%": mon.setDEF((currentMonster.getDEF()*sub.getSubValueInt()/100)+mon.getDEF()); break;
                            case "SPD": mon.setSPD(mon.getSPD() + sub.getSubValueInt()); break;
                            case "CRte": mon.setCR(mon.getCR() + sub.getSubValueInt()); break;
                            case "CDmg": mon.setCD(mon.getCD() + sub.getSubValueInt()); break;
                            case "RES": mon.setRES(mon.getRES() + sub.getSubValueInt()); break;
                            case "ACC": mon.setACC(mon.getACC() + sub.getSubValueInt()); break;
                            case "HP": mon.setHP(mon.getHP() + sub.getSubValueInt()); break;
                            case "DEF": mon.setDEF(mon.getDEF() + sub.getSubValueInt()); break;
                            case "ATK": mon.setATK(mon.getATK() + sub.getSubValueInt()); break;
                        }
                    }

                }
            }
//            if(ragecount>=4) {mon.setCD(mon.getCD()+40);}
//            if(swiftcount>=4) {mon.setSPD((currentMonster.getSPD()*25/100) + mon.getSPD());}
//            if(bladecount>=2){
//                int a = bladecount/2;
//                mon.setCR(mon.getCR()+12*a);
//            }

            String plus = " + ";
            StringBuilder sb = new StringBuilder();
            if(!reset){
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
    public int[] runesEquipped = new int[] {-1,-1,-1,-1,-1,-1,-1};
    private int getRuneFocused(){
        return runeFocused;
    }

    public void resetRuneArray(){
        runesEquipped = new int[] {currentMonster.getId(),-1,-1,-1,-1,-1,-1};
    }

    private void test_OptimizerScript(){
        statPriority1.setSelectedIndex(1);
        statPrioritry2.setSelectedIndex(2);
        statPriority3.setSelectedIndex(3);
        monsterSelectedID = 10;
        addMonsterButton.doClick();
    }
    private int currentRunePosition;



    private void createUIComponents() {
        System.out.println("Creating UI Components for MainAppPanel");
        // TODO: place custom component creation code here
//        System.out.println(this.parentFrame.localAssetList);
        monster_pane = new JPanel();
        monster_scroll_pane = new MonsterScrollPanel(this, monster_pane);
//        monster_scroll_pane.setVisible(false);


        rune_left = new JPanel();
        rune_left.setBackground(null);
        rune_right = new JPanel();
        rune_pane = new JPanel();
        rune_scroll_panel = new RuneScrollPanel(this, rune_pane, rune_left, rune_right);

        rune_right.setBackground(null);

    }
}














