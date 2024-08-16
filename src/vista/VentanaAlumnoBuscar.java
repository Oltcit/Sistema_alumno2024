package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.Coordinador;
import modelo.AlumnoDAO;
import modelo.AlumnoVO;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaAlumnoBuscar extends JFrame {

	/**
	 * 
	 */
	
	private JPanel contentPane;
	private Coordinador miCoordinador;
	private JScrollPane scrollPane;
	private JTable tabla;

	public Coordinador getMiCoordinador() {
		return miCoordinador;
	}

	public void setMiCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAlumnoBuscar frame = new VentanaAlumnoBuscar();
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
	public VentanaAlumnoBuscar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel.add(btnNewButton);
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
	}

	public void mostrarDatosConTableModel() {
		DefaultTableModel modelo = new DefaultTableModel();
		tabla = new JTable();
		tabla.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				miCoordinador.pasarDatosAlumno(pasarDatos(e));
				//se puede poner dispose() para que al seleccionar un alumno desaparezca la ventana
				// VentanaAlumnoBuscar
				//dispose();
			}
		});
		tabla.setModel(modelo);
		modelo.addColumn("DNI");
		modelo.addColumn("Apellido y nombre");
		modelo.addColumn("Fecha de nacimiento");
		modelo.addColumn("Documentación");
		
		AlumnoDAO miAlumnoDAO = new AlumnoDAO();
		miAlumnoDAO.buscarAlumnos(modelo);
		
		scrollPane.setViewportView(tabla);
	}

	protected AlumnoVO pasarDatos(MouseEvent e) {
		AlumnoVO miaAlumnoVO = new AlumnoVO();
		int row = tabla.rowAtPoint(e.getPoint());
		miaAlumnoVO.setDni(Integer.valueOf(tabla.getValueAt(row, 0).toString()));
		miaAlumnoVO.setApe(tabla.getValueAt(row, 1).toString());
		miaAlumnoVO.setFnac(tabla.getValueAt(row, 2).toString());
		String estado=tabla.getValueAt(row, 3).toString();
		
		if (estado.equals("false")) 
			miaAlumnoVO.setDoc((byte) 0);
		else
			miaAlumnoVO.setDoc((byte) 1);
		
		return miaAlumnoVO;
	}

}
