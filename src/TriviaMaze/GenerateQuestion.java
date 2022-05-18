package TriviaMaze;

import TriviaMaze.Question.Question;

import java.util.Random;

public class GenerateQuestion
{
    private static final Database database = new Database();
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
        return switch (theType) {
            case "tf" -> database.getTrueFalseQuestion();
            case "sa" -> database.getShortAnswerQuestion();
            default -> null;
        };
    }
}