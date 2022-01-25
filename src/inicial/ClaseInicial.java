package inicial;


import javax.swing.UIManager;

import auxiliar.GestorRecursos;
import interfaz.Autenticar;
import logica.CorreoCujae;

public class ClaseInicial {

   public static void main(String[] args) {

      CorreoCujae.getCorreoCujae().iniciarDatos();
      try {
         UIManager.setLookAndFeel("com.nilo.plaf.nimrod.NimRODLookAndFeel");
      } catch (Throwable e) {
         e.printStackTrace();
      }

      Splash sp = new Splash();
      sp.setVisible(true);

      try {
         GestorRecursos.T1.play();
         Thread.sleep(1800);
      } catch (Exception ex) {
         ex.printStackTrace();
      }

      sp.setVisible(false);
      GestorRecursos.T2.play();
      Autenticar frame = new Autenticar();
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
   }

}
