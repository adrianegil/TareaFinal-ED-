package logica;

public class ServArea {

   private String nombre;
   private int cantCuentas;

   public ServArea(String nombre, int cantCuentas) {
      super();
      this.nombre = nombre;
      this.cantCuentas = cantCuentas;
   }

   public String getNombre() {
      return nombre;
   }

   public void setNombre(String nombre) {
      this.nombre = nombre;
   }

   public int getCantCuentas() {
      return cantCuentas;
   }

   public void setCantCuentas(int cantCuentas) {
      this.cantCuentas = cantCuentas;
   }

   public String toString() {
      return "Serv. Area: " + this.getNombre();
   }
}
