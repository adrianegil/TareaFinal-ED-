package interfaz;

import javax.swing.JFrame;
import java.awt.Panel;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JPasswordField;

import auxiliar.HiloBarra;
import auxiliar.Seguridad;
import auxiliar.Utiles;
import inicial.Splash;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.Toolkit;
import javax.swing.JProgressBar;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JPanel;


public class Autenticar extends JFrame {

   private static final long serialVersionUID = 1L;
   private JPasswordField passwordFieldContraseña;
   private JButton btnAceptar;
   private JButton btnCancelar;
   private JTextField textField;
   public static JProgressBar progressBar;


   public Autenticar() {

      setLocationRelativeTo(null);
      setIconImage(Toolkit.getDefaultToolkit().getImage(Autenticar.class.getResource("/com/sun/java/swing/plaf/windows/icons/Warn.gif")));
      setResizable(false);
      setTitle("Informaci\u00F3n de Usuario");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 488, 356);
      getContentPane().setLayout(null);

      progressBar = new JProgressBar();
      progressBar.setVisible(false);
      progressBar.addChangeListener(new ChangeListener() {
         public void stateChanged(ChangeEvent e) {
            if (progressBar.getValue() == 100) {
               MenuPrincipal menu = new MenuPrincipal();
               menu.setLocationRelativeTo(null);
               menu.setVisible(true);
               Autenticar.this.dispose();
            }
         }
      });
      progressBar.setStringPainted(true);
      progressBar.setBounds(0, 314, 482, 14);
      getContentPane().add(progressBar);

      Panel panel = new Panel();
      panel.setBounds(0, 291, 482, 23);
      getContentPane().add(panel);
      panel.setLayout(new GridLayout(0, 2, 0, 0));
      JLabel lblCargando = new JLabel("Cargando...");
      lblCargando.setVisible(false);
      lblCargando.setBounds(211, 270, 76, 20);
      getContentPane().add(lblCargando);
      ImageIcon img = new ImageIcon(Splash.class.getResource("/recursos/logo 0.png"));
      JLabel labelImg = new JLabel(img);
      labelImg.setBounds(174, 101, 135, 96);
      labelImg.setIcon(Utiles.imagenAjustadaDefin(135, 96, "/recursos/logo 0.png", Image.SCALE_AREA_AVERAGING));
      getContentPane().add(labelImg);

      btnAceptar = new JButton("Aceptar");
      btnAceptar.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            String usuario = textField.getText();
            String contrasenna = passwordFieldContraseña.getText();
            Seguridad aux = new Seguridad();
            if (aux.acceso(usuario, contrasenna)) {
               progressBar.setVisible(true);
               lblCargando.setVisible(true);
               HiloBarra barr = new HiloBarra();
               barr.start();
            } else
               JOptionPane.showMessageDialog(Autenticar.this, "Usted no tiene acceso al sistema de Servidores", "Error", JOptionPane.ERROR_MESSAGE);
         }
      });
      panel.add(btnAceptar);
      getRootPane().setDefaultButton(btnAceptar);
      btnCancelar = new JButton("Cerrar");
      btnCancelar.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            System.exit(0);
         }
      });
      panel.add(btnCancelar);

      JLabel lblNewLabel_2 = new JLabel("Sistema de Servidores");
      lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 26));
      lblNewLabel_2.setBounds(112, 11, 286, 52);
      getContentPane().add(lblNewLabel_2);

      JLabel lblNewLabel_3 = new JLabel(" CUJAE");
      lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 25));
      lblNewLabel_3.setBounds(187, 57, 109, 28);
      getContentPane().add(lblNewLabel_3);

      JPanel panel_1 = new JPanel();
      panel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
      panel_1.setBounds(23, 208, 437, 63);
      panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 204, 0)), "Credenciales de Acceso", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));

      getContentPane().add(panel_1);
      panel_1.setLayout(null);

      JLabel lblNewLabel = new JLabel("Administrador de Red:");
      lblNewLabel.setBounds(10, 24, 135, 28);
      panel_1.add(lblNewLabel);
      lblNewLabel.setFont(new Font("Arial", Font.BOLD, 12));

      textField = new JTextField();
      textField.setBounds(139, 29, 103, 20);
      panel_1.add(textField);
      textField.setColumns(10);

      JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
      lblContrasea.setBounds(252, 28, 95, 20);
      panel_1.add(lblContrasea);
      lblContrasea.setFont(new Font("Arial", Font.BOLD, 12));

      passwordFieldContraseña = new JPasswordField();
      passwordFieldContraseña.setBounds(326, 29, 103, 20);
      panel_1.add(passwordFieldContraseña);
   }
}
