package TriviaMaze.Question;

public class Question
{
    private int id;
    private QuestionType questionType;
    protected String myQuestionBody;
    protected String myCorrectAnswer;
    protected String[] myFakeAnswers;

//    public Question(String theQuestionBody, String theAnswer) {
//        myQuestionBody = theQuestionBody;
//        myCorrectAnswer = theAnswer;
//    }
    public Question(QuestionType theType, int id)
    {
        questionType = theType;
        this.id = id;
        initialize();
    }
    private void initialize()
    {
        this.myQuestionBody = questionType.getQuestion();
        this.myCorrectAnswer = questionType.getCorrectAnswer();
        this.myFakeAnswers = questionType.getOtherAnswers();
    }

}
