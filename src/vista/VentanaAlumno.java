package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import controlador.Coordinador;
import modelo.AlumnoVO;

public class VentanaAlumno extends JFrame {

	private JPanel contentPane;
	private Coordinador miCoordinador;
	private JTextField txtDni;
	private JTextField txtApe;
	private JDateChooser selectorFecha;
	private JCheckBox chkDoc;
	private JButton btnAgrega;
	private JButton btnBusca;
	private JButton btnModifica;
	private JButton btnElimina;
	private JButton btnCancela;
	private JButton btnGuarda;
	private int accion;

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
					VentanaAlumno frame = new VentanaAlumno();
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
	public VentanaAlumno() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		btnGuarda = new JButton("Guardar");
		btnGuarda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarAlumno();
			}
		});
		panel.add(btnGuarda);
		
		btnAgrega = new JButton("Agregar");
		btnAgrega.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarAlumno();
			}
		});
		panel.add(btnAgrega);
		
		btnModifica = new JButton("Modificar");
		btnModifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accion=2;
				habilita(false, true, true, true, true, false, false, false, false, true);
			}
		});
		panel.add(btnModifica);
		
		btnElimina = new JButton("Eliminar");
		btnElimina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarAlumno();
			}
		});
		panel.add(btnElimina);
		
		btnBusca = new JButton("Buscar");
		btnBusca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarAlumno();
		}
		});
		panel.add(btnBusca);
		
		btnCancela = new JButton("Cancelar");
		btnCancela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
			}
		});
		panel.add(btnCancela);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Dni:");
		lblNewLabel.setBounds(30, 41, 46, 14);
		panel_1.add(lblNewLabel);
		
		txtDni = new JTextField();
		txtDni.setBounds(99, 38, 86, 20);
		panel_1.add(txtDni);
		txtDni.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Apellido:");
		lblNewLabel_1.setBounds(30, 89, 46, 14);
		panel_1.add(lblNewLabel_1);
		
		txtApe = new JTextField();
		txtApe.setBounds(99, 86, 86, 20);
		panel_1.add(txtApe);
		txtApe.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Fecha de nacimiento:");
		lblNewLabel_2.setBounds(30, 138, 113, 14);
		panel_1.add(lblNewLabel_2);
		
		chkDoc = new JCheckBox("Documentaci\u00F3n");
		chkDoc.setBounds(30, 177, 113, 23);
		panel_1.add(chkDoc);
		
		selectorFecha = new JDateChooser();
		selectorFecha.setBounds(157, 132, 70, 20);
		panel_1.add(selectorFecha);
		
		limpiar();
	}
	protected void eliminarAlumno() {
		int respuesta = JOptionPane.showConfirmDialog(null, "Está seguro de eliminar ese alumno?",
				"Confirmación", JOptionPane.YES_NO_OPTION);
		if (respuesta == JOptionPane.YES_NO_OPTION) {
			miCoordinador.eliminarAlumno(Integer.valueOf(txtDni.getText()));
			limpiar();
		}
		
	}

	protected void buscarAlumno() {
		miCoordinador.mostrarVentanaAlumnoBuscar();
		
	}

	protected void guardarAlumno() {
		try {
			Date date=selectorFecha.getDate();
			SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
			AlumnoVO miAlumnoVO = new AlumnoVO();
			miAlumnoVO.setDni(Integer.valueOf(txtDni.getText()));
			miAlumnoVO.setApe(txtApe.getText());
			miAlumnoVO.setFnac(sdf.format(date));
			if (chkDoc.isSelected())
				miAlumnoVO.setDoc((byte) 1);
			else
				miAlumnoVO.setDoc((byte) 0);
			if (accion==1) {
				miCoordinador.registrarAlumno(miAlumnoVO);
				limpiar();
			} else {
				miCoordinador.modificarAlumno(miAlumnoVO);
				limpiar();
			}
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error en el ingreso de datos", "Error",
					JOptionPane.ERROR_MESSAGE);
			limpiar();
		}
		
	}

	protected void agregarAlumno() {
		accion=1;
		habilita(true, true, true, true, true, false, false, false, false, true);	
	}

	public void habilita(boolean dni, boolean ape, boolean fecha, boolean doc, boolean bGuarda,
			boolean bAgrega, boolean bMod, boolean bEli, boolean bBusca, boolean bCancela) {
		txtDni.setEditable(dni);
		txtApe.setEditable(ape);
		selectorFecha.setEnabled(fecha);
		chkDoc.setEnabled(doc);
		btnGuarda.setVisible(bGuarda);
		btnAgrega.setEnabled(bAgrega);
		btnModifica.setEnabled(bMod);
		btnElimina.setEnabled(bEli);
		btnBusca.setEnabled(bBusca);
		btnCancela.setEnabled(bCancela);
	}
	
	private void limpiar() {
		txtDni.setText("");
		txtApe.setText("");
		selectorFecha.setCalendar(null);
		chkDoc.setSelected(false);
		
		habilita(true, false, false, false, false, true, false, false, true, true);
	}

	public void muestraAlumno(AlumnoVO miAlumnoVO) {
		SimpleDateFormat formato= new SimpleDateFormat("yyyy-MM-dd");
		Date miDia=new Date();
		GregorianCalendar miGCalendar = new GregorianCalendar();
		try {
			miDia=formato.parse(miAlumnoVO.getFnac());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		miGCalendar.setTime(miDia);
		txtDni.setText(String.valueOf(miAlumnoVO.getDni()));
		txtApe.setText(miAlumnoVO.getApe());
		selectorFecha.setCalendar(miGCalendar);

		if (miAlumnoVO.getDoc() == 0)
			chkDoc.setSelected(false);
		else
			chkDoc.setSelected(true);
		
		habilita(false, false, false, false, false, false, true, true, false, true);
	}
}
