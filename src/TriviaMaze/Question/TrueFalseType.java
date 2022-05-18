package TriviaMaze.Question;

public class TrueFalseType extends Question
{

    public TrueFalseType(String theBody, String theAnswer)
    {
        super(theBody, theAnswer);
    }

    public String getQuestion()
    {

        return this.myQuestionBody;


    }
    public String getCorrectAnswer() {
        return this.myCorrectAnswer;
    }

}
