package panels;

import runes.Rune;
import database.RuneDB;
import subpanel.RuneScrollPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateRunePanel extends  MyPanel {
    private MainAppPanel mainAppPanel;
    private JPanel mainPanel;
    private JComboBox setList;
    private JButton enterButton;
    private JButton clearButton;
    private JComboBox posList;
    private JComboBox statList;
    private JRadioButton grade1;
    private JRadioButton innate_yes;
    private JRadioButton innate_no;
    private JRadioButton grade2;
    private JLabel grade_label;
    private JPanel left_panel;
    private JLabel left_panel_label;
    private JLabel innate_label;
    private JLabel top_panel_label;
    private JPanel top_panel;
    private JButton substatButton;
    private JComboBox sub_innate;
    private JPanel bottom_panel;
    private JLabel bottom_panel_label;
    private JComboBox sub1;
    private JComboBox sub2;
    private JComboBox sub3;
    private JComboBox sub4;
    private JPanel right_panel;
    private JLabel right_panel_label;
    private JPanel substat_panel;
    private JPanel mainstat_panel;
    private JTextField sub_innateval;
    private JTextField sub_val1;
    private JTextField sub_val2;
    private JTextField sub_val3;
    private JTextField sub_val4;
    private JButton enterSubsButton;
    private JButton clearSubsButton;
    private JPanel mainstat_buttons;
    private JPanel substat_buttons;
    private JButton backSubsButton;
    private JLabel substat_panel_label;
    private JLabel innatelabel;
    private JLabel rune_main_stat_label;
    private JButton backButton;
    private ImageIcon leftPanelImg = new ImageIcon(this.getClass().getClassLoader().getResource("ui/gal.png"));
    private ImageIcon runeImage = new ImageIcon(this.getClass().getClassLoader().getResource("ui/rune.png"));
    private ImageIcon substatPanelImg = new ImageIcon(this.getClass().getClassLoader().getResource("ui/poker.png"));
    private ImageIcon no = new ImageIcon(this.getClass().getClassLoader().getResource("ui/no.png"));
    private void setParent(MainFrame p) {
        frame = p;
    }
    public JPanel getMain(){
        return mainPanel;
    }
    private Boolean applySelections(){
        //printSelection();
        if(setList.getSelectedIndex()==0 || posList.getSelectedIndex()==0 || statList.getSelectedIndex()==0
        || !grade1.isSelected()&&!grade2.isSelected() || !innate_no.isSelected() && !innate_yes.isSelected())
            return false;
        return true;
    }


    private void printSelection(){
        String grade=null;
        if(grade1.isSelected())grade = grade1.getText();
        else if(grade2.isSelected())grade = grade2.getText();
        String innate = null;
        if(innate_yes.isSelected())innate = innate_yes.getText();
        else if(innate_no.isSelected())innate = innate_no.getText();
        //System.out.println("Rune Set: " + Arrays.toString(setList.getSelectedObjects()));
        System.out.println("Rune Set: " + setList.getSelectedItem().toString());
        System.out.println("RuneGrad: " + grade);
        System.out.println("Innate:  " + innate);
        //System.out.println("Position: " + Arrays.toString(posList.getSelectedObjects()));
        System.out.println("Rune Set: " + posList.getSelectedItem().toString());
        //System.out.println("MainStat: " + Arrays.toString(statList.getSelectedObjects()));
        System.out.println("Rune Set: " + statList.getSelectedItem().toString());
    }
    private int getGradeSelected(){
        if(!grade1.isSelected()&&!grade2.isSelected())return 0;
        else if(grade1.isSelected())return 5;
        else return 6;
    }
    private int getInnateSelected(){
        if(!innate_yes.isSelected()&&!innate_no.isSelected())return -1;
        if(innate_yes.isSelected())return 1;
        if(innate_no.isSelected())return 0;
        return -1;
    }

    private void clearSelection(){
        setList.setSelectedIndex(0);
        posList.setSelectedIndex(0);
        statList.setSelectedIndex(0);
        undoRadioBut(); //does nada
    }
    private void clearSubSelections(){
        sub_innate.setSelectedIndex(0);
        sub1.setSelectedIndex(0);
        sub2.setSelectedIndex(0);
        sub3.setSelectedIndex(0);
        sub4.setSelectedIndex(0);
        sub_innateval.setText(null);
        sub_val1.setText(null);
        sub_val2.setText(null);
        sub_val3.setText(null);
        sub_val4.setText(null);
    }
    private void clearOneSubSelection(int a){
        switch (a){
            case 0: sub_innate.setSelectedIndex(0);
                    sub_innateval.setText(null);
                    break;
            case 1: sub1.setSelectedIndex(0);
                sub_val1.setText(null);
                break;
            case 2: sub2.setSelectedIndex(0);
                sub_val2.setText(null);
                break;
            case 3: sub3.setSelectedIndex(0);
                sub_val3.setText(null);
                break;
            case 4: sub4.setSelectedIndex(0);
                sub_val4.setText(null);
                break;
            default: break;
        }



    }
    private void undoRadioBut(){
        grade1.setSelected(false);
        grade2.setSelected(false);
        innate_yes.setSelected(false);
        innate_no.setSelected(false);
    }
    private void show_mainStatPanel(){
        mainstat_panel.setVisible(true);
        substatButton.setVisible(true);
        mainstat_buttons.setVisible(true);
//        hide_addSubsPanel();
//        frame.reframe();


    }
    private void hide_mainStatPanel(){
        mainstat_panel.setVisible(false);
    }
    private void hide_addSubsPanel(){
        substat_panel.setVisible(false);
        substat_buttons.setVisible(false);
//        frame.reframe();
    }
    private void show_addSubsPanel(){
        if(innate_no.isSelected()){
            sub_innate.setVisible(false);
            sub_innateval.setVisible(false);
            innatelabel.setVisible(false);
            clearOneSubSelection(0);
        } else {
            sub_innate.setVisible(true);
            sub_innateval.setVisible(true);
            innatelabel.setVisible(true);
        }
        String current_data = getGradeSelected() + " star " + setList.getSelectedItem().toString() + " " + statList.getSelectedItem().toString() + " slot " +  posList.getSelectedItem().toString();
        rune_main_stat_label.setText(current_data);
        substat_panel.setVisible(true);
        substat_buttons.setVisible(true);
        mainstat_panel.setVisible(false);
        mainstat_buttons.setVisible(false);

        frame.reframe();

        testScript2();


    }
    private boolean checkSubstatsInput(){
        int x;
        if(getInnateSelected()==1){
            if(sub_innate.getSelectedIndex()==0 || sub_innateval.getText().equals("")){
                JOptionPane.showMessageDialog(frame, "Please input innate property and value", "Not enough data", JOptionPane.OK_OPTION, no);
                return false;
            }
            if(!sub_innateval.equals("")){
                try{
                    x = Integer.parseInt(sub_innateval.getText());
                    if(x <= 0){
                        JOptionPane.showMessageDialog(frame, "Innate value cannot be zero or negative", "Wrong input", JOptionPane.OK_OPTION, no);
                        return false;
                    }
                }catch(NumberFormatException e){
                    JOptionPane.showMessageDialog(frame, "Innate value must be an integer", "Wrong input", JOptionPane.OK_OPTION, no);
                    return false;
                }
            }
        }
        // if innate selected is NO
        if(sub1.getSelectedIndex()==0 || sub2.getSelectedIndex()==0 ||sub3.getSelectedIndex()==0 ||sub4.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(frame, "Please select all substat properties", "Not enough data", JOptionPane.OK_OPTION, no);
            return false;
        }
        if(sub_val1.getText().equals("") || sub_val2.getText().equals("") || sub_val3.getText().equals("") || sub_val4.getText().equals("")){
            JOptionPane.showMessageDialog(frame, "Please input all substat values", "Not enough data", JOptionPane.OK_OPTION, no);
            return false;
        }
        else if(!sub_val1.getText().equals("") || !sub_val2.getText().equals("") || !sub_val3.getText().equals("") || !sub_val4.getText().equals("")){
            try{
                String[] subvals = {sub_val1.getText(), sub_val2.getText(), sub_val3.getText(), sub_val4.getText()};
                for(String s : subvals)
                    if(Integer.parseInt(s) <= 0){
                        JOptionPane.showMessageDialog(frame, "Substat values cannot be zero or negative", "Wrong input", JOptionPane.OK_OPTION, no);
                        return false;
                    }
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(frame, "A substat value must be an integer", "Wrong input", JOptionPane.OK_OPTION, no);
                return false;
            }
        }
        if(!isExclusiveSubstats(getInnateSelected())) return false;


        if(getInnateSelected()==1){
            if(failsIntegerRestriction(0) || failsIntegerRestriction(1) || failsIntegerRestriction(2) || failsIntegerRestriction(3) || failsIntegerRestriction(4) ) {
                JOptionPane.showMessageDialog(frame, new String[]{"SPD and CR max sub-stat value is 35", "hp max sub-stat value is 2375", "atk and def max sub-stat value is 130", "All other substats max value is 50"}, "Wrong input", JOptionPane.OK_OPTION, no);
                return false;
            }
        }
        else{
            if(failsIntegerRestriction(1) || failsIntegerRestriction(2) || failsIntegerRestriction(3) || failsIntegerRestriction(4)){
                JOptionPane.showMessageDialog(frame, new String[]{"SPD and CR max sub-stat value is 35","hp max sub-stat value is 2375", "atk and def max sub-stat value is 130", "All other substats max value is 50"}, "Wrong input", JOptionPane.OK_OPTION, no);
                return false;
            }
        }

        return true;
    }
    private boolean isExclusiveSubstats(int innate){
        boolean test = true;
        if(innate == 1){
            if(sub_innate.getSelectedIndex() == sub1.getSelectedIndex() || sub_innate.getSelectedIndex() == sub2.getSelectedIndex()
                    || sub_innate.getSelectedIndex() == sub3.getSelectedIndex() || sub_innate.getSelectedIndex() == sub4.getSelectedIndex()){
                JOptionPane.showMessageDialog(frame, "Substats cannot be the same as innate property", "Not enough data", JOptionPane.OK_OPTION, no);
                return false;
            }
            if(sub_innate.getSelectedIndex() == statList.getSelectedIndex()){
                JOptionPane.showMessageDialog(frame, "Innate property cannot be the same as main stat", "Not enough data", JOptionPane.OK_OPTION, no);
                return false;
            }
        }
        if(statList.getSelectedIndex() == sub1.getSelectedIndex() || statList.getSelectedIndex() == sub2.getSelectedIndex()
                || statList.getSelectedIndex() == sub3.getSelectedIndex() || statList.getSelectedIndex() == sub4.getSelectedIndex()){
            JOptionPane.showMessageDialog(frame, "Substats cannot be the same as main stat", "Not enough data", JOptionPane.OK_OPTION, no);
            return false;
        }
        if(sub1.getSelectedIndex() == sub2.getSelectedIndex() || sub1.getSelectedIndex() == sub3.getSelectedIndex()
                || sub1.getSelectedIndex() == sub4.getSelectedIndex()) test = false;
        if(sub2.getSelectedIndex() == sub3.getSelectedIndex() || sub2.getSelectedIndex() == sub4.getSelectedIndex()) test = false;
        if(sub3.getSelectedIndex() == sub4.getSelectedIndex()) test =  false;
        if(test == false){
            JOptionPane.showMessageDialog(frame, "Substat properties can't be the same", "Not enough data", JOptionPane.OK_OPTION, no);
            return false;
        }
        return true;
    }
    private boolean checkMainRestrictions(int posIndex){
//            --MainStat--
//        1    SPD
//        2    ATK%
//        3    HP%
//        4    DEF%
//        5    CRte%
//        6    CDmg%
//        7    ACC%
//        8    RES%
//        9    Att
//        10    Def
//        11   HP

        switch(posIndex){
            case 1: if(statList.getSelectedIndex() != 9){
                        JOptionPane.showMessageDialog(frame, "Slot 1 main stat is always Att", "Rune property error", JOptionPane.OK_OPTION, no);
                        statList.setSelectedIndex(9);
                        return false;
                    }
                    break;

            case 3: if(statList.getSelectedIndex() != 10){
                        JOptionPane.showMessageDialog(frame, "Slot 3 main stat is always Def", "Rune property error", JOptionPane.OK_OPTION, no);
                        statList.setSelectedIndex(10);
                        return false;
                    }
                    break;

            case 5: if(statList.getSelectedIndex() != 11){
                        JOptionPane.showMessageDialog(frame, "Slot 5 main stat is always HP", "Rune property error", JOptionPane.OK_OPTION, no);
                        statList.setSelectedIndex(11);
                        return false;
                    }
                    break;

            case 2:
//                    if(statList.getSelectedIndex() == 5 || statList.getSelectedIndex() == 6) {
//                        JOptionPane.showMessageDialog(parentFrame, "Invalid slot 2 main stat\nCRte% and CDmg% are exclusive to slot 4", "Rune property error", JOptionPane.OK_OPTION, no);
//                        return false;
//                    }
//                    if(statList.getSelectedIndex() == 7 || statList.getSelectedIndex() == 8){
//                        JOptionPane.showMessageDialog(parentFrame, "Invalid slot 2 main stat\nACC% and RES% are exclusive to slot 6", "Rune property error", JOptionPane.OK_OPTION, no);
//                        return false;
//                    }
//                    break;
                    if(statList.getSelectedIndex() == 5 || statList.getSelectedIndex() == 6 || statList.getSelectedIndex() == 7 || statList.getSelectedIndex() == 8) {
                        JOptionPane.showMessageDialog(frame, "Invalid slot 2 main stat\nCRte% and CDmg% are exclusive to slot 4\nACC% and RES% are exclusive to slot 6", "Rune property error", JOptionPane.OK_OPTION, no);
                        return false;
                    }
                    break;

            case 4:
//                    if(statList.getSelectedIndex() == 1) {
//                        JOptionPane.showMessageDialog(parentFrame, "Invalid slot 4 main stat\nSPD is exclusive to slot 2", "Rune property error", JOptionPane.OK_OPTION, no);
//                        return false;
//                    }
//                    if(statList.getSelectedIndex() == 7 || statList.getSelectedIndex() == 8){
//                        JOptionPane.showMessageDialog(parentFrame, "Invalid slot 4 main stat\nACC% and RES% are exclusive to slot 6", "Rune property error", JOptionPane.OK_OPTION, no);
//                        return false;
//                    }
//                    break;
                    if(statList.getSelectedIndex() == 1 || statList.getSelectedIndex() == 7 || statList.getSelectedIndex() == 8) {
                        JOptionPane.showMessageDialog(frame, "Invalid slot 4 main stat\nSPD is exclusive to slot 2\nACC% and RES% are exclusive to slot 6", "Rune property error", JOptionPane.OK_OPTION, no);
                        return false;
                    }
                    break;
            case 6:
//                    if(statList.getSelectedIndex() == 1) {
//                        JOptionPane.showMessageDialog(parentFrame, "Invalid slot 6 main stat\nSPD is exclusive to slot 2", "Rune property error", JOptionPane.OK_OPTION, no);
//                        return false;
//                    }
//                    if(statList.getSelectedIndex() == 5 || statList.getSelectedIndex() == 6){
//                        JOptionPane.showMessageDialog(parentFrame, "Invalid slot 6 main stat\nCRte% and CDmg% are exclusive to slot 4", "Rune property error", JOptionPane.OK_OPTION, no);
//                        return false;
//                    }
//                    break;
                    if(statList.getSelectedIndex() == 1 || statList.getSelectedIndex() == 5 || statList.getSelectedIndex() == 6) {
                        JOptionPane.showMessageDialog(frame, "Invalid slot 6 main stat\nSPD is exclusive to slot 2\nCRte% and CDmg% are exclusive to slot 4", "Rune property error", JOptionPane.OK_OPTION, no);
                        return false;
                    }
                    break;
            default: break;
        }
        return true;
    }
    private boolean checkSubRestrictions(){
//SPD
//ATK%
//HP%
//DEF%
//CRte%
//CDmg%
//ACC%
//RES%
//Att
//Def
//HP

        if(getInnateSelected()==1){
            if(posList.getSelectedIndex() == 1){
                if(sub_innate.getSelectedIndex() == 4){
                    JOptionPane.showMessageDialog(frame, "Slot 1 rune cannot have DEF% innate property", "Rune sub-property error", JOptionPane.OK_OPTION, no);
                    return false;
                }
            }
            if(posList.getSelectedIndex() == 3){
                if(sub_innate.getSelectedIndex() == 2){
                    JOptionPane.showMessageDialog(frame, "Slot 3 rune cannot have ATT% innate property", "Rune sub-property error", JOptionPane.OK_OPTION, no);
                    return false;
                }
            }
        }
        // if innate is false...
        if(posList.getSelectedIndex() == 1){
            if(sub1.getSelectedIndex() == 4 || sub2.getSelectedIndex() == 4 || sub3.getSelectedIndex() == 4 || sub4.getSelectedIndex() == 4){
                JOptionPane.showMessageDialog(frame, "Slot 1 rune cannot have DEF% substat", "Rune sub-property error", JOptionPane.OK_OPTION, no);
                return false;
            }
        }
        if(posList.getSelectedIndex() == 3){
            if(sub1.getSelectedIndex() == 2 || sub2.getSelectedIndex() == 2 || sub3.getSelectedIndex() == 2 || sub4.getSelectedIndex() == 2){
                JOptionPane.showMessageDialog(frame, "Slot 3 rune cannot have ATT% substat", "Rune sub-property error", JOptionPane.OK_OPTION, no);
                return false;
            }
        }
        return true;
    }
    public void setImages(){
        left_panel_label.setIcon(leftPanelImg);
        //substat_panel_label.setIcon(substatPanelImg);
        right_panel_label.setIcon(substatPanelImg);

    }
    private boolean failsIntegerRestriction(int a) {

            if (a == 0) {
            if (sub_innate.getSelectedIndex() == 11)
                if (Integer.parseInt(sub_innateval.getText()) > 2375) return true;
                if (sub_innate.getSelectedIndex() == 10 || sub_innate.getSelectedIndex() == 9)
                    if (Integer.parseInt(sub_innateval.getText()) > 130) return true;
                if (sub_innate.getSelectedIndex() == 1 || sub_innate.getSelectedIndex() == 5)
                    if (Integer.parseInt(sub_innateval.getText()) > 35) return true;
                if (sub_innate.getSelectedIndex() == 2 || sub_innate.getSelectedIndex() == 2 || sub_innate.getSelectedIndex() == 4 || sub_innate.getSelectedIndex() == 6 || sub_innate.getSelectedIndex() == 8)
                    if (Integer.parseInt(sub_innateval.getText()) > 50) return true;
            }

            if (a == 1) {
                if (sub1.getSelectedIndex() == 11)
                    if (Integer.parseInt(sub_val1.getText()) > 2375) return true;
                if (sub1.getSelectedIndex() == 10 || sub1.getSelectedIndex() == 9)
                    if (Integer.parseInt(sub_val1.getText()) > 130) return true;
                if (sub1.getSelectedIndex() == 1 || sub1.getSelectedIndex() == 5)
                    if (Integer.parseInt(sub_val1.getText()) > 35) return true;
                if (sub_innate.getSelectedIndex() == 2 || sub_innate.getSelectedIndex() == 2 || sub_innate.getSelectedIndex() == 4 || sub_innate.getSelectedIndex() == 6 || sub_innate.getSelectedIndex() == 8)
                    if (Integer.parseInt(sub_val1.getText()) > 50) return true;
            }
            if (a == 2) {
                if (sub2.getSelectedIndex() == 11)
                    if (Integer.parseInt(sub_val2.getText()) > 2375) return true;
                if (sub2.getSelectedIndex() == 10 || sub2.getSelectedIndex() == 9)
                    if (Integer.parseInt(sub_val2.getText()) > 130) return true;
                if (sub2.getSelectedIndex() == 1 || sub2.getSelectedIndex() == 5)
                    if (Integer.parseInt(sub_val2.getText()) > 35) return true;
                if (sub_innate.getSelectedIndex() == 2 || sub_innate.getSelectedIndex() == 2 || sub_innate.getSelectedIndex() == 4 || sub_innate.getSelectedIndex() == 6 || sub_innate.getSelectedIndex() == 8)
                    if (Integer.parseInt(sub_val2.getText()) > 50) return true;
            }
            if (a == 3) {
                if (sub3.getSelectedIndex() == 11)
                    if (Integer.parseInt(sub_val3.getText()) > 2375) return true;
                if (sub3.getSelectedIndex() == 10 || sub3.getSelectedIndex() == 9)
                    if (Integer.parseInt(sub_val3.getText()) > 130) return true;
                if (sub3.getSelectedIndex() == 1 || sub3.getSelectedIndex() == 5)
                    if (Integer.parseInt(sub_val3.getText()) > 35) return true;
                if (sub_innate.getSelectedIndex() == 2 || sub_innate.getSelectedIndex() == 2 || sub_innate.getSelectedIndex() == 4 || sub_innate.getSelectedIndex() == 6 || sub_innate.getSelectedIndex() == 8)
                    if (Integer.parseInt(sub_val3.getText()) > 50) return true;
            }
            if (a == 4) {
                if (sub4.getSelectedIndex() == 11)
                    if (Integer.parseInt(sub_val4.getText()) > 2375) return true;
                if (sub4.getSelectedIndex() == 10 || sub4.getSelectedIndex() == 9)
                    if (Integer.parseInt(sub_val4.getText()) > 130) return true;
                if (sub4.getSelectedIndex() == 1 || sub4.getSelectedIndex() == 5)
                    if (Integer.parseInt(sub_val4.getText()) > 35) return true;
                if (sub_innate.getSelectedIndex() == 2 || sub_innate.getSelectedIndex() == 2 || sub_innate.getSelectedIndex() == 4 || sub_innate.getSelectedIndex() == 6 || sub_innate.getSelectedIndex() == 8)
                    if (Integer.parseInt(sub_val4.getText()) > 50) return true;
            }


        return false;
    }
    private String[] printRuneDataObject(){
        String[] runedata = new String[3];
        StringBuilder subs = new StringBuilder();
        runedata[0] = setList.getSelectedItem().toString() + " " + getGradeSelected() + " star";
        runedata[1] = statList.getSelectedItem().toString() + " slot " +  posList.getSelectedItem().toString();
        if(getInnateSelected()!= -1 && getInnateSelected()==1)
            subs.append("Innate: " + sub_innate.getSelectedItem() + " " + sub_innateval.getText() + "\n");
        subs.append(sub1.getSelectedItem().toString() + " " + sub_val1.getText() + "\n");
        subs.append(sub2.getSelectedItem().toString() + " " + sub_val2.getText() + "\n");
        subs.append(sub3.getSelectedItem().toString() + " " + sub_val3.getText() + "\n");
        subs.append(sub4.getSelectedItem().toString() + " " + sub_val4.getText() + "\n");
        runedata[2] = subs.toString();
        return runedata;
    }
    private void setPanelLabelTextTo(String s){
        left_panel_label.setText(s);
        top_panel_label.setText(s);
        right_panel_label.setText(s);
        bottom_panel_label.setText(s);
        substat_panel_label.setText(s);
    }

    private void testScript1(){
        setList.setSelectedIndex(1);
        posList.setSelectedIndex(3);
        statList.setSelectedIndex(10);
        grade2.setSelected(true);
        innate_yes.setSelected(true);
    }

    private void testScript2(){
        sub_innate.setSelectedIndex(4);
        sub_innateval.setText("8");
        sub1.setSelectedIndex(7);
        sub_val1.setText("11");
        sub2.setSelectedIndex(1);
        sub_val2.setText("14");
        sub3.setSelectedIndex(5);
        sub_val3.setText("4");
        sub4.setSelectedIndex(3);
        sub_val4.setText("16");
    }

    private String convertStatToEnum(String s){

        if(s.equals("CRte%") || s.equals("CDmg%")) s = s.replace("%","");

        return s;
    }

    private Rune createRune(){
        Rune rune;
        String set, pos, stat, grade, innate;
        set = setList.getSelectedItem().toString();
        pos = posList.getSelectedItem().toString();
        stat = convertStatToEnum(statList.getSelectedItem().toString());
        grade = String.valueOf(getGradeSelected());
        innate = getInnateSelected() == 1 ? "1" : "0";
        rune = new Rune(grade, set, pos, innate, stat);

        rune.addRuneSubstat(convertStatToEnum(sub1.getSelectedItem().toString()), sub_val1.getText());
        rune.addRuneSubstat(convertStatToEnum(sub2.getSelectedItem().toString()), sub_val2.getText());
        rune.addRuneSubstat(convertStatToEnum(sub3.getSelectedItem().toString()), sub_val3.getText());
        rune.addRuneSubstat(convertStatToEnum(sub4.getSelectedItem().toString()), sub_val4.getText());
        if (innate == "1") {
            rune.addRuneSubstat(convertStatToEnum(sub_innate.getSelectedItem().toString()), sub_innateval.getText());
        }
        System.out.println(rune.toStringGUI());
        return rune;
    }

    public CreateRunePanel(MainFrame f){
        super(f);
//        setParent(f);

        //System.out.println("Rune Creator Panel created...");
        setImages();

        setPanelLabelTextTo("");

        hide_addSubsPanel();
        substatButton.setVisible(false);
        testScript1();

        enterButton.addActionListener(e -> {
            boolean test_completeness = (applySelections()) ? true : false;
            if(test_completeness){

                if(checkMainRestrictions(posList.getSelectedIndex())){
                    show_addSubsPanel();
                }

            }else{
                if(!grade1.isSelected() && !grade2.isSelected()) JOptionPane.showMessageDialog(frame, "Please choose the star grade", "Not enough data", JOptionPane.OK_OPTION, no);
                else if(!innate_no.isSelected() && !innate_yes.isSelected()) JOptionPane.showMessageDialog(frame, "Please choose an innate option", "Not enough data", JOptionPane.OK_OPTION, no);
                else JOptionPane.showMessageDialog(frame, "Please input main rune properties", "Not enough data", JOptionPane.OK_OPTION, no);
            }
        });
        clearButton.addActionListener(e -> {
                clearSelection();
                substatButton.setVisible(false);
                frame.reframe();
        });
        substatButton.addActionListener(e -> {

            System.out.println("Entering substats panel...");
            show_addSubsPanel();
        });
        enterSubsButton.addActionListener(e -> {

            if(!checkSubstatsInput()){
                JOptionPane.showMessageDialog(frame, "Please enter all substat properties and their values");

            }else{
                if(checkSubRestrictions()){

                    System.out.println("Main stats and Subs selected");
                    int selection = JOptionPane.showOptionDialog(frame, new Object[] {"Is this correct?", "Rune:", printRuneDataObject()},
                            "CreateOrNot", JOptionPane.YES_OPTION, JOptionPane.INFORMATION_MESSAGE, runeImage, null, null);

                    if(selection == 0){
                        System.out.println("Enter pressed, checking if rune exists in database...");
                        RuneDB cnnct = new RuneDB();
                        System.out.println("currentuserid: " + frame.getCurrentUserID());
                        Rune r = createRune();
                        boolean accept = cnnct.addRuneToUser(frame.getCurrentUserID(), r);
                        cnnct.closeConnection();
                        if(accept){
                            frame.mainApp_panel.offlineRuneBag.add(r);
                            RuneScrollPanel temp = (RuneScrollPanel) frame.mainApp_panel.rune_scroll_panel;
                            temp.addRow(r);
                            frame.changePanel_BackToMainApp();
                        }
                    }
                    else{
                        System.out.println("Cancel, rune is not ready");
                    }
                }
            }

        });
        clearSubsButton.addActionListener(e -> clearSubSelections());
        backSubsButton.addActionListener(e -> {
            hide_addSubsPanel();
            show_mainStatPanel();
            frame.reframe();
        });
        posList.addActionListener(e -> {
            switch(posList.getSelectedIndex()){
                case 1: statList.setSelectedIndex(9);
                        System.out.println("Slot 1, Att main stat selected...");
                        break;
                case 3: statList.setSelectedIndex(10);
                        System.out.println("Slot 3, Def main stat selected...");
                        break;
                case 5: statList.setSelectedIndex(11);
                        System.out.println("Slot 5, HP main stat selected...");
                        break;
                default: break;
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.changePanel_BackToMainApp();
//                parentFrame.engrave_panel.bottom_panel.setVisible(false);
//                parentFrame.engrave_panel.OLDrune_scroll_panel.setVisible(false);
                frame.reframe();

            }
        });
    }

}
