package model;

import model.cursos.Alumno;
import model.cursos.Asistencia;
import model.cursos.Curso;
import model.cursos.InfoEvaluacion;
import model.cursos.Profesor;
import model.questions.MainQuestions;
import model.questions.StatusResponse;

public class Centro implements ICentro {
	
	public static final int NUM_CURSOS = 10;
	
	public static final int OP_ADD_ALUMNO = 1;
	public static final int OP_MARCAR_ASISTENCIA = 2;
	public static final int OP_EVALUAR = 3;
	public static final int OP_MOSTRAR_ALUMNO = 4;
	public static final int OP_DELETE_ALUMNO = 5;
	
	private static Centro INSTANCE;
	
	private MainQuestions questions;
	
	private Curso[] cursos;
	
	private boolean logged;
	
	public Centro() {
		logged = true;
		cursos = new Curso[NUM_CURSOS];
		for(int i=0;i<cursos.length;i++) {
			Curso c = new Curso(i);
			Profesor profe = new Profesor();
			profe.setNombre("Profe "+i);
			c.setProfesor(profe);
			cursos[i] = c;
		}
		questions = new MainQuestions();
	}

	@Override
	public String nextQuestion() {
		return questions.next();
	}

	@Override
	public String processResponse(String response) {
		String r = null;
		StatusResponse sr = questions.processResponse(response);
		if(sr.isFinish() && sr.isIsok() && questions.getStatus()>MainQuestions.SECOND_STEP) {
			r = checkStResponse(sr);
		}else if(questions.getStatus()==MainQuestions.EXIT_STEP && ((Boolean)sr.getResult())) {
			logged = false;
			r = "Hasta pronto!!!!";
		}else if(!sr.isIsok()) {
			r = "Respuesta inesperada";
			questions.reset();
		}
		return r;
	}
	
	private String checkStResponse(StatusResponse sr) {
		String r = null;
		int c = questions.getCurso();
		if(c>=0 && c<cursos.length)
			r = cursos[c].processResponse(sr.getResult(), questions.getOp());
		return r;
	}

	@Override
	public boolean isLogged() {
		return logged;
	}
	
	public Curso[] getCursos() {
		return cursos;
	}
	
	public int getTotalCursos() {
		return cursos.length;
	}
	
	public Curso getCurso(int p) {
		return cursos[p];
	}
	
	public synchronized static Centro getInstance() {
		if(INSTANCE==null)
			INSTANCE = new Centro();
		return INSTANCE;
	}
	
	public static Curso getCurso(String p) {
		return getInstance().getCurso(Integer.parseInt(p));
	}

}
