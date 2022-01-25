package logica;

public class Servidor {

   private String numSerie;
   private String marca;
   private String numIP;
   private int cantMaxCorreo;

   public Servidor(String numSerie, String marca, String numIP, int cantMaxCorreo) {
      super();
      this.numSerie = numSerie;
      this.marca = marca;
      this.numIP = numIP;
      this.cantMaxCorreo = cantMaxCorreo;
   }

   public String getNumSerie() {
      return numSerie;
   }

   public void setNumSerie(String numSerie) {
      this.numSerie = numSerie;
   }

   public String getMarca() {
      return marca;
   }

   public void setMarca(String marca) {
      this.marca = marca;
   }

   public String getNumIP() {
      return numIP;
   }

   public void setNumIP(String numIP) {
      this.numIP = numIP;
   }

   public int getCantMaxCorreo() {
      return cantMaxCorreo;
   }

   public void setCantMaxCorreo(int cantMaxCorreo) {
      this.cantMaxCorreo = cantMaxCorreo;
   }
}
