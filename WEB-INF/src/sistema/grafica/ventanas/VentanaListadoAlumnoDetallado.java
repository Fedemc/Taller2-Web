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
import sistema.grafica.ventanas.*;
import sistema.logica.valueObjects.VOAlumnoDetallado;
import sistema.logica.valueObjects.VOBecadoDetallado;
import javax.swing.JTextArea;

public class VentanaListadoAlumnoDetallado {

	private JFrame frame;
	private JTextField txtCI;
	private ContVentAlumnoDet miCont;
	private JTextArea txtArea;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaListadoAlumnoDetallado window = new VentanaListadoAlumnoDetallado();
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
	public VentanaListadoAlumnoDetallado() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		miCont = new ContVentAlumnoDet(this);
		frame = new JFrame();
		frame.getContentPane().setEnabled(false);
		frame.setBounds(100, 100, 535, 413);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ingrese Cedula");
		lblNewLabel.setBounds(22, 59, 112, 14);
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblNewLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		frame.getContentPane().add(lblNewLabel);
		
		txtCI = new JTextField();
		txtCI.setBounds(157, 56, 127, 20);

		frame.getContentPane().add(txtCI);
		txtCI.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Listado detallado del Alumno");
		lblNewLabel_1.setBounds(114, 11, 262, 20);
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 20));
		frame.getContentPane().add(lblNewLabel_1);
		
		
		
		JButton btnMostrarDatos = new JButton("Mostrar Datos");
		btnMostrarDatos.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnMostrarDatos.setBounds(294, 55, 157, 23);
		
		btnMostrarDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//aca llamo al metodo q valida el campo
				validarCamposYGenerarListado();
			}
		});
		frame.getContentPane().add(btnMostrarDatos);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnCancelar.setBounds(294, 302, 157, 23);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		frame.getContentPane().add(btnCancelar);		
		
		txtArea = new JTextArea();
		txtArea.setFont(new Font("Calibri", Font.PLAIN, 16));
		txtArea.setEditable(false);
		txtArea.setBounds(22, 87, 262, 203);
		frame.getContentPane().add(txtArea);
	}
	
	public void setVisible(boolean valor)
	{
		frame.setVisible(valor);
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
			JOptionPane.showMessageDialog(frame, "Ingrese una cedula de alumno", "ERROR!", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void mostrarVOAlumDet(VOAlumnoDetallado voa)
	{
		//txtArea.setText("Cedula: " + voa.getCedula() + "\nNombre: " + voa.getNombre() + "\nApellido: " + voa.getApellido() + "\nDomicilio: " + voa.getDomicilio() + "\nCorreo electronico: " + voa.getDirCorreo() + "\nCuotaMensual: " + voa.getCuotaMensual() + "\nTipo de alumno: Comun");
		txtArea.setText(voa.toString());
	}
	
	public void mostrarVOBecDet(VOBecadoDetallado vob)
	{
		//txtArea.setText("Cedula: " + vob.getCedula() + "\nNombre: " + vob.getNombre() + "\nApellido: " + vob.getApellido() + "\nDomicilio: " + vob.getDomicilio() + "\nCorreo electronico: " + vob.getDirCorreo() + "\nCuotaMensual: " + vob.getCuotaMensual() + "\nTipo de alumno: Becado" + "\nDescuento: " + vob.getPorcentaje() + "\nDescripcion de beca:" + vob.getDescripcion());
		txtArea.setText(vob.toString());
	}
	
	public void mostrarError(String res)
	{
		JOptionPane.showMessageDialog(frame, res, "Resultado", JOptionPane.ERROR_MESSAGE);
	}
}
