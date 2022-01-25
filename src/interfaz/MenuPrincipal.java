package interfaz;

import java.awt.Desktop;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import auxiliar.Utiles;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JTree;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

import converter.Convert;
import cu.edu.cujae.ceis.tree.TreeNode;
import cu.edu.cujae.ceis.tree.binary.BinaryTreeNode;
import logica.CorreoCujae;
import logica.ServArea;
import logica.ServCentral;
import logica.ServIntermedio;

import treenode.DisplayableTreeNode;

import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class MenuPrincipal extends JFrame {

   private static final long serialVersionUID = 1L;
   private JPanel contentPane;
   JTree tree = Convert.convertToJTree(CorreoCujae.getCorreoCujae().getEstructura());

   public MenuPrincipal() {

      setLocationRelativeTo(null);
      setIconImage(Toolkit.getDefaultToolkit().getImage(MenuPrincipal.class.getResource("/recursos/logo 0.png")));
      setResizable(false);
      setTitle("Men\u00FA Principial");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 859, 620);

      JMenuBar menuBar = new JMenuBar();
      setJMenuBar(menuBar);

      JMenu mnNewMenu = new JMenu("Archivo");
      mnNewMenu.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
      menuBar.add(mnNewMenu);

      JMenu mnAadir = new JMenu("A\u00F1adir");
      mnNewMenu.add(mnAadir);

      JMenuItem mntmServidorIntermedio = new JMenuItem("Servidor Intermedio");
      mntmServidorIntermedio.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            AgregarServIntermedio serInter = new AgregarServIntermedio();
            serInter.setModal(true);
            serInter.setLocationRelativeTo(null);
            serInter.setVisible(true);
            dispose();
         }
      });
      mnAadir.add(mntmServidorIntermedio);

      JMenuItem mntmServidorDeArea = new JMenuItem("Servidor de Area");
      mntmServidorDeArea.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            AgregarServArea servArea = new AgregarServArea();
            servArea.setModal(true);
            servArea.setLocationRelativeTo(null);
            servArea.setVisible(true);
            dispose();
         }
      });
      mnAadir.add(mntmServidorDeArea);

      JMenuItem mntmModifi = new JMenuItem("Modificar");
      mntmModifi.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            DisplayableTreeNode node = (DisplayableTreeNode) tree.getLastSelectedPathComponent();
            if (node != null) {
               BinaryTreeNode<Object> Servidor = node.getNode();
               Object s = Servidor.getInfo();
               if (s instanceof ServCentral)
                  JOptionPane.showMessageDialog(MenuPrincipal.this, " El Servidor Central no se puede modificar", "ERROR", JOptionPane.ERROR_MESSAGE);
               else if (s instanceof ServIntermedio) {
                  ModificarServIntermedio dialog = new ModificarServIntermedio(s);
                  dialog.setLocationRelativeTo(null);
                  dialog.setModal(true);
                  dialog.setVisible(true);
                  dispose();
               } else {
                  ModificarServArea dialog = new ModificarServArea(s);
                  dialog.setLocationRelativeTo(null);
                  dialog.setModal(true);
                  dialog.setVisible(true);
                  dispose();
               }
            } else
               JOptionPane.showMessageDialog(MenuPrincipal.this, "Seleccione un Servidor");
         }
      });
      mnNewMenu.add(mntmModifi);

      JMenuItem mntmNewMenuItem = new JMenuItem("Salir");
      mntmNewMenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            System.exit(0);
         }
      });

      JMenuItem mntmDelete = new JMenuItem("Eliminar");
      mntmDelete.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {

            DisplayableTreeNode node = (DisplayableTreeNode) tree.getLastSelectedPathComponent();
            if (node != null) {
               BinaryTreeNode<Object> Servidor = node.getNode();
               if (Servidor.getInfo() instanceof ServCentral)
                  JOptionPane.showMessageDialog(MenuPrincipal.this, " El Servidor Central no se puede eliminar", "ERROR", JOptionPane.ERROR_MESSAGE);
               else {
                  JOptionPane.showMessageDialog(MenuPrincipal.this, "El Servidor se ha eliminado correctamente ");
                  CorreoCujae.getCorreoCujae().getEstructura().deleteNode(Servidor);
                  actualizar();
                  MenuPrincipal.this.dispose();
               }
            } else
               JOptionPane.showMessageDialog(MenuPrincipal.this, "Seleccione un Servidor");
         }
      });
      mnNewMenu.add(mntmDelete);
      mnNewMenu.add(mntmNewMenuItem);

      JMenu mnNewMenu_1 = new JMenu("Mostrar");
      mnNewMenu_1.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/com/sun/java/swing/plaf/windows/icons/UpFolder.gif")));
      menuBar.add(mnNewMenu_1);

      JMenuItem mntmInformacinDelServidor = new JMenuItem("Informaci\u00F3n del Servidor");
      mntmInformacinDelServidor.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {

            DisplayableTreeNode node = (DisplayableTreeNode) tree.getLastSelectedPathComponent();
            if (node != null) {
               BinaryTreeNode<Object> Servidor = node.getNode();
               Object s = Servidor.getInfo();
               if (s instanceof ServIntermedio)
                  JOptionPane.showMessageDialog(MenuPrincipal.this, "   SERVIDOR INTERMEDIO \n" + "NumSerie: " + ((ServIntermedio) s).getNumSerie() + "\nNumIP: "
                          + ((ServIntermedio) s).getNumIP() + "\nMarca: " + ((ServIntermedio) s).getMarca()
                          + "\nCantMaxCorreos: " + ((ServIntermedio) s).getCantMaxCorreo());
               else if (s instanceof ServArea)

                  JOptionPane.showMessageDialog(MenuPrincipal.this, "   SERVIDOR DE AREA \n" + "Nombre de Area: " + ((ServArea) s).getNombre() + "\nCantidad de Cuentas: " + ((ServArea) s).getCantCuentas());
               else
                  JOptionPane.showMessageDialog(MenuPrincipal.this, "   SERVIDOR CENTRAL \n" + "NumSerie: " + ((ServCentral) s).getNumSerie() + "\nNumIP: "
                          + ((ServCentral) s).getNumIP() + "\nMarca: " + ((ServCentral) s).getMarca()
                          + "\nCantMaxCorreos: " + ((ServCentral) s).getCantMaxCorreo());
            } else
               JOptionPane.showMessageDialog(MenuPrincipal.this, "Seleccione un Servidor");
         }
      });
      mnNewMenu_1.add(mntmInformacinDelServidor);

      JMenuItem mntmMostrarServidoresDe = new JMenuItem("Servidores de Area ordenados");
      mntmMostrarServidoresDe.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {

            PrintWriter pw2 = null;
            try {

               FileWriter fw2 = new FileWriter("ServidoresAreaIS.txt");
               BufferedWriter bw2 = new BufferedWriter(fw2);
               pw2 = new PrintWriter(bw2);
               ArrayList<TreeNode<Object>> leaves = CorreoCujae.getCorreoCujae().InsertionSort();
               for (int i = 0; i < leaves.size(); i++) {
                  BinaryTreeNode<Object> auxiliar = (BinaryTreeNode<Object>) leaves.get(i);
                  Object aux = auxiliar.getInfo();
                  if (aux instanceof ServArea)
                     pw2.println(i + 1 + " - Servidor:" + ((ServArea) ((BinaryTreeNode<Object>) leaves.get(i)).getInfo()).getNombre() + "  Cantidad de Cuentas: \n" + ((ServArea) ((BinaryTreeNode<Object>) leaves.get(i)).getInfo()).getCantCuentas());
               }

            } catch (IOException e1) {

            } finally {
               pw2.close();
            }
            try {
               Desktop.getDesktop().open(new File("ServidoresAreaIS.txt"));
            } catch (IOException | IllegalArgumentException e1) {
               JOptionPane.showMessageDialog(MenuPrincipal.this, "FICHERO NO ENCONTRADO!!!.");
            }
         }
      });
      mnNewMenu_1.add(mntmMostrarServidoresDe);

      JMenu mnAcercaDe = new JMenu("Acerca de");
      mnAcercaDe.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/com/sun/java/swing/plaf/windows/icons/HomeFolder.gif")));
      menuBar.add(mnAcercaDe);

      JMenuItem mntmAyuda = new JMenuItem("Ayuda");
      mntmAyuda.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            try {
               Desktop.getDesktop().open(new File("LEEME.txt"));
            } catch (IOException | IllegalArgumentException e1) {
               JOptionPane.showMessageDialog(MenuPrincipal.this, "FICHERO NO ENCONTRADO!!!.");
            }
         }
      });
      mnAcercaDe.add(mntmAyuda);

      JMenuItem mntmDesarrolladoresDelSoftware = new JMenuItem("Desarrolladores del Software");
      mntmDesarrolladoresDelSoftware.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(MenuPrincipal.this, "Est. Adrian E Gil");
         }
      });
      mnAcercaDe.add(mntmDesarrolladoresDelSoftware);

      JMenuItem mntmVersinDelSoftware = new JMenuItem("Versi\u00F3n del Software");
      mntmVersinDelSoftware.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(MenuPrincipal.this, "Versión 1.0");
         }
      });
      mnAcercaDe.add(mntmVersinDelSoftware);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(null);

      JLabel lblNewLabel = new JLabel("Estructutura");
      lblNewLabel.setFont(new Font("Arial", Font.BOLD, 40));
      lblNewLabel.setBounds(508, 11, 253, 37);
      contentPane.add(lblNewLabel);

      JLabel lblNewLabel_1 = new JLabel("Servidores de Correos");
      lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 40));
      lblNewLabel_1.setBounds(415, 59, 427, 37);
      contentPane.add(lblNewLabel_1);

      JScrollPane scrollPane_1 = new JScrollPane();
      scrollPane_1.setBounds(10, 11, 395, 548);
      contentPane.add(scrollPane_1);

      scrollPane_1.setViewportView(tree);

      JLabel lblNewLabel_Foto = new JLabel("");
      lblNewLabel_Foto.setBounds(447, 131, 395, 428);

      lblNewLabel_Foto.setIcon(Utiles.imagenAjustadaDefin(395, 428, "/recursos/cujae.png", Image.SCALE_AREA_AVERAGING));

      contentPane.add(lblNewLabel_Foto);
      tree.setVisible(true);
   }

   public static void actualizar() {
      MenuPrincipal menu = new MenuPrincipal();
      menu.setLocationRelativeTo(null);
      menu.setVisible(true);
   }
}
