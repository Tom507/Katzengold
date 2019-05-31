package base2;

import java.awt.Font;

import javax.swing.JOptionPane;

public class Questioner {
	OpenQuestion[] openQuestions;
	YesNoQuestion[] yesNoQuestions;

	public Questioner() {
		openQuestions = new OpenQuestion[2];
		openQuestions[0] = new OpenQuestion("Studienort für Medieninformatik", "Friedberg");
		openQuestions[1] = new OpenQuestion("Schlüssewort für Klassen-Methoden", "static");

		yesNoQuestions = new YesNoQuestion[2];
		yesNoQuestions[0] = new YesNoQuestion("Java ist OOP", true);
		yesNoQuestions[1] = new YesNoQuestion("Java ist älter als C", false);

	}

	public boolean askQuestion() {
		boolean result;
		if (Math.random() < 0.5) {
			OpenQuestion q = openQuestions[(int) (Math.random() * openQuestions.length)];
			result = q.ask();
		} else {
			YesNoQuestion q = yesNoQuestions[(int) (Math.random() * yesNoQuestions.length)];
			result = q.ask();
		}
		if (result) {
			JOptionPane.showMessageDialog(null, "Richtig", "Bewertung", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Leider Falsch", "Bewertung", JOptionPane.ERROR_MESSAGE);
		}
		return result;
	}

}
