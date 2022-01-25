package interfaz;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import logica.CorreoCujae;

import java.awt.Font;

import logica.ServArea;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class ModificarServArea extends JDialog {

   private JPanel contentPane;
   private JTextField textFieldNombreArea;
   private JTextField textFieldCanCuentas;
   private Object ServArea;

   public Object getServArea() {
      return ServArea;
   }

   public void setServArea(Object servArea) {
      ServArea = servArea;
   }

   public ModificarServArea(Object s) {

      setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
      setIconImage(Toolkit.getDefaultToolkit().getImage(ModificarServArea.class.getResource("/recursos/logo 0.png")));
      this.ServArea = s;
      setLocationRelativeTo(null);
      setTitle("Modificar Servidor de Area");
      setResizable(false);
      setBounds(100, 100, 448, 274);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(null);

      JPanel panel = new JPanel();
      panel.setLayout(null);
      panel.setBorder(new EmptyBorder(5, 5, 5, 5));
      panel.setBounds(10, 72, 432, 171);
      contentPane.add(panel);

      JLabel label = new JLabel("Nombre del Area:");
      label.setFont(new Font("Arial", Font.BOLD, 20));
      label.setBounds(10, 44, 171, 20);
      panel.add(label);

      JLabel label_1 = new JLabel("Cantidad de Cuentas:");
      label_1.setFont(new Font("Arial", Font.BOLD, 20));
      label_1.setBounds(10, 87, 227, 20);
      panel.add(label_1);

      textFieldNombreArea = new JTextField();
      textFieldNombreArea.setColumns(10);
      textFieldNombreArea.setBounds(269, 47, 153, 20);
      panel.add(textFieldNombreArea);

      textFieldCanCuentas = new JTextField();
      textFieldCanCuentas.setColumns(10);
      textFieldCanCuentas.setBounds(269, 90, 153, 20);
      panel.add(textFieldCanCuentas);

      if (s instanceof ServArea) {
         textFieldNombreArea.setText(((ServArea) s).getNombre());
         textFieldCanCuentas.setText("" + ((ServArea) s).getCantCuentas());
      }

      JButton btnModificar = new JButton("Modificar");
      btnModificar.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {

            if (!textFieldNombreArea.getText().isEmpty() && !textFieldCanCuentas.getText().isEmpty()) {
               boolean seguir = true;
               boolean NombreAreaOK = CorreoCujae.getCorreoCujae().validarString(textFieldNombreArea.getText());
               boolean CantCuentasOK = CorreoCujae.getCorreoCujae().validarInt(textFieldCanCuentas.getText());
               if (!NombreAreaOK) {
                  JOptionPane.showMessageDialog(ModificarServArea.this, "El nombre del Area no puede tener números");
                  seguir = false;
               }
               if (!CantCuentasOK) {
                  JOptionPane.showMessageDialog(ModificarServArea.this, "La cantidad máxima de cuentas del Area no puede tener letras");
                  seguir = false;
               }
               if (seguir) {
                  ServArea ser = new ServArea(textFieldNombreArea.getText(), Integer.parseInt(textFieldCanCuentas.getText()));
                  CorreoCujae.getCorreoCujae().ModificarServArea(((ServArea) s).getNombre(), ser);
                  JOptionPane.showMessageDialog(ModificarServArea.this, "Se ha modificado el Servidor de Area satisfactoriamente");
                  ModificarServArea.this.dispose();
                  MenuPrincipal.actualizar();
               }
            } else
               JOptionPane.showMessageDialog(ModificarServArea.this, "Se deben de llenar todos los datos");
         }
      });
      btnModificar.setBounds(228, 137, 89, 23);
      panel.add(btnModificar);

      JButton button_1 = new JButton("Cancelar");
      button_1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            ModificarServArea.this.dispose();
            MenuPrincipal.actualizar();
         }
      });
      button_1.setBounds(333, 137, 89, 23);
      panel.add(button_1);

      JLabel label_3 = new JLabel("Intruduzca los nuevos datos del Servidor");
      label_3.setFont(new Font("Arial", Font.BOLD, 22));
      label_3.setBounds(10, 11, 432, 99);
      contentPane.add(label_3);
   }
}
