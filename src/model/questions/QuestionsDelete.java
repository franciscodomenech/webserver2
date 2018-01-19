package model.questions;

public class QuestionsDelete extends Questions {

	private final int STEP_NIF = 0;
	private final int STEP_CONFIRM = 1;
	
	private String nif;
	
	@Override
	public void init() {
		this.questionsText.add("Nif: ");
		this.questionsText.add("Est�s seguro? Si/No");
	}

	@Override
	public StatusResponse processResponse(int step, String response) {
		StatusResponse sr = new StatusResponse();
		sr.setIsok(true);
		switch(step) {
		case STEP_NIF:
			nif = response;
			break;
		case STEP_CONFIRM:
			if(response.equals("Si"))
				sr.setResult(nif);
			else if(response.equals("No"))
				sr.setResult(null);
			else
				sr.setIsok(false);
			break;
		}
		return sr;
	}

}
