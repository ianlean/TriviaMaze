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

    private final JTextField myOutputText;
    private final JTextField myInputText;
    protected String myCurrentAnswer;

    public TextPanel() {
        this.myOutputText = new JTextField(50);
        this.myInputText = new JTextField(50);
        JButton myButton = new JButton("Enter");
        this.add(myOutputText);
        this.add(myInputText);
        this.add(myButton);
        makeButtonListener(myButton);
        this.myOutputText.setEnabled(true);
        this.myInputText.setEnabled(true);
        this.setBackground(Color.black);
    }

    public void addText(final String theText) {
        this.myOutputText.setText(theText);
    }

//    public String getText() {
//        return myInputText.getText();
//    }

    public void makeButtonListener(final JButton theButton)
    {
        theButton.addActionListener(e -> {
            if (this.myInputText.getText().equalsIgnoreCase(this.myCurrentAnswer))
            {
                switch (Frame.myCur)
                {
                    case "n" ->
                    {
                        addText("Correct!");
                        myController.askDirection("n");
                        Frame.mazeView.decrementY();
                    }
                    case "e" ->
                    {
                        addText("Correct!");
                        myController.askDirection("e");
                        Frame.mazeView.incrementX();
                    }
                    case "s" ->
                    {
                        addText("Correct!");
                        myController.askDirection("s");
                        Frame.mazeView.incrementY();
                    }
                    case "w" ->
                    {
                        addText("Correct!");
                        myController.askDirection("w");
                        Frame.mazeView.decrementX();
                    }
                }
            }
            else if (myCur == null)
            {
                addText("You can't go this way!");
            }
            else
            {
                addText("Wrong! This door is locked!");
                Frame.myController.findRoom(myCur).setStatus(Cell.RoomStatus.SEALED);
                endGameLost();
            }
            endGameWon();
            Frame.mazeView.validate();
            Frame.mazeView.repaint();
            Frame.myCur = null;
        });
    }

    private void endGameWon()
    {
        if (myController.getGameMaze().isGameOver())
        {
            this.removeAll();
            JTextField endText = new  JTextField(50);
            endText.setEnabled(true);
            endText.setText("You won!");
            this.add(endText);
            validate();
            repaint();
        }
    }
    private void endGameLost()
    {
        if (!myController.getGameMaze().hasRoute())
        {
            this.removeAll();
            JTextField endText = new  JTextField(50);
            endText.setEnabled(true);
            endText.setText("You lost!");
            this.add(endText);
            validate();
            repaint();
        }
    }
}
