package GUI;
/*
 * Assignment: Course Project "Trivia Maze"
 *
 * Instructor: Tom Capaul
 *
 * */
import TriviaMaze.Cell;
import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.Objects;
import static GUI.Frame.*;
/**
 * This is a superclass called "Question"
 * this class will contain different type of question's body and answer.
 *
 * @author Bohan Yang, Ian Mclean, Qinyu Tao
 * @version May 25th 2022
 */


public class TextPanel extends JPanel implements Serializable
{
    private final JTextField myQuestionText;
    private final JTextField myInputText;
    private final JTextField myResultText;
    private final JLabel myQuestion;
    private final JLabel myAnswer;
    private final JLabel myResult;
    protected String myCurrentAnswer;

    public TextPanel()
    {
        this.myQuestion = new JLabel("Question:");
        this.myQuestionText = new JTextField(55);
        this.myAnswer=new JLabel("Please type your answer:");
        myAnswer.setForeground(Color.BLACK);
        myQuestion.setForeground(Color.BLACK);
        this.myInputText = new JTextField(50);
        JButton myButton = new JButton("Enter");
        this.myResult = new JLabel("Result:");
        myResult.setForeground(Color.BLACK);
        this.myResultText = new JTextField(50);
        this.add(this.myQuestion);
        this.add(this.myQuestionText);
        this.add(this.myAnswer);
        this.add(this.myInputText);
        this.add(myButton);
        this.add(this.myResult);
        this.add(this.myResultText);
        makeButtonListener(myButton);
        this.myQuestionText.setEnabled(true);
        this.myInputText.setEnabled(true);
        this.myResultText.setEnabled(true);
        this.setBackground(Color.LIGHT_GRAY);
        this.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
    }

    public void addQuestionText(final String theText) {
        this.myQuestionText.setText(theText);
    }

    public void addResultText(final String theText) {
        this.myResultText.setText(theText);
    }

    public void clearText()
    {
        this.myInputText.setText("");
        this.myResultText.setText("");
    }

    public void makeButtonListener(final JButton theButton)
    {
        theButton.addActionListener(e ->
        {
            if (this.myInputText.getText().equalsIgnoreCase(this.myCurrentAnswer))
            {
                switch (Frame.myCur)
                {
                    case "n" ->
                            {
                                addResultText("Correct!");
                                myController.askDirection("n");
                                Frame.myMazeView.decrementY();
                            }
                    case "e" ->
                            {
                                addResultText("Correct!");
                                myController.askDirection("e");
                                Frame.myMazeView.incrementX();
                            }
                    case "s" ->
                            {
                                addResultText("Correct!");
                                myController.askDirection("s");
                                Frame.myMazeView.incrementY();
                            }
                    case "w" ->
                            {
                                addResultText("Correct!");
                                myController.askDirection("w");
                                Frame.myMazeView.decrementX();
                            }
                }
            }
            else if (myCur == null)
            {
                addResultText("You can't go this way!");
            }
            else
            {
                addResultText("Wrong! This door is locked!");
                Frame.myController.findRoom(myCur).setStatus(Cell.RoomStatus.SEALED);
                endGameLost();
            }
            endGameWon();
            Frame.myMazeView.validate();
            Frame.myMazeView.repaint();
            Frame.myCur = null;
        });
    }

    private void endGameWon()
    {
        if (myController.getGameMaze().isGameOver())
        {
            this.myResultText.setText("You Win!");
            validate();
            repaint();
            final ImageIcon winImage = new ImageIcon
                    (new ImageIcon(Objects.requireNonNull(getClass().getResource("/GUIPictures/win.png")))
                            .getImage().getScaledInstance(300,300,Image.SCALE_DEFAULT));
            JOptionPane.showMessageDialog(null,"",
                    "You Win!",JOptionPane.PLAIN_MESSAGE,winImage);
        }
    }
    private void endGameLost()
    {
        if (!myController.getGameMaze().hasRoute())
        {
            this.myResultText.setText("You Lose!");
            validate();
            repaint();
            final ImageIcon loseImage = new ImageIcon
                    (new ImageIcon(Objects.requireNonNull(getClass().getResource("/GUIPictures/lose.png")))
                            .getImage().getScaledInstance(300,300,Image.SCALE_DEFAULT));
            JOptionPane.showMessageDialog(null,"",
                    "You Lose!",JOptionPane.PLAIN_MESSAGE,loseImage);
        }
    }
}