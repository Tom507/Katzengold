package base2;

import java.awt.Font;

import javax.swing.JOptionPane;

public class Questioner {
	Question[] questions = new Question[5];

	public Questioner() {

		questions[0] = new OpenQuestion("Studienort für Medieninformatik", "Friedberg");
		questions[1] = new OpenQuestion("Schlüssewort für Klassen-Methoden", "static");

		questions[2] = new YesNoQuestion("Java ist OOP", true);
		questions[3] = new YesNoQuestion("Java ist älter als C", false);

		questions[4] = new YesNoQuestion("Ist Blender besser als Maya?", true);

		//questions[5] = new OptionsQuestion("Wie lange ist die Regelstudienzeit für Medieninformatik?(bin)   (1) 0001  (2) 1000  (3) 0110 ", 3);
	}

	public boolean askQuestion() {
		boolean result;

		Question q = questions[(int) (Math.random() * questions.length)];
		result = q.ask();


		if (result) {
			JOptionPane.showMessageDialog(null, "Richtig", "Bewertung", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Leider Falsch", "Bewertung", JOptionPane.ERROR_MESSAGE);
		}
		return result;
	}

}
