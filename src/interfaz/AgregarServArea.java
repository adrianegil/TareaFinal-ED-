package interfaz;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import logica.CorreoCujae;
import logica.ServArea;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class AgregarServArea extends JDialog {

   private JPanel contentPane;
   private JTextField textFieldNombArea;
   private JTextField textFieldCantCuentas;

   public AgregarServArea() {

      setLocationRelativeTo(null);
      setIconImage(Toolkit.getDefaultToolkit().getImage(AgregarServArea.class.getResource("/recursos/logo 0.png")));
      setResizable(false);
      setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
      setTitle("Datos del Servidor de Area");
      setBounds(100, 100, 458, 263);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(null);

      JLabel lblNewLabel = new JLabel("Nombre del Area:");
      lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
      lblNewLabel.setBounds(10, 44, 171, 20);
      contentPane.add(lblNewLabel);

      JLabel lblCantidadDeCuentas = new JLabel("Cantidad de Cuentas:");
      lblCantidadDeCuentas.setFont(new Font("Arial", Font.BOLD, 20));
      lblCantidadDeCuentas.setBounds(10, 87, 227, 20);
      contentPane.add(lblCantidadDeCuentas);

      textFieldNombArea = new JTextField();
      textFieldNombArea.setBounds(269, 47, 166, 20);
      contentPane.add(textFieldNombArea);
      textFieldNombArea.setColumns(10);

      textFieldCantCuentas = new JTextField();
      textFieldCantCuentas.setBounds(269, 90, 166, 20);
      contentPane.add(textFieldCantCuentas);
      textFieldCantCuentas.setColumns(10);
      JComboBox<String> comboBoxIP = new JComboBox();
      comboBoxIP.setFont(new Font("Arial", Font.BOLD, 14));
      comboBoxIP.setModel(new DefaultComboBoxModel(new String[]{"N\u00FAmero de Serie"}));
      comboBoxIP.setBounds(285, 140, 150, 20);
      contentPane.add(comboBoxIP);

      LinkedList<String> NumSerie = CorreoCujae.getCorreoCujae().ServInterNumSerie();
      Iterator<String> it = NumSerie.iterator();
      while (it.hasNext()) {
         comboBoxIP.addItem(it.next());
      }

      JButton btnNewButton = new JButton("Agregar");
      btnNewButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {

            if (!textFieldNombArea.getText().isEmpty() && !textFieldCantCuentas.getText().isEmpty()) {
               boolean nombreok = CorreoCujae.getCorreoCujae().validarString(textFieldNombArea.getText());
               boolean cantCuentOK = CorreoCujae.getCorreoCujae().validarInt(textFieldCantCuentas.getText());
               String serie = comboBoxIP.getSelectedItem().toString();
               boolean seguir = true;

               if (!nombreok) {
                  JOptionPane.showMessageDialog(AgregarServArea.this, "El nombre del Area no puede tener números");
                  seguir = false;
               }
               if (!cantCuentOK) {
                  JOptionPane.showMessageDialog(AgregarServArea.this, "La cantidad máxima de cuentas del Area no puede tener letras");
                  seguir = false;
               }
               if (serie == "Número de Serie") {
                  JOptionPane.showMessageDialog(AgregarServArea.this, "Se debe elejir el número de serie del Servidor Intermedio al cual se va a anclar este Servidor de Area");
                  seguir = false;
               }
               if (CorreoCujae.getCorreoCujae().buscarServArea(textFieldNombArea.getText())) {
                  JOptionPane.showMessageDialog(AgregarServArea.this, "Ya existe un Servidor de Area con ese nombre(POR FAVOR CAMBIELO)");
                  textFieldNombArea.setText("");
                  seguir = false;
               }
               if (seguir) {
                  ServArea s = new ServArea(textFieldNombArea.getText(), Integer.parseInt(textFieldCantCuentas.getText()));
                  CorreoCujae.getCorreoCujae().InsertarServidorArea(comboBoxIP.getSelectedItem().toString(), s);
                  JOptionPane.showMessageDialog(AgregarServArea.this, "Se ha introducido un nuevo Servidor de Area");
                  MenuPrincipal.actualizar();
                  AgregarServArea.this.dispose();
               }
            } else
               JOptionPane.showMessageDialog(AgregarServArea.this, "Se deben introducir todos los datos");
         }
      });
      btnNewButton.setBounds(247, 201, 89, 23);
      contentPane.add(btnNewButton);

      JButton btnNewButton_1 = new JButton("Cancelar");
      btnNewButton_1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            MenuPrincipal.actualizar();
            AgregarServArea.this.dispose();
         }
      });
      btnNewButton_1.setBounds(346, 201, 89, 23);
      contentPane.add(btnNewButton_1);

      JLabel lblNewLabel_1 = new JLabel("Anclar a Servidor Intermedio:");
      lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 18));
      lblNewLabel_1.setBounds(10, 139, 265, 20);
      contentPane.add(lblNewLabel_1);

   }
}
