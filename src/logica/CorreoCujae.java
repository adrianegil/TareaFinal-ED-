package logica;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;

import cu.edu.cujae.ceis.tree.TreeNode;
import cu.edu.cujae.ceis.tree.binary.BinaryTreeNode;
import cu.edu.cujae.ceis.tree.general.GeneralTree;
import cu.edu.cujae.ceis.tree.iterators.general.InBreadthIterator;

public class CorreoCujae {

   private GeneralTree<Object> estructura;

   public CorreoCujae() {
      super();
      estructura = new GeneralTree<>();
   }

   public GeneralTree<Object> getEstructura() {
      return estructura;
   }

   public void setEstructura(GeneralTree<Object> estructura) {
      this.estructura = estructura;
   }

   private static CorreoCujae CorCujae;

   public static CorreoCujae getCorreoCujae() {
      if (CorCujae == null) {
         CorCujae = new CorreoCujae();
      }
      return CorCujae;
   }

   //// FUNCIONALIDADES ///


   // INSERTAR
   public void InsertarServidorCentral(Servidor s) {
      BinaryTreeNode<Object> root = new BinaryTreeNode<Object>(s);
      estructura.insertNode(root, null);
   }

   public void InsertarServidorIntermedio(Servidor s) {
      BinaryTreeNode<Object> son = new BinaryTreeNode<Object>(s);
      estructura.insertNode(son, (BinaryTreeNode<Object>) estructura.getRoot());
   }

   public void InsertarServidorArea(String numSerie, ServArea s) {
      ArrayList<BinaryTreeNode<Object>> sons = (ArrayList<BinaryTreeNode<Object>>) estructura.getSons((BinaryTreeNode<Object>) estructura.getRoot());
      BinaryTreeNode<Object> son = new BinaryTreeNode<Object>(s);
      for (int i = 0; i < sons.size(); i++) {
         if (((ServIntermedio) sons.get(i).getInfo()).getNumSerie().equalsIgnoreCase(numSerie)) {
            estructura.insertNode(son, sons.get(i));
         }
      }
   }


   // MODIFICAR
   public void ModificarServIntermedio(String serie, ServIntermedio ser) {

      boolean encontrado = false;
      String NumSerie = ser.getNumSerie();
      String Marca = ser.getMarca();
      String NumIP = ser.getNumIP();
      int CanCorreos = ser.getCantMaxCorreo();

      InBreadthIterator<Object> it = estructura.inBreadthIterator();

      while (it.hasNext() != encontrado) {
         BinaryTreeNode<Object> obj = it.nextNode();

         if (obj.getInfo() instanceof ServIntermedio) {
            if (((ServIntermedio) obj.getInfo()).getNumSerie().equalsIgnoreCase(serie)) {
               ((ServIntermedio) obj.getInfo()).setNumSerie(NumSerie);
               ((ServIntermedio) obj.getInfo()).setMarca(Marca);
               ((ServIntermedio) obj.getInfo()).setNumIP(NumIP);
               ((ServIntermedio) obj.getInfo()).setCantMaxCorreo(CanCorreos);

               encontrado = true;
            }
         }
      }
   }

   public void ModificarServArea(String area, ServArea ser) {

      boolean encontrado = false;
      String NombArea = ser.getNombre();
      int CanCuentas = ser.getCantCuentas();
      InBreadthIterator<Object> it = estructura.inBreadthIterator();

      while (it.hasNext() && !encontrado) {

         BinaryTreeNode<Object> obj = it.nextNode();

         if (obj.getInfo() instanceof ServArea) {

            if (((ServArea) obj.getInfo()).getNombre().equalsIgnoreCase(area)) {
               ((ServArea) obj.getInfo()).setNombre(NombArea);
               ((ServArea) obj.getInfo()).setCantCuentas(CanCuentas);
               encontrado = true;
            }
         }
      }
   }


   // VALIDACIONES
   public static boolean validarIp(String ip) {

      boolean result = true;
      for (int i = 0; i < ip.length(); i++) {

         if (ip.charAt(i) != '1' && ip.charAt(i) != '2' && ip.charAt(i) != '3' && ip.charAt(i) != '4' && ip.charAt(i) != '5' && ip.charAt(i) != '6' && ip.charAt(i) != '7' && ip.charAt(i) != '8' && ip.charAt(i) != '9' && ip.charAt(i) != '0' && ip.charAt(i) != '.') {
            result = false;
         }
         if (ip.charAt(i) == '.' && ip.charAt(i + 1) == '.')
            result = false;
      }
      return result;
   }

   public static boolean validarInt(String num) {

      boolean result = true;
      String aux = num;

      for (int i = 0; i < aux.length(); i++) {
         if (aux.charAt(i) != '1' && aux.charAt(i) != '2' && aux.charAt(i) != '3' && aux.charAt(i) != '4' && aux.charAt(i) != '5' && aux.charAt(i) != '6' && aux.charAt(i) != '7' && aux.charAt(i) != '8' && aux.charAt(i) != '9' && aux.charAt(i) != '0') {
            result = false;
         }
      }
      return result;
   }

   public static boolean validarString(String nombre) {

      boolean result = true;

      for (int i = 0; i < nombre.length(); i++) {
         if (nombre.charAt(i) == '1' || nombre.charAt(i) == '2' || nombre.charAt(i) == '3' || nombre.charAt(i) == '4' || nombre.charAt(i) == '5' || nombre.charAt(i) == '6' || nombre.charAt(i) == '7' || nombre.charAt(i) == '8' || nombre.charAt(i) == '9' || nombre.charAt(i) == '0') {
            result = false;
         }
      }
      return result;
   }

   public boolean buscarSerIntermedio(String ip, String numSerie) {

      boolean encontrado = false;
      ArrayList<BinaryTreeNode<Object>> sons = (ArrayList<BinaryTreeNode<Object>>) estructura.getSons((BinaryTreeNode<Object>) estructura.getRoot());
      int i = 0;

      while (i < sons.size() && !encontrado) {
         if (((ServIntermedio) sons.get(i).getInfo()).getNumSerie().equalsIgnoreCase(numSerie) || ((ServIntermedio) sons.get(i).getInfo()).getNumIP().equalsIgnoreCase(ip)) {
            encontrado = true;
         }
         i++;
      }
      return encontrado;
   }

   public boolean buscarServArea(String area) {

      boolean encontrado = false;
      ArrayList<TreeNode<Object>> leaves = (ArrayList<TreeNode<Object>>) estructura.getLeaves();
      int i = 0;
      while (i < leaves.size() && !encontrado) {
         BinaryTreeNode<Object> auxiliar = (BinaryTreeNode<Object>) leaves.get(i);
         Object aux = auxiliar.getInfo();
         if (aux instanceof ServArea)
            if (((ServArea) ((BinaryTreeNode<Object>) auxiliar).getInfo()).getNombre().equalsIgnoreCase(area)) {
               encontrado = true;
            }
         i++;
      }
      return encontrado;
   }


   // AUXILIARES
   public LinkedList<String> ServInterNumSerie() {

      LinkedList<String> result = new LinkedList<String>();
      ArrayList<BinaryTreeNode<Object>> ServInter = (ArrayList<BinaryTreeNode<Object>>) estructura.getSons((BinaryTreeNode<Object>) estructura.getRoot());
      Iterator<BinaryTreeNode<Object>> it = ServInter.iterator();

      while (it.hasNext()) {
         BinaryTreeNode<Object> s = it.next();
         result.add(((ServIntermedio) s.getInfo()).getNumSerie());
      }
      return result;
   }


   // INICIAR DATOS
   public void iniciarDatos() {

      ServCentral ServidorCentral = new ServCentral("sc101", "samsung", "10.8.176.6", 255);
      InsertarServidorCentral(ServidorCentral);

      ServIntermedio ServidorIntermedio = new ServIntermedio("si100", "haier", "10.8.181.0", 100);
      InsertarServidorIntermedio(ServidorIntermedio);

      ServArea ServidorArea = new ServArea("biblioteca", 20);
      InsertarServidorArea("si100", ServidorArea);

      ServArea ServidorArea1 = new ServArea("economía", 15);
      InsertarServidorArea("si100", ServidorArea1);

      ServArea ServidorArea2 = new ServArea("dirección", 30);
      InsertarServidorArea("si100", ServidorArea2);

      ServIntermedio ServidorIntermedio1 = new ServIntermedio("si101", "hp", "10.8.181.1", 130);
      InsertarServidorIntermedio(ServidorIntermedio1);

      ServArea ServidorArea3 = new ServArea("telecomunicaciones", 25);
      InsertarServidorArea("si101", ServidorArea3);

      ServArea ServidorArea4 = new ServArea("automática", 35);
      InsertarServidorArea("si101", ServidorArea4);

      ServIntermedio ServidorIntermedio2 = new ServIntermedio("si102", "haier", "10.8.181.2", 125);
      InsertarServidorIntermedio(ServidorIntermedio2);

      ServArea ServidorArea5 = new ServArea("hidraulica", 25);
      InsertarServidorArea("si102", ServidorArea5);

      ServArea ServidorArea6 = new ServArea("civil", 10);
      InsertarServidorArea("si102", ServidorArea6);

      ServIntermedio ServidorIntermedio3 = new ServIntermedio("si103", "lg", "10.8.181.3", 115);
      InsertarServidorIntermedio(ServidorIntermedio3);

      ServArea ServidorArea7 = new ServArea("informática", 25);
      InsertarServidorArea("si103", ServidorArea7);

      ServArea ServidorArea8 = new ServArea("mecánica", 40);
      InsertarServidorArea("si103", ServidorArea8);

      ServArea ServidorArea9 = new ServArea("biomédica", 30);
      InsertarServidorArea("si103", ServidorArea9);

      ServIntermedio ServidorIntermedio34 = new ServIntermedio("si104", "asus", "10.8.181.4", 110);
      InsertarServidorIntermedio(ServidorIntermedio34);

      ServArea ServidorArea74 = new ServArea("arquitectura", 20);
      InsertarServidorArea("si104", ServidorArea74);

      ServArea ServidorArea84 = new ServArea("industrial", 45);
      InsertarServidorArea("si104", ServidorArea84);

      ServArea ServidorArea94 = new ServArea("química", 42);
      InsertarServidorArea("si104", ServidorArea94);

      ServArea ServidorArea904 = new ServArea("rectorado", 45);
      InsertarServidorArea("si104", ServidorArea904);
   }


   // FUNCIONES DE ORDENAMIENTO

   public ArrayList<TreeNode<Object>> InsertionSort() {

      ArrayList<TreeNode<Object>> leaves = (ArrayList<TreeNode<Object>>) estructura.getLeaves();
      ArrayList<TreeNode<Object>> leaves2 = new ArrayList<TreeNode<Object>>();

      for (int h = 0; h < leaves.size(); h++) {
         BinaryTreeNode<Object> auxiliar2 = (BinaryTreeNode<Object>) leaves.get(h);
         if (auxiliar2.getInfo() instanceof ServArea)
            leaves2.add(auxiliar2);
      }
      int j;
      TreeNode<Object> aux;
      for (int i = 1; i < leaves2.size(); i++) {
         aux = leaves2.get(i);
         j = i;
         while (j - 1 >= 0 && ((ServArea) ((BinaryTreeNode<Object>) aux).getInfo()).getCantCuentas() < ((ServArea) ((BinaryTreeNode<Object>) leaves2.get(j - 1)).getInfo()).getCantCuentas()) {
            leaves2.set(j, leaves2.get(j - 1));
            j = j - 1;
         }
         leaves2.set(j, aux);
      }
      return leaves2;
   }

   public ArrayList<TreeNode<Object>> Burbuja() {

      ArrayList<TreeNode<Object>> leaves = (ArrayList<TreeNode<Object>>) estructura.getLeaves();
      ArrayList<TreeNode<Object>> leaves2 = new ArrayList<TreeNode<Object>>();

      for (int h = 0; h < leaves.size(); h++) {
         BinaryTreeNode<Object> auxiliar2 = (BinaryTreeNode<Object>) leaves.get(h);
         if (auxiliar2.getInfo() instanceof ServArea)
            leaves2.add(auxiliar2);
      }


      for (int i = 0; i < leaves2.size(); i++) {
         for (int j = i + 1; j < leaves2.size(); j++) {
            if (((ServArea) ((BinaryTreeNode<Object>) leaves2.get(i)).getInfo()).getCantCuentas() > ((ServArea) ((BinaryTreeNode<Object>) leaves2.get(j)).getInfo()).getCantCuentas()) {
               BinaryTreeNode<Object> auxiliar = (BinaryTreeNode<Object>) leaves2.get(i);
               leaves2.set(i, leaves2.get(j));
               leaves2.set(j, auxiliar);
            }
         }
      }
      return leaves2;
   }

   public ArrayList<TreeNode<Object>> ShellSort() {

      ArrayList<TreeNode<Object>> leaves = (ArrayList<TreeNode<Object>>) estructura.getLeaves();
      ArrayList<TreeNode<Object>> leaves2 = new ArrayList<TreeNode<Object>>();

      for (int h = 0; h < leaves.size(); h++) {
         BinaryTreeNode<Object> auxiliar2 = (BinaryTreeNode<Object>) leaves.get(h);
         if (auxiliar2.getInfo() instanceof ServArea)
            leaves2.add(auxiliar2);
      }
      int salto, i, j, k;
      salto = leaves2.size() / 2;

      while (salto > 0) {
         for (i = salto; i < leaves2.size(); i++) {
            j = i - salto;
            while (j >= 0) {
               k = j + salto;
               if (((ServArea) ((BinaryTreeNode<Object>) leaves2.get(j)).getInfo()).getCantCuentas() < ((ServArea) ((BinaryTreeNode<Object>) leaves2.get(k)).getInfo()).getCantCuentas()) {
                  j = -1;
               } else {
                  BinaryTreeNode<Object> auxiliar = (BinaryTreeNode<Object>) leaves2.get(j);
                  leaves2.set(j, leaves2.get(k));
                  leaves2.set(k, auxiliar);
                  j -= salto;
               }
            }
         }
         salto = salto / 2;
      }
      return leaves2;

   }


   // ELIMINAR

   public void EliminarServidorArea(String name, int cantC) {
      boolean found = false;
      InBreadthIterator<Object> it = estructura.inBreadthIterator();
      while (it.hasNext() && !found) {
         BinaryTreeNode<Object> serv = it.nextNode();
         if (serv.getInfo() instanceof ServArea) {
            if (((ServArea) serv.getInfo()).getNombre().equalsIgnoreCase(name)) {
               if (((ServArea) serv.getInfo()).getCantCuentas() == cantC) {
                  estructura.deleteNode(serv);
                  found = true;
               }
            }
         }
      }
   }

   public void EliminarServidorIntermedio(String numeroSerie, String ip) {
      InBreadthIterator<Object> it = estructura.inBreadthIterator();
      boolean found = false;
      while (it.hasNext() && !found) {
         BinaryTreeNode<Object> serv = it.nextNode();
         if (serv.getInfo() instanceof ServIntermedio) {
            if (((ServIntermedio) serv.getInfo()).getNumSerie().equalsIgnoreCase(numeroSerie)) {
               if (((ServIntermedio) serv.getInfo()).getNumSerie().equalsIgnoreCase(ip)) {
                  estructura.deleteNode(serv);
                  found = true;
               }
            }
         }
      }
   }


   // PREGUNTA ESCRITA (OBTENER MARCA DE LOS EQUIPOS DE LOS SERV Q MAS CORREOS PUEDAN PROCESAR POR MINUTO)

   public ArrayList<String> marcas() {

      ArrayList<String> result = new ArrayList<>();
      InBreadthIterator<Object> it = estructura.inBreadthIterator();
      int mayor = 0;

      while (it.hasNext()) {
         BinaryTreeNode<Object> nodo = it.nextNode();
         if (nodo.getInfo() instanceof ServCentral || nodo.getInfo() instanceof ServIntermedio) {
            if (((Servidor) nodo.getInfo()).getCantMaxCorreo() > mayor) {
               result.clear();
               result.add(((Servidor) nodo.getInfo()).getMarca());
               mayor = ((Servidor) nodo.getInfo()).getCantMaxCorreo();
            } else if (((Servidor) nodo.getInfo()).getCantMaxCorreo() == mayor) {
               result.add(((Servidor) nodo.getInfo()).getMarca());
            }
         }
      }
      return result;
   }
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	