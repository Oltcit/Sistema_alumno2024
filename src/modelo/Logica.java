package modelo;

import javax.swing.JOptionPane;

import controlador.Coordinador;

public class Logica {
	Coordinador miCoordinador;

	public Coordinador getMiCoordinador() {
		return miCoordinador;
	}

	public void setMiCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
	}

	public void verificarDatos(AlumnoVO miAlumnoVO) {
		String msg="";
		boolean hayError=false;
		
		//if (miAlumnoVO.getDni()) {
			
		//}
		if (miAlumnoVO.getApe().isEmpty()){
			msg+="\n no se ingresó Apellido y nombre";
			hayError=true;
		}
		
		
		if (hayError){
			JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
		}
		else{
			AlumnoDAO miAlumnoDAO;
				miAlumnoDAO = new AlumnoDAO();
	//			miAlumnoDAO.registrarAlumno(miAlumnoVO);	
		}
		
	}
	
	

}
