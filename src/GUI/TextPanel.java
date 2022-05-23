package GUI;

import TriviaMaze.Cell;
import TriviaMaze.Question.Question;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;

import static GUI.Frame.*;

public class TextPanel extends JPanel implements Serializable
{

    private final JTextField myOutputText;
    private final JTextField myInputText;
    String currentAnswer;

    public TextPanel() {
        this.myOutputText = new JTextField(50);
        this.myInputText = new JTextField(50);
        JButton myButton = new JButton("Enter");
        this.add(myOutputText);
        this.add(myInputText);
        this.add(myButton);
        makeButtonListener(myButton);
        myOutputText.setEnabled(true);
        myInputText.setEnabled(true);
        this.setBackground(Color.black);
    }

    public void addText(String theText) {
        this.myOutputText.setText(theText);
    }

    public String getText() {
        return myInputText.getText();
    }

    public void makeButtonListener(final JButton theButton) {
        theButton.addActionListener(e -> {
            if (myInputText.getText().equalsIgnoreCase(currentAnswer)) {
                switch (Frame.myCur) {
                    case "n" -> {
                        addText("Correct!");
                        myController.askDirection("n");
                        Frame.mazeView.decrementY();
                    }
                    case "e" -> {
                        addText("Correct!");
                        myController.askDirection("e");
                        Frame.mazeView.incrementX();
                    }
                    case "s" -> {
                        addText("Correct!");
                        myController.askDirection("s");
                        Frame.mazeView.incrementY();
                    }
                    case "w" -> {
                        addText("Correct!");
                        myController.askDirection("w");
                        Frame.mazeView.decrementX();
                    }
                }
            } else if (myCur == null) {
                addText("You can't go this way!");
            } else {
                addText("Wrong! This door is locked!");
                Frame.myController.findRoom(myCur).setStatus(Cell.RoomStatus.SEALED);
            }
            endGame();
            Frame.mazeView.repaint();
            Frame.myCur = null;
        });
    }

    private void endGame() {
        if (myController.getGameMaze().isGameOver()) {
            this.removeAll();
            JTextField endText = new  JTextField(50);
            endText.setEnabled(true);
//            endText.setFont(new Font("Tahoma", Font.BOLD, 40));
            endText.setText("You won!");
            this.add(endText);
            validate();
            repaint();
        }
    }
}
