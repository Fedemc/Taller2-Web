package sistema.grafica.ventanas;

import java.awt.*;
import javax.swing.*;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import sistema.grafica.controladores.ContVentanaRespaldo;

public class VentanaRespaldo {

	private ContVentanaRespaldo contVentResp;
	
	private JFrame frame;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRespaldo window = new VentanaRespaldo();
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
	public VentanaRespaldo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Respaldar Datos");
		frame.setSize(275, 213);
		frame.getContentPane().setLayout(null);
		
		JButton btnRespaldar = new JButton("Respaldar");
		btnRespaldar.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnRespaldar.setBounds(43, 41, 178, 41);
		frame.getContentPane().add(btnRespaldar);
		
		btnRespaldar.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed (ActionEvent e)
				{
					contVentResp.Respaldar();
				}
			}
		);
		
		JButton btnVolver = new JButton("Cancelar");
		btnVolver.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnVolver.setBounds(43, 93, 178, 37);
		frame.getContentPane().add(btnVolver);
		
		btnVolver.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed (ActionEvent e)
				{
					frame.dispose();
				}
			}
		);
						
		Image iconG = new ImageIcon(this.getClass().getResource("/IconG.png")).getImage();
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		contVentResp=new ContVentanaRespaldo(this);
	}
	
	public void setVisible(boolean valor)
	{
		frame.setVisible(valor);
	}
	
	public void mostrarResultado(String res)
	{
		JOptionPane.showMessageDialog(frame, res, "Resultado", JOptionPane.INFORMATION_MESSAGE);
	}
}
