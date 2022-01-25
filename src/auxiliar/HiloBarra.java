package auxiliar;

import interfaz.Autenticar;

public class HiloBarra extends Thread {


   public void run() {

      for (int i = 0; i <= 100; i++) {
         Autenticar.progressBar.setValue(i);
         try {
            Thread.sleep(30);
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
   }
}
