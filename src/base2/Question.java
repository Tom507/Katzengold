package base2;

public abstract class Question {
    String question;

    public String getQuestion() {
        return question;
    }

    public abstract boolean ask();

}