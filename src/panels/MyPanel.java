
package panels;
import javax.swing.*;
import static tools.HelperMethods.*;
public abstract class MyPanel extends JPanel{
     public MainFrame parentFrame;
     public JPanel mainPanel;
     private void setParent(MainFrame p) {
            this.parentFrame = p;
     };
     public MyPanel(MainFrame PF){
          super();
          setParent(PF);
     }
}
