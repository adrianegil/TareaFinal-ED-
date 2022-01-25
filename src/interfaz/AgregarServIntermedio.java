package interfaz;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import logica.CorreoCujae;
import logica.ServIntermedio;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class AgregarServIntermedio extends JDialog {

   private JPanel contentPane;
   private JTextField textFieldNumSerie;
   private JTextField textFieldMarca;
   private JTextField textFieldIP;
   private JTextField textFieldCantCorreos;

   public AgregarServIntermedio() {

      setLocationRelativeTo(null);
      setIconImage(Toolkit.getDefaultToolkit().getImage(AgregarServIntermedio.class.getResource("/recursos/logo 0.png")));
      setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
      setResizable(false);
      setTitle("Datos del Servidor Intermedio");
      setBounds(100, 100, 435, 298);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(null);

      JLabel lblNewLabel = new JLabel("N\u00FAmero de Serie:");
      lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));
      lblNewLabel.setBounds(24, 37, 165, 27);
      contentPane.add(lblNewLabel);

      JLabel lblMa = new JLabel("Marca del Equipo:");
      lblMa.setFont(new Font("Arial", Font.BOLD, 18));
      lblMa.setBounds(24, 75, 165, 27);
      contentPane.add(lblMa);

      JLabel lblNmeroDeIp = new JLabel("N\u00FAmero de IP:");
      lblNmeroDeIp.setFont(new Font("Arial", Font.BOLD, 18));
      lblNmeroDeIp.setBounds(24, 107, 165, 27);
      contentPane.add(lblNmeroDeIp);

      JLabel lblCantidadDeCorreos = new JLabel("Cantidad de Correos:");
      lblCantidadDeCorreos.setFont(new Font("Arial", Font.BOLD, 18));
      lblCantidadDeCorreos.setBounds(24, 145, 199, 27);
      contentPane.add(lblCantidadDeCorreos);

      textFieldNumSerie = new JTextField();
      textFieldNumSerie.setBounds(233, 42, 165, 20);
      contentPane.add(textFieldNumSerie);
      textFieldNumSerie.setColumns(10);

      textFieldMarca = new JTextField();
      textFieldMarca.setBounds(233, 80, 165, 20);
      contentPane.add(textFieldMarca);
      textFieldMarca.setColumns(10);

      textFieldIP = new JTextField();
      textFieldIP.setBounds(233, 112, 165, 20);
      contentPane.add(textFieldIP);
      textFieldIP.setColumns(10);

      textFieldCantCorreos = new JTextField();
      textFieldCantCorreos.setBounds(233, 150, 165, 20);
      contentPane.add(textFieldCantCorreos);
      textFieldCantCorreos.setColumns(10);

      JButton btnNewButton = new JButton("Agregar");
      btnNewButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {

            if (!textFieldNumSerie.getText().isEmpty() && !textFieldMarca.getText().isEmpty() && !textFieldIP.getText().isEmpty() && !textFieldCantCorreos.getText().isEmpty()) {
               boolean IPOK = CorreoCujae.getCorreoCujae().validarIp(textFieldIP.getText());
               boolean CantCorreoOK = CorreoCujae.getCorreoCujae().getCorreoCujae().validarInt(textFieldCantCorreos.getText());
               boolean seguir = true;

               if (!IPOK) {
                  JOptionPane.showMessageDialog(AgregarServIntermedio.this, "El número de IP solo puede tener números y puntos y no puede tener más de un punto consecutivo");
                  seguir = false;
               }
               if (!CantCorreoOK) {
                  JOptionPane.showMessageDialog(AgregarServIntermedio.this, "La cantidad de correos no puede tener letras");
                  seguir = false;
               }
               if (CorreoCujae.getCorreoCujae().buscarSerIntermedio(textFieldIP.getText(), textFieldNumSerie.getText())) {

                  JOptionPane.showMessageDialog(AgregarServIntermedio.this, "Ya existe un Servidor Intermedio con ese número de serie o ese número de IP(POR FAVOR CAMBIELO)");
                  textFieldNumSerie.setText("");
                  textFieldIP.setText("");
                  seguir = false;
               }
               if (seguir) {
                  ServIntermedio s = new ServIntermedio(textFieldNumSerie.getText(), textFieldMarca.getText(), textFieldIP.getText(), Integer.parseInt(textFieldCantCorreos.getText()));
                  CorreoCujae.getCorreoCujae().InsertarServidorIntermedio(s);
                  JOptionPane.showMessageDialog(AgregarServIntermedio.this, "Se ha introducido un nuevo Servidor Intermedio");
                  MenuPrincipal.actualizar();
                  AgregarServIntermedio.this.dispose();
               }
            } else
               JOptionPane.showMessageDialog(AgregarServIntermedio.this, "Se debe introducir todos los datos del Servidor");
         }
      });
      btnNewButton.setBounds(233, 233, 89, 23);
      contentPane.add(btnNewButton);

      JButton btnNewButton_1 = new JButton("Cancelar");
      btnNewButton_1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            MenuPrincipal.actualizar();
            AgregarServIntermedio.this.dispose();
         }
      });
      btnNewButton_1.setBounds(326, 233, 89, 23);
      contentPane.add(btnNewButton_1);
   }
}
