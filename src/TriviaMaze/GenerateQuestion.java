package TriviaMaze;

import TriviaMaze.Question.Question;
import TriviaMaze.Question.QuestionType;

import java.util.Random;

public class GenerateQuestion
{
    private Random myRandom;

    public GenerateQuestion()
    {
        myRandom = new Random();
    }
    public Question generateRandomQuestion()
    {
        return switch (myRandom.nextInt(3)) {
            case 0 -> createQuestion("tf");
            case 1 -> createQuestion("single");
            case 2 -> createQuestion("short");
            default -> null;
        };
    }

    public Question createQuestion(final String theType) {
        QuestionType type;
        Question question;
        return null;
    }
}
