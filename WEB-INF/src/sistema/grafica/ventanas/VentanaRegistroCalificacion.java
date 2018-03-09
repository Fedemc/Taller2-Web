package sistema.grafica.ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import sistema.excepciones.AlumnoException;
import sistema.excepciones.InscripcionException;
import sistema.grafica.controladores.ContRegistroCalificacion;

import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;

public class VentanaRegistroCalificacion {

	private JFrame frmRegistroDeCalificacin;
	private JTextField textFieldCedulaAlumno;
	private JTextField textFieldCalificacion;
	private JTextField textFieldNroInscripcion;
	private ContRegistroCalificacion contRegCalif;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRegistroCalificacion window = new VentanaRegistroCalificacion();
					window.frmRegistroDeCalificacin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaRegistroCalificacion() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		
		frmRegistroDeCalificacin = new JFrame();
		frmRegistroDeCalificacin.setTitle("Registro de calificaci\u00F3n");
		frmRegistroDeCalificacin.setBounds(100, 100, 396, 215);
		frmRegistroDeCalificacin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRegistroDeCalificacin.getContentPane().setLayout(null);
		
		contRegCalif = new ContRegistroCalificacion(this);
		
		JLabel lblCedulaDelAlumno = new JLabel("Cedula del alumno");
		lblCedulaDelAlumno.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblCedulaDelAlumno.setBounds(57, 35, 143, 14);
		frmRegistroDeCalificacin.getContentPane().add(lblCedulaDelAlumno);
		
		textFieldCedulaAlumno = new JTextField();
		textFieldCedulaAlumno.setBounds(210, 32, 112, 20);
		frmRegistroDeCalificacin.getContentPane().add(textFieldCedulaAlumno);
		textFieldCedulaAlumno.setColumns(10);
		
		JLabel lblCalificacin = new JLabel("Calificaci\u00F3n");
		lblCalificacin.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblCalificacin.setBounds(57, 97, 143, 14);
		frmRegistroDeCalificacin.getContentPane().add(lblCalificacin);
		
		textFieldCalificacion = new JTextField();
		textFieldCalificacion.setBounds(210, 94, 112, 20);
		frmRegistroDeCalificacin.getContentPane().add(textFieldCalificacion);
		textFieldCalificacion.setColumns(10);
		
		JLabel lblNroDeInscripcin = new JLabel("Nro. de inscripci\u00F3n");
		lblNroDeInscripcin.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblNroDeInscripcin.setBounds(57, 66, 143, 14);
		frmRegistroDeCalificacin.getContentPane().add(lblNroDeInscripcin);
		
		textFieldNroInscripcion = new JTextField();
		textFieldNroInscripcion.setBounds(210, 63, 112, 20);
		frmRegistroDeCalificacin.getContentPane().add(textFieldNroInscripcion);
		textFieldNroInscripcion.setColumns(10);
		
		
		
		JButton btnRegistrarCalificacin = new JButton("Registrar");
		btnRegistrarCalificacin.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnRegistrarCalificacin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ced = textFieldCedulaAlumno.getText();
				String nroIns = textFieldNroInscripcion.getText();
				String nota = textFieldCalificacion.getText();
				
				if (ced.isEmpty() || nroIns.isEmpty() || nota.isEmpty()) {
					JOptionPane.showMessageDialog(frmRegistroDeCalificacin, "No se pueden dejar campos vacíos", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
				}
				else {
					try {
						long ci = Long.parseLong(ced);
						int nroInsc = Integer.parseInt(nroIns);
						int calif = Integer.parseInt(nota);
						contRegCalif.registrarCalificacion(ci, nroInsc, calif);
					} catch (RemoteException | AlumnoException | InscripcionException e1) {
						JOptionPane.showMessageDialog(frmRegistroDeCalificacin, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}					
				}				
			}
		});
		
		btnRegistrarCalificacin.setBounds(88, 125, 112, 23);
		frmRegistroDeCalificacin.getContentPane().add(btnRegistrarCalificacin);
		
		JButton btnCancelarYVolver = new JButton("Cancelar");
		btnCancelarYVolver.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnCancelarYVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmRegistroDeCalificacin.dispose();
			}
		});
		btnCancelarYVolver.setBounds(210, 125, 112, 23);
		frmRegistroDeCalificacin.getContentPane().add(btnCancelarYVolver);
		
		frmRegistroDeCalificacin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public void setVisible(boolean valor)
	{
		frmRegistroDeCalificacin.setVisible(valor);
	}

	public void mostrarError(String res)
	{
		JOptionPane.showMessageDialog(frmRegistroDeCalificacin, res, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	public void mostrarResultado(String res)
	{
		
		JOptionPane.showMessageDialog(frmRegistroDeCalificacin, res, "Resultado", JOptionPane.INFORMATION_MESSAGE);
		frmRegistroDeCalificacin.dispose();
	}
	
}
