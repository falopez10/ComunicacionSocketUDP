package Cliente;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

public class Cliente extends JFrame implements ActionListener
{

	private JPanel contentPane;
	
	 private static final String INICIAR_COMUNICACION = "Iniciar comunicaci\u00F3n";
	 private static final String ENVIAR = "Enviar";
	 private static final String AGREGAR_IP = "Agregar IP";
	 private static final String AGREGAR_PUERTO = "Agregar Puerto";
	 //private static final String SOLICITAR = "Solicitar";

	 
	private JButton btnIniciarComunicacin;
	private JButton btnEnviar;
	private JTextField textFieldIP;
	private JTextField textFieldPuerto;
	private JTextField textFieldNumObjetos;
	private JButton btnAgregarIp;
	private JButton btnAgregarPuerto;
	//private JButton btnSolicitar;
	private ClienteUDP clienteUDP;
	
	private String ip;
	private int puerto;
	private String numObjetos;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cliente frame = new Cliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void iniciarCom() throws Exception
	{
		clienteUDP = new ClienteUDP(numObjetos, puerto);
		
	}
	
	/**
	 * Create the frame.
	 */
	public Cliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBienvenidoALa = new JLabel("Bienvenido a la tranmisi\u00F3n por UDP");
		lblBienvenidoALa.setFont(new Font("Lucida Sans", Font.BOLD, 12));
		lblBienvenidoALa.setBounds(87, 11, 250, 38);
		contentPane.add(lblBienvenidoALa);
		
		JLabel lblNewLabel = new JLabel("Dirección IP del servidor");
		lblNewLabel.setFont(new Font("Lucida Sans", Font.PLAIN, 11));
		lblNewLabel.setBounds(10, 51, 151, 27);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Puerto de escucha");
		lblNewLabel_1.setFont(new Font("Lucida Sans", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(10, 89, 151, 27);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Número de objetos");
		lblNewLabel_2.setFont(new Font("Lucida Sans", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(10, 127, 151, 27);
		contentPane.add(lblNewLabel_2);
		
		btnIniciarComunicacin = new JButton("Iniciar comunicaci\u00F3n");
		btnIniciarComunicacin.setBounds(184, 206, 132, 27);
		btnIniciarComunicacin.addActionListener(this);
		contentPane.add(btnIniciarComunicacin);
		
		btnEnviar = new JButton("Enviar");
		btnEnviar.setBounds(326, 206, 87, 25);
		btnEnviar.addActionListener(this);
		contentPane.add(btnEnviar);
		
		textFieldIP = new JTextField();
		textFieldIP.setBounds(171, 51, 86, 20);
		contentPane.add(textFieldIP);
		textFieldIP.setColumns(10);
		
		textFieldPuerto = new JTextField();
		textFieldPuerto.setColumns(10);
		textFieldPuerto.setBounds(171, 92, 86, 20);
		contentPane.add(textFieldPuerto);
		
		textFieldNumObjetos = new JTextField();
		textFieldNumObjetos.setBounds(171, 130, 86, 20);
		contentPane.add(textFieldNumObjetos);
		textFieldNumObjetos.setColumns(10);
		
		btnAgregarIp = new JButton("Agregar IP");
		btnAgregarIp.setBounds(282, 50, 113, 23);
		btnAgregarIp.addActionListener(this);
		contentPane.add(btnAgregarIp);
		
		btnAgregarPuerto = new JButton("Agregar Puerto");
		btnAgregarPuerto.setBounds(282, 91, 113, 23);
		btnAgregarPuerto.addActionListener(this);
		contentPane.add(btnAgregarPuerto);
		
		//btnSolicitar = new JButton("Solicitar");
		//btnSolicitar.setBounds(282, 129, 113, 23);
		//btnSolicitar.addActionListener(this);
		//contentPane.add(btnSolicitar);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String comando = e.getActionCommand( );
		if(comando.equals(AGREGAR_IP))
		{
			ip = textFieldIP.getText();
			System.out.println(ip);
		}
		if(comando.equals(AGREGAR_PUERTO))
		{
			puerto = Integer.parseInt( textFieldPuerto.getText() );
			System.out.println(puerto);
		}
		if(comando.equals(ENVIAR))
		{
			numObjetos = textFieldNumObjetos.getText();
			System.out.println(numObjetos);
		}
		if(comando.equals(INICIAR_COMUNICACION))
		{
			try {
				iniciarCom();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
