package GUI;

import TriviaMaze.Cell;
import TriviaMaze.Question.Question;

import javax.swing.*;
import java.awt.*;

import static GUI.Frame.myController;
import static GUI.Frame.myCur;

public class TextPanel extends JPanel {

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
        this.setSize(200, 200);
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
            } else {
                addText("Wrong! This door is locked!");
                Frame.myController.findRoom(myCur).setStatus(Cell.RoomStatus.SEALED);
            }
            Frame.mazeView.repaint();
            Frame.myCur = null;
        });
    }
}
