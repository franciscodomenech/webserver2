package model;

public interface ICentro {
	public String processResponse(String response);
	public String nextQuestion();
	public boolean isLogged();
}
