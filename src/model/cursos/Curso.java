package model.cursos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import model.Centro;
import model.questions.StatusResponse;

public class Curso implements ICurso {
	
	private HashMap<String,Alumno> alumnos;
	private Profesor profesor;
	private int id;
	
	public Curso(int id) {
		this.id = id;
		alumnos = new HashMap<String,Alumno>();
	}

	public int getId() {
		return id;
	}

	@Override
	public void matricula(Alumno alumno) {
		alumnos.put(alumno.getNif(), alumno);
	}

	@Override
	public void pasarLista(HashMap<String, Boolean> asistencia) {
		Iterator<String> nifs = asistencia.keySet().iterator();
		while(nifs.hasNext()) {
			String nif = nifs.next();
			Alumno al = this.buscarAlumno(nif);
			if(al!=null)
				al.setAsistencia(asistencia.get(nif));
		}
	}

	@Override
	public void evaluar(int evaluacion, HashMap<String, Float> notas) {
		Iterator<String> nifs = notas.keySet().iterator();
		if(profesor!=null) {
			while(nifs.hasNext()) {
				String nif = nifs.next();
				profesor.evalua(this.buscarAlumno(nif),evaluacion,notas.get(nif));
			}
		}
		
	}

	@Override
	public Alumno buscarAlumno(String nif) {
		// TODO Auto-generated method stub
		return alumnos.get(nif);
	}

	@Override
	public void setProfesor(Profesor profe) {
		profesor = profe;
	}

	@Override
	public void setAsistencia(Asistencia asistencia) {
		Alumno al = buscarAlumno(asistencia.getNif());
		if(al!=null)
			al.setAsistencia(asistencia.isAsistencia());
	}

	@Override
	public void setEvaluacion(InfoEvaluacion ev) {
		if(profesor!=null)
			profesor.evalua(buscarAlumno(ev.getNif()), ev.getEvaluacion(), ev.getNota());
	}

	@Override
	public boolean borrarAlumno(String nif) {
		if(alumnos.containsKey(nif)) {
			alumnos.remove(nif);
			return true;
		}else
			return false;
	}

	@Override
	public String processResponse(Object result,int op) {
		String r = null;
		switch(op) {
		case Centro.OP_ADD_ALUMNO:
			matricula((Alumno) result);
			break;
		case Centro.OP_MARCAR_ASISTENCIA:
			setAsistencia((Asistencia) result);
			break;
		case Centro.OP_EVALUAR:
			setEvaluacion((InfoEvaluacion) result);
			break;
		case Centro.OP_MOSTRAR_ALUMNO:
			r = processSearchAlumn(result);
			break;
		case Centro.OP_DELETE_ALUMNO:
			r = processDeleteAlumn(result);
			break;
		}
		return r;
	}
	
	private String processSearchAlumn(Object result) {
		Alumno al = buscarAlumno((String) result);
		if(al!=null)
			return al.toString();
		else
			return "No existe";
	}
	
	private String processDeleteAlumn(Object result) {
		if(result==null)
			return "";
		else if(alumnos.containsKey((String)result)) {
			alumnos.remove((String)result);
			return "Borrado";
		}else
			return "No encontrado";
	}

	private int getPartDateFromTks(String[] tks,int p) {
		int num = 1;
		try {
			num = tks.length>p?Integer.parseInt(tks[p]):1;
		}catch(NumberFormatException nfe) {}
		return num;
	}
	
	@Override
	public synchronized boolean matricula(String nombre, String apellidos, String nif, String datestr) {
		if(!alumnos.containsKey(nif)) {
			String[] tks = datestr.split("-");
			int day = getPartDateFromTks(tks,2);
			int month = getPartDateFromTks(tks,1);
			int year = getPartDateFromTks(tks,0);
			alumnos.put(nif, new Alumno(nombre,apellidos,nif,day,month,year));
			return true;
		}else
			return false;
		
	}
	
	private boolean CheckKeysAlumno(Alumno a,String[] keys) {
		boolean ischecked = true;
		for(int i=0;(i<keys.length && ischecked);i++) {
			String key = keys[i];
			ischecked = a.getNombre().contains(key) || a.getApellidos().contains(key) || a.getNif().contains(key);
		}
		return ischecked;
	}
	
	private boolean checkFilter(Alumno a,String tosearch) {
		String cleanSearch = tosearch.trim();
		String[] keysToSeach = cleanSearch.split(" ");
		return cleanSearch.isEmpty() || CheckKeysAlumno(a,keysToSeach);
	}
	
	@Override
	public List<Alumno> filtrar(String tosearch) {
		ArrayList<Alumno> lista = new ArrayList<Alumno>();
		Iterator<String> it = alumnos.keySet().iterator();
		while(it.hasNext()) {
			String dni = it.next();
			synchronized(alumnos) {
				Alumno a = alumnos.get(dni);
				if(a!=null && checkFilter(a,tosearch))
					lista.add(a);
			}
		}
		return lista;
	}
	

}
