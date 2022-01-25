package logica;

public class ServCentral extends Servidor {

   public ServCentral(String numSerie, String marca, String numIP, int cantMaxCorreo) {
      super(numSerie, marca, numIP, cantMaxCorreo);
   }

   public String toString() {
      return "Serv. Central: " + this.getNumSerie();
   }
}
