package sistema.grafica.ventanas;

import java.awt.EventQueue;
import javax.swing.JFrame;
import sistema.grafica.controladores.ContVentanaRegistroAsignatura;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class VentanaRegistroAsignatura {

	private JFrame frmRegistroDeAsignatura;
	private JTextField textFieldCodigoAsig;
	private JTextField textFieldNombreAsig;
	private JTextField textFieldDescripcionAsig;
	private JButton btnRegistrarAsignatura;
	private JButton btnCancelarYVolver;
	
	private ContVentanaRegistroAsignatura contVent;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRegistroAsignatura window = new VentanaRegistroAsignatura();
					window.frmRegistroDeAsignatura.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaRegistroAsignatura() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	
		
		frmRegistroDeAsignatura = new JFrame();
		frmRegistroDeAsignatura.setTitle("Registro de Asignatura");
		frmRegistroDeAsignatura.setBounds(100, 100, 583, 199);
		frmRegistroDeAsignatura.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRegistroDeAsignatura.getContentPane().setLayout(null);
		
		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblCodigo.setBounds(78, 27, 54, 14);
		frmRegistroDeAsignatura.getContentPane().add(lblCodigo);
		
		textFieldCodigoAsig = new JTextField();
		textFieldCodigoAsig.setBounds(153, 24, 137, 20);
		frmRegistroDeAsignatura.getContentPane().add(textFieldCodigoAsig);
		textFieldCodigoAsig.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblNombre.setBounds(324, 27, 54, 14);
		frmRegistroDeAsignatura.getContentPane().add(lblNombre);
		
		textFieldNombreAsig = new JTextField();
		textFieldNombreAsig.setBounds(388, 24, 137, 20);
		frmRegistroDeAsignatura.getContentPane().add(textFieldNombreAsig);
		textFieldNombreAsig.setColumns(10);
		
		JLabel lblDescripcin = new JLabel("Descripci\u00F3n");
		lblDescripcin.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblDescripcin.setBounds(43, 71, 89, 14);
		frmRegistroDeAsignatura.getContentPane().add(lblDescripcin);
		
		textFieldDescripcionAsig = new JTextField();
		textFieldDescripcionAsig.setBounds(153, 68, 374, 20);
		frmRegistroDeAsignatura.getContentPane().add(textFieldDescripcionAsig);
		textFieldDescripcionAsig.setColumns(10);
		
		btnRegistrarAsignatura = new JButton("Registrar");
		btnRegistrarAsignatura.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnRegistrarAsignatura.setBounds(247, 108, 131, 23);
		btnRegistrarAsignatura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(validarDatos()) {
					contVent.registrarAsignatura(textFieldCodigoAsig.getText(), textFieldNombreAsig.getText(), textFieldDescripcionAsig.getText());
					frmRegistroDeAsignatura.dispose();
				}else
					mostrarError("Debe completar todos los campos.");
			}
		});
		frmRegistroDeAsignatura.getContentPane().add(btnRegistrarAsignatura);
		
		btnCancelarYVolver = new JButton("Cancelar");
		btnCancelarYVolver.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnCancelarYVolver.setBounds(394, 108, 131, 23);
		btnCancelarYVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmRegistroDeAsignatura.dispose();
			}
		});
		frmRegistroDeAsignatura.getContentPane().add(btnCancelarYVolver);
		
		frmRegistroDeAsignatura.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		contVent = new ContVentanaRegistroAsignatura(this);
	}
	
	public void setVisible(boolean valor)
	{
		frmRegistroDeAsignatura.setVisible(valor);
	}
	
	public boolean validarDatos () {
		boolean ok = false;
		
		if(textFieldCodigoAsig.getText().isEmpty()||textFieldNombreAsig.getText().isEmpty()||textFieldDescripcionAsig.getText().isEmpty()) {
			ok=false;
		}else
			ok=true;
		return ok;
	}
	
	public void mostrarError(String res)
	{
		JOptionPane.showMessageDialog(frmRegistroDeAsignatura, res, "Resultado", JOptionPane.INFORMATION_MESSAGE);
	}
}
