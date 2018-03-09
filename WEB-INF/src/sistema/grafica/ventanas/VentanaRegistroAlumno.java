package sistema.grafica.ventanas;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.event.*;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import sistema.grafica.controladores.ContVentanaRegistroAlumno;
import java.awt.Font;

public class VentanaRegistroAlumno {

	private JFrame frame;
	private JTextField txtCI;
	private JTextField txtNom;
	private JTextField txtApe;
	private JTextField txtDom;
	private JTextField txtTel;
	private JTextField txtMail;
	private JTextField txtDescuento;
	private JTextField txtDescripcion;
	private JCheckBox checkBecado;
	boolean becado=false;
	
	private ContVentanaRegistroAlumno contVent;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRegistroAlumno window = new VentanaRegistroAlumno();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaRegistroAlumno() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Registro de alumno");
		frame.setBounds(100, 100, 716, 357);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblIngreseLosDatos = new JLabel("Ingrese los datos del alumno en los campos");
		lblIngreseLosDatos.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblIngreseLosDatos.setBounds(34, 38, 295, 20);
		frame.getContentPane().add(lblIngreseLosDatos);
		
		JLabel lblCedula = new JLabel("Cedula");
		lblCedula.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblCedula.setBounds(34, 88, 67, 14);
		frame.getContentPane().add(lblCedula);
		
		txtCI = new JTextField();
		txtCI.setBounds(100, 85, 128, 20);
		frame.getContentPane().add(txtCI);
		txtCI.setColumns(10);
		
		JLabel lblNom = new JLabel("Nombre");
		lblNom.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblNom.setBounds(238, 88, 73, 14);
		frame.getContentPane().add(lblNom);
		
		txtNom = new JTextField();
		txtNom.setBounds(304, 85, 128, 20);
		frame.getContentPane().add(txtNom);
		txtNom.setColumns(10);
		
		JLabel lblApe = new JLabel("Apellido");
		lblApe.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblApe.setBounds(466, 88, 73, 14);
		frame.getContentPane().add(lblApe);
		
		txtApe = new JTextField();
		txtApe.setBounds(536, 85, 128, 20);
		frame.getContentPane().add(txtApe);
		txtApe.setColumns(10);
		
		JLabel lblDomicilio = new JLabel("Domicilio");
		lblDomicilio.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblDomicilio.setBounds(34, 119, 82, 14);
		frame.getContentPane().add(lblDomicilio);
		
		txtDom = new JTextField();
		txtDom.setBounds(100, 116, 332, 20);
		frame.getContentPane().add(txtDom);
		txtDom.setColumns(10);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblTelefono.setBounds(466, 119, 67, 14);
		frame.getContentPane().add(lblTelefono);
		
		txtTel = new JTextField();
		txtTel.setBounds(536, 113, 128, 20);
		frame.getContentPane().add(txtTel);
		txtTel.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblEmail.setBounds(34, 150, 53, 14);
		frame.getContentPane().add(lblEmail);
		
		txtMail = new JTextField();
		txtMail.setBounds(100, 147, 332, 20);
		frame.getContentPane().add(txtMail);
		txtMail.setColumns(10);
		
		JLabel lblDescuento = new JLabel("Descuento");
		lblDescuento.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblDescuento.setBounds(34, 209, 82, 14);
		lblDescuento.setVisible(false);
		frame.getContentPane().add(lblDescuento);
		
		txtDescuento = new JTextField();
		txtDescuento.setBounds(126, 206, 128, 20);
		frame.getContentPane().add(txtDescuento);
		txtDescuento.setColumns(10);
		//
		txtDescuento.setVisible(false);
		
		JLabel lblDescripcion = new JLabel("Descripci\u00F3n");
		lblDescripcion.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblDescripcion.setBounds(277, 209, 90, 14);
		lblDescripcion.setVisible(false);
		frame.getContentPane().add(lblDescripcion);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(377, 206, 287, 20);
		frame.getContentPane().add(txtDescripcion);
		txtDescripcion.setColumns(10);
		txtDescripcion.setVisible(false);
		
		JCheckBox checkBecado = new JCheckBox("Es Becado");
		checkBecado.setFont(new Font("Calibri", Font.PLAIN, 16));
		checkBecado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkBecado.isSelected()) {
					txtDescripcion.setVisible(true);
					txtDescuento.setVisible(true);
					lblDescripcion.setVisible(true);
					lblDescuento.setVisible(true);
					becado=true;
				}
				else {
					txtDescripcion.setVisible(false);
					txtDescuento.setVisible(false);
					lblDescripcion.setVisible(false);
					lblDescuento.setVisible(false);
					becado = false;
				}
			}
		});
		checkBecado.setBounds(532, 146, 97, 23);
		frame.getContentPane().add(checkBecado);
		
		JButton btnRegistrarAlumno = new JButton("Registrar");
		btnRegistrarAlumno.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnRegistrarAlumno.setBounds(398, 284, 128, 23);
		btnRegistrarAlumno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//llamar al validar y registrar
				validarDatosYRegistrarAlumno();
			}
		});
		frame.getContentPane().add(btnRegistrarAlumno);
		
		JButton btnVolverALa = new JButton("Cancelar");
		btnVolverALa.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnVolverALa.setBounds(536, 284, 128, 23);
		btnVolverALa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		frame.getContentPane().add(btnVolverALa);
		
		
		
		JLabel lblNewLabel_1 = new JLabel("(Marque la opcion si es becado)");
		lblNewLabel_1.setBounds(466, 168, 198, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		contVent=new ContVentanaRegistroAlumno(this);
		
		
	}
	
	public void setVisible(boolean valor)
	{
		frame.setVisible(valor);
	}
	
	private void validarDatosYRegistrarAlumno()
	{
		//Verificar campos vacios, si todo OK llamar al ContVent.regAlu();, ver si es becado!
		//Verificar el valor del rdbtn
		//boolean validaciones=!txtCI.getText().isEmpty() && !txtNom.getText().isEmpty() && !txtApe.getText().isEmpty() && !txtDom.getText().isEmpty() && !txtTel.getText().isEmpty() && !txtMail.getText().isEmpty();
		//Validaciones para becado, agrego al chequeo
		if(becado)
		{
			if(!txtCI.getText().isEmpty() && !txtNom.getText().isEmpty() && !txtApe.getText().isEmpty() && !txtDom.getText().isEmpty() && !txtTel.getText().isEmpty() && !txtMail.getText().isEmpty()&&!txtDescuento.getText().isEmpty() && !txtDescripcion.getText().isEmpty()){
				contVent.crearBecado(Long.parseLong(txtCI.getText()), txtNom.getText(), txtApe.getText(), txtDom.getText(), Integer.parseInt(txtTel.getText()), txtMail.getText(), Integer.parseInt(txtDescuento.getText()), txtDescripcion.getText());
			}else {
				mostrarError("Debe completar todos los campos.");
			}
			
		}else {
			if(!txtCI.getText().isEmpty() && !txtNom.getText().isEmpty() && !txtApe.getText().isEmpty() && !txtDom.getText().isEmpty() && !txtTel.getText().isEmpty() && !txtMail.getText().isEmpty()){
				contVent.crearAlumno(Long.parseLong(txtCI.getText()), txtNom.getText(), txtApe.getText(), txtDom.getText(), Integer.parseInt(txtTel.getText()), txtMail.getText());
			}else {
				mostrarError("Debe completar todos los campos.");
			}
			
		}
		
		/*
		if(validaciones)
		{
			//llamo al metodo del controlador pasandole los datos
			//Dos metodos, depende si es becado o no
			if(esBecado)
			{
				//cont crear becado
				contVent.crearBecado(Long.parseLong(txtCI.getText()), txtNom.getText(), txtApe.getText(), txtDom.getText(), Integer.parseInt(txtTel.getText()), txtMail.getText(), Integer.parseInt(txtDescuento.getText()), txtDescripcion.getText());
			}
			else
			{
				//cont crear alumno
				contVent.crearAlumno(Long.parseLong(txtCI.getText()), txtNom.getText(), txtApe.getText(), txtDom.getText(), Integer.parseInt(txtTel.getText()), txtMail.getText());
			}
		}
		else
		{
			mostrarError("Debe completar todos los campos.");
		}*/
	}
	
	public void mostrarError(String res)
	{
		JOptionPane.showMessageDialog(frame, res, "Resultado", JOptionPane.ERROR_MESSAGE);
	}
	
	public void mostrarResultado(String res)
	{
		JOptionPane.showMessageDialog(frame, res, "Resultado", JOptionPane.INFORMATION_MESSAGE);
		frame.dispose();
	}
}
