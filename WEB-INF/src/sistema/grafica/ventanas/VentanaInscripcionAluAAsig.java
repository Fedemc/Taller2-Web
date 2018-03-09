package sistema.grafica.ventanas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import sistema.grafica.controladores.ContVentanaListadoAluApe;

import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import sistema.grafica.controladores.ContVentanaInscripcionAluAAsig;
import java.awt.Font;

public class VentanaInscripcionAluAAsig {

	private JFrame frmInscripcinDeAlumno;
	private JTextField textFieldCedulaAlumno;
	private JTextField textFieldCodigoAsignatura;
	private JTextField textFieldMontoBase;
	private ContVentanaInscripcionAluAAsig contInscripcion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInscripcionAluAAsig window = new VentanaInscripcionAluAAsig();
					window.frmInscripcinDeAlumno.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaInscripcionAluAAsig() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmInscripcinDeAlumno = new JFrame();
		frmInscripcinDeAlumno.setTitle("Inscripci\u00F3n de alumno a asignatura");
		frmInscripcinDeAlumno.setBounds(100, 100, 405, 259);
		frmInscripcinDeAlumno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contInscripcion=new ContVentanaInscripcionAluAAsig(this);
		frmInscripcinDeAlumno.getContentPane().setLayout(null);
		
		JLabel lblCedulaDelAlumno = new JLabel("Cedula del alumno");
		lblCedulaDelAlumno.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblCedulaDelAlumno.setBounds(39, 48, 132, 14);
		frmInscripcinDeAlumno.getContentPane().add(lblCedulaDelAlumno);
		
		textFieldCedulaAlumno = new JTextField();
		textFieldCedulaAlumno.setBounds(192, 45, 113, 20);
		frmInscripcinDeAlumno.getContentPane().add(textFieldCedulaAlumno);
		textFieldCedulaAlumno.setColumns(10);
		
		JLabel lblCdigoDeAsignatura = new JLabel("C\u00F3digo de asignatura");
		lblCdigoDeAsignatura.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblCdigoDeAsignatura.setBounds(39, 79, 144, 14);
		frmInscripcinDeAlumno.getContentPane().add(lblCdigoDeAsignatura);
		
		textFieldCodigoAsignatura = new JTextField();
		textFieldCodigoAsignatura.setBounds(192, 76, 113, 20);
		frmInscripcinDeAlumno.getContentPane().add(textFieldCodigoAsignatura);
		textFieldCodigoAsignatura.setColumns(10);
		
		JLabel lblMontoBaseDe = new JLabel("Monto base");
		lblMontoBaseDe.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblMontoBaseDe.setBounds(39, 110, 123, 14);
		frmInscripcinDeAlumno.getContentPane().add(lblMontoBaseDe);
		
		textFieldMontoBase = new JTextField();
		textFieldMontoBase.setBounds(192, 107, 113, 20);
		frmInscripcinDeAlumno.getContentPane().add(textFieldMontoBase);
		textFieldMontoBase.setColumns(10);
		
		JButton btnInscribirAlumno = new JButton("Inscribir");
		btnInscribirAlumno.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnInscribirAlumno.setBounds(192, 138, 113, 23);
		frmInscripcinDeAlumno.getContentPane().add(btnInscribirAlumno);
		
		btnInscribirAlumno.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				String cedula = textFieldCedulaAlumno.getText();
				String codAsig = textFieldCodigoAsignatura.getText();
				String monto = textFieldMontoBase.getText();
				
				if ((cedula.isEmpty()) || (codAsig.isEmpty()) || (monto.isEmpty())) {
					JOptionPane.showMessageDialog(frmInscripcinDeAlumno, "No se pueden dejar campos vacíos", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
				}
				else {
					int montoBase = intAString(monto);
					long cedAlu = longAString(cedula);
					contInscripcion.inscribirAlumnoAsig(cedAlu, codAsig, montoBase);
				}
			}
		});
		
		JButton btnCancelarYVolver = new JButton("Cancelar");
		btnCancelarYVolver.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnCancelarYVolver.setBounds(192, 172, 113, 23);
		frmInscripcinDeAlumno.getContentPane().add(btnCancelarYVolver);
		
		btnCancelarYVolver.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				frmInscripcinDeAlumno.dispose();
			}
		});
		
		frmInscripcinDeAlumno.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public void setVisible(boolean valor)
	{
		frmInscripcinDeAlumno.setVisible(valor);
	}
	
	public int intAString(String s) {
		int montoBase = 0;
		try {
			Integer.parseInt(s);
		    montoBase = Integer.parseInt(s);
		}
		catch (NumberFormatException e) {
		     JOptionPane.showMessageDialog(frmInscripcinDeAlumno, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		return montoBase;
	}
	
	public long longAString(String s) {
		long cedula = 0;
		try {
			Long.parseLong(s);
			cedula = Long.parseLong(s);
		}
		catch (NumberFormatException e) {
		     JOptionPane.showMessageDialog(frmInscripcinDeAlumno, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		return cedula;
	}
	
	public void mostrarError(String res)
	{
		JOptionPane.showMessageDialog(frmInscripcinDeAlumno, res, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	public void mostrarResultado(String res)
	{
		
		JOptionPane.showMessageDialog(frmInscripcinDeAlumno, res, "Resultado", JOptionPane.INFORMATION_MESSAGE);
		frmInscripcinDeAlumno.dispose();
	}
}
