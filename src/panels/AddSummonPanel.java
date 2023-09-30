package panels;

import classes.subclasses.MacStyleScrollBar;
import classes.subclasses.MacStyleScrollBarUI;
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

//        button.setBorder(new RoundedBorder(20));
//        button.setOpaque(false);
//        button.setContentAreaFilled(false);

        System.out.println("AddSummonPanel: constructor");

        setup();



        button.addActionListener(e -> {
            System.out.println(this.monster_scroll.getSize());
            System.out.println(this.monster_pane.getSize());
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

        System.out.println("AddSummonPanel: createUIComponents()");

        button = new RoundButton("Title");
        // TODO: place custom component creation code here
        monster_pane = new JPanel();
        monster_scroll = new BaseMonsterScrollPanel(this, monster_pane);
        monster_scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        monster_scroll.setBorder(BorderFactory.createEmptyBorder());


    }

}
