package inicial;


import javax.swing.ImageIcon;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

import java.awt.Color;


public class Splash extends JWindow {


   private static final long serialVersionUID = 1L;


   public Splash() {
      getContentPane().setBackground(Color.WHITE);


      JPanel panel = (JPanel) getContentPane();

      ImageIcon img = new ImageIcon(Splash.class.getResource("/recursos/splash.png"));

      JLabel label = new JLabel(img);
      label.setBounds(0, 0, 519, 247);
      panel.add(label);


      setSize(519, 247);

      setLocationRelativeTo(null);


   }
}
