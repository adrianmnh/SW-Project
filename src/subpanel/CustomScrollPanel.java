package subpanel;

import static tools.HelperMethods.*;
import classes.Monster;
import classes.subclasses.CustomImageButton;
import classes.subclasses.MonsterImageIcon;
import classes.subclasses.MyImageIcon;
import panels.MainAppPanel;
import panels.MainFrame;
import panels.MyPanel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ObjectOutputStream;
import java.time.chrono.MinguoChronology;

public class CustomScrollPanel extends JScrollPane{

    private final JPanel monsterPanel;
    private JPanel left2;
    public MainAppPanel parentPanel;

    private Component outerComponent;
    private final MainFrame parentFrame;
    MonsterImageIcon selectedMonster;
    final int REPEAT = 1;
    int ICON_DIMENSION = 80;
    final int HEIGHT = 2;

    int COLUMN_SIZE;
    int COLUMNS;

    //        public leftScrollPanel(MainFrame PF, JPanel left1, JPanel left2) throws IOException {
    public CustomScrollPanel(MyPanel parentPanel, JPanel panel)  {


        this.monsterPanel = panel;
//        this.left2 = l2;
        this.parentPanel = (MainAppPanel) parentPanel;
//        this.outerComponent = component;
        this.parentFrame = parentPanel.parentFrame;

        this.getHorizontalScrollBar().setUnitIncrement(16);

        loadAssetsIntoPanels();

//        this.add(monsterPanel);

    }

    public void loadAssetsIntoPanels() {

        int size = this.parentPanel.parentFrame.monsterAssetFiles.size();

//        JOptionPane.showMessageDialog(parentFrame,"message", "Watcher", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getClassLoader().getResource("monsters/Artamiel.jpg")));

//        System.out.println("size: " + size);
//        for (int i = 0; i < REPEAT; i++) {
//            for (String name : this.parentFrame.monsterAssetFiles) {
//                ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource(name));
////                img = new ImageIcon(img.getImage().getScaledInstance(ICON_DIMENSION,ICON_DIMENSION, Image.SCALE_SMOOTH));
//                JLabel l = new JLabel(img);
//                monsterPanel.add(l);
//            }
//        }
        for (int i = 0; i < REPEAT; i++) {
            for (MyImageIcon img : this.parentFrame.monsterAssetIcons) {
                JLabel l = new JLabel(img.img);
                monsterPanel.add(l);
            }
        }
        COLUMNS = size * REPEAT / 2;
        if(size%2==1)   this.COLUMNS += 1;

        COLUMN_SIZE = ICON_DIMENSION * COLUMNS;


//        monsterPanel.setMinimumSize(new Dimension(COLUMN_SIZE, ICON_DIMENSION * HEIGHT));
//        monsterPanel.setPreferredSize(new Dimension(COLUMN_SIZE, ICON_DIMENSION * HEIGHT));
//        monsterPanel.setMaximumSize(new Dimension(COLUMN_SIZE, ICON_DIMENSION * HEIGHT));
        resizeComponent(monsterPanel, COLUMN_SIZE, ICON_DIMENSION * HEIGHT);

//        System.out.println("Col size: " + COLUMN_SIZE + " " + ICON_DIMENSION * HEIGHT);
//        System.out.println(monsterPanel.());

        monsterPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));


        this.parentPanel.parentFrame.pack();

//        System.out.println(monsterPanel.getSize());
////        System.out.println(counter * ICON_HEIGHT);
//        System.out.println(  parentFrame.getSize()  );
//        System.out.println( parentPanel.getSize()  );

        try {
            Thread.sleep(100);
//            this.setMinimumSize(new Dimension(COLUMN_SIZE, ICON_DIMENSION * HEIGHT));
//            this.setPreferredSize(new Dimension(COLUMN_SIZE, ICON_DIMENSION * HEIGHT));
//            this.setMaximumSize(new Dimension(COLUMN_SIZE, ICON_DIMENSION * HEIGHT));
//            Helper.resize(this, COLUMN_SIZE * ICON_DIMENSION + 10, ICON_DIMENSION * HEIGHT + 10);
//            Helper.resize(outerComponent, COLUMN_SIZE * ICON_DIMENSION + 10, ICON_DIMENSION * HEIGHT + 10);
        } catch (Exception e) {
            e.printStackTrace();
        }




        monsterPanel.addMouseListener(  new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e2) {

                System.out.println(monsterPanel.getSize());
                System.out.println(  parentFrame.getSize()  );
                System.out.println( parentPanel.getSize()  );

                int size = parentFrame.monsterAssetIcons.size();
                int row = e2.getY()/ICON_DIMENSION;
                int col = e2.getX()/ICON_DIMENSION;
                int pos = row * COLUMNS + col ;
                int assetPosInList = pos % size;
    //                System.out.println(row + " " + col + "\n" + assetPosInList);

                createMonsterImage(assetPosInList);

                ImageIcon thumbs = parentFrame.resourceAssetIcons.get("thumbs.png");
                thumbs = scaleImage(thumbs, (int) (thumbs.getIconWidth()*.8), (int) (thumbs.getIconHeight()*.8));
                ImageIcon img = parentFrame.monsterAssetIcons.get(assetPosInList).img;



//                JOptionPane optionPane = new JOptionPane(
//                        "Select " + selectedMonster.monster.getName(),
//                        JOptionPane.INFORMATION_MESSAGE,
//                        JOptionPane.OK_CANCEL_OPTION,
//                        null, new Object[] {new JLabel(img), JOptionPane.OK_OPTION, JOptionPane.CANCEL_OPTION}, JOptionPane.OK_OPTION
//                        );
//                optionPane.setMessageType(JOptionPane.PLAIN_MESSAGE);
//                // Create a JDialog to display the option pane
//                JDialog dialog = optionPane.createDialog(parentFrame, "Select Monster");
//                dialog.setVisible(true);
//                if (optionPane.getValue() != null && (int)optionPane.getValue() == JOptionPane.YES_OPTION) {
//                    System.out.println("Button clicked!");
//                    setSelectedMonster(assetPosInList);
//                }
                Object[] options = {"OK", "Cancel"}; // Custom button labels
                int result = JOptionPane.showOptionDialog(
                        parentFrame, // Use null for the parent frame
                        new Object[]{new JLabel(img)}, // Your custom content
                        "Select " + selectedMonster.monster.getName() + "?",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null, // Use null for the custom icon
                        options, // Custom button labels
                        options[0] // Default selection
                );

                if (result == JOptionPane.OK_OPTION) {
                    // Handle OK button click
                }



//                JOptionPane.showMessageDialog(parentFrame,message, "Watcher", JOptionPane.INFORMATION_MESSAGE, null);
//                String message = "Clicked on " + assetPosInList + "\n -- "
//                        + parentFrame.monsterAssetFiles.get(assetPosInList) + "\n --"
//                        + e2.getX() + " " + e2.getY()
//                        + row + " " + col;





//                System.out.println(row + " " + col + "\n");
//                System.out.println(assetPosInList);
//                System.out.println("Clicked on -- " + parentFrame.monsterAssetFiles.get(assetPosInList));

            }
        });



    }
    private void createMonsterImage(int assetPosInList){

        this.selectedMonster = parentFrame.monsterAssetIcons.get(assetPosInList);
        this.selectedMonster.monster = parentPanel.monsterBox.get(assetPosInList);

        this.selectedMonster.img = scaleImage(selectedMonster.img, 90,90);
    }
    private void setSelectedMonster(int assetPosInList){

        parentPanel.monsterSelected = assetPosInList;

        parentPanel.setMonsterPaneImage(selectedMonster);

        int padding = 15; // Adjust the padding value as needed
        Border paddingBorder = BorderFactory.createEmptyBorder(padding, padding, padding, padding);

        parentPanel.monsterPanelLabel.setBorder(paddingBorder);

//                Monster m = parentPanel.monsterBox.get(assetPosInList);


//                JOptionPane.showConfirmDialog(parentFrame, "Selected: " + m.getName() , "Monster Selected", JOptionPane.OK_OPTION, thumbs);


    }


}
