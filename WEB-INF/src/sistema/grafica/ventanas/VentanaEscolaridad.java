package sistema.grafica.ventanas;

import java.awt.EventQueue;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import sistema.grafica.controladores.ContVentanaEscolaridad;
import sistema.logica.valueObjects.VOInscripcion;
import sistema.logica.valueObjects.VOInscripcionDetallada;
import sistema.logica.valueObjects.VOInscripciones;
import java.util.ArrayList;
import javax.swing.JScrollPane;


public class VentanaEscolaridad {

	private JFrame frmConsultaDeEscolaridad;
	private JTextField txtCI;
	private JTable tblDatos;
	
	ContVentanaEscolaridad contVentanaEsc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaEscolaridad window = new VentanaEscolaridad();
					window.frmConsultaDeEscolaridad.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaEscolaridad() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmConsultaDeEscolaridad = new JFrame();
		frmConsultaDeEscolaridad.setTitle("Consulta de escolaridad");
		frmConsultaDeEscolaridad.setBounds(100, 100, 678, 464);
		frmConsultaDeEscolaridad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmConsultaDeEscolaridad.getContentPane().setLayout(null);
		
		JLabel lblIngreseCedula = new JLabel("Ingrese cedula");
		lblIngreseCedula.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblIngreseCedula.setBounds(30, 44, 103, 14);
		frmConsultaDeEscolaridad.getContentPane().add(lblIngreseCedula);
		
		txtCI = new JTextField();
		txtCI.setBounds(143, 41, 97, 20);
		frmConsultaDeEscolaridad.getContentPane().add(txtCI);
		txtCI.setColumns(10);
		
		JButton btnParcial = new JButton("Listado PARCIAL");
		btnParcial.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnParcial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//validarDatos
				if(validarDatos())
				{
					listadoParcial();
				}
				else
				{
					JOptionPane.showMessageDialog(frmConsultaDeEscolaridad, "Debe ingresar un dato en el campo de la cedula","Error", JOptionPane.ERROR_MESSAGE);
				}		
			}
		});
		btnParcial.setBounds(250, 40, 167, 23);
		frmConsultaDeEscolaridad.getContentPane().add(btnParcial);
		
		JButton btnCompleto = new JButton("Listado COMPLETO");
		btnCompleto.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnCompleto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//validarDatos
				if(validarDatos())
				{
					listadoCompleto();
				}
				else
				{
					JOptionPane.showMessageDialog(frmConsultaDeEscolaridad, "Debe ingresar un dato en el campo de la cedula","Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnCompleto.setBounds(427, 40, 167, 23);
		frmConsultaDeEscolaridad.getContentPane().add(btnCompleto);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmConsultaDeEscolaridad.dispose();
			}
		});
		btnCancelar.setBounds(448, 377, 167, 23);
		frmConsultaDeEscolaridad.getContentPane().add(btnCancelar);
		
		tblDatos = new JTable();
		tblDatos.setEnabled(false);
		tblDatos.setBounds(31, 121, 584, 288);
		frmConsultaDeEscolaridad.getContentPane().add(tblDatos);
		
		JScrollPane scrollPane = new JScrollPane(tblDatos);
		scrollPane.setBounds(30, 78, 585, 288);
		frmConsultaDeEscolaridad.getContentPane().add(scrollPane);
		
		
		
		
		
		
		
		contVentanaEsc=new ContVentanaEscolaridad(this);
	}

	public void setVisible(boolean valor)
	{
		frmConsultaDeEscolaridad.setVisible(valor);
	}
	
	public boolean validarDatos()
	{
		return (!txtCI.getText().isEmpty());
	}
	
	public void listadoCompleto()
	{
		VOInscripciones voIns=new VOInscripciones();
		//Paso 2do parametro en true porque indica que el listado es completo
		voIns=contVentanaEsc.crearListadoEscolaridad(Long.parseLong(txtCI.getText()), true);
		
		ArrayList<VOInscripcion> arrayInscripciones=voIns.getVOInscripcionesArray();
		
		DefaultTableModel modelo=new DefaultTableModel();
		
		modelo.addColumn("NroInscripcion");
		modelo.addColumn("Asignatura");
		modelo.addColumn("Año lectivo");
		modelo.addColumn("Calificación");	
		modelo.addColumn("Monto base");
		Object rowData[]= new Object[5];
		
		
		for(VOInscripcion voInsDec: arrayInscripciones)
		{
			rowData[0] = voInsDec.getNumero();
			rowData[1] = voInsDec.getNombreAsignatura();
			rowData[2] = voInsDec.getAnioLectivo();
			rowData[3] = voInsDec.getCalificacion();
			rowData[4] = ((VOInscripcionDetallada)voInsDec).getMontoBase();
			modelo.addRow(rowData);
			
		}
		
		tblDatos.setModel(modelo);
		
		
	}
	
	public void listadoParcial()
	{
		VOInscripciones voIns=new VOInscripciones();
		//Paso 2do parametro en false porque indica que el listado es parcial
		voIns=contVentanaEsc.crearListadoEscolaridad(Long.parseLong(txtCI.getText()), false);
		
		ArrayList<VOInscripcion> arrayInscripciones=voIns.getVOInscripcionesArray();
		
		DefaultTableModel modelo=new DefaultTableModel();
		modelo.addColumn("NroInscripcion");
		modelo.addColumn("Asignatura");
		modelo.addColumn("Año lectivo");
		modelo.addColumn("Calificación");
		Object rowData[]= new Object[4];
		
		for(VOInscripcion voInsDec: arrayInscripciones)
		{
			rowData[0] = voInsDec.getNumero();
			rowData[1] = voInsDec.getNombreAsignatura();
			rowData[2] = voInsDec.getAnioLectivo();
			rowData[3] = voInsDec.getCalificacion();
			modelo.addRow(rowData);
		}
		
		tblDatos.setModel(modelo);
	}
	
	public void mostrarError(String res)
	{
		JOptionPane.showMessageDialog(frmConsultaDeEscolaridad, res, "Error", JOptionPane.ERROR_MESSAGE);
	}
	public boolean isCellEditable(int rowIndex, int colIndex) {
        return false; 
    }
}
