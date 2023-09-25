package panels;

import classes.subclasses.MacStyleScrollBar;
import classes.subclasses.MacStyleScrollBarUI;
import classes.subclasses.RoundButton;
import panels.subpanel.BaseMonsterScrollPanel;

import javax.swing.*;
import java.awt.*;

import static tools.HelperMethods.resizeComponent;

public class AddSummonPanel extends MyPanel{
    private JPanel mainPanel;
    private JScrollPane monster_scroll;
    private JPanel monster_pane;
    private JButton button;

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
    }
    public JPanel getMain() {
        return mainPanel;
    }

    private void setup(){

//        JScrollBar customScrollBar = new JScrollBar(Adjustable.VERTICAL);
//        customScrollBar.setUI(new MacStyleScrollBarUI());
////        customScrollBar.setBackground(new Color(0x220B0C));
//        customScrollBar.setBackground(new Color(0x1F160B));


        JScrollBar customScrollBar = new MacStyleScrollBar(Adjustable.VERTICAL);
        customScrollBar.setBackground(new Color(0x1F160B));
        customScrollBar.setUnitIncrement((240/2)-80);





        monster_scroll.setVerticalScrollBar(customScrollBar);
        monster_scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//        monster_scroll.getVerticalScrollBar().setUnitIncrement((240/2)-80);

//        resizeComponent(monster_scroll, 658, 402);
//        resizeComponent(monster_scroll, 658, 302);
        resizeComponent(monster_scroll, 80*8 + 0 + 12, 80 * 3 + 0);
//        monster_scroll.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 1));
        monster_scroll.setBorder(BorderFactory.createEmptyBorder());
    }


    private void createUIComponents() {

        System.out.println("AddSummonPanel: createUIComponents()");

        button = new RoundButton("Title");
        // TODO: place custom component creation code here
        monster_pane = new JPanel();
        monster_scroll = new BaseMonsterScrollPanel(this, monster_pane);


    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Custom ScrollBar Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);

            // Create a JTextArea within a JScrollPane
            JTextArea textArea = new JTextArea(10, 30);
            JScrollPane scrollPane = new JScrollPane(textArea);

            // Create a custom vertical scroll bar with your custom UI
            JScrollBar verticalScrollBar = new JScrollBar(JScrollBar.VERTICAL);
            verticalScrollBar.setUI(new MacStyleScrollBarUI());
            verticalScrollBar.setBackground(Color.BLACK);

            // Set the custom scroll bar to the JScrollPane
            scrollPane.setVerticalScrollBar(verticalScrollBar);

            scrollPane.setBackground(Color.BLACK);

            frame.add(scrollPane);
            frame.setVisible(true);
        });


    }



}
