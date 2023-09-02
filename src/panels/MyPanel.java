
package panels;
import javax.swing.*;
public interface MyPanel {
     private void setParent(MainFrame p) {};
     JPanel getMain();
     MainFrame parentFrame = null;
}
