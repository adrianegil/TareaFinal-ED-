package logica;

public class ServIntermedio extends Servidor {

   public ServIntermedio(String numSerie, String marca, String numIP, int cantMaxCorreo) {
      super(numSerie, marca, numIP, cantMaxCorreo);
   }

   public String toString() {
      return " Serv. Intermedio: " + this.getNumSerie();
   }
}
