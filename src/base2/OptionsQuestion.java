package base2;

import javax.swing.JOptionPane;

public class OptionsQuestion extends Question {

    int correctAnswer;

    public OptionsQuestion(String question, int answer) {
        super();
        this.question = question;
        this.correctAnswer = answer;
    }
    public boolean ask() {
        String answer =  JOptionPane.showInputDialog(null, "Frage: "+ question);

        return Integer.parseInt(answer) == correctAnswer;
    }
}
