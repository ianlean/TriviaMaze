package TriviaMaze.Question;

public abstract class Question
{
    protected String myQuestionBody;
    protected String myCorrectAnswer;
    protected String myFakeAnswers;

    public Question(String theQuestionBody, String theAnswer) {
        myQuestionBody = theQuestionBody;
        myCorrectAnswer = theAnswer;
    }

    public abstract String getQuestion();


    public abstract String getCorrectAnswer();

    public abstract String[] getOtherAnswers();
}
