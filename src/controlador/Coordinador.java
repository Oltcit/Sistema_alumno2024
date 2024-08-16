package controlador;

import modelo.AlumnoDAO;
import modelo.AlumnoVO;
import modelo.Logica;
import vista.VentanaAlumno;
import vista.VentanaAlumnoBuscar;
import vista.VentanaPrincipal;

public class Coordinador {
	private VentanaAlumno miVentanaAlumno;
	private VentanaAlumnoBuscar miVentanaAlumnoBuscar;
	private VentanaPrincipal miVentanaPrincipal;
	private Logica miLogica;
	
	
	
	public Logica getMiLogica() {
		return miLogica;
	}
	public void setMiLogica(Logica miLogica) {
		this.miLogica = miLogica;
	}
	public VentanaAlumno getMiVentanaAlumno() {
		return miVentanaAlumno;
	}
	public void setMiVentanaAlumno(VentanaAlumno miVentanaAlumno) {
		this.miVentanaAlumno = miVentanaAlumno;
	}
	public VentanaAlumnoBuscar getMiVentanaAlumnoBuscar() {
		return miVentanaAlumnoBuscar;
	}
	public void setMiVentanaAlumnoBuscar(VentanaAlumnoBuscar miVentanaAlumnoBuscar) {
		this.miVentanaAlumnoBuscar = miVentanaAlumnoBuscar;
	}
	public VentanaPrincipal getMiVentanaPrincipal() {
		return miVentanaPrincipal;
	}
	public void setMiVentanaPrincipal(VentanaPrincipal miVentanaPrincipal) {
		this.miVentanaPrincipal = miVentanaPrincipal;
	}
	
	//mostrar ventanas
	public void mostrarVentanaAlumno() {
		miVentanaAlumno.setVisible(true);
		
	}
	public void registrarAlumno(AlumnoVO miAlumnoVO) {
		//miLogica.verificarDatos(miAlumnoVO);
		AlumnoDAO miAlumnoDAO = new AlumnoDAO();
		miAlumnoDAO.registrarAlumno(miAlumnoVO);
	}
	public void mostrarVentanaAlumnoBuscar() {
		miVentanaAlumnoBuscar.setVisible(true);
		miVentanaAlumnoBuscar.mostrarDatosConTableModel();
	}
	public void pasarDatosAlumno(AlumnoVO miAlumnoVO) {
		miVentanaAlumno.muestraAlumno(miAlumnoVO);
	}
	public void modificarAlumno(AlumnoVO miAlumnoVO) {
		AlumnoDAO miAlumnoDAO = new AlumnoDAO();
		miAlumnoDAO.modificarAlumno(miAlumnoVO);	
	}
	public void eliminarAlumno(Integer dni) {
		AlumnoDAO miAlumnoDAO = new AlumnoDAO();
		miAlumnoDAO.eliminarAlumno(dni);
	}
}
