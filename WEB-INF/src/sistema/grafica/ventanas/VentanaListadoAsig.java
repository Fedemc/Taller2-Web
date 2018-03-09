package sistema.grafica.ventanas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import sistema.grafica.controladores.ContVentanaListadoAsig;
import sistema.grafica.controladores.ContVentanaRespaldo;
import sistema.logica.valueObjects.*;

import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.table.*;
import java.awt.Font;

public class VentanaListadoAsig {

	private JFrame frmListadoDeAsignaturas;
	private JTable table;
	private JButton btnListarAsignaturas;
	private ContVentanaListadoAsig contVentListAsig;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaListadoAsig window = new VentanaListadoAsig();
					window.frmListadoDeAsignaturas.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaListadoAsig() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmListadoDeAsignaturas = new JFrame();
		frmListadoDeAsignaturas.setTitle("Listado de Asignaturas");
		frmListadoDeAsignaturas.setBounds(100, 100, 653, 385);
		frmListadoDeAsignaturas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contVentListAsig=new ContVentanaListadoAsig(this);
		frmListadoDeAsignaturas.getContentPane().setLayout(null);
		
		JLabel lblListadoDeAsignaturas = new JLabel("");
		lblListadoDeAsignaturas.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 20));
		lblListadoDeAsignaturas.setBounds(237, 26, 204, 28);
		frmListadoDeAsignaturas.getContentPane().add(lblListadoDeAsignaturas);
		
		//table = new JTable();
		//frame.getContentPane().add(table, "4, 4, fill, fill");
		
		JTable table = new JTable();
		table.setEnabled(false);
		JScrollPane jsp = new JScrollPane(table);
		jsp.setBounds(30, 26, 576, 271);
		frmListadoDeAsignaturas.getContentPane().add(jsp);
		
		//Creo modelo de tabla y objeto 
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Código");
		model.addColumn("Nombre");
		model.addColumn("Descripción");
		Object rowData[] = new Object[3];
		
		//Me traigo listado de asignaturas desde el controlador.
		ArrayList<VOAsignatura> listadoAsig = contVentListAsig.cargarDatos();
		
		//Cargo los datos en la tabla.
		for(int i = 0; i < listadoAsig.size(); i++) {
            rowData[0] = listadoAsig.get(i).getCodigo();
            rowData[1] = listadoAsig.get(i).getNombre();
            rowData[2] = listadoAsig.get(i).getDescripcion();
            model.addRow(rowData);
        }
		table.setModel(model);
		
		JButton btnCancelarVolver = new JButton("Cancelar");
		btnCancelarVolver.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnCancelarVolver.setBounds(449, 308, 157, 23);
		frmListadoDeAsignaturas.getContentPane().add(btnCancelarVolver);
		
		btnCancelarVolver.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				frmListadoDeAsignaturas.dispose();
			}
		});
		
		frmListadoDeAsignaturas.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public void setVisible(boolean valor)
	{
		frmListadoDeAsignaturas.setVisible(valor);
	}
	
	public JTable getTable() {
		return table;
	}
	
	public void mostrarError(String res)
	{
		JOptionPane.showMessageDialog(frmListadoDeAsignaturas, res, "Resultado", JOptionPane.ERROR_MESSAGE);
	}

}
