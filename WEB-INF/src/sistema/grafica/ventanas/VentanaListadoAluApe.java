package sistema.grafica.ventanas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import sistema.grafica.controladores.ContVentanaListadoAluApe;
import sistema.grafica.controladores.ContVentanaListadoAsig;
import sistema.logica.valueObjects.*;

import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.BevelBorder;

public class VentanaListadoAluApe {

	private JFrame frmListadoDeAlumnos;
	private JLabel lblIngreseApellido;
	private JTextField textField;
	private JButton btnListarAlumnosCon;
	private JButton btnCancelarYVolver;
	private ContVentanaListadoAluApe contVentListAluApe;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaListadoAluApe window = new VentanaListadoAluApe();
					window.frmListadoDeAlumnos.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaListadoAluApe() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmListadoDeAlumnos = new JFrame();
		frmListadoDeAlumnos.setTitle("Listado de Alumnos por Apellido");
		frmListadoDeAlumnos.setBounds(100, 100, 525, 432);
		frmListadoDeAlumnos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contVentListAluApe=new ContVentanaListadoAluApe(this);
		frmListadoDeAlumnos.getContentPane().setLayout(null);
		
		lblIngreseApellido = new JLabel("Ingrese apellido");
		lblIngreseApellido.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblIngreseApellido.setBounds(30, 30, 109, 14);
		frmListadoDeAlumnos.getContentPane().add(lblIngreseApellido);
		
		textField = new JTextField();
		textField.setBounds(148, 27, 188, 20);
		frmListadoDeAlumnos.getContentPane().add(textField);
		textField.setColumns(10);
		
		btnListarAlumnosCon = new JButton("Buscar");
		btnListarAlumnosCon.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnListarAlumnosCon.setBounds(346, 26, 113, 23);
		frmListadoDeAlumnos.getContentPane().add(btnListarAlumnosCon);
		
		JTable table = new JTable();
		table.setEnabled(false);
		JScrollPane jsp = new JScrollPane(table);
		jsp.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		jsp.setBounds(30, 72, 429, 265);
		frmListadoDeAlumnos.getContentPane().add(jsp);
		
		btnListarAlumnosCon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ape = textField.getText();
				if (!(ape.isEmpty())) {
					//Creo modelo de tabla y objeto 
					DefaultTableModel model = new DefaultTableModel();
					model.addColumn("Cédula");
					model.addColumn("Nombre");
					model.addColumn("Apellido");
					model.addColumn("Tipo de Alumno");
					Object rowData[] = new Object[4];
					
					//Me traigo listado de asignaturas desde el controlador.
					ArrayList<VOAlumno> listadoAlu = contVentListAluApe.cargarDatos(ape);
					
					//Cargo los datos en la tabla.
					for(int i = 0; i < listadoAlu.size(); i++) {
						rowData[0] = listadoAlu.get(i).getCedula();
						rowData[1] = listadoAlu.get(i).getNombre();
						rowData[2] = listadoAlu.get(i).getApellido();
						rowData[3] = listadoAlu.get(i).getTipo();
						model.addRow(rowData);
					}
					table.setModel(model);
				}
				else
					JOptionPane.showMessageDialog(frmListadoDeAlumnos, "No se puede dejar el campo apellido vacío", "Campo vacío", JOptionPane.WARNING_MESSAGE);
			}
		});
		
		btnCancelarYVolver = new JButton("Cancelar");
		btnCancelarYVolver.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnCancelarYVolver.setBounds(346, 348, 113, 23);
		frmListadoDeAlumnos.getContentPane().add(btnCancelarYVolver);
		
		btnCancelarYVolver.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				frmListadoDeAlumnos.dispose();
			}
		});
		
		
	}
	
	public void mostrarError(String res)
	{
		JOptionPane.showMessageDialog(frmListadoDeAlumnos, res, "Resultado", JOptionPane.ERROR_MESSAGE);
	}
	
	public void setVisible(boolean valor)
	{
		frmListadoDeAlumnos.setVisible(valor);
	}
}
