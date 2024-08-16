package controlador;

import modelo.Logica;
import vista.VentanaAlumno;
import vista.VentanaAlumnoBuscar;
import vista.VentanaPrincipal;

public class Principal {
	
	VentanaAlumno miVentanaAlumno;
	VentanaAlumnoBuscar miVentanaAlumnoBuscar;
	VentanaPrincipal miVentanaPrincipal;
	Coordinador miCoordinador;
	Logica miLogica;

	public static void main(String[] args) {
		
		Principal miPrincipal = new Principal();
		miPrincipal.iniciar();
	}

	private void iniciar() {
		miVentanaAlumno = new VentanaAlumno();
		miVentanaAlumnoBuscar = new VentanaAlumnoBuscar();
		miVentanaPrincipal = new VentanaPrincipal();
		miCoordinador = new Coordinador();
		miLogica = new Logica();
		
		miCoordinador.setMiVentanaAlumno(miVentanaAlumno);
		miCoordinador.setMiVentanaAlumnoBuscar(miVentanaAlumnoBuscar);
		miCoordinador.setMiVentanaPrincipal(miVentanaPrincipal);
		miCoordinador.setMiLogica(miLogica);
		
		miVentanaAlumno.setMiCoordinador(miCoordinador);
		miVentanaAlumnoBuscar.setMiCoordinador(miCoordinador);
		miVentanaPrincipal.setMiCoordinador(miCoordinador);
		miLogica.setMiCoordinador(miCoordinador);
		
		miVentanaPrincipal.setVisible(true);
		
		
	}

}
