package TriviaMaze;

import TriviaMaze.Question.Question;
import TriviaMaze.Question.TrueFalseType;

import java.util.Random;

public class GenerateQuestion
{
    private static Database database = new Database();
    private static Random myRandom;

    public GenerateQuestion()
    {
        myRandom = new Random();
    }
    public Question generateRandomQuestion()
    {
       // return createQuestion("tf");
        return switch (myRandom.nextInt(2)) {
            case 0 -> createQuestion("tf");
            case 1 -> createQuestion("sa");
            default -> null;
       };
    }

    public Question createQuestion(final String theType) {
        Question question = null;
        switch (theType)
        {
            case "tf":
                question = database.getTrueFalseQuestion();
                break;
            case "sa":
                question = database.getShortAnswerQuestion();
                break;
        }
        return question;
    }
}