package subpanel;

import static tools.HelperMethods.*;

import classes.subclasses.MonsterImageIcon;
import classes.subclasses.MyImageIcon;
import panels.MainAppPanel;
import panels.MainFrame;
import panels.MyPanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MonsterScrollPanel extends JScrollPane{

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
    public MonsterScrollPanel(MyPanel parentPanel, JPanel panel)  {


        this.monsterPanel = panel;
//        this.left2 = l2;
        this.parentPanel = (MainAppPanel) parentPanel;
//        this.outerComponent = component;
        this.parentFrame = parentPanel.parentFrame;

        this.getHorizontalScrollBar().setUnitIncrement(16);

        loadAssetsIntoPanels();

    }

    public void loadAssetsIntoPanels() {

        int size = this.parentPanel.parentFrame.monsterAssetFiles.size();
        int count = -1;
        for (int i = 0; i < REPEAT; i++) {
            for (MyImageIcon img : this.parentFrame.monsterAssetIcons) {
                JLabel l = new JLabel(img.img);
                monsterPanel.add(l);
                System.out.println(++count + " - Added " + img.img.getDescription());
            }
        }
        COLUMNS = (size * REPEAT) / 2;
        if(size%2==1)   this.COLUMNS += 1;
        System.out.println(size);
        System.out.println(COLUMNS);

        COLUMN_SIZE = ICON_DIMENSION * COLUMNS;

        resizeComponent(monsterPanel, COLUMN_SIZE, ICON_DIMENSION * HEIGHT);


        monsterPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));


        this.parentPanel.parentFrame.pack();

        monsterPanel.addMouseListener(  new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e2) {

                int size = parentFrame.monsterAssetIcons.size();
                int row = e2.getY()/ICON_DIMENSION;
                int col = e2.getX()/ICON_DIMENSION;
                int pos = row * COLUMNS + col ;
                int assetPosInList = pos;
                    System.out.println("row:" + row + " " + "col:" +col + " " + parentFrame.monsterAssetIcons.get(assetPosInList).name);

                createMonsterImage(assetPosInList);

                ImageIcon thumbs = parentFrame.resourceAssetIcons.getImage("thumbs.png");
//                thumbs = scaleImage(thumbs, (int) (thumbs.getIconWidth()*.8), (int) (thumbs.getIconHeight()*.8));
                ImageIcon img = parentFrame.monsterAssetIcons.get(assetPosInList).img;


                for (MyImageIcon img2 : parentFrame.monsterAssetIcons) {
                    JLabel l = new JLabel(img2.img);
                    monsterPanel.add(l);
                }


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
                    setSelectedMonster(assetPosInList);
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

        parentPanel.monster_name_label.setText(selectedMonster.monster.getName());

        parentPanel.currentMonster = selectedMonster.monster;

//                Monster m = parentPanel.monsterBox.get(assetPosInList);

//                JOptionPane.showConfirmDialog(parentFrame, "Selected: " + m.getName() , "Monster Selected", JOptionPane.OK_OPTION, thumbs);


    }


}
