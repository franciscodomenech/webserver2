package model.cursos;

import java.util.HashMap;

import model.questions.StatusResponse;

public interface ICurso {
	public void matricula(Alumno alumno);
	public boolean matricula(String nombre,String apellidos,String nif,String datestr);
	public void pasarLista(HashMap<String,Boolean> asistencia);
	public void evaluar(int evaluacion,HashMap<String,Float> notas);
	//Devuelve null en caso de no econtrarlo
	public Alumno buscarAlumno(String nif);
	
	public void setProfesor(Profesor profe);
	
	public void setAsistencia(Asistencia asistencia);
	
	public void setEvaluacion(InfoEvaluacion ev);
	
	public boolean borrarAlumno(String nif);
	
	public String processResponse(Object r,int op);
}
