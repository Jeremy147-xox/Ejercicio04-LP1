package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import entidad.Cliente;
import model.ClienteModel;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JRViewer;
import reporte.GeneradorReporte;

public class FrmReporteClienteConsulta extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JButton btnFiltrar;
	private JPanel panelReporte;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmReporteClienteConsulta frame = new FrmReporteClienteConsulta();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmReporteClienteConsulta() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 964, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblConsultaDeCliente = new JLabel("Consulta de cliente por nombre");
		lblConsultaDeCliente.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblConsultaDeCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsultaDeCliente.setBounds(10, 11, 908, 48);
		contentPane.add(lblConsultaDeCliente);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(235, 70, 162, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(this);
		btnFiltrar.setBounds(540, 72, 162, 23);
		contentPane.add(btnFiltrar);
		
		panelReporte = new JPanel();
		panelReporte.setBounds(34, 102, 891, 550);
		contentPane.add(panelReporte);
		panelReporte.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNombre.setBounds(176, 72, 65, 16);
		contentPane.add(lblNombre);
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnFiltrar) {
			do_btnFiltrar_actionPerformed(arg0);
		}
	}
	protected void do_btnFiltrar_actionPerformed(ActionEvent arg0) {
		String nom = txtNombre.getText().trim();
	
		ClienteModel model = new ClienteModel();
		List<Cliente> lstData = null;
		if(nom.equals("")){
			lstData = model.listaCliente();
		}else{
			lstData = model.consultaCliente(nom);	
		}
	
		// 1 La data
		JRBeanCollectionDataSource dataSource 
				= new JRBeanCollectionDataSource(lstData);

		// 2 El diseño del reporte (Diseño)
		String file = "reporteCliente.jasper";
		
		// 3 Se genera el reporte
		JasperPrint jasperPrint =
			GeneradorReporte.genera(file, dataSource, null);

		// 4 Se muestra en el visor
		JRViewer jRViewer = new JRViewer(jasperPrint);
		
		// 5 Se añade el visor al panel
		panelReporte.removeAll();
		panelReporte.add(jRViewer);
		panelReporte.repaint();
		panelReporte.revalidate();
		
	}
	
}
