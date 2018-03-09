package sistema.grafica.ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import sistema.grafica.controladores.ContVentanaMontoRecaudado;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class VentanaMontoRecaudado {

	private JFrame frmConsultarMontoRecaudado;
	
	private ContVentanaMontoRecaudado contVentMontoRec;
	private JTextField textCI;
	private JTextField textAnio;
	private JLabel lblCedulaDelAlumno;
	private JLabel lblAoLectivo;
	private JButton btnConsultarMonto;
	private JTextArea txtArea; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaMontoRecaudado window = new VentanaMontoRecaudado();
					window.frmConsultarMontoRecaudado.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaMontoRecaudado() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmConsultarMontoRecaudado = new JFrame();
		frmConsultarMontoRecaudado.setTitle("Consultar Monto Recaudado");
		frmConsultarMontoRecaudado.setBounds(100, 100, 393, 272);
		frmConsultarMontoRecaudado.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmConsultarMontoRecaudado.getContentPane().setLayout(null);
		
		lblCedulaDelAlumno = new JLabel("Cedula del alumno");
		lblCedulaDelAlumno.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblCedulaDelAlumno.setBounds(38, 34, 139, 14);
		frmConsultarMontoRecaudado.getContentPane().add(lblCedulaDelAlumno);
		
		textCI = new JTextField();
		textCI.setBounds(205, 31, 121, 20);
		frmConsultarMontoRecaudado.getContentPane().add(textCI);
		textCI.setColumns(10);
		
		frmConsultarMontoRecaudado.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		lblAoLectivo = new JLabel("A\u00F1o lectivo");
		lblAoLectivo.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblAoLectivo.setBounds(38, 65, 115, 14);
		frmConsultarMontoRecaudado.getContentPane().add(lblAoLectivo);
		
		textAnio = new JTextField();
		textAnio.setBounds(205, 62, 121, 20);
		frmConsultarMontoRecaudado.getContentPane().add(textAnio);
		textAnio.setColumns(10);
		
		btnConsultarMonto = new JButton("Consultar");
		btnConsultarMonto.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnConsultarMonto.setBounds(205, 93, 121, 23);
		btnConsultarMonto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validarCamposYCalcularMonto();
			}
		});
		
		frmConsultarMontoRecaudado.getContentPane().add(btnConsultarMonto);
		
		JLabel lblElMontoTotal = new JLabel("Total Recaudado");
		lblElMontoTotal.setBounds(38, 152, 132, 14);
		lblElMontoTotal.setFont(new Font("Calibri", Font.PLAIN, 18));
		frmConsultarMontoRecaudado.getContentPane().add(lblElMontoTotal);
		
		contVentMontoRec=new ContVentanaMontoRecaudado(this);
		
		txtArea = new JTextArea();
		txtArea.setFont(new Font("Calibri", Font.PLAIN, 16));
		txtArea.setEditable(false);
		txtArea.setBounds(205, 148, 121, 20);
		frmConsultarMontoRecaudado.getContentPane().add(txtArea);
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmConsultarMontoRecaudado.dispose();
			}
		});
		btnNewButton.setBounds(205, 179, 121, 23);
		frmConsultarMontoRecaudado.getContentPane().add(btnNewButton);
	}
	
	public void setVisible(boolean valor)
	{
		frmConsultarMontoRecaudado.setVisible(valor);
	}
	
	public void buscarTextoMontoRecaudado(long ci, int anio)
	{
		float res=contVentMontoRec.ObtenerMontoRecaudado(ci, anio);
		
		//depende del monto muestro el monto o sino muestro 0.
		if(res!=0)
		{
			txtArea.setText(String.valueOf(res));
		}
		else
		{
			txtArea.setText("");
		}
	}
	
	public void mostrarError(String res)
	{
		JOptionPane.showMessageDialog(frmConsultarMontoRecaudado, res, "ERROR!", JOptionPane.ERROR_MESSAGE);
	}
	
	private void validarCamposYCalcularMonto()
	{
		if(!textCI.getText().isEmpty())
		{
			if(!textAnio.getText().isEmpty())
			{
				//Si los campos no son vacíos
				buscarTextoMontoRecaudado(Long.parseLong(textCI.getText()), Integer.parseInt(textAnio.getText()));
			}
			else
			{
				JOptionPane.showMessageDialog(frmConsultarMontoRecaudado, "Ingrese un año lectivo", "ERROR!", JOptionPane.ERROR_MESSAGE);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(frmConsultarMontoRecaudado, "Ingrese una cedula de alumno", "ERROR!", JOptionPane.ERROR_MESSAGE);
		}
	}
}
