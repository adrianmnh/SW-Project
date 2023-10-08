package panels;

import classes.Monster;
import classes.subclasses.MacStyleScrollBar;
import classes.subclasses.MacStyleScrollBarUI;
import classes.subclasses.MonsterImageIcon;
import classes.subclasses.RoundButton;
import panels.subpanel.BaseMonsterScrollPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static tools.HelperMethods.resizeComponent;

public class AddSummonPanel extends MyPanel{
    private JPanel mainPanel;
    public JScrollPane monster_scroll;
    private JPanel monster_pane;
    private JButton button;
    private JButton back_button;

    public AddSummonPanel(MainFrame PF) {
        super(PF);
//        System.out.println("AddSummonPanel: constructor");
        setup();

        button.addActionListener(e -> {
            MonsterImageIcon monster = frame.baseMonsters.getBaseMonster("Oberon");

            MonsterImageIcon monster2 = monster.clone();

            MonsterImageIcon monster3 = frame.baseMonsters.getBaseMonster("Oberon");

            monster2.setBaseId(1000);

            if(monster.monster == monster2.monster){
                System.out.println("monster == monster2");
            }

            if(monster.monster.isEqual(monster2.monster) ){
                System.out.println(monster.baseId + " == " + monster2.baseId);
                System.out.println("monster == monster2");
            }


//            monster3.setBaseId(1000);
            System.out.println(monster2);

            if(monster2 == monster3){
                System.out.println("monster2 == monster3");
            }

            System.out.println("\n*****************************************************\n");
            if(monster.isEqual(monster3)){
                System.out.println("monster == monster3");
            }
            if(monster2.isEqual(monster3)){
                System.out.println("monster2 == monster3");
            }
            if(monster.isEqual(monster2)){
                System.out.println("monster == monster2");
            }

            System.out.println(monster);
            System.out.println(monster2);
            System.out.println(monster3);

            System.out.println("\n*****************************************************\n");

            Monster a = monster.monster.clone();

            if(a.isEqual(monster.monster)){
                System.out.println("a == monster");
                a.setATK(999999);
                if(!a.isEqual(monster.monster)){
                    System.out.println("a != monster after change");
                    System.out.println(a.getATK() + " != " + monster.monster.getATK());
                    System.out.println("After 1 change they are not equal, this means they are not the same object\n\n");
                }
            }

            Monster b = monster2.monster;
            if(b.isEqual(monster2.monster)){
                System.out.println("b == monster2");
                b.setATK(999999);
                if(b.isEqual(monster2.monster)){
                    System.out.println("b == monster2 after change");
                    System.out.println(b.getATK() + " == " + monster2.monster.getATK());
                    b.setSummonId(1000);
                    if(b.isEqual(monster2.monster)){
                        System.out.println("b == monster2 after change");
                        System.out.println(b.getSummonId() + " == " + monster2.monster.getSummonId());
                        System.out.println("After 2 changes they are equal, this means they are the same object");
                    }
                }

            }
        });

        back_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.changePanel(frame.mainApp_panel.getMain());
            }
        });

    }
    public JPanel getMain() {
        return mainPanel;
    }

    private void setup(){

    }

    private void UIsetup(){


    }



    private void createUIComponents() {

//        TODO: place custom component creation code here
//        TODO: System.out.println("AddSummonPanel: createUIComponents()");
        button = new RoundButton("Title");
        monster_pane = new JPanel();
        monster_scroll = new BaseMonsterScrollPanel(this, monster_pane);
        monster_scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        monster_scroll.setBorder(BorderFactory.createEmptyBorder());


    }

}
