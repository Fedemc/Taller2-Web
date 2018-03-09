package sistema.grafica.ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.BoxLayout;
import javax.swing.JToolBar;
import javax.swing.JInternalFrame;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;
import java.awt.SystemColor;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ListSelectionModel;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.event.MouseMotionAdapter;
import java.rmi.RemoteException;

import sistema.excepciones.AlumnoException;
import sistema.grafica.controladores.ContVentAlumnoDet;
import sistema.grafica.controladores.ContVentanaModificarAlumno;
import sistema.grafica.ventanas.*;
import sistema.logica.valueObjects.VOAlumnoDetallado;
import sistema.logica.valueObjects.VOBecadoDetallado;
import javax.swing.JTextArea;

public class VentanaModificarAlumno3 {

	private JFrame frmModificarDatosDel;
	private JTextField txtCI;
	private ContVentanaModificarAlumno miCont;
	
	private JTextArea txtArea;
	private JTextField textDomic;
	private JTextField textemail;
	private JTextField textTel;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaModificarAlumno3 window = new VentanaModificarAlumno3();
					window.frmModificarDatosDel.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaModificarAlumno3() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		miCont = new ContVentanaModificarAlumno(this);
		frmModificarDatosDel = new JFrame();
		frmModificarDatosDel.setTitle("Modificar datos del Alumno");
		frmModificarDatosDel.getContentPane().setEnabled(false);
		frmModificarDatosDel.setBounds(100, 100, 514, 486);
		frmModificarDatosDel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmModificarDatosDel.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ingrese Cedula");
		lblNewLabel.setBounds(35, 30, 112, 14);
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblNewLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		frmModificarDatosDel.getContentPane().add(lblNewLabel);
		
		txtCI = new JTextField();
		txtCI.setBounds(157, 27, 117, 20);

		frmModificarDatosDel.getContentPane().add(txtCI);
		txtCI.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(114, 11, 244, 20);
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 20));
		frmModificarDatosDel.getContentPane().add(lblNewLabel_1);
		
		
		
		JButton btnMostrarDatos = new JButton("Mostrar Datos");
		btnMostrarDatos.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnMostrarDatos.setBounds(284, 26, 145, 23);
		
		btnMostrarDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//aca llamo al metodo q valida el campo
				validarCamposYGenerarListado();
			}
		});
		frmModificarDatosDel.getContentPane().add(btnMostrarDatos);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnCancelar.setBounds(284, 413, 145, 23);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmModificarDatosDel.dispose();
			}
		});
		frmModificarDatosDel.getContentPane().add(btnCancelar);		
		
		txtArea = new JTextArea();
		txtArea.setFont(new Font("Calibri", Font.PLAIN, 16));
		txtArea.setEditable(false);
		txtArea.setBounds(157, 76, 272, 220);
		frmModificarDatosDel.getContentPane().add(txtArea);
		
		JLabel label = new JLabel("Tel\u00E9fono");
		label.setFont(new Font("Calibri", Font.PLAIN, 16));
		label.setBounds(56, 383, 72, 14);
		frmModificarDatosDel.getContentPane().add(label);
		
		textDomic = new JTextField();
		textDomic.setColumns(10);
		textDomic.setBounds(157, 318, 272, 20);
		frmModificarDatosDel.getContentPane().add(textDomic);
		
		JLabel label_1 = new JLabel("Direcci\u00F3n");
		label_1.setFont(new Font("Calibri", Font.PLAIN, 16));
		label_1.setBounds(56, 321, 78, 14);
		frmModificarDatosDel.getContentPane().add(label_1);
		
		textemail = new JTextField();
		textemail.setColumns(10);
		textemail.setBounds(157, 349, 272, 20);
		frmModificarDatosDel.getContentPane().add(textemail);
		
		JLabel label_2 = new JLabel("Email");
		label_2.setFont(new Font("Calibri", Font.PLAIN, 16));
		label_2.setBounds(56, 352, 66, 14);
		frmModificarDatosDel.getContentPane().add(label_2);
		
		textTel = new JTextField();
		textTel.setColumns(10);
		textTel.setBounds(157, 380, 117, 20);
		frmModificarDatosDel.getContentPane().add(textTel);
		
		JButton button = new JButton("Modificar datos");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cedula = txtCI.getText();
				String domicilio = textDomic.getText();
				String telefono = textTel.getText();
				String email = textemail.getText();
				
				if ((!(cedula.isEmpty())) && (!(telefono.isEmpty())) && (!(domicilio.isEmpty())) && (!(email.isEmpty()))) {
					long ced = longAString(cedula);
					int tel = intAString(telefono);
					miCont.modificarDatosAlumno(ced, domicilio, tel, email);
				}
				else {
					mostrarError("No se pueden dejar campos vacíos");
				}
			}
		});
		button.setFont(new Font("Calibri", Font.PLAIN, 16));
		button.setBounds(284, 379, 145, 23);
		frmModificarDatosDel.getContentPane().add(button);
	}
	
	public void setVisible(boolean valor)
	{
		frmModificarDatosDel.setVisible(valor);
	}
	
	private void validarCamposYGenerarListado()
	{
		if(!txtCI.getText().isEmpty())	//Si no esta vacio el campo CI
		{
			//Llamo al metodo del controlador de la ventana
			miCont.generarListado(Long.parseLong(txtCI.getText()));
		}
		else
		{
			JOptionPane.showMessageDialog(frmModificarDatosDel, "Ingrese una cedula de alumno", "ERROR!", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void mostrarVOAlumDet(VOAlumnoDetallado voa)
	{
		
		txtArea.setText(voa.toString());
		textDomic.setText(voa.getDomicilio());
		textemail.setText(voa.getDirCorreo());
		textTel.setText(String.valueOf(voa.getTelefono()));
	}
	
	public void mostrarVOBecDet(VOBecadoDetallado vob)
	{
		
		txtArea.setText(vob.toString());
		textDomic.setText(vob.getDomicilio());
		textemail.setText(vob.getDirCorreo());
		textTel.setText(String.valueOf(vob.getTelefono()));
		 
	}
	
	public void mostrarError(String res)
	{
		JOptionPane.showMessageDialog(frmModificarDatosDel, res, "Resultado", JOptionPane.ERROR_MESSAGE);
	}
	
	public long longAString(String s) {
		long cedula = 0;
		try {
			Long.parseLong(s);
			cedula = Long.parseLong(s);
		}
		catch (NumberFormatException e) {
		     JOptionPane.showMessageDialog(frmModificarDatosDel, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		return cedula;
	}
	
	public int intAString(String s) {
		int entero = 0;
		try {
			Integer.parseInt(s);
		    entero = Integer.parseInt(s);
		}
		catch (NumberFormatException e) {
		     JOptionPane.showMessageDialog(frmModificarDatosDel, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		return entero;
	}
	
	public void mostrarResultado(String res)
	{
		JOptionPane.showMessageDialog(frmModificarDatosDel, res, "Resultado", JOptionPane.INFORMATION_MESSAGE);
		limpiarCampos();
	}
	
	public void limpiarCampos() {
		txtCI.setText("");
		textDomic.setText("");
		textTel.setText("");
		textemail.setText("");
		txtArea.setText("");
	}
}
