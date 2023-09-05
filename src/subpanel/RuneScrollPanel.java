package subpanel;

import classes.subclasses.MyImageIcon;
import panels.MainAppPanel;
import panels.MainFrame;
import panels.MyPanel;
import runes.Rune;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static tools.HelperMethods.*;

public class RuneScrollPanel extends JScrollPane{

    private final JPanel runePanel;

    private JPanel left;

    private JPanel right;
    public MainAppPanel parentPanel;
    public MainFrame parentFrame;

    final int REPEAT = 1;
    int ICON_DIMENSION = 80;
    int COLUMN_SIZE;
    int COLUMNS;
    public RuneScrollPanel(MyPanel parentPanel, JPanel panel, JPanel l1, JPanel l2)  {

        this.runePanel = panel;

        this.left = l1;

        this.right = l2;

//        this.left2 = l2;
        this.parentPanel = (MainAppPanel) parentPanel;
//        this.outerComponent = component;
        this.parentFrame = parentPanel.parentFrame;

//        this.getHorizontalScrollBar().setUnitIncrement(16);

        this.getVerticalScrollBar().setUnitIncrement(16);

        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


//        loadAssetsIntoPanels();
//        resizeComponent(this, 300, 600);
//        resizeComponent(left, 150, 600);
//        resizeComponent(right, 150, 600);

    }

    public void loadAssetsIntoPanels() {

        int size = this.parentPanel.offlineRuneBag.size();
        int count = -1;
        for (int i = 0; i < REPEAT; i++) {
            for (Rune r : this.parentPanel.offlineRuneBag ) {
                System.out.println(r.toStringGUI());
                JLabel l = new JLabel(r.toStringGUI());
                right.add(l);
            }
        }

//        for (MyImageIcon img : this.parentFrame.resourceAssetIcons) {
//
//            JLabel l = new JLabel(img.img);
//            left.add(l);
//            System.out.println(++count + " - Added " + img.name);
//
//        }
        for (MyImageIcon img : parentFrame.monsterAssetIcons) {
            JLabel l = new JLabel(img.img);
            left.add(l);
            System.out.println(" - Added " + img.img.getDescription());
        }





//        COLUMNS = (size * REPEAT) / 2;
//        if(size%2==1)   this.COLUMNS += 1;
//        System.out.println(size);
//        System.out.println(COLUMNS);
//
//        COLUMN_SIZE = ICON_DIMENSION * COLUMNS;
//
//        resizeComponent(runePanel, COLUMN_SIZE, ICON_DIMENSION * HEIGHT);
//
//
//        runePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
//
//
//        this.parentPanel.parentFrame.pack();
//
        runePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e2) {

                int height = left.getPreferredSize().height;
                int width = left.getPreferredSize().width;
                left.setPreferredSize(new Dimension(width, height+800));

                parentFrame.pack();

                int row = e2.getY();
                int col = e2.getX();
                    System.out.println("row:" + row + " " + "col:" +col + " " );


//                    MyImageIcon image = parentFrame.resourceAssetIcons.get("runeon.png");
                ImageIcon image = parentFrame.resourceAssetIcons.getImage("runeon.png");

                for (int i = 0; i < 5; i++) {
                    JLabel l = new JLabel(image);
//                    l.setBounds(col, row, 80, 80);
                    left.add(l);
                }
                for (MyImageIcon img : parentFrame.monsterAssetIcons) {
                    JLabel l = new JLabel(img.img);
                    left.add(l);
                    System.out.println(" - Added " + img.img.getDescription());
                }





                String message = "Clicked on \n -- "
                        + e2.getX() + " " + e2.getY()
                        + row + " " + col;
                System.out.println(row + " " + col + "\n");
                JOptionPane.showMessageDialog(parentFrame,message, "Watcher", JOptionPane.INFORMATION_MESSAGE, image);



            }
        });


    }

    public void test() {
        System.out.println("test");
    }

}
