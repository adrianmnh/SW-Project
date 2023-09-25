
package panels;
import javax.swing.*;

public abstract class MyPanel extends JPanel{
     public MainFrame frame;
     public JPanel mainPanel;
     private void setParent(MainFrame p) {
            this.frame = p;
     };
     public MyPanel(MainFrame PF){
          super();
          setParent(PF);
     }
     abstract JPanel getMain();
}
