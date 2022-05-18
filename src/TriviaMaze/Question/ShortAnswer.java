package TriviaMaze.Question;

public class ShortAnswer extends  Question{


    public ShortAnswer(String theBody, String theAnswer)
    {
        super(theBody, theAnswer);
    }
    public String getQuestion() {
        return this.myQuestionBody;
    }


    public String getCorrectAnswer() {
        return this.myCorrectAnswer;
    }

}
