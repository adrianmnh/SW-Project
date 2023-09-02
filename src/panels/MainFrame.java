package panels;

import javax.swing.*;
import java.io.IOException;

public class MainFrame extends JFrame {

    private int currentUserID;

    private JPanel framepanel;

    private ImageIcon logo = new ImageIcon(getClass().getClassLoader().getResource("rune.png"));

    public LoginPanel login_panel = new LoginPanel(this);
    public CreateRunePanel rune_panel;
    public MainAppPanel mainApp_panel = new MainAppPanel(this);


    public int getCurrentUserID(){
        return this.currentUserID;
    }
    public void setCurrentUserID(int id){
        this.currentUserID = id;
    }
    public boolean parentBool = false;

    public void setBut(){
        parentBool = parentBool ? true : false;
    }

    public JPanel getMainAppPanel(){
        return this.mainApp_panel;
    }

    public void setSizeTo(int a, int b){
        this.setSize(1265-a, 740-b);
    }

    public void changePanel(JPanel jp){
        this.setSize(1,1);
        this.framepanel = jp;
        this.setContentPane(framepanel);
        this.pack();
    }
    public void changePanel_NewRune() {
        rune_panel = new CreateRunePanel(this);
        System.out.println("Entered new rune panel...");
        this.setVisible(true);
        //this.framepanel.removeAll();
        this.setSize(1,1);
        this.framepanel = rune_panel.getMain();
        this.setContentPane(framepanel);
        this.pack();
        this.setLocation(this.getX()+350, this.getY()+100);
    }
    public void changePanel_MainApp() {
//        mainApp_panel = new MainAppPanel(this);
        System.out.println("Entered app UI...");
        this.setVisible(true);
        this.framepanel.removeAll();
        this.setSize(1,1);
        this.framepanel = mainApp_panel.getMain();
        this.setContentPane(framepanel);
//        this.pack();
//        this.setSize(1042, 720);
        setSizeTo(200,0);
        this.setLocation(this.getX()-450, this.getY()-100);
    }
    public void changePanel_BackToMainApp() {
        //engrave_panel = new MainAppPanel(this);
        System.out.println("BackT to main UI...");
        this.setVisible(true);
        //this.framepanel.removeAll();
        this.setSize(1,1);
        this.framepanel = mainApp_panel.getMain();
        this.setContentPane(framepanel);
//        this.setSize(1290,720);
        setSizeTo(0,0);
        this.setLocation(this.getX()-350, this.getY()-100);
        //engrave_panel.getLoadRunes().doClick();
        System.out.println(this.size());
    }

    public void repackAfterLogin(){
        this.pack();
    }

    public void repack(){
        this.pack();
    }

    public MainFrame() throws IOException, InterruptedException {
        super("Main Application");
        this.setAlwaysOnTop(true);
        this.setSize(100, 100);
        this.setIconImage(logo.getImage());
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//        this.framepanel = login_panel.getMain();
        this.framepanel = mainApp_panel.getMain();
//
//
//
//        this.framepanel = rune_panel.getMain();

        //this.framepanel = engrave_panel.getMain();
        this.setContentPane(framepanel);
        this.pack();
        this.setLocation(750, 250);

    }


}
