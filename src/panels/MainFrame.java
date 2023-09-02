package panels;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class MainFrame extends JFrame {

    private int currentUserID;

    private JPanel framepanel;

    private ImageIcon logo = new ImageIcon(getClass().getClassLoader().getResource("rune.png"));

    public LoginPanel login_panel = new LoginPanel(this);
    public CreateRunePanel rune_panel;
    public ArrayList<String> localAssetList;
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

//        this.framepanel = rune_panel.getMain();

        //this.framepanel = engrave_panel.getMain();
        this.setContentPane(framepanel);
        this.pack();
        this.setLocation(750, 250);
    }
    public ArrayList<String> loadLocalAssetsInJAR(String s){

        ArrayList<String> assetList= new ArrayList<>();
        String message = null;



        if(true){
            try {
                Path root = null;
                String jarPath = null;
                jarPath = MainFrame.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
//                if(this.OS == "windows"){
                    jarPath = jarPath.replace("/", "\\");
                    jarPath = jarPath.substring(1, jarPath.length());
//                }
                root = Paths.get(jarPath);
                System.out.println("Reading From :: "+ root);
                var fileIn = Files.newInputStream(root);
                ZipInputStream zip = new ZipInputStream(fileIn);
                for (ZipEntry entry = zip.getNextEntry(); entry != null; entry = zip.getNextEntry()) {
                    String content = new String(zip.readAllBytes(), StandardCharsets.UTF_8);
//                    byte[] data = content.getBytes();
                    if(entry.getName().startsWith("assets/monsters/") && entry.getName().endsWith(".jpg")){
//                        map.put(entry.getName(), content.getBytes());
//                        map.put(entry.getName(), data);
                        assetList.add(entry.getName());
//                    System.out.println(entry.getName() + ": " + data.length + " bytes");

                    }
                }
                message = "Assets read from "+root;
//            JOptionPane.showMessageDialog(this, "Correct!", "login", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(assetList.get(5)));

            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }

        } else {

            File folder = new File(getClass().getClassLoader().getResource("assets/monsters/").getFile());
//                File[] files = folder.listFiles((dir, name) -> name.endsWith(".jpg"));
            System.out.println("Reading From :: "+ folder.getPath());
            File[] files = folder.listFiles();
            for (File file : files) {
                try (InputStream is = new FileInputStream(file)) {
//                    byte[] data = is.readAllBytes();
//                    String a = data.toString();
//                    System.out.println(file.getName() + ": " + data.length + " bytes");
//                    map.put("assets/monsters/"+file.getName(), data);
                    assetList.add("assets/monsters/"+file.getName());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            File[] imageFiles = new File("src/assets/monsters").listFiles();
            message = "Assets read from "+folder.getPath();
        }

        return assetList;
    }


}
