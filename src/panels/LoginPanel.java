package panels;

import classes.Database;
import classes.UserDB;
import panels.MainFrame;
import panels.MyPanels;
import classes.User;

import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;

public class LoginPanel extends JPanel implements MyPanels {

    public MainFrame parentFrame;
    private JPanel mainPanel;
    private JPasswordField user_password;
    private JFormattedTextField user_login;
    private JButton enterButton;
    private JPanel left_panel;
    private JLabel left_panel_label;
    public JButton newRunePageButton;
    private JLabel ulabel;
    private JLabel plabel;
    private JLabel loggedlabel;
    private JTextField newEmail;
    private JPanel createNew_panel;
    private JPanel login_panel;
    private JButton newAccountButton;
    private JTextField newName;
    private JPasswordField newPassword;
    private JButton resetButton;
    private JButton cancelButton;
    private JButton submitButton;
    private ImageIcon iconYes = new ImageIcon(getClass().getClassLoader().getResource("ok.png"));
    private ImageIcon iconNo = new ImageIcon(getClass().getClassLoader().getResource("no2.png"));
    private ImageIcon load = new ImageIcon(getClass().getClassLoader().getResource("load.png"));

    private String login;
    private char[] password;
//    private User user;
    public boolean tf = false;
    private KeyAdapter keyPressed;
    public int counter = 0;

    public void setParent(MainFrame p){
        parentFrame = p;
    }

    public JPanel getMain(){
        return mainPanel;
    }
    public JButton getRuneButton(){
        return newRunePageButton;
    }

    public ActionListener[] getButtonListener(){
        return newRunePageButton.getActionListeners();
    }

    private void pressEscapeToExit(){
        if(counter > 1) {
            System.out.println("exiting application");
            System.exit(1);
        }
        else if(counter == 0){
            counter++;
            System.out.println("EscapePressed x1");
        }
        else {
            JOptionPane.showMessageDialog(mainPanel, "Press ESC exit", "Exit", JOptionPane.WARNING_MESSAGE);
            counter++; System.out.println("EscapePressed x2");
        }

    }
    private void setLabelVisibleOff(){
        newRunePageButton.setVisible(true);
        user_password.setVisible(false);
        user_login.setVisible(false);
        ulabel.setVisible(false);
        plabel.setVisible(false);
        enterButton.setVisible(false);
        mainPanel.setVisible(false);
        loggedlabel.setText("Welcome " + user_login.getText() + "!");
        mainPanel.setVisible(true);
        newAccountButton.setVisible(false);
        parentFrame.repackAfterLogin();
    }

    public void initialSetup(){
        parentFrame.pack();
        System.out.println("Entering logging panel...");
        left_panel_label.setIcon(new ImageIcon(getClass().getClassLoader().getResource("yes2.png")));
        this.newRunePageButton.setVisible(false);
        createNew_panel.setVisible(false);
        //testScript();
    }

    private void testScript(){
        user_login.setText("tabia");
        user_password.setText("pas5word");
    }

    public LoginPanel(MainFrame frame){
        super();
        setParent(frame);
        initialSetup();

        //this is added to the username and password fields to execute "enterButton" by pressing Enter
        //1KeyAdapter
            keyPressed = new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    System.out.println("EnterPressed");
                    enterButton.doClick();
                    counter = 0;
                }
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                    pressEscapeToExit();
                }
            }
        };

        user_password.addKeyListener(keyPressed);
        user_login.addKeyListener(keyPressed);


        enterButton.addActionListener(e -> {
            UserDB connct = new UserDB();
//            String connectionString = "jdbc:sqlserver://localhost:20003;database=SummonersWar;user=sa;password=legion$%^;";
//            try {
//                Connection connection = DriverManager.getConnection(connectionString);
//                // Your database interactions here
//                System.out.println("Connection successful...\n\n\n\n\n");
//                connection.close();
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//                System.out.println("NOOOOOOOOOOOOO\n\n\n\n\n");
//            }
            parentFrame.setCurrentUserID(-404);
            login = user_login.getText();
            password = user_password.getPassword();
            User user = new User(login, password);

            parentFrame.setCurrentUserID(connct.userExists(user));
            boolean match = parentFrame.getCurrentUserID() != -404? true: false;
            if (match) {
                JOptionPane.showMessageDialog(parentFrame, "Correct!", "login", JOptionPane.ERROR_MESSAGE, iconYes);
                System.out.println("Login successful...");
                setLabelVisibleOff();
                connct.closeConnection();
            }else {
                JOptionPane.showMessageDialog(parentFrame, "Incorrect username or password.", "login", JOptionPane.ERROR_MESSAGE, iconNo);
                connct.closeConnection();
            }


            //System.out.print("username: " + Arrays.toString(login.toCharArray()).replaceAll(",", ""));
            //System.out.println("\tpassword: " + Arrays.toString(password).replaceAll(",",""));
            ////UNSECURE,  THIS requires variables above to print to console and process following statement
            //boolean match = (usertemp.compareTo(login) == 0 && Arrays.equals(passtemp, password)) ? true : false;
            //String usertemp = "tabia";
            //char[] passtemp = "pas5word".toCharArray();
            ////SECURE, no print statements
            //boolean match = (usertemp.compareTo(user_login.getText()) == 0 && Arrays.equals(passtemp, user_password.getPassword())) ? true : false;

        });
        newRunePageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("moving to 'Create New Rune Panel'...");
                System.out.println("********************");
                System.out.println("currnt userid: " + parentFrame.getCurrentUserID());
                System.out.println("********************");
                parentFrame.changePanel_MainApp();

            }
        });


        newRunePageButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    newRunePageButton.doClick();
                }
            }
        });

        newAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                Object o = JOptionPane.showInputDialog(parentFrame, "Please enter invitation code", "New Account",1, load, null, "");
//                if(o!=null){
//                    if(o.toString().equals("CS370")){
//                        login_panel.setVisible(false);
//                        createNew_panel.setVisible(true);
//                        parentFrame.pack();
//                    }
//                }
                login_panel.setVisible(false);
                createNew_panel.setVisible(true);
                parentFrame.pack();
            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newName.setText("");
                newPassword.setText("");
                newEmail.setText("");
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login_panel.setVisible(true);
                createNew_panel.setVisible(false);
                parentFrame.pack();
            }
        });
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String u = newName.getText();
                String p = newPassword.getText();
                String email = newEmail.getText();
                UserDB newacc = new UserDB();
                String query = String.format("INSERT INTO GameTool.Account VALUES ('%s', '%s', '%s');", u, p, email);
                if( !u.equals("") && u.length()>5 && p.length()>5 && !p.equals("") && email.length()>=5 && !email.equals("")){
                    Object o = JOptionPane.showInputDialog(parentFrame, "Please re-enter password", "New Account",1, load, null, "");
                    if(o!=null){
                        if(o.toString().equals(p)){
                            JOptionPane.showMessageDialog(parentFrame,"Success! Your username is: " + u, "Account created", 2, load);
                            newacc.execQuery(query);
                            System.out.println("New account created");
                            login_panel.setVisible(true);
                            createNew_panel.setVisible(false);
                            parentFrame.pack();
                        }else JOptionPane.showMessageDialog(parentFrame,"Incorrect password", "Error", 2, iconNo);
                    }
                }
                else{
                    if(u.length()<5)
                    JOptionPane.showMessageDialog(parentFrame,"Username must be at least 5 characters", "Error", 2, iconNo);
                    else if(p.length()<5)
                        JOptionPane.showMessageDialog(parentFrame,"Password is too short", "Error", 2, iconNo);
                    else if(email.length()<12)
                    JOptionPane.showMessageDialog(parentFrame,"Email must be 5 characters or longer", "Error", 2, iconNo);

                }
                newacc.closeConnection();
            }
        });
        newEmail.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    submitButton.doClick();
                }
            }
        });
        newPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    submitButton.doClick();
                }
            }
        });
        newName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    submitButton.doClick();
                }
            }
        });
        submitButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    submitButton.doClick();
                }
            }
        });
    }


}
