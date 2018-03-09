package sistema.grafica.ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import sistema.logica.valueObjects.VOAlumnos;
import sistema.logica.valueObjects.VOAlumno;
import sistema.logica.valueObjects.VOEgresadoPromedioCal;
import sistema.logica.valueObjects.VOEgresados;
import sistema.logica.valueObjects.VOInscripcion;
import sistema.logica.valueObjects.VOInscripcionDetallada;
import sistema.grafica.controladores.ContVentanaListadoEgresado;

public class VentanaListadoEgresados {

	private JFrame frmListadoDeEgresados;
	private JTable tblDatos;
	private ContVentanaListadoEgresado contVent;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaListadoEgresados window = new VentanaListadoEgresados();
					window.frmListadoDeEgresados.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaListadoEgresados() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmListadoDeEgresados = new JFrame();
		frmListadoDeEgresados.setTitle("Listado de egresados");
		frmListadoDeEgresados.setBounds(100, 100, 815, 488);
		frmListadoDeEgresados.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmListadoDeEgresados.getContentPane().setLayout(null);
		
		JLabel lblListadoDeEgresados = new JLabel("Listado de egresados");
		lblListadoDeEgresados.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblListadoDeEgresados.setBounds(31, 35, 155, 14);
		frmListadoDeEgresados.getContentPane().add(lblListadoDeEgresados);
		
		tblDatos = new JTable();
		tblDatos.setEnabled(false);
		tblDatos.setBounds(30, 104, 734, 278);
		frmListadoDeEgresados.getContentPane().add(tblDatos);
		
		JScrollPane scrollPane = new JScrollPane(tblDatos);
		scrollPane.setBounds(30, 119, 734, 288);
		frmListadoDeEgresados.getContentPane().add(scrollPane);
		
		JButton btnNewButton = new JButton("Listado PARCIAL");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contVent.generarListado(true);
			}
		});
		btnNewButton.setBounds(196, 31, 144, 23);
		frmListadoDeEgresados.getContentPane().add(btnNewButton);
		
		JButton btnListadoCompleto = new JButton("Listado COMPLETO");
		btnListadoCompleto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//llamo al generar completo
				contVent.generarListado(false);
			}
		});
		btnListadoCompleto.setBounds(196, 65, 144, 23);
		frmListadoDeEgresados.getContentPane().add(btnListadoCompleto);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmListadoDeEgresados.dispose();
			}
		});
		btnSalir.setBounds(620, 415, 144, 23);
		frmListadoDeEgresados.getContentPane().add(btnSalir);
		
		contVent=new ContVentanaListadoEgresado(this);
	}
	public void setVisible(boolean valor)
	{
		frmListadoDeEgresados.setVisible(valor);
	}
	
	public void mostrarVOEgrParcial(VOAlumnos voAls)
	{
		//me traigo el VO de Alumnos y lo recorro, tiro cada dato a la tabla
		DefaultTableModel modelo=new DefaultTableModel();
		modelo.addColumn("Apellido");
		modelo.addColumn("Nombre");
		modelo.addColumn("Cedula");
		Object rowData[]= new Object[3];
		
		for(VOAlumno voAl: voAls.getVOAlumnosArray())
		{
			rowData[0] = voAl.getNombre();
			rowData[1] = voAl.getApellido();
			rowData[2] = voAl.getCedula();
			modelo.addRow(rowData);
		}		
		tblDatos.setModel(modelo);
	}
	
	public void mostrarVOEgrCompleto(VOEgresados voEgrs)
	{
		//me traigo el VO de Egresados y lo recorro, tiro cada dato a la tabla
		DefaultTableModel modelo=new DefaultTableModel();
		modelo.addColumn("Apellido");
		modelo.addColumn("Nombre");
		modelo.addColumn("Cedula");
		modelo.addColumn("Promedio total");
		modelo.addColumn("Promedio aprobaciones");
		Object rowData[]= new Object[5];
		
		for(VOEgresadoPromedioCal voEgr: voEgrs.getVOEgresadosArray())
		{
			rowData[0] = voEgr.getNombre();
			rowData[1] = voEgr.getApellido();
			rowData[2] = voEgr.getCedula();
			rowData[3] = voEgr.getPromedioTotal();
			rowData[4] = voEgr.getPromedioAprob();
			modelo.addRow(rowData);
		}		
		tblDatos.setModel(modelo);
	}
	
	public void mostrarError(String res)
	{
		JOptionPane.showMessageDialog(frmListadoDeEgresados, res, "Resultado", JOptionPane.ERROR_MESSAGE);
	}
}