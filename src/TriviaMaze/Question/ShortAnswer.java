package TriviaMaze.Question;
/*
 * Assignment: Course Project "Trivia Maze"
 *
 * Instructor: Tom Capaul
 *
 * */
/**
 * This is a subclass called "ShortAnswer" extended Question class,
 * this class will contain a short answer question and the question answer.
 *
 * @author Bohan Yang, Ian Mclean, Qinyu Tao
 * @version May 25th 2022
 */
public class ShortAnswer extends Question
{
    /**
     * Construct the short answer class by calling superclass constructor
     *
     * @param theBody, the question body
     * @param theAnswer, the question answer
     * */
    public ShortAnswer(final String theBody, final String theAnswer)
    {
        super(theBody, theAnswer);
    }
    /**
     * A getter method to get the question body
     *
     * @return the String type of that question
     * */
    public String getQuestion() {
        return this.myQuestionBody;
    }

    /**
     * A getter method to get the question answer
     *
     * @return the String type of that question answer
     * */
    public String getCorrectAnswer() {
        return this.myCorrectAnswer;
    }

}
