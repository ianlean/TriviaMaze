package TriviaMaze.Question;

public abstract class Question
{
    protected String myQuestionBody;
    protected String myCorrectAnswer;

    public Question(String theQuestionBody, String theAnswer) {
        myQuestionBody = theQuestionBody;
        myCorrectAnswer = theAnswer;
    }

    public abstract String getQuestion();


    public abstract String getCorrectAnswer();
}
