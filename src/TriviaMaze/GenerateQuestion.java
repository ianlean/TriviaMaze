package TriviaMaze;

import TriviaMaze.Question.Question;
import TriviaMaze.Question.TrueFalseType;

import java.util.Random;

public class GenerateQuestion
{
    private static Database database = new Database();
    private Random myRandom;

    public GenerateQuestion()
    {
        myRandom = new Random();
    }
    public Question generateRandomQuestion()
    {
        return switch (myRandom.nextInt(2)) {
            case 0 -> createQuestion("tf");
            case 1 -> createQuestion("multi");
            default -> null;
        };
    }

    public Question createQuestion(final String theType) {
        Question question = switch (theType) {
            case "tf" -> database.getTrueFalseQuestion();
            case "multi" -> database.getMultipleChoice();
            default -> null;
        };
        return question;
    }
}
