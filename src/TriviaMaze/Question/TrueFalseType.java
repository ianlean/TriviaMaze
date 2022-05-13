package TriviaMaze.Question;

import TriviaMaze.Database;

import java.util.Random;

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

    public String[] getOtherAnswers() {
        return new String[0];
    }
}
