package auxiliar;

public class Seguridad {
   private final String nombreUsuario = "GilSoft";
   private final String contrasenna = "1234";

   public final boolean acceso(String usuario, String contrasenna) {
      return this.nombreUsuario.equalsIgnoreCase(usuario) && this.contrasenna.equalsIgnoreCase(contrasenna);
   }
}
