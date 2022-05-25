package TriviaMaze.Question;
/*
 * Assignment: Course Project "Trivia Maze"
 *
 * Instructor: Tom Capaul
 *
 * */
/**
 * This is a subclass called "Question" extended Question class,
 * this class will contain a true false question and the question answer.
 *
 * @author Bohan Yang, Ian Mclean, Qinyu Tao
 * @version May 25th 2022
 */
public class TrueFalseType extends Question
{
    /**
     * Construct the true or false class by calling superclass constructor
     *
     * @param theBody, the question body
     * @param theAnswer, the question answer
     * */
    public TrueFalseType(String theBody, String theAnswer)
    {
        super(theBody, theAnswer);
    }
    /**
     * A getter method to get the question body
     *
     * @return the String type of that question
     * */
    public String getQuestion()
    {
        return this.myQuestionBody;
    }
    /**
     * A getter method to get the question answer
     *
     * @return the String type of that question answer
     * */
    public String getCorrectAnswer()
    {
        return this.myCorrectAnswer;
    }

}
