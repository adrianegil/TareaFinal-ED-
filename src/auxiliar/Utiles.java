package auxiliar;

import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class Utiles {

   public static ImageIcon imagenAjustada(JComponent component, String imgURL, int scaleImgAlgorit) {
      return new ImageIcon(Toolkit.getDefaultToolkit().getImage(Utiles.class.getClass().getResource(imgURL)).getScaledInstance(component.getWidth(), component.getHeight(), scaleImgAlgorit));
   }

   public static ImageIcon imagenAjustadaDefin(int compoWidth, int compoHeight, String imgURL, int scaleImgAlgorit) {
      return new ImageIcon(Toolkit.getDefaultToolkit().getImage(Utiles.class.getClass().getResource(imgURL)).getScaledInstance(compoWidth, compoHeight, scaleImgAlgorit));
   }
}
