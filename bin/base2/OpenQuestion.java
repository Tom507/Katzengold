package base2;

import javax.swing.JOptionPane;

public class OpenQuestion {
	String question;
	String answer;
	
	public OpenQuestion(String question, String answer) {
		super();
		this.question = question;
		this.answer = answer;
	}

	public String getQuestion() {
		return question;
	}

	public static void main(String[] args) {
		OpenQuestion q = new OpenQuestion("Studienort f�r Medieninformatik", "Friedberg");		
		System.out.println( q.ask() );
	}
	
	public boolean ask() {
		String answer =  JOptionPane.showInputDialog(null, "Frage: "+ question);
		if( answer == null ) {
			return false;
		}
		return  this.answer.equalsIgnoreCase( answer.trim());
	}

}
