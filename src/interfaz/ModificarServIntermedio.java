package interfaz;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import logica.CorreoCujae;
import logica.ServIntermedio;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class ModificarServIntermedio extends JDialog {

   private JPanel contentPane;
   private Object ServInter;
   private JTextField textFieldNumSerie;
   private JTextField textFieldMarca;
   private JTextField textFieldIP;
   private JTextField textFieldCantCorreos;


   public Object getServInter() {
      return ServInter;
   }

   public void setServInter(Object servInter) {
      ServInter = servInter;
   }


   public ModificarServIntermedio(Object s) {

      setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
      setIconImage(Toolkit.getDefaultToolkit().getImage(ModificarServIntermedio.class.getResource("/recursos/logo 0.png")));
      setResizable(false);
      setTitle("Modificar Servidor Intermedio");
      this.ServInter = s;
      setLocationRelativeTo(null);
      setBounds(100, 100, 510, 356);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(null);

      JLabel lblNewLabel = new JLabel("Intruduzca los nuevos datos del Servidor");
      lblNewLabel.setFont(new Font("Arial", Font.BOLD, 25));
      lblNewLabel.setBounds(10, 33, 494, 63);
      contentPane.add(lblNewLabel);

      JLabel label = new JLabel("N\u00FAmero de Serie:");
      label.setFont(new Font("Arial", Font.BOLD, 20));
      label.setBounds(10, 121, 165, 27);
      contentPane.add(label);

      JLabel label_1 = new JLabel("Marca del Equipo:");
      label_1.setFont(new Font("Arial", Font.BOLD, 20));
      label_1.setBounds(10, 159, 199, 27);
      contentPane.add(label_1);

      JLabel label_2 = new JLabel("N\u00FAmero de IP:");
      label_2.setFont(new Font("Arial", Font.BOLD, 20));
      label_2.setBounds(10, 197, 165, 27);
      contentPane.add(label_2);

      JLabel label_3 = new JLabel("Cantidad de Correos:");
      label_3.setFont(new Font("Arial", Font.BOLD, 20));
      label_3.setBounds(10, 235, 214, 27);
      contentPane.add(label_3);

      textFieldNumSerie = new JTextField();
      textFieldNumSerie.setColumns(10);
      textFieldNumSerie.setBounds(251, 124, 214, 27);
      contentPane.add(textFieldNumSerie);

      textFieldMarca = new JTextField();
      textFieldMarca.setColumns(10);
      textFieldMarca.setBounds(251, 162, 214, 27);
      contentPane.add(textFieldMarca);

      textFieldIP = new JTextField();
      textFieldIP.setColumns(10);
      textFieldIP.setBounds(251, 200, 214, 27);
      contentPane.add(textFieldIP);

      textFieldCantCorreos = new JTextField();
      textFieldCantCorreos.setColumns(10);
      textFieldCantCorreos.setBounds(251, 238, 214, 27);
      contentPane.add(textFieldCantCorreos);

      if (ServInter instanceof ServIntermedio) {
         textFieldNumSerie.setText(((ServIntermedio) ServInter).getNumSerie());
         textFieldMarca.setText(((ServIntermedio) ServInter).getMarca());
         textFieldIP.setText(((ServIntermedio) ServInter).getNumIP());
         textFieldCantCorreos.setText("" + ((ServIntermedio) ServInter).getCantMaxCorreo());
      }

      JButton btnModificar = new JButton("Modificar");
      btnModificar.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {

            if (!textFieldNumSerie.getText().isEmpty() && !textFieldMarca.getText().isEmpty() && !textFieldIP.getText().isEmpty() && !textFieldCantCorreos.getText().isEmpty()) {
               boolean IPOK = CorreoCujae.getCorreoCujae().validarIp(textFieldIP.getText());
               boolean CantCorreoOK = CorreoCujae.getCorreoCujae().getCorreoCujae().validarInt(textFieldCantCorreos.getText());
               boolean seguir = true;
               if (!IPOK) {
                  JOptionPane.showMessageDialog(ModificarServIntermedio.this, "El número de IP solo puede tener números y puntos y no puede tener más de un punto consecutivo");
                  seguir = false;
               }
               if (!CantCorreoOK) {
                  JOptionPane.showMessageDialog(ModificarServIntermedio.this, "La cantidad de correos no puede tener letras");
                  seguir = false;
               }
               if (seguir) {
                  ServIntermedio s = new ServIntermedio(textFieldNumSerie.getText(), textFieldMarca.getText(), textFieldIP.getText(), Integer.parseInt(textFieldCantCorreos.getText()));
                  CorreoCujae.getCorreoCujae().ModificarServIntermedio(((ServIntermedio) ServInter).getNumSerie(), s);
                  JOptionPane.showMessageDialog(ModificarServIntermedio.this, "Se ha modificado el Servidor Intermedio satisfactoriamente");
                  ModificarServIntermedio.this.dispose();
                  MenuPrincipal.actualizar();
               }
            } else
               JOptionPane.showMessageDialog(ModificarServIntermedio.this, "Todos los datos del Servidor deben de ser rellenados");
         }
      });
      btnModificar.setBounds(251, 288, 89, 23);
      contentPane.add(btnModificar);

      JButton button_1 = new JButton("Cancelar");
      button_1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            ModificarServIntermedio.this.dispose();
            MenuPrincipal.actualizar();
         }
      });
      button_1.setBounds(376, 288, 89, 23);
      contentPane.add(button_1);
   }
}
