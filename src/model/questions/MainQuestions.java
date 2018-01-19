package model.questions;

import model.Centro;

public class MainQuestions {
	
	private Questions actualQD;
	private int actual;
	private final static int STARTQ = 0;
	private final int STARTQD = 3;
	
	public static final int FIRST_STEP = STARTQ+1;
	public static final int SECOND_STEP = STARTQ+2;
	
	public static final int EXIT_STEP = STARTQ;
	
	private int actualCurso;
	private int actualOp;
	
	public MainQuestions() {
		actual = STARTQ;
		actualQD = null;
	}
	
	public String next() {
		actual++;
		String q = "";
		switch(actual) {
		case FIRST_STEP:
			q = getQuestionInitial();
			break;
		case SECOND_STEP:
			q = getQuestionOp();
			break;
		case EXIT_STEP:
			q = getQuestionExit();
			break;
		default:
			setQuestionsForOp(actualOp);
			if(actualQD!=null)
				q = actualQD.getQuestion(actual-STARTQD);
			break;
			
		}
		if(q==null) {
			actualQD = null;
			actual = STARTQ-1;
			q = next();
		}
		return q;
	}
	
	private void setQuestionsForOp(int op) {
		if(this.getInternalStatus()==0) {
			switch(op) {
			case Centro.OP_ADD_ALUMNO:
				actualQD = new QuestionsAddAlumno();
				break;
			case Centro.OP_EVALUAR:
				actualQD = new QuestionsEvalua();
				break;
			case Centro.OP_MARCAR_ASISTENCIA:
				actualQD = new QuestionsAsistencia();
				break;
			case Centro.OP_MOSTRAR_ALUMNO:
				actualQD = new QuestionsBuscar();
				break;
			case Centro.OP_DELETE_ALUMNO:
				actualQD = new QuestionsDelete();
				break;
			}
		}
	}

	private String getQuestionInitial() {
		// TODO Auto-generated method stub
		return "Elige Curso(0,"+Centro.NUM_CURSOS+"):";
	}
	
	private String getQuestionExit() {
		return "Desea Salir (Si/No)";
	}
	

	private String getQuestionOp() {
		return "Operacion("+Centro.OP_ADD_ALUMNO+":Matricula, "+Centro.OP_MARCAR_ASISTENCIA+":Asistencia, "+Centro.OP_EVALUAR+":Evaluar, "+Centro.OP_MOSTRAR_ALUMNO+":Buscar, "+Centro.OP_DELETE_ALUMNO+":Eliminar )";
	}
	
	public int getStatus() {
		return actual;
	}
	
	public int getInternalStatus() {
		return actual - STARTQD;
	}
	
	public StatusResponse processResponse(String response) {
		StatusResponse sr = new StatusResponse();
		switch(actual) {
		case FIRST_STEP:
			try {
				this.actualCurso = Integer.parseInt(response);
				sr.setResult(actualCurso);
				sr.setIsok(actualCurso>=0 && actualCurso<Centro.NUM_CURSOS);
			}catch(NumberFormatException nfe) {}
			break;
		case SECOND_STEP:
			try {
				this.actualOp = Integer.parseInt(response);
				sr.setResult(actualOp);
				sr.setIsok(actualOp>=0 && actualOp<=Centro.OP_DELETE_ALUMNO);
			}catch(NumberFormatException nfe) {}
			break;
		case EXIT_STEP:
			try {
				sr.setResult(response.equals("Si"));
				sr.setIsok(true);
			}catch(NumberFormatException nfe) {}
			break;
		default:
			if(actualQD!=null)
				sr = actualQD.processResponse(getInternalStatus(), response);
			break;
		}
		return sr;
	}
	
	public void reset() {
		actual = STARTQ;
	}
	
	public int getCurso() {
		return actualCurso;
	}
	
	public int getOp() {
		return actualOp;
	}

}
