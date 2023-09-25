package panels;

import classes.Monster;
import classes.subclasses.IconArrayList;
import classes.subclasses.MonsterImageIcon;
import classes.subclasses.MyImageIcon;
import database.MonsterDB;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
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
import static tools.HelperMethods.*;

public class MainFrame extends JFrame {

    private int currentUserID;

    private JPanel framepanel;

    private GraphicsDevice[] outputDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
    //    private ImageIcon logo = new ImageIcon(getClass().getClassLoader().getResource("rune.png"));
    private ImageIcon logo = getImageIcon("ui/rune.png");

    public LoginPanel login_panel;
    //= new LoginPanel(this);
    public CreateRunePanel rune_panel;
    public ArrayList<String> monsterFiles;
    public ArrayList<String> uiFiles;
    public IconArrayList<MonsterImageIcon> monsterResources;
    public IconArrayList<MonsterImageIcon> baseMonsters;

    public IconArrayList<MyImageIcon> uiResources;
    public MainAppPanel mainApp_panel;

    public AddSummonPanel base_monster_panel;

    private String OperatingSystem;

    private String ExecutableType;

    public Color baseColor = new Color(0x1F160B);
    public Color baseRed = new Color(0x140505);

    public Color fontColor = new Color(0xD1DCB5);

    public int getCurrentUserID(){
        return this.currentUserID;
    }
    public void setCurrentUserID(int id){
        this.currentUserID = id;
    }
    public boolean parentBool = false;

    public JPanel getMainAppPanel(){
        return this.mainApp_panel;
    }

    public void setSizeTo(int a, int b){
        this.setSize(1265-a, 740-b);
    }

    public static void setUIFont(FontUIResource font) {
        UIDefaults defaults = UIManager.getDefaults();
        for (Object key : defaults.keySet()) {
            Object value = defaults.get(key);
            if (value instanceof FontUIResource) {
                defaults.put(key, font);
            }
        }
    }

    public void reframe(){

        this.pack();

        this.setLocationRelativeTo(null);

        // Check if there are at least two monitors
//        if (outputDevice.length >= 2) {
//            GraphicsDevice secondMonitor = outputDevice[1];
//            Rectangle bounds = secondMonitor.getDefaultConfiguration().getBounds();
//            int centerX = bounds.x + bounds.width / 2;
//            int centerY = bounds.y + bounds.height / 2;
////            int centerY = bounds.y + bounds.height / 3;
//
//            // Calculate the position to center the JFrame on the second monitor
//            int frameX = centerX - this.getWidth() / 2;
//            int frameY = centerY - this.getHeight() / 2;
////            int frameY = centerY + this.getHeight() / 4;
//            this.setLocation(frameX, frameY);
////            this.setLocation(secondMonitor.getDefaultConfiguration().getBounds().x, 0);
//        } else {
//            this.setLocationRelativeTo(null);
//            System.out.println("There is no second monitor available.");
//        }

    }

    private void setUI(){
        //        setUIFont(new FontUIResource(new Font("Segoe UI", Font.BOLD, 12)));
        UIManager.put("Panel.background", baseColor);
        UIManager.put("Panel.foreground", fontColor);

        UIManager.put("OptionPane.background", baseColor);
        UIManager.put("OptionPane.foreground", fontColor);

        UIManager.put("ScrollPane.background", baseColor);
        UIManager.put("ScrollPane.foreground", fontColor);

//        UIManager.put("Label.background", Color.RED);
        UIManager.put("Label.foreground", fontColor);

        UIManager.put("TextField.background", baseColor);
        UIManager.put("TextField.foreground", fontColor);

        UIManager.put("TextArea.background", baseColor);
        UIManager.put("TextArea.foreground", fontColor);

        UIManager.put("TextPane.background", baseColor);
        UIManager.put("TextPane.foreground", fontColor);

//        UIManager.put("InternalFrame.activeTitleBackground", Color.ORANGE );
//        UIManager.put("InternalFrame.activeTitleForeground", Color.RED);
//        UIManager.put("InternalFrame.titleFont", new Font("Dialog", Font.PLAIN, 11));
    }

    public MainFrame() throws IOException, InterruptedException {
        super("Main Application");
        setEXECType();
        setOS();

        setUI();


        loadAssetFilesFromLocation();

        createImageFromResources();

        login_panel = new LoginPanel(this);
        login_panel = new LoginPanel(this);
        mainApp_panel = new MainAppPanel(this, 1);
        rune_panel = new CreateRunePanel(this);
        base_monster_panel = new AddSummonPanel(this);

        this.setAlwaysOnTop(true);
        this.setSize(100, 100);
        this.setIconImage(logo.getImage());
        this.setVisible(true);
        this.setResizable(false);
//        this.setResizable(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//        this.framepanel = login_panel.getMain();


        this.framepanel = mainApp_panel.getMain();
//        this.framepanel = base_monster_panel.getMain();

//        this.framepanel = rune_panel.getMain();

        //this.framepanel = engrave_panel.getMain();
        this.setContentPane(framepanel);

        this.reframe();
//        this.setLocation(750, 250);
    }

    public void changePanel(JPanel jp){
        this.setVisible(true);
        this.setSize(1,1);
        this.framepanel = jp;
        this.setContentPane(framepanel);
        this.reframe();
    }
    public void changePanel_NewRune() {
        rune_panel = new CreateRunePanel(this);
        System.out.println("Entered new rune panel...");
        this.setVisible(true);
        this.setSize(1,1);
        this.framepanel = rune_panel.getMain();
        this.setContentPane(framepanel);
        this.reframe();
//        this.setLocation(this.getX()+350, this.getY()+100);
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
//        setSizeTo(200,0);
        this.reframe();


//        this.setLocation(this.getX()-450, this.getY()-100);
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
//        setSizeTo(0,0);
        this.reframe();

//        this.setLocation(this.getX()-350, this.getY()-100);
        //engrave_panel.getLoadRunes().doClick();
    }

    public void loadAssetFilesFromLocation(){

        this.uiFiles = new ArrayList<>();
        this.monsterFiles = new ArrayList<>();

        if(this.ExecutableType == "jar"){
            try {
                Path root = null;
                String monterJarPath = null;
                monterJarPath = MainFrame.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
                if(this.OperatingSystem == "windows"){
                    monterJarPath = monterJarPath.replace("/", "\\");
                    monterJarPath = monterJarPath.substring(1, monterJarPath.length());
                }
                System.out.println("Jar Path :: "+ monterJarPath);
                root = Paths.get(monterJarPath);
                System.out.println("Reading From :: "+ root);
                var monsterFileIn = Files.newInputStream(root);
                ZipInputStream monsterZippedStream = new ZipInputStream(monsterFileIn);
                for (ZipEntry monsterEntry = monsterZippedStream.getNextEntry(); monsterEntry != null; monsterEntry = monsterZippedStream.getNextEntry()) {
                    String content = new String(monsterZippedStream.readAllBytes(), StandardCharsets.UTF_8);
//                    byte[] data = content.getBytes();
                    if(monsterEntry.getName().startsWith("monsters/") && monsterEntry.getName().endsWith(".png")){
//                        map.put(entry.getName(), content.getBytes());
//                        map.put(entry.getName(), data);
                        this.monsterFiles.add(monsterEntry.getName());
//                    System.out.println(entry.getName() + ": " + data.length + " bytes");

                    }
                }
                var fileIn = Files.newInputStream(root);
                ZipInputStream uiZipStream = new ZipInputStream(fileIn);
                for (ZipEntry uiEntry = uiZipStream.getNextEntry(); uiEntry != null; uiEntry = uiZipStream.getNextEntry()) {
                    String content = new String(uiZipStream.readAllBytes(), StandardCharsets.UTF_8);
                    if(uiEntry.getName().startsWith("ui/") ){
                        this.uiFiles.add(uiEntry.getName());
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }

        } else {

            System.out.println("production Path :: ");
            File monsterResourceFolder = new File(getClass().getClassLoader().getResource("monsters/").getFile());
//                File[] files = folder.listFiles((dir, name) -> name.endsWith(".jpg"));
            System.out.println("Reading From :: "+ monsterResourceFolder.getPath());
            File[] monsterFolderFiles = monsterResourceFolder.listFiles();
            for (File file : monsterFolderFiles) {
//                System.out.println(file.getName());
                try (InputStream is = new FileInputStream(file)) {
//                    byte[] data = is.readAllBytes();
//                    String a = data.toString();
//                    System.out.println(file.getName() + ": " + data.length + " bytes");
//                    map.put("assets/monsters/"+file.getName(), data);
                    this.monsterFiles.add("monsters/" + file.getName());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            File resourceFolder = new File(getClass().getClassLoader().getResource("ui/").getFile());
            File[] resourceFolderFiles = resourceFolder.listFiles();
            for (File file : resourceFolderFiles) {
                    uiFiles.add("ui/" + file.getName());
            }

//            System.out.println("monsterAssetList size :: "+monsterAssetList.size());
//            System.out.println("monsterAssetList :: "+monsterAssetList);

//            File[] imageFiles = new File("src/assets/monsters").listFiles();
//            message = "Assets read from "+folder.getPath();
        }

    }
    private void createImageFromResources(){
        System.out.println("Creating Resources should be first\n\n***********************");

        this.monsterResources = new IconArrayList();
        for ( String monsterAssetFile : this.monsterFiles) {
//            System.out.println("monsterAssetFile :: "+monsterAssetFile);
            MonsterImageIcon icon = new MonsterImageIcon(monsterAssetFile);
            this.monsterResources.add(icon);
        }
        this.uiResources = new IconArrayList();
        for ( String resourceAssetFile : this.uiFiles) {
            MyImageIcon icon = new MyImageIcon(resourceAssetFile);
//            System.out.println("icon :: "+icon.name);
//            ImageIcon icon = getImageIcon(resourceAssetFile);
            this.uiResources.add(icon);
        }

        createBaseMonsters();
    }

    private void createBaseMonsters(){
        this.baseMonsters = new IconArrayList();
        MonsterDB monsterDB = new MonsterDB();
        ArrayList<String> arrayList = monsterDB.getAllMonsters();
        for ( String monsterName : arrayList ) {
//            System.out.println(monsterName);

            try {
                Monster monster = new Monster(monsterName);
                MonsterImageIcon monsterImageIcon =  this.monsterResources.get(monster.getName());
                monsterImageIcon.monster = monster;
                this.baseMonsters.add(monsterImageIcon);
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
                System.out.println("Error: " + monsterName + " not found in Monster Image Resources");

            }

        }
    }

    private void setOS(){
        System.out.print("Setting OS to: ");
        String os = System.getProperty("os.name").toUpperCase();
        if(os.contains("LINUX")){
            this.OperatingSystem = "linux";
        } else if(os.contains("WINDOWS")){
            this.OperatingSystem = "windows";
        } else this.OperatingSystem = "mac";
        System.out.println(os);
    }

    public String getEXEType(){
        return this.ExecutableType;
    }

    private void setEXECType(){
        String jarPath = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        if (jarPath.endsWith(".jar")) {
            System.out.println("RUNNING FROM JAR");
            this.ExecutableType = "jar";
        } else {
            System.out.println("RUNNING FORM EXEcute class");
            this.ExecutableType = "class";
        }
    }


}
