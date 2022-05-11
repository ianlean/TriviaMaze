package TriviaMaze.Question;

import TriviaMaze.Database;

import java.util.Random;

public class TrueFalseType implements QuestionType
{
    private Database myDatabase;
    private String myQuestion;
    private boolean myAnswer;
    private boolean myFakeAnswer;

    public TrueFalseType()
    {

    }
    @Override
    public String getQuestion()
    {


        return null;
    }

    @Override
    public String getCorrectAnswer() {
        return null;
    }

    @Override
    public String[] getOtherAnswers() {
        return new String[0];
    }
}
